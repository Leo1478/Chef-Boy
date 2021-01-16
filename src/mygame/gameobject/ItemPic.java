/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.ui.Picture;

/**
 *
 * @author leoze
 */
public class ItemPic {

    /**
     * @return the picture
     */
    public Picture getPicture() {
        return picture;
    }
    
    private Picture picture;
    
    private SimpleApplication app;
    
    private final int PICTUREWIDTH = 100;
    private final int PICTURELENGTH = 100;
    
    // picture of item for use in inventory;
    ItemPic(SimpleApplication app,  String path){
        
        this.app = app;

        init(path);
    }
    
    private void init(String path){
        
        picture = new Picture("Item");
        getPicture().setImage(app.getAssetManager(), path, true);
        getPicture().setWidth(PICTUREWIDTH);
        getPicture().setHeight(PICTURELENGTH);
        getPicture().setPosition(0, 0);
        
        //app.getGuiNode().attachChild(picture);

        //picture.setQueueBucket(RenderQueue.Bucket.Gui);
        
        
        
        
        
        
    }
    
    public void display(){
        
    }
    
    public void setPosition(int x, int y){
        getPicture().setPosition(x, y);
    }
}
