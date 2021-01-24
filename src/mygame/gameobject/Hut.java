package mygame.gameobject;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;


/**
 * Hut.java
 * hut building to spawn in
 * @author Leo Zeng
 * 2021/01/19
 */
public class Hut extends Prop{
    
    /**
     * Hut
     * constructor 
     * @param app application 
     * @param bulletAppState physics 
     * @param position current position 
     * @param name object name
     */
    public Hut(SimpleApplication app, BulletAppState bulletAppState, Vector3f position, String name){
        super(app, bulletAppState, position, name);
        init();
    }

    /**
     * init 
     * create model
     * add collision mesh to model
     * add model to rootNode
     * add mesh to bulletAppState for physics collision 
     */
    @Override
    void init() {
        
        setMat(app.getAssetManager().loadMaterial("Materials/hut.j3m"));
        setModel(app.getAssetManager().loadModel("Models/hut/hut.j3o"));
        getModel().setMaterial(getMat());
        getModel().setShadowMode(ShadowMode.Cast);
        setModelPosition();
        app.getRootNode().attachChild(getModel());
        
        initCollision();
        initPhysics();      
        
    }
}
