package mygame.gameobject;

import com.jme3.math.Vector3f;

/**
 * Action.java
 * actions a character can have
 * @author Leo Zeng
 * 2020/12/31
 */
public interface Action {
    
    /**
     * attack
     * attack another character
     * @param character character to attack
     */
    public void attack(Character character);
    
    /**
     * move 
     * move this character 
     * @param change change in position 
     */
    public void move(Vector3f change);
    
    
}
