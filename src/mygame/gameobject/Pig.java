/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;

/**
 *
 * @author leoze
 */
public class Pig extends Enemy {

    public Pig(SimpleApplication app, BulletAppState bulletAppState, Vector3f position, String name, int health) {
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
        initAnimation();
        setModelPosition();

    }

    @Override
    void init() {

        setMat(app.getAssetManager().loadMaterial("Materials/orange.j3m"));

        // change to xml file with animation later 
        setModel(app.getAssetManager().loadModel("Models/pig/Plane.mesh.xml"));
        //setModel(app.getAssetManager().loadModel("Models/cube/Cube.mesh.xml"));
        


        getModel().setMaterial(getMat());

        getModel().setShadowMode(RenderQueue.ShadowMode.Cast);



    }
    
}
