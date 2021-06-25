package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.entity.Agendamento;
import model.entity.enums.Consultorio;
import wrapper.AgendamentoWrapper;


public class AgendamentoDAO {
    
    //declarando as variáveis. Não estão inicializadas. Até serem inicializadas o valor é null e não podems e utilizadas.
    private Connection connect;
    private Statement statement;
    
    
    // construtor AgendamentoDAO
    public AgendamentoDAO() {
        connectToDatabase("localhost", 3306, "clinica_medica", "root", "pass");
    }
    
    // conexão com a base de dados
    private void connectToDatabase(String ip, int port, String schema, String username, String password) {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connectionStr = "jdbc:mysql://" + ip + ":" + port + "/" + schema + "?user=" + username + "&password=" + password + "&serverTimezone=UTC&useTimezone=true";

           
            connect = DriverManager.getConnection(connectionStr);

            // Statements allow to issue SQL queries to the database - usado para fazer as querys
            statement = connect.createStatement();
            
        } catch (Exception ex) {
            System.out.println("Failed to connect to database: " + ex.toString());
        }
    }
     
    public boolean insertAgendamento(Agendamento agendamento) {
        String insertStatement = "INSERT INTO agendamento (horario, data, id_medico, id_paciente) VALUES (?, ?, ?, ?)";
        
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(insertStatement);

            java.sql.Date sqlDate = new java.sql.Date(agendamento.getData().getTime());

            preparedStatement.setTime(1, agendamento.getHorario());
            preparedStatement.setDate(2, sqlDate);
            preparedStatement.setInt(3, agendamento.getIdMedico());
            preparedStatement.setInt(4, agendamento.getIdPaciente());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception ex) {
            System.out.println("Error while inserting data: " + ex.toString());
            return false;
        }
        return true;
    }
    
    
    public boolean deleteAgendamentoById(int id) {
        try {
            String deleteStatement = "DELETE FROM agendamento WHERE id=?";
            PreparedStatement preparedStatement = connect.prepareStatement(deleteStatement);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (Exception ex) {
            System.out.println("Error while deleting data: " + ex.toString());
            return false;
        }
        return true;
    }
    
    
    
    
    // transforma as rows do database -> objetos em lista local
    // transforma os dados da tabela na base em dados (objetos) em uma lista
    public List<Agendamento> parseResultSetToAgendamento(ResultSet resultSet) throws SQLException {
        List<Agendamento> agendamentos = new ArrayList<>();
        
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            Date dataAgendamento = resultSet.getDate("data");
            Time horarioAgendamento = resultSet.getTime("horario");
            int idMedico = resultSet.getInt("id_medico");
            int idPaciente = resultSet.getInt("id_paciente");

            Agendamento agendamento = new Agendamento(dataAgendamento,horarioAgendamento, idMedico, idPaciente);
            agendamentos.add(agendamento);
        }
        return agendamentos;
    }
    
    // -> criar metodo no DAO para buscar agendamentos na data passada (recebe um date)
    // SELECT * FROM clinica_medica.agendamento WHERE data="2021-06-08"
    public List<Agendamento> getAgendamentosDoDia(Date dataAgendamento) {
        
        String queryStatement = "SELECT * FROM clinica_medica.agendamento WHERE data=?";

        try {
            PreparedStatement preparedStatement = connect.prepareStatement(queryStatement);
            
            java.sql.Date sqlDate = new java.sql.Date(dataAgendamento.getTime());
            
            preparedStatement.setDate(1, sqlDate);

            // Recupera dados da base
            ResultSet resultSet = preparedStatement.executeQuery();
            
            List<Agendamento> agendamentos = parseResultSetToAgendamento(resultSet);
            preparedStatement.close();
            // Todos os agendamentos na lista "agendamentos"
            return agendamentos;

        } catch (Exception ex) {
            System.out.println("Error while querying data: " + ex.toString());
            return new ArrayList<>();
        }
    }
    
    // transforma as rows do database -> objetos em lista local
    // transforma os dados vindos das tabelas Agendamento, Médico e paciente na base em dados (objetos) em uma lista
    public List<AgendamentoWrapper> parseResultSetToAgendamentoWrapper(ResultSet resultSet) throws SQLException {
        List<AgendamentoWrapper> agendamentosWrappers = new ArrayList<>();
        
        while (resultSet.next()) {
            String nomePaciente = resultSet.getString("nome_paciente");
            Date dataNascimento = resultSet.getDate("data_nascimento");
            Time horarioAgendamento = resultSet.getTime("horario");
            Date dataAgendamento = resultSet.getDate("data");
            String nomeMedico = resultSet.getString("nome_medico");
            String nomeConsultorio = resultSet.getString("consultorio");
            int idAgendamento = resultSet.getInt("id_agendamento");
            int idPaciente = resultSet.getInt("id_paciente");
            
            Consultorio consultorio = Consultorio.valueOf(nomeConsultorio);

            AgendamentoWrapper agendamentoWrapper = new AgendamentoWrapper(nomePaciente, dataNascimento, horarioAgendamento, dataAgendamento, nomeMedico, consultorio, idAgendamento, idPaciente);
            agendamentosWrappers.add(agendamentoWrapper);
        }
        return agendamentosWrappers;
    }

    public List<AgendamentoWrapper> getAgendamentosWrapperDoDia(Date dataAgendamento) {

        String queryStatement = "SELECT age.id as id_agendamento, pe.nome as nome_paciente, pe.data_nascimento, horario, data, me.nome as nome_medico, me.consultorio, pe.id as id_paciente " +
                                "FROM agendamento as age " +
                                "JOIN medico as me ON age.id_medico=me.id " +
                                "JOIN paciente as pe ON age.id_paciente=pe.id " +
                                "WHERE data=?";

        try {
            PreparedStatement preparedStatement = connect.prepareStatement(queryStatement);

            java.sql.Date sqlDate = new java.sql.Date(dataAgendamento.getTime());

            preparedStatement.setDate(1, sqlDate);

            // Recupera dados da base
            ResultSet resultSet = preparedStatement.executeQuery();

            List<AgendamentoWrapper> agendamentosWrappers = parseResultSetToAgendamentoWrapper(resultSet);
            preparedStatement.close();
            return agendamentosWrappers;

        } catch (Exception ex) {
            System.out.println("Error while querying data: " + ex.toString());
            return new ArrayList<>();
        }
    }
    
    public List<AgendamentoWrapper> getAgendamentosWrapperDoDiaByNome(String nome) {
        String termoBusca = "%" + nome + "%";
        String queryStatement = "SELECT age.id as id_agendamento, pe.nome as nome_paciente, pe.data_nascimento, horario, data, me.nome as nome_medico, me.consultorio, pe.id as id_paciente " +
                                "FROM agendamento as age " +
                                "JOIN medico as me ON age.id_medico=me.id " +
                                "JOIN paciente as pe ON age.id_paciente=pe.id " +
                                "WHERE pe.nome like ?";

        try {
            PreparedStatement preparedStatement = connect.prepareStatement(queryStatement);
            preparedStatement.setString(1, termoBusca);

            // Recupera dados da base
            ResultSet resultSet = preparedStatement.executeQuery();
            List<AgendamentoWrapper> agendamentosWrappers = parseResultSetToAgendamentoWrapper(resultSet);
            preparedStatement.close();
            return agendamentosWrappers;

        } catch (Exception ex) {
            System.out.println("Error while querying data: " + ex.toString());
            return new ArrayList<>();
        }
    }
    
    public List<AgendamentoWrapper> getAgendamentosWrapperDoDiaByNomeEByData(String nome, Date dataAgendamento) {
        String termoBusca = "%" + nome + "%";
        String queryStatement = "SELECT age.id as id_agendamento, pe.nome as nome_paciente, pe.data_nascimento, horario, data, me.nome as nome_medico, me.consultorio, pe.id as id_paciente " +
                                "FROM agendamento as age " +
                                "JOIN medico as me ON age.id_medico=me.id " +
                                "JOIN paciente as pe ON age.id_paciente=pe.id " +
                                "WHERE pe.nome like ? AND data=?";

        try {
            PreparedStatement preparedStatement = connect.prepareStatement(queryStatement);
            preparedStatement.setString(1, termoBusca);
            
            java.sql.Date sqlDate = new java.sql.Date(dataAgendamento.getTime());
            preparedStatement.setDate(2, sqlDate);

            // Recupera dados da base
            ResultSet resultSet = preparedStatement.executeQuery();
            List<AgendamentoWrapper> agendamentosWrappers = parseResultSetToAgendamentoWrapper(resultSet);
            preparedStatement.close();
            return agendamentosWrappers;

        } catch (Exception ex) {
            System.out.println("Error while querying data: " + ex.toString());
            return new ArrayList<>();
        }
    }
 


}
