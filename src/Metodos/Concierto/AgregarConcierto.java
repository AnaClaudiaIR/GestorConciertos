package Metodos.Concierto;
import Clases.Concierto;

import java.sql.*;

public class AgregarConcierto {
    public static Concierto agregarConcierto(int ID_artista, Date fecha, String lugar, double precioEntrada) {
        Connection conexion = null;
        Concierto concierto = null;

        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "ana";
        String password = "ana";
        try {
            //Obtener el último ID --> Generar el siguiente sumando 1
            conexion = DriverManager.getConnection(url, user, password);
            conexion.setAutoCommit(false);

            PreparedStatement obtenerMaxID = conexion.prepareStatement("SELECT MAX(id_concierto) FROM Concierto");
            ResultSet rs = obtenerMaxID.executeQuery();

            int id_con;
            if (rs.next()){
                id_con = rs.getInt(1);
            } else id_con = 0;

            //ID del concierto generado de forma automática
            int id_concierto = id_con + 1;

            //Insertar los datos en la tabla SQL
            PreparedStatement insertarDatos = conexion.prepareStatement("INSERT INTO Concierto VALUES (?, ?, ?, ?, ?)");
            insertarDatos.setInt(1, id_concierto);
            insertarDatos.setDate(2, fecha);
            insertarDatos.setString(3, lugar);
            insertarDatos.setDouble(4, precioEntrada);
            insertarDatos.setInt(5, ID_artista);
            insertarDatos.executeUpdate();

            conexion.commit();

            //Generar el objeto que va a retornar
            concierto = new Concierto(id_concierto, ID_artista, fecha, lugar, precioEntrada);
            System.out.println("Concierto agregado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al agregar el Concierto --> " + e.getMessage());
            if (conexion != null) {
                try {
                    //Si hay un fallo hace rollback para que no se guarden los datos
                    conexion.rollback();
                } catch (SQLException ex) {
                    System.out.println("Error al hacer rollback --> " + ex.getMessage());
                }
        }
    } finally {
            if (conexion != null) {
                try {
                    //Una vez se finalizan todas las operaciones se cierra la conexión
                    conexion.close();
                } catch (SQLException ex) {
                    System.out.println("Error al cerrar la conexión --> " + ex.getMessage());
                }
            }
        }
        return concierto;
        }
}
