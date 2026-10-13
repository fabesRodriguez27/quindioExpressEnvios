package quindioexpress.servicios;
import quindioexpress.modelo.Paquete;
import quindioexpress.modelo.Repartidor;

public class CentroLogistico {
    private final GestionPaquetes gestionPaquetes;
    private final GestionRepartidores gestionRepartidores;
    private final GestionEntregas gestionEntregas;

    public CentroLogistico() {
        gestionPaquetes = new GestionPaquetes();
        gestionRepartidores = new GestionRepartidores();
        gestionEntregas = new GestionEntregas();
    }

    /**
    =================================================
    GESTION PAQUETES
    =================================================
     */

    public boolean agregarPaquete(Paquete paquete) {
        if (!gestionRepartidores.validarMunicipio(paquete.getDestino())) {
            return false;
        }
        return gestionPaquetes.registrarPaquete(paquete);
    }

    public void totalPaquetesRegistrados(){
        gestionPaquetes.mostrarCantidadPaquetes();
    }

    public boolean eliminarPaquete(String codigo) {
        Paquete paquete = gestionPaquetes.buscarPaquete(codigo);
        if (paquete != null) return false;
        if (paquete.idRepartidor() != null) {
            gestionRepartidores.eliminarPaqueteRepartidor(codigo, paquete.idRepartidor());
        }
        return gestionPaquetes.eliminarPaquete(codigo);
    }

    public Paquete buscarPaquete(String codigo) {
        return gestionPaquetes.buscarPaquete(codigo);
    }

    public Paquete buscarPaqueteBinaria(String codigo) {
        return gestionPaquetes.buscaPaquetePorBinaria(codigo);
    }


    public void ordenarPaquetesAlfabeticamente() {
        gestionPaquetes.ordenarPaquetesAlfabeticamente();
    }

    public void mostrarPaquetesPrioridad() {
        gestionPaquetes.mostrarPaquetesPorOrdenPrioridad();
    }

    public void mostrarPaquetesPorPeso() {
        gestionPaquetes.mostrarPaquetesOrdenPeso();
    }

    public void mostrarPaquetesPorTiempoEstimado() {
        gestionPaquetes.mostrarPaquetesOrdenTiempoEstimado();
    }

    public void mostrarPaquetesPorOrdenLlegada() {
        gestionPaquetes.mostrarPaquetesRegistradosOrden();
    }

    public Paquete buscarPaqueteArrayList(String codigo) {
        return gestionPaquetes.buscarPaqueteArrayList(codigo);
    }

    /**
    =================================================
    PROCESAR PAQUETES
    =================================================
     */

    //Procesar según el orden de registro (Arraylist<>)
    public String procesarPaqueteOrdenRegistro() {
        Paquete paquete = gestionPaquetes.obtenerPaquetePorOrdenLlegada();
        return procesarPaqueteSeleccionado(paquete);
    }

    // Procesar según la prioridad
    public String procesarPaquetePorPrioridad() {
        Paquete paquete = gestionPaquetes.obtenerPaquetePorPrioridad();
        return procesarPaqueteSeleccionado(paquete);
    }

    //Asignación el Paquete a un Repartidor de la Misma Zona
    private String procesarPaqueteSeleccionado(Paquete paquete) {
        if (paquete == null) {
            System.out.println("No hay paquetes pendientes.");
            return null;
        }

        if (paquete.idRepartidor() != null) {
            System.out.println("El paquete ya tiene un repartidor asignado.");
            return null;
        }

        Repartidor repartidor = gestionRepartidores.buscarRepartidorDisponiblePorZona(paquete.getDestino());

        if (repartidor == null) {
            System.out.println("No Hay Repartidores Disponibles en la Zona " + paquete.getDestino());
            return null;
        }
        //Le asigno paquete al repartidor
        repartidor.addPaquete(paquete.getCodigo());
        paquete.setIdRepartidor(repartidor.getId());
        return paquete.toString();
    }


    /**
    =================================================
    GESTION REPARTIDORES
    =================================================
     */

    public boolean crearRepartidor(String nombre, String zona) {
        return gestionRepartidores.crearRepartidor(nombre, zona);
    }

    public boolean eliminarRepartidor(String codigo) {
        return gestionRepartidores.eliminarRepartidor(codigo);
    }

    public String mostrarRepartidores() {
        return gestionRepartidores.mostrarRepartidores();
    }

    public String mostrarRepartidoresConPaquetes() {
        return gestionRepartidores.mostrarRepartidoresConPaquetes();
    }

    public boolean enviarRuta(String codigo) {
        return gestionRepartidores.ocuparRepartidor(codigo);
    }


    /**
    =================================================
    GESTION ENTREGAS
    =================================================
     */

    public boolean agregarEntrega(String codigo) {
        Paquete paquete = gestionPaquetes.buscarPaquete(codigo);

        if (paquete == null || paquete.idRepartidor() == null) {
            return false;
        }

        String idRepartidor = paquete.idRepartidor();

        if (!gestionRepartidores.verificarEntrega(idRepartidor,codigo)) {
            return false;
        }

        if (!gestionRepartidores.eliminarPaqueteRepartidor(codigo, idRepartidor)) {
            return false;
        }

        if (!gestionPaquetes.eliminarPaquete(codigo)) {
            return false;
        }

        return gestionEntregas.agregarEntrega(paquete);
    }

    public boolean eliminarEntrega(int posicion) {
        return gestionEntregas.eliminarEntrega(posicion);
    }

    public String obtenerEntrega(int posicion) {
        return gestionEntregas.obtenerPaquete(posicion);
    }

    public void mostrarHistorialEntregas() {
        gestionEntregas.mostrarHistorialEntregas();
    }

    public String paquetesEntregados() {
        return gestionEntregas.cantidadPaquetesEntregados();
    }


    /**
    =================================================
    REPORTES
    =================================================
     */

    public void mostrarMunicipiosConPaquete() {
        gestionPaquetes.obtenerMunicipiosSinDuplicados();
    }

    public void mostrarMunicipiosConPaqueteOrden() {
        gestionPaquetes.obtenerMunicipiosSinDuplicadosOrdenados();
    }

    public void obtenerPaquetesPorDestino() {
        gestionPaquetes.obtenerPaquetePorDestino();
    }

    public double obtenerPesoPorMunicipio(String municipio) {
        return gestionPaquetes.obtenerPesoTotalMunicipio(municipio);
    }

    public int contarPrioridadMinima(int prioridadMinima) {
        return gestionPaquetes.contarPrioridadMinima(prioridadMinima);
    }

    public Paquete obtenerPaqueteConMayorPeso() {
        return gestionPaquetes.obtenerPaqueteConMayorPeso();
    }
}






