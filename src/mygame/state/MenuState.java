/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.state;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import ui.Button;
import ui.Menu;
import ui.MenuManager;

/**
 *
 * @author leoze
 */
public class MenuState extends State {
    
    AppStateManager stateManager;
    
    //private SimpleApplication app;
    Button startButton;
    
    private MenuManager menuManager;
    private Menu menu;
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        this.app = (SimpleApplication) app;
        this.stateManager = stateManager;

        app.getInputManager().setCursorVisible(true);
        //app.getViewPort().setBackgroundColor(ColorRGBA.Red);

        menu = new Menu(this.app, stateManager);
        menuManager = new MenuManager(this.app, menu);


    }
    
    /**
     *  remove all menu elements from guiNode
     */
    @Override
    public void cleanUp(){
        app.getGuiNode().detachChildNamed("menuBackground");
        app.getGuiNode().detachChildNamed("button");
    }


    @Override
    public void update(float tpf) {


    }
    


}
