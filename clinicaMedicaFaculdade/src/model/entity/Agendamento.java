
package model.entity;

import java.sql.Time;
import java.util.Date;

public class Agendamento {
    
    Date data;
    Time horario;
    int idMedico;
    int idPaciente;

    public Agendamento() {
    }

    public Agendamento(Date data, Time horario) {
        this.data = data;
        this.horario = horario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getHorario() {
        return horario;
    }

    public void setHorario(Time horario) {
        this.horario = horario;
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
