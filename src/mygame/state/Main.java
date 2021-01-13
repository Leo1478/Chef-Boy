package mygame.state;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppState;
import com.jme3.renderer.RenderManager;
import com.jme3.system.AppSettings;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author leoze
 */
public class Main extends SimpleApplication {
    
    
    public GameState gameState;
    public MenuState menuState;

    
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
        
        menuState = new MenuState();
        stateManager.attach((AppState) menuState);

        gameState = new GameState();
        stateManager.attach((AppState) gameState); 
        
        menuState.setEnabled(true);
        gameState.setEnabled(paused);
        


    }

    @Override
    public void simpleUpdate(float tpf) {
        
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
    
    
}


