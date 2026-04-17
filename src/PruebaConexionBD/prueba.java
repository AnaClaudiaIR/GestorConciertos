package PruebaConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class prueba {
    public static void main(String[] args){
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "ana";
        String password = "ana";

        try(Connection conn = DriverManager.getConnection(url, user, password)){
            System.out.println("Conexión establecida.");
        } catch (SQLException e){
            System.out.println("ERROR --> "+e.getMessage());
        }
    }
}
