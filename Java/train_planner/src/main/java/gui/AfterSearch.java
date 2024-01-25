package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import models.Database;
import models.persons.Passenger;
import models.train.TrainRoute;
import models.train.Wagon;

import java.io.IOException;
import java.util.ArrayList;

/**
 * <p>At this screen, all tickets are stored in the database that match the search request are displayed as "cards" with information about the routes.</p>
 */
public class AfterSearch {
    //private final Passenger actual = Passenger.acutalLoggedUser;
    private final Database database = new Database();
    private final Gui gui = new Gui();


    // MENU
    @FXML
    private VBox homeButton;
    @FXML
    private VBox walletButton;
    @FXML
    private VBox profileButton;
    @FXML
    private VBox logoutButton;


    // HOME SCENE JE VLASTNE SEARCH (vyhladaj cestu)
    public void clickHomeButton() throws IOException {
        //System.out.println("KLIKOL SI NA HOME BUTTON");

        if(Passenger.acutalLoggedUser == null) {
            gui.changeScene("login.fxml");
        }
        else{
            gui.changeScene("search.fxml");
        }

    }
    // WALLET SCENA (zobrazí aktuálny kredit, dovolí nabyť kredit)
    public void clickWalletButton() throws  IOException{
        //System.out.println("KLIKOL SI NA WALLET BUTTON");
        gui.changeScene("wallet.fxml");

    }
    // PROFILE SCENA (MENO, PRIEZVISKO, INFO, MOZNOSŤ NASTAVIŤ INFORMACIE O SEBE(na dalsej screene), AKTUALNE KÚPENE LISTKY)
    public void clickProfileButton() throws  IOException{
        //System.out.println("KLIKOL SI NA PROFILE BUTTON");
        gui.changeScene("profile.fxml");

    }
    // ODHLASI UZIVATELA A PREJDE NA LOGIN PAGE

    public void clickLogoutButton() throws  IOException{
        System.out.println("Uživatel odhlásený !");

        // VYSKAKOVACIE OKNO
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("LOG OUT");
        alert.setHeaderText(null);
        if(Passenger.acutalLoggedUser != null){ alert.setContentText("Uživateľ " + Passenger.acutalLoggedUser.getUsername() + " bol odhlásený."); }
        else{ alert.setContentText("Uživateľ nebol odhlásený."); }
        alert.showAndWait();

        Passenger.acutalLoggedUser = null;
        gui.changeScene("login.fxml");

    }
    // END MENU


    ArrayList<TrainRoute> allTrainRoutes = null;    // VŠETKY TRASY



    @FXML
    private FlowPane ITEM_CONTAINER;
    @FXML
    private Text searched_date;
    @FXML
    private Text searched_from;
    @FXML
    private Text searched_to;
    @FXML
    private Text searched_time;

    // NASTAVÍ SEARCH INFO ZO Search SCREEN, PO STLACENI SEARCH TLAČIDLA
    public void displaySearchInfo(String date, String time, String from, String to){
        searched_date.setText(date);
        searched_time.setText(time);
        searched_from.setText(from);
        searched_to.setText(to);
    }

    public void initialize(){

        ITEM_CONTAINER.getChildren().clear();   // VYMAZE TIE ITEMY CO TAM BOLI


        Passenger actual = Passenger.acutalLoggedUser;
        System.out.println("MENO: " + actual.getName() + "  PRIEZVISKO: " + actual.getSurname());
        System.out.println("ID: " + actual.id + "  MONEY: " + actual.getMoney());

        // do allTrainRoutes načíta ArrayList kde sú uložene všetky TrainRoutec (cesty vlakov)
        // allTrainRoutes = database.loadTrainRoutesFromDB(Database.todayDate);

        // NASTAVÍ HLADANY DATUM, FROM, TO
        displaySearchInfo(Database.searchRequest[0], Database.searchRequest[1], Database.searchRequest[2], Database.searchRequest[3]);

        // NACITANE SÚ v Database.searchTrainRoutes ZO SCREEN SEARCH
        // AK PREJDEM NA AfterSearch, tak vypíšem z Database.searchTrainRoutes vsetky itemy DYNAMICKY
        try{
            if(Database.searchTrainRoutes == null)
                return;

            for(TrainRoute route : Database.searchTrainRoutes){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("routeItem.fxml"));

                BorderPane item = fxmlLoader.load();
                RouteItemController routeItemController = fxmlLoader.getController();

                // TU MENIM TEXT PRE KONKRETNY ITEM

                // PRECHÁDZAM V TRAIN ROUTE CEZ VŠETKY VAGÓNY
                // HLADAM NAJNIŽŠIU CENU CESTY
                int lowerPriceForSeat = route.setOfWagons.get(0).seatPrice; // NACITANIE PRVEJ CENY
                for(Wagon wagon : route.setOfWagons){
                    if(wagon.seatPrice < lowerPriceForSeat){
                        lowerPriceForSeat = wagon.seatPrice;
                    }
                }
                //routeItemController.setData(route.getFrom(), route.getTo(), route.getDepartureTimeString(), route.getArrivalTimeString(), "150");
                routeItemController.setData(route.getFrom(), route.getTo(), route.getDepartureTimeString(), route.getArrivalTimeString(), String.valueOf(lowerPriceForSeat));
                // NASTAVENIE ID PRE DANY TICKET, ABY SOM PRI KUPOVANI VEDEL PRACOVAŤ S DANÝM TICKETOM
                //item.setId("NASTAVENE_ID");
                // ROUTE ID = route.getId()
                //routeItemController.setUpTicketID(15);    // NASTAVÍ ID TICKETU
                routeItemController.setUpTicketID(route.getId());    // NASTAVÍ ID TICKETU

                ITEM_CONTAINER.getChildren().add(item); // PRIDÁ CELÝ ITEM
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
//        for (TrainRoute route : allTrainRoutes) {
//            System.out.println("––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––");
//            System.out.println("ID: " + route.getId() + "  FROM: " + route.getFrom() + "  TO: " + route.getTo() + "  DATE: " + route.getRouteDate());
//            System.out.println("Departure Time: " + route.getDepartureTime() + "  Arrival Time: " + route.getArrivalTime());
//            System.out.println("––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––");

//        }


    }
}
