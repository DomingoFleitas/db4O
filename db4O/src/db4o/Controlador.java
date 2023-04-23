package db4o;

import javax.swing.table.DefaultTableModel;

public class Controlador extends Conexion {

    public Controlador() {

    }

    public boolean insertarContacto(String nombre, String apellido1, String apellido2, String telefono, String email) {
        Contactos contacto = new Contactos(nombre, apellido1, apellido2, telefono, email);
        return Insertar(contacto);
    }

    public boolean actualizarContacto(String nombre, String apellido1, String apellido2, String telefono, String email) {
        Contactos contacto = new Contactos(nombre, apellido1, apellido2, telefono, email);
        return Actualizar(contacto);
    }

    public DefaultTableModel contactos() {
        String[] modelo = {"Nombre", "Apellido 1º", "Apellido 2º", "Telefono", "Email"};
        DefaultTableModel dtm = new DefaultTableModel(null, modelo);
        Contactos contactos = null;
        Contactos[] c = Consultar(contactos);
        if (c != null) {
            for (Contactos conta : c) {
                Object[] con = new Object[5];
                con[0] = conta.getNombre();
                con[1] = conta.getApellido1();
                con[2] = conta.getApellido2();
                con[3] = conta.getTelefono();
                con[4] = conta.getEmail();
                dtm.addRow(con);
            }
        }
        return dtm;
    }

    public boolean eliminarContactos(String telefono) {
        if (telefono != null) {
            Contactos contacto = new Contactos(null, null, null, telefono, null);
            return Eliminar(contacto);
        } else {
            return false;
        }
    }
}
