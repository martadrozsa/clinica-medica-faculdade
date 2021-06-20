package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import model.entity.Agendamento;


public class AgendamentoDAO {
    
    //declarando as variáveis. Não estão inicializadas. Até serem inicializadas o valor é null e não podems e utilizadas.
    private Connection connect;
    private Statement statement;
    
    // construtor AgendamentoDAO
    public AgendamentoDAO() {
        connectToDatabase("localhost", 3306, "clinica_medica", "root", "pass");
    }
    
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
    
    public List<Agendamento> getListaAgendamentos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean insertAgendamento(Agendamento agendamento) {
        try {
            String insertStatement = "INSERT INTO agendamento (horario, data, id_medico, id_paciente) VALUES (?, ?, ?, ?)";
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
}
