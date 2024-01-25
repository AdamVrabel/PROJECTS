package models.items;

public class Coffe extends Item{

    // MÁ name, getItem
    String description;

    public Coffe (String name, String description) {
        this.description = description;
    }

    @Override
    public Item getItem() {
        System.out.println("Vraciam BottleOfWater ako Item");
        return this;
        //return null;
    }
}
