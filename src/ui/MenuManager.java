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
public class MenuManager implements ActionListener{

    private Application app;
    private Menu menu;
    
    public MenuManager(Application app, Menu menu){
        this.app = app;
        this.menu = menu;
        setKeys();
    }
    
    private void setKeys(){
        app.getInputManager().addMapping("mouseLeft", new MouseButtonTrigger(0));
        app.getInputManager().addListener(this, "mouseLeft");
    }
    
    @Override
    public void onAction(String binding, boolean isPressed, float tpf) {
        if (binding.equals("mouseLeft") && isPressed) {
            menu.clickButton(getMousePosition());
            System.out.println("thing from MenuManger");
        }
    }
    
    private Vector2f getMousePosition(){
        return app.getInputManager().getCursorPosition();
    }
}
