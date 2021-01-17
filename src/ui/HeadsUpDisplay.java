/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.jme3.app.SimpleApplication;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.system.AppSettings;
import com.jme3.ui.Picture;

/**
 *
 * @author leoze
 */
public class HeadsUpDisplay {
    
    private SimpleApplication app;
    private Picture healthBar;
    private Picture crossHair; 
    
    public HeadsUpDisplay(SimpleApplication app){
        
        this.app = app;
        init();
    }
    
    private void init(){
        
        AppSettings settings = new AppSettings(true);
     
        Picture test = new Picture("health bar");
        test.setImage(app.getAssetManager(), "UI/health bar.png", true);
        test.setWidth(300);
        test.setHeight(100);
        test.setPosition(1300,600);

        app.getGuiNode().attachChild(test);
        test.setQueueBucket(RenderQueue.Bucket.Gui);
    }
    
    public void update(){
        
    }
    
    private void display(){
        
    }
    
}
