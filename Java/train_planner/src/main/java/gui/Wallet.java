package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.Database;
import models.persons.Passenger;

import java.io.IOException;

/**
 * on WALLET PAGE can actual logged user fill up his account
 */
public class Wallet {

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

    @FXML
    private Text USERNAME_TEXT;
    @FXML
    private Text MONEY_TEXT;
    @FXML
    private VBox VISA_FILL_CONTAINER;
    @FXML
    private VBox VISA_FILL_INFO;
    @FXML
    private TextField MONEY_TO_FILL;

    public void initialize(){
        VISA_FILL_CONTAINER.getChildren().clear();   // VYMAZE NA ZACIATKU FORMULAR PRE CISLO KARTY...

        Passenger actual = Passenger.acutalLoggedUser;

        USERNAME_TEXT.setText("USERNAME: " + actual.getUsername());
        MONEY_TEXT.setText("MONEY:  [ " + actual.getMoney() + "€ ]");

    }

    public void doApplePAY() throws IOException{
        System.out.println("MAKE APPLE PAY");
        if(MONEY_TO_FILL.getText().isEmpty()){  // NEZADAL SI SUMU NA DOBYTIE !
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("CANT FILL CREDIT !");
            alert.setHeaderText(null);
            alert.setContentText("YOU MUST ENTER AN AMOUNT TO FILL  !");
            alert.showAndWait();
            return;
        }
        int sumaNaDobytie = Integer.parseInt(MONEY_TO_FILL.getText());                                              // ZÍSKA SUMU NA DOBYTIE Z FORMULÁRA
        Passenger.acutalLoggedUser.setMoney( Passenger.acutalLoggedUser.getMoney() + sumaNaDobytie);                // AKTUALIZUJE PENIAZE USEROVI
        database.updateUserMoney(Passenger.acutalLoggedUser.getUserID(), Passenger.acutalLoggedUser.getMoney());    // AKTUALIZUJE PENIAZE USEROVI V DB

        // VYSKAKOVACIE OKNO
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("CREDIT FILLED !");
        alert.setHeaderText(null);
        alert.setContentText("PAYMENT SUCSESSFULL  !\nYOUR NEW CREDIT: " + Passenger.acutalLoggedUser.getMoney());
        alert.showAndWait();

        gui.changeScene("wallet.fxml");
    }

    public void showVISA_PAYMENT() throws IOException{ // OTVORÍ MOŽNOSTI PRE PLATBU KARTOU
        System.out.println("MAKE VISA PAY");
        if(MONEY_TO_FILL.getText().isEmpty()){  // NEZADAL SI SUMU NA DOBYTIE !
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("CANT FILL CREDIT !");
            alert.setHeaderText(null);
            alert.setContentText("YOU MUST ENTER AN AMOUNT TO FILL  !");
            alert.showAndWait();
            return;
        }
        VISA_FILL_CONTAINER.getChildren().add(VISA_FILL_INFO);
    }

    @FXML
    private TextField VISA_CARD_NUMBER;
    @FXML
    private TextField VISA_DATE;
    @FXML
    private TextField VISA_CVV;

    public void PAY_VISA() throws IOException{
        Alert alert = new Alert(Alert.AlertType.ERROR);

        if(VISA_CARD_NUMBER.getText().isEmpty() || VISA_DATE.getText().isEmpty() || VISA_CVV.getText().isEmpty()){  // AK NEZADÁŠ VŠETKY ÚDAJE Z KARTY
            alert.setTitle("CANT FILL CREDIT !");
            alert.setHeaderText(null);
            alert.setContentText("YOU MUST ENTER ALL CARD INFORMATIONS  !");
            alert.showAndWait();
            return;
        }
        String cardNumber = VISA_CARD_NUMBER.getText();     // ZÍSKAM ČÍSLO KARTY
        String cardDate = VISA_DATE.getText();              // ZISKAM DATUM KARTY
        String cardCVV = VISA_CVV.getText();                // ZISKAM CVV KARTY

        if(cardNumber.length() != 16){
            alert.setTitle("CANT FILL CREDIT !");
            alert.setHeaderText(null);
            alert.setContentText("WRONG CARD NUMBER  !");
            alert.showAndWait();
            return;
        }
        if(cardDate.length() != 5 || !cardDate.contains("/")){
            alert.setTitle("CANT FILL CREDIT !");
            alert.setHeaderText(null);
            alert.setContentText("WRONG CARD DATE  !");
            alert.showAndWait();
            return;
        }
        if(cardCVV.length() != 3){
            alert.setTitle("CANT FILL CREDIT !");
            alert.setHeaderText(null);
            alert.setContentText("WRONG CVV  !");
            alert.showAndWait();
            return;
        }



        int sumaNaDobytie = Integer.parseInt(MONEY_TO_FILL.getText());                                              // ZÍSKA SUMU NA DOBYTIE Z FORMULÁRA
        Passenger.acutalLoggedUser.setMoney( Passenger.acutalLoggedUser.getMoney() + sumaNaDobytie);                // AKTUALIZUJE PENIAZE USEROVI
        database.updateUserMoney(Passenger.acutalLoggedUser.getUserID(), Passenger.acutalLoggedUser.getMoney());    // AKTUALIZUJE PENIAZE USEROVI V DB


        // VYSKAKOVACIE OKNO
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("CREDIT FILLED !");
        alert.setHeaderText(null);
        alert.setContentText("PAYMENT SUCSESSFULL  !\nYOUR NEW CREDIT: " + Passenger.acutalLoggedUser.getMoney());
        alert.showAndWait();
        gui.changeScene("wallet.fxml");

    }


}
