
package model.entity;

import java.sql.Time;
import java.util.Date;

public class Agendamento {
    
    int id;
    Date dataAgendamento;
    Time horarioAgendamento;
    int idMedico;
    int idPaciente;

    public Agendamento() {
    }

    public Agendamento(int id, Date dataAgendamento, Time horarioAgendamento, int idMedico, int idPaciente) {
        this.id = id;
        this.dataAgendamento = dataAgendamento;
        this.horarioAgendamento = horarioAgendamento;
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
    }
    
    public Agendamento(Date dataAgendamento, Time horarioAgendamento, int idMedico, int idPaciente) {
        this.dataAgendamento = dataAgendamento;
        this.horarioAgendamento = horarioAgendamento;
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return dataAgendamento;
    }

    public void setData(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Time getHorario() {
        return horarioAgendamento;
    }

    public void setHorario(Time horarioAgendamento) {
        this.horarioAgendamento = horarioAgendamento;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    
  
}
