package ui;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector2f;
import mygame.state.GameState;

/**
 * GameStateManager
 * takes inputs for GameState
 * @author Leo Zeng
 * 2020/01/06
 */
public class GameStateManager implements ActionListener{
    
    private Application app;
    private AppStateManager stateManager;
    private Inventory inventory;
    
    /**
     * GameStateManager
     * constructor, init inventory and set keys 
     * @param app
     * @param stateManager
     * @param inventory 
     */
    public GameStateManager(SimpleApplication app, AppStateManager stateManager, Inventory inventory){
        this.app = app;
        this.stateManager = stateManager;
        this.inventory = inventory;
        setKeys();
    }
    
    /**
     * setKeys
     * set key input for GameState
     */
    private void setKeys(){
        app.getInputManager().addMapping("Inventory", new KeyTrigger(KeyInput.KEY_I));
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
        if (binding.equals("Inventory") && isPressed) {
            stateManager.getState(GameState.class).openInventory();   
        }
        if (binding.equals("Setting") && isPressed) {
            stateManager.getState(GameState.class).openSetting();
        }
    }
    
    /**
     * getMousePosition
     * @return the mouse position 
     */
    private Vector2f getMousePosition(){
        return app.getInputManager().getCursorPosition();
    }
}
