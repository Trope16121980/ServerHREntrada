
package peticiones;

import java.util.List;
import modelo.Empresa;

/**
 *
 * @author gsenorans
 */
public class SelectEmpresa {
    
    public String obtenerDatosEmpresa(List<Empresa> Listaempresas, String columna) {
        StringBuilder datosEmpresa = new StringBuilder();
        for (Empresa empresa : Listaempresas) {
            datosEmpresa.append("____________________________________________________________________\n");
            datosEmpresa.append("Nombre empresa: ").append(empresa.getNom()).append("\n")
                    .append("Dirección: ").append(empresa.getAddress()).append("\n")
                    .append("Teléfono: ").append(empresa.getTelephon()).append("\n");
        }
        datosEmpresa.append("____________________________________________________________________\n");
        return datosEmpresa.toString();
    }
    
}
