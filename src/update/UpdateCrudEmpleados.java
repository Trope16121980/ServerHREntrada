package update;

/**
 *
 * @author gsenorans
 */
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import modelo.*;
import peticiones.*;

public class UpdateCrudEmpleados {

    public static void handleSearchRequest(String crud, String nombreTabla,
            String dniNuevo, String datoDniNuevo,
            String nomNuevo, String datoNomNuevo,
            String apellidoNuevo, String datoApellidoNuevo,
            String nomempresaNuevo, String datoNomempresaNuevo,
            String departamentNuevo, String datoDepartamentNuevo,
            String codicardNuevo, int datoCodicardNuevo,
            String mailNuevo, String datoMailNuevo,
            String telephonNuevo, int datoTelephonNuevo,
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
                    System.out.println("Empleado modificado correctamente:");
                    System.out.println("____________________________________________________________________");
                    System.out.println("Dni: " + datoDniNuevo);
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
