package mygame.gameobject;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;

/**
 * Ham.java
 * item pig will drop after dying 
 * @author Ariana Hou
 * 2020/12/25
 */
public class Ham extends Item{
    
    /**
     * Ham
     * constructor 
     * @param app application 
     * @param position current posiiton 
     * @param name object name
     */
    public Ham(SimpleApplication app, Vector3f position, String name){
        
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
        
        setMat(app.getAssetManager().loadMaterial("Materials/ham.j3m"));
        setModel(app.getAssetManager().loadModel("Models/ham/ham.j3o"));   
        getModel().setMaterial(getMat());     
        getModel().setShadowMode(RenderQueue.ShadowMode.Cast);   
        setModelPosition(); 
        app.getRootNode().attachChild(getModel());
        
        setItemPic(new ItemPic(app, "Models/ham/ham pic.png"));
       
    }
}
