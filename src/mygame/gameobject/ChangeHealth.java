package mygame.gameobject;

/**
 * ChangeHealth.java
 * interface for changing a character's health
 * @author Leo Zeng
 * 2020/12/31
 */
public interface ChangeHealth {
    
    /**
     * addHealth
     * increase character health
     * @param amount amount to add
     */
    public void addHealth(int amount);
    
    /**
     * removeHealth 
     * decrease character health
     * @param amount amount to decrease
     */
    public void removeHealth(int amount);
    
    /**
     * takeDamage
     * check if character should take damage
     * @param amount amount to decrease 
     */
    public void takeDamage(int amount);
}
