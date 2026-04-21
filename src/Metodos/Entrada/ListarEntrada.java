package Metodos.Entrada;

import java.sql.*;

public class ListarEntrada {
    public static void listarEntradas(){
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "ana";
        String password = "ana";

        try(Connection conexion = DriverManager.getConnection(url, user, password);
            Statement statement = conexion.createStatement()){
            //Obtener datos de las tres tablas
            String listarEntradasSQL = "SELECT ID_ENTRADA, NOMBRE, FECHA, LUGAR, PRECIOENTRADA, COMPRADOR, CANTIDAD, FECHACOMPRA\n" +
                    "FROM ENTRADA JOIN CONCIERTO USING (ID_CONCIERTO)\n" +
                    "    JOIN ARTISTA USING (ID_ARTISTA)";
            ResultSet rs = statement.executeQuery(listarEntradasSQL);

            System.out.println("\n---Listado de Entradas---");

            //Mientras haya resultados mostrar los datos
            while(rs.next()){
                int ID_ENTRADA = rs.getInt("ID_ENTRADA");
                String NOMBRE = rs.getString("NOMBRE");
                String FECHA = rs.getString("FECHA");
                String LUGAR = rs.getString("LUGAR");
                double PRECIOENTRADA = rs.getDouble("PRECIOENTRADA");
                String COMPRADOR = rs.getString("COMPRADOR");
                int CANTIDAD = rs.getInt("CANTIDAD");
                String FECHACOMPRA = rs.getString("FECHACOMPRA");

                System.out.println("----------------------");
                System.out.print("ID ENTRADA: "+ID_ENTRADA+" --- ARTISTA: " + NOMBRE + " --- FECHA: " + FECHA + " --- LUGAR: " + LUGAR);
                System.out.println();
                System.out.print("COMPRADOR: "+COMPRADOR + " --- FECHA COMPRA: "+FECHACOMPRA);
                System.out.println(" --- CANTIDAD: "+CANTIDAD + " --- PRECIO ENTRADA: "+PRECIOENTRADA);
                System.out.println("TOTAL: " + CANTIDAD*PRECIOENTRADA);
            }
        } catch (SQLException e){
            System.out.println("ERROR al conectarse a la base de datos --> "+e.getMessage());
        }
    }
}
