import Movies.Movies;
import User.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AdminWindowController {

    @FXML
    private Button BACKTOSUbtn;

    @FXML
    private TextField DAteField;

    @FXML
    private TableColumn<Movies, String> DateConstrait;

    @FXML
    private TableColumn<Movies, Integer> IdConstrait;

    @FXML
    private TableColumn<User, Integer> LockedConstraits;

    @FXML
    private TableColumn<User, Integer> LogIDConstraits;

    @FXML
    private TableColumn<User, String> LoignConstraits;

    @FXML
    private TableView<Movies> MoviesTable;
    @FXML
    private TableView<User> accTable;
    @FXML
    private TextField NameOfTitleField;

    @FXML
    private TableColumn<Movies, String> TitleConstrait;
    @FXML
    private TableColumn<Movies, Integer> countconstr;
    @FXML
    private TableColumn<Movies, Integer> HallConstr;
    @FXML
    private Button addbtn;

    @FXML
    private TableColumn<User, Integer> admConstr;
    @FXML
    private TextField hallfld;
    @FXML
    private TextField lockfld;
    @FXML
    private TextField ADmfld;
    @FXML
    private Button chnbtn;

    @FXML
    private Button delbtn;

    @FXML
    private Button delflmbtn;

    @FXML
    private Button redbtn;
    ObservableList<Movies> forTable;
    ObservableList<User> ForACC;
    @FXML
    void initialize() {

          try {
              Broker.bytewriter.write((int)5);
          }
          catch (IOException e)
          {
              throw new RuntimeException(e);
          }
          ArrayList<Movies> list=new ArrayList<Movies>();
          try
          {list=(ArrayList<Movies>) Broker.reader.readObject();
          }
          catch (IOException e)
          {throw new RuntimeException(e);
          } catch (ClassNotFoundException e)
          {throw new RuntimeException(e);
          }
          forTable= FXCollections.observableArrayList(list);
          IdConstrait.setCellValueFactory(new PropertyValueFactory<Movies, Integer>("MovieId"));
          TitleConstrait.setCellValueFactory(new PropertyValueFactory<Movies, String>("FilmName"));
          DateConstrait.setCellValueFactory(new PropertyValueFactory<Movies, String>("ScreeningDateTime"));
        HallConstr.setCellValueFactory(new PropertyValueFactory<Movies, Integer>("HallNumer"));
        countconstr.setCellValueFactory(new PropertyValueFactory<Movies, Integer>("takecount"));
          MoviesTable.setItems(forTable);




           try {
                  Broker.bytewriter.write((int)10);
              } catch (IOException e) {
                  throw new RuntimeException(e);
              }
          ArrayList<User> listacc=new ArrayList<User>();
          try {
                 listacc=(ArrayList<User>) Broker.reader.readObject();
             } catch (IOException e) {
                 throw new RuntimeException(e);
             } catch (ClassNotFoundException e) {
                 throw new RuntimeException(e);
             }
          ForACC= FXCollections.observableArrayList(listacc);
          LogIDConstraits.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
          LockedConstraits.setCellValueFactory(new PropertyValueFactory<User, Integer>("Locked"));
          LoignConstraits.setCellValueFactory(new PropertyValueFactory<User, String>("LOGIN"));
          admConstr.setCellValueFactory(new PropertyValueFactory<User, Integer>("Admincode"));

          accTable.setItems(ForACC);

        addbtn.setOnAction(actionEvent->{
           Movies mov=new Movies();
            mov.FilmName=NameOfTitleField.getText().trim();
            mov.ScreeningDateTime=DAteField.getText().trim();
            mov.HallNumer=Integer.parseInt(hallfld.getText().trim());
            try {
                Broker.bytewriter.write((int)11);
                Broker.writer.writeObject(mov);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            addbtn.getScene().getWindow().hide();
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
            stage.show();
        });

        redbtn.setOnAction(actionEvent->{
            Movies mov=new Movies();
            mov=MoviesTable.getSelectionModel().getSelectedItem();
            mov.FilmName=NameOfTitleField.getText().trim();
            mov.ScreeningDateTime=DAteField.getText().trim();
            mov.HallNumer=Integer.parseInt(hallfld.getText().trim());
            try {
                Broker.bytewriter.write((int)12);
                Broker.writer.writeObject(mov);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            redbtn.getScene().getWindow().hide();
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
            stage.show();
        });
        delflmbtn.setOnAction(actionEvent->{
            Movies mov=new Movies();
            mov=MoviesTable.getSelectionModel().getSelectedItem();
            try {
                Broker.bytewriter.write((int)13);
                Broker.writer.writeObject(mov);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            delflmbtn.getScene().getWindow().hide();
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
            stage.show();
        });
        chnbtn.setOnAction(actionEvent->{
            User user=new User();
            user=accTable.getSelectionModel().getSelectedItem();
            user.Locked=Integer.parseInt(lockfld.getText());
            user.Admincode=Integer.parseInt(ADmfld.getText());
            try {
                Broker.bytewriter.write((int)14);
                Broker.writer.writeObject(user);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            chnbtn.getScene().getWindow().hide();
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
            stage.show();
        });
        delbtn.setOnAction(actionEvent->{
            User user=new User();
            user=accTable.getSelectionModel().getSelectedItem();
            try {
                Broker.bytewriter.write((int)15);
                Broker.writer.writeObject(user);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            delflmbtn.getScene().getWindow().hide();
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
            stage.show();
        });

        BACKTOSUbtn.setOnAction(actionEvent->{
            BACKTOSUbtn.getScene().getWindow().hide();
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

}
