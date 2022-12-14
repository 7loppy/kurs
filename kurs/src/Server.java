
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Server {
    public static void main(String[] args)throws IOException
    {
        try (ServerSocket server = new ServerSocket(8000))
        {
            System.out.println("hello server");
            while(true) {
                Broker broker = new Broker(server);
                new Thread(() -> {
                    try {
                        broker.RequestHandler();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        broker.close();
                    } catch (IOException e) { }

                }).start();
            }

        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

}


