package Clases;

import java.io.Serializable;

public class Artista implements Serializable {
    private int ID_artista;
    private String nombre;
    private String generoMusical;
    private String paisOrigen;

    public Artista(int ID_artista, String nombre, String generoMusical, String paisOrigen) {
        this.ID_artista = ID_artista;
        this.nombre = nombre;
        this.generoMusical = generoMusical;
        this.paisOrigen = paisOrigen;
    }

    public int getID_artista() {
        return ID_artista;
    }

    public void setID_artista(int ID_artista) {
        this.ID_artista = ID_artista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    @Override
    public String toString() {
        return "ID Artista: " + ID_artista +
                "\nNombre: " + nombre
                + "\nGenero Musical: " + generoMusical
                + "\nPaís de Origen: " + paisOrigen;
    }
}
