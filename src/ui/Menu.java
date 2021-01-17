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

/**
 *
 * @author leoze
 */
public class Menu {
    
    private SimpleApplication app;
    private AppStateManager stateManager;
    
    private Button startButton;
    private Button settingButton;
    
    public Menu(SimpleApplication app, AppStateManager stateManager){
        this.app = app;
        this.stateManager = stateManager;
        init();
    }
    
    private void init(){
        
        
        AppSettings settings = new AppSettings(true);
        
        Picture menuBackground = new Picture("menuBackground");
        menuBackground.setImage(app.getAssetManager(), "UI/menu background.png", true);
        menuBackground.setWidth(SCREENWIDTH);
        menuBackground.setHeight(SCREENHEIGHT);
        menuBackground.setPosition(0, 0);

        app.getGuiNode().attachChild(menuBackground);
        menuBackground.setQueueBucket(RenderQueue.Bucket.Gui);
        
        startButton = new Button(app, new Rectangle(322, 240, 600, 600), "UI/start button.png" );
        
        
        
    }
    
    private void display(){
        
    }
    
    public void clickButton(Vector2f mousePosition){
        
        Point point = new Point((int)mousePosition.x, (int)mousePosition.y);

        
        if(startButton.getHitBox().contains(point)){

            stateManager.getState(MenuState.class).exitState();
            stateManager.getState(MenuState.class).cleanUp();
            stateManager.getState(GameState.class).enterState();
        }
    }
    
    public void update(){
        
    }
    

}
