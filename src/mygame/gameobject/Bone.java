package mygame.gameobject;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;


/**
 * Bone
 * item wolf drops
 * @author William Zhao
 * 2021/01/23
 */
public class Bone extends Item{
    
    /**
     * Bone
     * constructor
     * @param app application 
     * @param position current position
     * @param name object name
     */
    public Bone(SimpleApplication app, Vector3f position, String name){
        
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
        
        setMat(app.getAssetManager().loadMaterial("Materials/Bone Material.j3m"));
        setModel(app.getAssetManager().loadModel("Models/Bone/Bone.j3o"));
        getModel().setMaterial(getMat());
        getModel().setShadowMode(RenderQueue.ShadowMode.Cast);
        setModelPosition(); 
        app.getRootNode().attachChild(getModel());

        setItemPic(new ItemPic(app, "Models/Bone/bone pic.png"));
        
    }
}