package sistematecnicos.estructuras;

import sistematecnicos.modelo.Tecnico;

import java.util.Arrays;

public class ArregloTecnicos {
    private final Tecnico[] tecnicos;
    private int cantidad;

    public ArregloTecnicos(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que cero.");
        }
        tecnicos = new Tecnico[capacidad];
    }

    public void registrar(Tecnico tecnico) {
        if (tecnico == null) {
            throw new IllegalArgumentException("El técnico no puede ser nulo.");
        }
        if (estaLleno()) {
            throw new IllegalStateException("No hay espacio para registrar más técnicos.");
        }
        if (buscarPorId(tecnico.getIdTecnico()) != null) {
            throw new IllegalArgumentException("Ya existe un técnico con ese identificador.");
        }

        tecnicos[cantidad] = tecnico;
        cantidad++;
    }


    /*-----------llamada a la funcion de busqueda por id usando busqueda lineal-------------*/
    public Tecnico buscarPorId(int idTecnico) {
        int posicion = buscarPosicionPorId(idTecnico);
        return posicion == -1 ? null : tecnicos[posicion];
    }

    public void actualizar(
            int idTecnico,
            String nombre,
            String especialidad,
            String telefono,
            boolean activo
    ) {
        Tecnico tecnico = buscarPorId(idTecnico);
        if (tecnico == null) {
            throw new IllegalArgumentException("No se encontró el técnico indicado.");
        }

        tecnico.setNombre(nombre);
        tecnico.setEspecialidad(especialidad);
        tecnico.setTelefono(telefono);
        tecnico.setActivo(activo);
    }
    /* ------------- Metodo de busqueda lineal para eliminacion -------------*/

    public Tecnico eliminar(int idTecnico) {
        int posicion = buscarPosicionPorId(idTecnico);
        if (posicion == -1) {
            return null;
        }

        Tecnico eliminado = tecnicos[posicion];
        for (int i = posicion; i < cantidad - 1; i++) {
            tecnicos[i] = tecnicos[i + 1];
        }
        tecnicos[cantidad - 1] = null;
        cantidad--;
        return eliminado;
    }

    public Tecnico[] copiarRegistrados() {
        return Arrays.copyOf(tecnicos, cantidad);
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getCapacidad() {
        return tecnicos.length;
    }

    public boolean estaLleno() {
        return cantidad == tecnicos.length;
    }

    public boolean estaVacio() {
        return cantidad == 0;
    }

    /* ------------- Metodo de busqueda lineal-------------*/
    private int buscarPosicionPorId(int idTecnico) {
        for (int i = 0; i < cantidad; i++) {
            if (tecnicos[i].getIdTecnico() == idTecnico) {
                return i;
            }
        }
        return -1;
    }
}
