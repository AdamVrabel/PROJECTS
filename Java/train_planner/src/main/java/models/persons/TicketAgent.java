package models.persons;

public class TicketAgent extends Employee {

    public TicketAgent(String name, String surname, String qualifiedFor){
        this.setName(name);
        this.setSurname(surname);
        this.setQualifiedFor(qualifiedFor);
    }


    @Override
    public void startRoute() { //polimorfizm will be also here
        // CONTROL TICKETS OF ALL PASSENGERS
        System.out.println("startRoute from TicketAgent");
    }
}
