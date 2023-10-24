package print;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import modelo.*;
import peticiones.Listaempleados;
import peticiones.Listaempresas;
import peticiones.Listajornada;
import peticiones.Listausers;

/**
 *
 * @author Gustavo_Senorans
 */
public class PrintPorColumna {

    public void selectColumna(String palabra) throws IOException {

        String[] frase = new String[6];
        frase = palabra.split(",");

        if (frase[5].equals("0") || frase[5].equals("1")) {

            String crud = frase[1];
            String nombreTabla = frase[2]; //Será el numero de tabla. (ej: 1->empleados 2->users 3-jornada 4-usertipe 5->empresa)
            String columna = frase[3]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla

            if (crud.equals("0")) {
                if (!nombreTabla.equals(null) && columna.equals("0")) {
                    switch (nombreTabla) {
                        case "0":
                            List<Empleados> listaEmpleados = new ArrayList<Empleados>();
                            listaEmpleados = Listaempleados.listaTotalEmpleados();
                            for (int i = 0; i < listaEmpleados.size(); i++) {
                                System.out.println("____________________________________________________________________");
                                System.out.println("Dni: " + listaEmpleados.get(i).getDni() + "\n"
                                        + "Nombre: " + listaEmpleados.get(i).getNom() + "\n"
                                        + "Apellido: " + listaEmpleados.get(i).getApellido() + "\n"
                                        + "Nombre empresa: " + listaEmpleados.get(i).getNomempresa() + "\n"
                                        + "Departamento: " + listaEmpleados.get(i).getDepartament() + "\n"
                                        + "Codigo tarjeta: " + listaEmpleados.get(i).getCodicard() + "\n"
                                        + "Mail: " + listaEmpleados.get(i).getMail() + "\n"
                                        + "Telefono: " + listaEmpleados.get(i).getTelephon() + "\n");
                                System.out.println("____________________________________________________________________");
                            }
                            break;
                        case "1":
                            List<Users> listaToUsers = new ArrayList<Users>();
                            listaToUsers = Listausers.listaTotalUsers();
                            for (int i = 0; i < listaToUsers.size(); i++) {
                                System.out.println("____________________________________________________________________");
                                System.out.println("Login: " + listaToUsers.get(i).getLogin() + "\n"
                                        + "Pass: " + listaToUsers.get(i).getPass() + "\n"
                                        + "Tipo de usuario: " + listaToUsers.get(i).getNumtipe() + "\n"
                                        + "Dni: " + listaToUsers.get(i).getDni() + "\n");
                                System.out.println("____________________________________________________________________");
                            }
                            break;
                        case "2":
                            List<Empresa> listaEmpresas = new ArrayList<Empresa>();
                            listaEmpresas = Listaempresas.listaTotalEmpresas();
                            for (int i = 0; i < listaEmpresas.size(); i++) {
                                System.out.println("____________________________________________________________________");
                                System.out.println("Nombre empresa: " + listaEmpresas.get(i).getNom() + "\n"
                                        + "Direccion: " + listaEmpresas.get(i).getAddress() + "\n"
                                        + "Telefono: " + listaEmpresas.get(i).getTelephon());
                                System.out.println("____________________________________________________________________");
                            }
                            break;
                        case "3":
                            List<Jornada> listaJornada = new ArrayList<Jornada>();
                            listaJornada = Listajornada.listaTotalJornada();
                            for (int i = 0; i < listaJornada.size(); i++) {
                                System.out.println("____________________________________________________________________");
                                System.out.println("Dni: " + listaJornada.get(i).getDni() + "\n"
                                        + "Nombre: " + listaJornada.get(i).getNom() + "\n"
                                        + "Apellido: " + listaJornada.get(i).getApellido() + "\n"
                                        + "Codigo tarjeta: " + listaJornada.get(i).getCodicard() + "\n"
                                        + "Hora entrada: " + listaJornada.get(i).getHoraentrada() + "\n"
                                        + "Hora salida: " + listaJornada.get(i).getHorasalida() + "\n"
                                        + "Total horas: " + listaJornada.get(i).getTotal() + "\n"
                                        + "Fehca: " + listaJornada.get(i).getFecha() + "\n");
                                System.out.println("____________________________________________________________________");
                            }
                            break;
                    }
                }
            }
        }
    }
}
