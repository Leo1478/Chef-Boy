package mygame.gameobject;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

/**
 * Item.java
 * all items that can be picked up 
 * @author Ariana Hou
 */
public abstract class Item extends GameObject implements Comparable{
    
    private boolean pickedUp = false; // if the item has been picked up
    private float pickUpRadius;
    
    private float rotation = 0;
    
    private ItemPic itemPic; // picture 
    
    /**
     * Item 
     * constructor 
     * @param app application 
     * @param position current position 
     * @param name object name
     */
    public Item(SimpleApplication app, Vector3f position, String name){
        super(app, position, name);
    }
    
    /**
     * behaviour
     * all item behaviour
     * @param tpf time per frame
     */
    public void behaviour(float tpf){
        
        rotate(tpf);
    }
    
    /**
     * rotate 
     * rotate item slowly
     * @param tpf time per frame 
     */
    private void rotate(float tpf){
        
        rotation += 1 * tpf;
        
        Quaternion roatation = new Quaternion();
        roatation.fromAngles(0, rotation , 0); // y rotation

        getModel().setLocalRotation(roatation); // change model rotation 
    }
    
    /**
     * pickedUp
     * set pickedUp to true, delete model
     */
    public void pickedUp(){
        pickedUp = true;
        
        deleteModel();
    }
    
    /**
     * compareTo
     * compare 2 item objects's class name 
     * @param o object to compare 
     * @return difference (-1, 0, or 1)
     */
    @Override
    public int compareTo(Object o){
        Item item = (Item) o;
        
        if(this.getClass().toString().compareTo(item.getClass().toString()) < 0){
            return -1;
        }
        else if(this.getClass().toString().compareTo(item.getClass().toString()) > 0){
            return 1;
        }
        return 0;
    }
    
    /**
     * getPickedUp
     * @return the pickedUp
     */
    public boolean getPickedUp() {
        return pickedUp;
    }
    

    /**
     * getPickUpRadius
     * @return the pickUpRadius
     */
    public float getPickUpRadius() {
        return pickUpRadius;
    }

    /**
     * setPickUpRadius
     * @param pickUpRadius the pickUpRadius to set
     */
    public void setPickUpRadius(float pickUpRadius) {
        this.pickUpRadius = pickUpRadius;
    }

    /**
     * getItemPic
     * @return the itemPic
     */
    public ItemPic getItemPic() {
        return itemPic;
    }

    /**
     * setItemPic
     * @param itemPic the itemPic to set
     */
    public void setItemPic(ItemPic itemPic) {
        this.itemPic = itemPic;
    } 
}
