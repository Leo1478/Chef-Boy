/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.state;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import java.awt.Rectangle;
import ui.Button;

/**
 * game over state 
 * @author leoze
 */
public class EndState extends AbstractAppState{
    
    private SimpleApplication app;
    private Button menuButton = new Button(app, new Rectangle(322, 240, 600, 600), "UI/test.png" );
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        
        this.app = (SimpleApplication) app;

    }


    @Override
    public void update(float tpf) {


        
    }

    void display() {

    }
    public void enterState(){
        setEnabled(true);
    }
    

    public void exitState() {
        setEnabled(false);
    }
}
