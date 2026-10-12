package quindioexpress.estructuras;

public class ListaSimplementeEnlazada <T> implements Iterable<T>, MiColeccion<T> {
    private int size;
    private Nodo<T> nodoPrimero;
    private Nodo<T> nodoUltimo;

    public ListaSimplementeEnlazada() {
        size = 0;
        nodoPrimero = null;
        nodoUltimo = null;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // Agregar un elemento, se agrega al final
    public void add(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);

        if (isEmpty()) {
            nodoPrimero = nuevoNodo;
            nodoUltimo = nuevoNodo;
        } else {
            nodoUltimo.setSiguiente(nuevoNodo);
            nodoUltimo = nuevoNodo;
        }
        size++;
    }

    // Obtener elemento según posición
    public T nodeValue(int posicion) {
        Nodo<T> auxiliar = nodoPrimero;

        if (posicion <0 || posicion >= size) {
            return null;
        } else {
            for (int i = 0; i < posicion; i++) {
                auxiliar = auxiliar.getSiguiente();
            }
            return auxiliar.getDato();
        }
    }

    // Eliminar elemento según posición
    public boolean remove(int posicion) {
        if (posicion <0 || posicion >= size) {
            return false;
        }else if (posicion == 0) {
            nodoPrimero = nodoPrimero.getSiguiente();
            if (size == 1) {
                nodoUltimo = null;
            }
        }else {
            Nodo<T> nodoAnterior = nodoPrimero;

            for (int i = 0; i < posicion - 1; i++) {
                nodoAnterior = nodoAnterior.getSiguiente();
            }

            Nodo<T> auxiliar = nodoAnterior.getSiguiente();
            nodoAnterior.setSiguiente(auxiliar.getSiguiente());

            if (posicion == size - 1) {
                nodoUltimo = nodoAnterior;
            }
        }
        size --;
        return true;
    }

    @Override
    public IteradorLista<T> iterator() {
        return new IteradorLista<>(nodoPrimero);
    }

}