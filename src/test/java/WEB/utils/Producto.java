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

    public Producto() {
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
