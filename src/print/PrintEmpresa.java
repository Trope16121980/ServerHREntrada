
package print;

import java.util.List;
import modelo.Empresa;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class PrintEmpresa {
    
   /**
     * Este método imprime la lista de empleados que hemos
     * buscado en el metodo listaTotalEmpresas del paquete peticiones he
     * imprime los dato en el textArea del JFrame del servidor
     *
     * @param Listaempresas List de empresas completa
     * @param columna el nombre de la columna
     * @return devuelve los datos de las empresas
     */
    public String obtenerDatosEmpresa(List<Empresa> Listaempresas, String columna) {
        StringBuilder datosEmpresa = new StringBuilder();
        for (Empresa empresa : Listaempresas) {
            datosEmpresa.append("\nNombre empresa: ").append(empresa.getNom()).append("\n")
                    .append("Direccion: ").append(empresa.getAddress()).append("\n")
                    .append("Telefono: ").append(empresa.getTelephon()).append("\n");
        }
        datosEmpresa.append("____________________________________________________________________\n");
        return datosEmpresa.toString();
    }
    
}
