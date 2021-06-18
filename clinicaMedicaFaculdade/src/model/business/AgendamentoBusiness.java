package model.business;

import DAO.AgendamentoDAO;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.entity.Agendamento;
import model.entity.Medico;
import model.entity.Paciente;
import model.entity.enums.Periodo;
import static model.entity.enums.Periodo.MATUTINO;

public class AgendamentoBusiness {
    
    private AgendamentoDAO agendamentoDAO;
    private MedicoBusiness medicoBusiness;

    public AgendamentoBusiness() {
        this.agendamentoDAO = new AgendamentoDAO();
        this.medicoBusiness = new MedicoBusiness();
    }
    

    
    public List<Agendamento> getListaAgendamento(Date dataAgendamento) {

        List<Medico> listaMedico = medicoBusiness.getMedicos();
        
        List<Agendamento> todosAgendamentos = new ArrayList<>();
        
        for (int i = 0; i < listaMedico.size(); i++) {
            
            Medico medico = listaMedico.get(i);
            List<Agendamento> novosAgendamentos = getAgendaMedico(medico, dataAgendamento);
            todosAgendamentos.addAll(novosAgendamentos); 
        }
        return todosAgendamentos;

    }
    
    public List<Agendamento> getAgendaMedico(Medico medico, Date dataAgendamento) {
        
        List<Agendamento> agendamentos = new ArrayList<>();
        
        Time sqlTime1  = Time.valueOf("07:00:00");
        Time sqlTime2  = Time.valueOf("08:00:00");
        Time sqlTime3  = Time.valueOf("09:00:00");
        Time sqlTime4  = Time.valueOf("10:00:00");
        Time sqlTime5  = Time.valueOf("11:00:00");
        Time sqlTime6  = Time.valueOf("12:00:00");
        Time sqlTime7  = Time.valueOf("13:00:00");
        Time sqlTime8  = Time.valueOf("14:00:00");
        Time sqlTime9  = Time.valueOf("15:00:00");
        Time sqlTime10  = Time.valueOf("16:00:00");
        Time sqlTime11  = Time.valueOf("17:00:00");
        Time sqlTime12  = Time.valueOf("18:00:00");
        
        int idMedico = medico.getId();
        Periodo periodoMedico = medico.getPeriodo();
        
        if(periodoMedico.equals(MATUTINO)){
            Agendamento agendamento1 = new Agendamento(dataAgendamento, sqlTime1, idMedico, 0);
            Agendamento agendamento2 = new Agendamento(dataAgendamento, sqlTime2, idMedico, 0);
            Agendamento agendamento3 = new Agendamento(dataAgendamento, sqlTime3, idMedico, 0);
            Agendamento agendamento4 = new Agendamento(dataAgendamento, sqlTime4, idMedico, 0);
            Agendamento agendamento5 = new Agendamento(dataAgendamento, sqlTime5, idMedico, 0);
            Agendamento agendamento6 = new Agendamento(dataAgendamento, sqlTime6, idMedico, 0);
            agendamentos.add(agendamento1);
            agendamentos.add(agendamento2);
            agendamentos.add(agendamento3);
            agendamentos.add(agendamento4);
            agendamentos.add(agendamento5);
            agendamentos.add(agendamento6);
        } else {
            Agendamento agendamento7 = new Agendamento(dataAgendamento, sqlTime7, idMedico, 0);
            Agendamento agendamento8 = new Agendamento(dataAgendamento, sqlTime8, idMedico, 0);
            Agendamento agendamento9 = new Agendamento(dataAgendamento, sqlTime9, idMedico, 0);
            Agendamento agendamento10 = new Agendamento(dataAgendamento, sqlTime10, idMedico, 0);
            Agendamento agendamento11 = new Agendamento(dataAgendamento, sqlTime11, idMedico, 0);
            Agendamento agendamento12 = new Agendamento(dataAgendamento, sqlTime12, idMedico, 0);
            agendamentos.add(agendamento7);
            agendamentos.add(agendamento8);
            agendamentos.add(agendamento9);
            agendamentos.add(agendamento10);
            agendamentos.add(agendamento11);
            agendamentos.add(agendamento12);
        }
        return agendamentos;
        
    }
   


}
