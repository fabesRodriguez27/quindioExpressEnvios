package quindioexpress.comparadores;
import quindioexpress.modelo.Paquete;
import java.util.Comparator;

public class ComparadorPaqueteCondicional implements Comparator<Paquete> {
    @Override
    public int compare(Paquete paquete1, Paquete paquete2) {

        //Primero va el orden por Prioridad
        if(paquete1.getPrioridad() != paquete2.getPrioridad()){
            return paquete1.getPrioridad() - paquete2.getPrioridad();
        }

        //Si tienen la misma prioridad, se atiene el paquete con el menor tiempoEstimado
        if(paquete1.getTiempoEstimado() != paquete2.getTiempoEstimado()){
            return paquete1.getTiempoEstimado() - paquete2.getTiempoEstimado();
        }

        //Comparador Por Código
        return paquete1.getCodigo().compareTo(paquete2.getCodigo());
    }
}
