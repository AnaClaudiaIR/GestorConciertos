package Metodos.Artista;

import Clases.Artista;

import java.sql.*;

public class EliminarArtista {
    public static void eliminarArtista(int ID_artista) {
        Connection conexion = null;
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "ana";
        String password = "ana";

        try {
            conexion = DriverManager.getConnection(url, user, password);
            conexion.setAutoCommit(false);

            //Eliminar primero de la tabla principal (padre)
            PreparedStatement eliminarArtistaPadre = conexion.prepareStatement("DELETE FROM Artista WHERE ID_artista = ?");
            eliminarArtistaPadre.setInt(1, ID_artista);
            eliminarArtistaPadre.executeUpdate();

            //Eliminar los datos de la tabla concierto
            PreparedStatement eliminarArtista = conexion.prepareStatement("DELETE FROM CONCIERTO WHERE ID_artista = ?");
            eliminarArtista.setInt(1, ID_artista);
            eliminarArtista.executeUpdate();

            conexion.commit();
            System.out.println("Artista eliminado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al eliminar artista --> " + e.getMessage());
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
