package model.business;

import DAO.PacienteDAO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.entity.Paciente;


public class PacienteBusiness {
    
    private PacienteDAO pacienteDAO;

    public PacienteBusiness() {
        this.pacienteDAO = new PacienteDAO();
    }
    
     public List<Paciente> getMinhaLista() {
        return pacienteDAO.getMinhaListaPacientes();
    }
    
    public List<Paciente> getMinhaListaByNome(String nome) {
        return pacienteDAO.getMinhaListByNome(nome);
    }

    public boolean insertPacienteIntoBD(Paciente paciente) {
        boolean isSuccess = pacienteDAO.insertPaciente(paciente);
        return isSuccess;
    }
      
    public boolean updatePacienteInBD(Paciente paciente) {
        return pacienteDAO.updatePaciente(paciente);
    }

    public boolean deletePacienteFromBD(int id) {
        return pacienteDAO.deletePacienteById(id);
    }
    
    // método de pesquisa para a TelaPesquisaView e para a TelaBuscarPacienteView
    public List<Paciente> getMinhaLista(String inputPesquisa) {
        return pacienteDAO.getMinhaListByNome(inputPesquisa);
    }

}
