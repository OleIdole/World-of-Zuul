/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }
    
    /**
     * Main method that initializes the game by creating a new game,
     * then running the play command.
     * 
     * @param args 
     */
    public static void main(String[] args)
    {
        Game game = new Game();
        game.play();

    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room mainSewer, eastSewer, southSewer, westSewer, northSewer,
                        massiveHole, bottom, tunnel, cave, trollCamp;
      
        // create the rooms
        mainSewer = new Room("directly under the center of the city.");
        eastSewer = new Room("heading east through the main system.");
        southSewer = new Room("heading south through the main system.");
        westSewer = new Room("heading west through the main system.");
        northSewer = new Room("heading north through the main system.");
        massiveHole = new Room("standing next to a massive hole, there "
                + "is a ladder.");
        bottom = new Room("standing at the bottom of a massive hole, "
                + "there are tons of spiders there! Get out!");
        tunnel = new Room(" barely alive! The ladder collapsed, you managed "
                + "to climb into a strange tunnel.");
        cave = new Room("in a giant cave, there are torches on the walls. "
                + "You hear strange voices echoing and it is too dark to "
                + "go back.");
        trollCamp = new Room("in a camp full of trolls, they spot you. "
                + "One troll knocks you out, you are trapped.");
        
        // initialise room exits
        
        // mainSewer exits
        mainSewer.setExits("north", northSewer);
        mainSewer.setExits("east", eastSewer);
        mainSewer.setExits("west", westSewer);
        mainSewer.setExits("south", southSewer);
        
        //eastSewer exits
        eastSewer.setExits("west", mainSewer);
        
        //westSewer exits
        westSewer.setExits("east", mainSewer);
        
        //southSewer exits
        southSewer.setExits("north", mainSewer);
        
        //northSewer exits
        northSewer.setExits("south", mainSewer);
        northSewer.setExits("north", massiveHole);
        
        //massiveHole exits
        massiveHole.setExits("south", northSewer);
        massiveHole.setExits("down", bottom);
        
        //bottom exits
        bottom.setExits("up", tunnel);
        
        //tunnel exits
        tunnel.setExits("deeper", cave);
        
        //cave exits
        cave.setExits("further", trollCamp);

        currentRoom = mainSewer;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("You are Zuul, the mighty rat of the sewers!");
        System.out.println("Your mission is to gain full dominance of the sewers.");
        System.out.print("To do this, you must explore every area and");
        System.out.println(" find every item, as they contain great power!");
        System.out.println();
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("That doesn't make sense, do something else!");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println();
        System.out.println("You are lost, but must keep going.");
        System.out.print("There are many dangerous areas, proceed");
        System.out.println(" with caution!");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    private void printLocationInfo() {
        System.out.println("You are " + currentRoom.getDescription());

        System.out.print(currentRoom.getExitString());
        
        System.out.println();
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
