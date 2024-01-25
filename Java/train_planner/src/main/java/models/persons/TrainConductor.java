package models.persons;

public class TrainConductor extends Employee{

    // ADD ACTUAL ROUTE, AND ALL DETAILS ABOUT ROUTE

    public TrainConductor(String name, String surname, String qualifiedFor){
        this.setName(name);
        this.setSurname(surname);
        this.setQualifiedFor(qualifiedFor);
    }

    @Override
    public void startRoute() { //polimorfizm will be also here
        // MOVE TRAIN AND CHANGE STATUS OF THE PASSENGERS
        // DISPLAY ALL DETAILS ABOUT ACTUAL ROUTE TO JAVAFX SCREEN later...

        System.out.println("startRoute from TrainConductor");
    }
}
