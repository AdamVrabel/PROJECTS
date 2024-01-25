package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.Database;
import models.items.Item;
import models.items.Ticket;
import models.persons.Passenger;
import models.train.TrainRoute;
import models.train.Wagon;

import java.io.IOException;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;

/**
 * ONE "ITEM" FOR TRAIN ROUTE IN AFTERSEARCH PAGE
 */
public class RouteItemController {
    private final Database database = new Database();
    private final Gui gui = new Gui();

    @FXML
    Text COMPANY;          // TEXT PRE NÁZOV ŽEL. SPOLOČNOSTI
    @FXML
    Text PRICE;            // CENA (napr from 120€...)
    @FXML
    Text FROM_TIME;        // ČAS ODCHODU
    @FXML
    Text FROM_LOCATION;    // MIESTO ODCHODU
    @FXML
    Text TO_TIME;          // ČAS PRÍCHODU
    @FXML
    Text TO_LOCATION;      // MIESTO PRÍCHODU
    @FXML
    Text duration;          // DĹŽKA TRVANIA CESTY
    @FXML
    VBox ECONOMY_BTN;           // "TLAČIDLLO" ECONOMY CLASS
    @FXML
    VBox FIRST_CLASS_BTN;       // "TLAČIDLLO" FIRST CLASS
    @FXML
    VBox BUY_TICKET_BTN;        // "TLAČIDLLO" BUY TICKET
    @FXML
    Text buyTicketTEXT;

    // KTORÁ JE TRUE, NA TÚ SOM KLIKOL
    boolean isChoosenEconomy = false;
    boolean isChoosenFirstClass = false;

    public void initialize(){

    }

