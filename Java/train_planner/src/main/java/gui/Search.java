package gui;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import models.Database;
import models.persons.Passenger;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <p>search page</p>
 * <p>after successful login</p>
 * <p>work with the loaded user from the database</p>
 * <p>On this page can search for train route, when enter search information is correct format and click on SEARCH button, screen change to AFTER SEARCH SCREEN.</p>
 */
public class Search {
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
    public void clickHomeButton() throws  IOException{
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


    // SEARCH
    @FXML
    private HBox searchBUTTON;
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
            //Database.searchRequest[1] = "2023-04-08";          // ZAPISE DEFAULTNY DATUM DO tmp DB
            Database.searchRequest[1] = Database.defaultDate;    // ZAPISE DEFAULTNY DATUM
            return;
        }

        LocalDate nacitanyDate = datePicker.getValue();
        System.out.println("NACITANY DATUM: " + nacitanyDate.toString());
        String formatovanyDate = nacitanyDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        System.out.println("FORMATOVANY DATUM: " + formatovanyDate);

        //Database.todayDate = nacitanyDate.toString();
        Database.searchRequest[0] = nacitanyDate.toString();    // ZAPISE NACITANY DATUM
    }
    @FXML
    private TextField timeInput;
    @FXML
    private Text errorSearchText;


    // AK SA KLIKNE NA BUTTON SEARCH
    public void searchRoute() throws IOException{

        // AK STLACI BUTTON, TAK PRV VŠETKO VYNULUJE, ABY SEARCH NASTAVIL KOREKTNE
        Database.searchTrainRoutes = null;

        String from = FROM_input.getText();
        String to = TO_input.getText();
        String time = timeInput.getText();


        if(datePicker.getValue() == null){      // AK JE DATUM NEZADANY, NASTAVÍ DEFAULTNU HODNOTU
            //Database.defaultDate = "2023-04-08";
            Database.searchRequest[0] = Database.defaultDate;
        }

        if( FROM_input.getText().isEmpty() || TO_input.getText().isEmpty() ) {
            if (FROM_input.getText().isEmpty()) {
                errorSearchText.setText("Musíš zadať začiatok !");
            }
            else{
                errorSearchText.setText("Musíš zadať cieľ.");
            }
        }
        else{
            errorSearchText.setText("");
            // DOTAZ NA DATABAZU SEARCH
            System.out.println("VYHĽADÁVAM CESTY V DATABÁZE: FROM: " + from + " TO: " + to + " DATUM: " + Database.defaultDate + " CAS: " + Database.defaultTime);


            // NASTAVI POLE STRINGOV Z MOJHO SEARCH REQUEST ///////////////////////////
            // ABY SOM V AfterSearch MOHOL PEKNE VYPISAT SEARCH INFO
            // SET UP SEARCH INFO
            //Database.searchRequest[1] = "10:00";    // SET DEFAULT TIME
            Database.searchRequest[2] = "";           // DELETE FROM
            Database.searchRequest[3] = "";           // DELETE TO

            // AK NEBOL DATUM ZADANY, NASTAVÍ DEFAULTNY, AK BOL, UŽ SA NASTAVIL v getDate()
            if(datePicker.getValue() == null){
                Database.searchRequest[0] = Database.defaultDate;   // DATE
            }
            // AK NEBOL ČAS ZADANY, NASTAVÍ DEFAULTNY
            if(timeInput.getText().isEmpty()){
                System.out.println(" TIME JE PRAZDNY !");
                Database.searchRequest[1] = Database.defaultTime;    // SET DEFAULT TIME
            }
            else{Database.searchRequest[1] = time;} // AK BOL ČAS ZADANY, NASTAVÍ HO

            Database.searchRequest[2] = from;           // FROM
            Database.searchRequest[3] = to;             // TO
            ///////////////////////////////////////////////////////////////////////////

            // NACITA Z DATABAZY PODLA DANEHO SEARCHU
            Database.searchTrainRoutes = database.loadTrainRoutesBySearchRequest();
            //Database.searchTrainRoutes = database.loadTrainRoutesFromDB(from, to, Database.todayDate);
            gui.changeScene("afterSearch.fxml");
        }

    }




    // END SEARCH



}
