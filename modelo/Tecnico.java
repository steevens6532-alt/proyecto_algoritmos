package sistematecnicos.modelo;

import java.util.Objects;

public class Tecnico {
    private final int idTecnico;
    private String nombre;
    private String especialidad;
    private String telefono;
    private boolean activo;

/*------------Metodo constructor tecnico-------------------------*/
    public Tecnico(int idTecnico, String nombre, String especialidad, String telefono, boolean activo) {
        if (idTecnico <= 0) {
            throw new IllegalArgumentException("El identificador debe ser positivo.");
        }
        this.idTecnico = idTecnico;
        setNombre(nombre);
        setEspecialidad(especialidad);
        setTelefono(telefono);
        this.activo = activo;
    }

    public int getIdTecnico() {
        return idTecnico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = validarTextoObligatorio(nombre, "nombre");
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = validarTextoObligatorio(especialidad, "especialidad");
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono == null ? "" : telefono.trim();
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    private static String validarTextoObligatorio(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("El campo " + campo + " es obligatorio.");
        }
        return valor.trim();
    }

    @Override
    public String toString() {
        return String.format(
                "[%d] %s | %s | %s | %s",
                idTecnico,
                nombre,
                especialidad,
                telefono.isBlank() ? "Sin teléfono" : telefono,
                activo ? "Activo" : "Inactivo"
        );
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }
        if (!(objeto instanceof Tecnico tecnico)) {
            return false;
        }
        return idTecnico == tecnico.idTecnico;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTecnico);
    }
}
