import java.io.*;
import java.net.Socket;

public class Broker implements Closeable {
    public static Socket socket;
    public static ObjectOutputStream writer;
    public static ObjectInputStream reader;
    public static OutputStream bytewriter;
    public static InputStream bytereader;
    public static BufferedWriter strwriter;
    public static BufferedReader strreaderr;
    public Broker(String ip,int port) {
        try {
            socket = new Socket(ip, port);
           writer = new ObjectOutputStream(socket.getOutputStream());
            reader = new ObjectInputStream(socket.getInputStream());
            bytewriter = socket.getOutputStream();
            bytereader = socket.getInputStream();
            strwriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            strreaderr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void close() throws IOException
    {
        System.out.println("5");
        reader.close();
        writer.close();
        socket.close();
    }
}


