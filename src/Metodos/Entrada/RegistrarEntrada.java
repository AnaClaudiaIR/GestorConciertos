package Metodos.Entrada;

import Clases.Entrada;

import java.sql.*;

public class RegistrarEntrada {
    public static void registrarEntrada(int idConcierto, String comprador, int cantidad, Date fechaCompra){
        Connection conexion = null;
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "ana";
        String password = "ana";

        try {
            conexion = DriverManager.getConnection(url, user, password);
            conexion.setAutoCommit(false);

            //Generar el ID automáticamente --> Sumar 1
            PreparedStatement obtenerMaxID = conexion.prepareStatement("SELECT MAX(id_entrada) FROM Entrada");
            ResultSet rs = obtenerMaxID.executeQuery();
            int id_ent;
            if (rs.next()) {
                id_ent = rs.getInt(1);
            } else {
                id_ent = 0;
            }
            int id_entrada = id_ent + 1;

            //Insertar los datos en la tabla SQL
            PreparedStatement insertarDatos = conexion.prepareStatement("INSERT INTO Entrada VALUES (?, ?, ?, ?, ?)");
            insertarDatos.setInt(1, id_entrada);
            insertarDatos.setString(2, comprador);
            insertarDatos.setInt(3, cantidad);
            insertarDatos.setDate(4, fechaCompra);
            insertarDatos.setInt(5, idConcierto);
            insertarDatos.executeUpdate();

            conexion.commit();
            System.out.println("Entrada registrada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al registrar la entrada --> " + e.getMessage());
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
