/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import mygame.state.Main;


/**
 *
 * @author leoze
 */
public class TreeThree extends Prop{
    
    
    public TreeThree(SimpleApplication app, BulletAppState bulletAppState, Vector3f position, String name){
        super(app, bulletAppState, position, name);
        init();
    }

    /**
     * init 
     * create model
     * add collision mesh to model
     * add model to rootNode
     * add mesh to bulletAppState for physics collision 
     */
    @Override
    void init() {
        
        setMat(app.getAssetManager().loadMaterial("Materials/TreeThree.j3m"));
        
        setModel(app.getAssetManager().loadModel("Models/trees/Tree 3/Tree 3.j3o"));
        
        getModel().setMaterial(getMat());
        
        getModel().setShadowMode(ShadowMode.Cast);
        
        setModelPosition(); // set position needs to be before creating collision mesh for some reason
        
        app.getRootNode().attachChild(getModel());
        
        initCollision();
        initPhysics();      
        
    }
    
}
