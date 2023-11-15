package codigo;

/**
 * @author Gustavo Senor�ns Varela
 * @version 1.4, 15/11/2023
 * @see documento "Normas de programaci�n v1.0"
 * @since jdk 17
 */
/**
 * @author Gustavo Senor�ns Varela
 */
public class Codigo {

    /**
     * Crea un c�digo aleatorio de inicio de sesi�n �nico basado en un tipo de
     * usuario.
     *
     * @param tipouser define el tipo de usuario 0 = admin / 1 = user
     * @return genera el codigo de usuario segun el tipo de usuarios ya sea
     * Admin o User
     */
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
