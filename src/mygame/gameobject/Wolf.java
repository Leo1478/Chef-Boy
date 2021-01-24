package mygame.gameobject;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;

/**
 * Wolf.java
 * wolf enemy
 * @author William Zhao
 */
public class Wolf extends Enemy{
    
    /**
     * Wolf
     * constructor
     * @param app application 
     * @param bulletAppState physics 
     * @param position current position 
     * @param name object name 
     * @param health current health
     */
    public Wolf(SimpleApplication app, BulletAppState bulletAppState, Vector3f position, String name, int health){
        super(app, bulletAppState, position, name, health);
        
        setAlive(true);
        setHealth(20);
        setDamage(10);
        setSpeed(0.5);
        setRange(10);
        setDetectionRange(100);
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
        setModel(app.getAssetManager().loadModel("Models/Wolf/Wolf.mesh.j3o"));

        // this is still pigs stuff 
        
        setMat(app.getAssetManager().loadMaterial("Materials/Wolf Material.j3m"));
        
        // change to xml file with animation later 
        // using pigs animation as a place holder change later
        setModel(app.getAssetManager().loadModel("Models/Wolf/Wolf.mesh.j3o"));

        getModel().setMaterial(getMat());
        getModel().setShadowMode(RenderQueue.ShadowMode.Cast);
    }
}
