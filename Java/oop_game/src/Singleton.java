import java.util.LinkedList;
import characters.*;

/**
 * Návrhový vzor <b>Singleton</b> zabezpečí že v programe bude vždy iba jedna inštancia. <br>
 * Ak ešte inštancia nebola vytvorená tak ju vytvorí.<br>
 * Ak už inštancia bola vytvorená tak ju iba vráti.
 */
public class Singleton {
    private static Singleton instance = null;
    private Singleton(){}
    public static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }
    private LinkedList<String> namesOfEnemy = new LinkedList<>();

    /**
     * Funkcia {@code addEnemy(Object thing)} skontroluje či je parameter {@code Enemy} alebo {@code String} a priradí do databázy <b>meno</b> bojovníka.
     * @param thing typu {@code Object}
     */
    public void addEnemy(Object thing){
        if(thing instanceof Enemy){
            namesOfEnemy.push(((Enemy)thing).getName()); // PUSH NAME OF ENEMY TO DATABASE
        }
        else if (thing instanceof String) {
            namesOfEnemy.push((String)thing);   // PUSH STRING TO DATABASE
        }
    }

    /**
     * Funkcia {@code doPrint()} vykoná výpis z databázy.
     */
    public void doPrint(){
        if(namesOfEnemy.isEmpty()){
            System.out.println("DATABÁZA JE PRÁZDNA");
        }
        else{
            System.out.print("PORAZIL SI BOJOVNÍKOV: ");
            System.out.println(namesOfEnemy);
        }
    }
    public void info(){
        System.out.println("Vypis z vnutra singletonu.");
    }


    @Override
    public int hashCode() {
        return ("FIIT STU").hashCode() + super.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Singleton){
            if(obj.hashCode() == this.hashCode()){
                return true;
            }
            else {
                return false;
            }
        }
        else{
            return false;
        }
    }
    @Override
    public String toString() {
        return "SINGLETON_"+ super.toString();
    }
}
