
package print;

import java.util.List;
import modelo.Jornada;

/**
 *
 * @author Gustavo_Senorans
 */
public class PrintJornada {

    public String obtenerDatosJornada(List<Jornada> Listajornada, String columna) {
        StringBuilder datosJornadaa = new StringBuilder();
        for (Jornada jornada : Listajornada) {
            datosJornadaa.append("____________________________________________________________________\n");
            datosJornadaa.append("Dni: ").append(jornada.getDni()).append("\n")
                    .append("Nombre: ").append(jornada.getNom()).append("\n")
                    .append("Apellido: ").append(jornada.getApellido()).append("\n")
                    .append("Codigo tarjeta: ").append(jornada.getCodicard()).append("\n")
                    .append("Hora entrada: ").append(jornada.getHoraentrada()).append("\n")
                    .append("Hora salida: ").append(jornada.getHorasalida()).append("\n")
                    .append("Total horas: ").append(jornada.getTotal()).append("\n")
                    .append("Fehca: ").append(jornada.getFecha()).append("\n");
        }
        datosJornadaa.append("____________________________________________________________________\n");
        return datosJornadaa.toString();
    }

    public String obtenerDatosJornadaNomApellido(List<Jornada> listaJornadaNomApellido, String columna, String apellido) {
        StringBuilder datosJornadaNomApellido = new StringBuilder();
        for (Jornada jornadaNomApellido : listaJornadaNomApellido) {
            datosJornadaNomApellido.append("____________________________________________________________________\n");
            datosJornadaNomApellido.append("Dni: ").append(jornadaNomApellido.getDni()).append("\n")
                    .append("Nombre: ").append(jornadaNomApellido.getNom()).append("\n")
                    .append("Apellido: ").append(jornadaNomApellido.getApellido()).append("\n")
                    .append("Codigo tarjeta: ").append(jornadaNomApellido.getCodicard()).append("\n")
                    .append("Hora entrada: ").append(jornadaNomApellido.getHoraentrada()).append("\n")
                    .append("Hora salida: ").append(jornadaNomApellido.getHorasalida()).append("\n")
                    .append("Total horas: ").append(jornadaNomApellido.getTotal()).append("\n")
                    .append("Fehca: ").append(jornadaNomApellido.getFecha()).append("\n");
        }
        datosJornadaNomApellido.append("____________________________________________________________________\n");
        return datosJornadaNomApellido.toString();
    }
}
