package testLogin;

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
import static org.junit.Assert.assertEquals;

public class TestThreadllogin {

    String ip = "192.168.56.1";
    int puerto = 8888;

    @Test
    public void test1_testLoginAdmin() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Socket socket = null;
        try {
            System.setProperty("javax.net.ssl.trustStore", "certificados/client/clientTrustedCerts.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "254535fd32_A");
            //Creamos el socket
            SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory
                    .getDefault();
            socket = clientFactory.createSocket(ip, puerto);
            BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter escriptor = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String mensajeServer = lector.readLine();
            escriptor.write("admin,admin");
            escriptor.newLine();
            escriptor.flush();
            mensajeServer = lector.readLine();
            assertEquals('A', mensajeServer.charAt(0));
            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();
            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2_testLoginUser() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Socket socket = null;
        try {
            System.setProperty("javax.net.ssl.trustStore", "certificados/client/clientTrustedCerts.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "254535fd32_A");
            SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory
                    .getDefault();
            socket = clientFactory.createSocket(ip, puerto);
            BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter escriptor = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String mensajeServer = lector.readLine();
            escriptor.write("user,user");
            escriptor.newLine();
            escriptor.flush();
            mensajeServer = lector.readLine();
            assertEquals('U', mensajeServer.charAt(0));
            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();
            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }

    @Test
    public void test3_testLoginError() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Socket socket = null;
        try {
            System.setProperty("javax.net.ssl.trustStore", "certificados/client/clientTrustedCerts.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "254535fd32_A");
            SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory
                    .getDefault();
            socket = clientFactory.createSocket(ip, puerto);
            BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter escriptor = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String mensajeServer = lector.readLine();
            escriptor.write("error,user");
            escriptor.newLine();
            escriptor.flush();
            mensajeServer = lector.readLine();
            assertEquals('-', mensajeServer.charAt(0));
            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();
            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }

    }

    @Test
    public void test4_testPassError() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Socket socket = null;
        try {
            System.setProperty("javax.net.ssl.trustStore", "certificados/client/clientTrustedCerts.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "254535fd32_A");
            SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory
                    .getDefault();
            socket = clientFactory.createSocket(ip, puerto);
            BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter escriptor = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String mensajeServer = lector.readLine();
            escriptor.write("admin,error");
            escriptor.newLine();
            escriptor.flush();
            mensajeServer = lector.readLine();
            assertEquals('-', mensajeServer.charAt(0));
            //cerramos sesión
            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();
            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }

    @Test
    public void test5_testLoginRepetido() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            System.setProperty("javax.net.ssl.trustStore", "certificados/client/clientTrustedCerts.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "254535fd32_A");
            SSLSocketFactory clientFactory1 = (SSLSocketFactory) SSLSocketFactory.getDefault();
            Socket socket1 = clientFactory1.createSocket(ip, puerto);
            try (BufferedReader lector1 = new BufferedReader(new InputStreamReader(socket1.getInputStream())); BufferedWriter escriptor1 = new BufferedWriter(new OutputStreamWriter(socket1.getOutputStream()))) {

                String mensajeServer1 = lector1.readLine();
                escriptor1.write("admin,admin");
                escriptor1.newLine();
                escriptor1.flush();
                mensajeServer1 = lector1.readLine();
                assertEquals('A', mensajeServer1.charAt(0));
                System.setProperty("javax.net.ssl.trustStore", "certificados/client/clientTrustedCerts.jks");
                System.setProperty("javax.net.ssl.trustStorePassword", "254535fd32_A");
                SSLSocketFactory clientFactory2 = (SSLSocketFactory) SSLSocketFactory.getDefault();
                Socket socket2 = clientFactory2.createSocket(ip, puerto);
                try (BufferedReader lector2 = new BufferedReader(new InputStreamReader(socket2.getInputStream())); BufferedWriter escriptor2 = new BufferedWriter(new OutputStreamWriter(socket2.getOutputStream()))) {

                    String mensajeServer2 = lector2.readLine();
                    escriptor2.write("admin,admin");
                    escriptor2.newLine();
                    escriptor2.flush();
                    mensajeServer2 = lector2.readLine();
                    assertEquals('-', mensajeServer2.charAt(0));
                    mensajeServer2 = lector2.readLine();
                } catch (IOException e) {
                } finally {
                    socket2.close();
                }

                escriptor1.write("exit");
                escriptor1.newLine();
                escriptor1.flush();
                mensajeServer1 = lector1.readLine();
            } catch (IOException e) {
            }
        } catch (IOException e) {
        }
    }
}
