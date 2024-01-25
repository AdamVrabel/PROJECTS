package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import models.Database;
import models.persons.Passenger;
import models.train.TrainRoute;
import models.train.Wagon;

import java.io.IOException;
import java.util.ArrayList;

/**
 * <b>ADMINISTRATOR MODE</b>
 * <p>display all routes from database, admin can delete route</p>
 */
public class AdminSearch {
    private final Database database = new Database();
    private final Gui gui = new Gui();


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
    private FlowPane ITEM_CONTAINER;

    public void initialize(){
        ArrayList<TrainRoute> allTrainRoutes = null;

        allTrainRoutes = database.loadTrainRoutesFromDB(Database.defaultDate);

        ITEM_CONTAINER.getChildren().clear();   // VYMAZE TIE ITEMY CO TAM BOLI
        // VLOZIM VSETKY LISTKY (podľa defaultneho datumu)
        try{
            if(allTrainRoutes == null)
                return;

            for(TrainRoute route : allTrainRoutes){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("adminRouteItem.fxml"));

                BorderPane item = fxmlLoader.load();
                AdminRouteItemController routeItemController = fxmlLoader.getController();

                // TU MENIM TEXT PRE KONKRETNY ITEM
                routeItemController.setData(route.getFrom(), route.getTo(), route.getDepartureTimeString(), route.getArrivalTimeString());

                routeItemController.setUpTicketID(route.getId());    // NASTAVÍ ID TICKETU
                ITEM_CONTAINER.getChildren().add(item);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }


    }
}
