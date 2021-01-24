/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.jme3.app.Application;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Vector2f;
import java.awt.Point;

/**
 *
 * @author leoze
 */
public class SettingManager implements ActionListener{

    private Application app;
    private Setting setting;
    
    public SettingManager(Application app, Setting setting){
        this.app = app;
        this.setting = setting;
        setKeys();
    }
    
    private void setKeys(){
        app.getInputManager().addMapping("mouseLeft", new MouseButtonTrigger(0));
//        app.getInputManager().addListener(this, "mouseLeft");
        
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
    
    private Vector2f getMousePosition(){
        return app.getInputManager().getCursorPosition();
    }
    
}
