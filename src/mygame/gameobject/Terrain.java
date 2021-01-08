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
 * terrain / ground object 
 * @author leoze
 */
public class Terrain extends Map{
    

    public Terrain(SimpleApplication app, BulletAppState bulletAppState, Vector3f position, String name){
        super(app, bulletAppState, position, name);
        init();
    }
    
    /**
     * init 
     * create model, add model to rootNode 
     * add rigidBody for physics 
     */
    @Override
    void init() {
        
        setMat(app.getAssetManager().loadMaterial("Materials/terrainDots.j3m"));
        
        setModel(app.getAssetManager().loadModel("Models/terrain/terrain.j3o"));
        
        getModel().setMaterial(getMat());
        
        getModel().setShadowMode(ShadowMode.Receive);

        app.getRootNode().attachChild(getModel());
        setModelPosition();
        
        initCollision();
        initPhysics();
    }

    
}
