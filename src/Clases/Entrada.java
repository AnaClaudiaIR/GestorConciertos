package Clases;

public class Entrada {
    private int ID_entrada;
    private int ID_concierto;
    private String comprador;
    private int cantidad;
    private String fechaCompra;

    public Entrada(int ID_entrada, int ID_concierto, String comprador, int cantidad, String fechaCompra) {
        this.ID_entrada = ID_entrada;
        this.ID_concierto = ID_concierto;
        this.comprador = comprador;
        this.cantidad = cantidad;
        this.fechaCompra = fechaCompra;
    }

    public int getID_entrada() {
        return ID_entrada;
    }

    public void setID_entrada(int ID_entrada) {
        this.ID_entrada = ID_entrada;
    }

    public int getID_concierto() {
        return ID_concierto;
    }

    public void setID_concierto(int ID_concierto) {
        this.ID_concierto = ID_concierto;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
}
