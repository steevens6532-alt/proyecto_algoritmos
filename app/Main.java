package sistematecnicos.app;

import sistematecnicos.estructuras.ArregloTecnicos;
import sistematecnicos.modelo.Tecnico;

import java.util.Scanner;

public class Main {
    private static final int CAPACIDAD_TECNICOS = 100;

    private final Scanner entrada = new Scanner(System.in);
    private final ArregloTecnicos tecnicos = new ArregloTecnicos(CAPACIDAD_TECNICOS);

    public static void main(String[] args) {
        new Main().iniciar();
    }

    private void iniciar() {
        cargarDatosDePrueba(); // <--- AQUÍ SE CARGAN LOS DATOS INICIALES
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            ejecutarOpcion(opcion);
        } while (opcion != 6);
    }

    // MÉTODO AGREGADO PARA INYECTAR LOS TÉCNICOS DE PRUEBA
    private void cargarDatosDePrueba() {
        tecnicos.registrar(new Tecnico(1, "Carlos Mendoza", "Redes y Telecomunicaciones", "987654321", true));
        tecnicos.registrar(new Tecnico(2, "María Fernández", "Soporte Técnico y Hardware", "912345678", true));
        tecnicos.registrar(new Tecnico(3, "Jorge Gómez", "Mantenimiento de Servidores", "955443322", false));
    }

    private void mostrarMenu() {
        System.out.println("\n=========================================");
        System.out.println("   SISTEMA DE GESTIÓN DE TÉCNICOS");
        System.out.println("=========================================");
        System.out.println("1. Registrar técnico");
        System.out.println("2. Listar técnicos");
        System.out.println("3. Buscar técnico");
        System.out.println("4. Actualizar técnico");
        System.out.println("5. Eliminar técnico");
        System.out.println("6. Salir");
    }

    private void ejecutarOpcion(int opcion) {
        try {
            switch (opcion) {
                case 1 -> registrarTecnico();
                case 2 -> listarTecnicos();
                case 3 -> buscarTecnico();
                case 4 -> actualizarTecnico();
                case 5 -> eliminarTecnico();
                case 6 -> System.out.println("Programa finalizado.");
                default -> System.out.println("Opción no válida.");
            }
        } catch (IllegalArgumentException | IllegalStateException error) {
            System.out.println("No se pudo completar la operación: " + error.getMessage());
        }
    }

    private void registrarTecnico() {
        int id = leerEntero("ID: ");
        String nombre = leerTexto("Nombre: ");
        String especialidad = leerTexto("Especialidad: ");
        String telefono = leerTexto("Teléfono: ");

        tecnicos.registrar(new Tecnico(id, nombre, especialidad, telefono, true));
        System.out.println("Técnico registrado correctamente.");
    }

    private void listarTecnicos() {
        Tecnico[] registrados = tecnicos.copiarRegistrados();
        if (registrados.length == 0) {
            System.out.println("No hay técnicos registrados.");
            return;
        }

        System.out.println("\nTécnicos registrados:");
        for (Tecnico tecnico : registrados) {
            System.out.println(tecnico);
        }
    }

    /*--------Método de busqueda lineal--------------*/

    private void buscarTecnico() {
        int id = leerEntero("ID del técnico: ");
        Tecnico tecnico = tecnicos.buscarPorId(id);
        System.out.println(tecnico == null ? "Técnico no encontrado." : tecnico);
    }

    private void actualizarTecnico() {
        int id = leerEntero("ID del técnico: ");
        String nombre = leerTexto("Nuevo nombre: ");
        String especialidad = leerTexto("Nueva especialidad: ");
        String telefono = leerTexto("Nuevo teléfono: ");
        boolean activo = leerSiNo("¿Se encuentra activo? (s/n): ");

        tecnicos.actualizar(id, nombre, especialidad, telefono, activo);
        System.out.println("Técnico actualizado correctamente.");
    }

    private void eliminarTecnico() {
        int id = leerEntero("ID del técnico: ");
        Tecnico eliminado = tecnicos.eliminar(id);
        System.out.println(eliminado == null
                ? "Técnico no encontrado."
                : "Técnico eliminado correctamente: " + eliminado.getNombre());
    }

    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String valor = entrada.nextLine().trim();
            try {
                return Integer.parseInt(valor);
            } catch (NumberFormatException error) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return entrada.nextLine();
    }

    private boolean leerSiNo(String mensaje) {
        while (true) {
            String respuesta = leerTexto(mensaje).trim().toLowerCase();
            if (respuesta.equals("s")) {
                return true;
            }
            if (respuesta.equals("n")) {
                return false;
            }
            System.out.println("Responda con s o n.");
        }
    }
}