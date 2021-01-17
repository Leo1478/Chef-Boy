/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import mygame.state.Main;

/**
 * all items that can be picked up 
 * @author leoze
 */
public abstract class Item extends GameObject{
    
    private boolean pickedUp = false;
    private float pickUpRadius;
    
    private float rotation = 0;
    
    private ItemPic itemPic;
    
    public Item(SimpleApplication app, Vector3f position, String name){
        super(app, position, name);
    }
    
    public void behaviour(ChefBoy chefBoy){
        
        rotate();
        
    }
    
    
    private void rotate(){
        
        rotation +=0.001;
        
        Quaternion roatation = new Quaternion();
        roatation.fromAngles(0, rotation , 0); // y rotation,  reverse number

        getModel().setLocalRotation(roatation); // change model rotation 
    }
    
    public void pickedUp(){
        pickedUp = true;
        
        deleteModel();
    }
    
    /**
     * @return the pickedUp
     */
    public boolean getPickedUp() {
        return pickedUp;
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

    /**
     * @return the itemPic
     */
    public ItemPic getItemPic() {
        return itemPic;
    }

    /**
     * @param itemPic the itemPic to set
     */
    public void setItemPic(ItemPic itemPic) {
        this.itemPic = itemPic;
    }
   
}
