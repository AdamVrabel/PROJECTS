package gui;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import models.Database;

import java.time.Duration;
import java.time.LocalTime;

/**
 * ONE "ITEM" FOR BOUGHT TICKET IN PROFILE PAGE
 */
public class TicketItemController {

    private final Database database = new Database();

    @FXML
    Text NADPIS;
    @FXML
    Text PRICE;            // CENA
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
    Text USER_TEXT;          // meno majitela cesty
    @FXML
    Text ROUTE_ID_TEXT;
    @FXML
    Text USER_ID_TEXT;

    // NASTAVI DATA PRE ITEM
    public void setData(String from, String to, String fromTime, String toTime, String price, String username, String routeID, String userID, String date){
        FROM_LOCATION.setText(from);
        TO_LOCATION.setText(to);
        FROM_TIME.setText(fromTime);
        TO_TIME.setText(toTime);
        PRICE.setText("PRICE: " + price);
        USER_TEXT.setText(username);
        ROUTE_ID_TEXT.setText("ROUTE ID : " + routeID);
        USER_ID_TEXT.setText("USER ID : " + userID);

        NADPIS.setText("TICKET  [" +date+ "]");


        // VYPOČET DURATION

        LocalTime localTime1 = LocalTime.parse(fromTime);
        LocalTime localTime2 = LocalTime.parse(toTime);

        Duration trvanie = Duration.between(localTime1, localTime2);
        long minutes = trvanie.toMinutes();

        String timeDiff = String.format("%2dh %2dmin", minutes / 60, minutes % 60);

        duration.setText(timeDiff);

    }

}
