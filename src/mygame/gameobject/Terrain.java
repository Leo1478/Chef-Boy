package mygame.gameobject;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;

/**
 * Terrain.java
 * terrain / ground
 * @author Ariana Hou
 * 2021/01/05
 */
public class Terrain extends Map{
    
    /**
     * Terrain
     * @param app application
     * @param bulletAppState physics
     * @param position current position 
     * @param name object name
     */
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
