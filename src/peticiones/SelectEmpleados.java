
package peticiones;

import java.util.List;
import modelo.Empleados;

/**
 *
 * @author gsenorans
 */
public class SelectEmpleados {

    public String obtenerDatosEmpleados(List<Empleados> listaEmpleados, String columna) {
        StringBuilder datosEmpleados = new StringBuilder();
        for (Empleados empleado : listaEmpleados) {
            datosEmpleados.append("____________________________________________________________________\n");
            datosEmpleados.append("Dni: ").append(empleado.getDni()).append("\n")
                    .append("Nombre: ").append(empleado.getNom()).append("\n")
                    .append("Apellido: ").append(empleado.getApellido()).append("\n")
                    .append("Nombre empresa: ").append(empleado.getNomempresa()).append("\n")
                    .append("Departamento: ").append(empleado.getDepartament()).append("\n")
                    .append("Codigo tarjeta: ").append(empleado.getCodicard()).append("\n")
                    .append("Mail: ").append(empleado.getMail()).append("\n")
                    .append("Teléfono: ").append(empleado.getTelephon()).append("\n");
        }
        datosEmpleados.append("____________________________________________________________________\n");
        return datosEmpleados.toString();
    }
    
    
     public String obtenerDatosEmpleadosNomApellido(List<Empleados> listaEmpleadosNomApellido, String columna, String apellido) {
        StringBuilder datosEmpleadosNomApellido = new StringBuilder();
        for (Empleados empleadoNomApellido : listaEmpleadosNomApellido) {
            datosEmpleadosNomApellido.append("____________________________________________________________________\n");
            datosEmpleadosNomApellido.append("Dni: ").append(empleadoNomApellido.getDni()).append("\n")
                    .append("Nombre: ").append(empleadoNomApellido.getNom()).append("\n")
                    .append("Apellido: ").append(empleadoNomApellido.getApellido()).append("\n")
                    .append("Nombre empresa: ").append(empleadoNomApellido.getNomempresa()).append("\n")
                    .append("Departamento: ").append(empleadoNomApellido.getDepartament()).append("\n")
                    .append("Codigo tarjeta: ").append(empleadoNomApellido.getCodicard()).append("\n")
                    .append("Mail: ").append(empleadoNomApellido.getMail()).append("\n")
                    .append("Teléfono: ").append(empleadoNomApellido.getTelephon()).append("\n");
        }
        datosEmpleadosNomApellido.append("____________________________________________________________________\n");
        return datosEmpleadosNomApellido.toString();
    }
}
