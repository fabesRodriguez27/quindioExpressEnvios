package quindioexpress.app;
import quindioexpress.modelo.Paquete;
import quindioexpress.servicios.QuindioExpress;

import javax.swing.*;

public class Main {
    public static QuindioExpress quindioExpress = new QuindioExpress();

    public static void main(String[] args) {
        /*
        DatosPrueba.probarBusqueda(quindioExpress, 500000);
         */
        DatosIniciales.cargar(quindioExpress);
        menuGestionarQuindioExpress();
    }

    public static void menuGestionarQuindioExpress() {
        int opcion;
        do {
            String mensaje = "====== INGRESE LA OPCIÓN CORRESPONDIENTE ======\n"
                    + "1. Gestionar Paquetes\n"
                    + "2. Gestionar Repartidores\n"
                    + "3. Reportes QuindíoExpress\n"
                    + "4. Salir del Programa\n";
            opcion = Integer.parseInt(JOptionPane.showInputDialog(mensaje));
            validarMenuGestionarQuindioExpress(opcion);
        } while(opcion!=4);
    }

    public static void validarMenuGestionarQuindioExpress(int opcion){
       switch (opcion){
           case 1:
               menuGestionarPaquetes();
               break;
           /*
           case 2:
               menuGestionarRepartidores();
               break;

           case 3:
               menuReportesQuindioExpress();
               break;
            */

           case 4:
               JOptionPane.showMessageDialog(null, ".....Saliendo del Programa....");
               break;

           default:
               System.out.println("Opción no válida. Intente Nuevamente");
               break;
       }
    }

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
        } while(opcion!=6);
    }

    public static void validarMenuGestionarPaquetes(int opcion){
        switch (opcion){
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

    public static void agregarPaquete(){
        String destino = JOptionPane.showInputDialog("Ingrese el Destino del Paquete: ");
        double peso = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el Peso del Paquete: "));
        int prioridad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la Prioridad del Paquete: "));
        int tiempoEstimado = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el Tiempo Estimado del Paquete: "));
        Paquete paquete = new Paquete(destino, peso, prioridad, tiempoEstimado);
        if(quindioExpress.agregarPaquete(paquete)){
            JOptionPane.showMessageDialog(null, "Paquete Registrado con Éxito");
        }else{
            JOptionPane.showMessageDialog(null, "No se Pudo Registrar el Paquete");
        }
    }

    public static void eliminarPaquete(){
        String codigo = JOptionPane.showInputDialog("Ingrese el Código del Paquete a Eliminar: ");
        if(quindioExpress.eliminarPaquete(codigo)){
            JOptionPane.showMessageDialog(null, "Paquete Eliminado con Éxito");
        }else{
            JOptionPane.showMessageDialog(null, "No se Pudo Eliminar el Paquete. El Código No Existe");
        }
    }

    public static void buscarPaquetePorCodigo() {
        String codigo = JOptionPane.showInputDialog("Ingrese el Código del Paquete a Buscar: ");
        Paquete paquete = quindioExpress.buscarPaquete(codigo);
        if(paquete!= null){
            System.out.println("El Paquete es: " + paquete.toString());
        }else{
            System.out.println("No se Pudo Encontrar el Paquete");
        }
    }

    public static void menuMostrarPaquetes(){
        int opcion;
        do {
            String mensaje = "====== INGRESE LA OPCIÓN CORRESPONDIENTE ======\n"
                    + "1. Mostrar Paquetes Por Orden Alfabético de Código\n"
                    + "2. Mostrar Paquetes Por Prioridad de Mayor a Menor\n"
                    + "3. Mostrar Paquetes Por Peso de Mayor a Menor\n"
                    + "4. Mostrar Paquetes Por Tiempo Estimado de Menor a Mayor\n"
                    + "5. Regresar";
            opcion = Integer.parseInt(JOptionPane.showInputDialog(mensaje));
            validarMenuMostrarPaquetes(opcion);
        } while(opcion!=5);
    }

    public static void validarMenuMostrarPaquetes(int opcion){
        switch (opcion){
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
                break;
            default:
                System.out.println("Opción no válida. Intente Nuevamente");
                break;
        }
    }

    public static void mostrarPaquetesOrdenAlfabetico(){
        System.out.println("===== Paquetes Ordenados Alfabéticamente ====\n");
        quindioExpress.ordenarPaquetesAlfabeticamente();
    }

    public static void mostrarPaquetePorPrioridad(){
        System.out.println("===== Paquetes Ordenados Por Prioridad ====\n");
        quindioExpress.mostrarPaquetesPrioridad();
    }

    public static void mostrarPaquetesPorPeso(){
        System.out.println("===== Paquetes Ordenados de Mayor a Menor Peso ====\n");
        quindioExpress.mostrarPaquetesPorPeso();
    }

    public static void mostrarPaquetesPorTiempoEstimado(){
        System.out.println("===== Paquetes Ordenados de Menor a Mayor Tiempo Estimado ====\n");
        quindioExpress.mostrarPaquetesPorTiempoEstimado();
    }

    public static void menuProcesarPaquetes(){
        int opcion;
        do {
            String mensaje = "====== INGRESE LA OPCIÓN CORRESPONDIENTE ======\n"
                    + "1. Procesar Paquete Por Orden de Registro\n"
                    + "2. Procesar Paquete Por Prioridad\n"
                    + "3. Regresar";
            opcion = Integer.parseInt(JOptionPane.showInputDialog(mensaje));
            validarMenuProcesarPaquetes(opcion);
        } while(opcion!=3);
    }

    public static void validarMenuProcesarPaquetes(int opcion){
        switch (opcion){
            case 1:
                procesarPaqueteOrdenRegistro();
                break;
            case 2:
                procesarPaquetePorPrioridad();
                break;
            case 3:
                break;
            default:
                System.out.println("Opción no válida. Intente Nuevamente");
                break;
        }
    }

    private static void procesarPaqueteOrdenRegistro() {
        quindioExpress.procesarPaqueteOrdenRegistro();
    }

    private static void procesarPaquetePorPrioridad() {
        quindioExpress.procesarPaquetePorPrioridad();
    }


}