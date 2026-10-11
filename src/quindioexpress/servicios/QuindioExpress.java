package quindioexpress.servicios;
import quindioexpress.modelo.Paquete;
import quindioexpress.modelo.Repartidor;
import java.util.*;

public class QuindioExpress {
    public static GestionPaquetes gestionPaquetes;
    public static GestionRepartidores gestionRepartidores;

    public QuindioExpress() {
        gestionPaquetes = new GestionPaquetes();
        gestionRepartidores = new GestionRepartidores();
    }

    public boolean agregarPaquete(Paquete paquete){
        return gestionPaquetes.registrarPaquete(paquete);
    }

    public boolean eliminarPaquete(String codigo){
        return gestionPaquetes.eliminarPaquete(codigo);
    }

    public Paquete buscarPaquete(String codigo){
        return gestionPaquetes.buscarPaquete(codigo);
    }

    public void ordenarPaquetesAlfabeticamente(){
        gestionPaquetes.ordenarPaquetesAlfabeticamente();
    }

    public void mostrarPaquetesPrioridad(){
        gestionPaquetes.mostrarPaquetesPorOrdenPrioridad();
    }

    public void mostrarPaquetesPorPeso(){
        gestionPaquetes.mostrarPaquetesOrdenPeso();
    }

    public void mostrarPaquetesPorTiempoEstimado() {
        gestionPaquetes.mostrarPaquetesOrdenTiempoEstimado();
    }

    public Paquete buscarPaqueteArrayList(String codigo){
        return gestionPaquetes.buscarPaqueteArrayList(codigo);
    }

    //Procesar según el orden de registro (Arraylist<>)
    public void procesarPaqueteOrdenRegistro() {
        Paquete paquete = gestionPaquetes.obtenerPaquetePorOrdenLlegada();
        procesarPaqueteSeleccionado(paquete);
    }

    // Procesar según la prioridad
    public void procesarPaquetePorPrioridad() {
        Paquete paquete = gestionPaquetes.obtenerPaquetePorPrioridad();
        procesarPaqueteSeleccionado(paquete);
    }

    //Asignación el Paquete a un Repartidor de la Misma Zona
    private void procesarPaqueteSeleccionado(Paquete paquete) {
        if (paquete == null) {
            System.out.println("No hay paquetes pendientes.");
            return;
        }
        Repartidor repartidor = gestionRepartidores.buscarRepartidorDisponiblePorZona(paquete.getDestino());

        if (repartidor == null) {
            System.out.println("No Hay Repartidores Disponibles en la Zona " + paquete.getDestino());
            return;
        }
        //Ocupo al Repartidor
        gestionRepartidores.ocuparRepartidor(repartidor);
        // Eliminar el paquete de las colecciones de paquetes pendientes
        boolean eliminado = gestionPaquetes.eliminarPaquete(paquete.getCodigo());

        if (eliminado) {
            System.out.println("Paquete Asignado Correctamente: \n");
            System.out.println(paquete.toString());
            System.out.println("Repartidor Asignado al Paquete: \n");
            System.out.println(repartidor);
        } else {
            // Si no se pudo retirar el paquete, restaurar la disponibilidad
            gestionRepartidores.volverRepartidorDisponible(repartidor);
            System.out.println("No se Pudo Procesar el Paquete.");
        }
    }
}
