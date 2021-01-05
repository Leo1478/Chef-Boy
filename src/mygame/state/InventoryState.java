/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.state;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import mygame.gameobject.Inventory;

/**
 *
 * @author leoze
 */
public class InventoryState extends AbstractAppState{
    
    private Main main;
    private Inventory inventory;
    
    public InventoryState(Main main, Inventory inventory){

    }
    
    @Override
    public void initialize(AppStateManager stateManager, Application app){
        this.main = (Main) app;
        this.inventory = main.gameState.getInventory();
    }
    
}
