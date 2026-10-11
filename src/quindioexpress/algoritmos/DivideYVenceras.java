package quindioexpress.algoritmos;

import quindioexpress.modelo.Paquete;

import java.util.List;

public class DivideYVenceras {

    // Paquete de mayor peso en el conjunto, con divide y venceras
    public static Paquete paqueteMayorPeso(List<Paquete> lista){
        if(!lista.isEmpty()){
            return mayorPeso(lista, 0, lista.size()-1);
        }
        return null;
    }

    public static Paquete mayorPeso(List<Paquete> lista, int inicio, int fin){
        if(inicio == fin){
            return lista.get(inicio);
        }

        int mitad = (inicio + fin) / 2;

        Paquete ladoIzquierdo = mayorPeso(lista, inicio, mitad);
        Paquete ladoDerecho =mayorPeso(lista, mitad+1, fin);

        if(ladoIzquierdo.getPeso()>= ladoDerecho.getPeso()){
            return ladoIzquierdo;
        }else{
            return ladoDerecho;
        }

    }
}
