/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.state;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.ui.Picture;
import java.awt.Point;
import java.awt.Rectangle;
import static mygame.state.Main.SCREENHEIGHT;
import static mygame.state.Main.SCREENWIDTH;
import ui.Button;

/**
 * shows starting animation / story 
 * @author leoze
 */
public class StartState extends State{
    
    private SimpleApplication app;
    private AppStateManager stateManager;
    
    private float timer;
    private boolean inited = false;
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        
        this.app = (SimpleApplication) app;
        this.stateManager = stateManager;

        
    }


    @Override
    public void update(float tpf) {

        timer -= tpf;
        
        if(timer <= 0){
            startGame();
        }
        
    }


    void startGame(){
        stateManager.getState(StartState.class).exitState(); // exit startState
        stateManager.getState(StartState.class).cleanUp();
        stateManager.getState(GameState.class).enterState(); // enter gameState
        stateManager.getState(GameState.class).init();
        stateManager.getState(GameState.class).addListener();
        app.getInputManager().setCursorVisible(false);
    }

    @Override
    public void cleanUp() {
        app.getGuiNode().detachChildNamed("start");
    }

    @Override
    public void init() {
        
        if(! inited){
            
            timer = 5;
        
            Picture start = new Picture("start");
            start.setImage(app.getAssetManager(), "UI/start.png", true);
            start.setWidth(SCREENWIDTH);
            start.setHeight(SCREENHEIGHT);
            start.setPosition(0, 0);

            app.getGuiNode().attachChild(start);
            start.setQueueBucket(RenderQueue.Bucket.Gui);
            
            inited = true;
        }
    }
    

}
