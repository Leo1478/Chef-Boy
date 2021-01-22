/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import mygame.state.Main;

/**
 * ham 
 * pig will drop after dying 
 * @author leoze
 */
public class Fillet extends Item{
    
    public Fillet(SimpleApplication app, Vector3f position, String name){
        
        super(app, position, name);
        setPickUpRadius(2);
        init();
    }


    @Override
    void init() {
        
        setMat(app.getAssetManager().loadMaterial("Materials/fillet.j3m"));
        
        setModel(app.getAssetManager().loadModel("Models/fillet/fillet.j3o"));
        
        getModel().setMaterial(getMat());
        
        getModel().setShadowMode(RenderQueue.ShadowMode.Cast);
        
        setModelPosition(); // set position needs to be before creating collision mesh for some reason
        
        app.getRootNode().attachChild(getModel());
        
        setItemPic(new ItemPic(app, "Models/fillet/fillet pic.png"));
        
    }
  
}