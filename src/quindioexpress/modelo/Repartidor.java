package quindioexpress.modelo;

import java.util.Objects;

public class Repartidor {

    private final String id;
    private final String nombre;
    private final String zona;
    private boolean disponible;


    // Mejorar constructor de acuerdo al documento
    public Repartidor(String id, String nombre, String zona) {
        this.id = id;
        this.nombre = nombre;
        this.zona = zona;
        this.disponible = true;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getZona() {
        return zona;
    }

    public boolean isDisponible() {
        return disponible;
    }

    // METODOS

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // Dos repartidores son iguales si tienen la misma identificación.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Repartidor)) return false;
        Repartidor otro = (Repartidor) o;
        return id.equals(otro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + " | " + nombre + " | zona " + zona
                + " | " + (disponible ? "disponible" : "no disponible");
    }
}
