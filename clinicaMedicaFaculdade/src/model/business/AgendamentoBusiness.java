
package model.business;

import DAO.AgendamentoDAO;
import model.entity.Agendamento;

public class AgendamentoBusiness {
    
    private AgendamentoDAO agendamentoDAO;

    public AgendamentoBusiness(AgendamentoDAO agendamentoDAO) {
        this.agendamentoDAO = agendamentoDAO;
    }
    
    public boolean insertAgendamentoIntoBD(Agendamento agendamento) {
        boolean isSuccess = agendamentoDAO.insertAgendamento(agendamento);
        return isSuccess;
    }
}
