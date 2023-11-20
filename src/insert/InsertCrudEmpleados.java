package insert;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import modelo.Empleados;
import peticiones.InsertEmpleados;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class InsertCrudEmpleados {

    /**
     * Imprime en el textarea del servidor lo datos a ingresar y envía el objeto al cliente
     * después de ser envíado al método InsertEmpleados.insertEmpleados para ser insertados en
     * la BBDD HREntrada
     * 
     * @param crud  en este caso es 1 para el insert
     * @param nombreTabla el número de la tabla en este caso 0, ya que se refiere al empleado
     * @param dni el nombre de la columna de la tabla
     * @param datoDni el dni del empleado a insertar
     * @param nom el nombre de la columna de la tabla
     * @param datoNom el nombre del empleado a insertar
     * @param apellido el nombre de la columna de la tabla
     * @param datoApellido el apellido del empleado a insertar
     * @param nomempresa el nombre de la columna de la tabla
     * @param datoNomempresa el nombre de la empresa con la que se relaciona el empleado a insertar
     * @param departament el nombre de la columna de la tabla
     * @param datoDepartament el departamento del empleado a insertar
     * @param codicard el nombre de la columna de la tabla
     * @param datoCodicard el codigo de tarjeta del empleado a insertar
     * @param mail el nombre de la columna de la tabla
     * @param datoMail el mail del empleado a insertar
     * @param telephon el nombre de la columna de la tabla
     * @param datoTelephon el número de teléfono del empleado a insertar
     * @param palabraAbuscar al array que contiene los datos
     * @param outObjeto el objeto que contiene el array
     * @param client el socket del cliente al que se le envían los datos
     * @throws IOException controla los errores
     */
    public static void handleInsertRequest(String crud, String nombreTabla, String dni, String datoDni, String nom,
            String datoNom, String apellido, String datoApellido, String nomempresa, String datoNomempresa,
            String departament, String datoDepartament, String codicard, String datoCodicard, String mail,
            String datoMail, String telephon, String datoTelephon, String palabraAbuscar, ObjectOutputStream outObjeto,
            Socket client) throws IOException {

        if (crud.equals("1")) {
            if (nombreTabla.equals("0")) {
                List<Empleados> insertEmpleados = new ArrayList<Empleados>();
                insertEmpleados = InsertEmpleados.insertEmpleados(crud, nombreTabla, dni, datoDni, nom, datoNom,
                        apellido, datoApellido, nomempresa, datoNomempresa, departament, datoDepartament, codicard,
                        datoCodicard, mail, datoMail, telephon, datoTelephon, palabraAbuscar, outObjeto, client);

                if (!insertEmpleados.isEmpty()) {
                    System.out.println("\nEmpleado creado correctamente, sus datos son:\n");
                    System.out.println("\nDni: " + datoDni + "\n" + "Nombre: " + datoNom + "\n" + "Apellido: "
                            + datoApellido + "\n" + "Nombre empresa: " + datoNomempresa + "\n" + "Departamento: "
                            + datoDepartament + "\n" + "Codigo tarjeta: " + datoCodicard + "\n"
                            + "Mail: " + datoMail + "\n" + "Telefono: " + datoTelephon + "\n");
                    System.out.println("____________________________________________________________________");
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(insertEmpleados);
                    outObjeto.flush();
                } else {
                    System.out.println("____________________________________________________________________");
                }
            }
        }
    }
}
