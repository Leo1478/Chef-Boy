package mygame.state;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import ui.Menu;
import ui.MenuManager;
import com.jme3.audio.AudioNode;
import com.jme3.audio.AudioData;

/**
 * MenuState.java
 * state for in menu
 * @author Leo Zeng
 * 2020/01/12
 */
public class MenuState extends State {
    
    private AppStateManager stateManager;
    
    private SimpleApplication app;
    
    private MenuManager menuManager;
    private Menu menu;
    private AudioNode bgm;
    
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

        app.getInputManager().setCursorVisible(true);
        
    }
    
    /**
     * init
     * initialise menu and menuManager
     */
    @Override
    public void init(){
        
        app.getInputManager().setCursorVisible(true);
        menu = new Menu(app, stateManager);
        menuManager = new MenuManager(app, menu);
        playBackgroundMusic();
    }
    
    /**
     * cleanUp
     * remove all menu elements from guiNode when finished 
     * remove listener 
     */
    @Override
    public void cleanUp(){
        
        app.getGuiNode().detachChildNamed("menuBackground");
        app.getGuiNode().detachChildNamed("button"); // delete the 3 buttons 
        app.getGuiNode().detachChildNamed("button");
        app.getGuiNode().detachChildNamed("button");
        
        app.getInputManager().removeListener(menuManager);

        menu = null;
        menuManager = null;
        bgm.stop();
        
    }

    /**
     * update
     * @param tpf time per frame
     */
    @Override
    public void update(float tpf) {
        
    }
    
    /**
     * addListener
     * add mouseLeft input
     */
    public void addListener(){
        app.getInputManager().addListener(menuManager, "mouseLeft");
    }
    
    /**
     * removeListener
     * remove listener when finished 
     */ 
    public void removeListener(){
        app.getInputManager().removeListener(menuManager);
    }
    
    /**
     * playBackgroundMusic
     * play music 
     */
    public void playBackgroundMusic(){
        bgm = new AudioNode(app.getAssetManager(), "Sounds/MenuBGM.ogg",AudioData.DataType.Buffer);
        bgm.setDirectional(false);
        bgm.setPositional(false);
        bgm.play();
        bgm.setLooping(true);
        
    }
}
