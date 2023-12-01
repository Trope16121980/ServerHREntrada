package update;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Jornada;
import peticiones.*;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class UpdateCrudJornada {

    /**
     * Este metodo envia los datos al metodo UpdateJornada.updateJornada para
     * ser modificado y después enviar el objeto al cliente e imprir en el text
     * area del servidor
     *
     * @param crud en este caso es el 2 de update
     * @param nombreTabla en este caso es el 2, ya que se refiere a empresa
     * @param dni el nombre de la columna de la tabla nombretabla
     * @param datoDni el dni del empleado que tiene la jornada iniciada
     * @param palabraAbuscar al array que contiene los datos
     * @param palabra variable necesaria para las modificaciones
     * @param outObjeto el objeto que contiene el array
     * @param client el socket del cliente al que se le envían los datos
     * @throws IOException controla los errores
     */
    public static void handleSearchRequestDni(String crud, String nombreTabla, String dni, String datoDni, String palabraAbuscar, String palabra, ObjectOutputStream outObjeto, Socket client) throws IOException {

        if (crud.equals("2")) {
            if (nombreTabla.equals("3")) {
                try {
                    List<Jornada> updateJornada = new ArrayList<Jornada>();
                    updateJornada = UpdateJornada.updateJornadaDni(crud, nombreTabla, dni, datoDni, palabra, palabra, outObjeto, client);

                    if (!updateJornada.isEmpty()) {
                        System.out.println("\nJornada modificada correctamente:");
                        Jornada jornada = updateJornada.get(0);
                        System.out.println("\nDni: " + datoDni);
                        System.out.println("Nombre: " + jornada.getNom());
                        System.out.println("Apellido: " + jornada.getApellido());
                        System.out.println("Hora entrada: " + jornada.getHoraentrada());
                        System.out.println("Hora salida: " + jornada.getHorasalida());
                        System.out.println("Total: " + jornada.getTotal());
                        System.out.println("Fecha: " + jornada.getFecha());
                        System.out.println("Codigo tarjeta: " + jornada.getCodicard());
                        System.out.println("____________________________________________________________________");

                        outObjeto = new ObjectOutputStream(client.getOutputStream());
                        outObjeto.writeObject(updateJornada);
                        outObjeto.flush();
                    } else {
                        System.out.println("____________________________________________________________________");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(UpdateCrudJornada.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     *
     * @param crud en este caso es el 2 de update
     * @param nombreTabla en este caso es el 2, ya que se refiere a empresa
     * @param codicard el nombre de la columna de la tabla nombretabla
     * @param datoCodicard el codigo de tarjeta del empleado que tiene la
     * jornada iniciada
     * @param palabraAbuscar al array que contiene los datos
     * @param palabra variable necesaria para las modificaciones
     * @param outObjeto el objeto que contiene el array
     * @param client el socket del cliente al que se le envían los datos
     * @throws IOException controla los errores
     */
    public static void handleSearchRequestCodicard(String crud, String nombreTabla, String codicard, String datoCodicard, String palabraAbuscar, String palabra, ObjectOutputStream outObjeto, Socket client) throws IOException {

        if (crud.equals("2")) {
            if (nombreTabla.equals("3")) {
                try {
                    List<Jornada> updateJornada = new ArrayList<Jornada>();
                    updateJornada = UpdateJornada.updateJornadaCodicard(crud, nombreTabla, codicard, datoCodicard, palabra, palabra, outObjeto, client);

                    if (!updateJornada.isEmpty()) {
                        System.out.println("\nJornada modificada correctamente:");
                        Jornada jornada = updateJornada.get(0);
                        System.out.println("\nDni: " + jornada.getDni());
                        System.out.println("Nombre: " + jornada.getNom());
                        System.out.println("Apellido: " + jornada.getApellido());
                        System.out.println("Hora entrada: " + jornada.getHoraentrada());
                        System.out.println("Hora salida: " + jornada.getHorasalida());
                        System.out.println("Total: " + jornada.getTotal());
                        System.out.println("Fecha: " + jornada.getFecha());
                        System.out.println("Codigo tarjeta: " + datoCodicard);
                        System.out.println("____________________________________________________________________");

                        outObjeto = new ObjectOutputStream(client.getOutputStream());
                        outObjeto.writeObject(updateJornada);
                        outObjeto.flush();
                    } else {
                        System.out.println("____________________________________________________________________");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(UpdateCrudJornada.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
