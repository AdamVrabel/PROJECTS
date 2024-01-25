package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.Database;
import models.persons.Passenger;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <b>ADMINISTRATOR MODE</b>
 * <p>administrator can add new route to database</p>
 */
public class AdminNewRoute {
    private final Database database = new Database();
    private final Gui gui = new Gui();

    private static String loadedDate;


    @FXML
    private VBox searchRoute;
    @FXML
    private VBox createRouteButton;
    @FXML
    private VBox logoutButton;

    public void clickSearchButton() throws IOException {
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


    @FXML
    private TextField FROM_input;
    @FXML
    private TextField TO_input;
    @FXML
    private DatePicker datePicker;
    // NACITA DATUM Z INPUTU
    public void getDate(ActionEvent event) throws IOException{

        // AK NENACITA ZIADNY DATUM, NASTAVÍ DEFAULTNÝ
        if(datePicker.getValue() == null){
            return;
        }

        LocalDate nacitanyDate = datePicker.getValue();
        //System.out.println("NACITANY DATUM: " + nacitanyDate.toString());
        String formatovanyDate = nacitanyDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        //System.out.println("FORMATOVANY DATUM: " + formatovanyDate);

        loadedDate = nacitanyDate.toString();   // NAČÍTA DÁTUM
    }

    @FXML
    private TextField timeDeparture;
    @FXML
    private TextField timeArrival;
    @FXML
    private Text error;

    public void addNewRoute() throws IOException{
        error.setText("");

        String from = FROM_input.getText();
        String to = TO_input.getText();
        String DepartureTime = timeDeparture.getText();
        String ArrivalTime = timeArrival.getText();
        // DATUM = loadedDate

        if( FROM_input.getText().isEmpty() || TO_input.getText().isEmpty() || timeDeparture.getText().isEmpty() || timeArrival.getText().isEmpty() || datePicker.getValue()==null ){
            error.setText("You must fill in everything !");
        }
        else{
            System.out.println("PRIDAVAM TRAIN ROUTE DO DATABAZY");
            System.out.println(from);
            System.out.println(to);
            System.out.println(loadedDate);
            System.out.println(DepartureTime);
            System.out.println(ArrivalTime);
            System.out.println("_________________________________");

            // DOTAZ NA DATABAZU (PRIDANIE ZAZNAMU DO DATABAZY)
            database.addRouteToDB( from, to, DepartureTime, ArrivalTime, loadedDate);

            // VYSKAKOVACIE OKNO
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("NEW ROUTE SUCSSESSFULY CREATED");
            alert.setHeaderText("ROUTE ADDED !");
            alert.setContentText("From: " + from + "\n" +
                    "To: " + to + "\n" +
                    "Departure Time: " + DepartureTime + "\n" +
                    "Arrival Time: " + ArrivalTime + "\n" +
                    "Date: " + loadedDate
            );
            alert.showAndWait();
        }


    }
}
