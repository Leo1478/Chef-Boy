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
import com.jme3.renderer.queue.RenderQueue;
import mygame.state.Main;

/**
 *
 * @author leoze
 */
public class Volcano extends Prop{
   
    
    public Volcano(SimpleApplication app, BulletAppState bulletAppState, Vector3f position, String name){
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
        
        setMat(app.getAssetManager().loadMaterial("Materials/volcano.j3m"));
        
        setModel(app.getAssetManager().loadModel("Models/volcano/volcano.j3o"));
        
        getModel().setMaterial(getMat());
        
        getModel().setShadowMode(RenderQueue.ShadowMode.Cast);
        
        setModelPosition(); // set position needs to be before creating collision mesh for some reason
        
        app.getRootNode().attachChild(getModel());
        
        initCollision();
        
        initPhysics();
  
        
    }
    @Override
    public void initCollision() {
        setCollisionMesh(CollisionShapeFactory.createMeshShape(getModel()));
        setRigidBody(new RigidBodyControl(getCollisionMesh(), 0));
        getModel().addControl(getRigidBody());
    }


}
