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
    

}
