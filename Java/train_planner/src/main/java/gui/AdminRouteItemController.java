package gui;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.Database;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;

/**
 * <b>ADMINISTRATOR MODE</b>
 * <p>ONE "ITEM" FOR TRAIN ROUTE</p>
 */
public class AdminRouteItemController {
    private final Database database = new Database();
    private final Gui gui = new Gui();

    @FXML
    Text COMPANY;          // TEXT PRE NÁZOV ŽEL. SPOLOČNOSTI
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
    VBox deleteROUTE;        // "TLAČIDLLO" DELETE ROUTE


    public void initialize(){

    }

    // NASTAVI DATA PRE ITEM
    public void setData(String from, String to, String fromTime, String toTime){
        FROM_LOCATION.setText(from);
        TO_LOCATION.setText(to);
        FROM_TIME.setText(fromTime);
        TO_TIME.setText(toTime);

        // VYPOČET DURATION

        LocalTime localTime1 = LocalTime.parse(fromTime);
        LocalTime localTime2 = LocalTime.parse(toTime);

        Duration trvanie = Duration.between(localTime1, localTime2);
        long minutes = trvanie.toMinutes();

        String timeDiff = String.format("%2dh %2dmin", minutes / 60, minutes % 60);

        duration.setText(timeDiff);

    }
    // PRI DYNAMICKOM VYTVÁRANÍ SI NASTAVÍM ID TICKETU Z DATABÁZY, ABY PO KLIKNUTÍ NA DELETE SOM VEDEL VYMAZAŤ TICKET
    public void setUpTicketID(int ticketID) {

        //System.out.println(" TICKET ID: " + ticketID);
        deleteROUTE.setId(""+ticketID);

        // PRIDA EVENT HANDLER NA KLIKNUTIE NA DELETE BUTTON
        deleteROUTE.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                  // NACITA VSETKY CESTY Z DB
               // Passenger.acutalLoggedUser.updateTickets(); // NACITA AKTUALNE LISTKY Z DB

                //System.out.println("# mouse click detected! " + mouseEvent.getSource());
                System.out.println("# CLICKED DELETE ROUTE, ROUTE ID [" + deleteROUTE.getId() + "]");
                database.deleteRoute(deleteROUTE.getId());  // VYMAZE ROUTE PODLA ID Z DATABAZY

                try {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("DELETING !");
                    alert.setHeaderText(null);
                    alert.setContentText("CHOOSED ROUTE DELETED !");
                    gui.changeScene("adminSearch.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });


    }


}
