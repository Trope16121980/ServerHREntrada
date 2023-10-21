/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
