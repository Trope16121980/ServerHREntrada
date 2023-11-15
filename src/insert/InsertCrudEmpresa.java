package insert;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import modelo.Empresa;
import peticiones.InsertEmpresa;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class InsertCrudEmpresa {

    /**
     * Imprime en el textarea del servidor lo datos a ingresar y envía el objeto
     * al cliente después de ser envíado al método
     * InsertEmpresa.insertEmpresa para ser insertados en la BBDD HREntrada
     *
     * @param crud en este caso es 1 para el insert
     * @param nombreTabla el número de la tabla en este caso 2, ya que se
     * refiere a la empresa
     * @param nom el nombre de la columna de la tabla
     * @param datoNom el nombre de la empresa que se quiere insertar
     * @param address el nombre de la columna de la tabla
     * @param datoAddress la dirección de la empresa que se quiere insertar
     * @param telephon el nombre de la columna de la tabla
     * @param datoTelephon el número de teléfono de la empresa que se quiere insertar
     * @param palabraAbuscar al array que contiene los datos
     * @param outObjeto el objeto que contiene el array
     * @param client el socket del cliente al que se le envían los datos
     * @throws IOException controla los errores
     */
    public static void handleInsertRequest(String crud, String nombreTabla, String nom, String datoNom, String address,
            String datoAddress, String telephon, String datoTelephon, String palabraAbuscar,
            ObjectOutputStream outObjeto, Socket client) throws IOException {

        if (crud.equals("1")) {
            if (nombreTabla.equals("2")) {
                List<Empresa> insertEmpresa = new ArrayList<Empresa>();
                insertEmpresa = InsertEmpresa.insertEmpresa(crud, nombreTabla, nom, datoNom, address, datoAddress,
                        telephon, datoTelephon, palabraAbuscar, outObjeto, client);

                if (!insertEmpresa.isEmpty()) {
                    System.out.println("Empresa creada correctamente, sus datos son:\n");
                    System.out.println("____________________________________________________________________");
                    System.out.println("Nombre: " + datoNom + "\n" + "Address: " + datoAddress + "\n" + "Telefono: "
                            + Integer.parseInt(datoTelephon) + "\n");
                    System.out.println("____________________________________________________________________");
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(insertEmpresa);
                    outObjeto.flush();
                } else {
                    System.out.println("____________________________________________________________________");
                }
            }
        }
    }
}
