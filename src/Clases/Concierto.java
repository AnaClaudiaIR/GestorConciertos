package Clases;

public class Concierto {
    private int ID_concierto;
    private int ID_artista;
    private String fecha;
    private String lugar;
    private double precioEntrada;

    public Concierto(int ID_concierto, int ID_artista, String fecha, String lugar, double precioEntrada) {
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
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
}
