import Movies.Movies;
import User.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Broker implements Closeable{
    public static Socket socket;
    public static ObjectOutputStream writer;
    public static ObjectInputStream reader;
    public static OutputStream bytewriter;
    public static InputStream bytereader;
    public static BufferedWriter strwriter;
    public static BufferedReader strreaderr;
    public Broker(ServerSocket s){
        try {
            socket = s.accept();
           reader = new ObjectInputStream(socket.getInputStream());
            writer = new ObjectOutputStream(socket.getOutputStream());
            bytewriter = socket.getOutputStream();
            bytereader = socket.getInputStream();
            strwriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            strreaderr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void RequestHandler() throws IOException {
        System.out.println("Client Connected");
        while(true)
        {
            try
            {
                DBHandler worker = new DBHandler();
                int choose=bytereader.read();
               if(choose==1) {
                        User recvUser = (User) reader.readObject();

                       int i= worker.registr(recvUser);
                       bytewriter.write(i);
                    }


                if(choose==2) {
                       User recvUser=(User)reader.readObject();
                        ResultSet result = worker.authoriz(recvUser);
                        int counter=0;
                        try {
                            while (result.next()) {
                               counter++;
                                recvUser.id = result.getInt(1);
                                recvUser.Locked = result.getInt(4);
                                recvUser.Balance = result.getString(6);
                                recvUser.FIRSTNAME = result.getString(7);
                                recvUser.SECONDNAME = result.getString(8);
                                recvUser.Admincode = result.getInt(5);
                            }
                            if(counter==1)
                            {
                                Broker.writer.writeObject(recvUser);
                            }
                            else{
                                recvUser.id=0;
                                Broker.writer.writeObject(recvUser);
                                System.out.println("no such acc");
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }


                    }
                if(choose==3) {
                    int id=  bytereader.read();
                     int size=bytereader.read();
                     byte [] bt=new byte[size];
                     bytereader.read(bt);
                     String pass=new String(bt,"UTF-8");
                     System.out.println(pass+"  "+id);
                     worker.ChangePass(pass,id);
                    }
                if(choose==4) {
                    int id=  bytereader.read();
                    int size=bytereader.read();
                    byte [] bt=new byte[size];
                    bytereader.read(bt);
                    String bal=new String(bt,"UTF-8");
                    worker.ChangeBalance(bal,id);
                    }
                if(choose==5) {
                    ArrayList<Movies> list=new ArrayList<Movies>();
                    list=worker.getDAta();
                    writer.writeObject(list);
                    }
                if(choose==6) {

                    User recvus=(User) reader.readObject();
                    Movies recmov=(Movies) reader.readObject();
                    worker.setbuy(recvus,recmov);
                    worker.ChangeBalance(recvus.Balance,recvus.id);
                    worker.changeCountOfPlace(recmov);
                }
                if(choose==7) {

                    Movies recmov=(Movies) reader.readObject();
                   recmov.countofpl=worker.getcount(recmov.MovieId);
                   writer.writeObject(recmov);
                }
                if(choose==8) {
                    User recvus=(User) reader.readObject();
                    ArrayList<Movies> list=new ArrayList<Movies>();
                    list= worker.getActiveTicket(recvus);
                    writer.writeObject(list);
                }

                if(choose==9) {
                    Movies recvmov=(Movies) reader.readObject();
                    User user=(User)reader.readObject();
                    worker.returnTick(recvmov,user);
                }
                if(choose==10) {
                    ArrayList<User> list=new ArrayList<User>();
                    list=worker.getACC();
                    writer.writeObject(list);
                }
                if(choose==11) {
                    Movies movies=(Movies)reader.readObject();
                     worker.addfilm(movies);
                }
                if(choose==12) {
                    Movies movies=(Movies)reader.readObject();
                    worker.redfilm(movies);
                }
                if(choose==13) {
                    Movies movies=(Movies)reader.readObject();
                    worker.removefilm(movies);
                }
                if(choose==14) {
                User user=(User)reader.readObject();
                worker.ChngAdmAndLock(user);
            }
                if(choose==15) {
                User user=(User)reader.readObject();
                worker.DelACC(user);
            }

            } catch(ClassNotFoundException e){
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public void close() throws IOException
    {
        reader.close();
        writer.close();
        socket.close();
    }
}
