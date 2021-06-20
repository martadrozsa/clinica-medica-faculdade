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
 
    public List<AgendamentoWrapper> getListaAgendamento(Date dataAgendamento) {

        List<AgendamentoWrapper> todosAgendamentos = new ArrayList<>();
        
        List<Medico> listaMedico = medicoBusiness.getMedicos();
        
        // Para cada médico existente na base, cria os slots disponíveis de agendamentos do médico e adiciona na lista de "todos Agendamentos"
        for (int i = 0; i < listaMedico.size(); i++) {
            
            Medico medico = listaMedico.get(i);
            List<AgendamentoWrapper> novosAgendamentos = getAgendaMedico(medico, dataAgendamento);
            todosAgendamentos.addAll(novosAgendamentos); 
        }
        
        // Pegar lista de agendamentos da base que existem  para dataAgendamento.
        // -> criar metodo no DAO para buscar agendamentos na data passada (recebe um date)
        // SELECT * FROM clinica_medica.agendamento WHERE data="2021-06-08"
        
        // agendamentosDoDia (Agendamento) <- slots  de agendamento ocupado
        // todosAgendamentos (AgendamentoWrapper) <- todos os slots de agendamentos
        
        // Dados mocks (como se tivessem vindo da base)
        
        List<Agendamento> agendamentosDoDia = agendamentoDAO.getAgendamentosDoDia(dataAgendamento);
            
        for (int i = 0; i < todosAgendamentos.size(); i++) {
            AgendamentoWrapper wrapper = todosAgendamentos.get(i);
            
            Agendamento agendamento = encontraAgendamentoParaWrapper(wrapper, agendamentosDoDia);
            if (agendamento == null) {
                continue;
            }
            
            int idPaciente = agendamento.getIdPaciente();
            Paciente paciente = pacienteBusiness.getMinhaListaById(idPaciente);
            String nomePaciente = paciente.getNome();
            wrapper.setNomePaciente(nomePaciente);           
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
//            String currTime = "0" + i + ":00:00";
//            valueOf(currTime)
//            new wrapper passando os dados
//            adicionar o wrapper no acumulador

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
}
