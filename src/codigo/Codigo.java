package codigo;
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
