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
 *
 * @author gsenorans
 */
public class UpdateCrudJornada {

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
