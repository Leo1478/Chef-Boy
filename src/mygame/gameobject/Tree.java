package mygame.gameobject;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;


/**
 * Tree.java
 * tree prop
 * @author Leo Zeng
 * 2020/12/25
 */
public class Tree extends Prop{
    
    /**
     * Tree
     * @param app application 
     * @param bulletAppState physics 
     * @param position current position 
     * @param name object name
     */
    public Tree(SimpleApplication app, BulletAppState bulletAppState, Vector3f position, String name){
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
        
        setMat(app.getAssetManager().loadMaterial("Materials/tree.j3m"));
        setModel(app.getAssetManager().loadModel("Models/trees/Tree 1/tree.j3o"));
        getModel().setMaterial(getMat());
        getModel().setShadowMode(ShadowMode.Cast);
        setModelPosition();
        app.getRootNode().attachChild(getModel());
        
        initCollision();
        initPhysics();      
        
    }
    
}
