package Clases;

import java.io.Serializable;
import java.util.Date;

public class Concierto implements Serializable {
    private int ID_concierto;
    private int ID_artista;
    private java.sql.Date fecha; //Tipo de fecha que sea compatible con el "date" de SQL
    private String lugar;
    private double precioEntrada;

    public Concierto(int ID_concierto, int ID_artista, java.sql.Date fecha, String lugar, double precioEntrada) {
        this.ID_concierto = ID_concierto;
        this.ID_artista = ID_artista;
        this.fecha = fecha;
        this.lugar = lugar;
        this.precioEntrada = precioEntrada;
    }

    public int getID_concierto() {
        return ID_concierto;
    }

    public void setID_concierto(int ID_concierto) {
        this.ID_concierto = ID_concierto;
    }

    public int getID_artista() {
        return ID_artista;
    }

    public void setID_artista(int ID_artista) {
        this.ID_artista = ID_artista;
    }

    public java.sql.Date getFecha() {
        return fecha;
    }

    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public double getPrecioEntrada() {
        return precioEntrada;
    }

    public void setPrecioEntrada(double precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    @Override
    public String toString() {
        return "ID Concierto: " + ID_concierto
                + "\nID Artista: " + ID_artista
                + "\nFecha: " + fecha
                + "\nLugar: " + lugar
                + "\nPrecio Entrada: " + precioEntrada;
    }
}
