package Metodos.Artista;

import java.sql.*;

public class ListarArtista {
    public static void listarArtistas(){
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "ana";
        String password = "ana";

        try(Connection conexion = DriverManager.getConnection(url, user, password);
            Statement statement = conexion.createStatement()){
            String listarArtistasSQL = "select * from Artista";
            ResultSet rs = statement.executeQuery(listarArtistasSQL);

            System.out.println("\n---Listado de artistas---");

            //Listar artistas mientras haya resultados
            while(rs.next()){
                int idArtista = rs.getInt("ID_Artista");
                String nombre = rs.getString("NOMBRE");
                String generoMusical = rs.getString("GENEROMUSICAL");
                String paisOrigen = rs.getString("PAISORIGEN");

                System.out.println("--------------------");
                System.out.println("ID Artista: " + idArtista);
                System.out.println("Nombre: "+nombre);
                System.out.println("Género Musical: "+generoMusical);
                System.out.println("País de Origen: "+paisOrigen);
            }
        } catch (SQLException e){
            System.out.println("ERROR al conectarse a la base de datos --> "+e.getMessage());
        }
    }
}
