package db4o;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseReadOnlyException;

public class Conexion {

    private ObjectContainer objectContainer;

    private void open() {
        //CREAMOS LA CONEXION Y EL ARCHIVO QUE ALMACENARÁ LOS DATOS
        objectContainer = Db4oEmbedded.openFile("agenda.db4o");
    }

    public boolean Insertar(Contactos objeto) {
        try {
            //INSERTAMOS EL OBJETO RECIBIDO EN LA BASE DE DATOS
            open();
            objectContainer.store(objeto);
            objectContainer.close();
            return true;
        } catch (DatabaseClosedException | DatabaseReadOnlyException ex) {
            System.out.println("Error en la base de datos, err: " + ex);
            return false;
        }
    }

    public Contactos[] Consultar(Contactos objeto) {
        try {
            //CONSULTAMOS LOS OBJETOS ALMACENADOS EN LA BASE DE DATOS.
            Contactos[] contactos = null;
            open();
            ObjectSet resultados = objectContainer.queryByExample(objeto);
            int i = 0;
            if (resultados.hasNext()) {
                contactos = new Contactos[resultados.size()];
                while (resultados.hasNext()) {
                    contactos[i] = (Contactos) resultados.next();
                    i++;
                }
            }
            objectContainer.close();
            return contactos;
        } catch (DatabaseClosedException | DatabaseReadOnlyException ex) {
            System.out.println("Error en la base de datos, err: " + ex);
            return null;
        }
    }

    public boolean Eliminar(Contactos objeto) {
        try {
            //CONSULTAMOS LOS OBJETOS ALMACENADOS EN LA BASE DE DATOS Y SI EXISTE UNA COINCIDENCIA LA ELIMINAMOS            
            open();
            ObjectSet resultados = objectContainer.queryByExample(objeto);
            if (resultados.size() > 0) {
                Contactos contacto = (Contactos) resultados.next();
                objectContainer.delete(contacto);
                objectContainer.close();
                return true;
            } else {
                this.objectContainer.close();
                return false;
            }
        } catch (DatabaseClosedException | DatabaseReadOnlyException ex) {
            System.out.println("Error en la base de datos, err: " + ex);
            return false;
        }
    }

    public boolean Actualizar(Contactos objeto) {
        try {
            //BUSCAMOS SI EXISTE EL OBJETO, SI ES ASÍ LO ACTUALIZAMOS EN LA BASE DE DATOS
            open();
            ObjectSet resultados = objectContainer.queryByExample(new Contactos(null, null, null, objeto.getTelefono(), null));
            if (resultados.size() > 0) {
                Contactos resultado = (Contactos) resultados.next();
                resultado.setNombre(objeto.getNombre());
                resultado.setApellido1(objeto.getApellido1());
                resultado.setApellido2(objeto.getApellido2());
                resultado.setTelefono(objeto.getTelefono());
                resultado.setEmail(objeto.getEmail());
                objectContainer.store(resultado);
                objectContainer.close();
                return true;
            } else {
                objectContainer.close();
                return false;
            }
        } catch (DatabaseClosedException | DatabaseReadOnlyException e) {
            System.out.println("Error en la base de datos, err: " + e);
            return false;
        }
    }
}
