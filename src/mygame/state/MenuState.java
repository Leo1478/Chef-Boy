/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.state;

import com.jme3.app.Application;
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

/**
 *
 * @author leoze
 */
public class MenuState extends BaseAppState {

    private Main main;
    
    Rectangle startButtonBox;
    
    @Override
    protected void initialize(Application app) {
        this.main = (Main) app;

        main.getInputManager().setCursorVisible(true);

        main.getViewPort().setBackgroundColor(ColorRGBA.Red);

        //main.setDisplayStatView(false); 
        //main.setDisplayFps(false);
        
        startButtonBox = new Rectangle(322, 240, 600, 600);
        
        AppSettings settings = new AppSettings(true);

        Picture test = new Picture("test");
        test.setImage(main.getAssetManager(), "UI/test.png", true);
        test.setWidth(settings.getWidth());
        test.setHeight(settings.getHeight());
        test.setPosition(settings.getWidth() / 2, settings.getHeight() / 2);

        main.getGuiNode().attachChild(test);

        test.setQueueBucket(Bucket.Gui);

    }


    @Override
    public void update(float tpf) {

        Vector2f click2d = main.getInputManager().getCursorPosition();
        System.out.println(click2d.toString());
        
        Point point = new Point((int)click2d.x, (int)click2d.y);
        
        if(startButtonBox.contains(point)){
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
