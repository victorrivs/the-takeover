
/**
 * Items class. Define's an item's description and it's weight. Also determines
 * the text that is returned when the view and move commands are entered for the 
 * object.
 * 
 * @Victor Riveros 
 * @version 12.11.2014
 */
public class Item
{
    private String description;
    private int weight;
    private String viewText;
    private String moveText;
    
    public Item(String description, int weight, String viewText, String moveText){
        this.description = description;
        this.weight = weight;
        this.viewText = viewText; 
        this.moveText = moveText;
    }
    public String getItemDescription(){
        return "a " + description + " weighing " + weight + " lbs.";
    }
    public String getDescription(){
        return description;
    }
    public String getViewText(){
        return viewText;
    }
    public int getItemWeight(){
        return weight;
    }
    public String getMoveText(){
        return moveText;
    }
}
