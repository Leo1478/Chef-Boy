package mygame.state;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.ui.Picture;
import static mygame.state.Main.SCREENHEIGHT;
import static mygame.state.Main.SCREENWIDTH;

/**
 * EndState.java
 * game over state
 * shows end cut scene
 * @author Leo Zeng
 * 2021/01/21
 */
public class EndState extends State{
    
    private SimpleApplication app;
    private float timer; // length of cut scene 
    
    /**
     * initialize 
     * called when state is attatched to StateManager
     * @param stateManager engine StateManager, controls states 
     * @param app application 
     */
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        
        this.app = (SimpleApplication) app;

    }
    
    /**
     * init
     * init win / lose pictures based on condition 
     * @param win whether player win or lose
     */
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


    /**
     * update 
     * update timer 
     * @param tpf time per frame
     */
    @Override
    public void update(float tpf) {
        
        timer -= tpf;
        
        if(timer <= 0){
            app.stop(); // exit program 
        }
    }


    /**
     * cleanUp
     * clean up all graphics
     */
    @Override
    public void cleanUp() {
        app.getGuiNode().detachChildNamed("win");
        app.getGuiNode().detachChildNamed("lose");
    }

    /**
     * init
     */
    @Override
    public void init() {
        
    }

}
