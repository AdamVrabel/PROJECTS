package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import javafx.stage.Stage;
import javafx.event.ActionEvent;
import models.Database;
import models.persons.Passenger;

import java.io.IOException;

/**
 * user or administrator login page
 * <li>check entered informations in database, and login if correct</li>
 * <li>registration page link</li>
 */
public class Login {

    public Login(){

    }


    @FXML
    private Button login;
    @FXML
    private Hyperlink registration;
    @FXML
    private Text wrongLogin;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public void userLogin(ActionEvent event) throws IOException{
        checkLogin();
    }

    private void checkLogin() throws IOException{
        Gui gui = new Gui();
        Database database = new Database();

        if(username.getText().isEmpty() && password.getText().isEmpty()){
            wrongLogin.setText("Please enter your data.");
        }
        else if(username.getText().equals("admin") && password.getText().equals("admin")){
            System.out.println("LOGIN AS ADMIN !");
            gui.changeScene("admin.fxml");
        }
        //else if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin") ){
        else if( database.validateLogin(username.getText().toString(), password.getText().toString()) ){
            wrongLogin.setText("Success !");
            System.out.println("RECORD FOUND IN DATABASE, LOGGING IN...");

            //Passenger.acutalLoggedUser = new Passenger(username.getText().toString(), password.getText().toString());   // ZAPISE AKY UZIVATEL JE PRIHLASENY
            Passenger.acutalLoggedUser = database.getUser(username.getText().toString(), password.getText().toString());


            // VYSKAKOVACIE OKNO
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("PRIHLÁSENIE ÚSPLEŠNÉ");
            alert.setHeaderText(null);
            alert.setContentText("Uživateľ " + username.getText() + " je prihlásený.");
            alert.showAndWait();

            //gui.changeScene("afterLogin.fxml");
            gui.changeScene("search.fxml");
        }
        else{
            wrongLogin.setText("Wrong username or password !");
        }

    }

    public void userRegistration(ActionEvent event) throws IOException{ // KLIKNEM NA ODKAZ REGISTRATION, presmeruje ma na registration page
        //System.out.println("REGISTRATION");
        Gui gui = new Gui();
        gui.changeScene("registration.fxml");
    }


}
