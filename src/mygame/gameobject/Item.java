/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

import com.jme3.math.Vector3f;
import mygame.state.Main;

/**
 * all items that can be picked up 
 * @author leoze
 */
public abstract class Item extends GameObject{
    
    private boolean playerPickUp;
    private float pickUpRadius;
    
    public Item(Main main, Vector3f position, String name){
        super(main, position, name);
    }
    
    public void behaviour(Player player){
        pickUp(player);
    }
    
    /**
     * if player is within pickUpRadius of item, it will be picked up
     * @param player 
     */
    void pickUp(Player player){
        
        double distance;
        
        double x = this.getPosition().x;
        double x1 = player.getPosition().x;
        double z = this.getPosition().z;
        double z1 = player.getPosition().z;
        distance = Math.sqrt(Math.pow(x1-x, 2) + Math.pow(z1-z, 2));
        
        
        if (getPickUpRadius() > distance){
            setPlayerPickUp(true); 
            System.out.println("picked up item");
            main.getRootNode().detachChild(getModel()); // remove model 
        }
        
    } 

    /**
     * @return the playerPickUp
     */
    public boolean isPlayerPickUp() {
        return playerPickUp;
    }

    /**
     * @param playerPickUp the playerPickUp to set
     */
    public void setPlayerPickUp(boolean playerPickUp) {
        this.playerPickUp = playerPickUp;
    }

    /**
     * @return the pickUpRadius
     */
    public float getPickUpRadius() {
        return pickUpRadius;
    }

    /**
     * @param pickUpRadius the pickUpRadius to set
     */
    public void setPickUpRadius(float pickUpRadius) {
        this.pickUpRadius = pickUpRadius;
    }
}
