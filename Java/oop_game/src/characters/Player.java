package characters;

/**
 * <p>Trieda {@code Player} dedí od triedy {@code Person}.</p>
 * <p>Je to aktuálny hráč, ktorý hraje hru.</p>
 */
public class Player extends Person {

    public int numberOfWins = 0;

    // KONŠTRUKTOR
    public Player(){
        health = 100;
    }
    // KONŠTRUKTOR
    public Player(String name){
        this.name = name;
        health = 100;
        System.out.println("Hráč "+name+" bol vytvorený!");
    }

    /**
     * Funkcia {@code setName(String name)} nastaví meno a realizuje kontrolný výpis. <br>
     * @param name meno osoby ( {@code Person} )
     */
    @Override
    public void setName(String name) {
        this.name = name;
        System.out.println("Meno "+name+" bolo pridelené hráčovi!");
    }

    @Override
    public void predstavSa() {
        System.out.println("METHOD FROM CHILD, SOM PLAYER, predstavujem sa!");
    }

    /**
     * Funkcia {@code playerInfo()} vypíše informácie o hráčovi ( {@code Player} ). <br>
     */
    public void playerInfo(){
        System.out.println("----------------MOJA POSTAVA----------------");
        System.out.println("MENO: "+ name);
        System.out.println("POČET VÝHIER: "+ numberOfWins);
        System.out.println("MAXIMÁLNY ŽIVOT: "+ health);
        System.out.println("ZBRAŇ: \""+weapon.getItemName()+ "\" ("+weapon.getStrength()+")");
        System.out.println("BRNENIE: \""+armor.getItemName()+ "\" ("+armor.getArmor()+")");
        System.out.println("--------------------------------------------");
    }

    /**
     * Funkcia {@code updateStats(Enemy enemy)} zavolá funkciu {@code winning()} a skontroluje či je zbraň alebo brnenie lepšie, ak áno tak ich priradí hráčovi.<br>
     * @param enemy protivník, ktorý bol porazený v súboji
     */
    public void updateStats(Enemy enemy){
        winning();

        // AK JE ZBRAN PROTIVNIKA LEPSIA, TAK VYMEN
        if( (enemy.weapon.getStrength()) > (this.weapon.getStrength()) ){
            System.out.println("ZBRAN PROTIVNIKA JE LEPSIA, vezmem si ju...");
            this.weapon = enemy.weapon.getWeapon();
        }
        // AK JE BRNENIE PROTIVNIKA LEPSIE, TAK VYMEN
        if( enemy.armor.getArmor() > this.armor.getArmor() ){
            System.out.println("BRNENIE PROTIVNIKA JE LEPSIE, vezmem si ho...");
            this.armor = enemy.armor.getArmorItem();
        }
    }

    /**
     * Funkcia {@code winning()} sa zavolá pri výhre hráča ( {@code Player} ), obnoví hráčovi život, počíta množstvo vyhratých súbojov .<br>
     */
    public void winning(){        // KAZDY PERSON musi mat implementáciu vyhra()
        System.out.println();
        System.out.println("Vyhral si bitku !");
        numberOfWins++;
        health = 100 + (10*numberOfWins);
    }


    @Override
    public int hashCode() {
        return name.hashCode() * weapon.hashCode() * armor.hashCode() + numberOfWins;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Player){
            if ( ( ((Person)obj).name.hashCode() == this.name.hashCode()) && (((Person)obj).weapon.hashCode() == this.weapon.hashCode()) ){
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
        return "PLAYER_"+ super.toString();
    }

}
