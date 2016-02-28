
import java.util.HashMap;
import java.util.Iterator;

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
    private HashMap<String, Item> itemsInRoom;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * 
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        itemsInRoom = new HashMap<String, Item>();
    }
    
    /**
     * Puts an item into the room.
     * 
     * @param item The item that is put into the room.
     */
    public void putItem(Item item)
    {
        this.itemsInRoom.put(item.getName(), item);
    }
    
    /**
     * Returns the item that is requested by its name.
     * 
     * @param name This is the name of the item.
     * @return Returns the item that is requested by its name.
     */
    public Item getItem(String name)
    {
        return itemsInRoom.get(name);
    }
    
    /**
     * Checks if the input name is a valid name for an item in the room.
     * If there is an item with that name, returns true, if not, false.
     * 
     * @param name Name of the item you want to check for.
     * @return If there is an item with that name, returns true, if not, false.
     */
    public boolean checkForItem(String name)
    {
        return itemsInRoom.containsKey(name);
    }
    
    /**
     * Creates and returns a string with the list of all items in the room.
     * 
     * @return Returns a string with the list of all items in the room.
     */
    public String getItemList()
    {
        Iterator<String> it = itemsInRoom.keySet().iterator();
        String items = "Items: ";
        
        if(!it.hasNext())
        {
            items += "There are no items here";
        }
        
        while(it.hasNext())
        {
            items += it.next() + " ";
        }
        return items;
    }
    
    /**
     * Returns the full details of the item requested.
     * 
     * @param item The item that you want to get details about.
     * @return Returns the full details of the item requested.
     */
    public String getItemDetails(Item item)
    {
        return item.getLongDescription();
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
     * If there are no exits, returns the description
     * "There are no exits!".
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
        if(checkIfNoExits())
                {
                    returnString = "There are no exits!";
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
     * 
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Returns a full description of the room.
     * 
     * @return The full description of the room.
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + 
                "\n" + getItemList();
    }
    
    /**
     * Returns true if there are exits, false if there are no exits.
     * 
     * @return true if there are exits, false if there are no exits.
     */
    public Boolean checkIfNoExits()
    {
        return exits.isEmpty();
    }

}
