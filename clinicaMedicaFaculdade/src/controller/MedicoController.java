package controller;

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

    public boolean cadastrar(int crm, String especialidade, String nomePeriodo, String nomeConsultorio, String nome, String telefone) {
        // convertendo String em ENUM
        Periodo periodo = Periodo.valueOf(nomePeriodo);
        Consultorio consultorio = Consultorio.valueOf(nomeConsultorio);
        
        Medico medico = new Medico(crm, especialidade, periodo, consultorio, nome, telefone);
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
    
    // transformando os dados da base em uma matriz de texto para imprimir na tela
    @SuppressWarnings("unchecked")
    public String[][] getMinhaMatrizTexto() {
        
        List<Medico> minhaLista = medicoBusiness.getMinhaLista();
        
        String[][] matrizMedicos = new String[minhaLista.size()][7];
        
        for (int i = 0; i < minhaLista.size(); i++) {
            matrizMedicos[i][0] = minhaLista.get(i).getId() + "";
            matrizMedicos[i][1] = minhaLista.get(i).getNome();
            matrizMedicos[i][2] = minhaLista.get(i).getTelefone() + "";
            matrizMedicos[i][3] = minhaLista.get(i).getCrm() + "";
            matrizMedicos[i][4] = minhaLista.get(i).getEspecialidade();
            matrizMedicos[i][5] = minhaLista.get(i).getPeriodo().toString();
            matrizMedicos[i][6] = minhaLista.get(i).getConsultorio().toString();   
        }
        return matrizMedicos;
    }
  
}
