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

    public static void handleDeleteEmpresa(String crud, String nombreTabla, String nom, String datoNom, String palabraAbuscar,
            ObjectOutputStream outObjeto, Socket client) throws IOException {

        if (crud.equals("3")) {
            if (nombreTabla.equals("2")) {
                List<Empresa> deleteEmpresa = new ArrayList<Empresa>();
                deleteEmpresa = DeleteEmpresa.deleteEmpresa(crud, nombreTabla, nom, datoNom, palabraAbuscar, datoNom, outObjeto, client);

                if (!deleteEmpresa.isEmpty()) {
                    System.out.println("\nEmpresa eliminada correctamente, sus datos son:\n");
                    Empresa empresa = deleteEmpresa.get(0);
                    System.out.println("\nNombre: " + datoNom + "\n" + "Address: " + empresa.getAddress() + "\n" + "Telefono: "
                            + empresa.getTelephon()+ "\n");
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
