package mygame.gameobject;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;

/**
 * Pig.java
 * pig enemy
 * @author Ariana Hou
 */
public class Pig extends Enemy {

    /**
     * Pig 
     * constructor set all values 
     * @param app application 
     * @param bulletAppState physics 
     * @param position current position 
     * @param name obejct name 
     * @param health pig health 
     */
    public Pig(SimpleApplication app, BulletAppState bulletAppState, Vector3f position, String name, int health) {
        super(app, bulletAppState, position, name, health);

        setAlive(true);
        setHealth(health);
        setDamage(10);
        setSpeed(0.3);
        setRange(10);
        setDetectionRange(30);
        setAttackSpeed(7);
        setCoolDown(7);
        
        init();
        initCollision();
        initAnimation();
        setModelPosition();

    }

    /**
     * init
     * initialise model
     */
    @Override
    void init() {

        setMat(app.getAssetManager().loadMaterial("Materials/orange.j3m"));
        setModel(app.getAssetManager().loadModel("Models/pig/Plane.mesh.j3o"));
        getModel().setMaterial(getMat());
        getModel().setShadowMode(RenderQueue.ShadowMode.Cast);

    }
}
