package search;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import errores.Errores;
import modelo.Jornada;
import peticiones.Listajornada;
import print.PrintJornada;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class SearchCrudJornada {

    /**
     * Este método verifica los datos y hace el envio de los datos al cliente en
     * forma de objeto y si no le envia un error
     *
     * @param crud en este caso es el 0 de select
     * @param nombreTabla en este caso es el 3, ya que se refiere a jornada
     * @param columna nombre de la tabla
     * @param palabraAbuscar dato a buscar dni, nom, apellido, codicard, fecha
     * de la jornada laboral
     * @param outObjeto el objeto a enviar al cliente
     * @param client el socket del cliente
     * @throws IOException controla los errores
     */
    public static void handleSearchRequest(String crud, String nombreTabla, String columna, String palabraAbuscar,
            ObjectOutputStream outObjeto, Socket client) throws IOException {

        if (crud.equals("0")) {
            if (nombreTabla.equals("3") && columna.equals("dni")) {
                PrintJornada jornada = new PrintJornada();
                List<Jornada> listaToJornadaDni = Listajornada.listaTotalJornadaDni(palabraAbuscar);

                if (!listaToJornadaDni.isEmpty()) {
                    String datosJornadaDni = jornada.obtenerDatosJornada(listaToJornadaDni, columna);
                    System.out.println(datosJornadaDni);
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(listaToJornadaDni);
                    outObjeto.flush();
                } else {
                    Errores error = new Errores();
                    String errorDni = error.errorDni();
                    System.out.println(errorDni);
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(errorDni);
                    outObjeto.flush();
                }

            } else if (nombreTabla.equals("3") && columna.equals("nom")) {
                PrintJornada jornada = new PrintJornada();
                List<Jornada> listaTotalJornadaNom = Listajornada.listaTotalJornadaNom(palabraAbuscar);

                if (!listaTotalJornadaNom.isEmpty()) {
                    String datosJornadaDni = jornada.obtenerDatosJornada(listaTotalJornadaNom, columna);
                    System.out.println(datosJornadaDni);
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(listaTotalJornadaNom);
                    outObjeto.flush();
                } else {
                    Errores error = new Errores();
                    String errorNom = error.errorNom();
                    System.out.println(errorNom);
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(errorNom);
                    outObjeto.flush();
                }

            } else if (nombreTabla.equals("3") && columna.equals("apellido")) {
                PrintJornada jornada = new PrintJornada();
                List<Jornada> listaTotalJornadaAapellido = Listajornada.listaTotalJornadaApellido(palabraAbuscar);

                if (!listaTotalJornadaAapellido.isEmpty()) {
                    String datosJornadaDni = jornada.obtenerDatosJornada(listaTotalJornadaAapellido, columna);
                    System.out.println(datosJornadaDni);
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(listaTotalJornadaAapellido);
                    outObjeto.flush();
                } else {
                    Errores error = new Errores();
                    String errorApellido = error.errorApellido();
                    System.out.println(errorApellido);
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(errorApellido);
                    outObjeto.flush();
                }

            } else if (nombreTabla.equals("3") && columna.equals("codicard")) {
                PrintJornada jornada = new PrintJornada();
                List<Jornada> listaJornadaCodiCard = Listajornada.listaJornadaCodiCard(palabraAbuscar);

                if (!listaJornadaCodiCard.isEmpty()) {
                    String datosJornadaCodicard = jornada.obtenerDatosJornada(listaJornadaCodiCard, columna);
                    System.out.println(datosJornadaCodicard);
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(listaJornadaCodiCard);
                    outObjeto.flush();
                } else {
                    Errores error = new Errores();
                    String erroCodicard = error.erroCodicard();
                    System.out.println(erroCodicard);
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(erroCodicard);
                    outObjeto.flush();
                }

            } else {
                if (nombreTabla.equals("3") && columna.equals("fecha")) {
                    PrintJornada jornada = new PrintJornada();
                    List<Jornada> listaTotalJornadaFecha = Listajornada.listaTotalJornadaFecha(palabraAbuscar);

                    if (!listaTotalJornadaFecha.isEmpty()) {
                        String datosEmpresa = jornada.obtenerDatosJornada(listaTotalJornadaFecha, columna);
                        System.out.println(datosEmpresa);
                        outObjeto = new ObjectOutputStream(client.getOutputStream());
                        outObjeto.writeObject(listaTotalJornadaFecha);
                        outObjeto.flush();
                    } else {
                        Errores error = new Errores();
                        String erroFechaJornada = error.erroFechaJornada();
                        System.out.println(erroFechaJornada);
                        outObjeto = new ObjectOutputStream(client.getOutputStream());
                        outObjeto.writeObject(erroFechaJornada);
                        outObjeto.flush();
                    }

                }
            }
        }
    }
}
