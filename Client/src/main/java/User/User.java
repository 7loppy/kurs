package User;

import com.mysql.cj.jdbc.admin.MiniAdmin;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = -4710024205100527877L;
    public int id;

    public String LOGIN;
    public String PASSWORD;

    public int Admincode=0;
    public int Locked = 0;
    public String Balance = "0";
    public String FIRSTNAME;
    public String SECONDNAME;


    public void setId(int id) {
        this.id = id;
    }

    public void setLOGIN(String LOGIN) {
        this.LOGIN = LOGIN;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public void setAdmincode(int admincode) {
        Admincode = admincode;
    }

    public void setLocked(int locked) {
        Locked = locked;
    }

    public void setBalance(String balance) {
        Balance = balance;
    }

    public void setFIRSTNAME(String FIRSTNAME) {
        this.FIRSTNAME = FIRSTNAME;
    }

    public void setSECONDNAME(String SECONDNAME) {
        this.SECONDNAME = SECONDNAME;
    }

    public int getId() {
        return id;
    }

    public String getLOGIN() {
        return LOGIN;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public int getAdmincode() {
        return Admincode;
    }

    public int getLocked() {
        return Locked;
    }

    public String getBalance() {
        return Balance;
    }

    public String getFIRSTNAME() {
        return FIRSTNAME;
    }

    public String getSECONDNAME() {
        return SECONDNAME;
    }

    public User() {
    }

    public User(String FIRSTNAME, String SECONDNAME, String LOGIN, String PASSWORD) {
        this.FIRSTNAME = FIRSTNAME;
        this.SECONDNAME = SECONDNAME;
        this.LOGIN = LOGIN;
        this.PASSWORD = PASSWORD;
    }
    public User(int Id,String FIRSTNAME, String SECONDNAME, String LOGIN, String PASSWORD,int Locked,int Amd) {
        this.id=Id;
        this.Locked=Locked;
        this.Admincode= Amd;
        this.FIRSTNAME = FIRSTNAME;
        this.SECONDNAME = SECONDNAME;
        this.LOGIN = LOGIN;
        this.PASSWORD = PASSWORD;
    }

    public User(User user) {
        this.id=user.id;
        this.FIRSTNAME = user.FIRSTNAME;
        this.SECONDNAME = user.SECONDNAME;
        this.Locked=user.Locked;
        this.Admincode=user.Admincode;
        this.Balance= user.Balance;;
        this.LOGIN = user.LOGIN;
        this.PASSWORD = user.PASSWORD;
    }

}