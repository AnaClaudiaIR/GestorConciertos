package Metodos;

import Clases.Artista;
import Metodos.Artista.AgregarArtista;

import java.util.Scanner;

public class MainGestorConciertos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

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
                    System.out.println("Nombre: ");
                    String nombre = sc.next();

                    System.out.println("Genero musical: ");
                    String genero = sc.next();

                    System.out.println("País origen: ");
                    String pais = sc.next();

                    Artista artista = AgregarArtista.agregarArtista(nombre, genero, pais);
                    break;
            }
        } while (opcion != 0);
    }
}
