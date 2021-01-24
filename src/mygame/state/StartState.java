package mygame.state;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.ui.Picture;
import static mygame.state.Main.SCREENHEIGHT;
import static mygame.state.Main.SCREENWIDTH;

/**
 * StartState.java
 * shows starting cut scene
 * @author LeoZeng
 * 2020/01/12
 */
public class StartState extends State{
    
    private SimpleApplication app;
    private AppStateManager stateManager;
    
    private float timer; // length of scene 
    private boolean inited = false; // only need to show scene once 
    
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
     * update 
     * update timer and start game
     * @param tpf time per frame
     */
    @Override
    public void update(float tpf) {

        timer -= tpf;
        
        if(timer <= 0){
            startGame();
        }
    }


    /**
     * startGame
     * exit from startState and enter gameState 
     */
    void startGame(){
        stateManager.getState(StartState.class).exitState(); // exit startState
        stateManager.getState(StartState.class).cleanUp();
        stateManager.getState(GameState.class).enterState(); // enter gameState
        stateManager.getState(GameState.class).init();
        stateManager.getState(GameState.class).addListener();
        app.getInputManager().setCursorVisible(false);
    }

    /**
     * cleanUp
     * remove graphics from node when done 
     */
    @Override
    public void cleanUp() {
        app.getGuiNode().detachChildNamed("start");
    }

    /**
     * init
     * initialise start cut scene picture 
     */
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
