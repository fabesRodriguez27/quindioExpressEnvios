package quindioexpress.app;
import quindioexpress.modelo.Paquete;
import quindioexpress.servicios.CentroLogistico;

import javax.swing.*;

public class Main {
    public static CentroLogistico quindioExpress = new CentroLogistico();

    public static void main(String[] args) {
        /*
        DatosPrueba.probarBusqueda(quindioExpress, 500000);
         */
        DatosIniciales.cargar(quindioExpress);
        quindioExpress.totalPaquetesRegistrados();
        menuGestionarQuindioExpress();
    }

    /**
    ==================================
    MENU PRINCIPAL
    ==================================
     */

    public static void menuGestionarQuindioExpress() {
        int opcion;
        do {
            String mensaje = "====== INGRESE LA OPCIÓN CORRESPONDIENTE ======\n"
                    + "1. Gestionar Paquetes\n"
                    + "2. Gestionar Repartidores\n"
                    + "3. Gestionar Entregas\n"
                    + "4. Reportes QuindíoExpress\n"
                    + "5. Salir del Programa\n";
            opcion = Integer.parseInt(JOptionPane.showInputDialog(mensaje));
            validarMenuGestionarQuindioExpress(opcion);
        } while (opcion != 5);
    }

    public static void validarMenuGestionarQuindioExpress(int opcion) {
        switch (opcion) {
            case 1:
                menuGestionarPaquetes();
                break;
            case 2:
                menuGestionarRepartidores();
                break;
            case 3:
                menuHistorialEntregas();
                break;
            case 4:
                menuReportes();
                break;

            case 5:
                mostrarMensaje(".....Saliendo del Programa....");
                break;

            default:
                System.out.println("Opción no válida. Intente Nuevamente");
                break;
        }
    }

    /**
    ==================================
    MENU GESTIONAR PAQUETES
    ==================================
     */

    public static void menuGestionarPaquetes() {
        int opcion;
        do {
            String mensaje = "====== INGRESE LA OPCIÓN CORRESPONDIENTE ======\n"
                    + "1. Agregar Paquete\n"
                    + "2. Eliminar Paquete\n"
                    + "3. Buscar Paquete por Código\n"
                    + "4. Mostrar Paquetes\n"
                    + "5. Procesar Paquetes\n"
                    + "6. Regresar al Menú Principal";
            opcion = Integer.parseInt(JOptionPane.showInputDialog(mensaje));
            validarMenuGestionarPaquetes(opcion);
        } while (opcion != 6);
    }

    public static void validarMenuGestionarPaquetes(int opcion) {
        switch (opcion) {
            case 1:
                agregarPaquete();
                break;
            case 2:
                eliminarPaquete();
                break;
            case 3:
                buscarPaquetePorCodigo();
                break;
            case 4:
                menuMostrarPaquetes();
                break;
            case 5:
                menuProcesarPaquetes();
                break;
            case 6:
                break;
            default:
                System.out.println("Opción no válida. Intente Nuevamente");
                break;
        }
    }

