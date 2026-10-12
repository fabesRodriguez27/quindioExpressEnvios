package quindioexpress.estructuras;

import java.util.Iterator;

public class IteradorLista <T> implements Iterator <T> {
    private Nodo<T> actual;

    public IteradorLista(Nodo<T> nodoPrimero) {
        actual = nodoPrimero;
    }

    @Override
    public boolean hasNext() {
        return actual != null;
    }

    @Override
    public T next() {

        T dato = actual.getDato();
        actual = actual.getSiguiente();

        return dato;
    }
}
