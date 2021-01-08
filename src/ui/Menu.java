/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.jme3.app.SimpleApplication;
import java.awt.Rectangle;

/**
 *
 * @author leoze
 */
public class Menu {
    
    private SimpleApplication app;
    
    private Button startButton;
    private Button settingButton;
    
    public Menu(SimpleApplication app){
        this.app = app;
        init();
    }
    
    private void init(){
        startButton = new Button(app, new Rectangle(322, 240, 600, 600), "UI/test.png" );
    }
    
    private void display(){
        
    }
    
    public void update(){
        
    }
    
}
