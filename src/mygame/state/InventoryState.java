/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.state;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import java.awt.Rectangle;
import ui.Button;
import ui.Inventory;
import ui.InventoryManager;

/**
 *
 * @author leoze
 */
public class InventoryState extends State{
    
    private SimpleApplication app;
    private Inventory inventory;
    private InventoryManager inventoryManager;
    
    private Button gameButton;
    
    public InventoryState(){

    }
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        this.app = (SimpleApplication) app;
        this.stateManager = stateManager;

    }

    public void init(Inventory inventory){
        app.getInputManager().setCursorVisible(true);
        this.inventory = inventory;
        this.inventory.init();
        this.inventoryManager = new InventoryManager(app, stateManager, inventory);
        
    }
    
    @Override
    public void update(float tpf){
        inventory.update();
        System.out.println("in inventoryState");
    }

    @Override
    public void cleanUp() {
        app.getGuiNode().detachChildNamed("inventoryBackground");
        app.getGuiNode().detachChildNamed("button");
        app.getGuiNode().detachChildNamed("button");
        app.getInputManager().removeListener(inventoryManager);
        
        inventory = null;
        inventoryManager = null;
    }

    @Override
    public void init() {
        
    }
    
    public void addListener(){
        app.getInputManager().addListener(inventoryManager, "mouseLeft");
        app.getInputManager().addListener(inventoryManager, "Setting");
    }
    
    public void removeListener(){
        app.getInputManager().removeListener(inventoryManager);
    }
}
