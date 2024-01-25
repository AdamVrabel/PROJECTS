package models.persons;

import models.items.*;
import models.persons.*;

public class Steward extends Employee{
    // HAS MANY ITEMS (BottleOfWate, Coffe ...)
    // CAN GIVE TO Passenger, when he wants it

    public Steward(String name, String surname, String qualifiedFor){
        this.setName(name);
        this.setSurname(surname);
        this.setQualifiedFor(qualifiedFor);
    }

    @Override
    public void startRoute() { //polimorfizm will be also here
        // BUDE DOSTUPNY NA ZAVOLANIE OD Passenger aby si mohli vybrať čo chcú (VODA/ KÁVA...)
        // WILL BE AVAILABLE ON CALL FROM Passenger, Passenger can choose what they want (WATER/ COFFEE...)
        System.out.println("startRoute from Steward");
    }

    public Item getMeWhatIWant(Item itemWhatWant){
        if(itemWhatWant instanceof BottleOfWater){
            System.out.println("WANTs WATER");
        }
        if(itemWhatWant instanceof Coffe){
            System.out.println("WANTs COFFE");
        }

        return null;
    }
}
