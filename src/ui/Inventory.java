package ui;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.Vector2f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.system.AppSettings;
import com.jme3.ui.Picture;
import java.awt.Point;
import java.awt.Rectangle;
import mygame.gameobject.Item;
import mygame.gameobject.ItemPic;
import mygame.state.GameState;
import mygame.state.InventoryState;
import static mygame.state.Main.SCREENHEIGHT;
import static mygame.state.Main.SCREENWIDTH;

/**
 * Inventory.java
 * inventory to store and display items 
 * @author Ariana Hou
 * 2021/01/02
 */
public class Inventory {
    
    private SimpleApplication app;
    
    private Item[] itemArray; // array of items 
    private int size; // number of items 
    private Button sortButton;
    private Button gameButton;
    private AppStateManager stateManager;

    /**
     * Inventory 
     * constructor set array size 
     * @param app application 
     * @param stateManager controls states 
     */
    public Inventory(SimpleApplication app, AppStateManager stateManager){
        this.app = app;
        this.stateManager = stateManager;
        size = 0;
        itemArray = new Item[50];

    }
    
    /**
     * init
     * initialise pictures and buttons
     */
    public void init(){
        
       initBackground();
       initItemPicture();
       
       sortButton = new Button(app, new Rectangle(300, 50, 320, 100), "UI/sort.png");
       gameButton = new Button(app, new Rectangle(880, 50, 320, 100), "UI/return to game black.png");
    }
    
    /**
     * initBackground
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
     * initItemPicture
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
    
    /**
     * setItemPosition
     * set item position in a grid 
     * 5 * 10 grid 
     */
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
    
    /**
     * clickButton
     * check if button is clicked 
     * sort or return to game 
     * @param mousePosition position of mouse 
     */
    public void clickButton(Vector2f mousePosition){

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
    
    /**
     * update 
     * update pucture position 
     */
    public void update(){
        setPicturePosition();
    }
    
    /**
     * add
     * add item to array 
     * @param item item to add 
     */
    public void add(Item item){
        if(size < 50){
            itemArray[size] = item;
            size ++;
        }
    }
    
    /**
     * remove 
     * remove item from array
     * @param index index to remove 
     */
    public void remove(int index){
        itemArray[index] = null;
    }
    
    /**
     * sort
     * call bubbleSort to sort items 
     */
    public void sort(){
        itemArray = bubbleSort(itemArray);
        //Arrays.sort(itemArray, 0, size);
    }
    
    /**
     * getSize 
     * @return the size
     */
    public int getSize(){
        return size;
    }
    
    /**
     * bubbleSort
     * bubble sort to compare adjcent items 
     * sort by class name 
     * @param itemArray array to sort
     * @return sorted array 
     */
    private Item[] bubbleSort(Item[] itemArray) {
        boolean changed; // if change has been made 
        Item temp;
        do{
            changed = false;
            for(int i = 0 ; i < size-1 ; i ++){ // loop through array 
                if(itemArray[i].compareTo(itemArray[i + 1]) >= 1){ // if i or i+1 should be swaped 
                    temp = itemArray[i]; // swap items 
                    itemArray[i] = itemArray[i + 1];
                    itemArray[i + 1] = temp;
                    changed = true;
                }
            }
        }while(changed);
        
        return itemArray;
    }
}
