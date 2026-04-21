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
    public static void leerDatos(){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("resumen_artistas_conciertos.dat"))){
            List<Artista> listaArtistas = (List<Artista>) in.readObject();
            List<Concierto> listaConcierto = (List<Concierto>) in.readObject();
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Error al leer datos --> "+e.getMessage());
        }
    }
}
