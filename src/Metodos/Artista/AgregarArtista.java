package Metodos.Artista;

import Clases.Artista;

import java.sql.*;

public class AgregarArtista {
    public static Artista agregarArtista(String nombre, String generoMusical, String paisOrigen) {
        Connection conexion = null;
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "ana";
        String password = "ana";

        Artista artista = null;
        try {
            conexion = DriverManager.getConnection(url, user, password);
            conexion.setAutoCommit(false);

            PreparedStatement obtenerMaxID = conexion.prepareStatement("SELECT MAX(id_artista) FROM Artista");
            ResultSet rs = obtenerMaxID.executeQuery();
            int id_art;
            if (rs.next()) {
                id_art = rs.getInt(1);
            } else {
                id_art = 0;
            }
            int id_artista = id_art + 1;

            PreparedStatement insertarDatos = conexion.prepareStatement("INSERT INTO ARTISTA VALUES (?,?,?,?)");
            insertarDatos.setInt(1, id_artista);
            insertarDatos.setString(2, nombre);
            insertarDatos.setString(3, generoMusical);
            insertarDatos.setString(4, paisOrigen);
            insertarDatos.executeUpdate();

            conexion.commit();
            artista = new Artista(id_artista, nombre, generoMusical, paisOrigen);
            System.out.println("Artista agregado correctamente.");

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
        return artista;
    }
}
