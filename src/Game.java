/**
 *  This is a very simple, text based adventure game.
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Victor Riveros
 * @version 2014.12.8
 */

public class Game 
{
    private Parser parser;
    //     private Room currentRoom;
    private Room previousRoom;
    private Player player;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        player = new Player();
        createRooms();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room motel, theater, zoo, indian, teepee, art, monument, pool, outside1, outside2, outside3, outside4,
        outside5, outside6, whiteHouseEntrance, whiteHouseHallwayWest, whiteHouseHallwayEast, 
        whiteHouseHallwayMaintenance, maintenance1, maintenance2, control, whiteHouseHallwayOval, oval;

        // create the rooms
        motel = new Room("motel", "in a suspicious looking motel");
        theater = new Room("theater", "in Ford's Theater, site of Abraham Lincoln's assasination");
        zoo = new Room("zoo", "in the National Zoological Park");
        indian = new Room("indian", "in the National Museum of the American Indian");
        art = new Room("art", "in the National Gallery of Art");
        teepee = new Room("teepee", "inside of an authentic Sioux teepee.");
        monument = new Room ("monument", "in a ruined Lincoln Monument.");
        pool = new Room ("pool", "at the foot of the Reflecting Pool at the Lincoln Monument");
        outside1 = new Room("outside1", "outside in a barren landscape. An old theater can be seen to the west.");
        outside2 = new Room("outside2", "outside in a barren landscape. Monkey screeching can be heard to the east.");
        outside3 = new Room("outside3", "outside in a barren landscape. A statue of a man" + "\n" + "in a feather headdress be seen to the west.");
        outside4 = new Room("outside4", "outside in a barren landsape. A trail of paint leads to the east.");
        outside5 = new Room("outside5", "outside in a barren landscape. The iconic Lincoln Monument lies in ruins to the west.");
        outside6 = new Room("outside6", "outside in a barren landscape. You are at the gates of the White House, now guarded closely by the Communists.");
        whiteHouseEntrance = new Room("whiteHouseEntrance", "inside the White House. Dark hallways lead to the east and west.");
        whiteHouseHallwayWest = new Room("whiteHouseHallwayWest", "in a dark hallway in the west wing of the White House.");
        whiteHouseHallwayEast = new Room("whiteHouseHallwayEast", "in a dark hallway in the east wing of the White House.");
        whiteHouseHallwayMaintenance = new Room("whiteHouseHallwayMaintenance", "at the entrance of two rooms. A maintenance room marked '#1' leads to the west. A maintence room marked '#2' leads to the east.");
        maintenance1 = new Room("maintenance1", "in a dark room surrounded by cleaning supplies. The door has locked itself.");
        maintenance2 = new Room("maintenance2", "in a dark room surrounded by cleaning supplies. A small keypad is on the wall.");
        control = new Room("control", "in the White House control room. A countdown appears on the screen.");
        whiteHouseHallwayOval = new Room("whiteHouseHallwayOval", "at the entrance of a room. The door bears the presidential seal. Talking can be heard from within the room.");
        oval = new Room("oval", "inside the Oval Office. You are now face to face with Joseph Stalin, leader of the Communists.");
        
        // initialise room exits
        motel.setExit("north", outside1);
        outside1.setExit("south", motel);
        outside1.setExit("west", theater);
        theater.setExit("east", outside1);
        outside1.setExit("north", outside2);
        outside2.setExit("south", outside1);
        outside2.setExit("east", zoo);
        zoo.setExit("west", outside2);
        outside2.setExit("north", outside3);
        outside3.setExit("south", outside2);
        outside3.setExit("west", indian);
        indian.setExit("east", outside3);
        indian.setExit("north", teepee);
        teepee.setExit("south", indian);
        outside3.setExit("north", outside4);
        outside4.setExit("south", outside3);
        outside4.setExit("east", art);
        art.setExit("west", outside4);
        outside4.setExit("north", outside5);
        outside5.setExit("west", monument);
        monument.setExit("east", outside5);
        monument.setExit("north", pool);
        pool.setExit("south", monument);
        outside5.setExit("north", outside6);
        outside6.setExit("south", outside5);
        outside6.setExit("north", whiteHouseEntrance);
        whiteHouseEntrance.setExit("south", outside6);
        whiteHouseEntrance.setExit("west", whiteHouseHallwayWest);
        whiteHouseHallwayWest.setExit("east", whiteHouseEntrance);
        whiteHouseHallwayWest.setExit("north", whiteHouseHallwayOval);
        whiteHouseHallwayOval.setExit("south", whiteHouseHallwayWest);
        whiteHouseHallwayOval.setExit("north", oval);
        oval.setExit("south", whiteHouseHallwayOval);
        whiteHouseEntrance.setExit("east", whiteHouseHallwayEast);
        whiteHouseHallwayEast.setExit("west", whiteHouseEntrance);
        whiteHouseHallwayEast.setExit("north", whiteHouseHallwayMaintenance);
        whiteHouseHallwayMaintenance.setExit("south", whiteHouseHallwayEast);
        whiteHouseHallwayMaintenance.setExit("west", maintenance1);
        maintenance1.setExit("east", whiteHouseHallwayMaintenance);
        whiteHouseHallwayMaintenance.setExit("east", maintenance2);
        maintenance2.setExit("west", whiteHouseHallwayMaintenance);
        maintenance2.setExit("east", control);
        control.setExit("west", maintenance2);

