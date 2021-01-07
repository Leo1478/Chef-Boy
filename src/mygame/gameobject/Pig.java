/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import mygame.state.Main;

/**
 *
 * @author leoze
 */
public class Pig extends Enemy{
    
    BulletAppState bulletAppState;

    public Pig(SimpleApplication app, BulletAppState bulletAppState, Vector3f position, String name, int health){
        super(app, bulletAppState, position, name, health);
        
        this.bulletAppState = bulletAppState;
        
        setDamage(10);
        setSpeed(600);
        setRange(10);
        setDetectionRange(30);
        
        init();
        initCollision();
        setModelPosition();

    }
    

    
    @Override
    void init() {
        
        setMat(app.getAssetManager().loadMaterial("Materials/orange.j3m"));
        
        // change to xml file with animation later 
        setModel(app.getAssetManager().loadModel("Models/pig/pig.j3o"));
        
        getModel().setMaterial(getMat());
        
        getModel().setShadowMode(RenderQueue.ShadowMode.Cast);
        
        app.getRootNode().attachChild(getModel());    
        
        initCollision();
        
        

    }
    
    
    @Override
    public void initCollision(){
        
        BoxCollisionShape collisionShape = new BoxCollisionShape(new Vector3f(0.8f, 0.5f, 0.8f));
        setCharacterControl(new CharacterControl(collisionShape, 0.05f));
        getCharacterControl().setFallSpeed(10);
        
        bulletAppState.getPhysicsSpace().add(getCharacterControl());
        getCharacterControl().setGravity(new Vector3f(0, -60f, 0));
        
        getCharacterControl().setPhysicsLocation(getPosition());
        
        // init rigidbody here for all characters 
        
    }
    
}

