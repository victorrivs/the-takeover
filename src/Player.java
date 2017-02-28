import java.util.*;
/**
 * something something description
 * 
 * @Victor Riveros
 * @version 12.14.2014
 */
public class Player
{
    private Room currentRoom ;
    private ArrayList <Item> inventory = new ArrayList <Item>();
    private ArrayList <Weapon> holster = new ArrayList <Weapon>();
    private ArrayList <Key> keys = new ArrayList <Key>();
    private int healthPoints;

    public Player()
    {
        currentRoom = null;
        healthPoints = 100;
    }

    public Room getCurrentRoom(){
        return currentRoom;
    }

    public void setCurrentRoom(Room room){
        currentRoom = room;
    }

    public String walk(String x){
        String walkString = "This is not a valid exit.";
        Room nextRoom = currentRoom.getExit(x);
        if(currentRoom.returnRoomIsLocked() == true){
            walkString = "The door has locked behind you. You're trapped.";
        }
        else if(currentRoom.returnChallengeComplete() == false){
            walkString = "You must give a valid answer to continue." + "\n" + "Name one of the authors of the Communist Manifesto.";
        }
        else if(nextRoom != null){
            currentRoom = nextRoom;
            walkString = currentRoom.getLongDescription();
            if(currentRoom.returnStartChallenge() == true){
                walkString = challenge();
            }
            if (currentRoom.returnTeepeeKey() == true){
                currentRoom.setKeyIsAvailable(true);
                walkString += "You have found a key!";
            }
        }
        return walkString;
    }
    
    public String challenge(){
        currentRoom.setChallengeComplete(false);
        currentRoom.setChallenge(false);
        String challengeString = "You have been detained and questioned by communist troops." + "\n" + "Name one of the authors of the Communist Manifesto to continue.";
        return challengeString;
    }
    
    public String answerChallenge(Command command){
        String answerString = "That is an incorrect answer." + "\n" + "You have blown your cover and have been executed.";
        String answer = command.getCommandWord() + " " + command.getSecondWord();
        if(currentRoom.returnChallengeComplete() == true){
            answerString = "What?";
        }
        else if((answer.equals("Friedrich Engels"))||(answer.equals("Karl Marx"))){
            answerString = "You have answered correctly." + "\n" + "You are clear to go.";
            currentRoom.setChallengeComplete(true);
        }
        return answerString;
    }

    //     public String take(Command command){
    //         String takeString = "Sorry! This item does not exist in this room.";
    //         if(!command.hasSecondWord()){
    //             return
    //             takeString = "Take what?";
    //         }
    //         else{
    //             ArrayList <Item> itemList = new ArrayList <Item> (currentRoom.getItems());
    //             String itemTaken = command.getSecondWord();
    //             Item itemToTake = null;
    //             for(int x = 0; x < itemList.size(); x++){
    //                 itemToTake = (Item)itemList.get(x);
    //                 if(itemToTake.getDescription().equals(itemTaken)){
    //                     inventory.add(itemToTake);
    //                     takeString = "You have taken " + itemToTake.getItemDescription();
    //                     currentRoom.getItems().remove(itemToTake);
    //                 }
    //             }
    //             ArrayList <Weapon> weaponList = new ArrayList <Weapon> (currentRoom.getWeapons());
    //             Weapon weaponToTake = null;
    //             takeString = "You have picked up: ";
    //             for (int x = 0; x < weaponList.size(); x++){
    //                 weaponToTake = (Weapon)weaponList.get(x);
    //                 if(weaponToTake.getDescription().equals(itemTaken)){
    //                     holster.add(weaponToTake);
    //                     currentRoom.getWeapons().remove(weaponToTake);
    //                     takeString += weaponToTake.getWeaponDescription();
    //                 }
    //             }
    //             currentRoom.setWeaponIsAvailable(false);
    //         }
    //         return takeString;
    //     }
    public String take(Command command){
        String takeString = "Sorry! There is nothing to take.";
        if(!command.hasSecondWord()){
            return
            takeString = "Take what?";
        }
        else{
            ArrayList <Item> itemList = new ArrayList <Item> (currentRoom.getItems());
            ArrayList <Weapon> weaponList = new ArrayList <Weapon> (currentRoom.getWeapons());
            String itemTaken = command.getSecondWord();
            Item itemToTake = null;
            Weapon weaponToTake = null;
            if(itemTaken.equals("ammo")){
                takeString = takeAmmo();
            }
            else if(itemTaken.equals("key")){
                takeString = takeKey();
            }
            else{
                if(itemList.size() != 0){
                    for(int x = 0; x < itemList.size(); x++){
                        itemToTake = (Item)itemList.get(x);
                        if(itemToTake.getDescription().equals(itemTaken)){
                            if(itemToTake.getItemWeight() > 49){
                                takeString = "This item is too heavy to take. Try moving it instead.";
                            }
                            else{
                                inventory.add(itemToTake);
                                takeString = "The following item(s) have been added to your inventory: " + "\n" + "\t" + itemToTake.getItemDescription();
                                currentRoom.getItems().remove(itemToTake);
                                if(itemTaken.equals("hat")){
                                    takeString += "\n" + "You have found a key!";
                                }
                            }
                        }
                    }
                }
                if(weaponList.size() != 0){
                    for(int y = 0; y < weaponList.size(); y++){
                        weaponToTake = (Weapon)weaponList.get(y);
                        if(weaponToTake.getDescription().equals(itemTaken)){
                            holster.add(weaponToTake);
                            takeString = "The following weapon has been added to your holster: " + "\n" + "\t" + weaponToTake.getWeaponDescription();
                            currentRoom.getWeapons().remove(weaponToTake);
                            //currentRoom.setWeaponIsAvailable(false);
                        }
                    }
                }
            }
        }
        return takeString;
    }

