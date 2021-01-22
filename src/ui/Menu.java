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
public class Menu {
    
    private SimpleApplication app;
    private AppStateManager stateManager;
    
    private Button playButton;
    private Button settingButton;
    private Button exitButton;
    
    public Menu(SimpleApplication app, AppStateManager stateManager){
        this.app = app;
        this.stateManager = stateManager;
        init();
    }
    
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
    
    private void display(){
        
    }
    
    public void clickButton(Vector2f mousePosition){
        
        Point point = new Point((int)mousePosition.x, (int)mousePosition.y);

        
        if(playButton.getHitBox().contains(point)){ // play button
            
            stateManager.getState(MenuState.class).exitState(); // exit menuState
            stateManager.getState(MenuState.class).cleanUp();
            stateManager.getState(MenuState.class).removeListener();
            stateManager.getState(GameState.class).enterState(); // enter gameState
            stateManager.getState(GameState.class).init();
            stateManager.getState(GameState.class).addListener();
            app.getInputManager().setCursorVisible(false);
        }
        if(settingButton.getHitBox().contains(point)){ // setting button
            stateManager.getState(MenuState.class).exitState(); // exit menuState
            stateManager.getState(MenuState.class).cleanUp();
            stateManager.getState(MenuState.class).removeListener();
            stateManager.getState(SettingState.class).enterState(); // enter gameState
            stateManager.getState(SettingState.class).init();
            stateManager.getState(SettingState.class).addListener();
        }
        
        if(exitButton.getHitBox().contains(point)){ // exit button 
            app.stop(); // exit program 
        }
    }
    
    public void update(){
        
    }
}
