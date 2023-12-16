package testSelect;

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
import static testSelect.TestSelect.codigo;
import static testSelect.TestSelect.ip;
import static testSelect.TestSelect.puerto;

/**
 *
 * @author gsenorans
 */
public class TestSelect {

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
    public void selectEmpresaCompleta() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + empresaTabla + ",0,0," + orden;
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
    public void selectEmpresaNom() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + empresaTabla + ",nom,Toyota," + orden;
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
    public void selectEmpresaAddress() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + empresaTabla + ",address,El Prat," + orden;
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
    public void selectEmpresaNomError() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + empresaTabla + ",nom,error," + orden;
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
    public void selectEmpresaAddressError() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + empresaTabla + ",address,error," + orden;
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
    public void selectEmpleadosCompleta() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + empleadoTabla + ",0,0," + orden;
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
    public void selectEmpleadosDni() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + empleadoTabla + ",dni,12345678A," + orden;
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
    public void selectEmpleadosNom() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + empleadoTabla + ",nom,Users," + orden;
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
    public void selectEmpleadosApellido() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + empleadoTabla + ",apellido,HREntrada," + orden;
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
    public void selectEmpleadosNomEmpresa() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + empleadoTabla + ",nomempresa,Toyota," + orden;
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
    public void selectEmpleadosDepartament() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + empleadoTabla + ",departament,Informatica," + orden;
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
    public void selectEmpleadosCodicard() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + empleadoTabla + ",codicard,3," + orden;
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
    public void selectEmpleadosMail() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + empleadoTabla + ",mail,Administrado.HREntrada@gmail.com," + orden;
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
    public void selectEmpleadosTelephon() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + empleadoTabla + ",telephon,933456789," + orden;
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
    public void selectEmpleadosNomApellido() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + empleadoTabla + ",nom,Juan,apellido,Perez," + orden;
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
    public void selectEmpleadosDniError() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + empleadoTabla + ",dni,error," + orden;
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
    public void selectEmpleadosNomError() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + empleadoTabla + ",nom,error," + orden;
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
    public void selectEmpleadosApellidoError() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + empleadoTabla + ",apellido,error," + orden;
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
    public void selectEmpleadosNomEmpresaError() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + empleadoTabla + ",nomempresa,error," + orden;
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
    public void selectEmpleadosDepartamentError() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + empleadoTabla + ",departament,error," + orden;
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
    public void selectEmpleadosCodicardError() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + empleadoTabla + ",codicard,error," + orden;
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
    public void selectEmpleadosMailError() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + empleadoTabla + ",mail,error," + orden;
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
    public void selectEmpleadosTelephonError() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + empleadoTabla + ",telephon,error," + orden;
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
    public void selectEmpleadosNomApellidoErrorNom() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + empleadoTabla + ",nom,error,apellido,Perez," + orden;
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
    public void selectEmpleadosNomApellidoErrorApellido() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + empleadoTabla + ",nom,Juan,apellido,error," + orden;
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
    public void selectUsersCompleto() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + usersTabla + ",0,0," + orden;
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
    public void selectUsersDni() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + usersTabla + ",dni,12345678A," + orden;
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
    public void selectUsersNumtipe() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + usersTabla + ",numtipe,1," + orden;
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
    public void selectUsersDniError() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + usersTabla + ",dni,error," + orden;
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
    public void selectUsersNumtipeError() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + usersTabla + ",numtipe,25," + orden;
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
    public void selectJornadaCompleto() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",0,0," + orden;
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
    public void selectJornadaDni() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",dni,12345678A," + orden;
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
    public void selectJornadaNom() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",nom,Juan," + orden;
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
    public void selectJornadaApellido() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",apellido,HREntrada," + orden;
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
    public void selectJornadaCodicard() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",codicard,1," + orden;
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
    public void selectJornadaFecha() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",fecha,2023/11/26," + orden;
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
    public void selectJornadaNomApellido() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",nom,Juan,apellido,Perez," + orden;
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
    public void selectJornadaDniFecha() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",dni,12345678A,fecha,2023/11/26," + orden;
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
    public void selectJornadaNomFecha() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",nom,Users,fecha,2023/11/26," + orden;
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
    public void selectJornadaApellidoFecha() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",apellido,Perez,fecha,2023/11/26," + orden;
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
    public void selectJornadaCodicardFecha() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",codicard,1,fecha,2023/11/26," + orden;
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
    public void selectJornadaNomApellidoFecha() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",nom,Juan,apellido,Perez,fecha,2023/11/26," + orden;
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
    public void selectJornadaDniError() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",dni,error," + orden;
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
    public void selectJornadaNomError() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",nom,error," + orden;
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
    public void selectJornadaApellidoError() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",apellido,error," + orden;
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
    public void selectJornadaCodicardError() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",codicard,75," + orden;
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
    public void selectJornadaFechaError() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",fecha,2024/11/26," + orden;
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
    public void selectJornadaNomApellidoErrorNom() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",nom,error,apellido,Perez," + orden;
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
    public void selectJornadaNomApellidoErrorApellido() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",nom,Juan,apellido,error," + orden;
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
    public void selectJornadaDniFechaErrorDni() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",dni,error,fecha,2023/11/26," + orden;
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
    public void selectJornadaDniFechaErrorFecha() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",dni,12345678A,fecha,2024/11/26," + orden;
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
    public void selectJornadaNomFechaErrorNom() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",nom,error,fecha,2023/11/26," + orden;
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
    public void selectJornadaNomFechaErrorFecha() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",nom,Juan,fecha,2024/11/26," + orden;
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
    public void selectJornadaApellidoFechaErrorApellido() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",apellido,error,fecha,2023/11/26," + orden;
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
    public void selectJornadaApellidoFechaErrorFecha() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",apellido,Perez,fecha,2024/11/26," + orden;
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
    public void selectJornadaCodicardFechaErrorCodicard() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",codicard,75,fecha,2023/11/26," + orden;
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
    public void selectJornadaCodicardFechaErrorFecha() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",codicard,1,fecha,2024/11/26," + orden;
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
    public void selectJornadaNomApellidoFechaErrorNom() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",nom,error,apellido,Perez,fecha,2023/11/26," + orden;
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
    public void selectJornadaNomApellidoFechaErrorApellido() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",nom,Juan,apellido,error,fecha,2023/11/26," + orden;
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
    public void selectJornadaNomApellidoFechaErrorFecha() throws ClassNotFoundException {
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

            String palabra = codigo + ",0," + jornadaTabla + ",nom,Juan,apellido,Perez,fecha,2024/11/26," + orden;
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
