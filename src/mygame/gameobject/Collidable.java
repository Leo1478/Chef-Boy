package mygame.gameobject;

/**
 * Collidable.java
 * object that can have collision 
 * @author Leo Zeng
 * 2020/12/31
 */
public interface Collidable {
    
    /**
     * initCollision
     * init collision for character
     */
    public void initCollision();
    
    /**
     * updateCollision
     * update collision mesh
     */
    public void updateCollision();
    
    /**
     * deleteCollision
     * delete collision mesh 
     */
    public void deleteCollision();
}
