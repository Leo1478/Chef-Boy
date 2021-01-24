package ui;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.Vector2f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.ui.Picture;
import java.awt.Point;
import java.awt.Rectangle;
import static mygame.state.Main.SCREENHEIGHT;
import static mygame.state.Main.SCREENWIDTH;
import mygame.state.MenuState;
import mygame.state.SettingState;
import mygame.state.StartState;

/**
 * Menu.java
 * menu screen of game 
 * contains play, setting, exit button
 * @author Leo Zeng
 */
public class Menu {
    
    private SimpleApplication app;
    private AppStateManager stateManager;
    
    private Button playButton;
    private Button settingButton;
    private Button exitButton;
    
    /**
     * Menu
     * constructor
     * @param app application 
     * @param stateManager controls states 
     */
    public Menu(SimpleApplication app, AppStateManager stateManager){
        this.app = app;
        this.stateManager = stateManager;
        init();
    }
    
    /**
     * init
     * initialise background and buttons 
     */
    private void init(){
        
        Picture menuBackground = new Picture("menuBackground");
        menuBackground.setImage(app.getAssetManager(), "UI/menu background.png", true);
        menuBackground.setWidth(SCREENWIDTH);
        menuBackground.setHeight(SCREENHEIGHT);
        menuBackground.setPosition(0, 0);

        app.getGuiNode().attachChild(menuBackground);
        menuBackground.setQueueBucket(RenderQueue.Bucket.Gui);
        
        playButton = new Button(app, new Rectangle(50, 650, 320, 100), "UI/play button.png" );
        settingButton = new Button(app, new Rectangle(50, 500, 320, 100), "UI/setting button.png");
        exitButton = new Button(app, new Rectangle(50, 350, 320, 100), "UI/exit button.png");
        
    }
    
    /**
     * clickButton
     * check if buttons are clicked 
     * @param mousePosition current mouse position 
     */
    public void clickButton(Vector2f mousePosition){
        
        Point point = new Point((int)mousePosition.x, (int)mousePosition.y);

        if(playButton.getHitBox().contains(point)){ // play button
            
            stateManager.getState(MenuState.class).exitState(); // exit menuState
            stateManager.getState(MenuState.class).cleanUp();
            stateManager.getState(MenuState.class).removeListener();
            stateManager.getState(StartState.class).enterState(); // enter startState
            stateManager.getState(StartState.class).init();

        }
        if(settingButton.getHitBox().contains(point)){ // setting button
            stateManager.getState(MenuState.class).exitState(); // exit menuState
            stateManager.getState(MenuState.class).cleanUp();
            stateManager.getState(MenuState.class).removeListener();
            stateManager.getState(SettingState.class).enterState(); // enter settingState
            stateManager.getState(SettingState.class).init();
            stateManager.getState(SettingState.class).addListener();
        }
        
        if(exitButton.getHitBox().contains(point)){ // exit button 
            app.stop(); // exit program 
        }
    }
}
