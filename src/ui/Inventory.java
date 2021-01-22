/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.Vector2f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.system.AppSettings;
import com.jme3.ui.Picture;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import mygame.gameobject.Item;
import mygame.gameobject.ItemPic;
import mygame.state.GameState;
import mygame.state.InventoryState;
import static mygame.state.Main.SCREENHEIGHT;
import static mygame.state.Main.SCREENWIDTH;
import mygame.state.MenuState;
import mygame.state.SettingState;

/**
 *
 * @author leoze
 */
public class Inventory {
    
    private SimpleApplication app;
    
    private Item[] itemArray;
    private int pictureSize; // size of all itemPics in pixels
    private int size;
    private Button sortButton;
    private Button gameButton;
    private AppStateManager stateManager;

    public Inventory(SimpleApplication app, AppStateManager stateManager){
        this.app = app;
        this.stateManager = stateManager;
        size = 0;
        itemArray = new Item[50];

    }
    
    public void init(){
        
       initBackground();
       initItemPicture();
       
       sortButton = new Button(app, new Rectangle(50, 650, 320, 100), "UI/return to game.png");
       gameButton = new Button(app, new Rectangle(50, 500, 320, 100), "UI/return to game.png");
    }
    
    /**
     * init background of inventory 
     */
    private void initBackground(){
        
        AppSettings settings = new AppSettings(true);
        
        Picture inventoryBackground = new Picture("inventoryBackground");
        inventoryBackground.setImage(app.getAssetManager(), "UI/inventory background.png", true);
        inventoryBackground.setWidth(SCREENWIDTH);
        inventoryBackground.setHeight(SCREENHEIGHT);
        inventoryBackground.setPosition(0, 0);
        inventoryBackground.setLocalTranslation(0, 0, 1); // z = 1 makes inventory in front of everything else 

        app.getGuiNode().attachChild(inventoryBackground);
        inventoryBackground.setQueueBucket(RenderQueue.Bucket.Gui);
        
    }
    
    /**
     * init each item picture in inventory
     */
    private void initItemPicture(){
        if(size > 0){
            for(Item i : itemArray){
                ItemPic current = i.getItemPic(); // get picture 
                app.getGuiNode().attachChild(current.getPicture()); // attatch to GuiNide
                current.getPicture().setQueueBucket(RenderQueue.Bucket.Gui);
            }            
        }

    }
    
    private void setPicturePosition(){
        
        int x = 200; // starting position of first itemPic
        int y = 500;
        
        for(int i = 0; i < size; i++){
            
            Item item = itemArray[i];
            ItemPic current = item.getItemPic();
            current.getPicture().setPosition(x, y); // set position of picture

            x += 120;
            if(i % 10 == 0){ // next row 
                y += 120;
                x = 200;
            }
        }
    }
    
    public void clickButton(Vector2f mousePosition){
        
        System.out.println("clickButton in inventory");
        
        Point point = new Point((int)mousePosition.x, (int)mousePosition.y);
        
        if(sortButton.getHitBox().contains(point)){ // sort button
             
        }
        
        if(gameButton.getHitBox().contains(point)){ // return to game button
            
            System.out.println("return to game");
            stateManager.getState(InventoryState.class).exitState();
            stateManager.getState(InventoryState.class).removeListener();
            stateManager.getState(InventoryState.class).cleanUp();
            
            stateManager.getState(GameState.class).enterState();
            stateManager.getState(GameState.class).addListener();
            app.getInputManager().setCursorVisible(false);
            
        }
    }
    
    
    public void update(){
        display();
        setPicturePosition();
    }
    
    public void add(Item item){
        if(size < 50){
            itemArray[size] = item;
            size ++;
        }
    }
    
    public void remove(int index){
        itemArray[index] = null;
    }
    
    public void sort(){
        
    }
    
    private void selectItem(Vector2f mousePosition){
        // selected = item at mouse location 
    }
    
    /**
     * drag item from position with mouse 
     * @param item 
     * @param mousePosition 
     */
    public void drag(Item item, Vector2f mousePosition){
        if(item != null){
            // make item follow mouse position 
        }
    }
    
    /**
     * set item down with mouse 
     * @param item 
     * @param mousePosition 
     */
    public void place(Item item, Vector2f mousePosition){
        // called with mouse lets go
        // place item at empty spot
        // if spot is not empty, return item to original location 
        
    }
    
    private void display(){
        // loop through each itemPic 
        // display each 
    }
    
    private void displaySelected(Item item, Vector2f mousePosition){
        // display selected item and it's changed position 
    }
    
}