    public String takeAmmo(){
        String ammoString = "There is no ammo to take.";
        if((currentRoom.getEnemiesLeft() == 0)&&(currentRoom.getAmmoIsAvailable()== true)){
            int ammoAvailable = 1 + (int)(Math.random()*5);
            ammoString = "You have picked up ammo. Your holster now contains: ";
            for(int x=0; x < holster.size(); x++){
                holster.get(x).addAmmo(ammoAvailable);
                ammoString += "\n" + "\t" + holster.get(x).getWeaponDescription();
            }
            currentRoom.setAmmoAvailable(0, false);
        }
        return ammoString; 
    }
    // 
    //     public String takeWeapon(){
    //         String weaponString = "There is no weapon to take.";
    //         if(currentRoom.getWeaponIsAvailable() == true){
    //             ArrayList <Weapon> weaponList = new ArrayList <Weapon> (currentRoom.getWeapons());
    //             Weapon weaponToTake = null;
    //             weaponString = "You have picked up: ";
    //             for (int x = 0; x < weaponList.size(); x++){
    //                 weaponToTake = (Weapon)weaponList.get(x);
    //                 holster.add(weaponToTake);
    //                 currentRoom.getWeapons().remove(weaponToTake);
    //                 weaponString += weaponToTake.getWeaponDescription();
    //             }
    //             currentRoom.setWeaponIsAvailable(false);
    //         }
    //         return weaponString;
    //     }

    public String takeKey(){
        String keyString = "You cannot take that.";
        if(currentRoom.getKeyIsAvailable() == true){
            ArrayList <Key> keyList = new ArrayList <Key> (currentRoom.getKeys());
            Key keyToTake = null;
            for(int x = 0; x < keyList.size(); x++){
                keyToTake = (Key)keyList.get(x);
                keys.add(keyToTake);
                currentRoom.getKeys().remove(keyToTake);
            }
            currentRoom.setKeyIsAvailable(false);
            keyString = "You have taken key number " + keyToTake.getKeyNumber() + ".";
        }
        return keyString;
    }

    public String drop(Command command){
        String dropString = "This item does not exist in your inventory.";
        if(!command.hasSecondWord()){
            return
            dropString = "Drop what?";
        }
        else{
            String itemDropped = command.getSecondWord();
            Item itemToDrop = null;
            for(int x = 0; x < inventory.size(); x++){
                itemToDrop = (Item)inventory.get(x);
                if(itemToDrop.getDescription().equals(itemDropped)){
                    inventory.remove(itemToDrop);
                    currentRoom.getItems().add(itemToDrop);
                    dropString = "You have dropped " + itemToDrop.getItemDescription();
                }
            }
        }
        return dropString;
    }

