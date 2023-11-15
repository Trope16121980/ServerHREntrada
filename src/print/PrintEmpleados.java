package print;

import java.util.List;
import modelo.Empleados;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class PrintEmpleados {

    /**
     * Este método imprime la lista de empleados que hemos
     * buscado en el metodo listaTotalEmpleados del paquete peticiones he
     * imprime los dato en el textArea del JFrame del servidor
     *
     * @param listaEmpleados List de empleados completa
     * @param columna el nombre de la columna
     * @return devuel los datos de los empleados
     */
    public String obtenerDatosEmpleados(List<Empleados> listaEmpleados, String columna) {
        StringBuilder datosEmpleados = new StringBuilder();
        for (Empleados empleado : listaEmpleados) {
            datosEmpleados.append("____________________________________________________________________\n");
            datosEmpleados.append("Dni: ").append(empleado.getDni()).append("\n").append("Nombre: ")
                    .append(empleado.getNom()).append("\n").append("Apellido: ").append(empleado.getApellido())
                    .append("\n").append("Nombre empresa: ").append(empleado.getNomempresa()).append("\n")
                    .append("Departamento: ").append(empleado.getDepartament()).append("\n").append("Codigo tarjeta: ")
                    .append(empleado.getCodicard()).append("\n").append("Mail: ").append(empleado.getMail())
                    .append("\n").append("Telefono: ").append(empleado.getTelephon()).append("\n");
        }
        datosEmpleados.append("____________________________________________________________________\n");
        return datosEmpleados.toString();
    }

    /**
     * Este método imprime la lista de empleados que hemos
     * buscado en el metodo listaEmpleadosNomApellido del paquete peticiones he
     * imprime los dato en el textArea del JFrame del servidor
     *
     * @param listaEmpleadosNomApellido
     * @param columna
     * @param apellido
     * @return
     */
    public String obtenerDatosEmpleadosNomApellido(List<Empleados> listaEmpleadosNomApellido, String columna,
            String apellido) {
        StringBuilder datosEmpleadosNomApellido = new StringBuilder();
        for (Empleados empleadoNomApellido : listaEmpleadosNomApellido) {
            datosEmpleadosNomApellido.append("____________________________________________________________________\n");
            datosEmpleadosNomApellido.append("Dni: ").append(empleadoNomApellido.getDni()).append("\n")
                    .append("Nombre: ").append(empleadoNomApellido.getNom()).append("\n").append("Apellido: ")
                    .append(empleadoNomApellido.getApellido()).append("\n").append("Nombre empresa: ")
                    .append(empleadoNomApellido.getNomempresa()).append("\n").append("Departamento: ")
                    .append(empleadoNomApellido.getDepartament()).append("\n").append("Codigo tarjeta: ")
                    .append(empleadoNomApellido.getCodicard()).append("\n").append("Mail: ")
                    .append(empleadoNomApellido.getMail()).append("\n").append("Telefono: ")
                    .append(empleadoNomApellido.getTelephon()).append("\n");
        }
        datosEmpleadosNomApellido.append("____________________________________________________________________\n");
        return datosEmpleadosNomApellido.toString();
    }
}
