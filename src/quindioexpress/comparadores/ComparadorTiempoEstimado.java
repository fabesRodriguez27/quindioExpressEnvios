package quindioexpress.comparadores;

import quindioexpress.modelo.Paquete;

import java.util.Comparator;

public class ComparadorTiempoEstimado implements Comparator<Paquete> {
    @Override
    public int compare(Paquete paquete1, Paquete paquete2) {
        return paquete1.getTiempoEstimado() - paquete2.getTiempoEstimado();
    }
}
