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
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import mygame.state.Main;

/**
 * terrain / ground object 
 * @author leoze
 */
public class Terrain extends Map{
    
    private RigidBodyControl landscape;

    public Terrain(Main main, Vector3f position, String name){
        super(main, position, name);
        init();
    }
    
    /**
     * init 
     * create model, add model to rootNode 
     * add rigidBody for physics 
     */
    @Override
    void init() {
        
        setMat(main.getAssetManager().loadMaterial("Materials/terrainDots.j3m"));
        
        setModel(main.getAssetManager().loadModel("Models/terrain/terrain.j3o"));
        
        getModel().setMaterial(getMat());
        
        getModel().setShadowMode(ShadowMode.Receive);

        setCollisionMesh(CollisionShapeFactory.createMeshShape(getModel()));
        landscape = new RigidBodyControl(getCollisionMesh(), 0);
        getModel().addControl(landscape);
        
        main.getRootNode().attachChild(getModel());
        setModelPosition();
        initPhysics();
    }
    
    /**
     * init physics 
     * add physics to bulletAppstate 
     */
    void initPhysics(){
        main.gameState.bulletAppState.getPhysicsSpace().add(landscape);
    }

    
}
