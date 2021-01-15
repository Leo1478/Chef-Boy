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

/**
 *
 * @author leoze
 */
public class InventoryState extends State{
    
    private SimpleApplication app;
    private Inventory inventory;
    
    public InventoryState(){

    }
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        this.app = (SimpleApplication) app;
        this.stateManager = stateManager;

        app.getInputManager().setCursorVisible(true);
    }

    public void init(Inventory inventory){
        this.inventory = inventory;
        inventory.initPicture();
        //inventoryManager = new InventoryManager(app, inventory);
    }
    
    @Override
    public void update(float tpf){
        
    }

    @Override
    public void cleanUp() {
        app.getGuiNode().detachChildNamed("inventoryBackground");
    }

    public void enterState(Inventory inventory){
        super.enterState();
        init(inventory);
    }
    
}
