package peticiones;

import errores.Errores;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Jornada;
import fecha.Fechas;

/**
 *
 * @author gsenorans
 */
public class UpdateJornada {

    public static ArrayList<Jornada> updateJornadaDni(String crud, String nombreTabla,
            String dni, String datoDni, String palabraAbuscar, String palabra, ObjectOutputStream outObjeto, Socket client) throws IOException, SQLException {
        Fechas fecha = new Fechas();
        ArrayList<Jornada> jornada = new ArrayList<Jornada>();

        try {
            String nombreNuevo = "SELECT * FROM empleados where dni = ?";
            PreparedStatement psNom = controladores.Conexion.getconexion().prepareStatement(nombreNuevo);
            psNom.setString(1, datoDni);
            ResultSet rsNom = psNom.executeQuery();
            if (!rsNom.next()) {
                Errores error = new Errores();
                String errorDni = error.errorDni();
                System.out.println(errorDni);
                outObjeto = new ObjectOutputStream(client.getOutputStream());
                outObjeto.writeObject(errorDni);
                outObjeto.flush();
            } else {
                psNom.close();

                try {
                    String horasalidaNull = "SELECT * FROM jornada where dni = ? and horasalida = 'nulo'";
                    PreparedStatement psHorasalida = controladores.Conexion.getconexion().prepareStatement(horasalidaNull);
                    psHorasalida.setString(1, datoDni);
                    ResultSet rsHorasalida = psHorasalida.executeQuery();

                    if (!rsHorasalida.next()) {
                        Errores error = new Errores();
                        String errorUpdateJornada = error.errorUpdateJornada();
                        System.out.println(errorUpdateJornada);
                        outObjeto = new ObjectOutputStream(client.getOutputStream());
                        outObjeto.writeObject(errorUpdateJornada);
                        outObjeto.flush();
                    } else {
                        psHorasalida.close();
                        String horasalida = fecha.hora();
                        String total = null;

                        //SALIDA
                        String sCadena = rsHorasalida.getString("horaentrada");

                        String sSubCadena = sCadena.substring(0, 2);
                        String sSubCadena1 = sCadena.substring(3, 5);
                        int horasEntrada = Integer.parseInt(sSubCadena);
                        int minEntrada = Integer.parseInt(sSubCadena1);
                        int horas_entrada = horasEntrada;
                        int min_entrada = minEntrada;

                        //ENTRADA
                        String sCadena2 = horasalida;
                        String sSubCadena3 = sCadena2.substring(0, 2);
                        String sSubCadena4 = sCadena2.substring(3, 5);
                        int horasSalida = Integer.parseInt(sSubCadena3);
                        int minSalida = Integer.parseInt(sSubCadena4);
                        int horas_salida = horasSalida;
                        int min_salida = minSalida;

                        //CALCULOS
                        if (horas_entrada == horas_salida && min_entrada <= min_salida) {

                            int total_horas = (horas_entrada - horas_salida);
                            int total_min = (min_salida - min_entrada);

                            int hr = total_horas;
                            int mn = total_min;
                            String sr = (hr + ":" + mn);

                            total = sr;
                        }
                        if (horas_entrada > horas_salida && min_entrada > min_salida) {

                            int total_horas_entrada = (24 - horas_entrada);
                            int total_min_entrada = (60 - min_entrada);
                            int total_horas = (horas_salida + total_horas_entrada - 1);
                            int total_min = (total_min_entrada + min_salida);

                            int hr = total_horas;
                            int mn = total_min;
                            String sr = (hr + ":" + mn);

                            total = sr;
                        }
                        if (horas_entrada > horas_salida && min_entrada == min_salida) {

                            int total_horas_entrada = (24 - horas_entrada);
                            int total_horas = (total_horas_entrada + horas_salida);
                            int total_min = (min_entrada - min_salida);

                            int hr = total_horas;
                            int mn = total_min;
                            String sr = (hr + ":" + mn);

                            total = sr;
                        }
                        if (horas_entrada > horas_salida && min_entrada < min_salida) {

                            int total_horas_entrada = (24 - horas_entrada);
                            int total_horas = (total_horas_entrada + horas_salida);
                            int total_min_salida = (60 - min_salida);
                            int total_min_entrada = (60 - min_entrada);
                            int total_min = (total_min_entrada - total_min_salida);

                            int hr = total_horas;
                            int mn = total_min;
                            String sr = (hr + ":" + mn);

                            total = sr;
                        }
                        if (horas_entrada < horas_salida && min_entrada == min_salida) {

                            int total_horas_entrada = (24 - horas_entrada);
                            int total_horas_salida = (24 - horas_salida);
                            int total_horas = (total_horas_entrada - total_horas_salida);
                            int total_min = (min_entrada - min_salida);

                            int hr = total_horas;
                            int mn = total_min;
                            String sr = (hr + ":" + mn);

                            total = sr;
                        }
                        if (horas_entrada < horas_salida && min_entrada < min_salida) {

                            int total_horas_entrada = (24 - horas_entrada);
                            int total_horas_salida = (24 - horas_salida);
                            int total_horas = (total_horas_entrada - total_horas_salida);
                            int total_min_salida = (60 - min_salida);
                            int total_min_entrada = (60 - min_entrada);
                            int total_min = (total_min_entrada - total_min_salida);

                            int hr = total_horas;
                            int mn = total_min;
                            String sr = (hr + ":" + mn);

                            total = sr;
                        }
                        if (horas_entrada < horas_salida && min_entrada > min_salida) {

                            int total_horas_entrada = (24 - horas_entrada);
                            int total_horas_salida = (24 - horas_salida);
                            int total_horas = (total_horas_entrada - total_horas_salida - 1);
                            int total_min_entrada = (60 - min_entrada);
                            int total_min = (total_min_entrada + min_salida);

                            int hr = total_horas;
                            int mn = total_min;
                            String sr = (hr + ":" + mn);

                            total = sr;
                        }
                        String consulta = "UPDATE jornada SET horasalida = ?, total = ? WHERE dni = ?";
                        PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
                        preparedStatement.setString(1, horasalida);
                        preparedStatement.setString(2, total);
                        preparedStatement.setString(3, datoDni);

                        preparedStatement.executeUpdate();
                        preparedStatement.close();

                        Jornada nuevaJornada = new Jornada(rsHorasalida.getString("dni"), rsHorasalida.getString("nom"), rsHorasalida.getString("apellido"), rsHorasalida.getString("horaentrada"), horasalida, total, rsHorasalida.getString("fecha"), rsHorasalida.getString("codicard"));
                        jornada.add(nuevaJornada);
                    }
                } catch (SQLException e) {
                    Errores error = new Errores();
                    String errorUpdateJornada = error.errorUpdateJornada();
                    System.out.println(errorUpdateJornada);
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(errorUpdateJornada);
                    outObjeto.flush();
                }
                return jornada;
            }
        } catch (SQLException e) {
            Errores error = new Errores();
            String errorDni = error.errorDni();
            System.out.println(errorDni);
            outObjeto = new ObjectOutputStream(client.getOutputStream());
            outObjeto.writeObject(errorDni);
            outObjeto.flush();
        }
        return jornada;
    }
    
