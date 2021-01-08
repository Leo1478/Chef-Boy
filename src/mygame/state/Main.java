package mygame.state;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppState;
import com.jme3.bullet.BulletAppState;
import com.jme3.font.BitmapFont;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.shadow.DirectionalLightShadowRenderer;
import com.jme3.system.AppSettings;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author leoze
 */
public class Main extends SimpleApplication {
    
    
    public GameState gameState;
    public MenuState menuState;
    
    public AppSettings settings1 = settings;

    
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
        appSettings.put("Width",1920);      
        appSettings.put("Height",1080);       
        appSettings.put("Title", "ChefBoy");  
        app.setSettings(appSettings);   
        app.start();  
        
        
    }

    /**
     * create states
     */
    @Override
    public void simpleInitApp() {
        
        //menuState = new MenuState();
        //stateManager.attach((AppState) menuState);

        gameState = new GameState();
        stateManager.attach((AppState) gameState); 
        
        //gameState.setEnabled(false);

        
        
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
    
    
}


