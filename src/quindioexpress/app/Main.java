package quindioexpress.app;

import quindioexpress.servicios.QuindioExpress;

public class Main {
    public static void main(String[] args) {
        QuindioExpress centro = new QuindioExpress();
        DatosIniciales.cargar(centro);
    }
}