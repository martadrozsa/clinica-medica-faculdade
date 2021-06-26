package model.business;

import DAO.AgendamentoDAO;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.entity.Agendamento;
import model.entity.Medico;
import model.entity.Paciente;
import model.entity.enums.Consultorio;
import model.entity.enums.Periodo;
import static model.entity.enums.Periodo.MATUTINO;
import wrapper.AgendamentoWrapper;

public class AgendamentoBusiness {
    
    private AgendamentoDAO agendamentoDAO;
    private MedicoBusiness medicoBusiness;
    private PacienteBusiness pacienteBusiness;
    private AgendamentoWrapper agendamentoWrapper;

    public AgendamentoBusiness() {
        agendamentoDAO = new AgendamentoDAO();
        medicoBusiness = new MedicoBusiness();
        pacienteBusiness = new PacienteBusiness();
        agendamentoWrapper = new AgendamentoWrapper();
    }
    
    
    public boolean saveAgendamento(Agendamento agendamento) {
        boolean isSuccess = agendamentoDAO.insertAgendamento(agendamento);
        return isSuccess;
    }
   
    

    public boolean deleteAgendamentoFromBD(int id) {
        return agendamentoDAO.deleteAgendamentoById(id);
    }
    
    
    
    
    public List<AgendamentoWrapper> getListaAgendamento(Date dataAgendamento) {
        // todosAgendamentos (AgendamentoWrapper) <- todos os slots de agendamentos
        List<AgendamentoWrapper> todosAgendamentos = new ArrayList<>();
        
        List<Medico> listaMedico = medicoBusiness.getMedicos();
        
        // Para cada médico existente na base, cria os slots disponíveis de agendamentos do médico e adiciona na lista de "todos Agendamentos"
        for (int i = 0; i < listaMedico.size(); i++) {
            
            Medico medico = listaMedico.get(i);
            List<AgendamentoWrapper> novosAgendamentos = getAgendaMedico(medico, dataAgendamento);
            todosAgendamentos.addAll(novosAgendamentos); 
        }
            
        // agendamentosDoDia (Agendamento) <- slots  de agendamento ocupado
        List<Agendamento> agendamentosDoDia = agendamentoDAO.getAgendamentosDoDia(dataAgendamento);
            
        for (int i = 0; i < todosAgendamentos.size(); i++) {
            AgendamentoWrapper wrapper = todosAgendamentos.get(i);
            
            Agendamento agendamento = encontraAgendamentoParaWrapper(wrapper, agendamentosDoDia);
            if (agendamento == null) {
                continue;
            }
            
            int idPaciente = agendamento.getIdPaciente();
            Paciente paciente = pacienteBusiness.getPacienteById(idPaciente);
            String nomePaciente = paciente.getNome();
            wrapper.setNomePaciente(nomePaciente);
            
            wrapper.setIdPaciente(idPaciente);
            
            int idAgendamento = agendamento.getId();
            wrapper.setIdAgendamento(idAgendamento);
        }
        return todosAgendamentos;
    }
    
    
    // se encontrar, retorna agendamento; se não, retorna null
    public Agendamento encontraAgendamentoParaWrapper(AgendamentoWrapper wrapper, List<Agendamento> agendamentosDoDia) {
        
        // buscar um agendamento que tenha um horarioAgendamento e idMedico iguais ao do wrapper.
        // se encontrar, retornar o agendamento. se nao encontrar, retorna null
        for (int i = 0; i < agendamentosDoDia.size(); i++) {
            Agendamento currAgendamento = agendamentosDoDia.get(i);
            
            if (wrapper.getHorarioAgendamento().equals(currAgendamento.getHorario()) &&
                wrapper.getIdMedico() == currAgendamento.getIdMedico()
            ) {
                return currAgendamento;
            }
        }
        return null;
    }
    
    
    public List<AgendamentoWrapper> getAgendaMedico(Medico medico, Date dataAgendamento) {
        
        List<AgendamentoWrapper> agendamentos = new ArrayList<>();
       
        String nome = medico.getNome();
        String especialidade = medico.getEspecialidade();
        Consultorio consultorio = medico.getConsultorio();
        Periodo periodoMedico = medico.getPeriodo();
        String nomePaciente = "";
        int idMedico = medico.getId();
           
        if(periodoMedico.equals(MATUTINO)){
            for (int i = 7; i  <= 12; i++) {
                String currTime = "0" + i + ":00:00";
                Time time = Time.valueOf(currTime);
                
                AgendamentoWrapper agendamento = new AgendamentoWrapper(time, nome, especialidade, consultorio, nomePaciente, idMedico);
                agendamentos.add(agendamento);
            }

        } else {
            for (int i = 13; i <= 18; i++) {
                String currTime = "0" + i + ":00:00";
                Time time = Time.valueOf(currTime);
                AgendamentoWrapper agendamento = new AgendamentoWrapper(time, nome, especialidade, consultorio, nomePaciente, idMedico);
                agendamentos.add(agendamento);
            }
        }
        return agendamentos;
    }
    
   
    // verifica se tem nome e date, se houver nome e date, chama o metodo no DAO que tem nome e date
    // se houver apenas o nome, chama o metodo do DAO que tem nome
    // se houuver apenas date, chama o metodo do DAO que tem date
    
    public List<AgendamentoWrapper> getListaAgendamentoWrapper(String nome, Date dataAgendamento) {

        if(dataAgendamento != null && nome != null && !nome.equals("")) {
            return agendamentoDAO.getAgendamentosWrapperDoDiaByNomeEByData(nome, dataAgendamento);
        } else if (dataAgendamento != null) {
            return agendamentoDAO.getAgendamentosWrapperDoDia(dataAgendamento);
        } else if (nome != null && !nome.equals("")){
            return agendamentoDAO.getAgendamentosWrapperDoDiaByNome(nome);
        } else {
            return new ArrayList<>();
        }
    }
    

   
}
