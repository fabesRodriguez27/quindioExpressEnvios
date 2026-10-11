package quindioexpress.app;
import quindioexpress.modelo.Paquete;
import quindioexpress.modelo.Repartidor;
import quindioexpress.servicios.QuindioExpress;

/**
 * Datos quemados para la demostración.
 * - 30 paquetes con códigos no consecutivos
 * - 7 destinos: Armenia (9), Calarcá (5), Salento (4), Circasia (4),Montenegro (3), Quimbaya (3), La Tebaida (2)
 * - 7 repartidores (Armenia tiene dos; Salento tiene uno NO disponible; La Tebaida no tiene repartidor)
 * - Prioridades de 1 a 5 (válidas con cualquiera de los dos rangos del enunciado)
 */

public class DatosIniciales {

    public static void cargar(QuindioExpress centro) {
        cargarPaquetes(centro);
        /*
        cargarRepartidores(centro);

         */
    }

    private static void cargarPaquetes(QuindioExpress center) {

        registrar(center, "PQ731", "Armenia",     4.5, 5, 45);
        registrar(center, "PQ105", "Salento",     3.0, 5, 25);
        registrar(center, "PQ942", "Armenia",     7.2, 4, 20);
        registrar(center, "PQ318", "Calarcá",     2.3, 2, 35);
        registrar(center, "PQ567", "Salento",    12.0, 3, 50);
        registrar(center, "PQ829", "Armenia",     3.2, 1, 40);
        registrar(center, "PQ214", "Circasia",    5.1, 2, 30);
        registrar(center, "PQ421", "Montenegro",  7.4, 4, 55);
        registrar(center, "PQ842", "Armenia",     9.8, 3, 25);
        registrar(center, "PQ476", "Quimbaya",    6.0, 5, 60);
        registrar(center, "PQ653", "Calarcá",    18.7, 2, 45);   // el más pesado
        registrar(center, "PQ390", "La Tebaida",  1.5, 1, 50);
        registrar(center, "PQ918", "Armenia",    10.4, 4, 35);
        registrar(center, "PQ247", "Circasia",    2.8, 3, 20);
        registrar(center, "PQ584", "Salento",     8.6, 2, 40);
        registrar(center, "PQ706", "Montenegro", 14.3, 5, 65);
        registrar(center, "PQ163", "Armenia",     6.7, 1, 30);
        registrar(center, "PQ871", "Calarcá",     4.0, 3, 25);
        registrar(center, "PQ439", "Quimbaya",   11.2, 4, 55);
        registrar(center, "PQ625", "Circasia",    3.6, 5, 15);
        registrar(center, "PQ052", "Armenia",    13.5, 2, 45);
        registrar(center, "PQ798", "La Tebaida",  5.5, 3, 35);
        registrar(center, "PQ341", "Calarcá",     9.1, 1, 60);
        registrar(center, "PQ967", "Salento",     2.2, 4, 30);
        registrar(center, "PQ283", "Armenia",    15.8, 3, 50);
        registrar(center, "PQ514", "Montenegro",  1.9, 2, 20);
        registrar(center, "PQ676", "Quimbaya",    7.9, 1, 40);
        registrar(center, "PQ129", "Circasia",   16.2, 4, 55);
        registrar(center, "PQ850", "Armenia",     8.3, 5, 20);
        registrar(center, "PQ420", "Calarcá",     6.4, 3, 30);
    }

    /*
    private static void cargarRepartidores(QuindioExpress center) {
        center.registrarRepartidor(new Repartidor("R001", "Carlos Gómez",    "Armenia"));
        center.registrarRepartidor(new Repartidor("R002", "Laura Ramírez",   "Armenia"));
        center.registrarRepartidor(new Repartidor("R003", "Andrés Torres",   "Calarcá"));
        center.registrarRepartidor(new Repartidor("R005", "Julián Ospina",   "Circasia"));
        center.registrarRepartidor(new Repartidor("R006", "Diana Herrera",   "Montenegro"));
        center.registrarRepartidor(new Repartidor("R007", "Felipe Castaño",  "Quimbaya"));

        // Repartidor de Salento NO disponible: sirve para demostrar la regla
        Repartidor marta = new Repartidor("R004", "Marta Quintero", "Salento");
        marta.setDisponible(false);
        center.registrarRepartidor(marta);
    }

     */

    private static void registrar(QuindioExpress center, String codigo, String destino, double peso, int prioridad, int tiempo) {
        center.agregarPaquete(new Paquete(destino, peso, prioridad, tiempo));
    }
}