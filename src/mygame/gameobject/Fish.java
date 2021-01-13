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
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bounding.BoundingBox;

/**
 *
 * @author ariana
 */
public class Fish extends Enemy {

    public Fish(SimpleApplication app, BulletAppState bulletAppState, Vector3f position, String name, int health) {
        super(app, bulletAppState, position, name, health);

        setAlive(true);
        setHealth(20);
        setDamage(10);
        setSpeed(0.3);
        setRange(10);
        setDetectionRange(30);
        setAttackSpeed(5);
        setCoolDown(5);
        
        init();
        initCollision();
        setModelPosition();

    }

    @Override
    void init() {

        setMat(app.getAssetManager().loadMaterial("Materials/orange.j3m"));

        // change to xml file with animation later 
        setModel(app.getAssetManager().loadModel("Models/pig/Plane.mesh.j3o"));

        getModel().setMaterial(getMat());

        getModel().setShadowMode(RenderQueue.ShadowMode.Cast);

        app.getRootNode().attachChild(getModel());

        initCollision();

    }
    
}
