import java.io.IOException;

import Movies.Movies;
import User.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PurchaseController {

    @FXML
    private Button InputBtn;

    @FXML
    private TextField PlaceField;

    @FXML
    private Button backbtn;

    @FXML
    private TextField fld1;

    @FXML
    private TextField fld11;

    @FXML
    private TextField fld12;

    @FXML
    private TextField fld13;

    @FXML
    private TextField fld14;

    @FXML
    private TextField fld15;

    @FXML
    private TextField fld16;

    @FXML
    private TextField fld17;

    @FXML
    private TextField fld18;

    @FXML
    private TextField fld19;

    @FXML
    private TextField fld2;

    @FXML
    private TextField fld20;

    @FXML
    private TextField fld21;

    @FXML
    private TextField fld22;

    @FXML
    private TextField fld23;

    @FXML
    private TextField fld24;

    @FXML
    private TextField fld25;

    @FXML
    private TextField fld26;

    @FXML
    private TextField fld27;

    @FXML
    private TextField fld28;

    @FXML
    private TextField fld29;

    @FXML
    private TextField fld3;

    @FXML
    private TextField fld30;

    @FXML
    private TextField fld31;

    @FXML
    private TextField fld32;

    @FXML
    private TextField fld33;

    @FXML
    private TextField fld34;

    @FXML
    private TextField fld35;

    @FXML
    private TextField fld36;

    @FXML
    private TextField fld37;

    @FXML
    private TextField fld38;

    @FXML
    private TextField fld39;

    @FXML
    private TextField fld4;

    @FXML
    private TextField fld40;

    @FXML
    private TextField fld41;

    @FXML
    private TextField fld42;

    @FXML
    private TextField fld43;

    @FXML
    private TextField fld44;

    @FXML
    private TextField fld45;

    @FXML
    private TextField fld46;

    @FXML
    private TextField fld47;

    @FXML
    private TextField fld48;

    @FXML
    private TextField fld49;

    @FXML
    private TextField fld5;

    @FXML
    private TextField fld50;

    @FXML
    private TextField fld51;

    @FXML
    private TextField fld52;

    @FXML
    private TextField fld53;

    @FXML
    private TextField fld54;

    @FXML
    private TextField fld55;

    @FXML
    private TextField fld56;

    @FXML
    private TextField fld57;

    @FXML
    private TextField fld58;

    @FXML
    private TextField fld59;

    @FXML
    private TextField fld6;

    @FXML
    private TextField fld60;

    @FXML
    private TextField fld61;

    @FXML
    private TextField fld62;

    @FXML
    private TextField fld63;

    @FXML
    private TextField fld64;

    Movies temp= null;

    @FXML
    void initialize() {
        try {
            Broker.bytewriter.write((int)7);
            Broker.writer.writeObject(Client.ActiveMovie);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            temp = (Movies) Broker.reader.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for(int i=0; temp.countofpl[i]!=0;i++)
            switch (temp.countofpl[i]) {
                case 1: {
                    fld1.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 2: {
                    fld2.setStyle("-fx-background-color: #333333;");
                    break;
                }
                case 3: {
                    fld3.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 4: {
                    fld4.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 5: {
                    fld5.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 6: {
                    fld6.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 11: {
                    fld11.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 12: {
                    fld12.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 13: {
                    fld13.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 14: {
                    fld14.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 15: {
                    fld15.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 16: {
                    fld16.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 17: {
                    fld17.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 18: {
                    fld18.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 19: {
                    fld19.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 20: {
                    fld20.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 21: {
                    fld21.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 22: {
                    fld22.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 23: {
                    fld23.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 24: {
                    fld24.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 25: {
                    fld25.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 26: {
                    fld26.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 27: {
                    fld27.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 28: {
                    fld28.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 29: {
                    fld29.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 30: {
                    fld30.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 31: {
                    fld31.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 32: {
                    fld32.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 33: {
                    fld33.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 34: {
                    fld34.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 35: {
                    fld35.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 36: {
                    fld36.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 37: {
                    fld37.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 38: {
                    fld38.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 39: {
                    fld39.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 40: {
                    fld40.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 41: {
                    fld41.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 42: {
                    fld42.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 43: {
                    fld43.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 44: {
                    fld44.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 45: {
                    fld45.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 46: {
                    fld46.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 47: {
                    fld47.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 48: {
                    fld48.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 49: {
                    fld49.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 50: {
                    fld50.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 51: {
                    fld51.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 52: {
                    fld52.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 53: {
                    fld53.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 54: {
                    fld54.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 55: {
                    fld55.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 56: {
                    fld56.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 57: {
                    fld57.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 58: {
                    fld58.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 59: {
                    fld59.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 60: {
                    fld60.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 61: {
                    fld61.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 62: {
                    fld62.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 63: {
                    fld63.setStyle("-fx-background-color: #666666;");
                    break;
                }
                case 64: {
                    fld64.setStyle("-fx-background-color: #666666;");
                    break;
                }
        }
            InputBtn.setOnAction(actionEvent -> {
            int check=Integer.parseInt(PlaceField.getText().trim());
            int minus=Integer.parseInt(Client.ActiveUser.Balance);
            int g=0;
            if(PlaceField.getText().trim().equals(""))
            {
            PlaceField.clear();
            PlaceField.setPromptText("Выбирете место");
            }
            else {
                for (int i = 0; temp.countofpl[i] != 0; i++) {
                    if (check == temp.countofpl[i]) g = 1;
                }

                    if ((check >= 1 && check <= 6) || (check >= 11 && check <= 64)) {
                        if (g == 1) {
                            PlaceField.clear();
                            PlaceField.setPromptText("Место занято");
                        } else {
                            if (check >= 1 && check <= 6 &&minus>=24) {
                                minus -= 24;
                                Client.ActiveMovie.CountOfLove-=1;
                                Client.ActiveUser.Balance = Integer.toString(minus);
                                Client.ActiveMovie.PlaceNumer = check;
                                sell();
                            }
                            if (check >= 11 && check <= 58&&minus>=12) {
                                minus -= 12;
                                Client.ActiveMovie.CountOfSingle-=1;

                                Client.ActiveUser.Balance = Integer.toString(minus);
                                Client.ActiveMovie.PlaceNumer = check;
                                sell();
                            }
                            if (check >= 59 && check <= 64&&minus>=32) {
                                minus -= 32;
                                Client.ActiveMovie.CountOfVip-=1;
                                Client.ActiveUser.Balance = Integer.toString(minus);
                                Client.ActiveMovie.PlaceNumer = check;
                                sell();
                            }


                        }
                    } else {
                        PlaceField.clear();
                        PlaceField.setPromptText("Такого места нет");
                    }
                }

        });


        backbtn.setOnAction(actionEvent -> {
            backbtn.getScene().getWindow().hide();
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

    private void sell() {
        try {
            Broker.bytewriter.write((int) 6);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {

            User us=new User(Client.ActiveUser);
            Movies mov=new Movies(Client.ActiveMovie);
            Broker.writer.writeObject(us);
            Broker.writer.writeObject(mov);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PlaceField.clear();
        PlaceField.setPromptText("Приобретен");
    }

}
