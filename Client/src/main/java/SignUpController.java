import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import User.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authorizationbtn;

    @FXML
    private TextField loginfield;

    @FXML
    private PasswordField passwordfield;

    @FXML
    private Button registrationbtn;

    @FXML
    void initialize() {

        registrationbtn.setOnAction(actionEvent->{
            registrationbtn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fxml/Registration.fxml"));

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
        authorizationbtn.setOnAction(actionEvent->{
            String login=loginfield.getText().trim();
            String password=passwordfield.getText().trim();
            if(!login.equals("")&&!password.equals(""))
            {
                try {
                    Broker.bytewriter.write((int)2);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                authorizate(login,password);
            }
            else {
                loginfield.clear();
                loginfield.setPromptText("Введите логин");
                passwordfield.clear();
                passwordfield.setPromptText("Введите пароль");
            }
        });
    }

   private void authorizate(String login, String password) {
       try{
           User user = new User();
           user.LOGIN = login;
           user.PASSWORD = password;
           Broker.writer.writeObject(user);
           Client.ActiveUser= (User)Broker.reader.readObject();
          if( Client.ActiveUser.id==0)
          {
              loginfield.clear();
              loginfield.setPromptText("Неверный логин или пароль");
              passwordfield.clear();
              passwordfield.setPromptText("Неверный логин или пароль");
          }
          else {
              if (Client.ActiveUser.Admincode == 1 && Client.ActiveUser.Locked == 0) {
                  authorizationbtn.getScene().getWindow().hide();
                  FXMLLoader loader1 = new FXMLLoader();
                  loader1.setLocation(getClass().getResource("fxml/Admin.fxml"));

                  try {
                      loader1.load();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
                  Parent root = loader1.getRoot();
                  Stage stage = new Stage();
                  stage.setScene(new Scene(root));
                  stage.showAndWait();
              } else {
                  authorizationbtn.getScene().getWindow().hide();
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
                  stage.showAndWait();
              }
          }
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e)
       {
           e.printStackTrace();
       }
   }


}
