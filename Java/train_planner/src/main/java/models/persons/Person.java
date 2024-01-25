package models.persons;

public abstract class Person {
    public int id;
    private String name;
    private String surname;

    // GETTERs / SETTERs
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }


    // FOR POLYMORFIZM
    public void startRoute(){   // KAŽDÝ MUSÍ MAŤ IMPLEMENTOVANÉ startRoute()   //polimorfizm will be also here
        System.out.println("startRoute from Person");
    }

}
