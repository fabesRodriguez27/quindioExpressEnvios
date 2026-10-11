package quindioexpress.algoritmos;

import quindioexpress.modelo.Paquete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BusquedaBinaria {

    // Localizar el paquete con busqueda binaria
    public static Paquete buscarPaquete(List<Paquete> lista, String codigo){
        if(!lista.isEmpty()){
            Collections.sort(lista);
            return buscar(lista, codigo, 0,  lista.size()-1);
        }
        return null;
    }

    //Método auxiliar
    public static Paquete buscar(List<Paquete> lista, String codigo, int inicio, int fin) {
        if (inicio > fin) {
            return null;                                   // caso base: no existe
        }
        int medio = (inicio + fin) / 2;
        String codigoDelMedio = lista.get(medio).getCodigo();
        int comparacion = codigo.compareTo(codigoDelMedio);

        if (comparacion == 0) {
            return lista.get(medio);                       // caso base: encontrado
        }
        if (comparacion > 0) {
            return buscar(lista, codigo, medio + 1, fin);  // va despues, sigo con la derecha
        }
        return buscar(lista, codigo, inicio, medio - 1); // va antes, sigo con la izquierda
    }
}
