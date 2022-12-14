import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Movies.Movies;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MoviesController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackToSUfFeld;

    @FXML
    private TableColumn<Movies, Integer> IdConstrait1;
    @FXML
    private TableView<Movies> MoviesTable1;

    @FXML
    private Button PurschaseBtn;

    @FXML
    private TableColumn<Movies, String> TimeConstrait1;

    @FXML
    private TableColumn<Movies, String> TitleConstrait1;

    @FXML
    private Button moviesbtn;

    @FXML
    private Button profilebtn;



    ObservableList<Movies> forTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        try {
            Broker.bytewriter.write((int)5);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Movies>list=new ArrayList<Movies>();
        try {
            list=(ArrayList<Movies>) Broker.reader.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        forTable= FXCollections.observableArrayList(list);
        IdConstrait1.setCellValueFactory(new PropertyValueFactory<Movies, Integer>("MovieId"));
        TitleConstrait1.setCellValueFactory(new PropertyValueFactory<Movies, String>("FilmName"));
        TimeConstrait1.setCellValueFactory(new PropertyValueFactory<Movies, String>("ScreeningDateTime"));

        MoviesTable1.setItems(forTable);



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
        PurschaseBtn.setOnAction(actionEvent->{
            Client.ActiveMovie=MoviesTable1.getSelectionModel().getSelectedItem();
            PurschaseBtn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fxml/Purchase.fxml"));

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
