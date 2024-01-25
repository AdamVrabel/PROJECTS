package models.train;

import models.persons.Passenger;
import java.util.ArrayList;
import java.util.Random;



/**
 * <p>Wagon in TrainRoute</p>
 * <p>has numberOfPlaces, seatPrice</p>
 */

// PARENT CLASS IN POLYMORFIZM
public class Wagon {
    Random rand = new Random();
    //int numberOfWagon;
    public int numberOfPlaces;
    public int seatPrice;
    ArrayList<Passenger> passengers;


    public Wagon(){
        System.out.println("CONSTRUCTOR of WAGON");
    }

    public void setUpWagon(){
        System.out.println("POLYMORFIZMUS:  from Wagon");
    }
    public void methodFromWagon(){
        System.out.println("som metoda z wagonu");
    }
}
