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
public class UpdateCrudEmpresa {

    /**
     * Este metodo envia los datos al metodo UpdateEmpresa.updateEmpresa para ser
     * modificado y después enviar el objeto al cliente e imprir en el text area
     * del servidor
     *
     * @param crud en este caso es el 2 de update
     * @param nombreTabla en este caso es el 2, ya que se refiere a empresa
     * @param nomNuevo el nombre de la columna de la tabla nombretabla+Nuevo
     * @param datoNomnuevo el nombre nuevo de la empresa
     * @param addressNuevo el nombre de la columna de la tabla nombretabla+Nuevo
     * @param datoAddressNuevo la dirección nueva de la empresa
     * @param telephonNuevo el nombre de la columna de la tabla nombretabla+Nuevo
     * @param datoTelephonNuevo el teléfono nuevo de la empresas
     * @param nom el nombre de la columna original de la tabla
     * @param datoNom el nombre de la empresa a modificar
     * @param palabraAbuscar al array que contiene los datos
     * @param palabra variable necesaria para las modificaciones
     * @param outObjeto el objeto que contiene el array
     * @param client el socket del cliente al que se le envían los datos
     * @throws IOException controla los errores
     */
    public static void handleSearchRequest(String crud, String nombreTabla, String nomNuevo,
            String datoNomnuevo, String addressNuevo, String datoAddressNuevo, String telephonNuevo,
            String datoTelephonNuevo, String nom, String datoNom, String palabraAbuscar,
            String palabra, ObjectOutputStream outObjeto, Socket client) throws IOException {

        if (crud.equals("2")) {
            if (nombreTabla.equals("2")) {
                List<Empresa> updateEmpresa = new ArrayList<Empresa>();
                updateEmpresa = UpdateEmpresa.updateEmpresa(crud, nombreTabla, nomNuevo, datoNomnuevo, addressNuevo, datoAddressNuevo,
                        telephonNuevo, datoTelephonNuevo, nom, datoNom, palabraAbuscar, palabra, outObjeto, client);
                if (!updateEmpresa.isEmpty()) {
                    for (Empresa empresa : updateEmpresa) {
                        System.out.println("\nNombre de empresa modificado correctamente:");
                        System.out.println("Nombre: " + datoNomnuevo);
                        System.out.println("Direccion: " + datoAddressNuevo);
                        System.out.println("Telefono: " + datoTelephonNuevo);
                        System.out.println("____________________________________________________________________");
                    }
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(updateEmpresa);
                    outObjeto.flush();
                } else {
                    System.out.println("____________________________________________________________________");
                }
            }
        }
    }
}
