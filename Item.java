/*
 * This class contains the item information and anything relevant to the item.
 * This is where all the information of each item is stored.
 */

/**
 *
 * @author OleMartin
 */
public class Item {

    private String name;
    private String description;
    private double weight;
    private boolean edible;
    private String eatEffect;

    public Item(String name, String description, double weight, boolean edible,
            String eatEffect) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.edible = edible;
        this.eatEffect = eatEffect;

    }

    public String getEatEffect() {
        return this.eatEffect;
    }

    public boolean checkEdible() {
        return this.edible;
    }

    /**
     * Returns the name of the item.
     *
     * @return Returns the name of the item.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the description of the item.
     *
     * @return Returns the description of the item.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the weight of the item in kg.
     *
     * @return Returns the weight of the item in kg.
     */
    public double getWeight() {
        return this.weight;
    }

    /**
     * Returns the full description of the item.
     *
     * @return Returns the full description of the item.
     */
    public String getLongDescription() {
        return "Item name: " + this.name + ", description: "
                + this.description + ", weight: " + this.weight + "kg.";
    }

}
