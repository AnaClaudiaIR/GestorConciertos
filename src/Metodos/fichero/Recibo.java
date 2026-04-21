package Metodos.fichero;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class Recibo {
    public static void generarRecibo(int id_entrada, String recibo){
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "ana";
        String password = "ana";

        int ID_ENTRADA = 0;
        String NOMBRE = "";
        String FECHA= "";
        String LUGAR = "";
        double PRECIOENTRADA= 0;
        String COMPRADOR= "";
        int CANTIDAD= 0;
        String FECHACOMPRA= "";

        try(Connection conexion = DriverManager.getConnection(url, user, password);
            Statement statement = conexion.createStatement()){
            //Obtener datos de las tres tablas según el ID de la entrada que se haya recibido
            String listarEntradasSQL = "SELECT ID_ENTRADA, NOMBRE, FECHA, LUGAR, PRECIOENTRADA, COMPRADOR, CANTIDAD, FECHACOMPRA\n" +
                    "FROM ENTRADA JOIN CONCIERTO USING (ID_CONCIERTO)\n" +
                    "    JOIN ARTISTA USING (ID_ARTISTA)" +
                    " WHERE ID_ENTRADA = ?";

            PreparedStatement preparedStatement = conexion.prepareStatement(listarEntradasSQL);
            preparedStatement.setInt(1, id_entrada);
            ResultSet rs = preparedStatement.executeQuery();

            //Se generan los datos si hay un resultado
            if(rs.next()){
                ID_ENTRADA = rs.getInt("ID_ENTRADA");
                NOMBRE = rs.getString("NOMBRE");
                FECHA = rs.getString("FECHA");
                LUGAR = rs.getString("LUGAR");
                PRECIOENTRADA = rs.getDouble("PRECIOENTRADA");
                COMPRADOR = rs.getString("COMPRADOR");
                CANTIDAD = rs.getInt("CANTIDAD");
                FECHACOMPRA = rs.getString("FECHACOMPRA");
            }

            //Escribir en un archivo de texto los datos --> Generar el recibo
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(recibo))){
                bw.write("*******DATOS DE COMPRA*******");
                bw.newLine();
                bw.write("· ID ENTRADA: "+ID_ENTRADA+" --- ARTISTA: " + NOMBRE + " --- FECHA: " + FECHA + " --- LUGAR: " + LUGAR);
                bw.newLine();
                bw.write("· COMPRADOR: "+COMPRADOR + "\n· FECHA COMPRA: "+FECHACOMPRA);
                bw.newLine();
                bw.write("· CANTIDAD: "+CANTIDAD + " --- PRECIO ENTRADA: "+PRECIOENTRADA+"€");
                bw.newLine();
                bw.write("TOTAL: " + CANTIDAD*PRECIOENTRADA + "€");

                System.out.println("Datos guardados exitosamente.");
            } catch (IOException e){
                System.out.println("Error al escribir los datos --> " + e.getMessage());
            }
        } catch (SQLException e){
            System.out.println("ERROR al conectarse a la base de datos --> "+e.getMessage());
        }
    }
}
