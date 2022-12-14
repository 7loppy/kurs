
import User.User;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backbtn;

    @FXML
    private TextField firstnamefield;

    @FXML
    private TextField regloginfield;

    @FXML
    private Button registratebtn;

    @FXML
    private PasswordField regpasswordfield;

    @FXML
    private TextField secondnamefield;

    @FXML
    void initialize() {
        registratebtn.setOnAction(actionEvent->{
                try {
                    Broker.bytewriter.write((int) 1);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                User user = new User();
                user.LOGIN = regloginfield.getText();
                user.PASSWORD = regpasswordfield.getText();
                user.Locked = 0;
                user.Admincode = 0;
                user.Balance = "0";
                user.FIRSTNAME = firstnamefield.getText();
                user.SECONDNAME = secondnamefield.getText();
                if(!user.LOGIN.equals("")&&!user.PASSWORD.equals("")&&!user.FIRSTNAME.equals("")&&!user.SECONDNAME.equals(""))
                {

                    reg(user);
                }
                else {
                    regloginfield.clear();
                    regloginfield.setPromptText("Введите логин");
                    regpasswordfield.clear();
                    regpasswordfield.setPromptText("Введите пароль");
                    firstnamefield.clear();
                    firstnamefield.setPromptText("Введите имя");
                    secondnamefield.clear();
                    secondnamefield.setPromptText("Введите Фамилию");
                }


        });

        backbtn.setOnAction(actionEvent->{
            backbtn.getScene().getWindow().hide();
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



}

    private void reg(User us) {
        int i = 0;
        User user = us;
        try {
            Broker.writer.writeObject(user);
            i = Broker.bytereader.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("4   "+i);
        if (i == 1) {
            regloginfield.clear();
            regloginfield.setPromptText("Логин уже используется");
        }
        else {
            Client.ActiveUser=user;
            registratebtn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fxml/UserProfile.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            }
        }
    }

