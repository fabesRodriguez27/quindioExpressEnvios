package quindioexpress.estructuras;

// sin public para que solo pueda ser accesible desde el mismo package de estructuras
class Nodo<T> {
    T valor;
    Nodo<T> siguiente;

    Nodo(T valor) {
        this.valor = valor;
    }
}