        //adds items
        motel.addItem (new Item("newspaper", 1, 
        "SENATOR JOSEPH MCCARTHY HUMILIATED" + "\n" + "By JULIUS ROSENBERG" + "\n" + "\t" + "Senator Joseph McCarthy issued his latest warning to the American public of Communist subversives last night in a national broadcast." + "\n" + "During the speech, he claimed that the possibility of Soviet Union spies working in the government posed a very grave threat." + "\n" + "\t" + "The speech, however, went unplanned when several members of the audience began to heckle McCarthy." + "\n"+ "The heckling soon escalated until McCarthy was forced to stop his speech" + "\n" + "as members of the audience threw items and laughed at him.",
        "There is nothing to see here."));
        theater.addItem (new Item("hat", 3, "A bloody top hat with the initials 'A.L.' inscribed on the inside.", "You have found a key!"));
        zoo.addItem (new Item("banana", 1, "A yellow fruit packed with potassium. Preferred treat of all primates.", "There is nothing to see here."));
        indian.addItem (new Item("paper", 1, "MANIFESTO OF THE COMMUNIST PARTY" + "\n" + "By Karl Marx and Friedrich Engels" + "\n" + "Let the ruling classes tremble at a Communistic revolution." + "\n" + "The proletarians have nothing to lose but their chains." + "\n"+ "They have a world to win." + "\n" + "Workingmen of all countries unite!", 
        "There is nothing to see here."));
        art.addItem (new Item("Picasso painting", 50, "An authentic Pablo Picasso Painting.", "A booby trap has been set off! You have been electrocuted and lost 15 points of health."));
        art.addItem (new Item("Van Gogh painting", 50, "An authentic Vincent Van Gogh Painting.", "You have found a key!"));

        //adds character
        theater.addCharacter (new Character("Abraham Lincoln", "\t" + "\t" + "An Abraham Lincoln impersonator caught in the wrong place at the wrong time."+ "\n" + "\t" + "\t" + "He has taken refuge in the theater.",
        "Are you the famous Joseph McCarthy? I thought you looked familiar" + "\n" + "You won't believe what happened. Almost overnight," + "\n" + "Communist infilitrators led a revolt against the government. They have now taken" + "\n" + "control of Washington D.C. You were right. Now please help us." + "\n" + "You're our last hope. I heard the government hid keys" + "\n" + "all around the city. Find them and take them." + "\n" + "I don't know what they do, but I'm sure they're important." + "\n" + "Oh, and arm yourself. It's dangerous out there."));
        zoo.addCharacter (new Character("monkey", "\t" + "\t" + "One of the only animals left who still hasn't" + "\n" + "\t" + "\t" + "figured out how to open his cage.",
        "OOH OOH AHH AHH!"));
        monument.addCharacter (new Character("Alben Barkley", "\t" + "\t" + "Vice President of the United States, and only" + "\n" + "\t" + "\t" + "survivor of the White House staff.",
        "Senator McCarthy! Arent't I glad to see" + "\n" + "you here. They killed everyone." + "\n" + "I'm the only who managed to escape. We won't be safe" + "\n" + "for long. They're planning to blow us all up." + "\n" + "We hid keys around the city. Take them to the White House." + "\n" + "Use them to disarm the defense system in the control room." + "\n" + "But be careful.They've got the place closely guarded."));

        //adds weapons
        theater.addWeapon(new Weapon("pistol", 2));
        
        //adds enemies
        zoo.addEnemies(1);
        
        //adds key to room
        theater.addKey(new Key(1));
        zoo.addKey(new Key(2));
        teepee.addKey(new Key(3));
        
        //adds keyword to unlock key
        theater.addKeyword("take hat");
        theater.addKeyword("move hat");
        zoo.addKeyword("give banana to monkey");
        indian.addKeyword("move teepee");
        art.addKeyword("move Van Gogh painting");
        pool.addKeyword("swim");
        
        //key available upon entry into teepee
        teepee.setTeepeeKey(true);
        
