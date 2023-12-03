package delete;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import modelo.Empresa;
import peticiones.DeleteEmpresa;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class DeleteCrudEmpresa {

    /**
     * Este metodo envia los datos al metodo DeleteEmpresa.deleteEmpresa para
     * ser liminada y después enviar el objeto al cliente e imprir en el text
     * area del servidor
     *
     * @param crud en este caso es el 3 de delete
     * @param nombreTabla en este caso es el 2, ya que se refiere a los empresa
     * @param nom el nombre de la columna original de la tabla
     * @param datoNom del nombre de la empresa
     * @param palabraAbuscar al array que contiene los datos
     * @param outObjeto el objeto que contiene el array
     * @param client el socket del cliente al que se le envían los datos
     * @throws IOException controla los errores
     */
    public static void handleDeleteEmpresa(String crud, String nombreTabla, String nom, String datoNom, String palabraAbuscar,
            ObjectOutputStream outObjeto, Socket client) throws IOException {

        if (crud.equals("3")) {
            if (nombreTabla.equals("2")) {
                List<Empresa> deleteEmpresa = new ArrayList<Empresa>();
                deleteEmpresa = DeleteEmpresa.deleteEmpresa(crud, nombreTabla, nom, datoNom, palabraAbuscar, datoNom, outObjeto, client);

                if (!deleteEmpresa.isEmpty()) {
                    System.out.println("____________________________________________________________________");
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(deleteEmpresa);
                    outObjeto.flush();
                } else {
                    System.out.println("____________________________________________________________________");
                }
            }
        }
    }
}
