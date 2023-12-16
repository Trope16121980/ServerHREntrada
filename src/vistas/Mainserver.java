package vistas;

import fecha.Fechas;
import vistas.WindowServer;
import controladores.Threadllogin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocketFactory;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class Mainserver {

    /**
     * Metodo de inicio del servidor genera la llamada al hashmap, al metodo
     * thread para el inicio del socket con el cliente
     */
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket socket;
        Fechas fecha = new Fechas();
        String nombreBase = "prueba";
        String extension = ".txt";
        int contador = 1;
        File archivo;
        FileOutputStream fileOutputStream;
        int i = 0;

        System.setProperty("javax.net.ssl.keyStore", "certificados/server/serverKey.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "1225atcdF_B");
        SSLServerSocketFactory serverFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

        try {
            WindowServer WindowServer = new WindowServer();
            WindowServer.setVisible(true);

            Path folderPath = Paths.get("file");
            if (!Files.exists(folderPath)) {
                Files.createDirectories(folderPath);
            }

            do {
                String nombreArchivo = nombreBase + contador + extension;
                archivo = new File("file/" + nombreArchivo);
                contador++;
            } while (archivo.exists());
            archivo.createNewFile();

            fileOutputStream = new FileOutputStream(archivo);

            OutputStream outputStream = new OutputStream() {
                @Override
                public void write(int b) throws IOException {
                    WindowServer.appendText(String.valueOf((char) b));
                    fileOutputStream.write(b);
                }
            };
            PrintStream printStream = new PrintStream(outputStream);
            System.setOut(printStream);

            try {
                server = serverFactory.createServerSocket(8888, 1000);
            } catch (IOException ex) {
                System.out.println("Error al crear el socket del server\n" + ex);

            }
            HashMap<String, String> logins = new HashMap<String, String>();
            System.out.println(fecha.fecha_hora());
            System.out.println("\nEsperando cliente...");
            boolean inicio = true;
            while (inicio) {
                socket = server.accept();
                i++;
                new Threadllogin(socket, logins).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(WindowServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
