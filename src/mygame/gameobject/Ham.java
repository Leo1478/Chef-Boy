/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import mygame.state.Main;

/**
 * ham 
 * pig will drop after dying 
 * @author leoze
 */
public class Ham extends Item{
    
    public Ham(Main main, Vector3f position, String name){
        
        super(main, position, name);
        setPickUpRadius(2);
        init();
    }


    @Override
    void init() {
        
        Material mat = main.getAssetManager().loadMaterial("Materials/ham.j3m");
        setModel(main.getAssetManager().loadModel("Models/ham/ham.j3o"));
        getModel().setMaterial(mat);
        
        
        getModel().setShadowMode(RenderQueue.ShadowMode.Cast);
        
        setPosition(); // set position needs to be before creating collision mesh for some reason
        
        main.getRootNode().attachChild(getModel());
    }


    
    @Override
    void delete() {
        main.getRootNode().detachChild(getModel());
    }    
}
