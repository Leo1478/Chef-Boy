package mygame.state;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppState;
import com.jme3.renderer.RenderManager;
import com.jme3.system.AppSettings;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author leoze
 */
public class Main extends SimpleApplication {
    
    
    private GameState gameState;
    private MenuState menuState;
    private InventoryState inventoryState;
    
    public static final int SCREENWIDTH = 1500;
    public static final int SCREENHEIGHT = 800;
    
    /**
     * main method 
     * start program 
     * disable jmonkey start screen 
     * @param args 
     */
    public static void main(String[] args) {
        
        Main app = new Main();
        app.showSettings = false;            
        AppSettings appSettings = new AppSettings(true);   
        appSettings.put("Width",SCREENWIDTH);      //1920
        appSettings.put("Height",SCREENHEIGHT);    //1080   
        appSettings.put("Title", "ChefBoy");  
        app.setSettings(appSettings);   
        app.start();  
        
    }

    /**
     * create states 
     */
    @Override
    public void simpleInitApp() {
        

        getInputManager().setCursorVisible(true);
        
        setDisplayStatView(true); 
        setDisplayFps(true);
        
        menuState = new MenuState(); // create each state 
        stateManager.attach((AppState) menuState);

        gameState = new GameState();
        stateManager.attach((AppState) gameState);
        
        inventoryState = new InventoryState();
        stateManager.attach((AppState) inventoryState);
        
        menuState.setEnabled(false); // start all of the states on disabled 
        gameState.setEnabled(false);    
        inventoryState.setEnabled(false);
        
    }

    boolean menu = false;
    @Override
    public void simpleUpdate(float tpf) {
        if(! menu){
            // entering the menu has to be called here
            // if called from simpleInitApp(), the menu will be created before menuState
            // jmonkey initialize the states after simpleInitApp()
            menuState.enterState(); 
            menu = true;
        }
    }

    @Override
    public void simpleRender(RenderManager rm) {
        
    }
}


