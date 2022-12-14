public class Const {
    public static final String ACCOUNTS_TABLE="Accounts";

    public static final String ACCOUNTS_IDACCOUNTS="idaccount";
    public static final String ACCOUNTS_LOGIN="login";
    public static final String ACCOUNTS_PASSWORD="password";
    public static final String ACCOUNTS_LOCKED="locked";
    public static final String ACCOUNTS_ADMINCODE="AdminCode";
    public static final String ACCOUNTS_BALANCE="balance";
    public static final String ACCOUNTS_FIRSTNAME="firstname";
    public static final String ACCOUNTS_SECONDNAME="secondname";


    public static final String TICKET_TABLE="Ticket";

    public static final String TICKET_IDTICKET="idticket";
    public static final String TICKET_LOVE="count_of_love_places";
    public static final String TICKET_VIP="count_of_vip_places";
    public static final String TICKET_SINGLE="count_of_single_places";
    public static final String TICKET_NAME="Name_of_film";
    public static final String TICKET_DATE_TIME="movie_screening_date_time";
    public static final String TICKET_NUMER="numer_of_hall";


    public static final String BUYING_TABLE="Buying";

    public static final String BUYING_ID="idbuying";
    public static final String BUYING_FK1="Accounts_idaccount";
    public static final String BUYING_FK2="ticket_idticket";
    public static final String BUYING_NUMER="numer_of_place";

}
