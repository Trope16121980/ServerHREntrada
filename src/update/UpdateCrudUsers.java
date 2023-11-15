/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package update;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import modelo.Users;
import peticiones.UpdateUsers;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class UpdateCrudUsers {

    /**
     * Este metodo envia los datos al metodo UpdateUsers.updateUsers para ser
     * modificado y después enviar el objeto al cliente e imprir en el text area
     * del servidor
     *
     * @param crud en este caso es el 2 de update
     * @param nombreTabla en este caso es el 1, ya que se refiere a usuarios
     * @param loginNuevo el nombre de la columna de la tabla nombretabla+Nuevo
     * @param datoLoginNuevo el login nuevo del usuario
     * @param passNuevo el nombre de la columna de la tabla nombretabla+Nuevo
     * @param datoPassNuevo el password nuevo del usuario
     * @param numtipeNuevo el nombre de la columna de la tabla nombretabla+Nuevo
     * @param datoNumtipeNuevo el tipo de usuario 
     * @param dniNuevo el nombre de la columna de la tabla nombretabla+Nuevo
     * @param datoDniNuevo el dni el empleado
     * @param dni el nombre de la columna original de la tabla
     * @param datoDni el dni del usuario a modificar
     * @param palabraAbuscar al array que contiene los datos
     * @param palabra variable necesaria para las modificaciones
     * @param outObjeto el objeto que contiene el array
     * @param client el socket del cliente al que se le envían los datos
     * @throws IOException controla los errores
     */
    public static void handleSearchRequest(String crud, String nombreTabla,
            String loginNuevo, String datoLoginNuevo,
            String passNuevo, String datoPassNuevo,
            String numtipeNuevo, int datoNumtipeNuevo,
            String dniNuevo, String datoDniNuevo,
            String dni, String datoDni,
            String palabraAbuscar, String palabra, ObjectOutputStream outObjeto, Socket client) throws IOException {

        if (crud.equals("2")) {
            if (nombreTabla.equals("1")) {
                List<Users> updateUsers = new ArrayList<Users>();
                updateUsers = UpdateUsers.updateUsers(crud, nombreTabla,
                        loginNuevo, datoLoginNuevo,
                        passNuevo, datoPassNuevo,
                        numtipeNuevo, datoNumtipeNuevo,
                        dniNuevo, datoDniNuevo,
                        dni, datoDni,
                        palabraAbuscar, palabra, outObjeto, client);

                if (!updateUsers.isEmpty()) {
                    System.out.println("Empleado modificado correctamente:");
                    System.out.println("____________________________________________________________________");

                    System.out.println("Login: " + datoLoginNuevo);
                    System.out.println("Pass: " + datoPassNuevo);
                    System.out.println("Numtipe: " + datoNumtipeNuevo);
                    System.out.println("Dni: " + datoDniNuevo);
                    System.out.println("____________________________________________________________________");

                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(updateUsers);
                    outObjeto.flush();
                } else {
                    System.out.println("____________________________________________________________________");
                }
            }
        }
    }

}
