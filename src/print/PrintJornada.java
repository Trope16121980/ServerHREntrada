
package print;

import java.util.List;
import modelo.Jornada;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */

public class PrintJornada {

      /**
     * Este método imprime la lista de jornada que hemos
     * buscado en el metodo listaTotalJornada del paquete peticiones he
     * imprime los dato en el textArea del JFrame del servidor
     *
     * @param Listajornada List de las jornadas completas
     * @param columna el nombre de la columna
     * @return devuelve los datos de las jornada
     */
    
    public String obtenerDatosJornada(List<Jornada> Listajornada, String columna) {
        StringBuilder datosJornadaa = new StringBuilder();
        for (Jornada jornada : Listajornada) {
            datosJornadaa.append("\nDni: ").append(jornada.getDni()).append("\n")
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

     /**
     * Este método imprime la lista de jornada que hemos
     * buscado en el metodo listaJornadaNomApellido del paquete peticiones he
     * imprime los dato en el textArea del JFrame del servidor
     *
     * @param listaJornadaNomApellido List de las jornadas por nombre y apellido
     * @param columna el nombre de la columna
     * @param apellido el apellido a buscar
     * @return devuelve los datos de las jornada
     */
    public String obtenerDatosJornadaNomApellido(List<Jornada> listaJornadaNomApellido, String columna, String apellido) {
        StringBuilder datosJornadaNomApellido = new StringBuilder();
        for (Jornada jornadaNomApellido : listaJornadaNomApellido) {
            datosJornadaNomApellido.append("\nDni: ").append(jornadaNomApellido.getDni()).append("\n")
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
    
     /**
     * Este método imprime la lista de jornada que hemos
     * buscado en el metodo listaJornadaDniFecha del paquete peticiones he
     * imprime los dato en el textArea del JFrame del servidor
     *
     * @param listaJornadaDniFecha List de las jornadas por dni y fecha
     * @param dni el dni a buscar
     * @param fecha la fecha a buscar
     * @return devuelve los datos de las jornada
     */
    
    public String obtenerDatosJornadaDniFecha(List<Jornada> listaJornadaDniFecha, String dni, String fecha) {
        StringBuilder datosJornadaDniFecha = new StringBuilder();
        for (Jornada jornadaDniFecha : listaJornadaDniFecha) {
        	datosJornadaDniFecha.append("\nDni: ").append(jornadaDniFecha.getDni()).append("\n")
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
    
     /**
     * Este método imprime la lista de jornada que hemos
     * buscado en el metodo listaJornadaNomFecha del paquete peticiones he
     * imprime los dato en el textArea del JFrame del servidor
     *
     * @param listaJornadaNomFecha List de las jornadas por nombre y fecha
     * @param nom el nombre a buscar
     * @param fecha la fecha de la buscar
     * @return devuelve los datos de las jornada
     */
    public String obtenerDatosJornadaNomFecha(List<Jornada> listaJornadaNomFecha, String nom, String fecha) {
        StringBuilder datosJornadaNomFecha = new StringBuilder();
        for (Jornada jornadaNomFecha : listaJornadaNomFecha) {
        	datosJornadaNomFecha.append("\nDni: ").append(jornadaNomFecha.getDni()).append("\n")
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
    
     /**
     * Este método imprime la lista de jornada que hemos
     * buscado en el metodo listaJornadaApellidoFecha del paquete peticiones he
     * imprime los dato en el textArea del JFrame del servidor
     *
     * @param listaJornadaApellidoFecha List de las jornadas por apellido y fecha
     * @param apellido el apellido a buscar
     * @param fecha fecha de la jornada
     * @return devuelve los datos de las jornada
     */
    public String obtenerDatosJornadaApellidoFecha(List<Jornada> listaJornadaApellidoFecha, String apellido, String fecha) {
        StringBuilder datosJornadaApellidoFecha = new StringBuilder();
        for (Jornada jornadaApellidoFecha : listaJornadaApellidoFecha) {
        	datosJornadaApellidoFecha.append("\nDni: ").append(jornadaApellidoFecha.getDni()).append("\n")
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
    
     /**
     * Este método imprime la lista de jornada que hemos
     * buscado en el metodo listaJornadaCodicardFecha del paquete peticiones he
     * imprime los dato en el textArea del JFrame del servidor
     *
     * @param listaJornadaCodicardFecha List de las jornadas por codigo de tarjeta y fecha
     * @param codicard el codigo de tarjeta a bucar
     * @param fecha la fecha a buscar
     * @return devuelve los datos de las jornada
     */
    public String obtenerDatosJornadaCodicardFecha(List<Jornada> listaJornadaCodicardFecha, String codicard, String fecha) {
        StringBuilder datosJornadaCodicardFecha = new StringBuilder();
        for (Jornada jornadaCodicardFecha : listaJornadaCodicardFecha) {
        	datosJornadaCodicardFecha.append("\nDni: ").append(jornadaCodicardFecha.getDni()).append("\n")
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
    
     /**
     * Este método imprime la lista de jornada que hemos
     * buscado en el metodo listaJornadaNomApellidoFecha del paquete peticiones he
     * imprime los dato en el textArea del JFrame del servidor
     *
     * @param listaJornadaNomApellidoFecha List de las jornadas por nombre, apellido y fecha
     * @param nom el nombre a buscar
     * @param apellido el apellido a buscar
     * @param fecha la fecha a buscar
     * @return devuelve los datos de las jornada
     */
    public String obtenerDatosJornadaNomApellidoFecha(List<Jornada> listaJornadaNomApellidoFecha, String nom, String apellido, String fecha) {
        StringBuilder datosJornadaNomApellidoFecha = new StringBuilder();
        for (Jornada jornadaNomApellidoFecha : listaJornadaNomApellidoFecha) {
        	datosJornadaNomApellidoFecha.append("\nDni: ").append(jornadaNomApellidoFecha.getDni()).append("\n")
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
