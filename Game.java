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
        // mainSewer items
        mainSewer.putItem(new Item("pistol", "This is a big pistol", 5));
        mainSewer.putItem(new Item("bread", "This bread has seen better days", 0.3));
        
        //eastSewer exits
        eastSewer.setExits("west", mainSewer);
        //eastSewer items
        eastSewer.putItem(new Item("garbage", "A sack full of garbage", 10));
        
        //westSewer exits
        westSewer.setExits("east", mainSewer);
        //westSewer items
        westSewer.putItem(new Item("hamburger", "A nasty looking hamburger", 0.4));
        
        //southSewer exits
        southSewer.setExits("north", mainSewer);
        //southSewer items
        southSewer.putItem(new Item("pancakes", "Some rotten pancakes", 0.3));
        
        //northSewer exits
        northSewer.setExits("south", mainSewer);
        northSewer.setExits("north", massiveHole);
        //northSewer items
        northSewer.putItem(new Item("matches", "A pair of matches", 0.1));
        
        //massiveHole exits
        massiveHole.setExits("south", northSewer);
        massiveHole.setExits("down", bottom);
        // massiveHole items
        massiveHole.putItem(new Item("rock", "A large rock", 20));
        
        //bottom exits
        bottom.setExits("up", tunnel);
        //bottom items
        bottom.putItem(new Item("rope", "A long rope", 5));
        
        //tunnel exits
        tunnel.setExits("deeper", cave);
        //tunnel items
        tunnel.putItem(new Item("spade", "A metal spade", 4));
        
        //cave exits
        cave.setExits("further", trollCamp);
        //cave items
        cave.putItem(new Item("skeletons", "A pile of skeletons", 15));

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
            if(processCommand(command))
            {
            finished = true;
            }
            if(currentRoom.checkIfNoExits())
            {
                System.out.println("GAME OVER!");
                finished = true;
            }
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
        else if (commandWord.equals("look"))
        {
            look();
        }
        else if (commandWord.equals("fart"))
        {
            fart();
        }
        else if (commandWord.equals("wave"))
        {
            wave();
        }
        else if (commandWord.equals("inspect"))
        {
            inspect(command);
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
        System.out.println(parser.showCommands());
    }
    
    /**
     * Here we look around in the room, retrieve full information about
     * the room.
     */
    private void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }
    
    /**
     * Here we release some gass by farting.
     */
    private void fart()
    {
        System.out.println("You take a look around you, then fart loudly!");
        System.out.println("Something stinks!");
    }
    
    /**
     * Here we perform a wave emote.
     */
    private void wave()
    {
        System.out.println("You wave, there is no other person around.");
        System.out.println("You stop waving because it looks stupid.");
    }
    
    /**
     * Here we inspect an item in order to get full details of it.
     * This command consists of two words, first inspect, followed by
     * the item you are going to inspect.
     * This method checks if the commands are valid and uses the item name
     * to retrieve full information about said item.
     * 
     * @param command Checks the users inputs so we can check if they type
     * a valid command.
     */
    private void inspect(Command command)
    {   
        String secondWord = command.getSecondWord();
        if(!command.hasSecondWord()) {
        // if there is no second word, we don't know what to inspect...
        System.out.println("Inspect what?");
        }
    
        if(command.hasSecondWord())
        {
        if(secondWord.equals("pistol"))
        {
            System.out.println(currentRoom.getItemDetails(currentRoom.getItem("pistol")));
        }
        
        else
        {
            System.out.println("Cant find an item called " + secondWord);
        }
        }
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
            System.out.println("You can't go there!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    private void printLocationInfo() {
        System.out.println(currentRoom.getLongDescription());
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
