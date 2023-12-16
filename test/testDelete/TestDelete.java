package testDelete;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;
import javax.net.ssl.SSLSocketFactory;
import org.junit.Test;

/**
 *
 * @author gsenorans
 */
public class TestDelete {

    static String ip = "192.168.56.1";
    static int puerto = 8888;
    static String codigo = "";
    static String mensajeServer = "";
    static Socket socket;
    static BufferedReader lector;
    static BufferedWriter escriptor;
    static String empleadoTabla = "0";
    static String usersTabla = "1";
    static String empresaTabla = "2";
    static String jornadaTabla = "3";
    static String orden = "0";

    @Test
    public void test1_deleteEmpresa() throws ClassNotFoundException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            System.setProperty("javax.net.ssl.trustStore", "certificados/client/clientTrustedCerts.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "254535fd32_A");
            SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory
                    .getDefault();
            socket = clientFactory.createSocket(ip, puerto);
            lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            escriptor = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            mensajeServer = lector.readLine();
            escriptor.write("admin,admin");
            escriptor.newLine();
            escriptor.flush();
            codigo = lector.readLine();

            String palabra = codigo + ",3," + empresaTabla + ",nom,Toyota," + orden;
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();

            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }

    @Test
    public void test2_deleteEmpresaError() throws ClassNotFoundException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            System.setProperty("javax.net.ssl.trustStore", "certificados/client/clientTrustedCerts.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "254535fd32_A");
            SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory
                    .getDefault();
            socket = clientFactory.createSocket(ip, puerto);
            lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            escriptor = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            mensajeServer = lector.readLine();
            escriptor.write("admin,admin");
            escriptor.newLine();
            escriptor.flush();
            codigo = lector.readLine();

            String palabra = codigo + ",3," + empresaTabla + ",nom,error," + orden;
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();

            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }

//     @Test
//    public void deleteEmpresaRecuperacion() throws ClassNotFoundException {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//
//        try {
//            System.setProperty("javax.net.ssl.trustStore", "certificados/client/clientTrustedCerts.jks");
//            System.setProperty("javax.net.ssl.trustStorePassword", "254535fd32_A");
//            SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory
//                    .getDefault();
//            socket = clientFactory.createSocket(ip, puerto);
//            lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            escriptor = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//
//            mensajeServer = lector.readLine();
//            escriptor.write("admin,admin");
//            escriptor.newLine();
//            escriptor.flush();
//            codigo = lector.readLine();
//
//            String palabra = codigo + ",1," + empresaTabla + ",nom,Toyota,address,El Prat,telephon,856859865," + orden;
//            escriptor.write(palabra);
//            escriptor.newLine();
//            escriptor.flush();
//
//            escriptor.write("exit");
//            escriptor.newLine();
//            escriptor.flush();
//            //leemos el último mensaje  que nos envía el server para poder cerrar correctamente
//            mensajeServer = lector.readLine();
//            socket.close();
//        } catch (IOException e) {
//        }
//    }
    @Test
    public void test3_deleteEmpleado() throws ClassNotFoundException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            System.setProperty("javax.net.ssl.trustStore", "certificados/client/clientTrustedCerts.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "254535fd32_A");
            SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory
                    .getDefault();
            socket = clientFactory.createSocket(ip, puerto);
            lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            escriptor = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            mensajeServer = lector.readLine();
            escriptor.write("admin,admin");
            escriptor.newLine();
            escriptor.flush();
            codigo = lector.readLine();

            String palabra = codigo + ",3," + empleadoTabla + ",dni,84574589A," + orden;
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();

            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }

    @Test
    public void test4_deleteEmpleadoError() throws ClassNotFoundException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            System.setProperty("javax.net.ssl.trustStore", "certificados/client/clientTrustedCerts.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "254535fd32_A");
            SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory
                    .getDefault();
            socket = clientFactory.createSocket(ip, puerto);
            lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            escriptor = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            mensajeServer = lector.readLine();
            escriptor.write("admin,admin");
            escriptor.newLine();
            escriptor.flush();
            codigo = lector.readLine();

            String palabra = codigo + ",3," + empleadoTabla + ",dni,error," + orden;
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();

            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }

//    @Test
//    public void deleteEmpleadoRecuperacion() throws ClassNotFoundException {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//
//        try {
//            System.setProperty("javax.net.ssl.trustStore", "certificados/client/clientTrustedCerts.jks");
//            System.setProperty("javax.net.ssl.trustStorePassword", "254535fd32_A");
//            SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory
//                    .getDefault();
//            socket = clientFactory.createSocket(ip, puerto);
//            lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            escriptor = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//
//            mensajeServer = lector.readLine();
//            escriptor.write("admin,admin");
//            escriptor.newLine();
//            escriptor.flush();
//            codigo = lector.readLine();
//
//            String palabra = codigo + ",1," + empleadoTabla + ",dni,159845854C,nom,Juan,apellido,Perez,nomempresa,Toyota,departament,Mecanico,codicard,3,mail,Juan.Toyota@gmail.com,telephon,852146985," + orden;
//            escriptor.write(palabra);
//            escriptor.newLine();
//            escriptor.flush();
//
//            escriptor.write("exit");
//            escriptor.newLine();
//            escriptor.flush();
//            //leemos el último mensaje  que nos envía el server para poder cerrar correctamente
//            mensajeServer = lector.readLine();
//            socket.close();
//        } catch (IOException e) {
//        }
//    }
    @Test
    public void test5_deleteUsers() throws ClassNotFoundException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            System.setProperty("javax.net.ssl.trustStore", "certificados/client/clientTrustedCerts.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "254535fd32_A");
            SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory
                    .getDefault();
            socket = clientFactory.createSocket(ip, puerto);
            lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            escriptor = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            mensajeServer = lector.readLine();
            escriptor.write("admin,admin");
            escriptor.newLine();
            escriptor.flush();
            codigo = lector.readLine();

            String palabra = codigo + ",3," + empleadoTabla + ",dni,12345679B," + orden;
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();

            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }

