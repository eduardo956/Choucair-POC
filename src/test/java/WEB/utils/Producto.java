package WEB.utils;

public class Producto {
    private String nombre;
    private double precioTotal;
    private int cantidad;

    public Producto(String nombre, double precioTotal, int cantidad) {
        this.nombre = nombre;
        this.precioTotal = precioTotal;
        this.cantidad = cantidad;
    }
    public String getNombre() {
        return nombre;
    }


    public double getPrecioTotal() {
        return precioTotal;
    }


    public int getCantidad() {
        return cantidad;
    }
}
