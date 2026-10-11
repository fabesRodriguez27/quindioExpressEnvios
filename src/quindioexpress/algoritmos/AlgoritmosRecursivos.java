package quindioexpress.algoritmos;
import quindioexpress.modelo.Paquete;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgoritmosRecursivos {

    // Peso total de paquetes de un determinado municipio
    public static double pesoTotalPorMunicipio(List<Paquete> lista, String municipio){
        if(!lista.isEmpty()){
            return pesoTotal(lista, municipio, 0);
        }
        return 0;
    }

    //Método auxiliar
    public static double pesoTotal(List<Paquete> lista, String municipio, int i){
        if(i==lista.size()){    // caso base
            return 0;
        }
        Paquete paquete = lista.get(i);
        double pesoTotal = 0;
        if(paquete.getDestino().equalsIgnoreCase(municipio)){
            pesoTotal = paquete.getPeso();
        }
        return pesoTotal + pesoTotal(lista,municipio, i+1);
    }

    //Paquetes con prioridad igual o superior a un valor
    public static int contarPrioridadMinima(List<Paquete> lista, int prioridadMin){
        if(!lista.isEmpty()){
            return contarPrioridad(lista, prioridadMin, 0);
        }
        return 0;
    }

    //Método auxiliar
    public static int contarPrioridad(List<Paquete> lista,int prioridadMin ,int i){
        if(i ==lista.size()){
            return 0;
        }
        if(lista.get(i).getPrioridad() >= prioridadMin){
            return 1 + contarPrioridad(lista, prioridadMin, i+1);
        }
        return contarPrioridad(lista,prioridadMin,i+1);
    }
}
