package models.items;

import models.train.TrainRoute;

import java.sql.Date;
import java.sql.Time;

/**
 * Ticket for train route with all details about route
 */
public class Ticket extends Item{
    public String routeID;
    public String userID;
    public TrainRoute route;

    public String routeDate;
    public String departureTime;
    public String arrivalTime;
    public String from;
    public String to;
    public int price;


    public Ticket(){
        name = "TESTING TICKET";
    }
    public Ticket(TrainRoute route, int userID, int price){
        routeID = Integer.toString(route.getId());
        routeID = Integer.toString(route.getId());
        this.userID = Integer.toString(userID);

        name = "TICKET : ROUTE_ID["+route.getId()+"]";
        this.route = route;

        routeDate = route.getRouteDate().toString();
        departureTime = route.getDepartureTimeString();
        arrivalTime = route.getArrivalTimeString();
        from = route.getFrom();
        to = route.getTo();

        this.price = price;

    }


    @Override
    public Item getItem() {
        return this;
    }
}
