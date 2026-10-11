package quindioexpress.servicios;
import quindioexpress.modelo.Repartidor;
import java.util.HashMap;

public class GestionRepartidores {
    private HashMap<String, Repartidor> repartidores;

    public GestionRepartidores() {
        this.repartidores = new HashMap<>();
    }

    public Repartidor buscarRepartidorDisponiblePorZona(String zona) {
        for (Repartidor repartidor : repartidores.values()) {
            if (repartidor.isDisponible() && repartidor.getZona().equalsIgnoreCase(zona)) {
                return repartidor;
            }
        }
        return null;
    }

    public void ocuparRepartidor(Repartidor repartidor){
        repartidor.setDisponible(false);
    }

    public void volverRepartidorDisponible(Repartidor repartidor){
        repartidor.setDisponible(true);
    }

}
