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
        
        //inventoryManager = new InventoryManager(app, inventory);
    }
    
    @Override
    public void update(float tpf){
        inventory.update();
    }

    @Override
    public void cleanUp() {
        app.getGuiNode().detachChildNamed("inventoryBackground");
        app.getInputManager().removeListener(inventoryManager);
        
        inventory = null;
        inventoryManager = null;
    }

    @Override
    public void init() {
        
    }
}
