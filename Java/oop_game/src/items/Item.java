package items;

import java.util.Random;

/**
 * <p>Abstraktná trieda {@code Item}, z ktorej sa nedá vytvoriť inštancia, dá sa z nej iba dediť.</p>
 */
public abstract class Item {
    private String name;
    static final Random random = new Random();  // STATIC - PATRÍ CELEJ TRIEDE, nie inštanciám (final, nebudem ju menit, iba používať)

    /**
     * Funkcia {@code getItemName()} vypýta meno Itemu. <br>
     * @return name - názov Itemu ( {@code String} )
     */
    public final String getItemName(){
        return this.name;
    }

    /**
     * Funkcia {@code setItemName(String name)} nastaví názov Itemu. <br>
     * @param name názov Itemu ( {@code String} )
     */
    public final void setItemName(String name){
        this.name = name;
    }

}
