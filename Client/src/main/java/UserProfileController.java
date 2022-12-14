import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Movies.Movies;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class UserProfileController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button retbtn;
    @FXML
    private Button BackToSUfFeld;

    @FXML
    private TableView<Movies> actticktable;
    @FXML
    private Label BalanceLabel;

    @FXML
    private Button ChangePasswordBtn;
    @FXML
    private  Button pfdbtn;
    @FXML
    private TextField ChangePasswordField;

    @FXML
    private Label FirstNameLabel;

    @FXML
    private Button ReplenishBalanceBtn;

    @FXML
    private TextField ReplenishBalanceField;

    @FXML
    private TableColumn<Movies, String> date_timeconstr;
    @FXML
    private Label SecondNameLabel;
    @FXML
    private Button moviesbtn;

    @FXML
    private TableColumn<Movies, Integer> numerconstraits;

    @FXML
    private Button profilebtn;
    @FXML
    private TableColumn<Movies, String> titleconstraits;

    ObservableList<Movies> forTable;

    @FXML
    void initialize() {

        try {
            Broker.bytewriter.write((int)8);
            Broker.writer.writeObject(Client.ActiveUser);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

         ArrayList<Movies> list=new ArrayList<Movies>();

         try {
             list=(ArrayList<Movies>) Broker.reader.readObject();
         } catch (IOException e) {
             throw new RuntimeException(e);
         } catch (ClassNotFoundException e) {
             throw new RuntimeException(e);
         }
          forTable= FXCollections.observableArrayList(list);
        numerconstraits.setCellValueFactory(new PropertyValueFactory<Movies,Integer>("PlaceNumer"));
          titleconstraits.setCellValueFactory(new PropertyValueFactory<Movies, String>("FilmName"));
          date_timeconstr.setCellValueFactory(new PropertyValueFactory<Movies, String>("ScreeningDateTime"));
        actticktable.setItems(forTable);



        SecondNameLabel.setText(Client.ActiveUser.SECONDNAME);
        FirstNameLabel.setText(Client.ActiveUser.FIRSTNAME);
        BalanceLabel.setText(Client.ActiveUser.Balance);

        pfdbtn.setOnAction(actionEvent -> {
            try {
            Movies mob=actticktable.getSelectionModel().getSelectedItem();
            Document document = new Document();
            PdfWriter writer = null;
                writer = PdfWriter.getInstance(document, new FileOutputStream("Ticket.pdf"));
            document.open();
            document.add(new Paragraph("Ticket"));
            document.add(new Paragraph("Title: "+mob.FilmName+"  Date/Time: "+mob.ScreeningDateTime+"  Place_numer: "+mob.PlaceNumer));
            document.close();
            writer.close();
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        retbtn.setOnAction(actionEvent -> {
            Client.ActiveMovie=actticktable.getSelectionModel().getSelectedItem();
            try {
                Broker.bytewriter.write((int)9);
                Broker.writer.writeObject(Client.ActiveMovie);
                Broker.writer.writeObject(Client.ActiveUser);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            retbtn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fxml/UserProfile.fxml"));

            try {
                loader.load();
            }catch (IOException e)
            {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });



        BackToSUfFeld.setOnAction(actionEvent->{
            BackToSUfFeld.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fxml/SignUp.fxml"));

            try {
                loader.load();
            }catch (IOException e)
            {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        });
        ChangePasswordBtn.setOnAction(actionEvent->{
            try {
                Broker.bytewriter.write((int)3);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(ChangePasswordField.getText().equals("") )
        {
            ChangePasswordField.clear();
            ChangePasswordField.setPromptText("Введите новый пароль");
        }
        else{
                if (ChangePasswordField.getText().equals(Client.ActiveUser.PASSWORD))
                {
                    ChangePasswordField.clear();
                    ChangePasswordField.setPromptText("Совпадает с нынешним");
                }
                else {
                    Client.ActiveUser.PASSWORD = ChangePasswordField.getText().trim();
                    try {
                       Broker.bytewriter.write(Client.ActiveUser.id);
                        byte [] bt=new byte[ Client.ActiveUser.PASSWORD.length()];
                        bt= Client.ActiveUser.PASSWORD.getBytes();
                        Broker.bytewriter.write(Client.ActiveUser.PASSWORD.length());
                        Broker.bytewriter.write(bt);
                        System.out.println(Client.ActiveUser.id + "   " + Client.ActiveUser.PASSWORD);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                   ChangePasswordField.clear();
                   ChangePasswordField.setPromptText("Изменено");
                }
        }
        });
        ReplenishBalanceBtn.setOnAction(actionEvent->{
            int re1,re2;
            try {
                Broker.bytewriter.write((int)4);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(ReplenishBalanceField.getText().equals(""))
            {
                ReplenishBalanceField.clear();
                ReplenishBalanceField.setPromptText("Введите что либо");
            }
            else {
                if (ReplenishBalanceField.getText().equals("0")) {
                    ReplenishBalanceField.clear();
                    ReplenishBalanceField.setPromptText("Нельзя пополнить на 0");
                } else {
                    re1=Integer.parseInt(Client.ActiveUser.Balance);
                    re2=Integer.parseInt(ReplenishBalanceField.getText().trim());
                    re1+=re2;
                    Client.ActiveUser.Balance=Integer.toString(re1);
                    try {
                        Broker.bytewriter.write(Client.ActiveUser.id);
                        byte [] bt=new byte[ Client.ActiveUser.PASSWORD.length()];
                        bt= Client.ActiveUser.Balance.getBytes();
                        Broker.bytewriter.write(Client.ActiveUser.Balance.length());
                        Broker.bytewriter.write(bt);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    ReplenishBalanceField.clear();
                    ReplenishBalanceField.setPromptText("Пополнено");
                }
            }
        });
        moviesbtn.setOnAction(actionEvent->{
            moviesbtn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fxml/Movies.fxml"));

            try {
                loader.load();
            }catch (IOException e)
            {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
        profilebtn.setOnAction(actionEvent->{
            profilebtn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fxml/UserProfile.fxml"));

            try {
                loader.load();
            }catch (IOException e)
            {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });

    }

}
