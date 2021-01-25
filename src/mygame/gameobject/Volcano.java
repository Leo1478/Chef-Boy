package mygame.gameobject;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;

/**
 * Volcano.java
 * volcano prop
 * @author Ariana Hou
 * 2021/01/07
 */
public class Volcano extends Prop{
   
    /**
     * Volcano
     * constructor
     * @param app application 
     * @param bulletAppState physics 
     * @param position current position 
     * @param name object name 
     */
    public Volcano(SimpleApplication app, BulletAppState bulletAppState, Vector3f position, String name){
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
<<<<<<< Updated upstream
        


        setMat(app.getAssetManager().loadMaterial("Materials/Volcano Material.j3m"));
        
        setModel(app.getAssetManager().loadModel("Models/volcano/Volcano 2/volcano 2.j3o"));

        setMat(app.getAssetManager().loadMaterial("Materials/volcano.j3m"));
        setModel(app.getAssetManager().loadModel("Models/volcano/volcano.j3o"));
=======
>>>>>>> Stashed changes

        setMat(app.getAssetManager().loadMaterial("Materials/Volcano Material.j3m"));
        
        setModel(app.getAssetManager().loadModel("Models/volcano/Volcano 2/volcano 2.j3o"));
        
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes

        getModel().setMaterial(getMat());
        getModel().setShadowMode(RenderQueue.ShadowMode.Cast);
        setModelPosition();
        app.getRootNode().attachChild(getModel());
        
        initCollision();
        initPhysics();
  
    }
}
