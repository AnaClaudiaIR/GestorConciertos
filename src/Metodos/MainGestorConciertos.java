package Metodos;

import Clases.Artista;
import Clases.Concierto;
import Clases.Entrada;
import Metodos.Artista.AgregarArtista;
import Metodos.Artista.EliminarArtista;
import Metodos.Artista.ListarArtista;
import Metodos.Concierto.AgregarConcierto;
import Metodos.Concierto.EliminarConcierto;
import Metodos.Concierto.ListarConcierto;
import Metodos.Entrada.ListarEntrada;
import Metodos.Entrada.RegistrarEntrada;
import Metodos.fichero.GenerarResumen;
import Metodos.fichero.Recibo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainGestorConciertos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "ana";
        String password = "ana";

        List<Artista> listaArtistas = new ArrayList<>();
        List<Concierto> listaConciertos = new ArrayList<>();

        int opcion = -1;
        boolean id_valido = false;
        boolean opcion_valida = false;
        boolean id_artista_concierto_valido = false;
        boolean id_concierto_valido = false;
        boolean id_entrada_valido = false;

        do {
            while (!opcion_valida) {
                System.out.println("\n---GESTOR DE CONCIERTOS---");
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
                System.out.println("9. Generar recibo.");
                System.out.println("----------------------");
                System.out.println("10. Generar resumen Artista + Conciertos");
                System.out.println("0. Salir.");
                System.out.println("-----------------------");
                System.out.print("Opción: ");
                try {
                    opcion = sc.nextInt();
                    sc.nextLine();
                    opcion_valida = true;
                } catch (InputMismatchException ex) {
                    System.out.println("Por favor, ingrese un número.");
                    sc.nextLine();
                }
            }
            opcion_valida = false;

            switch (opcion) {
                case 1:
                    System.out.println("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.println("Genero musical: ");
                    String genero = sc.nextLine();

                    System.out.println("País origen: ");
                    String pais = sc.nextLine();

                    Artista artista = AgregarArtista.agregarArtista(nombre, genero, pais);
                    listaArtistas.add(artista);
                    break;
                case 2:
                    try(Connection conn = DriverManager.getConnection(url, user, password)){
                        while (!id_valido) {
                            try {
                                System.out.println("Ingresa el ID del artista a eliminar: ");
                                int  idElim = sc.nextInt();
                                sc.nextLine();

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
                    id_valido = false;
                    break;
                case 3:
                    ListarArtista.listarArtistas();
                    break;
                case 4:
                    try(Connection conn = DriverManager.getConnection(url, user, password)){
                        while (!id_artista_concierto_valido) {
                            try {
                                System.out.println("Ingresa el ID del artista del concierto: ");
                                int idArtistaConcierto = sc.nextInt();
                                sc.nextLine();

                                PreparedStatement comprobarID = conn.prepareStatement("SELECT id_artista FROM artista WHERE id_artista = ?");
                                comprobarID.setInt(1, idArtistaConcierto);
                                ResultSet resultado = comprobarID.executeQuery();
                                if (resultado.next()) {
                                    id_artista_concierto_valido = true;

                                    System.out.println("Fecha: ");
                                    String fecha = sc.nextLine();

                                    System.out.println("Lugar: ");
                                    String lugar = sc.nextLine();

                                    System.out.println("Precio de la entrada: ");
                                    double precioEntrada = sc.nextDouble();

                                    Concierto concierto = AgregarConcierto.agregarConcierto(idArtistaConcierto,fecha,lugar,precioEntrada);
                                    listaConciertos.add(concierto);
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
                    id_artista_concierto_valido = false;
                    break;
                case 5:
                    try(Connection conn = DriverManager.getConnection(url, user, password)){
                        while (!id_concierto_valido) {
                            try {
                                System.out.println("Ingresa el ID del concierto a eliminar: ");
                                int idConciertoElim = sc.nextInt();
                                sc.nextLine();

                                PreparedStatement comprobarID = conn.prepareStatement("SELECT id_concierto FROM concierto WHERE id_concierto = ?");
                                comprobarID.setInt(1, idConciertoElim);
                                ResultSet resultado = comprobarID.executeQuery();
                                if (resultado.next()) {
                                    id_concierto_valido = true;
                                    EliminarConcierto.eliminarConcierto(idConciertoElim);
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
                    id_concierto_valido = false;
                    break;
                case 6:
                    ListarConcierto.listarConciertos();
                    break;
                case 7:
                    try(Connection conn = DriverManager.getConnection(url, user, password)){
                        while (!id_concierto_valido) {
                            try {
                                System.out.println("Ingresa el ID del concierto: ");
                                int idConcierto = sc.nextInt();
                                sc.nextLine();

                                PreparedStatement comprobarID = conn.prepareStatement("SELECT id_concierto FROM concierto WHERE id_concierto = ?");
                                comprobarID.setInt(1, idConcierto);
                                ResultSet resultado = comprobarID.executeQuery();
                                if (resultado.next()) {
                                    id_concierto_valido = true;

                                    System.out.println("Comprador: ");
                                    String comprador = sc.nextLine();

                                    System.out.println("Cantidad: ");
                                    int cantidad = sc.nextInt();

                                    java.sql.Date fechaCompra = java.sql.Date.valueOf(LocalDate.now());

                                  RegistrarEntrada.registrarEntrada(idConcierto, comprador, cantidad, fechaCompra);

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
                    id_concierto_valido = false;
                    break;
                case 8:
                    ListarEntrada.listarEntradas();
                    break;
                case 9:
                    try(Connection conn = DriverManager.getConnection(url, user, password)){
                        while (!id_entrada_valido) {
                            try {
                                System.out.println("Ingresa el ID de la entrada: ");
                                int idEntrada = sc.nextInt();
                                sc.nextLine();

                                PreparedStatement comprobarID = conn.prepareStatement("SELECT id_entrada FROM entrada WHERE id_entrada = ?");
                                comprobarID.setInt(1, idEntrada);
                                ResultSet resultado = comprobarID.executeQuery();
                                if (resultado.next()) {
                                    id_entrada_valido = true;
                                    Recibo.generarRecibo(idEntrada,"recibo.txt");
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
                    id_entrada_valido = false;
                    break;
                case 10:
                    GenerarResumen.guardarDatos("resumen.txt", listaConciertos, listaArtistas);
                    break;
                case 0:
                    System.out.println("Has salido.");
                    break;
                default:
                    System.out.println("Introduce una de las opciones (0-8).");
                    break;
            }
        } while (opcion != 0);
    }
}
