
import java.util.Stack;
import java.util.ArrayList;

/**
 * This class is specifically for the player of the game. The player is the one
 * who keeps control of where he is, what items he has, how much he can carry,
 * and what his name is. The game is the one who tells the player where to go,
 * this is not something the player does. The player also remembers where he has
 * been, which enables him to go back if he wishes.
 */
/**
 *
 * @author OleMartin
 */
public class Player {

    private String name;
    private Room currentRoom;
    private double maxCarryWeight;
    private Stack<Room> prevRooms;
    private ArrayList<Item> items;

    public Player(String name, int maxCarryWeight) {
        this.name = name;
        this.maxCarryWeight = maxCarryWeight;
        prevRooms = new Stack<>();
        items = new ArrayList<>();
    }

    public void increaseMaxCarryWeight(double increase)
    {
        this.maxCarryWeight += increase;
    }
    
    /**
     * Takes the requested item by adding it from the items array.
     *
     * @param item The item you want to take.
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Drops the requested item by removing it from the items array.
     *
     * @param item The item you want to remove.
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * Here we check the items array for an item, to see if the player has that
     * item. Returns true if the player has the item, false if not.
     *
     * @param item The item that you want to check for.
     * @return Returns true if the player has the item, false if not.
     */
    public boolean checkForItem(Item item) {
        boolean found = false;
        if (items.contains(item)) {
            found = true;
        }
        return found;
    }

    /**
     * Returns an item by requesting it with its itemName. Goes through the
     * array of items and if its found, it will get the item that is connected
     * to the name.
     *
     * @param itemName The name of the item you want to get.
     * @return Returns an item by requesting it with its itemName.
     */
    public Item getItem(String itemName) {
        Item searchedItem = null;
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                searchedItem = item;
            }
        }
        return searchedItem;
    }

    /**
     * If there is somewhere to go back to, meaning that there is a value in the
     * prevRooms stack, then it returns true. If player can go back, then
     * currentRoom will change to the Room on top of the prevRooms stack. If
     * there is nowhere to go back, returns false.
     *
     * @return Returns true/false depending if u can go back.
     */
    public boolean goBack() {
        boolean goBack = false;
        if (!prevRooms.isEmpty()) {
            currentRoom = prevRooms.pop();
            goBack = true;
        } else {
            goBack = false;
        }
        return goBack;
    }

    /**
     * pushes currentRoom into prevRooms stack, then changes currentRoom to
     * nextRoom.
     *
     * @param nextRoom The next room to move the player to.
     */
    public void goRoom(Room nextRoom) {
        prevRooms.push(currentRoom);
        currentRoom = nextRoom;
    }

    /**
     * Sets the current room and nothing else.
     *
     * @param nextRoom Sets the current room and nothing else.
     */
    public void setCurrentRoom(Room nextRoom) {
        currentRoom = nextRoom;
    }

    /**
     * Returns the remaining weight that the player can carry. Does this by
     * finding the total weight of items carried, and substract this from the
     * maxCarryWeight. Example: if your maxCarryWeight is 90kg and total weight
     * of items in inventory is 50kg. WeightLimit is 90kg-50kg = 40kg.
     *
     * @return Returns the remaining weight that the player can carry.
     */
    public double getWeightLimit() {
        double weightLimit = maxCarryWeight;
        weightLimit -= getCurrentWeight();
        return weightLimit;
    }

    public String getItemsCarriedAndWeight() {
        String itemsAndWeight = "";
        if (!items.isEmpty()) {
            itemsAndWeight = "The items carried are: "
                    + getCurrentItems() + "and the total weight carried is "
                    + getCurrentWeight() + "kg.";
        } else {
            itemsAndWeight = "You dont carry any items!";
        }
        return itemsAndWeight;
    }

    /**
     * Goes through the items carried and adds the weight together. Returns the
     * total weight of the items carried.
     *
     * @return Returns the total weight of the items carried.
     */
    public double getCurrentWeight() {
        double currentWeight = 0;
        for (Item item : items) {
            currentWeight += item.getWeight();
        }
        return currentWeight;
    }

    /**
     * Goes through the items carried and builds a string with all their names.
     * Returns a string with all the items carried.
     *
     * @return Returns a string with all the items carried.
     */
    public String getCurrentItems() {
        String itemsCarried = "";
        for (Item item : items) {
            itemsCarried += (item.getName() + ", ");
        }
        return itemsCarried;
    }

    /**
     * Returns the name of the player.
     *
     * @return Returns the name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the current room of the player.
     *
     * @return Returns the current room of the player.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Returns the max carryweight of the player.
     *
     * @return Returns the max carry weight of the player.
     */
    public double getMaxCarryWeight() {
        return maxCarryWeight;
    }

    /**
     * Returns a full description of the player.
     *
     * @return Returns a full description of the player.
     */
    public String getDescription() {
        String details = "Name: ";
        details += name + ", current room: ";
        details += currentRoom + ", max carry weight: ";
        details += maxCarryWeight + "kg.";
        return details;
    }

}
