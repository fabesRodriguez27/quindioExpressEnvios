package quindioexpress.servicios;
import quindioexpress.algoritmos.AlgoritmosRecursivos;
import quindioexpress.algoritmos.BusquedaBinaria;
import quindioexpress.algoritmos.DivideYVenceras;
import quindioexpress.comparadores.ComparadorPaqueteCondicional;
import quindioexpress.comparadores.ComparadorPesoPaquete;
import quindioexpress.comparadores.ComparadorTiempoEstimado;
import quindioexpress.modelo.Paquete;
import quindioexpress.modelo.Repartidor;
import java.util.*;

public class QuindioExpress {

    //Orden de Inserción y util para Emplear el sort
    private ArrayList<Paquete> paquetesRegistrados;
    //Búsqueda Inmediata
    private HashMap<String, Paquete> paquetesPorCodigo;
    //La Prioridad de Despacho
    private PriorityQueue<Paquete> prioridades;
    private HashMap<String, Repartidor>  repartidores;
    /* Meli
    private ListaSimplementeEnlazada<Paquete> historialDespachos;
     */
    public QuindioExpress() {
        this.paquetesRegistrados = new ArrayList<>();
        this.paquetesPorCodigo = new HashMap<>();
        this.prioridades = new PriorityQueue<>(new ComparadorPaqueteCondicional());
        this.repartidores = new HashMap<>();
    }

    //Registrar Paquete
    public boolean registrarPaquete(Paquete paquete) {
        String codigo = paquete.getCodigo();
        //Evitar Códigos Duplicados
        if (paquetesPorCodigo.containsKey(codigo)) {
            return false;
        }
        paquetesRegistrados.add(paquete);
        paquetesPorCodigo.put(codigo, paquete);
        prioridades.offer(paquete);
        return true;
    }

    //Buscar Paquete
    public Paquete buscarPaquete(String codigo){
        return paquetesPorCodigo.get(codigo);
    }

    public boolean eliminarPaquete(String codigo){
        Paquete paqueteHallado = buscarPaquete(codigo);
        if(paqueteHallado != null){
            paquetesRegistrados.remove(paqueteHallado);
            paquetesPorCodigo.remove(paqueteHallado.getCodigo());
            prioridades.remove(paqueteHallado);
            return true;
        }
        return false;
    }

    //Mostrar Paquetes Registrados
    public void mostrarPaquetesRegistradosOrden(){
        for (Paquete paquete : paquetesRegistrados) {
            System.out.println(paquete.toString());
        }
    }

    //Mostrar Paquete en Prioridad
    public void mostrarPaquetePorOrdenPrioridad(){
        Paquete paquete = prioridades.peek();
        if(paquete != null){
            System.out.println(paquete.toString());
        }else{
            System.out.println("No hay paquetes registrados");
        }
    }

    // Obtener el primer paquete registrado
    public Paquete obtenerPaquetePorOrdenLlegada() {
        if (paquetesRegistrados.isEmpty()) {
            return null;
        }
        return paquetesRegistrados.get(0);
    }

    // Consultar el paquete de mayor prioridad
    public Paquete obtenerPaquetePorPrioridad() {
        return prioridades.peek();
    }

    // ---------- REPORTES ----------
    //Municipios que Tienen Paquetes Registrados y Ordenado Alfabéticamente
    public Set<String> obtenerMunicipiosSinDuplicadosOrdenados(){
        TreeSet<String> municipios = new TreeSet<>();
        for (Paquete paquete : paquetesRegistrados) {
            municipios.add(paquete.getDestino());
        }
        return municipios;
    }

    //Paquetes por Municipio
    public HashMap<String, ArrayList<String>> obtenerPaquetePorDestino(){
        HashMap<String, ArrayList<String>> destinosConCodigo = new HashMap<>();
        for (Paquete paquete : paquetesRegistrados) {
            String municipio = paquete.getDestino();
            if(destinosConCodigo.containsKey(municipio)){
                destinosConCodigo.get(municipio).add(paquete.getCodigo());
            }
            else{
                ArrayList<String> codigos = new ArrayList<>();
                codigos.add(paquete.getCodigo());
                destinosConCodigo.put(municipio, codigos);
            }
        }
        return destinosConCodigo;
    }

    //Paquetes Ordenados Alfabéticamente Codigo
    public ArrayList<Paquete> ordenarPaquetesAlfabeticamente(){
        ArrayList<Paquete> copia = new ArrayList<>(paquetesRegistrados);
        Collections.sort(copia);
        return copia;
    }

    //Paquetes Ordenados Por Peso
    public ArrayList<Paquete> ordenarPaquetesPeso(){
        ArrayList<Paquete> copia = new ArrayList<>(paquetesRegistrados);
        Collections.sort(copia, new ComparadorPesoPaquete());
        return copia;
    }

    //Paquetes Ordenados Por TiempoEstimado
    public ArrayList<Paquete> ordenarPaquetesTiempoEstimado(){
        ArrayList<Paquete> copia = new ArrayList<>(paquetesRegistrados);
        Collections.sort(copia, new ComparadorTiempoEstimado());
        return copia;
    }

    // REPARTIDORES

    // Registrar repartidor
    public boolean registrarRepartidor(Repartidor repartidor){
        if(repartidores.containsKey(repartidor.getId())){
            return false;
        }
        repartidores.put(repartidor.getId(), repartidor);
        return true;
    }

    // Consultar repartidor
    public Repartidor consultarRepartidor(String id) {
        return repartidores.get(id);
    }

    // Peso total de un determinado municipio
    public double pesoTotalMunicipio(String municipio){
        return AlgoritmosRecursivos.pesoTotalPorMunicipio(paquetesRegistrados, municipio);
    }

    // Paquete de mayor peso
    public Paquete paqueteConMayorPeso(){
        return DivideYVenceras.paqueteMayorPeso(paquetesRegistrados);
    }

    // cantidad de paquetes con minima prioridad
    public int contarPrioridadMin(int prioridadMinima){
        return AlgoritmosRecursivos.contarPrioridadMinima(paquetesRegistrados, prioridadMinima);
    }

    // Localizar paquete por busqueda Binaria
    public Paquete buscaPaquetePorBinaria(String codigo){
        return BusquedaBinaria.buscarPaquete(paquetesRegistrados, codigo);
    }



}