    public String getInventory(){
        String inventoryString = "";
        if(inventory.size() != 0){
            inventoryString = "Your inventory contains the following items: ";
            for(int x = 0; x < inventory.size(); x++){
                inventoryString += "\n" + "\t" + inventory.get(x).getItemDescription();
            }
        }
        else{
            inventoryString = "Your inventory contains no items." + "\n" + "Please use the take command to add an item in the room to your inventory.";
        }
        return inventoryString;
    }

    public String getHolster(){
        String holsterString = "";
        if(holster.size() != 0){
            holsterString = "Your holster contains the following weapons: ";
            for(int x = 0; x < holster.size(); x++){
                holsterString += "\n" + "\t" + holster.get(x).getWeaponDescription();
            }
        }
        else{
            holsterString = "Your holster contains no weapons." + "\n" + "Please use the take weapon command to add a weapon in the room to your holster.";
        }
        return holsterString;
    }

    public String kill(Command command){
        String killString = "There are no enemies to kill.";
        if(command.hasSecondWord() == false){
            killString = "Shoot with what?";
        }
        else if(currentRoom.getEnemiesLeft() > 0){
            int enemiesKilled = 0;
            int enemiesAlive = currentRoom.getEnemiesLeft();
            String weaponRequested = command.getSecondWord();
            int hitPoints = 0;
            if(holster.size() != 0){
                for(int x = 0; x < holster.size(); x++){
                    Weapon weaponUsed = (Weapon)holster.get(x);
                    if(weaponUsed.getDescription().equals(weaponRequested)){
                        hitPoints = (int)(Math.random()*weaponUsed.getAmmo());
                        if(hitPoints < currentRoom.getEnemiesLeft()){
                            for(int y = 0; y < hitPoints; y++){
                                currentRoom.setEnemies(y,"dead");
                                enemiesKilled ++;
                            }
                            weaponUsed.subtractAmmo(enemiesKilled);
                            enemiesAlive = currentRoom.getEnemiesLeft() - hitPoints; 
                            currentRoom.setEnemiesLeft(enemiesAlive);
                            killString = "You have killed " + enemiesKilled + " enemies and failed to kill " + enemiesAlive + "." + "\n" + "Your cover has been blown. You are taking damage.";
                        }
                        if(hitPoints >= currentRoom.getEnemiesLeft()){
                            for(int z=0; z < currentRoom.getEnemiesLeft(); z++){
                                currentRoom.setEnemies(z, "dead");
                                enemiesKilled ++;
                            }
                            weaponUsed.subtractAmmo(enemiesKilled);
                            currentRoom.setEnemiesLeft(0);
                            enemiesAlive = 0;
                            killString = "You have killed all " + enemiesKilled + " Communist enemies.";
                            int ammoToAdd = (int)(Math.random()*10);
                            currentRoom.setAmmoAvailable(ammoToAdd, true);
                        }
                        healthPoints -= enemiesAlive;
                        killString += "\n" + "Your remaining health is " + healthPoints;
                    }
                    else{
                        killString = "This weapon does not exist.";
                    }
                }
            }
            else{
                killString = "You have no weapons to kill with.";
            }
        }
        return killString;
    }

    public void checkKeyword(Command command){
        String keywordToCheck;

        if(command.hasFourthWord() == true){
            keywordToCheck = command.getCommandWord() + " " + command.getSecondWord() + " " + command.getThirdWord() + " " + command.getFourthWord();
        }
        else if(command.hasThirdWord() == true){
            keywordToCheck = command.getCommandWord() + " " + command.getSecondWord() + " " + command.getThirdWord();
        }
        else if(command.hasSecondWord() == true){
            keywordToCheck = command.getCommandWord() + " " + command.getSecondWord();
        }
        else{
            keywordToCheck = command.getCommandWord();
        }
        ArrayList <String> keywordList = new ArrayList <String> (currentRoom.getKeywords());
        String keyword = "";
        for(int x = 0; x < keywordList.size(); x++){
            keyword = (String)keywordList.get(x);
            if(keyword.equals(keywordToCheck)){
                currentRoom.setKeyIsAvailable(true);
            }
        }
    }

