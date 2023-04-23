package db4o;

public class Contactos {

    private String nombre;
    private String apellido1;
    private String apellido2;
    private String telefono;
    private String email;

    public Contactos() {
    }

    public Contactos(String nombre, String apellido1, String apellido2, String telefono, String email) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.telefono = telefono;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contacto: " + "Nombre: " + nombre + ", Apellido 1º: " + apellido1 + ", Apellido2º: " + apellido2 + ", Teléfono: " + telefono + ", Email: " + email;
    }

}
