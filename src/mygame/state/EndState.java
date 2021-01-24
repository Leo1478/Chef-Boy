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
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.ui.Picture;
import java.awt.Rectangle;
import static mygame.state.Main.SCREENHEIGHT;
import static mygame.state.Main.SCREENWIDTH;
import ui.Button;

/**
 * game over state 
 * @author leoze
 */
public class EndState extends State{
    
    private SimpleApplication app;
    private float timer;
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        
        this.app = (SimpleApplication) app;

    }
    
    public void init(boolean win){
        
        timer = 5;
        
        if(win){
            
            Picture winPicture = new Picture("win");
            winPicture.setImage(app.getAssetManager(), "UI/win.png", true);
            winPicture.setWidth(SCREENWIDTH);
            winPicture.setHeight(SCREENHEIGHT);
            winPicture.setPosition(0, 0);

            app.getGuiNode().attachChild(winPicture);
            winPicture.setQueueBucket(RenderQueue.Bucket.Gui);
        }
        else{
            
            Picture losePicture = new Picture("lose");
            losePicture.setImage(app.getAssetManager(), "UI/lose.png", true);
            losePicture.setWidth(SCREENWIDTH);
            losePicture.setHeight(SCREENHEIGHT);
            losePicture.setPosition(0, 0);

            app.getGuiNode().attachChild(losePicture);
            losePicture.setQueueBucket(RenderQueue.Bucket.Gui);
        }
    }


    @Override
    public void update(float tpf) {
        
        timer -= tpf;
        
        if(timer <= 0){
            app.stop(); // exit program 
        }
    }


    @Override
    public void cleanUp() {
        app.getGuiNode().detachChildNamed("win");
        app.getGuiNode().detachChildNamed("lose");
    }

    @Override
    public void init() {
        
    }

}
