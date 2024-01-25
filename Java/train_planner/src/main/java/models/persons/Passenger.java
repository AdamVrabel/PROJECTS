package models.persons;

import models.Database;
import models.items.Item;
import models.items.Ticket;
import models.train.TrainRoute;

import java.util.ArrayList;

/**
 * <b>Passenger is user in app</b>
 * <li>can search routes</li>
 * <li>can buy tickets</li>
 * <li>has ticket inventory</li>
 * <li>has money in account</li>
 */
public class Passenger extends Person{

    public static Passenger acutalLoggedUser;

    // HAS name, surname
    private String username;
    private String password;
    private int money;

    public ArrayList<Ticket> ticketInventory = new ArrayList<Ticket>();    // INVENTORY OF USER TICKETS, loaded from DB



    //ArrayList<Item> inventory = new ArrayList<Item>();  // TICKET, WATER, COFFE...

    public void updateTickets(){
        //acutalLoggedUser.id;
        Database database = new Database();
        ticketInventory = database.getTickets(acutalLoggedUser.id);
    }

    @Override
    public void startRoute() { //polimorfizm will be also here
        // PASSENGER CHANGE SCREEN, he can order coffe, water and display more detail informations about actual route
    }

    public Passenger(int id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }
    public int getUserID() {return id;}
    public Passenger(int id, String username, String password, String name, String surname, int money){
        this.id = id;
        this.setName(name);
        this.setSurname(surname);
        this.username = username;
        this.password = password;
        this.money = money;
    }
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {this.password = password;}
    public int getMoney() {return money;}
    public void setMoney(int money) {
        this.money = money;
    }

    /*public void addToInventory(Item itemToAdd){
        inventory.add(itemToAdd);
    }*/
}
