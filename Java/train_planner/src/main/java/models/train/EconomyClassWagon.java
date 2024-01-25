package models.train;

import java.util.Random;

/**
 * <b>ECONOMY WAGON</b>
 * <p>extends from Wagon, has own settings</p>
 */
public class EconomyClassWagon extends Wagon{

    @Override
    public void setUpWagon() {
        //super.setUpWagon();

        System.out.println("POLYMORFIZMUS:  from Economy Class Wagon");
        numberOfPlaces = 30; // places

        seatPrice = rand.nextInt(30,51);    // RANDOM PRICE 30-50€
    }

    public void methodFromEconomyClassWagon(){
        System.out.println("som metoda z ECONOMY CLASS wagonu");
    }
}
