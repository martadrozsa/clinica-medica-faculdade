package contoller;

import java.util.Date;
import java.util.List;
import model.business.MedicoBusiness;
import model.entity.Medico;
import model.entity.enums.Consultorio;
import model.entity.enums.Periodo;


public class MedicoController {

    private final MedicoBusiness medicoBusiness;

    public MedicoController() {
        this.medicoBusiness = new MedicoBusiness();
    }

    public boolean cadastrar(int crm, String especialidade, String nomePeriodo, String nomeConsultorio, String nome, Date dataNascimento, String endereco, String telefone) {
        // convertendo String em ENUM
        Periodo periodo = Periodo.valueOf(nomePeriodo);
        Consultorio consultorio = Consultorio.valueOf(nomeConsultorio);
        
        Medico medico = new Medico(crm, especialidade, periodo, consultorio, nome, dataNascimento, endereco, telefone);
        return medicoBusiness.insertMedicoIntoBD(medico);
    }

    public boolean editar(String nome, Date dataNascimento, String endereco, String telefone) {
        Medico paciente = new Medico();
        return medicoBusiness.updateMedicoInBD(paciente);
    }

    public boolean apagar(int id) {
        return medicoBusiness.deleteMedicoFromBD(id);
    }

    public List<Medico> getMinhaLista() {
        return medicoBusiness.getMinhaLista();
    }
    
    @SuppressWarnings("unchecked")
    public String[][] getMinhaMatrizTexto() {
        List<Medico> minhalista = medicoBusiness.getMinhaLista();
        int tamanho = minhalista.size();
        String[][] matrizPacientes = new String[tamanho][5];
        for (int i = 0; i < tamanho; i++) {
            matrizPacientes[i][0] = minhalista.get(i).getId() + "";
            matrizPacientes[i][1] = minhalista.get(i).getNome();
            matrizPacientes[i][2] = minhalista.get(i).getDataNascimento() + "";
            matrizPacientes[i][3] = minhalista.get(i).getEndereco();
            matrizPacientes[i][4] = minhalista.get(i).getTelefone() + "";
        }
        return matrizPacientes;
    }   
}