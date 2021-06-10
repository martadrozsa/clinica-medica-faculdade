package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PacienteDAO {
    
    private Connection connect;
    private Statement statement;
   
    
    public PacienteDAO() {
        connectToDatabase("localhost", 3306, "clinica_medica", "root", "pass");
    }
    
    private void connectToDatabase(String ip, int port, String schema, String username, String password) {
        try {
            // This will load the MySQL driver, each DB has its own driver
            String driver = ("com.mysql.cj.jdbc.Driver");
            Class.forName (driver);
            String connectionStr = "jdbc:mysql://" + ip + ":" + port + "/" + schema + "?user=" + username + "&password=" + password + "?useTimezone=true&serverTimezone=UTC";
           
            connect = DriverManager.getConnection(connectionStr);

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            
        } catch (Exception ex) {
            System.out.println("Failed to connect to database: " + ex.toString());
        }
    }
    
    public Connection getConexao() {
        Connection connection = null; //instância da conexão
        try {
        // Carregamento do JDBC Driver
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
        // Configurar a conexão
            String server = "localhost";
            String database = "db_alunos";
            String url = "jdbc:mysql://" + server + ":3306/" + database + "?useTimezone=true&serverTimezone=UTC";
            String user = "root";
            String password = "rootpass";
        
// Conectando..
            connection = DriverManager.getConnection(url, user, password);
        // Testando..
            if (connection != null) {
                System.out.println("Status: Conectado!");
            } else {
                System.out.println("Status: NÃO CONECTADO!");
            }
            return connection;
            
        } catch (ClassNotFoundException e) { //Driver não encontrado
            System.out.println("O driver nao foi encontrado.");
            return null;
        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar...");
            return null;
        }
    }
   
    
}
