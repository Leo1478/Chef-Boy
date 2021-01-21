/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author leoze
 */
public class Setting {
    
    private SimpleApplication app;
    private AppStateManager stateManager;
    
    private Button menuButton;
    private Button gameButton;
    
    public Setting(SimpleApplication app, AppStateManager stateManager){
        this.app = app;
        this.stateManager = stateManager;
        init();
    }
    
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
        
    }
    
    private void display(){
        
    }
    
    public void clickButton(Vector2f mousePosition){
        
        Point point = new Point((int)mousePosition.x, (int)mousePosition.y);

        
        if(menuButton.getHitBox().contains(point)){ // play button
            
            stateManager.getState(SettingState.class).exitState(); // exit settingState
            stateManager.getState(SettingState.class).cleanUp();
            stateManager.getState(GameState.class).cleanUp(); // clean up gameState
            stateManager.getState(MenuState.class).enterState(); // enter menuState
            stateManager.getState(MenuState.class).init(); // enter menuState
            
        }
        
        if(gameButton.getHitBox().contains(point)){ // exit button 
            stateManager.getState(SettingState.class).exitState(); // exit settingState
            stateManager.getState(SettingState.class).cleanUp();
            stateManager.getState(GameState.class).enterState(); // enter gameState
            app.getInputManager().setCursorVisible(false);
        }
    }
    
    public void update(){
        
    }
}
