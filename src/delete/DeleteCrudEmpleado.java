package delete;

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
public class DeleteCrudEmpleado {

    public static void handleDeleteEmpleado(String crud, String nombreTabla, String dni, String datoDni, String palabraAbuscar,
            ObjectOutputStream outObjeto, Socket client) throws IOException {

        if (crud.equals("3")) {
            if (nombreTabla.equals("0")) {
                List<Empleados> deleteEmpleado = new ArrayList<Empleados>();
                deleteEmpleado = DeleteEmpleado.deleteEmpleado(crud, nombreTabla, dni, datoDni, palabraAbuscar, datoDni, outObjeto, client);

                if (!deleteEmpleado.isEmpty()) {
                    System.out.println("\nEmpresa eliminada correctamente, sus datos son:\n");
                    Empleados empleados = deleteEmpleado.get(0);
                    System.out.println("\nDni: " + datoDni + "\n"
                            + "Nombre: " + empleados.getNom() + "\n"
                            + "Apellido: " + empleados.getApellido() + "\n"
                            + "Nombre empresa: " + empleados.getNomempresa() + "\n"
                            + "Departamento: " + empleados.getDepartament() + "\n"
                            + "Codigo tarjeta: " + empleados.getCodicard() + "\n"
                            + "Mail: " + empleados.getMail() + "\n"
                            + "Telefono: " + empleados.getTelephon());
                    System.out.println("____________________________________________________________________");
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(deleteEmpleado);
                    outObjeto.flush();
                } else {
                    System.out.println("____________________________________________________________________");
                }
            }
        }
    }
}
