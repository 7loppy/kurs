
import Movies.Movies;
import User.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

public class Client extends Application
{
    public static  User ActiveUser = new User();
    public static Movies ActiveMovie = new Movies();
    @Override
    public void start(Stage stage) throws IOException {

          Parent root=FXMLLoader.load(getClass().getResource("fxml/SignUP.fxml"));
        stage.setTitle("CinemaApp");
            stage.setScene(new Scene(root,800,500));
            stage.show();
    }

    public static void main(String[] args){
        Broker broker=new Broker("127.0.0.1", 8000);
        launch(args);
    }
}