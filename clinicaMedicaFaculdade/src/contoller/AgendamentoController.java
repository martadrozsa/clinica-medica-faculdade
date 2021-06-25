package contoller;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import model.business.AgendamentoBusiness;
import model.entity.Agendamento;
import model.entity.enums.Consultorio;
import wrapper.AgendamentoWrapper;

public class AgendamentoController {
    
    private AgendamentoBusiness agendamentoBusinnes;
    private AgendamentoWrapper agendamentoWrapper;

    public AgendamentoController() {
        this.agendamentoBusinnes = new AgendamentoBusiness();
        this.agendamentoWrapper = new AgendamentoWrapper();
    }
    
    public boolean cadastrarAgendamento(Date dataAgendamento, Time horarioAgendamento, int idMedico, int idPaciente){
        Agendamento agendamento = new Agendamento(dataAgendamento, horarioAgendamento, idMedico, idPaciente);
        return agendamentoBusinnes.saveAgendamento(agendamento);
    }
    
    public boolean apagarAgendamento(int id) {
        return agendamentoBusinnes.deleteAgendamentoFromBD(id);
    }
   
    public String[][] getAgendamentosByDate(Date dataAgendamento){
        
        List<AgendamentoWrapper> listaAgendamentosWrappers = agendamentoBusinnes.getListaAgendamento(dataAgendamento);
        String[][] matrizAgendamentos = new String[listaAgendamentosWrappers.size()][6];
        
        for(int i = 0; i < listaAgendamentosWrappers.size(); i++) {
            
            AgendamentoWrapper agendamento = listaAgendamentosWrappers.get(i);
            
            String consultorio = "";
            if (agendamento.getConsultorio().equals(Consultorio.CONSULTORIO_1)) {
                consultorio = "Consultório 1";
            }
            else {
                consultorio = "Consultório 2";
            }            
            
            matrizAgendamentos[i][0] = agendamento.getHorarioAgendamento()+ "";
            matrizAgendamentos[i][1] = agendamento.getNomeMedico()+ "";
            matrizAgendamentos[i][2] = agendamento.getEspecialidade() + "";
            matrizAgendamentos[i][3] = consultorio;
            matrizAgendamentos[i][4] = agendamento.getNomePaciente() + "";
            
            matrizAgendamentos[i][5] = agendamento.getIdMedico() + "";
            
            
        }
        return matrizAgendamentos;
    }
    
    
        public String[][] getAgendamentosByDateConsulta(String nome, Date dataAgendamento){
        
        List<AgendamentoWrapper> listaAgendamentosWrappers = agendamentoBusinnes.getListaAgendamentoWrapper(nome, dataAgendamento);
        String[][] matrizAgendamentosWrappers = new String[listaAgendamentosWrappers.size()][8];
        
        for(int i = 0; i < listaAgendamentosWrappers.size(); i++) {
            
            AgendamentoWrapper agendamentoWrapper = listaAgendamentosWrappers.get(i);
            
            String consultorio = "";
            if (agendamentoWrapper.getConsultorio().equals(Consultorio.CONSULTORIO_1)) {
                consultorio = "Consultório 1";
            }
            else {
                consultorio = "Consultório 2";
            }            
            
            matrizAgendamentosWrappers[i][0] = agendamentoWrapper.getNomePaciente() + "";
            matrizAgendamentosWrappers[i][1] = agendamentoWrapper.getDataNascimento() + "";
            matrizAgendamentosWrappers[i][2] = agendamentoWrapper.getHorarioAgendamento()+ "";
            matrizAgendamentosWrappers[i][3] = agendamentoWrapper.getDataAgendamento() + "";
            matrizAgendamentosWrappers[i][4] = agendamentoWrapper.getNomeMedico()+ "";
            matrizAgendamentosWrappers[i][5] = consultorio;
            matrizAgendamentosWrappers[i][6] = agendamentoWrapper.getIdAgendamento() + "";
            matrizAgendamentosWrappers[i][7] = agendamentoWrapper.getIdPaciente() + "";
        }
        return matrizAgendamentosWrappers;
    }
    

}
