package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.Database;
import models.items.Ticket;
import models.persons.Passenger;
import models.train.TrainRoute;

import java.io.IOException;

/**
 * PROFILE PAGE displays all bought tickets for actual logged user
 */
public class Profile {

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
        System.out.println("KLIKOL SI NA HOME BUTTON");

        if(Passenger.acutalLoggedUser == null) {
            gui.changeScene("login.fxml");
        }
        else{
            gui.changeScene("search.fxml");
        }

    }
    // WALLET SCENA (zobrazí aktuálny kredit, dovolí nabyť kredit)
    public void clickWalletButton() throws  IOException{
        System.out.println("KLIKOL SI NA WALLET BUTTON");
        gui.changeScene("wallet.fxml");

    }
    // PROFILE SCENA (MENO, PRIEZVISKO, INFO, MOZNOSŤ NASTAVIŤ INFORMACIE O SEBE(na dalsej screene), AKTUALNE KÚPENE LISTKY)
    public void clickProfileButton() throws  IOException{
        System.out.println("KLIKOL SI NA PROFILE BUTTON");
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

    @FXML
    private FlowPane ITEM_CONTAINER;
    @FXML
    private Text USERNAME_TEXT;

    public void initialize(){
        Passenger.acutalLoggedUser.updateTickets(); // NACITA AKTUALNE LISTKY Z DB

        ITEM_CONTAINER.getChildren().clear();   // VYMAZE TIE ITEMY CO TAM BOLI


        Passenger actual = Passenger.acutalLoggedUser;
        USERNAME_TEXT.setText( "USERNAME: " + actual.getUsername() );    // NASTAVI NA GUI USERNAME
        System.out.println("MENO: " + actual.getName() + "  PRIEZVISKO: " + actual.getSurname());
        System.out.println("ID: " + actual.id + "  MONEY: " + actual.getMoney());

        // DYNAMICKY VYPÍŠE KÚPENÉ LÍSTKY PRIHLÁSENÉHO USERA
        try{

            for(Ticket ticket : Passenger.acutalLoggedUser.ticketInventory){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ticketItem.fxml"));

                BorderPane item = fxmlLoader.load();
                TicketItemController ticketItemController = fxmlLoader.getController();

                // String from, String to, String fromTime, String toTime, String price, String username, String routeID, String userID
                //ticketItemController.setData(ticket.from, ticket.to, ticket.departureTime, ticket.arrivalTime, "200€", "user123", ticket.routeID, ticket.userID);
                ticketItemController.setData(ticket.from, ticket.to, ticket.departureTime, ticket.arrivalTime, ticket.price+"€", actual.getUsername(), ticket.routeID, ticket.userID, ticket.routeDate);

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
