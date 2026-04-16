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

            PreparedStatement eliminarArtistaPadre = conexion.prepareStatement("DELETE FROM Artista WHERE ID_artista = ?");
            eliminarArtistaPadre.setInt(1, ID_artista);
            eliminarArtistaPadre.executeUpdate();

            PreparedStatement eliminarArtista = conexion.prepareStatement("DELETE FROM CONCIERTO WHERE ID_artista = ?");
            eliminarArtista.setInt(1, ID_artista);
            eliminarArtista.executeUpdate();

            /*METER ESTO EN EL MAIN

            boolean id_valido = false;
            while (id_valido) {
                System.out.println("Ingresa el ID del artista a eliminar: ");
                try {
                    PreparedStatement comprobarID = conexion.prepareStatement("SELECT id_artista FROM artista WHERE id_artista = ?");
                    comprobarID.setInt(1, ID_artista);
                } catch (Exception e) {
                    System.out.println("ID inválido. Introduce un número correcto.");
                }
            }*/
            conexion.commit();
            System.out.println("Artista eliminado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al agregar artista --> " + e.getMessage());
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
