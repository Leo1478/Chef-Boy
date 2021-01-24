package ui;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Vector2f;
import mygame.state.InventoryState;
import mygame.state.SettingState;

/**
 * InventoryManager.java
 * manages inputs for inventory 
 * @author Ariana Hou
 */
public class InventoryManager implements ActionListener {
    
    private Application app;
    private AppStateManager stateManager;
    private Inventory inventory;
    
    /**
     * InventoryManager
     * constructor init inventory and set keys 
     * @param app application 
     * @param stateManager controls state 
     * @param inventory inventory of user 
     */
    public InventoryManager(SimpleApplication app, AppStateManager stateManager, Inventory inventory){
        this.app = app;
        this.stateManager = stateManager;
        this.inventory = inventory;
        setKeys();
    }
    
    /**
     * setKeys 
     * set mouse and esc input 
     */
    private void setKeys(){
        app.getInputManager().addMapping("mouseLeft", new MouseButtonTrigger(0));
        app.getInputManager().addMapping("Setting", new KeyTrigger(KeyInput.KEY_ESCAPE));
        
    }

    /**
     * onAction
     * method from ActionListener 
     * if key is pressed, change to true
     * @param binding key binding 
     * @param isPressed if key is pressed 
     * @param tpf time per frame
     */
    @Override
    public void onAction(String binding, boolean isPressed, float tpf) {
        if (binding.equals("mouseLeft") && isPressed) {
            inventory.clickButton(getMousePosition());
            System.out.println("hi");
        }
        if (binding.equals("Setting") && isPressed) {
            stateManager.getState(SettingState.class).enterState();
            stateManager.getState(SettingState.class).init();
            stateManager.getState(SettingState.class).addListener();
            stateManager.getState(InventoryState.class).exitState();
            stateManager.getState(InventoryState.class).removeListener();
            stateManager.getState(InventoryState.class).cleanUp();

        }
    }

    /**
     * getMousePosition
     * @return the mouse position 
     */
    private Vector2f getMousePosition() {
        return app.getInputManager().getCursorPosition();
    }
}
