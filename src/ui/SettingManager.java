package ui;

import com.jme3.app.Application;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Vector2f;

/**
 * SettingManager
 * inputs for setting screen 
 * @author Leo Zeng
 * 2021/01/16
 */
public class SettingManager implements ActionListener{

    private Application app;
    private Setting setting;
    
    /**
     * SettingManager
     * constructor set keys 
     * @param app application 
     * @param setting setting screen 
     */
    public SettingManager(Application app, Setting setting){
        this.app = app;
        this.setting = setting;
        setKeys();
    }
    
    /**
     * setKeys
     * add mouseLeft input
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
            setting.clickButton(getMousePosition());
        }
    }
    
    /**
     * getMousePosition
     * @return current mouse position
     */
    private Vector2f getMousePosition(){
        return app.getInputManager().getCursorPosition();
    }
    
}
