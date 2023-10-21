
package codigo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gustavo_Senorans
 */
public class Codigo {

    public static String crearCodigoLogin(int tipouser) {
        String codigo = "-1";

        int numeroAleatorio = (int) (Math.floor(Math.random() * (1 - 99999 + 1) + 99999));
        if (tipouser == 0) {
            codigo = "A" + String.valueOf(numeroAleatorio);
        } else if (tipouser == 1) {
            codigo = "U" + String.valueOf(numeroAleatorio);
        }
        return codigo;
    }
}
