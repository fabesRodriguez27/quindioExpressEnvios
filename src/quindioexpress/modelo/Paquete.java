package quindioexpress.modelo;

import java.util.Objects;


public class Paquete implements Comparable<Paquete> {

    public static final int PRIORIDAD_MIN = 0;
    public static final int PRIORIDAD_MAX = 10;
    public static final int PRIORIDAD_DEFECTO = 5;

    private final String codigo;
    private final String destino;
    private final double peso;
    private final int prioridad;
    private final int tiempoEstimado;


    public Paquete(String codigo, String destino, double peso, int prioridad, int tiempoEstimado) {
        this.codigo = codigo;
        this.destino = destino;
        this.peso = peso;
        this.prioridad = PRIORIDAD_DEFECTO;
        this.tiempoEstimado = tiempoEstimado;
    }


    public String getCodigo() {
        return codigo;
    }

    public String getDestino() {
        return destino;
    }

    public double getPeso() {
        return peso;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    // Orden natural, por código ascendente
    @Override
    public int compareTo(Paquete otro) {
        return this.codigo.compareTo(otro.codigo);
    }

    // Dos paquetes son iguales si tienen el mismo código
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Paquete)) return false;
        Paquete otro = (Paquete) o;
        return codigo.equals(otro.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return codigo + " | " + destino + " | " + peso + " kg | prioridad " + prioridad
                + " | " + tiempoEstimado + " min";
    }
}