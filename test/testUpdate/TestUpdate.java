package testUpdate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author gsenorans
 */
public class TestUpdate {

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
    public void test1_updateEmpresaPascual() throws ClassNotFoundException {
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

            String palabra = codigo + ",2," + empresaTabla + ",nomNuevo,Pascual,addressNuevo,Lugo,telephonNuevo,+34983652847,nom,Toyota," + orden;
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
    public void test2_updateEmpresaAToyota() throws ClassNotFoundException {
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

            String palabra = codigo + ",2," + empresaTabla + ",nomNuevo,Toyota,addressNuevo,Lugo,telephonNuevo,+34983652847,nom,Pascual," + orden;
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
    public void test3_updateEmpresaAddress() throws ClassNotFoundException {
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

            String palabra = codigo + ",2," + empresaTabla + ",nomNuevo,Toyota,addressNuevo,El Prat,telephonNuevo,+34983652847,nom,Toyota," + orden;
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
    public void test4_updateEmpresaTelephon() throws ClassNotFoundException {
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

            String palabra = codigo + ",2," + empresaTabla + ",nomNuevo,Toyota,addressNuevo,El Prat,telephonNuevo,856859865,nom,Toyota," + orden;
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
    public void test5_updateEmpresaErrorNon() throws ClassNotFoundException {
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

            String palabra = codigo + ",2," + empresaTabla + ",nomNuevo,Toyota,addressNuevo,El Prat,telephonNuevo,856859865,nom,Error," + orden;
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
    public void updateEmpresaRecuperacion() throws ClassNotFoundException {
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

            String palabra = codigo + ",2," + empresaTabla + ",nomNuevo,Toyota,addressNuevo,El Prat,telephonNuevo,856859865,nom,Pascual," + orden;
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
    public void test6_updateEmpleadoCompletoPorDni() throws ClassNotFoundException {
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

            String palabra = codigo + ",2," + empleadoTabla + ",dniNuevo,12365487A,nomNuevo,Gustavo,apellidoNuevo,Senorans,nomempresaNuevo,Toyota,departamentNuevo,Informatica,codicardNuevo,125,mailNuevo,gustavo.Toyota@gmail.com,telephonNuevo,658956856,dni,84574589A," + orden;
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
    public void test7_updateEmpleadoNom() throws ClassNotFoundException {
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

            String palabra = codigo + ",2," + empleadoTabla + ",dniNuevo,12365487A,nomNuevo,Juan,apellidoNuevo,Senorans,nomempresaNuevo,Toyota,departamentNuevo,Informatica,codicardNuevo,125,mailNuevo,gustavo.Toyota@gmail.com,telephonNuevo,658956856,dni,12365487A," + orden;
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
    public void test8_updateEmpleadoApellido() throws ClassNotFoundException {
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

            String palabra = codigo + ",2," + empleadoTabla + ",dniNuevo,12365487A,nomNuevo,Juan,apellidoNuevo,Perez,nomempresaNuevo,Toyota,departamentNuevo,Informatica,codicardNuevo,125,mailNuevo,gustavo.Toyota@gmail.com,telephonNuevo,658956856,dni,12365487A," + orden;
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
    public void test9_updateEmpleadoEmpresa() throws ClassNotFoundException {
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

            String palabra = codigo + ",2," + empleadoTabla + ",dniNuevo,12365487A,nomNuevo,Juan,apellidoNuevo,Perez,nomempresaNuevo,Pascual,departamentNuevo,Informatica,codicardNuevo,125,mailNuevo,gustavo.Toyota@gmail.com,telephonNuevo,658956856,dni,12365487A," + orden;
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
    public void test10_updateEmpleadoDepartament() throws ClassNotFoundException {
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

            String palabra = codigo + ",2," + empleadoTabla + ",dniNuevo,12365487A,nomNuevo,Juan,apellidoNuevo,Perez,nomempresaNuevo,Pascual,departamentNuevo,Mantenimiento,codicardNuevo,125,mailNuevo,gustavo.Toyota@gmail.com,telephonNuevo,658956856,dni,12365487A," + orden;
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
    public void test11_updateEmpleadoCodicard() throws ClassNotFoundException {
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

            String palabra = codigo + ",2," + empleadoTabla + ",dniNuevo,12365487A,nomNuevo,Juan,apellidoNuevo,Perez,nomempresaNuevo,Pascual,departamentNuevo,Mantenimiento,codicardNuevo,1540,mailNuevo,gustavo.Toyota@gmail.com,telephonNuevo,658956856,dni,12365487A," + orden;
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
    public void test12_updateEmpleadoMail() throws ClassNotFoundException {
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

            String palabra = codigo + ",2," + empleadoTabla + ",dniNuevo,12365487A,nomNuevo,Juan,apellidoNuevo,Perez,nomempresaNuevo,Pascual,departamentNuevo,Mantenimiento,codicardNuevo,1540,mailNuevo,juan.Pascual@gmail.com,telephonNuevo,658956856,dni,12365487A," + orden;
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
    public void test13_updateEmpleadoTelephon() throws ClassNotFoundException {
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

            String palabra = codigo + ",2," + empleadoTabla + ",dniNuevo,12365487A,nomNuevo,Juan,apellidoNuevo,Perez,nomempresaNuevo,Pascual,departamentNuevo,Mantenimiento,codicardNuevo,1540,mailNuevo,juan.Pascual@gmail.com,telephonNuevo,852146985,dni,12365487A," + orden;
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
    public void test14_updateEmpleadoErrorDniNoExiste() throws ClassNotFoundException {
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

            String palabra = codigo + ",2," + empleadoTabla + ",dniNuevo,12345679B,nomNuevo,Juan,apellidoNuevo,Perez,nomempresaNuevo,Pascual,departamentNuevo,Mantenimiento,codicardNuevo,1540,mailNuevo,juan.Pascual@gmail.com,telephonNuevo,852146985,dni,error," + orden;
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
    public void test15_updateEmpleadoErrorDniRepetido() throws ClassNotFoundException {
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

            String palabra = codigo + ",2," + empleadoTabla + ",dniNuevo,12345679B,nomNuevo,Juan,apellidoNuevo,Perez,nomempresaNuevo,Pascual,departamentNuevo,Mantenimiento,codicardNuevo,1540,mailNuevo,juan.Pascual@gmail.com,telephonNuevo,852146985,dni,12365487A," + orden;
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
    public void test16_updateEmpleadoErrorEmpresa() throws ClassNotFoundException {
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

            String palabra = codigo + ",2," + empleadoTabla + ",dniNuevo,12365487A,nomNuevo,Juan,apellidoNuevo,Perez,nomempresaNuevo,Error,departamentNuevo,Mantenimiento,codicardNuevo,1540,mailNuevo,juan.Pascual@gmail.com,telephonNuevo,852146985,dni,12365487A," + orden;
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
    public void test17_updateEmpleadoErrorCodicard() throws ClassNotFoundException {
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

            String palabra = codigo + ",2," + empleadoTabla + ",dniNuevo,12365487A,nomNuevo,Juan,apellidoNuevo,Perez,nomempresaNuevo,Pascual,departamentNuevo,Mantenimiento,codicardNuevo,2,mailNuevo,juan.Pascual@gmail.com,telephonNuevo,852146985,dni,12365487A," + orden;
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
    public void updateEmpleadoRecuperacion() throws ClassNotFoundException {
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

            String palabra = codigo + ",2," + empleadoTabla + ",dniNuevo,84574589A,nomNuevo,Juan,apellidoNuevo,Perez,nomempresaNuevo,Toyota,departamentNuevo,Mecanico,codicardNuevo,3,mailNuevo,Juan.Toyota@gmail.com,telephonNuevo,852146985,dni,84574589A," + orden;
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
    public void test18_updateUsersPass() throws ClassNotFoundException {
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

            String palabra = codigo + ",2," + usersTabla + ",passNuevo,prueba,numtipeNuevo,1,login,user," + orden;
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
    public void test19_updateUsersPass() throws ClassNotFoundException {
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

            String palabra = codigo + ",2," + usersTabla + ",passNuevo,prueba,numtipeNuevo,0,login,user," + orden;
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
    public void test20_updateUsersErrorLogin() throws ClassNotFoundException {
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

            String palabra = codigo + ",2," + usersTabla + ",passNuevo,prueba,numtipeNuevo,0,login,error," + orden;
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
    public void test21_updateUsersErrorNumtipe() throws ClassNotFoundException {
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

            String palabra = codigo + ",2," + usersTabla + ",passNuevo,prueba,numtipeNuevo,2,login,prueba," + orden;
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
    public void updateUsersRecuperacion() throws ClassNotFoundException {
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

            String palabra = codigo + ",2," + usersTabla + ",passNuevo,user,numtipeNuevo,1,login,user," + orden;
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
    public void test22_updateJornadaDni() throws ClassNotFoundException {
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

            String palabra = codigo + ",2," + jornadaTabla + ",dni,12345679B," + orden;
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
    public void test23_updateJornadaCodicard() throws ClassNotFoundException {
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

            String palabra = codigo + ",2," + jornadaTabla + ",codicard,1," + orden;
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
    public void updateJornadaRecuperacion() throws ClassNotFoundException {
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
            escriptor.write("user,user");
            escriptor.newLine();
            escriptor.flush();
            codigo = lector.readLine();

            String palabra = codigo + ",2," + jornadaTabla + ",dni,84574589A," + orden;
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
}