    public static void agregarPaquete() {
        try {
            String destino = JOptionPane.showInputDialog("Ingrese el Destino del Paquete:");

            // Si el usuario cancela, no se registra el paquete.
            if (destino == null) {
                return;
            }
            double peso = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el Peso del Paquete:"));
            int prioridad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la Prioridad del Paquete:"));
            int tiempoEstimado = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el Tiempo Estimado del Paquete:"));

            Paquete paquete = new Paquete(destino, peso, prioridad, tiempoEstimado
            );

            if (quindioExpress.agregarPaquete(paquete)) {
                JOptionPane.showMessageDialog(null, "Paquete registrado con éxito");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo registrar el paquete. " + "Verifique que el destino esté registrado.");
            }

        } catch (NumberFormatException e) {JOptionPane.showMessageDialog(null, "Debe ingresar un número válido.", "Dato incorrecto", JOptionPane.WARNING_MESSAGE);

        } catch (IllegalArgumentException e) {JOptionPane.showMessageDialog(null, "Error al registrar el paquete: " + e.getMessage(), "Dato Incorrecto", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void eliminarPaquete() {
        String codigo = JOptionPane.showInputDialog("Ingrese el Código del Paquete a Eliminar: ");
        if (quindioExpress.eliminarPaquete(codigo)) {
            JOptionPane.showMessageDialog(null, "Paquete Eliminado con Éxito");
        } else {
            JOptionPane.showMessageDialog(null, "No se Pudo Eliminar el Paquete. El Código No Existe");
        }
    }

    public static void buscarPaquetePorCodigo() {
        String codigo = JOptionPane.showInputDialog("Ingrese el Código del Paquete a Buscar: ");
        Paquete paquete = quindioExpress.buscarPaquete(codigo);
        Paquete paquete1 = quindioExpress.buscarPaqueteBinaria(codigo);
        if (paquete != null && paquete1 != null) {
            System.out.println("El Paquete es: " + paquete.toString());
            System.out.println("El Paquete por busqueda binaria es: " + paquete1.toString());
        } else {
            System.out.println("No se Pudo Encontrar el Paquete");
        }
    }

    public static void menuMostrarPaquetes() {
        int opcion;
        do {
            String mensaje = "====== INGRESE LA OPCIÓN CORRESPONDIENTE ======\n"
                    + "1. Mostrar Paquetes Por Orden Alfabético de Código\n"
                    + "2. Mostrar Paquetes Por Prioridad de Mayor a Menor\n"
                    + "3. Mostrar Paquetes Por Peso de Mayor a Menor\n"
                    + "4. Mostrar Paquetes Por Tiempo Estimado de Menor a Mayor\n"
                    + "5. Mostrar Paquetes Por Orden de Llegada\n"
                    + "6. Regresar";
            opcion = Integer.parseInt(JOptionPane.showInputDialog(mensaje));
            validarMenuMostrarPaquetes(opcion);
        } while (opcion != 6);
    }

    public static void validarMenuMostrarPaquetes(int opcion) {
        switch (opcion) {
            case 1:
                mostrarPaquetesOrdenAlfabetico();
                break;
            case 2:
                mostrarPaquetePorPrioridad();
                break;
            case 3:
                mostrarPaquetesPorPeso();
                break;
            case 4:
                mostrarPaquetesPorTiempoEstimado();
                break;
            case 5:
                mostrarPaquetesPorOrdenLlegada();
                break;
            case 6:
                break;
            default:
                System.out.println("Opción no válida. Intente Nuevamente");
                break;
        }
    }

    public static void mostrarPaquetesOrdenAlfabetico() {
        System.out.println("===== Paquetes Ordenados Alfabéticamente ====\n");
        quindioExpress.ordenarPaquetesAlfabeticamente();
    }

    public static void mostrarPaquetePorPrioridad() {
        System.out.println("===== Paquetes Ordenados Por Prioridad ====\n");
        quindioExpress.mostrarPaquetesPrioridad();
    }

    public static void mostrarPaquetesPorPeso() {
        System.out.println("===== Paquetes Ordenados de Mayor a Menor Peso ====\n");
        quindioExpress.mostrarPaquetesPorPeso();
    }

    public static void mostrarPaquetesPorTiempoEstimado() {
        System.out.println("===== Paquetes Ordenados de Menor a Mayor Tiempo Estimado ====\n");
        quindioExpress.mostrarPaquetesPorTiempoEstimado();
    }

    public static void mostrarPaquetesPorOrdenLlegada() {
        System.out.println("===== Paquetes Ordenados por Orden de Llegada ====\n");
        quindioExpress.mostrarPaquetesPorOrdenLlegada();
    }

    public static void menuProcesarPaquetes() {
        int opcion;
        do {
            String mensaje = "====== INGRESE LA OPCIÓN CORRESPONDIENTE ======\n"
                    + "1. Procesar Paquete Por Orden de Registro\n"
                    + "2. Procesar Paquete Por Prioridad\n"
                    + "3. Regresar";
            opcion = Integer.parseInt(JOptionPane.showInputDialog(mensaje));
            validarMenuProcesarPaquetes(opcion);
        } while (opcion != 3);
    }

    public static void validarMenuProcesarPaquetes(int opcion) {
        switch (opcion) {
            case 1:
                String mensaje1 = quindioExpress.procesarPaqueteOrdenRegistro();
                if (mensaje1 != null) {
                    mostrarMensaje("Se procesó el paquete: \n" + mensaje1);
                } else {
                    mostrarMensaje("No se encontró ningún paquete en la posición especificada");
                }
                break;
            case 2:
                String mensaje2 = quindioExpress.procesarPaquetePorPrioridad();
                if (mensaje2 != null) {
                    mostrarMensaje("Se proceso el paquete: \n" + mensaje2);
                } else {
                    mostrarMensaje("No se encontró ningún paquete en la posición especificada");
                }
                break;
            case 3:
                break;
            default:
                System.out.println("Opción no válida. Intente Nuevamente");
                break;
        }
    }

    /**
    ==================================
    MENU GESTIONAR REPARTIDORES
    ==================================
     */

    public static void menuGestionarRepartidores() {
        String menu = "====== INGRESE LA OPCIÓN CORRESPONDIENTE ======\n"
                + "(1) Agregar Repartidor:\n"
                + "(2) Eliminar Repartidor:\n"
                + "(3) Mostrar Repartidores:\n"
                + "(4) Mostrar Repartidores con Paquetes Asignados:\n"
                + "(5) Enviar a RUTA repartidor: \n"
                + "(6) Volver al Menú Principal:\n";
        int opcion;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
            validarMenuGestionarRepartidores(opcion);
        } while (opcion != 6);
    }

    public static void validarMenuGestionarRepartidores(int opcion) {
        switch (opcion) {

            case 1:
                //(1) Agregar Entregas de Repartidor
                boolean canCrear = quindioExpress.crearRepartidor(
                        capturarDato("Ingrese el nombre del Repartidor: "),
                        capturarDato("Ingrese la zona: "));
                mostrarMensaje(canCrear ? "Se creó el repartidor" : "No se pudo crear el repartidor");
                break;

            case 2:
                //(2) Eliminar Repartidor
                boolean canEliminar = quindioExpress.eliminarRepartidor(
                        capturarDato("Ingrese el id del repartidor que desea eliminar: "));
                mostrarMensaje(canEliminar ? "Se eliminó el repartidor" : "No se pudo eliminar el repartidor");
                break;

            case 3:
                //(3) Mostrar Repartidores
                mostrarMensaje(quindioExpress.mostrarRepartidores());
                break;

            case 4:
                //(4) Mostrar Repartidores con Paquetes Asignados
                mostrarMensaje(quindioExpress.mostrarRepartidoresConPaquetes());
                break;

            case 5:
                //(4) Mandar a ruta repartidor
                boolean canEnviar = quindioExpress.enviarRuta(
                        capturarDato("Ingrese el código del repartidor que sale a ruta"));
                mostrarMensaje(canEnviar ? "Se envió a ruta" : "El repartidor no tiene paquetes asignados");
                break;

            case 6:
                break;

            default:
                mostrarMensaje("No ingresó ninguna opción válida");
                break;
        }
    }

    /**
    ==================================
    MENU HISTORIAL DE ENTREGAS
    ==================================
     */

    public static void menuHistorialEntregas() {
        String menu = "====== INGRESE LA OPCIÓN CORRESPONDIENTE ======\n"
                + "(1) Agregar Paquete entregado:\n"
                + "(2) Eliminar Paquete por posición:\n"
                + "(3) Obtener Paquete en posición específica: \n"
                + "(4) Mostrar historial entregas:\n"
                + "(5) Cantidad de paquetes Entregados:\n"
                + "(6) Volver al Menú Anterior:\n";
        int opcion;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
            validarMenuHistorialEntregas(opcion);
        } while (opcion != 6);
    }

    public static void validarMenuHistorialEntregas(int opcion) {
        switch (opcion) {

            case 1:
                //(1) Agregar Paquete Entregado
                boolean canAgregar = quindioExpress.agregarEntrega(
                        capturarDato("Ingrese el código del paquete entregado: "));
                mostrarMensaje(canAgregar ? "Se agregó la entrega al historial" : "No se pudo agregar la entrega al historial");
                break;

            case 2:
                //(2) Eliminar Paquete por posición
                boolean canEliminar = quindioExpress.eliminarEntrega(
                        Integer.parseInt(capturarDato("Ingrese la posición de la entrega que desea eliminar: ")));
                mostrarMensaje(canEliminar ? "Se eliminó la entrega" : "No se pudo eliminar la entrega");
                break;

            case 3:
                //((3) Obtener Paquete en posición específica
                String mensaje = quindioExpress.obtenerEntrega(
                        Integer.parseInt(capturarDato("Ingrese la posición de la entrega que desea obtener: ")));
                if (mensaje == null) {
                    mostrarMensaje("No se encontró ningún paquete en la posición especificada");
                }else {
                    mostrarMensaje(mensaje);
                }
                break;

            case 4:
                //(4) Mostrar historial entregas
                quindioExpress.mostrarHistorialEntregas();
                break;

            case 5:
                //(5) Cantidad de paquetes Entregados
                mostrarMensaje(quindioExpress.paquetesEntregados());
                break;

            case 6:
                break;

            default:
                mostrarMensaje("No ingresó ninguna opción válida");
                break;
        }
    }

     /**
    ==================================
    MENU REPORTES QUINDÍO EXPRESS
    ==================================
     */


    public static void menuReportes() {
        int opcion;
        do {
            String mensaje = "====== INGRESE LA OPCIÓN CORRESPONDIENTE ======\n"
                    + "(1) Municipios con al menos un paquete\n"
                    + "(2) Municipios con al menos un paquete ordenados alfabéticamente\n"
                    + "(3) Paquetes por municipio\n"
                    + "(4) Peso de paquetes pendientes por zona\n"
                    + "(5) Numero de paquetes mayor a prioridad indicada\n"
                    + "(6) Paquete con mayor peso\n"
                    + "(7) Regresar a menu principal \n";
            opcion = Integer.parseInt(JOptionPane.showInputDialog(mensaje));
            validarMenuReportes(opcion);
        } while (opcion != 7);
    }

    public static void validarMenuReportes(int opcion) {
        switch (opcion) {
            case 1:
                //(1) Municipios con al menos un paquete
                System.out.println("\n===== Municipios con Al Menos Un Paquete =====");
                quindioExpress.mostrarMunicipiosConPaquete();
                break;
            case 2:
                //(2) Municipios con al menos un paquete orden alfabético
                System.out.println("\n===== Municipios con Al Menos Un Paquete Ordenados Alfabéticamente =====");
                quindioExpress.mostrarMunicipiosConPaqueteOrden();
                break;
            case 3:
                //(2) Paquetes por municipio
                System.out.println("\n===== Paquetes Por Destino =====");
                quindioExpress.obtenerPaquetesPorDestino();
                break;
            case 4:
                //(3) Peso de paquetes pendientes por zona
                String municipio = JOptionPane.showInputDialog("Ingrese el Municipio al que le Desea Obtener el Peso Total de Paquetes: ");
                double peso = quindioExpress.obtenerPesoPorMunicipio(municipio);
                mostrarMensaje("El Peso total de " + municipio + " es: " + peso);
                break;
            case 5:
                //(4) Número de paquetes mayor a prioridad indicada
                int prioridad = Integer.parseInt(capturarDato("Ingrese la prioridad que desea verificar: "));
                int conteoPrioridad = quindioExpress.contarPrioridadMinima(prioridad);
                mostrarMensaje("El Número de Paquetes con Prioridad Mayor o Igual a " + prioridad + " es: " + conteoPrioridad);
                break;
            case 6:
                //(5) Paquete con mayor peso
                Paquete paquete = quindioExpress.obtenerPaqueteConMayorPeso();
                mostrarMensaje("El Paquete con Mayor Peso es: " + "\n" + paquete.toString());
                break;
            case 7:
                break;
            default:
                System.out.println("Opción no válida. Intente Nuevamente");
                break;
        }
    }

    public static void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public static String capturarDato(String mensaje) {
        return JOptionPane.showInputDialog(mensaje);
    }
}