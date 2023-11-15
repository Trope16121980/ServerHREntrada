package fecha;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class Fechas {

    /**
     * Genera un String con el formato dd/MM/yyyy-HH:mm:ss
     *
     * @return devuelve la fecha y hora
     */
    public String fecha_hora() {

        Date fechaHora_actual = new Date();
        SimpleDateFormat formatoFechaHora = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
        return formatoFechaHora.format(fechaHora_actual);
    }

    /**
     * Genera un String con el formato dd-MM-yyyy - HH-mm-ss
     *
     * @return devuelve la fecha y hora
     */
    public String nombre_fichero() {

        Date fechaHora_actual = new Date();
        SimpleDateFormat formatoFechaHora = new SimpleDateFormat("dd-MM-yyyy - HH-mm-ss");
        return formatoFechaHora.format(fechaHora_actual);
    }

    /**
     * Genera un String con el formato dd/MM/yyyy
     *
     * @return devuelve la fecha
     */
    public String fecha() {

        Date fecha_actual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.format(fecha_actual);
    }

    /**
     * Genera un String con el formato yyyy/MM/dd
     *
     * @return devuelve la fecha
     */
    public String fecha_Jornada() {

        Date fecha_actual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
        return formatoFecha.format(fecha_actual);
    }

    /**
     * Genera un String con el formato HH:mmHH:mm
     *
     * @return devuelve la hora actual
     */
    public String hora() {

        Date hora_actual = new Date();
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
        return formatoHora.format(hora_actual);
    }

    /**
     * Genera un String con el formato MM/yyyy
     *
     * @return devuelve el mes y el año
     */
    public String mes_ano() {

        Date mes_ano_actual = new Date();
        SimpleDateFormat formatomesAno = new SimpleDateFormat("MM/yyyy");
        return formatomesAno.format(mes_ano_actual);
    }

}
