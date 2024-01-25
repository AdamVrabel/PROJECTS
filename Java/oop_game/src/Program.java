import characters.*;
import java.util.Scanner;

/**
 * {@code Program} implementuje INTERFACE {@code GameLoop}. <br>
 * Je to hlavný cyklus hry. <br>
 * Hra pozostáva z Hráča ( {@code Player} ) ktorý bojuje proti protivníkom ( {@code Enemy} ) <br>
 */
public class Program implements GameLoop {
    Person actualPlayer = null;
    private static final int DIFFICULTY_NUMBER = 15; // KONŠTANTA (final)
    Singleton programDatabase = Singleton.getInstance();    //GET INSTANCE SINGLETON DATABASE

    public void trySingleton(){
        System.out.println("Singleton EXAMPLE");
        Singleton database = Singleton.getInstance();

        database.info();

        Singleton databaza2 = Singleton.getInstance();

        if(database.equals(databaza2)){
            System.out.println("SÚ ROVNAKÉ !");
        }
        //database.addEnemy();
    }
    public void justTry(){
        System.out.println("JUST TRYING !");

    }

    /**
     * Funkcia {@code readTextFromConsole()} načíta text z konzole.
     * @return  vráti načítaný text (typu String)
     */
    public String readTextFromConsole(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * Funkcia {@code setDifficulty()} vypíše možnosti a nastaví náročnosť hry.
     *
     * <p>Možnosti náročnosti:</p>
     * <ul>
     *     <li>ZAČIATOČNÍK</li>
     *     <li>POKROČILÝ</li>
     * </ul>
     */
    public void setDifficulty(){
        System.out.println("-----------ZVOĽTE NÁROČNOST HRY!-----------");
        System.out.println("     (A)-ZAČIATOČNÍK   (B)-POKROČILÝ");
        System.out.println("-------------------------------------------");

        String inputText = readTextFromConsole();
        if(inputText.equals("A")){
            System.out.println("Bola zvolená náročnosť \"ZAČIATOČNÍK\"");
            System.out.println();
            Main.difficulty = 2;
        } else if (inputText.equals("B")) {
            System.out.println("Bola zvolená náročnosť \"POKROČILÝ\"");
            System.out.println();
            Main.difficulty = 0;
        }
        else{
            System.out.println("Nepodarilo sa zvoliť náročnosť, skúste to znovu! ");
            System.out.println();
            Main.difficulty = 333;
            setDifficulty();
        }

    }

    /**
     * Funkcia {@code createPlayer()} vypýta meno z konzoly a vytvorí hráča ( {@code Player} ). <br>
     * Na začiatku hráč dostane zbraň a brnenie.
     * Podľa zvolenej náročnosti hry sa upravuje sila zbrane.
     * @see items
     * @see characters
     */
    public void createPlayer(){
        System.out.print("Zadajte meno hráča:  ");

        actualPlayer = new Player();
        actualPlayer.setName(readTextFromConsole());

        // AK JE NAROCNOST "ZAČIATOČNÍK" tak sila zbrane + (DIFFICULTY_NUMBER * <5;10>)
        if(Main.difficulty == 2) {
            actualPlayer.weapon.setStrength(
                    actualPlayer.weapon.getStrength() + (DIFFICULTY_NUMBER * Person.random.nextInt(5,11))
            );
        }
        //System.out.println(actualPlayer.weapon.name);
        //System.out.println(actualPlayer.weapon.getItemName());
        //System.out.println(actualPlayer.weapon.getStrength());
        //System.out.println(actualPlayer.name);
    }

    /**
     * Funkcia {@code mainMenu()} vypíše možnosti hry a umožňuje zvolenie možnosti. <br>
     * <ul>
     *     <li>MOJA POSTAVA</li>
     *     <li>SUBOJ</li>
     *     <li>KONIEC</li>
     * </ul>
     * <p>Voľba <b>MOJA POSTAVA</b> vypíše informácie o hráčovi ( {@code Player} )</p>
     * <p>Voľba <b>SUBOJ</b> vytvorí protivníka ( {@code Enemy} ), jeho život bude v náhodnom okolí hráča ( {@code Player} ).
     * <br>
     * Následne zavolá funkciu {@code fight()}.</p>
     * <p>Voľba <b>KONIEC</b> ukončí hru a vypíše informácie o hre.</p>
     */
    public void mainMenu(){
        System.out.println();
        System.out.println("------------------ MENU -------------------");
        System.out.println(" (A)-MOJA POSTAVA   (B)-SUBOJ   (X)-KONIEC");
        System.out.println("-------------------------------------------");

        String inputText = readTextFromConsole();

        switch (inputText) {
            case "A" -> {
                // MOJA POSTAVA
                if(actualPlayer instanceof Player){
                    ((Player)actualPlayer).playerInfo();
                }
                mainMenu();
            }
            case "B" -> {
                // SUBOJ
                Enemy fighter = null;
                if( ((Player)actualPlayer).numberOfWins == 0 ){
                    fighter = new Enemy(50);
                }
                else{
                    fighter = new Enemy( ((Player)actualPlayer) );
                }

                System.out.println("-------------------SUBOJ-------------------");
                System.out.print("NÁROČNOSŤ  ");

                if(Main.difficulty == 2)
                    System.out.print("(ZAČIATOČNÍK)");

                else if(Main.difficulty == 0)
                    System.out.print("(POKROČILÝ)");

                System.out.println();

                System.out.println(actualPlayer.getName()+"  VS  "+fighter.getName());
                Person.introduceYourself(actualPlayer);
                Person.introduceYourself(fighter);
                System.out.println("-------------------------------------------");

                fight( ((Player)actualPlayer) , fighter);

                if(actualPlayer.getHealth() > 0){   // AK NIEJE PLAYER MRTVY, DOVOL MU HRAŤ ĎALEJ
                    mainMenu();
                }
                else{   // AK PLAYER UZ ZOMREL, VYHRAL FIGHTER
                    System.out.println("----------------GAME - INFO----------------");
                    fighter.winning();
                    //actualPlayer.playerInfo();
                    System.out.println("Meno hráča: "+actualPlayer.getName());
                    System.out.println("Bojoval si proti "+Enemy.numberOfEnemy+" protivníkom");
                    programDatabase.doPrint();  // VYPIS ZO SINGLETONU
                    //System.out.println("Počet vyhratých bitiek: "+actualPlayer.numberOfWins);
                    System.out.println("Počet vyhratých bitiek: "+((Player)actualPlayer).numberOfWins);
                    System.out.println("-------------------------------------------");
                }

            }

            case "X" -> {
                System.out.println("HRA SKONČILA !");
                System.out.println("----------------GAME - INFO----------------");
                System.out.println("Meno hráča: "+actualPlayer.getName());
                System.out.println("Bojoval si proti "+Enemy.numberOfEnemy+" protivníkom");
                programDatabase.doPrint();  // VYPIS ZO SINGLETONU
                //System.out.println("Počet vyhratých bitiek: "+actualPlayer.numberOfWins);
                System.out.println("Počet vyhratých bitiek: "+((Player)actualPlayer).numberOfWins);
                System.out.println("-------------------------------------------");
            }

            default -> mainMenu();
        }
    }

    /**
     * Funkcia {@code fight()} nasimuluje súboj medzi hráčom ( {@code Player} ) a protivníkom ( {@code Enemy} ). <br>
     * Súboj končí pokiaľ jeden z bojovníkov neminie svoj život.
     * @param player hráč ktorý bojuje
     * @param enemy  protivník, ktorý sa bráni a následne útočí hráča
     * @see characters.Person
     */
    private void fight(Player player, Enemy enemy){

        int tmpHit=0;
        while(player.getHealth()>0 && enemy.getHealth()>0){
            System.out.println("-----------------------------------");
            tmpHit = player.attack();
            System.out.println(player.getName()+" útočí !  SILA ZÁSAHU:("+tmpHit+")");

            enemy.defense(player, tmpHit);

            if(enemy.getHealth() > 0){
                System.out.println("-----------------------------------");
                tmpHit = enemy.attack();
                System.out.println(enemy.getName()+" útočí !  SILA ZÁSAHU:("+tmpHit+")");
                player.defense(enemy, tmpHit);
                System.out.println("-----------------------------------");
            }

        }

        if(player.getHealth() > enemy.getHealth()){     // AK VYHRAL HRAC
            player.updateStats(enemy);

            programDatabase.addEnemy(enemy);            // PRIDANIE MENA PORAZENÉHO ENEMY DO ZOZNAMU
            //databaseInsideFight.doPrint();
        }
    }

}
