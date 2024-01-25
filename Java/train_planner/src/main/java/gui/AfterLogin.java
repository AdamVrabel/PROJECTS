package gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import models.Database;
import models.persons.Passenger;
import models.train.TrainRoute;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AfterLogin {

    @FXML
    private Text welcomeText;
    @FXML
    private Text nameText;
    @FXML
    private HBox ticketsButton;
    @FXML
    private HBox moneysButton;
    @FXML
    private HBox settingsButton;
    @FXML
    private Button logoutButton;


    @FXML
    private VBox routesContainer;   // CONTAINER FOR NEW ITEMS (route has one container)
    // KONTAINER KDE SA UKLADAJU GridPane, jeden GridPane je jedna cesta


    // TESTING STATIC TICKET ITEMS
    @FXML
    private GridPane STATIC_TICKET_PRINT1; // STATICKE ITEMy pre výpis cesty, NEPOUŽÍVAŤ V PROGRAME, maju sa vytvoriť dynamicky z databázy do routesContainer
    @FXML
    private GridPane STATIC_TICKET_PRINT2;
    @FXML
    private GridPane STATIC_TICKET_PRINT3;
    @FXML
    private GridPane STATIC_TICKET_PRINT4;


    @FXML
    private Button search;
    @FXML
    private TextField FROM_input;
    @FXML
    private TextField TO_input;
    @FXML
    private DatePicker datePicker;




    private final Passenger actual = Passenger.acutalLoggedUser;
    private final Database database = new Database();
    private final Gui gui = new Gui();

    ArrayList<TrainRoute> allTrainRoutes = null;
    ArrayList<TrainRoute> searchTrainRoutes = null; // po vyhľadaní naplní toto pole

    String todayDate = "2023-04-08";

    // NACITA DATUM Z INPUTU
    public void getDate(ActionEvent event) throws IOException{
        LocalDate nacitanyDate = datePicker.getValue();
        System.out.println("NACITANY DATUM: " + nacitanyDate.toString());
        String formatovanyDate = nacitanyDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        System.out.println("FORMATOVANY DATUM: " + formatovanyDate);

        todayDate = nacitanyDate.toString();

    }


    public void initialize(){

        // VYMAZE STATICKE VYPISY ROUTEs
        //STATIC_TICKET_PRINT1.setVisible(false); // IBA MU VYPNE VIDITELNOST, NO OSTANE TAM FYZICKY
        routesContainer.getChildren().remove(STATIC_TICKET_PRINT1); // VYMATE STATIC_TICKET_PRINT1
        routesContainer.getChildren().remove(STATIC_TICKET_PRINT2);
        routesContainer.getChildren().remove(STATIC_TICKET_PRINT3);
        routesContainer.getChildren().remove(STATIC_TICKET_PRINT4);


        welcomeText.setText("Hello, " + actual.getUsername() + " !");
        if(actual.getName() != null && actual.getSurname() != null){
            nameText.setText( actual.getName() + " " + actual.getSurname() );
        }
        System.out.println("MENO: " + actual.getName() + "  PRIEZVISKO: " + actual.getSurname());
        System.out.println("ID: " + actual.id + "  MONEY: " + actual.getMoney());


        // do trainRoutes načíta ArrayList kde sú uložene TrainRoutec (cesty vlakov)
        allTrainRoutes = database.loadTrainRoutesFromDB(todayDate);
        for (TrainRoute route : allTrainRoutes) {
            System.out.println("––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––");
            System.out.println("ID: " + route.getId() + "  FROM: " + route.getFrom() + "  TO: " + route.getTo() + "  DATE: " + route.getRouteDate());
            System.out.println("Departure Time: " + route.getDepartureTime() + "  Arrival Time: " + route.getArrivalTime());
            System.out.println("––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––");

//            VYGENERUJE DO JavaFX ITEM pre jednu route
//            System.out.println("### TEMPORARY PRINT ###");
//            System.out.println(route.getRouteDate().toString());
//            System.out.println(route.getFrom());
//            System.out.println(route.getTo());
//            System.out.println(route.getDepartureTime().toString());
//            System.out.println(route.getArrivalTime().toString());
//            System.out.println("#######################");

            // MAKE DYNAMIC ITEM TO JAVAFX, for each route in database
            routesContainer.getChildren().add( makeGridPane(route.getRouteDate().toString() , route.getFrom(), route.getTo(), route.getDepartureTime().toString(), route.getArrivalTime().toString(), route.getId()) );
        }


    }


    public void logout() throws IOException{
        System.out.println("Uživatel odhlásený !");
        Passenger.acutalLoggedUser = null;

        gui.changeScene("login.fxml");
    }

    public void doClick() throws IOException{   // TESTING ONLY
        System.out.println("CLICK !");


        // PRIDANIE PRVKU DO VBox kontainera                TEST
        //Text text = new Text("Skusam veci, dufam, že fungujú");
        //routesContainer.getChildren().add(text);
        //routesContainer.getChildren().add(makeITEM());

        routesContainer.getChildren().add( makeGridPane("2023-04-08" , "Bratislava", "Kosice", "12:00", "15:00", 5) ); // PRIDA ZÁZNAM CESTY DO VBox-u
        routesContainer.getChildren().add( makeGridPane("2023-04-08" , "Humenné", "Trnava", "10:00", "24:00", 4) ); // PRIDA ZÁZNAM CESTY DO VBox-u


    }


    ///////////////
    private Text makeITEM(){

        Text text = new Text("Vytvorí mi tento text, WAAAAU.");

        return text;
    }

    // CREATE AND RETURN JAVAFX ITEM FOR DISPLAY ROUTE
    private GridPane makeGridPane(String local_date, String local_from, String local_to, String local_departure, String local_arrival, int routeID){
        GridPane gridPane = new GridPane();

        //Text text1 = new Text("DATE:  2023-04-08");
        //Text text2 = new Text("FROM:  Bratislava");
        //Text text3 = new Text("TO:  Košice");
        //Text text4 = new Text("BUY TICKET");
        //Text text5 = new Text("DEPARTURE:  12:00");
        //Text text6 = new Text("ARRIVAL:  15:00");

        //  gridPane.add( ČO PRIDAVAM, COLUMN, ROW);
//        gridPane.add(text1, 0, 0);
//        gridPane.add(text2, 0, 1);
//        gridPane.add(text3, 0, 2);
//        gridPane.add(text4, 1, 0);
//        gridPane.add(text5, 1, 1);
//        gridPane.add(text6, 1, 2);
//
        gridPane.getStyleClass().add("dynamic_ticketBlock");   // set CSS class for GridPane                     // nastavíme CSS triedu pre GridPane
//
//        gridPane.getColumnConstraints().add(new ColumnConstraints(200, 200, 200));        // FIRST COLUMN WIDTH   (double minWidth, double prefWidth, double maxWidth)


        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        column1.setMaxWidth(200.0);
        column1.setMinWidth(200.0);
        column1.setPrefWidth(200.0);
        //gridPane.getColumnConstraints().add(column1);

        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        column2.setMinWidth(200.0);
        column2.setMinWidth(200.0);
        column2.setPrefWidth(200.0);
        //gridPane.getColumnConstraints().add(column2);

        ColumnConstraints column3 = new ColumnConstraints();
        column3.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        column3.setMinWidth(100.0);
        //column3.setMinWidth(200.0);
        //column3.setPrefWidth(200.0);
        //gridPane.getColumnConstraints().add(column3);


        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(25.0);
        row1.setPrefHeight(25.0);
        row1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        //gridPane.getRowConstraints().add(row1);

        RowConstraints row2 = new RowConstraints();
        row2.setMinHeight(25.0);
        row2.setPrefHeight(25.0);
        row2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        //gridPane.getRowConstraints().add(row2);

        RowConstraints row3 = new RowConstraints();
        row3.setMinHeight(25.0);
        row3.setPrefHeight(25.0);
        row3.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        //gridPane.getRowConstraints().add(row3);

        ///////////////////////////////////////////////////////////////////////////////
        gridPane.getColumnConstraints().addAll(column1, column2, column3);   // PRIDAM STLPCE
        gridPane.getRowConstraints().addAll(row1, row2, row3);               // PRIDAM RIADKY
        ///////////////////////////////////////////////////////////////////////////////

        // Create Text elements
        Text dateText = new Text("DATE:  " + local_date);
        dateText.setStrokeType(StrokeType.OUTSIDE);
        dateText.setStrokeWidth(0.0);
        dateText.setWrappingWidth(180.0);
        dateText.setFont(Font.font("Arial Black", 13.0));
        dateText.setFill(Color.BLACK);
        gridPane.add(dateText, 0, 0);

        Text fromText = new Text("FROM:  " + local_from);
        fromText.setStrokeType(StrokeType.OUTSIDE);
        fromText.setStrokeWidth(0.0);
        fromText.setWrappingWidth(180.0);
        fromText.setFont(Font.font("Arial Black", 13.0));
        fromText.setFill(Color.BLACK);
        gridPane.add(fromText, 0, 1);

        Text toText = new Text("TO:  " + local_to);
        toText.setStrokeType(StrokeType.OUTSIDE);
        toText.setStrokeWidth(0.0);
        toText.setWrappingWidth(180.0);
        toText.setFont(Font.font("Arial Black", 13.0));
        toText.setFill(Color.BLACK);
        gridPane.add(toText, 0, 2);

        Text buyText = new Text("BUY TICKET");
        buyText.setStrokeType(StrokeType.OUTSIDE);
        buyText.setStrokeWidth(0.0);
        //buyText.setWrappingWidth(180.0);
        buyText.setWrappingWidth(0);
        buyText.setFont(Font.font("Arial Rounded MT Bold", 18.0));
        buyText.setUnderline(true);
        buyText.setFill(Color.BLACK);
        buyText.setId("CLICKABLE_BUY_TICKET" + routeID);  // PRIDA ID BUY TICKET TEXTU        // KLIKANIE ZATIAĽ NEFUNGUJE
        buyText.getStyleClass().add("handCursor");       // PRIDA CSS class handCursor
        buyText.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
        gridPane.add(buyText, 2, 0);

        Text departureText = new Text("DEPARTURE:  " + local_departure);
        departureText.setStrokeType(StrokeType.OUTSIDE);
        departureText.setStrokeWidth(0.0);
        departureText.setWrappingWidth(180.0);
        departureText.setFont(Font.font("Arial Black", 13.0));
        departureText.setFill(Color.BLACK);
        gridPane.add(departureText, 1, 1);

        Text arrivalText = new Text("ARRIVAL:  " + local_arrival);
        arrivalText.setStrokeType(StrokeType.OUTSIDE);
        arrivalText.setStrokeWidth(0.0);
        arrivalText.setWrappingWidth(180.0);
        arrivalText.setFont(Font.font("Arial Black", 13.0));
        arrivalText.setFill(Color.BLACK);
        gridPane.add(arrivalText, 1, 2);

        VBox.setMargin(gridPane, new Insets(20, 0 , 20, 0));  // PADING NA KAZDY NOVY gridPane (zhora zdola 20px)
        //gridPane.setVgap(10);

        return gridPane;
    }

    // PRE "BUY TICKET" eventHandler na kliknutie
    private class MyEventHandler implements EventHandler<Event> {
        @Override
        public void handle(Event evt) {
            System.out.println("~CLICK ON BUY TICKET~");
            //System.out.println("~KLIKOL SOM NA " + ((Control)evt.getSource()).getId());
        }
    }

    // ZMENÍ TEXT V ZADANOM #TICKET_PRINTe (jeden item s jednou cestou)
    public void changeTextInTicketItem(){

    }

    public void doTicketsButton() throws  IOException{
        System.out.println("KLIKOL NA TICKETS !");
    }
    public void doMoneysButton() throws  IOException{
        System.out.println("KLIKOL NA MONEYS !");
    }
    public void doSettingsButton() throws  IOException{
        System.out.println("KLIKOL NA SETTINGS !");
    }


    // SEARCH TICKETS FROM, TO, DATE later...
    public void search(ActionEvent event) throws IOException{

        routesContainer.getChildren().clear();      // VYMAZE TIE CO TAM BOLI PREDTYM


        String fromINPUT = FROM_input.getText();
        String toINPUT = TO_input.getText();

        System.out.println("FROM: " + fromINPUT);
        System.out.println("TO: " + toINPUT);

        searchTrainRoutes = database.loadTrainRoutesFromDB(fromINPUT, toINPUT, todayDate);
        for (TrainRoute route : searchTrainRoutes){
//            System.out.println("––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––");
//            System.out.println("ID: " + route.getId() + "  FROM: " + route.getFrom() + "  TO: " + route.getTo() + "  DATE: " + route.getRouteDate());
//            System.out.println("Departure Time: " + route.getDepartureTime() + "  Arrival Time: " + route.getArrivalTime());
//            System.out.println("––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––");
            routesContainer.getChildren().add( makeGridPane(route.getRouteDate().toString() , route.getFrom(), route.getTo(), route.getDepartureTime().toString(), route.getArrivalTime().toString(), route.getId()) );
        }

    }



}
