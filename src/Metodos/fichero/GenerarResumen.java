package Metodos.fichero;

import Clases.Artista;
import Clases.Concierto;

import java.io.*;
import java.util.List;

public class GenerarResumen {
    public static void guardarDatos(String fichero, List<Concierto> listaConciertos, List<Artista> listaArtistas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero))) {
            bw.write("*********LISTA DE ARTISTAS**********");
            bw.newLine();
            for (Artista artista : listaArtistas){
                bw.write(artista.toString());
                bw.newLine();
            }
            bw.newLine();
            bw.write("*********LISTA DE CONCIERTOS**********");
            bw.newLine();
            for (Concierto concierto : listaConciertos){
                bw.write(concierto.toString());
            }

        } catch (IOException e){
            System.out.println("Error al guardar datos --> "+e.getMessage());
        }
    }
}
