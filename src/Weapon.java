
/**
 * Weapons class defines a weapon, its description, and the number of ammo 
 * units in the weapon. Ammo can be added to the weapon if picked up, as well 
 * as subtracted if the weapon is used.
 * 
 * @Victor Riveros 
 * @12.18.2014
 */
public class Weapon
{
    private String description;
    private int ammo;
    public Weapon(String description, int ammo){
        this.description = description;
        this.ammo = ammo;
    }

    public String getDescription(){
        return description;
    }
    
    public String getWeaponDescription(){
        return "a " + description + " containing " + ammo + " bullets";
    }
    
    public int getAmmo(){
        return ammo;
    }
    
    public void addAmmo(int a){
        ammo += a;
    }
    
    public void subtractAmmo(int a){
        ammo -= a;
    }
}
