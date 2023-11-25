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

    public static void handleSearchRequest(String crud, String nombreTabla, String dni, String datoDni, String palabraAbuscar, String palabra, ObjectOutputStream outObjeto, Socket client) throws IOException {

        if (crud.equals("2")) {
            if (nombreTabla.equals("3")) {
                try {
                    List<Jornada> updateJornada = new ArrayList<Jornada>();
                    updateJornada = UpdateJornada.updateJornada(crud, nombreTabla, dni, datoDni, palabra, palabra, outObjeto, client);

                    if (!updateJornada.isEmpty()) {
                        System.out.println("\nJornada modificado correctamente:");
                        Jornada jornada = updateJornada.get(0);
                        System.out.println("\nDni: " + datoDni);
                        System.out.println("Nombre: " + jornada.getNom()); // Reemplaza getNombre con el nombre del getter correspondiente
                        System.out.println("Apellido: " + jornada.getApellido()); // Reemplaza getApellido con el nombre del getter correspondiente
                        System.out.println("Hora entrada: " + jornada.getHoraentrada()); // Reemplaza getHoraEntrada con el nombre del getter correspondiente
                        System.out.println("Hora salida: " + jornada.getHorasalida()); // Reemplaza getHoraSalida con el nombre del getter correspondiente
                        System.out.println("Total: " + jornada.getTotal()); // Reemplaza getTotal con el nombre del getter correspondiente
                        System.out.println("Fecha: " + jornada.getFecha()); // Reemplaza getFecha con el nombre del getter correspondiente
                        System.out.println("Codigo tarjeta: " + jornada.getCodicard()); // Reemplaza getCodigoTarjeta con el nombre del getter correspondiente
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
