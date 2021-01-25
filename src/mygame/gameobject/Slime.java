package mygame.gameobject;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;

/**
 * Slime.java
 * slime enemy 
 * @author Ariana Hou
 * 2020/12/30
 */
public class Slime extends Enemy{
    
    /**
     * Slime
     * constructor set all values 
     * @param app application 
     * @param bulletAppState physics 
     * @param position current position 
     * @param name object name 
     * @param health current health
     */
    public Slime(SimpleApplication app, BulletAppState bulletAppState, Vector3f position, String name, int health){
        super(app, bulletAppState, position, name, health);
        
        setAlive(true);
        setHealth(10);
        setDamage(3);
        setSpeed(0.5);
        setRange(10);
        setDetectionRange(50);
        setAttackSpeed(2);
        setCoolDown(2);
        
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
        
        setMat(app.getAssetManager().loadMaterial("Materials/slime.j3m"));
        setModel(app.getAssetManager().loadModel("Models/slime/Plane.mesh.j3o"));
        getModel().setMaterial(getMat());
        getModel().setShadowMode(RenderQueue.ShadowMode.Cast);
    }
}
