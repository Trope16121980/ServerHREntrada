package insert;

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
public class InsertCrudJornada {

    /**
     * Imprime en el textarea del servidor lo datos a ingresar y envía el objeto
     * al cliente después de ser envíado al método InsertJornada.insertJornada
     * para ser insertados en la BBDD HREntrada
     *
     * @param crud en este caso es 1 para el insert
     * @param nombreTabla el número de la tabla en este caso 3, ya que se
     * refiere a la jornada
     * @param dni el nombre de la columna de la tabla
     * @param datoDni el dni del empleado que quiere iniciar jornada
     * @param palabraAbuscar al array que contiene los datos
     * @param outObjeto el objeto que contiene el array
     * @param client el socket del cliente al que se le envían los datos
     * @throws IOException controla los errores
     */
    public static void handleInsertRequest(String crud, String nombreTabla, String dni, String datoDni, String palabraAbuscar, ObjectOutputStream outObjeto,
            Socket client) throws IOException {

        if (crud.equals("1")) {
            if (nombreTabla.equals("3")) {
                List<Jornada> insertJornada = new ArrayList<Jornada>();
                insertJornada = InsertJornada.insertJornada(crud, nombreTabla, dni, datoDni, palabraAbuscar, outObjeto, client);
                if (!insertJornada.isEmpty()) {
                    for (Jornada jornada : insertJornada) {
                        System.out.println("\nJornada creada correctamente:");
                        System.out.println("\nDni: " + jornada.getDni());
                        System.out.println("Nombre: " + jornada.getNom());
                        System.out.println("Apellido: " + jornada.getApellido());
                        System.out.println("Hora entrada: " + jornada.getHoraentrada());
                        System.out.println("Hora salida: " + jornada.getHorasalida());
                        System.out.println("Total: " + jornada.getTotal());
                        System.out.println("Fecha: " + jornada.getFecha());
                        System.out.println("Codigo tarjeta: " + jornada.getCodicard());
                        System.out.println("____________________________________________________________________");
                    }
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(insertJornada);
                    outObjeto.flush();
                } else {
                    System.out.println("____________________________________________________________________");
                }
            }
        }
    }

    /**
     * Imprime en el textarea del servidor lo datos a ingresar y envía el objeto
     * al cliente después de ser envíado al método
     * InsertJornada.insertJornadaCodicard para ser insertados en la BBDD
     * HREntrada
     *
     * @param crud en este caso es 1 para el insert
     * @param nombreTabla el número de la tabla en este caso 3, ya que se
     * refiere a la jornada
     * @param codicard el nombre de la columna de la tabla
     * @param datoCodicard el codigo de tarjeta del empleado que quiere iniciar
     * jornada
     * @param palabraAbuscar al array que contiene los datos
     * @param outObjeto el objeto que contiene el array
     * @param client el socket del cliente al que se le envían los datos
     * @throws IOException controla los errores
     */
    public static void handleInsertCodicardRequest(String crud, String nombreTabla, String codicard, String datoCodicard, String palabraAbuscar, ObjectOutputStream outObjeto,
            Socket client) throws IOException {

        if (crud.equals("1")) {
            if (nombreTabla.equals("3")) {
                List<Jornada> insertJornadaCodicard = new ArrayList<Jornada>();
                insertJornadaCodicard = InsertJornada.insertJornadaCodicard(crud, nombreTabla, codicard, datoCodicard, palabraAbuscar, outObjeto, client);
                if (!insertJornadaCodicard.isEmpty()) {
                    for (Jornada jornada : insertJornadaCodicard) {
                        System.out.println("Jornada creada correctamente:");
                        System.out.println("____________________________________________________________________");
                        System.out.println("Dni: " + jornada.getDni());
                        System.out.println("Nombre: " + jornada.getNom());
                        System.out.println("Apellido: " + jornada.getApellido());
                        System.out.println("Hora entrada: " + jornada.getHoraentrada());
                        System.out.println("Hora salida: " + jornada.getHorasalida());
                        System.out.println("Total: " + jornada.getTotal());
                        System.out.println("Fecha: " + jornada.getFecha());
                        System.out.println("Codigo tarjeta: " + jornada.getCodicard());
                        System.out.println("____________________________________________________________________");
                    }
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(insertJornadaCodicard);
                    outObjeto.flush();
                } else {
                    System.out.println("____________________________________________________________________");
                }
            }
        }
    }
}
