package Metodos.Concierto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EliminarConcierto {
    public static void eliminarConcierto(int id_concierto) {
        Connection conexion = null;
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "ana";
        String password = "ana";

        try {
            conexion = DriverManager.getConnection(url, user, password);
            conexion.setAutoCommit(false);

            //Eliminar primero de la tabla padre para evitar errores
            PreparedStatement eliminarConciertoPadre = conexion.prepareStatement("DELETE FROM Concierto WHERE id_concierto=?");
            eliminarConciertoPadre.setInt(1, id_concierto);
            eliminarConciertoPadre.executeUpdate();

            //Eliminar los datos de la tabla de entrada
            PreparedStatement eliminarConcierto = conexion.prepareStatement("DELETE FROM Entrada WHERE id_concierto=?");
            eliminarConcierto.setInt(1, id_concierto);
            eliminarConcierto.executeUpdate();

            conexion.commit();
            System.out.println("Concierto eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar concierto --> " + e.getMessage());
            if (conexion != null) {
                try {
                    conexion.rollback();
                } catch (SQLException ex) {
                    System.out.println("Error al hacer rollback --> " + ex.getMessage());
                }
            }
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException ex) {
                    System.out.println("Error al cerrar la conexión --> " + ex.getMessage());
                }
            }
        }
    }
}
