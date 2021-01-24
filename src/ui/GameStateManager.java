/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Vector2f;
import mygame.state.GameState;
import mygame.state.InventoryState;
import mygame.state.MenuState;
import mygame.state.SettingState;

/**
 *
 * @author leoze
 */
public class GameStateManager implements ActionListener{
    
    private Application app;
    private AppStateManager stateManager;
    private Inventory inventory;
    
    public GameStateManager(SimpleApplication app, AppStateManager stateManager, Inventory inventory){
        this.app = app;
        this.stateManager = stateManager;
        this.inventory = inventory;
        setKeys();
    }
    
    private void setKeys(){
        app.getInputManager().addMapping("Inventory", new KeyTrigger(KeyInput.KEY_I));
//        app.getInputManager().addListener(this, "Inventory");
        
        app.getInputManager().addMapping("Setting", new KeyTrigger(KeyInput.KEY_ESCAPE));
//        app.getInputManager().addListener(this, "Setting");
    }
    
    @Override
    public void onAction(String binding, boolean isPressed, float tpf) {
        if (binding.equals("Inventory") && isPressed) {
            stateManager.getState(GameState.class).openInventory();
            
        }
        if (binding.equals("Setting") && isPressed) {
            stateManager.getState(GameState.class).openSetting();
        }
    }
    
    private Vector2f getMousePosition(){
        return app.getInputManager().getCursorPosition();
    }
    
    
}
