/**
 * <p><b>FIIT HEROES</b></p>
 * @author Adam Vrabeľ
 * @version ZADANIE 3
 */
public class Main {
    public static int difficulty = 333;

    public static void main(String[] args) {
        System.out.println("ADAM VRABEL,  ZADANIE ZOOP");
        System.out.println("FIIT HEROES");
        System.out.println();

        Program run = new Program();

        run.setDifficulty();
        run.createPlayer();
        run.mainMenu();

        ///run.trySingleton();
        ///run.justTry();
    }
}