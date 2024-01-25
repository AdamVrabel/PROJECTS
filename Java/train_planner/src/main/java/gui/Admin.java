package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import models.Database;
import models.persons.Passenger;

import java.io.IOException;

/**
 * <b>ADMINISTRATOR MODE</b>
 * <p>display this page after login as administrator, admin can choose from menu</p>
 */
public class Admin {
    private final Database database = new Database();
    private final Gui gui = new Gui();


    @FXML
    private VBox searchRoute;
    @FXML
    private VBox createRouteButton;
    @FXML
    private VBox logoutButton;

    public void clickSearchButton() throws IOException{
        gui.changeScene("adminSearch.fxml");
    }
    public void clickCreateButton() throws IOException{
        gui.changeScene("adminNewRoute.fxml");

    }
    // ODHLASI UZIVATELA A PREJDE NA LOGIN PAGE
    public void clickLogoutButton() throws IOException {
        System.out.println("Administrator odhlásený !");

        // VYSKAKOVACIE OKNO
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ADMINISTRATION LOG OUT");
        alert.setHeaderText(null);
        alert.setContentText("ADMINISTRATOR bol odhlásený.");
        alert.showAndWait();

        Passenger.acutalLoggedUser = null;
        gui.changeScene("login.fxml");

    }
}
