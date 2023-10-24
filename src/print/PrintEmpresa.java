
package print;

import java.util.List;
import modelo.Empresa;

/**
 *
 * @author Gustavo_Senorans
 */
public class PrintEmpresa {
    
    public String obtenerDatosEmpresa(List<Empresa> Listaempresas, String columna) {
        StringBuilder datosEmpresa = new StringBuilder();
        for (Empresa empresa : Listaempresas) {
            datosEmpresa.append("____________________________________________________________________\n");
            datosEmpresa.append("Nombre empresa: ").append(empresa.getNom()).append("\n")
                    .append("Direccion: ").append(empresa.getAddress()).append("\n")
                    .append("Telefono: ").append(empresa.getTelephon()).append("\n");
        }
        datosEmpresa.append("____________________________________________________________________\n");
        return datosEmpresa.toString();
    }
    
}
