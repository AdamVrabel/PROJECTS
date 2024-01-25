package models.train;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import models.persons.*;


/**
 * <b>TrainRoute</b>
 * <p>data loaded from DB</p>
 * <li>has id, locomotive, setOfWagons, details about route, trainCrew ...</li>
 */

// CLASSa pre VLAKOVÚ CESTU
public class TrainRoute {
    private int id;
    private Locomotive locomotive;
    public ArrayList<Wagon> setOfWagons = new ArrayList<>();    // COMPOSITION, TrainRoute cannot exist without Wagon

    // PRIDÁ VAGÓN potom treba zavolať setupWagons()
    public void addWagon(Wagon wagonToAdd){
        setOfWagons.add(wagonToAdd);
        //Wagon firstClass = new FirstClassWagon();
        //Wagon economyClass = new EconomyClassWagon();

        //setOfWagons.add(firstClass);
        //setOfWagons.add(economyClass);

    }

    public void setUpWagons(){  // POLYMORFIZM
        // arrays of firstClass/ economyClass wagons...

        //firstClass.setUpWagon();
        //economyClass.setUpWagon();
        // WITH POLYMORFIZM  SET PRICE, NUMBER OF SEATS FOR FIRST_CLASS/ ECONOMY WAGON
        for (Wagon actualWagon : setOfWagons) {
            actualWagon.setUpWagon();
        }
    }
    // PREJDE ARRAYLIST setOfWagons A VRÁTI TEN S NAJNIŽŠOU CENOU
    // AK KLIKNEM NA ECONOMY/ FIRST CLASS BTN TAK MI VEZME NAJLACNEJSI A VRATI CELY VAGON S NAJMENŠOU CENOU
    public Wagon najlacnejsiWagon(String type){
        int tmpLowerPrice = -7;
        Wagon lowerPriceWagon = null;
        if(type.equals("ECONOMY")){
            // POSLANY BOL ECONOMY
            for(Wagon wagon : setOfWagons){ // PRECHADZAM VSETKYMY VAGONMI
                if(wagon instanceof EconomyClassWagon){
                    if(tmpLowerPrice == -7){// PRE PRVY PRIPAD WAGONA
                        tmpLowerPrice = wagon.seatPrice;
                        lowerPriceWagon = wagon;
                    }
                    if(wagon.seatPrice < tmpLowerPrice){
                        tmpLowerPrice = wagon.seatPrice;
                        lowerPriceWagon = wagon;
                    }
                }
            }
            
        }
        else if (type.equals("FIRSTCLASS")) {
            // POSLANY BOL FIRST CLASS
            for(Wagon wagon : setOfWagons){ // PRECHADZAM VSETKYMY VAGONMI
                if(wagon instanceof FirstClassWagon){
                    if(tmpLowerPrice == -7){// PRE PRVY PRIPAD WAGONA
                        tmpLowerPrice = wagon.seatPrice;
                        lowerPriceWagon = wagon;
                    }
                    if(wagon.seatPrice < tmpLowerPrice){
                        tmpLowerPrice = wagon.seatPrice;
                        lowerPriceWagon = wagon;
                    }
                }
            }
            
        }
        return lowerPriceWagon; // VRÁTIM VAGÓN S NAJMENŠOU CENOU (podľa vstupného typu)
    }

    private Date routeDate;         // DÁTUM CESTY VLAKU
    private Time departureTime;     // ČAS ODCHODU VLAKU
    private Time arrivalTime;       // ČAS PRÍCHODU VLAKU DO CIELOVEJ STANICE
    private String from;            // MESTO ZAČIATKU CESTY
    private String to;              // MESTO KONCA CESTY

//    ArrayList<Passenger> allPassengers;

    public TrainRoute(int id, String from, String to, Time departureTime, Time arrivalTime, Date routeDate) {
        this.id = id;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.from = from;
        this.to = to;
        this.routeDate = routeDate;

        locomotive = new Locomotive();
    }

    // GETTERs & SETTERs
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public Time getDepartureTime() {return departureTime;}
    public String getDepartureTimeString(){
        // DEFINOVANIE FORMATU PRE CAS
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(departureTime);
    }
    public void setDepartureTime(Time departureTime) {this.departureTime = departureTime;}
    public Time getArrivalTime() {return arrivalTime;}
    public String getArrivalTimeString(){
        // DEFINOVANIE FORMATU PRE CAS
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(arrivalTime);
    }
    public void setArrivalTime(Time arrivalTime) {this.arrivalTime = arrivalTime;}
    public String getFrom() {return from;}
    public void setFrom(String from) {this.from = from;}
    public String getTo() {return to;}
    public void setTo(String to) {this.to = to;}
    public Date getRouteDate() {return routeDate;}
    public void setRouteDate(Date routeDate) {this.routeDate = routeDate;}



    ArrayList<Employee> trainCrew = new ArrayList<Employee>();
    public void setUpTrainCrew(){
        // CHOOSE AVAILABLE EMPLOYEE
        Employee steward = new Steward("Stewe", "Stevy", "Master Steward");
        Employee ticketAgent = new TicketAgent("Adel", "Ticker", "Boss TicketAgent");
        Employee trainConductor = new TrainConductor("Crazy", "Driver", "Speed Train Driver");

        trainCrew.add(steward);
        trainCrew.add(ticketAgent);
        trainCrew.add(trainConductor);

        locomotive.setTrainDriver((TrainConductor)trainConductor);
    }


    // WHEN ROUTE STARTS, run startRoute()
    // make .startRoute() over Employee in trainCrew, java thanks to polymofizm make different job on each employee
    public void startRoute(){
        for (Employee actualEmployee : trainCrew) {
            actualEmployee.startRoute();
        }
    }
}
