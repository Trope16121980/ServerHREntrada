package insert;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import modelo.Users;
import peticiones.InsertUsers;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class InsertCrudUsers {

    /**
     * Imprime en el textarea del servidor lo datos a ingresar y envía el objeto
     * al cliente después de ser envíado al método InsertUsers.insertUser para
     * ser insertados en la BBDD HREntrada
     *
     * @param crud en este caso es 1 para el insert
     * @param nombreTabla el número de la tabla en este caso 1, ya que se
     * refiere al usuario
     * @param login el nombre de la columna de la tabla
     * @param datoLogin el login del usuario que quiere insertar
     * @param pass el nombre de la columna de la tabla
     * @param datoPass el password del usuario que quiere insertar
     * @param numTipe el nombre de la columna de la tabla
     * @param datoNumTipe el tipo de usuario que quiere insertar
     * @param dni el nombre de la columna de la tabla
     * @param datoDni el dni del empleado que quiere insertar para que u pueda
     * iniciar sesión como admin o user generico
     * @param palabraAbuscar al array que contiene los datos
     * @param outObjeto el objeto que contiene el array
     * @param client el socket del cliente al que se le envían los datos
     * @throws IOException controla los errores
     */
    public static void handleInsertRequest(String crud, String nombreTabla, String login, String datoLogin, String pass,
            String datoPass, String numTipe, String datoNumTipe, String dni, String datoDni, String palabraAbuscar,
            ObjectOutputStream outObjeto, Socket client) throws IOException {

        if (crud.equals("1")) {
            if (nombreTabla.equals("1")) {
                List<Users> insertUser = new ArrayList<Users>();
                insertUser = InsertUsers.insertUser(crud, nombreTabla, login, datoLogin, pass, datoPass, numTipe,
                        datoNumTipe, dni, datoDni, palabraAbuscar, outObjeto, client);
                if (!insertUser.isEmpty()) {
                    System.out.println(("Usuario creado correctamente, sus datos son: \n"));
                    System.out.println("Login: " + datoLogin + "\n" + "Pass: " + datoPass + "\n" + "Num Tipe: "
                            + datoNumTipe + "\n" + "Dni: " + datoDni);
                    System.out.println("____________________________________________________________________");
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(insertUser);
                    outObjeto.flush();
                } else {
                    System.out.println("____________________________________________________________________");
                }
            }
        }
    }

}
