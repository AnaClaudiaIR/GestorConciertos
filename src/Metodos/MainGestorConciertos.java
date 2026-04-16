package Metodos;

import Clases.Artista;
import Metodos.Artista.AgregarArtista;
import Metodos.Artista.EliminarArtista;
import Metodos.Artista.ListarArtista;

import java.sql.*;
import java.util.Scanner;

public class MainGestorConciertos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        boolean id_valido = false;

        do {
            System.out.println("---GESTOR DE CONCIERTOS---");
            System.out.println("1. Agregar Artista.");
            System.out.println("2. Eliminar Artista.");
            System.out.println("3. Listar Artistas.");
            System.out.println("----------------------");
            System.out.println("4. Agregar Concierto.");
            System.out.println("5. Eliminar Concierto.");
            System.out.println("6. Listar Conciertos.");
            System.out.println("----------------------");
            System.out.println("7. Registrar Entrada.");
            System.out.println("8. Listar Entradas.");
            System.out.println("----------------------");
            System.out.println("0. Salir.");
            System.out.println("-----------------------");
            System.out.print("Opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    sc.nextLine();
                    System.out.println("Nombre: ");
                    String nombre = sc.next();

                    System.out.println("Genero musical: ");
                    String genero = sc.next();

                    System.out.println("País origen: ");
                    String pais = sc.next();

                    Artista artista = AgregarArtista.agregarArtista(nombre, genero, pais);
                    break;
                case 2:
                    String url = "jdbc:oracle:thin:@localhost:1521:xe";
                    String user = "ana";
                    String password = "ana";

                    try(Connection conn = DriverManager.getConnection(url, user, password)){
                        while (!id_valido) {
                            try {
                                System.out.println("Ingresa el ID del artista a eliminar: ");
                                int  idElim = sc.nextInt();

                                PreparedStatement comprobarID = conn.prepareStatement("SELECT id_artista FROM artista WHERE id_artista = ?");
                                comprobarID.setInt(1, idElim);
                                ResultSet resultado = comprobarID.executeQuery();
                                if (resultado.next()) {
                                    id_valido = true;
                                    EliminarArtista.eliminarArtista(idElim);
                                } else {
                                    throw new Exception("Introduce un número correcto.");
                                }
                            } catch (Exception e) {
                                System.out.println("ID inválido --> " +e.getMessage());
                            }
                        }
                    } catch (SQLException e){
                        System.out.println("ERROR al conectarse a base de datos --> "+e.getMessage());
                    }
                    break;
                case 3:
                    ListarArtista.listarArtistas();
            }
        } while (opcion != 0);
    }
}
