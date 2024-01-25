package items;
import characters.Enemy;
import characters.Player;
import java.util.Random;

/**
 * <p>Trieda {@code Armor} dedí od triedy {@code Item}.</p>
 * <p>Brnenie "chráni" hráča {@code Player} alebo protivníka {@code Enemy}.</p>
 */
public class Armor extends Item{
    private int armor;

    /**
     * Funkcia {@code getArmor()} vráti silu panciera. <br>
     * @return armor - sila panciera ( {@code int} )
     */
    public int getArmor() {
        return armor;
    }

    /**
     * Funkcia {@code setArmor(int armor)} nastaví silu brnenia. <br>
     */
    public void setArmor(int armor) {
        this.armor = armor;

        if(armor == 0){
            System.out.println("BRNENIE BOLO ZNIČENÉ !");
        }
    }

    /**
     * Funkcia {@code getArmorItem()} vyžiada referenciu na brnenie. <br>
     * @return Armor - referencia na Pancier {@code Armor}
     */
    public Armor getArmorItem(){
        return this;
    }

    //KONŠTRUKTOR
    public Armor(){
        String [] namesOfArmors ={
                "Zázračná prilba proti F(x)",
                "Magické nohavice nekonečných bodov",
                "Nepriestrelná vesta"
        };
        //Random random = new Random();
        //int randomInteger = random.nextInt(3); // GENERUJE NAHODNE 0, 1, 2

        // VYBERIE NAHODNE NAZOV ZBRANE Z POLA namesOfWeapons
        //name = namesOfWeapons[random.nextInt(3)];
        setItemName(namesOfArmors[random.nextInt(3)]);

        armor = (random.nextInt(10,31)); //CISLA <10;30>
    }

    @Override
    public int hashCode() {
        return armor * getItemName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Armor){
            if ( ( ((Armor)obj).armor == this.armor) && (obj.hashCode() == this.hashCode()) ){
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
        return "ARMOR_"+ super.toString();
    }
}
