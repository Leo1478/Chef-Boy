package mygame.state;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import ui.Setting;
import ui.SettingManager;

/**
 * SettingState.java
 * state for in setting screen
 * @author Leo Zeng
 * 2021/01/12
 */
public class SettingState extends State {
    
    private AppStateManager stateManager;
    
    private SimpleApplication app;
    
    private SettingManager settingManager;
    private Setting setting;
    
    /**
     * initialize 
     * called when state is attatched to StateManager
     * @param stateManager engine StateManager, controls states 
     * @param app application 
     */    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        this.app = (SimpleApplication) app;
        this.stateManager = stateManager;
        
    }
    
    /**
     * init
     * initialise setting and setting manager
     */
    @Override
    public void init(){
        
        app.getInputManager().setCursorVisible(true);

        setting = new Setting(app, stateManager);
        settingManager = new SettingManager(app, setting);
    }
    
    /**
     * cleanUp
     * remove all graphics when finished 
     * remove listener 
     */
    @Override
    public void cleanUp(){
        
        app.getGuiNode().detachChildNamed("settingBackground");
        app.getGuiNode().detachChildNamed("button");
        app.getGuiNode().detachChildNamed("button");
        app.getGuiNode().detachChildNamed("button");
        app.getGuiNode().detachChildNamed("button");
        app.getGuiNode().detachChildNamed("button");
        app.getGuiNode().detachChildNamed("button");
        
        app.getInputManager().removeListener(settingManager);
        
        settingManager = null;
        setting = null;
        
    }
 
    /**
     * addListener
     * add mouseLeft input
     */
    public void addListener(){
        app.getInputManager().addListener(settingManager, "mouseLeft");
    }
    
    /**
     * removeListener
     * remove listener when finished
     */
    public void removeListener(){
        app.getInputManager().removeListener(settingManager);
    }
}