    public String viewItem(Command command){
        String viewString = "This item does not exist in your inventory.";
        if(!command.hasSecondWord()){
            return
            viewString = "View what?";
        }
        else{
            String itemViewed = command.getSecondWord();
            Item itemToView = null;
            for(int x = 0; x < inventory.size(); x++){
                itemToView = (Item)inventory.get(x);
                if(itemToView.getDescription().equals(itemViewed)){
                    viewString = itemToView.getViewText();
                }
            }
        }
        return viewString;
    }

    public String moveItem(Command command){
        String moveString = "Sorry! There is nothing to move.";
        if(!command.hasSecondWord()){
            return
            moveString = "Move what?";
        }
        else{
            ArrayList <Item> itemList = new ArrayList <Item> (currentRoom.getItems());
            String itemMoved = command.getSecondWord();
            Item itemToMove = null;
            if(itemList.size() != 0){
                for(int x = 0; x < itemList.size(); x++){
                    itemToMove = (Item)itemList.get(x);
                    if(itemToMove.getDescription().equals(itemMoved)){
                        moveString = "You have moved a " + itemToMove.getDescription() + ". " + "\n" + itemToMove.getMoveText();
                        if(itemMoved.equals("Picasso painting")){
                            healthPoints -= 15;
                            moveString += "\n" + "Health: " + healthPoints + " points";
                        }
                    }
                }
            }
        }
        return moveString;
    }

    public String talk(Command command){
        String talkString = "There is no one to talk to here.";
        if(!command.hasThirdWord()){
            return
            talkString = "Talk to who?";
        }
        else{
            String characterName;
            if(command.hasFourthWord() == true){
                characterName = command.getThirdWord() + " " + command.getFourthWord();
            }
            else{
                characterName = command.getThirdWord();
            }
            ArrayList <Character> characterList = new ArrayList <Character> (currentRoom.getCharacters());
            Character characterToTalk = null;
            if(characterList.size() != 0){
                for(int x = 0; x < characterList.size(); x++){
                    characterToTalk = (Character)characterList.get(x);
                    if(characterToTalk.getCharacterName().equals(characterName)){
                        talkString = characterToTalk.getDialogue();
                    }
                }
            }
        }
        return talkString;
    }

    public String give(Command command){
        String giveString = "You cannot do that.";
        if(!command.hasSecondWord()){
            giveString = "Give what?";
        }
        else if(!command.hasFourthWord()){
            giveString = "To who?";
        }
        else{
            String item = command.getSecondWord();
            String character = null;
            if(command.hasFifthWord() == true){
                character = command.getFourthWord() + " " + command.getFifthWord();
            }
            else{
                character = command.getFourthWord();
            }
            ArrayList <Character> characterList = new ArrayList <Character> (currentRoom.getCharacters());
            Character characterToGive = null;
            Item itemToGive = null;
            if(characterList.size() != 0){
                for(int x = 0; x < characterList.size(); x++){
                    characterToGive = (Character)characterList.get(x);
                    if(characterToGive.getCharacterName().equals(character)){
                        if(inventory.size() != 0){
                            for(int y = 0; y < inventory.size(); y++){
                                itemToGive = (Item)inventory.get(y);
                                if(itemToGive.getDescription().equals(item)){
                                    if((item.equals("banana"))&&(character.equals("monkey"))){
                                        giveString = "The monkey has handed you a key." + "\n" + takeKey();
                                        inventory.remove(itemToGive);
                                    }
                                    else{
                                        giveString = character + " does not want a " + item + ".";
                                    }
                                }
                                else{
                                    giveString = "The item you want to give does not exist in your inventory.";
                                }
                            }
                        }
                        else{
                            giveString = "You have nothing to give.";
                        }
                    }
                    else{
                        giveString = "This character does not exist.";
                    }
                }
            }
            else{
                giveString = "There are no characters in this room.";
            }
        }
        return giveString;
    }
    
    public String swim(){
        String swimString = "There is no water to swim here.";
        if(currentRoom.returnCanSwim() == true){
            swimString = "You have found a key in the pool!";
        }
        return swimString;
    }
}
