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
        appSettings.put("Width",1500);      //1920
        appSettings.put("Height",800);    //1080   
        appSettings.put("Title", "ChefBoy");  
        app.setSettings(appSettings);   
        app.start();  
        
    }

    /**
     * create states 
     */
    @Override
    public void simpleInitApp() {
        
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
        
        menuState.initialize(stateManager, this);  // init and start the menu
        menuState.enterState();
        
    }

    @Override
    public void simpleUpdate(float tpf) {
        
    }

    @Override
    public void simpleRender(RenderManager rm) {
        
    }
}


