package mygame.gameobject;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;

/**
 * Jelly.java
 * item slime will drop after dying
 * @author Ariana Hou
 * 2021/01/03
 */
public class Jelly extends Item{
    
    /**
     * Jelly
     * constructor
     * @param app application 
     * @param position current position 
     * @param name name of object 
     */
    public Jelly(SimpleApplication app, Vector3f position, String name){
        
        super(app, position, name);
        setPickUpRadius(2);
        init();
    }

    /**
     * init 
     * initialise model
     */
    @Override
    void init() {
        
        setMat(app.getAssetManager().loadMaterial("Materials/jelly.j3m"));
        setModel(app.getAssetManager().loadModel("Models/jelly/jelly.j3o"));
        getModel().setMaterial(getMat());
        getModel().setShadowMode(RenderQueue.ShadowMode.Cast);
        setModelPosition();
        app.getRootNode().attachChild(getModel());
        
        setItemPic(new ItemPic(app, "Models/jelly/jelly pic.png"));
        
    }
}
