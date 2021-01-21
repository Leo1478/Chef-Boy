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
import ui.Setting;
import ui.SettingManager;

/**
 *
 * @author leoze
 */
public class SettingState extends State {
    
    private AppStateManager stateManager;
    
    private SimpleApplication app;
    private Button startButton;
    
    private SettingManager settingManager;
    private Setting setting;
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        this.app = (SimpleApplication) app;
        this.stateManager = stateManager;
        
    }
    
    @Override
    public void init(){
        
        app.getInputManager().setCursorVisible(true);

        setting = new Setting(app, stateManager);
        settingManager = new SettingManager(app, setting);
    }
    
    /**
     *  remove all menu elements from guiNode
     */
    @Override
    public void cleanUp(){
        
        app.getGuiNode().detachChildNamed("settingBackground");
        app.getGuiNode().detachChildNamed("button");
        app.getGuiNode().detachChildNamed("button");
        
        app.getInputManager().removeListener(settingManager);
        
    }

    @Override
    public void update(float tpf) {

    }
}
