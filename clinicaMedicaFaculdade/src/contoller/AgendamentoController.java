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
            

            Consultorio cons = agendamentoWrapper.getConsultorio();
            String consultorio = cons.getNomeFormatado();          
            
            matrizAgendamentos[i][HORARIO.ordinal()] = agendamentoWrapper.getHorarioAgendamento()+ "";
            matrizAgendamentos[i][NOME_MEDICO.ordinal()] = agendamentoWrapper.getNomeMedico()+ "";
            matrizAgendamentos[i][ESPECIALIDADE.ordinal()] = agendamentoWrapper.getEspecialidade() + "";
            matrizAgendamentos[i][CONSULTORIO.ordinal()] = consultorio;
            matrizAgendamentos[i][NOME_PACIENTE.ordinal()] = agendamentoWrapper.getNomePaciente() + "";
            
            matrizAgendamentos[i][ID_MEDICO.ordinal()] = agendamentoWrapper.getIdMedico() + "";
            matrizAgendamentos[i][ID_AGENDAMENTO.ordinal()] = agendamentoWrapper.getIdAgendamento() + "";
            matrizAgendamentos[i][ID_PACIENTE.ordinal()] = agendamentoWrapper.getIdPaciente() + "";
        }

        return matrizAgendamentos;
    }
    
    // usado pela tela de consulta agendamento
    public String[][] getAgendamentosByDateConsulta(String nome, Date dataAgendamento){
        
        List<AgendamentoWrapper> listaAgendamentosWrappers = agendamentoBusinnes.getListaAgendamentoWrapper(nome, dataAgendamento);
        String[][] matrizAgendamentosWrappers = new String[listaAgendamentosWrappers.size()][10];
        
        for(int i = 0; i < listaAgendamentosWrappers.size(); i++) {
            
            AgendamentoWrapper agendamentoWrapper = listaAgendamentosWrappers.get(i);
                      
            
            Consultorio cons = agendamentoWrapper.getConsultorio();
            String consultorio = cons.getNomeFormatado();

            matrizAgendamentosWrappers[i][HORARIO.ordinal()] = agendamentoWrapper.getHorarioAgendamento()+ "";
            matrizAgendamentosWrappers[i][NOME_MEDICO.ordinal()] = agendamentoWrapper.getNomeMedico()+ "";            
            matrizAgendamentosWrappers[i][ESPECIALIDADE.ordinal()] = agendamentoWrapper.getEspecialidade() + "";
            matrizAgendamentosWrappers[i][CONSULTORIO.ordinal()] = consultorio;            
            matrizAgendamentosWrappers[i][NOME_PACIENTE.ordinal()] = agendamentoWrapper.getNomePaciente() + "";
            matrizAgendamentosWrappers[i][ID_MEDICO.ordinal()] = agendamentoWrapper.getIdMedico() + "";
            matrizAgendamentosWrappers[i][ID_AGENDAMENTO.ordinal()] = agendamentoWrapper.getIdAgendamento() + "";
            matrizAgendamentosWrappers[i][ID_PACIENTE.ordinal()] = agendamentoWrapper.getIdPaciente() + "";
            matrizAgendamentosWrappers[i][DATA_NASCIMENTO.ordinal()] = agendamentoWrapper.getDataNascimento() + "";
            matrizAgendamentosWrappers[i][DATA_AGENDAMENTO.ordinal()] = agendamentoWrapper.getDataAgendamento() + "";
        }
        return matrizAgendamentosWrappers;
    }

    public enum DadosMatrizAgendamento {
        HORARIO,
        NOME_MEDICO,
        ESPECIALIDADE,
        CONSULTORIO,
        NOME_PACIENTE,
        ID_MEDICO,
        ID_AGENDAMENTO,
        ID_PACIENTE,
        DATA_NASCIMENTO,
        DATA_AGENDAMENTO,
    }
}
