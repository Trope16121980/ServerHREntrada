package testInsert;

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
public class TestInsert {

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

            String palabra = codigo + ",3," + empresaTabla + ",nom,Puleva," + orden;
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();
            //leemos el último mensaje  que nos envía el server para poder cerrar correctamente
            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }

    @Test
    public void test2_insertEmpresa() throws ClassNotFoundException {
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

            String palabra = codigo + ",1," + empresaTabla + ",nom,Puleva,address,Oviedo,telephon,+34933568956," + orden;
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();
            //leemos el último mensaje  que nos envía el server para poder cerrar correctamente
            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }

    @Test
    public void test3_insertEmpresaError() throws ClassNotFoundException {
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

            String palabra = codigo + ",1," + empresaTabla + ",nom,Puleva,address,Oviedo,telephon,+34933568956," + orden;
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();
            //leemos el último mensaje  que nos envía el server para poder cerrar correctamente
            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }

    @Test
    public void test4_deleteEmpleadoGustavo() throws ClassNotFoundException {
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

            String palabra = codigo + ",3," + empleadoTabla + ",dni,12345678D," + orden;
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();
            //leemos el último mensaje  que nos envía el server para poder cerrar correctamente
            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }

    @Test
    public void test5_insertEmpleadoPedro() throws ClassNotFoundException {
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

            String palabra = codigo + ",1," + empleadoTabla + ",dni,159845854C,nom,Pedro,apellido,Varela,nomempresa,Toyota,departament,Informatica,codicard,65,mail,pedro.varela@gmail.com,telephon,856958654," + orden;
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();
            //leemos el último mensaje  que nos envía el server para poder cerrar correctamente
            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }

    @Test
    public void test6_insertEmpleadoGustavo() throws ClassNotFoundException {
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

            String palabra = codigo + ",1," + empleadoTabla + ",dni,12345678D,nom,Gustavo,apellido,Senorans Varela,nomempresa,Toyota,departament,Informatica,codicard,1234645,mail,gustavo.senorans@gmail.com,telephon,933654485," + orden;
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();
            //leemos el último mensaje  que nos envía el server para poder cerrar correctamente
            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }

    @Test
    public void test7_insertEmpleadoErroDni() throws ClassNotFoundException {
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

            String palabra = codigo + ",1," + empleadoTabla + ",dni,12345678D,nom,Gustavo,apellido,Senorans Varela,nomempresa,Toyota,departament,Informatica,codicard,1234645,mail,gustavo.senorans@gmail.com,telephon,933654485," + orden;
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();
            //leemos el último mensaje  que nos envía el server para poder cerrar correctamente
            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }

    @Test
    public void test8_insertEmpleadoErrorEmpresa() throws ClassNotFoundException {
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

            String palabra = codigo + ",1," + empleadoTabla + ",dni,12345678D,nom,Gustavo,apellido,Senorans Varela,nomempresa,error,departament,Informatica,codicard,1234645,mail,gustavo.senorans@gmail.com,telephon,933654485," + orden;
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();
            //leemos el último mensaje  que nos envía el server para poder cerrar correctamente
            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }

    @Test
    public void test9_insertEmpleadoErrorCodicard() throws ClassNotFoundException {
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

            String palabra = codigo + ",1," + empleadoTabla + ",dni,12345678D,nom,Gustavo,apellido,Senorans Varela,nomempresa,Toyota,departament,Informatica,codicard,1,mail,gustavo.senorans@gmail.com,telephon,933654485," + orden;
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();
            //leemos el último mensaje  que nos envía el server para poder cerrar correctamente
            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }

    @Test
    public void test10_deleteUsers() throws ClassNotFoundException {
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

            String palabra = codigo + ",3," + usersTabla + ",dni,12345678D," + orden;
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();
            //leemos el último mensaje  que nos envía el server para poder cerrar correctamente
            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }

    @Test
    public void test11_insertUsers() throws ClassNotFoundException {
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

            String palabra = codigo + ",1," + usersTabla + ",login,gsv,pass,gsv,numtipe,0,dni,12345678D," + orden;
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();
            //leemos el último mensaje  que nos envía el server para poder cerrar correctamente
            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }

    @Test
    public void test12_insertUsersErrorDniNotExit() throws ClassNotFoundException {
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

            String palabra = codigo + ",1," + usersTabla + ",login,gsv,pass,gsv,numtipe,0,dni,error," + orden;
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();
            //leemos el último mensaje  que nos envía el server para poder cerrar correctamente
            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }

    @Test
    public void test13_insertUsersErrorDniExit() throws ClassNotFoundException {
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

            String palabra = codigo + ",1," + usersTabla + ",login,gsv,pass,gsv,numtipe,0,dni,12345678D," + orden;
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();
            //leemos el último mensaje  que nos envía el server para poder cerrar correctamente
            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }

    @Test
    public void test14_insertUsersErrorlogin() throws ClassNotFoundException {
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

            String palabra = codigo + ",1," + usersTabla + ",login,gsv,pass,prueba,numtipe,0,dni,159845854C," + orden;
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();
            //leemos el último mensaje  que nos envía el server para poder cerrar correctamente
            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }

    @Test
    public void test15_insertUsersErrorNumtipe() throws ClassNotFoundException {
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

            String palabra = codigo + ",1," + usersTabla + ",login,pedro,pass,pedro,numtipe,25,dni,159845854C," + orden;
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();
            //leemos el último mensaje  que nos envía el server para poder cerrar correctamente
            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }

    @Test
    public void test16_insertJornadaDniGustavo() throws ClassNotFoundException {
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

            String palabra = codigo + ",1," + jornadaTabla + ",dni,12345678D," + orden;
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();
            //leemos el último mensaje  que nos envía el server para poder cerrar correctamente
            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }

    @Test
    public void test17_insertJornadaCodicardPedro() throws ClassNotFoundException {
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

            String palabra = codigo + ",1," + jornadaTabla + ",codicard,65," + orden;
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();
            //leemos el último mensaje  que nos envía el server para poder cerrar correctamente
            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }

    @Test
    public void test18_insertJornadaErrorDni() throws ClassNotFoundException {
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

            String palabra = codigo + ",1," + jornadaTabla + ",dni,error," + orden;
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();
            //leemos el último mensaje  que nos envía el server para poder cerrar correctamente
            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }
    
    @Test
    public void test19_insertJornadaErrorJornadaIniciada() throws ClassNotFoundException {
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

            String palabra = codigo + ",1," + jornadaTabla + ",dni,12345678D," + orden;
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            escriptor.write("exit");
            escriptor.newLine();
            escriptor.flush();
            //leemos el último mensaje  que nos envía el server para poder cerrar correctamente
            mensajeServer = lector.readLine();
            socket.close();
        } catch (IOException e) {
        }
    }
}
