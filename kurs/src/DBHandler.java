import Movies.Movies;
import User.User;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class DBHandler extends Bdcon{
    Connection dbCOnnection;
    public Connection getDbCOnnection()
            throws ClassNotFoundException,SQLException {
        String connectioSstring="jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");
        dbCOnnection=DriverManager.getConnection(connectioSstring, dbUser, dbPass);
        return dbCOnnection;
    }
    public static byte[] Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(input.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toSTr(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);

        StringBuilder hexString = new StringBuilder(number.toString(16));

        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }
    public int registr(User user)
    {
        int i=0;
        ResultSet resultSet = null;
        String pull = "SELECT * FROM "+ Const.ACCOUNTS_TABLE + " WHERE " + Const.ACCOUNTS_LOGIN + "=?";
        try {
            PreparedStatement prst = getDbCOnnection().prepareStatement(pull);
            prst.setString(1, user.LOGIN);

            resultSet = prst.executeQuery();
            while(resultSet.next()){
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        if(i==0) {
            String insert = "INSERT INTO " + Const.ACCOUNTS_TABLE + "("
                    + Const.ACCOUNTS_LOGIN + "," + Const.ACCOUNTS_PASSWORD + "," + Const.ACCOUNTS_LOCKED + ","
                    + Const.ACCOUNTS_ADMINCODE + "," + Const.ACCOUNTS_BALANCE + "," + Const.ACCOUNTS_FIRSTNAME + ","
                    + Const.ACCOUNTS_SECONDNAME + ")" + "VALUES(?,?,?,?,?,?,?)";
            try {
                PreparedStatement prst = getDbCOnnection().prepareStatement(insert);
                prst.setString(1, user.LOGIN);
                prst.setString(2, toSTr(Hash(user.PASSWORD)));
                prst.setInt(3, user.Locked);
                prst.setInt(4, user.Admincode);
                prst.setString(5, user.Balance);
                prst.setString(6, user.FIRSTNAME);
                prst.setString(7, user.SECONDNAME);
                prst.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
return i;
    }
   public ResultSet authoriz(User user){

       ResultSet resultSet = null;
       String pull = "SELECT * FROM "+ Const.ACCOUNTS_TABLE + " WHERE " + Const.ACCOUNTS_LOGIN + "=? AND " + Const.ACCOUNTS_PASSWORD + "=?";
       try {
           PreparedStatement prst = getDbCOnnection().prepareStatement(pull);

           prst.setString(1, user.LOGIN);
           prst.setString(2, toSTr(Hash(user.PASSWORD)));

           resultSet = prst.executeQuery();
       } catch (SQLException e) {
           e.printStackTrace();
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
       return  resultSet;
   }
        public void ChangePass(String pass, int id) {
            String change = "UPDATE " + Const.ACCOUNTS_TABLE + " SET " + Const.ACCOUNTS_PASSWORD + "=? WHERE " + Const.ACCOUNTS_IDACCOUNTS + "=?";
            try {
                PreparedStatement prSt = getDbCOnnection().prepareStatement(change);
                prSt.setString(1, toSTr(Hash(pass)));
                prSt.setInt(2, id);
                prSt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        public void ChangeBalance(String Balance, int id) {
            String change = "UPDATE " + Const.ACCOUNTS_TABLE + " SET " + Const.ACCOUNTS_BALANCE + "="+Balance+" WHERE " + Const.ACCOUNTS_IDACCOUNTS + "="+id;
            try {
                PreparedStatement prSt = getDbCOnnection().prepareStatement(change);
                prSt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    public ArrayList<Movies> getDAta() {
        ResultSet resultSet=null;
        ArrayList<Movies> list=new ArrayList<Movies>();
        String get="SELECT*FROM "+ Const.TICKET_TABLE;
        try {
            PreparedStatement prSt = getDbCOnnection().prepareStatement(get);
            resultSet = prSt.executeQuery();

            for(int i=0;resultSet.next();i++)
            {
                Movies newMovie = new Movies(resultSet.getInt(1),resultSet.getInt(2),
                        resultSet.getInt(4),resultSet.getInt(3),
                        resultSet.getString(5),resultSet.getString(6),resultSet.getInt(7));
                newMovie.takecount=newMovie.CountOfSingle+newMovie.CountOfVip+newMovie.CountOfLove;
                list.add(i,newMovie);
            }

        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }


        return list;
    }
    public void setbuy(User us,Movies mov){
        String insert="INSERT INTO "+Const.BUYING_TABLE+"("
               +Const.BUYING_FK1+","+Const.BUYING_FK2+","
                +Const.BUYING_NUMER+")"+"VALUES("+us.id+","+mov.MovieId+","+mov.PlaceNumer+")";
        try {
            PreparedStatement prst=getDbCOnnection().prepareStatement(insert);
            prst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public void changeCountOfPlace(Movies mov)
    {
        String change1 = "UPDATE " + Const.TICKET_TABLE + " SET "+ Const.TICKET_LOVE + "="+mov.CountOfLove+","
                + Const.TICKET_SINGLE + "="+mov.CountOfSingle+","+ Const.TICKET_VIP + "="+mov.CountOfVip+
                " WHERE " + Const.TICKET_IDTICKET + "="+mov.MovieId;
        try {
            PreparedStatement prSt = getDbCOnnection().prepareStatement(change1);
            prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public int[]  getcount(int iD)
    {
        int [] mas=new int[60];
        ResultSet resultSet=null;
        String get="SELECT*FROM "+ Const.BUYING_TABLE+" WHERE "+Const.BUYING_FK2+"="+iD;
        try {
            PreparedStatement prSt = getDbCOnnection().prepareStatement(get);
            resultSet = prSt.executeQuery();

            for(int i=0;resultSet.next();i++)
            {
                mas[i]=resultSet.getInt(4);
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return mas;
    }
    public ArrayList<Movies> getActiveTicket(User user)
    {
        ResultSet resultSet=null;
        ResultSet resset=null;
        ArrayList<Movies> list=new ArrayList<Movies>();
        String get="SELECT*FROM "+ Const.BUYING_TABLE+" WHERE "+Const.BUYING_FK1+"="+user.id;
        try {
            PreparedStatement prSt = getDbCOnnection().prepareStatement(get);
            resultSet = prSt.executeQuery();

            for(int i=0;resultSet.next();i++)
            {
                String get2="SELECT*FROM "+ Const.TICKET_TABLE+" WHERE "+Const.TICKET_IDTICKET+"="+resultSet.getInt(3);
                PreparedStatement prSt1 = getDbCOnnection().prepareStatement(get2);
                resset=prSt1.executeQuery();
                Movies mov=new Movies();
                mov.PlaceNumer=resultSet.getInt(4);
                for (int j=i;resset.next();) {
                    mov.FilmName = resset.getString(5);
                    mov.ScreeningDateTime = resset.getString(6);
                    list.add(i,mov);
                }
            }

        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }



    public void returnTick(Movies mov,User user)
    {
        ResultSet resultSet=null;
        String sel="SELECT*FROM "+Const.TICKET_TABLE+" WHERE "+Const.TICKET_NAME+"=? AND "+Const.TICKET_DATE_TIME+"=?";
        try {

            PreparedStatement prst = getDbCOnnection().prepareStatement(sel);
            prst.setString(1,mov.FilmName);
            prst.setString(2,mov.ScreeningDateTime);
            resultSet=prst.executeQuery();
            resultSet.next();
            mov.MovieId=resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        del(user,mov);
    }
    public void del(User user,Movies mov)
    {
        String delete="DELETE FROM "+ Const.BUYING_TABLE+ " WHERE "+ Const.BUYING_FK1 + "=? AND "+ Const.BUYING_FK2
                + "=? AND "+Const.BUYING_NUMER+"=?";
        try {
            PreparedStatement prst=getDbCOnnection().prepareStatement(delete);
            prst.setInt(1,user.id);
            prst.setInt(2,mov.MovieId);
            prst.setInt(3,mov.PlaceNumer);
            prst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Uptadeval(user,mov);
    }
public void Uptadeval(User user,Movies mov)
{
    if(mov.PlaceNumer>=1&&mov.PlaceNumer<=6) {
        System.out.println("1");
        String insert = "UPDATE " + Const.TICKET_TABLE + " SET "
                + Const.TICKET_LOVE + "=" + (mov.CountOfLove+1) +
                " WHERE " + Const.TICKET_IDTICKET + "=" + mov.MovieId;
        try {
            PreparedStatement zzz=getDbCOnnection().prepareStatement(insert);
            zzz.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        int z=Integer.parseInt(user.Balance);
        z+=24;
        user.Balance=Integer.toString(z);
        String up = "UPDATE " + Const.ACCOUNTS_TABLE + " SET "
                + Const.ACCOUNTS_BALANCE + "=" + user.Balance+
                " WHERE " + Const.ACCOUNTS_IDACCOUNTS + "=" + user.id;
        try {
            PreparedStatement zzz=getDbCOnnection().prepareStatement(up);
            zzz.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    if(mov.PlaceNumer>=11&&mov.PlaceNumer<=58) {
        System.out.println("2");
        String insert2 = "UPDATE " + Const.TICKET_TABLE + " SET "
                + Const.TICKET_SINGLE + "=" + (mov.CountOfSingle+1) +
                " WHERE " + Const.TICKET_IDTICKET + "=" + mov.MovieId;
        try {
            PreparedStatement zzz=getDbCOnnection().prepareStatement(insert2);
            zzz.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        int z=Integer.parseInt(user.Balance);
        z+=12;
        user.Balance=Integer.toString(z);
        String up = "UPDATE " + Const.ACCOUNTS_TABLE + " SET "
                + Const.ACCOUNTS_BALANCE + "=" + user.Balance+
                " WHERE " + Const.ACCOUNTS_IDACCOUNTS + "=" + user.id;
        try {
            PreparedStatement zzz=getDbCOnnection().prepareStatement(up);
            zzz.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    if(mov.PlaceNumer>=59&&mov.PlaceNumer<=64) {
        System.out.println("3");
        String insert3 = "UPDATE " + Const.TICKET_TABLE + " SET "
                + Const.TICKET_VIP + "=" + (mov.CountOfVip+1) +
                " WHERE " + Const.TICKET_IDTICKET + "=" + mov.MovieId;
        try {
            PreparedStatement zzz=getDbCOnnection().prepareStatement(insert3);
            zzz.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        int z=Integer.parseInt(user.Balance);
        z+=32;
        user.Balance=Integer.toString(z);
        String up = "UPDATE " + Const.ACCOUNTS_TABLE + " SET "
                + Const.ACCOUNTS_BALANCE + "=" + user.Balance+
                " WHERE " + Const.ACCOUNTS_IDACCOUNTS + "=" + user.id;
        try {
            PreparedStatement zzz=getDbCOnnection().prepareStatement(up);
            zzz.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
    public ArrayList<User> getACC() {
        ResultSet resultSet=null;
        ArrayList<User> list=new ArrayList<User>();
        String get="SELECT*FROM "+ Const.ACCOUNTS_TABLE;
        try {
            PreparedStatement prSt = getDbCOnnection().prepareStatement(get);
            resultSet = prSt.executeQuery();

            for(int i=0;resultSet.next();i++)
            {
                User user = new User(resultSet.getInt(1),
                        resultSet.getString(7),resultSet.getString(8),
                        resultSet.getString(2),resultSet.getString(3),
                        resultSet.getInt(4),resultSet.getInt(5));
                list.add(i,user);
            }

        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }


        return list;
    }
    public void addfilm(Movies mov){
        String insert="INSERT INTO "+ Const.TICKET_TABLE+"("
                +Const.TICKET_NAME+","+Const.TICKET_DATE_TIME+","
                +Const.TICKET_VIP+","+Const.TICKET_LOVE+","+Const.TICKET_SINGLE+","
                +Const.TICKET_NUMER+")"+
                "VALUES("+"'"+mov.FilmName+"'"+","+"'"+mov.ScreeningDateTime+"'"+","+mov.CountOfVip+","+mov.CountOfLove+","+mov.CountOfSingle
                +","+mov.HallNumer+")";
        try {
            PreparedStatement prst=getDbCOnnection().prepareStatement(insert);
            prst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public void redfilm(Movies mov){
        String insert="UPDATE "+ Const.TICKET_TABLE+" SET "
                +Const.TICKET_NAME+"="+"'"+mov.FilmName+"'"+","+Const.TICKET_DATE_TIME+"="+"'"+mov.ScreeningDateTime+"'"+","
                +Const.TICKET_VIP+"="+mov.CountOfVip+","+Const.TICKET_LOVE+"="+mov.CountOfLove+","
                +Const.TICKET_SINGLE+"="+mov.CountOfSingle+","
                +Const.TICKET_NUMER+"="+mov.HallNumer+" WHERE "+Const.TICKET_IDTICKET+"="+mov.MovieId;
        try {
            PreparedStatement prst=getDbCOnnection().prepareStatement(insert);
            prst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public void removefilm(Movies mov){
        String insert="DELETE FROM "+ Const.TICKET_TABLE+" WHERE "+Const.TICKET_IDTICKET+"="+mov.MovieId;
        try {
            PreparedStatement prst=getDbCOnnection().prepareStatement(insert);
            prst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void ChngAdmAndLock(User user){
        String insert="UPDATE "+ Const.ACCOUNTS_TABLE+" SET "
                +Const.ACCOUNTS_LOCKED+"="+user.Locked+"," +Const.ACCOUNTS_ADMINCODE+"="+user.Admincode+"" +
                " WHERE "+Const.ACCOUNTS_IDACCOUNTS+"="+user.id;
        try {
            PreparedStatement prst=getDbCOnnection().prepareStatement(insert);
            prst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public void DelACC(User user){
        String insert="DELETE FROM "+ Const.ACCOUNTS_TABLE+" WHERE "+Const.ACCOUNTS_IDACCOUNTS+"="+user.id;
        try {
            PreparedStatement prst=getDbCOnnection().prepareStatement(insert);
            prst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}

