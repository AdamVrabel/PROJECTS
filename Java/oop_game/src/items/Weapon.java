package items;
import java.util.Random;

/**
 * <p>Trieda {@code Weapon} dedí od triedy {@code Item}.</p>
 * <p>Pomocou zbrane môže {@code Person} útočiť.</p>
 */
public class Weapon extends Item {

    private int strength;

    /**
     * Funkcia {@code getStrength()} vráti silu zbrane. <br>
     * @return strength - sila zbrane ( {@code int} )
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Funkcia {@code setStrength(int sila)} nastaví silu zbrane. <br>
     */
    public void setStrength(int sila) {
        this.strength = sila;
    }

    /**
     * Funkcia {@code getWeapon()} vyžiada referenciu na zbraň. <br>
     * @return Weapon - referencia na Zbraň {@code Weapon}
     */
    public Weapon getWeapon(){
        return this;
    }

    //KONŠTRUKTOR
    public Weapon(){
        String [] namesOfWeapons ={
                "Analytická zbraň odmocnín",
                "Objektovo orientovaný meč",
                "Kniha čo reže vajcia"
        };
        //Random random = new Random();
        //int randomInteger = random.nextInt(3); // GENERUJE NAHODNE 0, 1, 2

        // VYBERIE NAHODNE NAZOV ZBRANE Z POLA namesOfWeapons
        //name = namesOfWeapons[random.nextInt(3)];
        setItemName(namesOfWeapons[random.nextInt(3)]);

        strength = (random.nextInt(70,91)); //CISLA <70;90>
    }

    @Override
    public int hashCode() {
        return strength * getItemName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Weapon){
            if ( ( ((Weapon)obj).strength == this.strength) && (obj.hashCode() == this.hashCode()) ){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
    @Override
    public String toString() {
        return "WEAPON_"+ super.toString();
    }
}
