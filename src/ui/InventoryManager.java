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
import java.util.ArrayList;
import mygame.state.GameState;
import mygame.state.InventoryState;
import mygame.state.SettingState;

/**
 *
 * @author leoze
 */
public class InventoryManager implements ActionListener {
    
    private Application app;
    private AppStateManager stateManager;
    private Inventory inventory;
    
    public InventoryManager(SimpleApplication app, AppStateManager stateManager, Inventory inventory){
        this.app = app;
        this.stateManager = stateManager;
        this.inventory = inventory;
        setKeys();
    }
    

    private void setKeys(){
        app.getInputManager().addMapping("mouseLeft", new MouseButtonTrigger(0));
//        app.getInputManager().addListener(this, "click");
        
        app.getInputManager().addMapping("Setting", new KeyTrigger(KeyInput.KEY_ESCAPE));
//        app.getInputManager().addListener(this, "Setting");
        
    }

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
    
    private Vector2f getMousePosotion(){
        return app.getInputManager().getCursorPosition();
    }
    
    public void update(){

    }

    private Vector2f getMousePosition() {
        return app.getInputManager().getCursorPosition();
    }
}
