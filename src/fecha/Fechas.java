
package fecha;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Gustavo_Senorans
 */
public class Fechas {

    public String fecha_hora() {

        Date fechaHora_actual = new Date();
        SimpleDateFormat formatoFechaHora = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
        return formatoFechaHora.format(fechaHora_actual);
    }

    public String nombre_fichero() {

        Date fechaHora_actual = new Date();
        SimpleDateFormat formatoFechaHora = new SimpleDateFormat("dd-MM-yyyy - HH-mm-ss");
        return formatoFechaHora.format(fechaHora_actual);
    }

    public String fecha() {

        Date fecha_actual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.format(fecha_actual);
    }

    public String hora() {

        Date hora_actual = new Date();
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
        return formatoHora.format(hora_actual);
    }

    public String mes_ano() {

        Date mes_ano_actual = new Date();
        SimpleDateFormat formatomesAno = new SimpleDateFormat("MM/yyyy");
        return formatomesAno.format(mes_ano_actual);
    }

}
