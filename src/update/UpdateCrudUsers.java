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
 *
 * @author gsenorans
 */
public class UpdateCrudUsers {
    
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
