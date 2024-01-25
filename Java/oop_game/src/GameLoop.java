import characters.*;

// INTERFACE JE AKO ŠABLÓNA
public interface GameLoop {
    Person actualPlayer = null;

    String readTextFromConsole();
    void setDifficulty();
    void createPlayer();
    void mainMenu();
}
