package quindioexpress.servicios;
import quindioexpress.algoritmos.AlgoritmosRecursivos;
import quindioexpress.algoritmos.BusquedaBinaria;
import quindioexpress.algoritmos.DivideYVenceras;
import quindioexpress.comparadores.ComparadorPaqueteCondicional;
import quindioexpress.comparadores.ComparadorPesoPaquete;
import quindioexpress.comparadores.ComparadorTiempoEstimado;
import quindioexpress.modelo.Paquete;

import java.util.*;

public class GestionPaquetes {

    //Orden de Inserción y util para Emplear el sort
    private ArrayList<Paquete> paquetesRegistrados;
    //Búsqueda Inmediata
    private HashMap<String, Paquete> paquetesPorCodigo;
    //La Prioridad de Despacho
    private PriorityQueue<Paquete> prioridades;

    public GestionPaquetes() {
        this.paquetesRegistrados = new ArrayList<>();
        this.paquetesPorCodigo = new HashMap<>();
        this.prioridades = new PriorityQueue<>(new ComparadorPaqueteCondicional());
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
        System.out.println("Registrado: " + codigo);
        return true;
    }

    public void mostrarCantidadPaquetes() {
        System.out.println("Paquetes registrados: " + paquetesRegistrados.size());
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

    //Paquetes Ordenados Alfabéticamente Codigo
    public void ordenarPaquetesAlfabeticamente(){
        ArrayList<Paquete> copia = new ArrayList<>(paquetesRegistrados);
        Collections.sort(copia);
        imprimirLista(copia);
    }

    public void imprimirLista(ArrayList<Paquete> lista){
        for(Paquete paquete : lista){
            System.out.println(paquete.toString());
        }
    }

    //Mostrar todos los paquetes ordenados por prioridad
    public void mostrarPaquetesPorOrdenPrioridad() {
        PriorityQueue<Paquete> copia = new PriorityQueue<>(prioridades);
        if (copia.isEmpty()) {
            System.out.println("No hay paquetes registrados");
            return;
        }
        while (!copia.isEmpty()) {
            Paquete paquete = copia.poll();
            System.out.println(paquete.toString());
        }
    }

    //Paquetes Ordenados Por Peso
    public void mostrarPaquetesOrdenPeso(){
        ArrayList<Paquete> copia = new ArrayList<>(paquetesRegistrados);
        Collections.sort(copia, new ComparadorPesoPaquete());
        imprimirLista(copia);
    }

    //Paquetes Ordenados Por TiempoEstimado
    public void mostrarPaquetesOrdenTiempoEstimado(){
        ArrayList<Paquete> copia = new ArrayList<>(paquetesRegistrados);
        Collections.sort(copia, new ComparadorTiempoEstimado());
        imprimirLista(copia);
    }

    //Buscar Paquete ArrayList (Para la Prueba)
    public Paquete buscarPaqueteArrayList(String codigo){
        for(Paquete paquete : paquetesRegistrados) {
            if(paquete.getCodigo().equals(codigo)) {
                return paquete;
            }
        }
        return null;
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
        for (Paquete paquete : paquetesRegistrados) {

            if (paquete.idRepartidor() == null) {
                return paquete;
            }
        }
        return null;
    }

    // Consultar el paquete de mayor prioridad
    public Paquete obtenerPaquetePorPrioridad() {
        PriorityQueue<Paquete> copia = new PriorityQueue<>(prioridades);
        while (!copia.isEmpty()) {
            Paquete paquete = copia.poll();
            if (paquete.idRepartidor() == null) {
                return paquete;
            }
        }
        return null;
    }

    // ---------- REPORTES ----------

    //Municipios que Tienen Paquetes Registrados y sin orden alfabético
    public void obtenerMunicipiosSinDuplicados(){
        HashSet<String> municipios = new HashSet<>();
        for (Paquete paquete : paquetesRegistrados) {
            municipios.add(paquete.getDestino());
        }
        imprimirColeccion(municipios);
    }

    //Municipios que Tienen Paquetes Registrados y Ordenado Alfabéticamente
    public void obtenerMunicipiosSinDuplicadosOrdenados(){
        TreeSet<String> municipios = new TreeSet<>();
        for (Paquete paquete : paquetesRegistrados) {
            municipios.add(paquete.getDestino());
        }
        imprimirColeccion(municipios);
    }

    public void imprimirColeccion(Collection<String> coleccion) {
        for (String elemento : coleccion) {
            System.out.println(elemento);
        }
    }

    //Paquetes por Municipio
    public void obtenerPaquetePorDestino(){
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
        imprimirPaquetesPorDestino(destinosConCodigo);
    }

    public void imprimirPaquetesPorDestino(HashMap<String, ArrayList<String>> destinosConCodigo) {
        for(Map.Entry<String, ArrayList<String>> entry : destinosConCodigo.entrySet()){
            System.out.println("Municipio: " + entry.getKey());
            for(String codigo : entry.getValue()){
                System.out.println("  Código: " + codigo);
            }
        }
    }

    //Peso total de un determinado municipio
    public double obtenerPesoTotalMunicipio(String municipio){
        return AlgoritmosRecursivos.pesoTotalPorMunicipio(paquetesRegistrados, municipio);
    }

    //Cantidad de paquetes con minima prioridad
    public int contarPrioridadMinima(int prioridadMinima){
        return AlgoritmosRecursivos.contarPrioridadMinima(paquetesRegistrados, prioridadMinima);
    }

    //Paquete de mayor peso
    public Paquete obtenerPaqueteConMayorPeso(){
        return DivideYVenceras.paqueteMayorPeso(paquetesRegistrados);
    }


    //Localizar paquete por Búsqueda Binaria
    public Paquete buscaPaquetePorBinaria(String codigo){
        return BusquedaBinaria.buscarPaquete(paquetesRegistrados, codigo);
    }
}
