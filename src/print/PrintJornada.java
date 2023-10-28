
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
    
    public String obtenerDatosJornadaDniFecha(List<Jornada> listaJornadaDniFecha, String dni, String fecha) {
        StringBuilder datosJornadaDniFecha = new StringBuilder();
        for (Jornada jornadaDniFecha : listaJornadaDniFecha) {
        	datosJornadaDniFecha.append("____________________________________________________________________\n");
        	datosJornadaDniFecha.append("Dni: ").append(jornadaDniFecha.getDni()).append("\n")
                    .append("Nombre: ").append(jornadaDniFecha.getNom()).append("\n")
                    .append("Apellido: ").append(jornadaDniFecha.getApellido()).append("\n")
                    .append("Codigo tarjeta: ").append(jornadaDniFecha.getCodicard()).append("\n")
                    .append("Hora entrada: ").append(jornadaDniFecha.getHoraentrada()).append("\n")
                    .append("Hora salida: ").append(jornadaDniFecha.getHorasalida()).append("\n")
                    .append("Total horas: ").append(jornadaDniFecha.getTotal()).append("\n")
                    .append("Fehca: ").append(jornadaDniFecha.getFecha()).append("\n");
        }
        datosJornadaDniFecha.append("____________________________________________________________________\n");
        return datosJornadaDniFecha.toString();
    }
    
    public String obtenerDatosJornadaNomFecha(List<Jornada> listaJornadaNomFecha, String nom, String fecha) {
        StringBuilder datosJornadaNomFecha = new StringBuilder();
        for (Jornada jornadaNomFecha : listaJornadaNomFecha) {
        	datosJornadaNomFecha.append("____________________________________________________________________\n");
        	datosJornadaNomFecha.append("Dni: ").append(jornadaNomFecha.getDni()).append("\n")
                    .append("Nombre: ").append(jornadaNomFecha.getNom()).append("\n")
                    .append("Apellido: ").append(jornadaNomFecha.getApellido()).append("\n")
                    .append("Codigo tarjeta: ").append(jornadaNomFecha.getCodicard()).append("\n")
                    .append("Hora entrada: ").append(jornadaNomFecha.getHoraentrada()).append("\n")
                    .append("Hora salida: ").append(jornadaNomFecha.getHorasalida()).append("\n")
                    .append("Total horas: ").append(jornadaNomFecha.getTotal()).append("\n")
                    .append("Fehca: ").append(jornadaNomFecha.getFecha()).append("\n");
        }
        datosJornadaNomFecha.append("____________________________________________________________________\n");
        return datosJornadaNomFecha.toString();
    }
    
    public String obtenerDatosJornadaApellidoFecha(List<Jornada> listaJornadaApellidoFecha, String apellido, String fecha) {
        StringBuilder datosJornadaApellidoFecha = new StringBuilder();
        for (Jornada jornadaApellidoFecha : listaJornadaApellidoFecha) {
        	datosJornadaApellidoFecha.append("____________________________________________________________________\n");
        	datosJornadaApellidoFecha.append("Dni: ").append(jornadaApellidoFecha.getDni()).append("\n")
                    .append("Nombre: ").append(jornadaApellidoFecha.getNom()).append("\n")
                    .append("Apellido: ").append(jornadaApellidoFecha.getApellido()).append("\n")
                    .append("Codigo tarjeta: ").append(jornadaApellidoFecha.getCodicard()).append("\n")
                    .append("Hora entrada: ").append(jornadaApellidoFecha.getHoraentrada()).append("\n")
                    .append("Hora salida: ").append(jornadaApellidoFecha.getHorasalida()).append("\n")
                    .append("Total horas: ").append(jornadaApellidoFecha.getTotal()).append("\n")
                    .append("Fehca: ").append(jornadaApellidoFecha.getFecha()).append("\n");
        }
        datosJornadaApellidoFecha.append("____________________________________________________________________\n");
        return datosJornadaApellidoFecha.toString();
    }
    
    public String obtenerDatosJornadaCodicardFecha(List<Jornada> listaJornadaCodicardFecha, String codicard, String fecha) {
        StringBuilder datosJornadaCodicardFecha = new StringBuilder();
        for (Jornada jornadaCodicardFecha : listaJornadaCodicardFecha) {
        	datosJornadaCodicardFecha.append("____________________________________________________________________\n");
        	datosJornadaCodicardFecha.append("Dni: ").append(jornadaCodicardFecha.getDni()).append("\n")
                    .append("Nombre: ").append(jornadaCodicardFecha.getNom()).append("\n")
                    .append("Apellido: ").append(jornadaCodicardFecha.getApellido()).append("\n")
                    .append("Codigo tarjeta: ").append(jornadaCodicardFecha.getCodicard()).append("\n")
                    .append("Hora entrada: ").append(jornadaCodicardFecha.getHoraentrada()).append("\n")
                    .append("Hora salida: ").append(jornadaCodicardFecha.getHorasalida()).append("\n")
                    .append("Total horas: ").append(jornadaCodicardFecha.getTotal()).append("\n")
                    .append("Fehca: ").append(jornadaCodicardFecha.getFecha()).append("\n");
        }
        datosJornadaCodicardFecha.append("____________________________________________________________________\n");
        return datosJornadaCodicardFecha.toString();
    }
    
    public String obtenerDatosJornadaNomApellidoFecha(List<Jornada> listaJornadaNomApellidoFecha, String nom, String apellido, String fecha) {
        StringBuilder datosJornadaNomApellidoFecha = new StringBuilder();
        for (Jornada jornadaNomApellidoFecha : listaJornadaNomApellidoFecha) {
        	datosJornadaNomApellidoFecha.append("____________________________________________________________________\n");
        	datosJornadaNomApellidoFecha.append("Dni: ").append(jornadaNomApellidoFecha.getDni()).append("\n")
                    .append("Nombre: ").append(jornadaNomApellidoFecha.getNom()).append("\n")
                    .append("Apellido: ").append(jornadaNomApellidoFecha.getApellido()).append("\n")
                    .append("Codigo tarjeta: ").append(jornadaNomApellidoFecha.getCodicard()).append("\n")
                    .append("Hora entrada: ").append(jornadaNomApellidoFecha.getHoraentrada()).append("\n")
                    .append("Hora salida: ").append(jornadaNomApellidoFecha.getHorasalida()).append("\n")
                    .append("Total horas: ").append(jornadaNomApellidoFecha.getTotal()).append("\n")
                    .append("Fehca: ").append(jornadaNomApellidoFecha.getFecha()).append("\n");
        }
        datosJornadaNomApellidoFecha.append("____________________________________________________________________\n");
        return datosJornadaNomApellidoFecha.toString();
    }
}
