/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.state;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.system.AppSettings;
import com.jme3.ui.Picture;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import mygame.gameobject.Button;

/**
 *
 * @author leoze
 */
public class MenuState extends BaseAppState {


    
    private SimpleApplication app;
    
    private Button startButton = new Button(app, new Rectangle(322, 240, 600, 600), "UI/test.png" );
    
    @Override
    protected void initialize(Application app) {
        this.app = (SimpleApplication) app;

        app.getInputManager().setCursorVisible(true);
        app.getViewPort().setBackgroundColor(ColorRGBA.Red);

        //main.setDisplayStatView(false); 
        //main.setDisplayFps(false);
        

    }


    @Override
    public void update(float tpf) {

        Vector2f click2d = app.getInputManager().getCursorPosition();
        System.out.println(click2d.toString());
        
        Point point = new Point((int)click2d.x, (int)click2d.y);
        
        if(startButton.getHitBox().contains(point)){
            System.out.println("Mouse is in button");
        }
        
    }

    void display() {

    }

    public void exitState() {
        setEnabled(false);
        //main.gameState.setEnabled(true);
    }



    @Override
    protected void cleanup(Application app) {
    }

    @Override
    protected void onEnable() {
        System.out.println("opening menu");
    }

    @Override
    protected void onDisable() {
    }

    

}
