
package print;

import java.util.List;
import modelo.Users;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class PrintUsers {

    /**
     /**
     * Imprime los datos en el textArea del servidor según el crud que utilice
     * el nombre de la tabla he imprime las listas completas
     *
     * @param Listausers lista de usuarios
     * @param columna en este caso es el 1 que pertenece a users
     * @return devuelve la lista de usuarios completa
     */
    public String obtenerDatosUsers(List<Users> Listausers, String columna) {
        StringBuilder datosUsers = new StringBuilder();
        for (Users users : Listausers) {
            datosUsers.append("\nLogin: ").append(users.getLogin()).append("\n")
                    .append("Pass: ").append(users.getPass()).append("\n")
                    .append("Tipo de usuario: ").append(users.getNumtipe()).append("\n")
                    .append("Dni: ").append(users.getDni()).append("\n");
        }
        datosUsers.append("____________________________________________________________________\n");
        return datosUsers.toString();
    }
}