        //sets ability to swim
        pool.setCanSwim(true);
        
        //sets start of challenge
        outside4.setChallenge(true);
        
        //locks room
        maintenance1.setRoomLock(true);

        player.setCurrentRoom(motel);  // start game outside
        previousRoom = motel; //sets intial previous room
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
        System.out.println("Welcome to The Takeover!");
        System.out.println("You are now playing as Senator Joseph McCarthy.");
        System.out.println("Type 'help' if you need help.");
        System.out.println("Type 'look' to find out what's in your room.");
        System.out.println();
        //printLocationInfo();
        System.out.println(player.getCurrentRoom().getLongDescription());
        //System.out.println();
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
            System.out.println("I don't know what you mean...");
            return false;
        }

        String firstCommandWord = command.getCommandWord();
        String secondCommandWord = command.getSecondWord();
        String thirdCommandWord = command.getThirdWord();
        String fourthCommandWord = command.getFourthWord();
        player.checkKeyword(command);
        if (firstCommandWord.equals("help")) {
            printHelp();
        }
        else if (firstCommandWord.equals("go")) {
            if(secondCommandWord.equals("back")){
                goBack();
            }
            else{
                //goRoom(command);
                previousRoom = player.getCurrentRoom();
                String direction = command.getSecondWord();
                System.out.println(player.walk(direction));
                //printLocationInfo();
            }
        }
        else if (firstCommandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (firstCommandWord.equals("look")){
            lookCommand();
        }
        else if(firstCommandWord.equals("blacklist")){
            blackList();
        }
        else if(firstCommandWord.equals("take")){
//             if(secondCommandWord.equals("ammo")){
//                 System.out.println(player.takeAmmo());
//             }
//             else if(secondCommandWord.equals("weapon")){
//                 System.out.println(player.takeWeapon());
//             }
//             else if(secondCommandWord.equals("key")){
//                 System.out.println(player.takeKey());
//             }
//             else{
                System.out.println(player.take(command));
            
        }
        else if(firstCommandWord.equals("drop")){
            System.out.println(player.drop(command));
        }
        else if(firstCommandWord.equals("inventory")){
            System.out.println(player.getInventory());
        }
        else if(firstCommandWord.equals("shoot")){
            System.out.println(player.kill(command));
        }
        else if(firstCommandWord.equals("holster")){
            System.out.println(player.getHolster());
        }
        else if(firstCommandWord.equals("view")){
            System.out.println(player.viewItem(command));
        }
        else if(firstCommandWord.equals("move")){
            System.out.println(player.moveItem(command));
        }
        else if(firstCommandWord.equals("talk")){
            System.out.println(player.talk(command));
        }
        else if(firstCommandWord.equals("give")){
            System.out.println(player.give(command));
        }
        else if(firstCommandWord.equals("swim")){
            System.out.println(player.swim());
        }
        else if((firstCommandWord.equals("Friedrich"))||(firstCommandWord.equals("Karl"))){
            System.out.println(player.answerChallenge(command));
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
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around Washington D.C.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
        System.out.println();
    }

    //     /** 
    //      * Try to go in one direction. If there is an exit, enter
    //      * the new room, otherwise print an error message.
    //      */
    //     private void goRoom(Command command) 
    //     {
    //         if(!command.hasSecondWord()) {
    //             // if there is no second word, we don't know where to go...
    //             System.out.println("Go where?");
    //             return;
    //         }
    // 
    //         String direction = command.getSecondWord();
    // 
    //         // Try to leave current room.
    //         Room nextRoom = null;
    //         previousRoom = currentRoom;
    //         
    //         nextRoom = currentRoom.getExit(direction); //replaces above code
    //         if (nextRoom == null) {
    //             System.out.println("There is no door!");
    //         }
    //         else {
    //             currentRoom = nextRoom;
    //             System.out.println("You are " + currentRoom.getDescription());
    //             System.out.println(currentRoom.getExitString()); 
    //         }
    //     }

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

    private void printLocationInfo(){
        //System.out.println("You are " + currentRoom.getDescription());
        //System.out.println(currentRoom.getExitString()); 
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    private void lookCommand(){
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    private void blackList(){
        System.out.println("Congratulations! You have blacklisted another celebrity.");
    }
    //     private void goBack(){
    //             if(previousRoom != null){
    //             currentRoom = previousRoom;
    //             printLocationInfo();
    //         }
    private void goBack(){
        if((player.getCurrentRoom().returnRoomIsLocked() == true)||(player.getCurrentRoom().returnChallengeComplete() == false)){
            System.out.println("You cannot go back.");
        }
        else if(previousRoom != null){
            player.setCurrentRoom(previousRoom);
            printLocationInfo();
        }
    }
}

