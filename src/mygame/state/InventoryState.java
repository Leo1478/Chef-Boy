package mygame.state;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import ui.Inventory;
import ui.InventoryManager;

/**
 * InventoryState.java
 * state for in inventory 
 * @author Ariana Hou
 * 2021/01/14
 */
public class InventoryState extends State{
    
    private SimpleApplication app;
    private AppStateManager stateManager;
    private Inventory inventory;
    private InventoryManager inventoryManager;
    
    /**
     * initialize 
     * called when state is attatched to StateManager
     * @param stateManager engine StateManager, controls states 
     * @param app application 
     */
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        this.app = (SimpleApplication) app;
        this.stateManager = stateManager;

    }

    /**
     * init
     * initialise inventory and manager
     * @param inventory inventory from GameState
     */
    public void init(Inventory inventory){
        app.getInputManager().setCursorVisible(true);
        this.inventory = inventory;
        this.inventory.init();
        this.inventoryManager = new InventoryManager(app, stateManager, inventory);
        
    }
    
    /**
     * update 
     * update inventory state 
     * @param tpf time per frame
     */
    @Override
    public void update(float tpf){
        inventory.update();
    }

    /**
     * cleanUp
     * remove all graphucs and listener when finished 
     */
    @Override
    public void cleanUp() {
        app.getGuiNode().detachChildNamed("inventoryBackground");
        app.getGuiNode().detachChildNamed("grid");
        app.getGuiNode().detachChildNamed("button");
        app.getGuiNode().detachChildNamed("button");
        for(int i = 0; i < inventory.getSize(); i++){
            app.getGuiNode().detachChildNamed("item pic");
        }
        app.getInputManager().removeListener(inventoryManager);
        
        inventory = null;
        inventoryManager = null;
    }

    /**
     * init
     */
    @Override
    public void init() {
        
    }
    
    /**
     * addListener 
     * add mouse and esc input to inventory 
     */
    public void addListener(){
        app.getInputManager().addListener(inventoryManager, "mouseLeft");
        app.getInputManager().addListener(inventoryManager, "Setting");
    }
    
    /**
     * removeListener
     * remove listener when finished 
     */
    public void removeListener(){
        app.getInputManager().removeListener(inventoryManager);
    }
}
