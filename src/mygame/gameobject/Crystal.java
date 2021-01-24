package mygame.gameobject;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;

/**
 * Crystal.java
 * crystal prop
 * @author William Zhao
 * 2021/01/20
 */
public class Crystal extends Prop{
    
    /**
     * Crystal 
     * constructor
     * @param app application 
     * @param bulletAppState bulletAppState for physics 
     * @param position current position 
     * @param name name of object 
     */
    public Crystal(SimpleApplication app, BulletAppState bulletAppState, Vector3f position, String name){
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
        
        setMat(app.getAssetManager().loadMaterial("Materials/Crystal.j3m"));
        setModel(app.getAssetManager().loadModel("Models/crystal/crystal.j3o"));
        getModel().setMaterial(getMat());
        getModel().setShadowMode(ShadowMode.Cast);
        setModelPosition();
        app.getRootNode().attachChild(getModel());
        
        initCollision();
        initPhysics();      
        
    }    
}
