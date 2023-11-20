package update;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import modelo.*;
import peticiones.*;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class UpdateCrudEmpleados {

    /**
     * Este metodo envia los datos al metodo UpdateEmpleados.updateEmpleados
     * para ser modificado y después enviar el objeto al cliente e imprir en el
     * text area del servidor
     *
     * @param crud en este caso es el 2 de update
     * @param nombreTabla en este caso es el 2, ya que se refiere a empresa
     * @param dniNuevo el nombre de la columna de la tabla nombretabla+Nuevo
     * @param datoDniNuevo
     * @param nomNuevo el nombre de la columna de la tabla nombretabla+Nuevo
     * @param datoNomNuevo el nombre nuevo del empleado
     * @param apellidoNuevo el nombre de la columna de la tabla
     * nombretabla+Nuevo
     * @param datoApellidoNuevo el apellido nuevo del empleado
     * @param nomempresaNuevo el nombre de la columna de la tabla
     * nombretabla+Nuevo
     * @param datoNomempresaNuevo el nombre nuevo de la empresa
     * @param departamentNuevo el nombre de la columna de la tabla
     * nombretabla+Nuevo
     * @param datoDepartamentNuevo el departamento nuevo al que pertenece
     * @param codicardNuevo el nombre de la columna de la tabla
     * nombretabla+Nuevo
     * @param datoCodicardNuevo el nuevo código de tarjeta
     * @param mailNuevo el nombre de la columna de la tabla nombretabla+Nuevo
     * @param datoMailNuevo el nuevo mail de empleado
     * @param telephonNuevo el nombre de la columna de la tabla
     * nombretabla+Nuevo
     * @param datoTelephonNuevo el nuevo telefono de empleado
     * @param dni el nombre de la columna original de la tabla
     * @param datoDni del dni el empleado
     * @param palabraAbuscar al array que contiene los datos
     * @param palabra variable necesaria para las modificaciones
     * @param outObjeto el objeto que contiene el array
     * @param client el socket del cliente al que se le envían los datos
     * @throws IOException controla los errores
     */
    public static void handleSearchRequest(String crud, String nombreTabla,
            String dniNuevo, String datoDniNuevo,
            String nomNuevo, String datoNomNuevo,
            String apellidoNuevo, String datoApellidoNuevo,
            String nomempresaNuevo, String datoNomempresaNuevo,
            String departamentNuevo, String datoDepartamentNuevo,
            String codicardNuevo, String datoCodicardNuevo,
            String mailNuevo, String datoMailNuevo,
            String telephonNuevo, String datoTelephonNuevo,
            String dni, String datoDni,
            String palabraAbuscar, String palabra, ObjectOutputStream outObjeto, Socket client) throws IOException {

        if (crud.equals("2")) {
            if (nombreTabla.equals("0")) {
                List<Empleados> updateEmpleados = new ArrayList<Empleados>();
                updateEmpleados = UpdateEmpleados.updateEmpleados(crud, nombreTabla,
                        dniNuevo, datoDniNuevo, nomNuevo,
                        datoNomNuevo, apellidoNuevo, datoApellidoNuevo,
                        nomempresaNuevo, datoNomempresaNuevo, departamentNuevo,
                        datoDepartamentNuevo, codicardNuevo, datoCodicardNuevo,
                        mailNuevo, datoMailNuevo,
                        telephonNuevo, datoTelephonNuevo,
                        dni, datoDni,
                        palabraAbuscar, palabra, outObjeto, client);

                if (!updateEmpleados.isEmpty()) {
                    System.out.println("\nEmpleado modificado correctamente:");
                    System.out.println("\nDni: " + datoDniNuevo);
                    System.out.println("Nombre: " + datoNomNuevo);
                    System.out.println("Apellido: " + datoApellidoNuevo);
                    System.out.println("Nombre empresa: " + datoNomempresaNuevo);
                    System.out.println("Departament: " + datoDepartamentNuevo);
                    System.out.println("Codigo tarjeta: " + datoCodicardNuevo);
                    System.out.println("Mail: " + datoMailNuevo);
                    System.out.println("Telefono: " + datoTelephonNuevo);
                    System.out.println("____________________________________________________________________");

                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(updateEmpleados);
                    outObjeto.flush();
                } else {
                    System.out.println("____________________________________________________________________");
                }
            }
        }
    }

}
