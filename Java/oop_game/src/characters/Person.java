package characters;

import items.*;
import java.util.Random;

/**
 * <p>Abstraktná trieda {@code Person}, z ktorej sa nedá vytvoriť inštancia, dá sa z nej iba dediť.</p>
 */
public abstract class Person {

    protected String name;  // prístupny v balíku characters, aj v triedach ktoré dedia z Person
    protected int health;
    public Weapon weapon = new Weapon();
    public Armor armor = new Armor();
    public static final Random random = new Random();  // static - patrí celej PERSON; final-neupravuje sa ďalej

    /**
     * Funkcia {@code getName()} vypíše možnosti hry a umožňuje zvolenie možnosti. <br>
     * @return Meno ( {@code String} )
     */
    public String getName() {
        return name;
    }

    /**
     * Funkcia {@code setName(String name)} nastaví meno. <br>
     * @param name meno osoby ( {@code Person} )
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Funkcia {@code getHealth()} vráti aktualné zdravie. <br>
     * @return health ( {@code int} )
     */
    public int getHealth() {
        return health;
    }

    /**
     * Funkcia {@code setHealth(int health)} nastaví zdravie na hodnotu health. <br>
     * @param health veľkosť zdravia ( {@code int} )
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Abstraktná funkcia {@code winning()}. <br>
     * Každý kto dedí z triedy {@code Person} musí mať napísanú implementáciu funkcie {@code winning()}.
     */
    public abstract void winning(); // KAZDY kto dedí z PERSON musi mat implementáciu winning()

    public void predstavSa(){
        System.out.println("Ahoj");
    }

    /**
     * Statická funkcia {@code introduceYourself(Person person)} vypíše informácie o hráčovi {@code Player} a protivníkovy hráča {@code Enemy}. <br>
     * @param person aktuálny hráč
     */
    public static void introduceYourself(Person person){
        if(person instanceof Player){
            System.out.print("#HRÁČ   ");
        }
        else if(person instanceof Enemy){
            System.out.print("#PROTIVNÍK   ");
        }
        System.out.println(person.name+" HP("+person.health+")  ZBRAŇ: \""+person.weapon.getItemName()+ "\"("+person.weapon.getStrength()+")  BRNENIE: \""+person.armor.getItemName()+ "\"("+person.armor.getArmor()+")");
    }

    /**
     * Funkcia {@code attack()} vykoná útok. <br>
     * @return sila útoku ( {@code int} )
     */
    public int attack(){
        return weapon.getStrength();
    }

    /**
     * Funkcia {@code defense(Person attacker,int damage)} vykoná obranu. <br>
     * @param attacker kto útočí ( {@code Person} )
     * @param damage sila poškodenia ( {@code int} )
     *
     * <p>Náhodne sa vyberá z možností:</p>
     * <ul>
     *      <li>vyhnutie sa zásahu (0)</li>
     *      <li>ubratie z panciera (1)</li>
     *      <li>ubratie zo života (2)</li>
     * </ul>
     */
    public void defense(Person attacker,int damage){
        int randomNumber = Person.random.nextInt(3);
        //System.out.println("OBRANA: NAHODNE CISLO: "+randomNumber);

        if(randomNumber == 0){
            System.out.println(name + " sa uhol.");

            System.out.print(name);
            System.out.print(" (HP: "+health+")     ");
            System.out.print(" (BRNENIE: "+armor.getArmor()+")     ");
            System.out.println();

            System.out.print(attacker.name);
            System.out.print(" (HP: "+attacker.health+")     ");
            System.out.print(" (BRNENIE: "+attacker.armor.getArmor()+")     ");
            System.out.println();
        }
        else if (randomNumber == 1) {
            System.out.println("PANCIER ZASIAHNUTÝ.");

            if(armor.getArmor() > 0){                   // AK EŠTE MÁŠ BRNENIE TAK UBERAJ Z NEHO
                if( damage > armor.getArmor()){         // AK JE POSKODENIE VACSIE AKO ZNESIE PANCIER
                                                        //    UBERIEM CELY PANCIER, ZVYŠOK UBERIEM ZO ŽIVOTA
                    damage = damage - armor.getArmor();

                    System.out.print(name);
                    System.out.print(" (HP: "+health+"-"+damage+")     ");
                    System.out.print(" (BRNENIE: "+armor.getArmor()+"-"+armor.getArmor()+")     ");
                    System.out.println();

                    armor.setArmor(0);
                    health = health - damage;

                    System.out.print(attacker.name);
                    System.out.print(" (HP: "+attacker.health+")     ");
                    System.out.print(" (BRNENIE: "+attacker.armor.getArmor()+")     ");
                    System.out.println();
                }
                else if(damage == armor.getArmor()){    // AK JE POŠKODENIE ROVNAKÉ AKO BRNENIE
                    System.out.print(name);
                    System.out.print(" (HP: "+health+")     ");
                    System.out.print(" (BRNENIE: "+armor.getArmor()+"-"+armor.getArmor()+")     ");
                    System.out.println();

                    armor.setArmor(0);

                    System.out.print(attacker.name);
                    System.out.print(" (HP: "+attacker.health+")     ");
                    System.out.print(" (BRNENIE: "+attacker.armor.getArmor()+")     ");
                    System.out.println();
                }
                else{               // AK JE POSKODENIE MENSIE AKO PANCIER, IBA ODRÁTA Z PANCIERA
                    System.out.print(name);
                    System.out.print(" (HP: "+health+")     ");
                    System.out.print(" (BRNENIE: "+armor.getArmor()+"-"+damage+")     ");
                    System.out.println();

                    System.out.print(attacker.name);
                    System.out.print(" (HP: "+attacker.health+")     ");
                    System.out.print(" (BRNENIE: "+attacker.armor.getArmor()+")     ");
                    System.out.println();

                    armor.setArmor(armor.getArmor() - damage);
                }
                //this.armor.setBrnenie( this.armor.getArmor() - damage);
            }
            else{   // AK NEMAM BRNENIE TAK UBERAJ PRIAMO ZIVOT
                System.out.println("ZÁSAH ! brnenie más rozbité, uberám zo života.");
                System.out.print(name);
                System.out.print(" (HP: "+health+"-"+damage+")     ");
                System.out.print(" (BRNENIE: "+armor.getArmor()+")     ");
                System.out.println();

                System.out.print(attacker.name);
                System.out.print(" (HP: "+attacker.health+")     ");
                System.out.print(" (BRNENIE: "+attacker.armor.getArmor()+")     ");
                System.out.println();

                health = health - damage;
            }
        }
        else {      // UBRATIE ZO ŽIVOTA
            System.out.println("ZÁSAH ! uberám zo života.");
            System.out.print(name);
            System.out.print(" (HP: "+health+"-"+damage+")     ");
            System.out.print(" (BRNENIE: "+armor.getArmor()+")     ");
            System.out.println();

            System.out.print(attacker.name);
            System.out.print(" (HP: "+attacker.health+")     ");
            System.out.print(" (BRNENIE: "+attacker.armor.getArmor()+")     ");
            System.out.println();

            health = health - damage;
        }
    }

}
