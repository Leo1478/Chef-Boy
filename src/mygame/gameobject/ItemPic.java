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
    
    Picture picture;
    Item item;
    
    SimpleApplication app;
    
    // picture of item for use in inventory;
    ItemPic(SimpleApplication app,  String path){

        init();
    }
    
    private void init(){
        
        picture = new Picture("Item");
        picture.setImage(app.getAssetManager(), "UI/test.png", true);
        
        app.getGuiNode().attachChild(picture);

        picture.setQueueBucket(RenderQueue.Bucket.Gui);
    }
    
    public void display(){
        
    }
}
