package models.persons;

public abstract class Employee extends Person{
    // FROM PERSON = name, surname
    //             = startRoute()
    private String jobPosition;    // či je Steward, TicketAgent, TrainConductor

    public String getQualifiedFor() {return jobPosition;}

    public void setQualifiedFor(String jobPosition) {this.jobPosition = jobPosition;}

    //int password;


}
