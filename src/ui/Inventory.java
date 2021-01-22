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
import java.util.Arrays;
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
       
       sortButton = new Button(app, new Rectangle(300, 50, 320, 100), "UI/sort.png");
       gameButton = new Button(app, new Rectangle(880, 50, 320, 100), "UI/return to game black.png");
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
        
        Picture grid = new Picture("grid");
        grid.setImage(app.getAssetManager(), "UI/grid.png", true);
        grid.setWidth(1180);
        grid.setHeight(580);
        grid.setPosition(160, 200);
        grid.setLocalTranslation(160, 200, 1); // z = 1 makes inventory in front of everything else 

        app.getGuiNode().attachChild(grid);
        grid.setQueueBucket(RenderQueue.Bucket.Gui);
        
    }
    
    /**
     * init each item picture in inventory
     */
    private void initItemPicture(){

            for(int i = 0; i < size; i++){
                Item item = itemArray[i];
                ItemPic current = item.getItemPic(); // get picture 
                app.getGuiNode().attachChild(current.getPicture()); // attatch to GuiNide
                current.getPicture().setQueueBucket(RenderQueue.Bucket.Gui);
            }            


    }
    
    private void setPicturePosition(){
        
        int x = 160; // starting position of first itemPic
        int y = 680;
        
        for(int i = 0; i < size; i++){
            
            Item item = itemArray[i];
            ItemPic current = item.getItemPic();
            current.getPicture().setPosition(x, y); // set position of picture

            x += 120;
            if(i % 10 == 9){ // next row 
                y -= 120;
                x = 160;
            }
        }
    }
    
    public void clickButton(Vector2f mousePosition){
        
        System.out.println("clickButton in inventory");
        
        Point point = new Point((int)mousePosition.x, (int)mousePosition.y);
        
        if(sortButton.getHitBox().contains(point)){ // sort button
             sort();
             update();
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
        
        
        
        System.out.println(app.getInputManager().getCursorPosition());
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
        itemArray = bubbleSort(itemArray);
        //Arrays.sort(itemArray, 0, size);
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
    
    public int getSize(){
        return size;
    }
    
    private Item[] bubbleSort(Item[] itemArray) {
        boolean changed;
        Item temp;
        do{
            changed = false;
            for(int i = 0 ; i < size-1 ; i ++){
                if(itemArray[i].compareTo(itemArray[i + 1]) >= 1){
                    temp = itemArray[i];
                    itemArray[i] = itemArray[i + 1];
                    itemArray[i + 1] = temp;
                    changed = true;
                }
            }
        }while(changed);
        return itemArray;
    }

}
