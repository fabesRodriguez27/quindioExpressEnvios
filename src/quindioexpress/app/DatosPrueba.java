package quindioexpress.app;
import quindioexpress.modelo.Paquete;
import quindioexpress.servicios.QuindioExpress;

import java.util.Random;

public class DatosPrueba {

    public static String crearPaquetes(QuindioExpress centro, int cantidad) {

        Random random = new Random();
        String codigoUltimo = null;
        String[] municipios = {"Armenia", "Calarcá", "Circasia", "Montenegro", "Quimbaya", "Pereira", "Manizales"};

        for (int i = 0; i < cantidad; i++) {

            String destino = municipios[random.nextInt(municipios.length)];
            double peso = random.nextDouble(49) + 1;
            int prioridad = random.nextInt(5) + 1;
            int tiempoEstimado = random.nextInt(10) + 1;

            Paquete paquete = new Paquete(destino, peso, prioridad, tiempoEstimado);

            //Si el código está repetido, generamos otro paquete.
            while (!centro.agregarPaquete(paquete)) {
                paquete = new Paquete(destino, peso, prioridad, tiempoEstimado);
            }
            // Guardamos el código real del paquete registrado.
            codigoUltimo = paquete.getCodigo();
        }

        return codigoUltimo;
    }

    public static void probarBusqueda(QuindioExpress centro, int cantidad) {

        // 1. Crear todos los paquetes.
        // Este tiempo no se incluye en las mediciones porque quiero medir es solo buscar
        String codigoBuscado = crearPaquetes(centro, cantidad);
        // 2. Solicitar la liberación de memoria no utilizada.
        System.gc();
        // 3. Buscar usando HashMap.
        long inicioHashMap = System.nanoTime();
        Paquete paqueteHashMap = centro.buscarPaquete(codigoBuscado);
        long finHashMap = System.nanoTime();
        long tiempoHashMap = finHashMap - inicioHashMap;
        // 4. Buscar usando ArrayList.
        long inicioArrayList = System.nanoTime();
        Paquete paqueteArrayList = centro.buscarPaqueteArrayList(codigoBuscado);
        long finArrayList = System.nanoTime();
        long tiempoArrayList = finArrayList - inicioArrayList;

        // 5. Mostrar los resultados.
        System.out.println("\n==============================");
        System.out.println("PRUEBA CON " + cantidad + " PAQUETES");
        System.out.println("==============================");
        System.out.println("Código buscado: " + codigoBuscado);

        System.out.println("\n--- BÚSQUEDA CON HASHMAP ---");
        System.out.println("Paquete encontrado:\n " + (paqueteHashMap));
        System.out.println("Tiempo: " + tiempoHashMap + " ns");

        System.out.println("\n--- BÚSQUEDA CON ARRAYLIST ---");
        System.out.println("Paquete encontrado:\n " + (paqueteArrayList));
        System.out.println("Tiempo: " + tiempoArrayList + " ns");

    }
}