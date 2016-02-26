
import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }
    
    /**
     * Returns the next room in the direction you request.
     * 
     * @param direction
     * @return 
     */
    public Room getExit(String direction)
    {
        Room nextRoom = exits.get(direction);
        return nextRoom;
    }
    
    /**
     * Return a description of the room's exits,
     * for example, "Exits: north west".
     * 
     * @return A description of the available exits.
     */
    public String getExitString()
    {
        String returnString = "Exits:";
        for(String exit : exits.keySet())
        {
            returnString += " " + exit;
        }
        return returnString;
    }
    
    /**
     * Sets exit direction from the room, and where
     * you end up by going that direction.
     * 
     * @param direction Direction of the exit.
     * @param neighbour The room in that direction.
     */
    public void setExits(String direction, Room neighbour)
    {
        exits.put(direction, neighbour);
    }
    
    /**
     * Returns a description of the room.
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

}
