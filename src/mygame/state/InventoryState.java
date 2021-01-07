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
    
    private Application app;
    private Inventory inventory;
    
    public InventoryState(Application app, Inventory inventory){

    }
    
    @Override
    public void initialize(AppStateManager stateManager, Application app){
        this.app = (Application) app;
        
    }
    
}
