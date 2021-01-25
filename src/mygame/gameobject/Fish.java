package mygame.gameobject;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;

/**
 * Fish.java
 * fish enemy
 * @author Ariana Hou
 * 2021/01/10
 */
public class Fish extends Enemy {

    /**
     * Fish
     * constructor set all values 
     * @param app application 
     * @param bulletAppState physics 
     * @param position current position 
     * @param name object name 
     * @param health current health
     */
    public Fish(SimpleApplication app, BulletAppState bulletAppState, Vector3f position, String name, int health) {
        super(app, bulletAppState, position, name, health);

        setAlive(true);
        setHealth(health);
        setDamage(5);
        setSpeed(0.6);
        setRange(10);
        setDetectionRange(50);
        setAttackSpeed(5);
        setCoolDown(5);

        init();
        initCollision();
        initAnimation();
        setModelPosition();

    }

    /**
     * init
     */
    @Override
    void init() {

        setMat(app.getAssetManager().loadMaterial("Materials/fish.j3m"));
        setModel(app.getAssetManager().loadModel("Models/fish/fish.mesh.j3o"));
        getModel().setMaterial(getMat());
        getModel().setShadowMode(RenderQueue.ShadowMode.Cast);
        
    }  
}
