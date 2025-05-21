import java.io.Serializable;

public class Producto implements Serializable {

    private int cod;
    private String nombre;
    private float precio;
    private int stock;

    public Producto(int cod, String nombre, float precio, int stock) {
        this.cod = cod;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public int getCod() {
        return cod;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }
    @Override
    public String toString() {
        return "Código: " + cod + "\n"
                + "Nombre: " + nombre + "\n"
                + "Precio: " + precio + "\n"
                + "Stock: " + stock;
    }
}