     public static ArrayList<Jornada> updateJornadaCodicard(String crud, String nombreTabla,
            String codicad, String datoCodicard, String palabraAbuscar, String palabra, ObjectOutputStream outObjeto, Socket client) throws IOException, SQLException {
        Fechas fecha = new Fechas();
        ArrayList<Jornada> jornada = new ArrayList<Jornada>();

        try {
            String nombreNuevo = "SELECT * FROM empleados where codicard = ?";
            PreparedStatement psNom = controladores.Conexion.getconexion().prepareStatement(nombreNuevo);
            psNom.setString(1, datoCodicard);
            ResultSet rsNom = psNom.executeQuery();
            if (!rsNom.next()) {
                Errores error = new Errores();
                String erroCodicard = error.erroCodicard();
                System.out.println(erroCodicard);
                outObjeto = new ObjectOutputStream(client.getOutputStream());
                outObjeto.writeObject(erroCodicard);
                outObjeto.flush();
            } else {
                psNom.close();

                try {
                    String horasalidaNull = "SELECT * FROM jornada where codicard = ? and horasalida = 'nulo'";
                    PreparedStatement psHorasalida = controladores.Conexion.getconexion().prepareStatement(horasalidaNull);
                    psHorasalida.setString(1, datoCodicard);
                    ResultSet rsHorasalida = psHorasalida.executeQuery();

                    if (!rsHorasalida.next()) {
                        Errores error = new Errores();
                        String errorUpdateJornada = error.errorUpdateJornada();
                        System.out.println(errorUpdateJornada);
                        outObjeto = new ObjectOutputStream(client.getOutputStream());
                        outObjeto.writeObject(errorUpdateJornada);
                        outObjeto.flush();
                    } else {
                        psHorasalida.close();
                        String horasalida = fecha.hora();
                        String total = null;

                        //SALIDA
                        String sCadena = rsHorasalida.getString("horaentrada");

                        String sSubCadena = sCadena.substring(0, 2);
                        String sSubCadena1 = sCadena.substring(3, 5);
                        int horasEntrada = Integer.parseInt(sSubCadena);
                        int minEntrada = Integer.parseInt(sSubCadena1);
                        int horas_entrada = horasEntrada;
                        int min_entrada = minEntrada;

                        //ENTRADA
                        String sCadena2 = horasalida;
                        String sSubCadena3 = sCadena2.substring(0, 2);
                        String sSubCadena4 = sCadena2.substring(3, 5);
                        int horasSalida = Integer.parseInt(sSubCadena3);
                        int minSalida = Integer.parseInt(sSubCadena4);
                        int horas_salida = horasSalida;
                        int min_salida = minSalida;

                        //CALCULOS
                        if (horas_entrada == horas_salida && min_entrada <= min_salida) {

                            int total_horas = (horas_entrada - horas_salida);
                            int total_min = (min_salida - min_entrada);

                            int hr = total_horas;
                            int mn = total_min;
                            String sr = (hr + ":" + mn);

                            total = sr;
                        }
                        if (horas_entrada > horas_salida && min_entrada > min_salida) {

                            int total_horas_entrada = (24 - horas_entrada);
                            int total_min_entrada = (60 - min_entrada);
                            int total_horas = (horas_salida + total_horas_entrada - 1);
                            int total_min = (total_min_entrada + min_salida);

                            int hr = total_horas;
                            int mn = total_min;
                            String sr = (hr + ":" + mn);

                            total = sr;
                        }
                        if (horas_entrada > horas_salida && min_entrada == min_salida) {

                            int total_horas_entrada = (24 - horas_entrada);
                            int total_horas = (total_horas_entrada + horas_salida);
                            int total_min = (min_entrada - min_salida);

                            int hr = total_horas;
                            int mn = total_min;
                            String sr = (hr + ":" + mn);

                            total = sr;
                        }
                        if (horas_entrada > horas_salida && min_entrada < min_salida) {

                            int total_horas_entrada = (24 - horas_entrada);
                            int total_horas = (total_horas_entrada + horas_salida);
                            int total_min_salida = (60 - min_salida);
                            int total_min_entrada = (60 - min_entrada);
                            int total_min = (total_min_entrada - total_min_salida);

                            int hr = total_horas;
                            int mn = total_min;
                            String sr = (hr + ":" + mn);

                            total = sr;
                        }
                        if (horas_entrada < horas_salida && min_entrada == min_salida) {

                            int total_horas_entrada = (24 - horas_entrada);
                            int total_horas_salida = (24 - horas_salida);
                            int total_horas = (total_horas_entrada - total_horas_salida);
                            int total_min = (min_entrada - min_salida);

                            int hr = total_horas;
                            int mn = total_min;
                            String sr = (hr + ":" + mn);

                            total = sr;
                        }
                        if (horas_entrada < horas_salida && min_entrada < min_salida) {

                            int total_horas_entrada = (24 - horas_entrada);
                            int total_horas_salida = (24 - horas_salida);
                            int total_horas = (total_horas_entrada - total_horas_salida);
                            int total_min_salida = (60 - min_salida);
                            int total_min_entrada = (60 - min_entrada);
                            int total_min = (total_min_entrada - total_min_salida);

                            int hr = total_horas;
                            int mn = total_min;
                            String sr = (hr + ":" + mn);

                            total = sr;
                        }
                        if (horas_entrada < horas_salida && min_entrada > min_salida) {

                            int total_horas_entrada = (24 - horas_entrada);
                            int total_horas_salida = (24 - horas_salida);
                            int total_horas = (total_horas_entrada - total_horas_salida - 1);
                            int total_min_entrada = (60 - min_entrada);
                            int total_min = (total_min_entrada + min_salida);

                            int hr = total_horas;
                            int mn = total_min;
                            String sr = (hr + ":" + mn);

                            total = sr;
                        }
                        String consulta = "UPDATE jornada SET horasalida = ?, total = ? WHERE codicard = ?";
                        PreparedStatement preparedStatement = controladores.Conexion.getconexion().prepareStatement(consulta);
                        preparedStatement.setString(1, horasalida);
                        preparedStatement.setString(2, total);
                        preparedStatement.setString(3, datoCodicard);

                        preparedStatement.executeUpdate();
                        preparedStatement.close();

                        Jornada nuevaJornada = new Jornada(rsHorasalida.getString("dni"), rsHorasalida.getString("nom"), rsHorasalida.getString("apellido"), rsHorasalida.getString("horaentrada"), horasalida, total, rsHorasalida.getString("fecha"), rsHorasalida.getString("codicard"));
                        jornada.add(nuevaJornada);
                    }
                } catch (SQLException e) {
                    Errores error = new Errores();
                    String errorUpdateJornada = error.errorUpdateJornada();
                    System.out.println(errorUpdateJornada);
                    outObjeto = new ObjectOutputStream(client.getOutputStream());
                    outObjeto.writeObject(errorUpdateJornada);
                    outObjeto.flush();
                }
                return jornada;
            }
        } catch (SQLException e) {
            Errores error = new Errores();
            String erroCodicard = error.erroCodicard();
            System.out.println(erroCodicard);
            outObjeto = new ObjectOutputStream(client.getOutputStream());
            outObjeto.writeObject(erroCodicard);
            outObjeto.flush();
        }
        return jornada;
    }
}
