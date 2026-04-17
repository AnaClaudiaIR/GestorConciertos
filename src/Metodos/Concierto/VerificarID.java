package Metodos.Concierto;

import java.sql.*;

public class VerificarID {
    public static void VerificarIDConcierto(int id_concierto_eliminar) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "ana";
        String password = "ana";

        boolean id_concierto_valido = false;

        try(Connection conn = DriverManager.getConnection(url, user, password)){
            while (!id_concierto_valido) {
                try {
                    PreparedStatement comprobarID = conn.prepareStatement("SELECT id_concierto FROM concierto WHERE id_concierto = ?");
                    comprobarID.setInt(1, id_concierto_eliminar);
                    ResultSet resultado = comprobarID.executeQuery();
                    if (resultado.next()) {
                        id_concierto_valido = true;
                        EliminarConcierto.eliminarConcierto(id_concierto_eliminar);
                    } else {
                        throw new Exception("Introduce un número correcto.");
                    }
                } catch (Exception e) {
                    System.out.println("ID inválido --> " +e.getMessage());
                }
            }
        } catch (SQLException e){
            System.out.println("ERROR al conectarse a base de datos --> "+e.getMessage());
        }
        id_concierto_valido = false;
    }
}