    // NASTAVI DATA PRE ITEM
    public void setData(String from, String to, String fromTime, String toTime, String price){
        FROM_LOCATION.setText(from);
        TO_LOCATION.setText(to);
        FROM_TIME.setText(fromTime);
        TO_TIME.setText(toTime);
        PRICE.setText("from " + price + "€");

        // VYPOČET DURATION

        LocalTime localTime1 = LocalTime.parse(fromTime);
        LocalTime localTime2 = LocalTime.parse(toTime);

        Duration trvanie = Duration.between(localTime1, localTime2);
        long minutes = trvanie.toMinutes();

        String timeDiff = String.format("%2dh %2dmin", minutes / 60, minutes % 60);

        duration.setText(timeDiff);

    }
    // PRI DYNAMICKOM VYTVÁRANÍ SI NASTAVÍM ID TICKETU Z DATABÁZY, ABY PO KLIKNUTÍ NA BUY SOM VEDEL PRACOVAŤ S TICKETOM
    TrainRoute localTrainRoute = null;
    int localPrice = 0;
    public void setUpTicketID(int ticketID) {

        //System.out.println(" TICKET ID: " + ticketID);
        BUY_TICKET_BTN.setId(""+ticketID);

        // NAJDE ROUTE PODLA ID V database.searchTrainRoutes
        for(TrainRoute route : Database.searchTrainRoutes){
            if(String.valueOf(route.getId()).equals(BUY_TICKET_BTN.getId())){    // AK NAJDEM V POLI VYHLADANYCH TEN NA KTORÝ KLIKNEM ....
                localTrainRoute = route;
            }
        }
        //TrainRoute localTrainRoute = database.getTrainRoute(BUY_TICKET_BTN.getId());

        // PRIDA EVENT HANDLER NA KLIKNUTIE NA BUY BUTTON
        BUY_TICKET_BTN.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Passenger.acutalLoggedUser.updateTickets(); // NACITA AKTUALNE LISTKY Z DB

                //System.out.println("# mouse click detected! " + mouseEvent.getSource());
                System.out.println("# CLICKED BUY TICKET, ROUTE ID [" + BUY_TICKET_BTN.getId() + "]");

                // IF KONTROLA CI SI ZVOLIL ECONOMY ALEBO FIRST CLASS
                if(isChoosenFirstClass == false && isChoosenEconomy == false){
                    System.out.println("ERROR: ZVOĽ TYP VOZNA !");
                    // MAS ZVOLENY LISTOK, MOZES HO KUPIT
                    // VYSKAKOVACIE OKNO
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("CHOOSE CLASS OF WAGON !");
                    alert.setHeaderText(null);
                    alert.setContentText("At first, choose seat type from: \n    ECONOMY CLASS or FIRST CLASS");
                    //alert.setContentText("ACTUAL USER: " + Passenger.acutalLoggedUser.getUsername() + "\n" +
                    //        "BOUGHT TICKET FOR ROUTE ID: [" + BUY_TICKET_BTN.getId() + "]"
                    //);
                    alert.showAndWait();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    // MAS ZVOLENY LISTOK, MOZES HO KUPIT
                    // SKONTROLUJEM CI UZ NEMA USER KUPENY LISTOK NA TUTO CESTU
                    String clickedRouteID = BUY_TICKET_BTN.getId();

                    for(Ticket actualTicket : Passenger.acutalLoggedUser.ticketInventory){
                        if( actualTicket.routeID.equals(clickedRouteID) ){
                            System.out.println(" UZ TENTO MAS !");
                            // VYSKAKOVACIE OKNO
                            alert.setTitle("CANT BUY TICKET !");
                            alert.setHeaderText(null);
                            alert.setContentText("YOU ALREADY HAVE THIS TICKET !");
                            alert.showAndWait();
                            return;
                        }
                    }

                    // KONTROLA CI MA DOST PENAZI
                    if(Passenger.acutalLoggedUser.getMoney() < localPrice){
                        System.out.println("NEDOSTATOK PENAZI !");
                        // VYSKAKOVACIE OKNO
                        alert.setTitle("CANT BUY TICKET !");
                        alert.setHeaderText(null);
                        alert.setContentText("NOT ENOUGH OF MONEY ! \n" + "YOUR CREDIT = ["+Passenger.acutalLoggedUser.getMoney()+"€]\n"
                                +
                                "PLEASE, GO TO WALLET, AND FILL UP CREDIT");
                        alert.showAndWait();
                        return;
                    }


                    // UŽ MOZE KUPIŤ LÍSTOK
                    // VYSKAKOVACIE OKNO
                    alert.setTitle("TICKED BOUGHT !");
                    alert.setHeaderText(null);
                    alert.setContentText("ACTUAL USER: " + Passenger.acutalLoggedUser.getUsername() + "\n" +
                            "BOUGHT TICKET FOR ROUTE ID: [" + BUY_TICKET_BTN.getId() + "] \nPRICE: " + localPrice + "€"
                    );
                    alert.showAndWait();

                    // PRIDÁ USEROVI ID, TICKETU DO DATABAZY
                    // UPDATE-USER TO DATABASE
                    //database.updateUserTICKET(Passenger.acutalLoggedUser.getUserID(), BUY_TICKET_BTN.getId());  // UPDATE (actualUser, CLICKED TICKET ROUTE_ID)


                    // vytvorí TICKET(item)
                    //TrainRoute clickedTrainRoute = database.getTrainRoute(BUY_TICKET_BTN.getId());
                    //Ticket newTicket = new Ticket(clickedTrainRoute, Passenger.acutalLoggedUser.getUserID());
                    //////localTrainRoute = database.getTrainRoute(BUY_TICKET_BTN.getId());
                    Ticket newTicket = new Ticket(localTrainRoute, Passenger.acutalLoggedUser.getUserID(), localPrice);


                    // DALŠIA TABULKA V DB, PREDANE LISTKY, KDE BUDE ID USERA A ID CESTY
                    // PRIDÁ ZAZNAM DO TABULKY "tickets" (routeID, userID)
                    int routeID = Integer.parseInt(BUY_TICKET_BTN.getId());
                    int actualUserID = Passenger.acutalLoggedUser.getUserID();
                    database.addBoughtTicketToDB(routeID, actualUserID, localPrice);

                    // AKTUALIZUJE Passenger.actualLoggedUser ()
                    Passenger.acutalLoggedUser.updateTickets(); // NACITA AKTUALNE LISTKY Z DB

                    // ODČÍTA MU SUMU ZA LÍSTOK Z KREDITU
                    Passenger.acutalLoggedUser.setMoney( Passenger.acutalLoggedUser.getMoney() - localPrice );
                    database.updateUserMoney(actualUserID, Passenger.acutalLoggedUser.getMoney());  // AKTUALIZUJE PENIAZE AJ V DATABAZE

                    try {
                        gui.changeScene("profile.fxml");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }




            }
        });

        // PRIDA EVENT HANDLER NA KLIKNUTIE NA ECONOMY BUTTON
        ECONOMY_BTN.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent) {

                isChoosenEconomy = true;   // AK KLIKNEM NA ECONOMY, potom pri kontrole na BUY TICKET kontrolujem ktoré je kliknuté
                isChoosenFirstClass = false;

                Wagon tmpWagon = localTrainRoute.najlacnejsiWagon("ECONOMY");
                String price = String.valueOf(tmpWagon.seatPrice);
                PRICE.setText(price + "€");
                localPrice = Integer.parseInt(price);   // PREVEDIE DANU CENU, ABY KED KLIKNEM NA BUY, ABY SOM VEDEL AKÚ SUMU MÁM ZTIAHNUŤ Z KREDITU




            }
        });

        // PRIDA EVENT HANDLER NA KLIKNUTIE NA FIRST CLASS BUTTON
        FIRST_CLASS_BTN.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent) {

                isChoosenEconomy = false;
                isChoosenFirstClass = true; // AK KLIKNEM NA FIRSTCLASS, potom pri kontrole na BUY TICKET kontrolujem ktoré je kliknuté

                Wagon tmpWagon = localTrainRoute.najlacnejsiWagon("FIRSTCLASS");
                String price = String.valueOf(tmpWagon.seatPrice);
                PRICE.setText(price + "€");
                localPrice = Integer.parseInt(price);   // PREVEDIE DANU CENU, ABY KED KLIKNEM NA BUY, ABY SOM VEDEL AKÚ SUMU MÁM ZTIAHNUŤ Z KREDITU

            }
        });

    }


}
