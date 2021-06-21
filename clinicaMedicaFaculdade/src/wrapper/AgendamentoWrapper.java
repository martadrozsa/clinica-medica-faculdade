package wrapper;

import java.sql.Time;
import java.util.Date;
import model.entity.enums.Consultorio;

public class AgendamentoWrapper {
    
    private Time horarioAgendamento;
    private String nomeMedico;
    private String especialidade;
    private Consultorio consultorio;
    private String nomePaciente;
    private int idMedico;
    private Date dataNascimento;
    private Date dataAgendamento;

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

    public AgendamentoWrapper(String nomePaciente, Date dataNascimento, Time horarioAgendamento, Date dataAgendamento, String nomeMedico, Consultorio consultorio) {
        this.nomePaciente = nomePaciente;
        this.dataNascimento = dataNascimento;
        this.horarioAgendamento = horarioAgendamento;
        this.dataAgendamento = dataAgendamento;
        this.nomeMedico = nomeMedico;
        this.consultorio = consultorio;
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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }
 
}