    @Test
    public void test6_deleteUsersError() throws ClassNotFoundException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            System.setProperty("javax.net.ssl.trustStore", "certificados/client/clientTrustedCerts.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "254535fd32_A");
            SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory
                    .getDefault();
            socket = clientFactory.createSocket(ip, puerto);
            lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            escriptor = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            mensajeServer = lector.readLine();
            escriptor.write("admin,admin");
            escriptor.newLine();
            escriptor.flush();
            codigo = lector.readLine();

            String palabra = codigo + ",3," + empleadoTabla + ",dni,error," + orden;
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();

            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }

//    @Test
//    public void deleteUsersRecuperacion() throws ClassNotFoundException {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//
//        try {
//            System.setProperty("javax.net.ssl.trustStore", "certificados/client/clientTrustedCerts.jks");
//            System.setProperty("javax.net.ssl.trustStorePassword", "254535fd32_A");
//            SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory
//                    .getDefault();
//            socket = clientFactory.createSocket(ip, puerto);
//            lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            escriptor = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//
//            mensajeServer = lector.readLine();
//            escriptor.write("admin,admin");
//            escriptor.newLine();
//            escriptor.flush();
//            codigo = lector.readLine();
//
//            String palabra = codigo + ",1," + usersTabla + ",login,user,pass,user,numtipe,1,dni,12345679B," + orden;
//            escriptor.write(palabra);
//            escriptor.newLine();
//            escriptor.flush();
//
//            escriptor.write("exit");
//            escriptor.newLine();
//            escriptor.flush();
//            //leemos el último mensaje  que nos envía el server para poder cerrar correctamente
//            mensajeServer = lector.readLine();
//            socket.close();
//        } catch (IOException e) {
//        }
//    }
    
    @Test
    public void test6_deleteJornada() throws ClassNotFoundException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            System.setProperty("javax.net.ssl.trustStore", "certificados/client/clientTrustedCerts.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "254535fd32_A");
            SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory
                    .getDefault();
            socket = clientFactory.createSocket(ip, puerto);
            lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            escriptor = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            mensajeServer = lector.readLine();
            escriptor.write("admin,admin");
            escriptor.newLine();
            escriptor.flush();
            codigo = lector.readLine();

            String palabra = codigo + ",3," + jornadaTabla + ",dni,84574589A,fecha,2023/11/26," + orden;
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();

            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }

    @Test
    public void test7_deleteJornadaErrorDni() throws ClassNotFoundException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            System.setProperty("javax.net.ssl.trustStore", "certificados/client/clientTrustedCerts.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "254535fd32_A");
            SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory
                    .getDefault();
            socket = clientFactory.createSocket(ip, puerto);
            lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            escriptor = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            mensajeServer = lector.readLine();
            escriptor.write("admin,admin");
            escriptor.newLine();
            escriptor.flush();
            codigo = lector.readLine();

            String palabra = codigo + ",3," + jornadaTabla + ",dni,error,fecha,2023/11/26," + orden;
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();

            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }
    
    @Test
    public void test8_deleteJornadaErrorFecha() throws ClassNotFoundException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            System.setProperty("javax.net.ssl.trustStore", "certificados/client/clientTrustedCerts.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "254535fd32_A");
            SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory
                    .getDefault();
            socket = clientFactory.createSocket(ip, puerto);
            lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            escriptor = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            mensajeServer = lector.readLine();
            escriptor.write("admin,admin");
            escriptor.newLine();
            escriptor.flush();
            codigo = lector.readLine();

            String palabra = codigo + ",3," + jornadaTabla + ",dni,84574589A,fecha,error," + orden;
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();

            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }

//     @Test
//    public void deleteJornadaRecuperacion() throws ClassNotFoundException {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//
//        try {
//            System.setProperty("javax.net.ssl.trustStore", "certificados/client/clientTrustedCerts.jks");
//            System.setProperty("javax.net.ssl.trustStorePassword", "254535fd32_A");
//            SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory
//                    .getDefault();
//            socket = clientFactory.createSocket(ip, puerto);
//            lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            escriptor = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//
//            mensajeServer = lector.readLine();
//            escriptor.write("admin,admin");
//            escriptor.newLine();
//            escriptor.flush();
//            codigo = lector.readLine();
//
//            String palabra = codigo + ",1," + jornadaTabla + ",dni,84574589A," + orden;
//            escriptor.write(palabra);
//            escriptor.newLine();
//            escriptor.flush();
//
//            escriptor.write("exit");
//            escriptor.newLine();
//            escriptor.flush();
//
//            mensajeServer = lector.readLine();
//            socket.close();
//        } catch (IOException e) {
//        }
//    }
}
