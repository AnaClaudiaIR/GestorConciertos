package Metodos.Concierto;

import java.sql.*;

public class ListarConcierto {
    public static void listarConciertos(){
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "ana";
        String password = "ana";

        try(Connection conexion = DriverManager.getConnection(url, user, password);
            Statement statement = conexion.createStatement()){
            String listarConciertosSQL = "SELECT ID_CONCIERTO, FECHA, LUGAR, PRECIOENTRADA, NOMBRE\n" +
                    "FROM CONCIERTO JOIN ARTISTA USING (ID_ARTISTA)";
            ResultSet rs = statement.executeQuery(listarConciertosSQL);

            System.out.println("\n---Listado de Conciertos---");

            while(rs.next()){
                int idConcierto = rs.getInt("ID_Concierto");
                String fecha = rs.getString("fecha");
                String lugar = rs.getString("lugar");
                double precio = rs.getDouble("precioentrada");
                String nombreArtista = rs.getString("NOMBRE");

                System.out.println("--------------------");
                System.out.println("ID Artista: " + idConcierto);
                System.out.println("Fecha: " + fecha);
                System.out.println("Lugar: " + lugar);
                System.out.println("Precio de Entrada: " + precio);
                System.out.println("Nombre del Artista: " + nombreArtista);
            }
        } catch (SQLException e){
            System.out.println("ERROR al conectarse a la base de datos --> "+e.getMessage());
        }
    }
}
