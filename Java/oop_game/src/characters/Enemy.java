package characters;

/**
 * <p>Trieda {@code Enemy} dedí od triedy {@code Person}.</p>
 * <p>Je to protivník, ktorý bude bojovať proti hráčovi {@code Player}.</p>
 */
public class Enemy extends Person {

    public static int numberOfEnemy = 0;    // POČÍTADLO VŠETKÝCH ENEMY

    // KONŠTRUKTOR
    public Enemy(){
        health = 100;
        numberOfEnemy++;
    }
    // KONŠTRUKTOR
    public Enemy(int health){
        name = createEnemyName();
        this.health = health;
        numberOfEnemy++;
    }
    // KONŠTRUKTOR
    public Enemy(Player actualPlayer){
        name = createEnemyName();
        // ZIVOT HRACA + (10 * numberOfWins * <-2;3> )
        this.health =( (actualPlayer.health) + (10 * actualPlayer.numberOfWins * Person.random.nextInt(-2, 3)) ) ;

        System.out.println();
        numberOfEnemy++;
    }


    /**
     * Funkcia {@code createEnemyName()} vyberie meno protivníka. <br>
     * @return meno protivníka ( {@code String} ).
     */
    private String createEnemyName(){
        String [] names ={
                "Šialený matematik",
                "Pozriem a vidím šaľeňak",
                "Prirodzený inteligent"
        };

        return(names[Person.random.nextInt(3)]);
    }

    /**
     * Funkcia {@code winning()} sa zavolá pri výhre enemy ( {@code Enemy} ), vypíše notifikáciu o porazení.<br>
     */
    public void winning(){  // KAZDY PERSON musi mat implementáciu vyhra()
        System.out.println("HRA SA SKONCILA ! (porazil ťa "+name+")");
    }

    @Override
    public int hashCode() {
        return name.hashCode() * weapon.hashCode() * armor.hashCode() + numberOfEnemy;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Enemy){
            if ( ( ((Enemy)obj).name.hashCode() == this.name.hashCode()) && (((Enemy)obj).weapon.hashCode() == this.weapon.hashCode()) ){
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
        return "ENEMY_"+ super.toString();
    }
}
