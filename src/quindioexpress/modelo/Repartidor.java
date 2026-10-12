package quindioexpress.modelo;

import java.util.ArrayList;

public class Repartidor {

    private final String id;
    private final String nombre;
    private final String zona;
    private boolean disponible;
    private ArrayList<String> paquetes;

    public Repartidor(String id, String nombre, String zona) {
        this.id = id;
        this.nombre = nombre;
        this.zona = zona;
        this.disponible = true;
        this.paquetes = new ArrayList<>();
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

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public ArrayList<String> getPaquetes() {
        return this.paquetes;
    }

    public void addPaquete(String codigoPaquete) {
       this.paquetes.add(codigoPaquete);
    }

    @Override
    public String toString() {
        return id + " | " + nombre + " | zona " + zona
                + " | " + (disponible ? "disponible" : "no disponible");
    }
}
