package wrapper;

import java.sql.Time;
import model.entity.enums.Consultorio;

public class AgendamentoWrapper {
    
    private Time horarioAgendamento;
    private String nomeMedico;
    private String especialidade;
    private Consultorio consultorio;
    private String nomePaciente;
    private int idMedico;

    public AgendamentoWrapper() {
    }

    public AgendamentoWrapper(Time horarioAgendamento, String nomeMedico, String especialidade, Consultorio consultorio, String nomePaciente, int idMedico) {
        this.horarioAgendamento = horarioAgendamento;
        this.nomeMedico = nomeMedico;
        this.especialidade = especialidade;
        this.consultorio = consultorio;
        this.nomePaciente = nomePaciente;
        this.idMedico = idMedico;
    }
    

    public Time getHorarioAgendamento() {
        return horarioAgendamento;
    }

    public void setHorarioAgendamento(Time horarioAgendamento) {
        this.horarioAgendamento = horarioAgendamento;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    
   
}
