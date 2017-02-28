import java.util.*;
/**
 * Class Room - a room in an adventure game.
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 */
public class Room 
{
    public String roomName;
    public String description;
    public Room northExit;
    public Room southExit;
    public Room eastExit;
    public Room westExit;
    private HashMap<String, Room> exits = new HashMap <String, Room>();
    private ArrayList <Item> items = new ArrayList<Item>(); 
    private ArrayList <Character> characters = new ArrayList <Character>();
    private ArrayList <Weapon> weapons = new ArrayList <Weapon>();
    private ArrayList <String> keywords = new ArrayList <String>();
    private ArrayList <Key> Keys = new ArrayList <Key>();
    private String enemies [];
    private int enemiesLeft = 0; 
    private boolean ammoIsAvailable;
    private int ammoAvailable;
    //private boolean weaponIsAvailable;
    private boolean keyIsAvailable;
    private boolean teepeeKey;
    private boolean canSwim;
    private boolean startChallenge;
    private boolean roomIsLocked;
    private boolean challengeComplete = true;
    

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String roomName, String description) 
    {
        this.roomName = roomName;
        this.description = description;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east, Room south, Room west) 
    {
        if(north != null)
            exits.put("north", north);
        if(east != null)
            exits.put("east", east);
        if(south != null)
            exits.put("south", south);
        if(west != null)
            exits.put("west", west);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    public String getExitString(){
        String returnString = "Exits: ";
        Set<String> keys = exits.keySet();
        for(String exit : keys){
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction){
        return (Room)exits.get(direction);
    }

    public void setExit(String direction, Room neighbor){
        exits.put(direction, neighbor);
    }

    public String getLongDescription(){
        String resultString = "You are " + description + "\n" + getExitString() + "\n";
        if(items.size() != 0){
            resultString += "This room contains the following items: ";
            Iterator iter = items.iterator ();
            while(iter.hasNext()){
                Item nextItem = (Item)iter.next();
                resultString += "\n " + "\t" + nextItem.getItemDescription();
            }
        }
        resultString += "\n";
        if(characters.size() != 0){
            resultString += "This room contains the following characters: ";
            Iterator iter = characters.iterator ();
            while(iter.hasNext()){
                Character nextCharacter = (Character)iter.next();
                resultString += "\n " + "\t" + nextCharacter.getCharacterDescription();
            }
        }
        resultString += "\n";
        if(weapons.size() != 0){
            resultString += "This room contains the following weapons: ";
            Iterator iter = weapons.iterator ();
            while(iter.hasNext()){
                Weapon nextWeapon = (Weapon)iter.next();
                resultString += "\n " + "\t" + nextWeapon.getWeaponDescription();
            }
        }
        resultString += "\n";
        if(enemiesLeft != 0){
            resultString += "\n" + "This room is guarded by " + enemiesLeft + " enemies that must be killed.";
        }
        
        return resultString;
    }

//     public String getLocationInfo(){
//         String locationInfo = "You are " + description + "\n" + getExitString() + "\n";
//         return locationInfo;
//     }

    public void addItem(Item item){
        items.add(item);
    }

    public void addCharacter(Character character){
        characters.add(character);
    }

    public void addWeapon(Weapon weapon){
        weapons.add(weapon);
        //weaponIsAvailable = true;
    }

    public int getEnemiesLeft(){
        return enemiesLeft;
    }
    
    public void setEnemiesLeft(int x){
        enemiesLeft = x;
    }
    
    public void addEnemies(int numberOfEnemies){
        enemies = new String [numberOfEnemies];
        for(int x = 0; x < enemies.length; x++){
            enemies[x] = "alive";
        }
        setEnemiesLeft(numberOfEnemies);
    }
    
    public void setEnemies(int enemy, String death){
        enemies[enemy] = death;
    }

    public ArrayList getItems(){
        return items;
    }
    
    public ArrayList getWeapons(){
        return weapons;
    }
    
    public void setAmmoAvailable (int ammo, boolean x){
        ammoAvailable = ammo;
        ammoIsAvailable = x;
    }
    
    public boolean getAmmoIsAvailable(){
        return ammoIsAvailable;
    }
    
    public int getAmmoAvailable(){
        return ammoAvailable;
    }
    
//     public boolean getWeaponIsAvailable(){
//         return weaponIsAvailable;
//     }
    
//     public void setWeaponIsAvailable(boolean a){
//         weaponIsAvailable = a;
//     }
    
    public void addKeyword (String k){
        keywords.add(k);
    }
    
    public ArrayList getKeywords (){
        return keywords;
    }
    
    public void addKey(Key k){
        Keys.add(k);
    }
    
    public ArrayList getKeys(){
        return Keys;
    }
    
    public boolean getKeyIsAvailable(){
        return keyIsAvailable;
    }
    
    public void setKeyIsAvailable(boolean x){
        keyIsAvailable = x;
    }
    
    public ArrayList getCharacters(){
        return characters;
    }
    
    public boolean returnTeepeeKey(){
        return teepeeKey;
    }
    
    public void setTeepeeKey(boolean x){
        teepeeKey = x;
    }
    
    public void setCanSwim(boolean x){
        canSwim = x;
    }
    
    public boolean returnCanSwim(){
        return canSwim;
    }
    
    public void setChallenge(boolean x){
        startChallenge = x;
    }
    
    public boolean returnStartChallenge(){
        return startChallenge;
    }
    
    public void setRoomLock(boolean x){
        roomIsLocked = x;
    }
    
    public boolean returnRoomIsLocked(){
        return roomIsLocked;
    }
    
    public void setChallengeComplete(boolean x){
        challengeComplete = x;
    }
    
    public boolean returnChallengeComplete(){
        return challengeComplete;
    }
}

