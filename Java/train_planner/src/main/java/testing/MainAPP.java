package testing;

import models.persons.Passenger;

import java.sql.Date;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Random;

import models.train.*;

public class MainAPP {

    public static void main(String[] args) {

        /*
        String time1 = "15:00";
        String time2 = "16:30";

        LocalTime localTime1 = LocalTime.parse(time1);
        LocalTime localTime2 = LocalTime.parse(time2);

        Duration duration = Duration.between(localTime1, localTime2);
        long minutes = duration.toMinutes();

        String timeDiff = String.format("%02dh %02dmin", minutes / 60, minutes % 60);

        System.out.println("Rozdiel medzi " + time1 + " a " + time2 + " je " + timeDiff + ".");

        System.out.println("Rozdiel medzi " + time1 + " a " + time2 + " je " + duration.toMinutes() + " minút.");
         */


        // JUST TRY POLYMORFIZMUS

        Wagon firstClassWagon = new FirstClassWagon();      // takto mám prístup k metódam ktoré sú v class Wagon, ale vykoná sa z FirstClassWagon
        Wagon economyClassWagon = new EconomyClassWagon();

        firstClassWagon.methodFromWagon();

        ((FirstClassWagon) firstClassWagon).methodFromFirstClassWagon();    // JEDINE DOWNCASTINGOM SA VIEM DOSTAT K METODAM Z POTOMKA



        /*
        Date localDate = new Date(2023,4,9);
        Time timeStart = new Time(20,10,0);
        Time timeEnd = new Time(22,15,0);

        // TEST ALTERNATIVE, CREATE ROUTE, SET UP WAGON, TRAIN CREW AND START ROUTE
        TrainRoute testRoute = new TrainRoute(0,"Humenne", "Kosice", timeStart, timeEnd, localDate);

        testRoute.setUpWagons();    // SET UP WAGONs
        testRoute.setUpTrainCrew(); // SET UP TRAIN CREW
        testRoute.startRoute();

        Passenger passenger = new Passenger(0, "Test", "test");

         */

    }
}
