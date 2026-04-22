package Metodos.fichero;

import Clases.Artista;
import Clases.Concierto;

import java.io.*;
import java.util.List;

public class GenerarResumen {
    //Guardar los datos del concierto y artistas en un archivo binario --> Que se guarden para las próximas ejecuciones
    public static void guardarDatos(List<Concierto> listaConciertos, List<Artista> listaArtistas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("resumen_artistas_conciertos.dat"))) {
            oos.writeObject(listaArtistas);
            oos.writeObject(listaConciertos);
            System.out.println("Datos guardados exitosamente.");
        } catch (IOException e){
            System.out.println("Error al guardar datos --> "+e.getMessage());
        }
    }

    //Leer el archivo binario
    public static void cargarDatos(List<Artista> listaArtistas, List<Concierto> listaConciertos) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("resumen_artistas_conciertos.dat"))){
            List<Artista> artistas = (List<Artista>) in.readObject();
            List<Concierto> conciertos = (List<Concierto>) in.readObject();

            listaArtistas.addAll(artistas);
            listaConciertos.addAll(conciertos);
            System.out.println("Datos cargados guardados exitosamente.");

        } catch (IOException | ClassNotFoundException e){
            System.out.println("Error al leer datos --> "+e.getMessage());
        }
    }

    //Mostrar los datos en la consola
    public static void mostrarDatos(List<Artista> listaArtistas,List<Concierto> listaConcierto){
        System.out.println("***ARTISTAS***");
        for (Artista artista : listaArtistas) {
            System.out.println("------------------");
            System.out.println(artista.toString());
        }

        System.out.println("***CONCIERTO***");
        for (Concierto concierto : listaConcierto) {
            System.out.println("------------------");
            System.out.println(concierto.toString());
        }
    }
}
