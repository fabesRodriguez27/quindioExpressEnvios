package quindioexpress.app;
import quindioexpress.modelo.Paquete;
import quindioexpress.servicios.CentroLogistico;

/**
 * Datos quemados para la demostración.
 * - 30 paquetes con códigos no consecutivos
 * - 7 destinos: Armenia (9), Calarcá (5), Salento (4), Circasia (4),Montenegro (3), Quimbaya (3), La Tebaida (2)
 * - 8 repartidores (Armenia tiene dos)
 * - Prioridades de 1 a 5 (válidas con cualquiera de los dos rangos del enunciado)
 */

public class DatosIniciales {

    public static void cargar(CentroLogistico quindioExpress) {
        cargarPaquetes(quindioExpress);
        cargarRepartidores(quindioExpress);

    }

    private static void cargarPaquetes(CentroLogistico quindioExpress) {

        quindioExpress.agregarPaquete(new Paquete("Armenia",4.5, 5, 45));
        quindioExpress.agregarPaquete(new Paquete("Salento",3.0, 5, 25));
        quindioExpress.agregarPaquete(new Paquete("Armenia",7.2, 4, 20));
        quindioExpress.agregarPaquete(new Paquete("Calarca",2.3, 2, 35));
        quindioExpress.agregarPaquete(new Paquete("Salento",12.0, 3, 50));
        quindioExpress.agregarPaquete(new Paquete("Armenia",3.2, 1, 40));
        quindioExpress.agregarPaquete(new Paquete("Circasia",5.1, 2, 30));
        quindioExpress.agregarPaquete(new Paquete("Montenegro",7.4, 4, 55));
        quindioExpress.agregarPaquete(new Paquete("Armenia",9.8, 3, 25));
        quindioExpress.agregarPaquete(new Paquete("Quimbaya",6.0, 5, 60));
        quindioExpress.agregarPaquete(new Paquete("Calarca",18.7, 2, 45));   // el más pesado
        quindioExpress.agregarPaquete(new Paquete( "La Tebaida",1.5, 1, 50));
        quindioExpress.agregarPaquete(new Paquete("Armenia",10.4, 4, 35));
        quindioExpress.agregarPaquete(new Paquete("Circasia",2.8, 3, 20));
        quindioExpress.agregarPaquete(new Paquete("Salento",8.6, 2, 40));
        quindioExpress.agregarPaquete(new Paquete("Montenegro",14.3, 5, 65));
        quindioExpress.agregarPaquete(new Paquete("Armenia",6.7, 1, 30));
        quindioExpress.agregarPaquete(new Paquete("Calarca",4.0, 3, 25));
        quindioExpress.agregarPaquete(new Paquete("Quimbaya",11.2, 4, 55));
        quindioExpress.agregarPaquete(new Paquete("Circasia",3.6, 5, 15));
        quindioExpress.agregarPaquete(new Paquete("Armenia",13.5, 2, 45));
        quindioExpress.agregarPaquete(new Paquete("La Tebaida",5.5, 3, 35));
        quindioExpress.agregarPaquete(new Paquete("Calarca",9.1, 1, 60));
        quindioExpress.agregarPaquete(new Paquete("Salento",2.2, 4, 30));
        quindioExpress.agregarPaquete(new Paquete("Armenia",15.8, 3, 50));
        quindioExpress.agregarPaquete(new Paquete("Montenegro",1.9, 2, 20));
        quindioExpress.agregarPaquete(new Paquete("Quimbaya",7.9, 1, 40));
        quindioExpress.agregarPaquete(new Paquete("Circasia",16.2, 4, 55));
        quindioExpress.agregarPaquete(new Paquete("Armenia",8.3, 5, 20));
        quindioExpress.agregarPaquete(new Paquete("Calarca",6.4, 3, 30));
    }

    private static void cargarRepartidores(CentroLogistico quindioExpress) {
        quindioExpress.crearRepartidor("Carlos Gómez","Armenia");
        quindioExpress.crearRepartidor("Laura Ramírez","Armenia");
        quindioExpress.crearRepartidor("Andrés Torres","Calarcá");
        quindioExpress.crearRepartidor("Julián Ospina","Circasia");
        quindioExpress.crearRepartidor("Diana Herrera","Montenegro");
        quindioExpress.crearRepartidor("Felipe Castaño","Quimbaya");
        quindioExpress.crearRepartidor("Marta Quintero", "Salento");
        quindioExpress.crearRepartidor("Pedro Ramirez", "La Tebaida");

    }
}