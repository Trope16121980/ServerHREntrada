
package peticiones;

import java.util.List;
import modelo.Users;

/**
 *
 * @author gsenorans
 */
public class SelectUsers {

    public String obtenerDatosUsers(List<Users> Listausers, String columna) {
        StringBuilder datosUsers = new StringBuilder();
        for (Users users : Listausers) {
            datosUsers.append("____________________________________________________________________\n");
            datosUsers.append("Login: ").append(users.getLogin()).append("\n")
                    .append("Pass: ").append(users.getPass()).append("\n")
                    .append("Tipo de usuario: ").append(users.getNumtipe()).append("\n")
                    .append("Dni: ").append(users.getDni()).append("\n");
        }
        datosUsers.append("____________________________________________________________________\n");
        return datosUsers.toString();
    }
}
