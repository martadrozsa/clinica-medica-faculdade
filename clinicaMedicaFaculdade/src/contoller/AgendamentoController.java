package contoller;

import static contoller.AgendamentoController.DadosMatrizAgendamento.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import model.business.AgendamentoBusiness;
import model.entity.Agendamento;
import model.entity.enums.Consultorio;
import wrapper.AgendamentoWrapper;

public class AgendamentoController {
    private final AgendamentoBusiness agendamentoBusinnes;

    public AgendamentoController() {
        agendamentoBusinnes = new AgendamentoBusiness();
    }
    
    public boolean cadastrarAgendamento(Date dataAgendamento, Time horarioAgendamento, int idMedico, int idPaciente){
        Agendamento agendamento = new Agendamento(dataAgendamento, horarioAgendamento, idMedico, idPaciente);
        return agendamentoBusinnes.saveAgendamento(agendamento);
    }
    
    public boolean apagarAgendamento(int id) {
        return agendamentoBusinnes.deleteAgendamentoFromBD(id);
    }
   
    // usado pela tela de agendamento
    public String[][] getAgendamentosByDate(Date dataAgendamento){
        
        List<AgendamentoWrapper> listaAgendamentosWrappers = agendamentoBusinnes.getListaAgendamento(dataAgendamento);
        String[][] matrizAgendamentos = new String[listaAgendamentosWrappers.size()][8];
        
        for(int i = 0; i < listaAgendamentosWrappers.size(); i++) {
            
            AgendamentoWrapper agendamentoWrapper = listaAgendamentosWrappers.get(i);
            
            String consultorio = "";
            if (agendamentoWrapper.getConsultorio().equals(Consultorio.CONSULTORIO_1)) {
                consultorio = "Consultório 1";
            }
            else {
                consultorio = "Consultório 2";
            }            
            
            matrizAgendamentos[i][HORARIO.getIndex()] = agendamentoWrapper.getHorarioAgendamento()+ "";
            matrizAgendamentos[i][NOME_MEDICO.getIndex()] = agendamentoWrapper.getNomeMedico()+ "";
            matrizAgendamentos[i][ESPECIALIDADE.getIndex()] = agendamentoWrapper.getEspecialidade() + "";
            matrizAgendamentos[i][CONSULTORIO.getIndex()] = consultorio;
            matrizAgendamentos[i][NOME_PACIENTE.getIndex()] = agendamentoWrapper.getNomePaciente() + "";
            
            matrizAgendamentos[i][ID_MEDICO.getIndex()] = agendamentoWrapper.getIdMedico() + "";
            matrizAgendamentos[i][ID_AGENDAMENTO.getIndex()] = agendamentoWrapper.getIdAgendamento() + "";
            matrizAgendamentos[i][ID_PACIENTE.getIndex()] = agendamentoWrapper.getIdPaciente() + "";
        }

        return matrizAgendamentos;
    }
    
    // usado pela tela de consulta agendamento
    public String[][] getAgendamentosByDateConsulta(String nome, Date dataAgendamento){
        
        List<AgendamentoWrapper> listaAgendamentosWrappers = agendamentoBusinnes.getListaAgendamentoWrapper(nome, dataAgendamento);
        String[][] matrizAgendamentosWrappers = new String[listaAgendamentosWrappers.size()][9];
        
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
            matrizAgendamentosWrappers[i][8] = agendamentoWrapper.getIdMedico() + "";
        }
        return matrizAgendamentosWrappers;
    }

    public enum DadosMatrizAgendamento {
        HORARIO(0),
        NOME_MEDICO(1),
        ESPECIALIDADE(2),
        CONSULTORIO(3),
        NOME_PACIENTE(4),
        ID_MEDICO(5),
        ID_AGENDAMENTO(6),
        ID_PACIENTE(7)
        ;
        
        private final int index;
        
        DadosMatrizAgendamento(int index) {
            this.index = index;
        }
        
        public int getIndex() {
            return index;
        }
    }
}
