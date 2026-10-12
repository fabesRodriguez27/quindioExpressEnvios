package quindioexpress.modelo;
import java.util.Objects;
import java.util.Random;

public class Paquete implements Comparable<Paquete> {

    public static final int PRIORIDAD_MIN = 0;
    public static final int PRIORIDAD_MAX = 10;
    public static final int PRIORIDAD_DEFECTO = 5;

    private final String codigo;
    private final String destino;
    private final double peso;
    private final int prioridad;
    private final int tiempoEstimado;
    private String idRepartidor;


    public Paquete(String destino, double peso, int prioridad, int tiempoEstimado) {
        if (destino == null || destino.trim().isEmpty()) {
            throw new IllegalArgumentException("El destino es obligatorio");
        }
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser positivo");
        }
        if (prioridad < 1 || prioridad > 10) {
            throw new IllegalArgumentException("La prioridad debe estar entre 1 y 10");
        }
        if (tiempoEstimado <= 0) {
            throw new IllegalArgumentException("El tiempo debe ser positivo");
        }
        this.codigo = generarCodigo();
        this.destino = destino;
        this.peso = peso;
        this.prioridad = prioridad;
        this.tiempoEstimado = tiempoEstimado;
        this.idRepartidor = null;
    }

    //Constructor con Prioridad Por Defecto
    public Paquete(String destino, double peso, int tiempoEstimado) {
        this(destino, peso, PRIORIDAD_DEFECTO, tiempoEstimado);
    }

    public static String generarCodigo() {
        Random random = new Random();
        int numero = random.nextInt(1000000);
        return String.format("PQ%06d", numero);
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

    public String idRepartidor() {
        return idRepartidor;
    }

    public void setIdRepartidor(String idRepartidor) {
        this.idRepartidor=idRepartidor;
    }

    // Orden natural, por código ascendente
    @Override
    public int compareTo(Paquete otro) {
        return this.codigo.compareTo(otro.codigo);
    }

    @Override
    public String toString() {
        return "Código Paquete : " + codigo + "\n" +
                "Destino : " + destino + "\n" +
                "Peso: " + peso + "\n" +
                "Prioridad: " + prioridad + "\n" +
                "Tiempo Estimado: " + tiempoEstimado + "\n";
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


}