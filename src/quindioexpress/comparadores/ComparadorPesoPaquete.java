package quindioexpress.comparadores;
import quindioexpress.modelo.Paquete;
import java.util.Comparator;

public class ComparadorPesoPaquete implements Comparator<Paquete> {
    @Override
    public int compare(Paquete paquete1, Paquete paquete2) {
        return Double.compare(paquete2.getPeso(), paquete1.getPeso());
    }
}
