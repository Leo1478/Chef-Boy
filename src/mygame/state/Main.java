package mygame.state;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppState;
import com.jme3.renderer.RenderManager;
import com.jme3.system.AppSettings;

/**
 * Main.java
 * main class to start program 
 * creates all states, then enters MenuState
 * @author Leo Zeng
 * 2020/12/20
 */
public class Main extends SimpleApplication {
    
    
    private GameState gameState; // states 
    private MenuState menuState;
    private InventoryState inventoryState;
    private SettingState settingState;
    private StartState startState;
    private EndState endState;
    
    public static final int SCREENWIDTH = 1500;
    public static final int SCREENHEIGHT = 800;
    
    /**
     * main method 
     * start program 
     * disable jmonkey start screen 
     * @param args arguements 
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
     * simpleInitApp
     * create states, add to StateManager 
     */
    @Override
    public void simpleInitApp() {

        getInputManager().deleteMapping( SimpleApplication.INPUT_MAPPING_EXIT ); // unbind esc to exit 
        getInputManager().setCursorVisible(true); // make cursor visible to start
        
        setDisplayStatView(true); 
        setDisplayFps(true);
        
        menuState = new MenuState(); // create each state 
        stateManager.attach((AppState) menuState);

        gameState = new GameState();
        stateManager.attach((AppState) gameState);
        
        inventoryState = new InventoryState();
        stateManager.attach((AppState) inventoryState);
        
        settingState = new SettingState();
        stateManager.attach((AppState) settingState);
        
        startState = new StartState();
        stateManager.attach((AppState) startState);
        
        endState = new EndState();
        stateManager.attach((AppState) endState);
        
        menuState.setEnabled(false); // start all of the states on disabled 
        gameState.setEnabled(false);    
        inventoryState.setEnabled(false);
        settingState.setEnabled(false);
        startState.setEnabled(false);
        endState.setEnabled(false);
        
    }

    boolean menu = false;
    /**
     * simpleUpdate
     * update program 
     * enter MenuState
     * @param tpf time per frame
     */
    @Override
    public void simpleUpdate(float tpf) {
        if(! menu){
            // entering the menu has to be called here
            // if called from simpleInitApp(), the menu will be created before menuState
            // jmonkey initialize the states after simpleInitApp()
            menuState.enterState(); 
            menuState.init();
            menuState.addListener();
            menu = true;
            
            
        }
    }

    /**
     * SimpleRender
     * @param rm render manager
     */
    @Override
    public void simpleRender(RenderManager rm) {
        
    }
}


