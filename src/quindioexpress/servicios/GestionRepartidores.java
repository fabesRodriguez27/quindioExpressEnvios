package quindioexpress.servicios;
import quindioexpress.modelo.Repartidor;

import javax.swing.*;
import java.util.HashMap;
import java.util.Random;

public class GestionRepartidores {
    private HashMap<String, Repartidor> repartidores;
    private final String[] MUNICIPIOS = {"Armenia", "Calarca", "Circasia", "Salento", "Filandia", "Montenegro",
            "Quimbaya", "La Tebaida", "Pijao", "Buenavista", "Córdoba", "Génova"};

    public GestionRepartidores() {
        this.repartidores = new HashMap<>();
    }

    public boolean crearRepartidor(String nombre, String zona) {
        String id;
        do {
            id = generarCodigo();
        } while (repartidores.containsKey(id));

        if (!validarMunicipio(zona)) {
            return false;
        } else {
            repartidores.put(id, new Repartidor(id, nombre, zona));
            return true;
        }
    }

    public boolean eliminarRepartidor (String id){
        Repartidor repartidor = repartidores.get(id);

        if (repartidor == null) {
            return false;
        }

        if (!repartidor.getPaquetes().isEmpty()){
            return  false;
        }

        repartidores.remove(id);
        return true;
    }

    public String mostrarRepartidores (){

        String mensaje = "REPARTIDORES REGISTRADOS: \n";

        for(Repartidor repartidor : repartidores.values()){
            mensaje += repartidor.toString()+"\n";
        }

        return mensaje;
    }

    public String mostrarRepartidoresConPaquetes (){

        String mensaje = "REPARTIDORES CON PAQUETES ASIGNADOS: \n";

        for(Repartidor repartidor : repartidores.values()) {
            if (!repartidor.getPaquetes().isEmpty()) {
                mensaje += repartidor.getNombre() + "\n";
                for (String paquete : repartidor.getPaquetes()) {
                    mensaje += "Paquete: " + paquete + "\n";
                }
            }
        }
        return mensaje;
    }

    public Repartidor buscarRepartidorDisponiblePorZona (String zona){
        for (Repartidor repartidor : repartidores.values()) {
            if (repartidor.isDisponible() && repartidor.getZona().equalsIgnoreCase(zona)) {
                return repartidor;
            }
        }
        return null;
    }

    public boolean eliminarPaqueteRepartidor (String codigo, String id) {
        Repartidor repartidor = repartidores.get(id);
        if (repartidor == null) {
            return false;
        }
        boolean eliminado = repartidor.getPaquetes().remove(codigo);

        if (eliminado && repartidor.getPaquetes().isEmpty()) {
            repartidor.setDisponible(true);
        }
        return eliminado;
    }

    public boolean ocuparRepartidor (String id){
        if (repartidores.containsKey(id) && !repartidores.get(id).getPaquetes().isEmpty()) {
            repartidores.get(id).setDisponible(false);
            return true;
        }
        return false;
    }

    public boolean validarMunicipio (String municipio){
        if (municipio == null) {
            return false;
        }
        for (String m : MUNICIPIOS) {
            if (m.equalsIgnoreCase(municipio.trim())) {
                return true;
            }
        }
        return false;
    }

    public String generarCodigo () {
        Random random = new Random();
        int numero = random.nextInt(1000);
        return String.format("R%03d", numero);
    }

    public boolean verificarEntrega(String id, String codigo) {

        Repartidor repartidor = repartidores.get(id);

        if (repartidor == null) {
            return false;
        }

        if (repartidor.isDisponible()) {
            return false;
        }

        return repartidor.getPaquetes().contains(codigo);
    }

}

