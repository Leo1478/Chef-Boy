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
import java.awt.Rectangle;

/**
 *
 * @author leoze
 */
public class Button {
    
    private Rectangle hitBox;
    private Picture picture;
    private String path;
    private boolean clicked;
    private SimpleApplication app;
    
    public Button(SimpleApplication app, Rectangle hitBox, String path){
        this.hitBox = hitBox;
        this.path = path;
        this.clicked = false;
        this.app = app;
        init();
    }
    
    private void init(){
       
        
        AppSettings settings = new AppSettings(true);
     
        Picture picture = new Picture("button");
        picture.setImage(app.getAssetManager(), path, true);
        picture.setWidth(hitBox.width);
        picture.setHeight(hitBox.height);
        picture.setPosition(hitBox.x, hitBox.y);

        app.getGuiNode().attachChild(picture);
        picture.setQueueBucket(RenderQueue.Bucket.Gui);
        
    }

    /**
     * @return the hitBox
     */
    public Rectangle getHitBox() {
        return hitBox;
    }

    /**
     * @param hitBox the hitBox to set
     */
    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
    }

    /**
     * @return the picture
     */
    public Picture getPicture() {
        return picture;
    }

    /**
     * @param picture the picture to set
     */
    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    /**
     * @return the clicked
     */
    public boolean isClicked() {
        return clicked;
    }

    /**
     * @param clicked the clicked to set
     */
    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
    
}
