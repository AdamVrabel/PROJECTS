package models.items;

public class BottleOfWater extends Item{

    // MÁ name, getItem
    String description;
    int mililiters;

    public BottleOfWater(String name, String description, int mililiters) {
        this.description = description;
        this.mililiters = mililiters;
    }

    @Override
    public Item getItem() {
        System.out.println("Vraciam BottleOfWater ako Item");
        return this;
        //return null;
    }
}
