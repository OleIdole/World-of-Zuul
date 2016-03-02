
import java.util.Stack;

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
    private int maxCarryWeight;
    private Stack<Room> prevRooms;

    public Player(String name, int maxCarryWeight) {
        this.name = name;
        this.maxCarryWeight = maxCarryWeight;
        prevRooms = new Stack<>();
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
    public int getMaxCarryWeight() {
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
