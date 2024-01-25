package gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import javafx.stage.Stage;
import javafx.event.ActionEvent;
import models.Database;

import java.io.IOException;

/**
 * <p>registration page</p>
 * <p>if user with entered data does not exist, it will be created</p>
 */
public class Registration {

    public Registration(){

    }

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Text wrongRegister;
    @FXML
    private Button registrationButton;
    @FXML
    private Button backButton;


    public void validateRegistration(ActionEvent event) throws IOException{
        if(username.getText().isEmpty() || password.getText().isEmpty()){
            wrongRegister.setText("Please enter your data.");
        } else if (username.getText().equals("admin") && password.getText().equals("admin")) {
            wrongRegister.setText("Cannot create administration account !");
        } else{
            String usernameString = username.getText().toString();
            String passwordString = password.getText().toString();
            Database database = new Database();
            Gui gui = new Gui();

            boolean exist = database.validateLogin(usernameString, passwordString); // ZISTI CI EXISTUJE UZIVATEL AKEHO CHCEME VYTVORIT
            if(exist){
                // UZIVATEL EXISTUJE, REGISTRACIA NEUSPESNA
                wrongRegister.setText("User already exist. TRY AGAIN!");
            }
            else{
                // VYTVORI UZIVATELA
                //System.out.println("UZIVATEL NEEXISTUJE, VYTVÁRAM UZIVATELA !");
                System.out.println("CREATING NEW USER ...");
                System.out.println("NEW USER - username: " + usernameString + "  password: " + passwordString);

                // DO DATABAZY PRIDA UZIVATELA
                database.addNewUser(usernameString, passwordString);

                gui.changeScene("login.fxml");
            }

        }
    }

    public void backToLogin(ActionEvent event) throws IOException{
        Gui gui = new Gui();
        gui.changeScene("login.fxml");
    }



}
