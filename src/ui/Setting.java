package ui;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.Vector2f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.system.AppSettings;
import com.jme3.ui.Picture;
import java.awt.Point;
import java.awt.Rectangle;
import mygame.state.GameState;
import static mygame.state.Main.SCREENHEIGHT;
import static mygame.state.Main.SCREENWIDTH;
import mygame.state.MenuState;
import mygame.state.SettingState;

/**
 * Setting.java
 * setting screen of game
 * @author Leo Zeng
 * 2021/01/09
 */
public class Setting {
    
    private SimpleApplication app;
    private AppStateManager stateManager;
    
    private Button menuButton;
    private Button gameButton;
    
    private Button changeButton;
    private Button thirtyButton;
    private Button sixtyButton;
    private Button unlimitedButton;
    
    /**
     * Setting 
     * constructor 
     * @param app application 
     * @param stateManager controls states 
     */
    public Setting(SimpleApplication app, AppStateManager stateManager){
        this.app = app;
        this.stateManager = stateManager;
        init();
    }
    
    /**
     * init
     * initialise background 
     * all buttons 
     */
    private void init(){
        
        Picture settingBackground = new Picture("settingBackground");
        settingBackground.setImage(app.getAssetManager(), "UI/setting background.png", true);
        settingBackground.setWidth(SCREENWIDTH);
        settingBackground.setHeight(SCREENHEIGHT);
        settingBackground.setPosition(0, 0);

        app.getGuiNode().attachChild(settingBackground);
        settingBackground.setQueueBucket(RenderQueue.Bucket.Gui);
        
        menuButton = new Button(app, new Rectangle(50, 650, 320, 100), "UI/return to menu.png" );
        gameButton = new Button(app, new Rectangle(50, 500, 320, 100), "UI/return to game.png");
        changeButton = new Button(app, new Rectangle(50, 350, 320, 100), "UI/frame rate.png");
        thirtyButton = new Button(app, new Rectangle(400, 350, 320, 100), "UI/30.png");
        sixtyButton = new Button(app, new Rectangle(750, 350, 320, 100), "UI/60.png");
        unlimitedButton = new Button(app, new Rectangle(1100, 350, 320, 100), "UI/unlimited.png");
        
    }

    /**
     * clickButton
     * check if buttons are clicked 
     * @param mousePosition current mouse position
     */
    public void clickButton(Vector2f mousePosition){
        
        Point point = new Point((int)mousePosition.x, (int)mousePosition.y);
        
        if(thirtyButton.getHitBox().contains(point)){
            
            AppSettings appSettings = new AppSettings(true);   
            appSettings.setFrameRate(30);
            appSettings.put("Width",SCREENWIDTH);
            appSettings.put("Height",SCREENHEIGHT);
            appSettings.put("Title", "ChefBoy");  
            app.setSettings(appSettings);   
            app.restart();
            
        } else if(sixtyButton.getHitBox().contains(point)){
            
            AppSettings appSettings = new AppSettings(true);   
            appSettings.setFrameRate(60);
            appSettings.put("Width",SCREENWIDTH);
            appSettings.put("Height",SCREENHEIGHT);
            appSettings.put("Title", "ChefBoy");  
            app.setSettings(appSettings);   
            app.restart();
            
        } else if(unlimitedButton.getHitBox().contains(point)){
            
            AppSettings appSettings = new AppSettings(true);   
            appSettings.setFrameRate(-1);
            appSettings.put("Width",SCREENWIDTH);
            appSettings.put("Height",SCREENHEIGHT);
            appSettings.put("Title", "ChefBoy");  
            app.setSettings(appSettings);   
            app.restart();
            
        } else if(menuButton.getHitBox().contains(point)){ // return to menu button
            
            stateManager.getState(SettingState.class).exitState(); // exit settingState
            stateManager.getState(SettingState.class).cleanUp();
            stateManager.getState(SettingState.class).removeListener();
            stateManager.getState(MenuState.class).enterState(); // enter menuState
            stateManager.getState(MenuState.class).init(); 
            stateManager.getState(MenuState.class).addListener(); 
            
        } else if(gameButton.getHitBox().contains(point)){ // return to game button 
            
            stateManager.getState(SettingState.class).exitState(); // exit settingState
            stateManager.getState(SettingState.class).cleanUp();
            stateManager.getState(SettingState.class).removeListener();
            stateManager.getState(GameState.class).enterState(); // enter gameState
            stateManager.getState(GameState.class).addListener();
            if( !stateManager.getState(GameState.class).getInit()){
                stateManager.getState(GameState.class).init();
            }
            app.getInputManager().setCursorVisible(false);
        }
        
    }
}
