package quindioexpress.estructuras;

public interface MiColeccion<T> {

    void add(T dato);

    boolean remove(int posicion);

    T nodeValue(int posicion);

    int getSize();

    boolean isEmpty();

}
