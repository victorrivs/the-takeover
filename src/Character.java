
/**
 * Characters in the game. Defines the character's description, and the character's
 * dialogue.
 * 
 * @Victor Riveros 
 * @version 12.12.2014
 */
public class Character
{
    private String characterName;
    private String description;
    private String dialogue;
    public Character (String name, String description, String dialogue){
        characterName = name;
        this.description = description;
        this.dialogue = dialogue;
    }
    public String getCharacterDescription(){
        return characterName + ": " + "\n" + description;
    }
    public String getCharacterName(){
        return characterName;
    }
    public String getDialogue(){
        return dialogue;
    }
}
