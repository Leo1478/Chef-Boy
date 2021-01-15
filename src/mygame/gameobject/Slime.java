/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingBox;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;

/**
 *
 * @author leoze
 */
public class Slime extends Enemy{
    
    
    public Slime(SimpleApplication app, BulletAppState bulletAppState, Vector3f position, String name, int health){
        super(app, bulletAppState, position, name, health);
        
        setAlive(true);
        setHealth(20);
        setDamage(5);
        setSpeed(0.5);
        setRange(10);
        setDetectionRange(5);
        setAttackSpeed(10);
        setCoolDown(10);
        
        init();
        initCollision();
        initAnimation();
        setModelPosition();

    }
    
    @Override
    void init() {
        
        // this is still pigs stuff 
        
        setMat(app.getAssetManager().loadMaterial("Materials/orange.j3m"));
        
        // change to xml file with animation later 
        setModel(app.getAssetManager().loadModel("Models/slime/Plane.mesh.j3o"));
        
        getModel().setMaterial(getMat());
        
        getModel().setShadowMode(RenderQueue.ShadowMode.Cast);
        
        app.getRootNode().attachChild(getModel());    
        
        initCollision();

    }
    @Override
    public void initCollision(){
        
        Vector3f extent = ((BoundingBox) getModel().getWorldBound()).getExtent(new Vector3f());
        BoxCollisionShape collisionShape = new BoxCollisionShape(extent);
        setCharacterControl(new CharacterControl(collisionShape, 0.05f));
        getCharacterControl().setFallSpeed(10);
        
        bulletAppState.getPhysicsSpace().add(getCharacterControl());
        getCharacterControl().setGravity(new Vector3f(0, -60f, 0));
        
        getCharacterControl().setPhysicsLocation(getPosition());
        
    }
}
