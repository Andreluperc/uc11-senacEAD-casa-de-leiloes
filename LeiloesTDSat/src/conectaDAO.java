
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
public class conectaDAO {

    private Connection conn;

    public Connection connectDB() throws ClassNotFoundException {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/uc11?user=root&password=Alrm100694.");
            System.out.println("Conectou");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }

        return conn;
    }
    
    public void desconectar() {

        try {
            
            if (conn != null && !conn.isClosed()){
                
                conn.close();
                System.out.println("Desconectado");
            
            }
        } catch (SQLException se) {
            
            System.out.println("Problema ao desconectar do banco." + se);

        }
        
    }

    public Connection getConn() {
        return conn;
    }

}
