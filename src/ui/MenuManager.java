package ui;

import com.jme3.app.Application;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Vector2f;

/**
 * MenuManager.java
 * get inputs for menu
 * @author Leo Zeng
 * 202101/04
 */
public class MenuManager implements ActionListener{

    private Application app;
    private Menu menu;
    
    /**
     * MenuManager
     * constructor init menu, set keys
     * @param app application
     * @param menu menu screen 
     */
    public MenuManager(Application app, Menu menu){
        this.app = app;
        this.menu = menu;
        setKeys();
    }
    
    /**
     * setKeys 
     * mouseLeft input 
     */
    private void setKeys(){
        app.getInputManager().addMapping("mouseLeft", new MouseButtonTrigger(0));
    }
    
    /**
     * onAction
     * method from ActionListener 
     * if key is pressed, change to true
     * @param binding key binding 
     * @param isPressed if key is pressed 
     * @param tpf time per frame
     */
    @Override
    public void onAction(String binding, boolean isPressed, float tpf) {
        
        if (binding.equals("mouseLeft") && isPressed) {
            menu.clickButton(getMousePosition());
        }
    }
    
    /**
     * getMousePosition
     * @return current ousePosition
     */
    private Vector2f getMousePosition(){
        return app.getInputManager().getCursorPosition();
    }
}
