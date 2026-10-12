package quindioexpress.servicios;

import quindioexpress.estructuras.IteradorLista;
import quindioexpress.estructuras.ListaSimplementeEnlazada;
import quindioexpress.modelo.Paquete;

public class GestionEntregas {
    private ListaSimplementeEnlazada<Paquete> paquetesEntregados;

    public GestionEntregas() {
        this.paquetesEntregados = new ListaSimplementeEnlazada<>();
    }

    public boolean agregarEntrega(Paquete paquete){
        this.paquetesEntregados.add(paquete);
        return true;
    }

    public boolean eliminarEntrega(int posicion){
        return this.paquetesEntregados.remove(posicion-1);
    }

    public String obtenerPaquete(int posicion){

        Paquete paquete = paquetesEntregados.nodeValue(posicion - 1);

        if (paquete == null) {
            return null;
        }

        return paquete.toString();
    }

    public void mostrarHistorialEntregas(){

        if (paquetesEntregados.isEmpty()) {
            System.out.println("No hay paquetes entregados.");
            return;
        }

        System.out.printf("%-12s %-20s %-20s%n",
                "Posición", "Código", "Destino");
        System.out.println("----------------------------------------------------");

        IteradorLista<Paquete> iterador = paquetesEntregados.iterator();
        int posicion = 0;

        while(iterador.hasNext()){
            Paquete paquete = iterador.next();
            posicion++;
            System.out.printf("%-12d %-20s %-20s%n",
                    posicion, "Paquete: " + paquete.getCodigo(), paquete.getDestino());
        }
    }

    public String cantidadPaquetesEntregados() {
        return "La cantidad de paquetes entregados a la fecha es:" + paquetesEntregados.getSize();
    }
}
