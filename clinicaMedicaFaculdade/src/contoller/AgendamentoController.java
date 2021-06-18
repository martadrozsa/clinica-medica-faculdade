package contoller;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import model.business.AgendamentoBusiness;
import model.entity.Agendamento;

public class AgendamentoController {
    
    private AgendamentoBusiness agendamentoBusinnes;

    public AgendamentoController() {
        this.agendamentoBusinnes = new AgendamentoBusiness();
    }
   
    
    public String[][] getAgendamentosByDate(Date dataAgendamento){
        
        List<Agendamento> listaAgendamento = agendamentoBusinnes.getListaAgendamento(dataAgendamento);
        String[][] matrizAgendamentos = new String[listaAgendamento.size()][5];
        
        for(int i = 0; i < listaAgendamento.size(); i++) {
            
            Agendamento agendamentos = listaAgendamento.get(i);
            
            matrizAgendamentos[i][0] = agendamentos.getHorario() + "";
            matrizAgendamentos[i][1] = agendamentos.getIdMedico() + "";
            matrizAgendamentos[i][2] = agendamentos.getIdPaciente() + "";
            
            matrizAgendamentos[i][3] = "Consultorio A"; 
            matrizAgendamentos[i][4] = "Podólogo"; 
        }
        return matrizAgendamentos;
    }
    

}
