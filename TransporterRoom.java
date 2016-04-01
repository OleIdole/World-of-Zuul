
import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author OleMartin
 */
public class TransporterRoom extends Room
{
    private Random randomGenerator;

    public TransporterRoom(String description) {
        super(description);
        randomGenerator = new Random();
    }
    

    /**
     * Return a random room, independent of the direction parameter.
     * @param direction Ignored.
     * @return A random room.
     */
    @Override
    public Room getExit(String direction) {
        return findRandomRoom();
    }
    
    /**
     * Choose a random room
     * @return A random room
     */
    private Room findRandomRoom()
    {
        Game game = new Game();
        ArrayList<Room> allRooms = game.getAllRooms();
        int randomRange = (allRooms.size() - 1);
        int randomNumber = randomGenerator.nextInt(randomRange);
        Room randomRoom = allRooms.get(randomNumber);
        return randomRoom;
    }
    
}
