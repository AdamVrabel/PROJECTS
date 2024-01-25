package models.train;

/**
 * <b>FIRST CLASS WAGON</b>
 * <p>extends from Wagon, has own settings</p>
 */
public class FirstClassWagon extends Wagon{

    @Override
    public void setUpWagon() {
        //super.setUpWagon();

        System.out.println("POLYMORFIZMUS:  from First Class Wagon");
        numberOfPlaces = 10; // places

        seatPrice = rand.nextInt(70,101);    // RANDOM PRICE 70-100€
    }

    public void methodFromFirstClassWagon(){
        System.out.println("som metoda z FIRST CLASS wagonu");
    }
}
