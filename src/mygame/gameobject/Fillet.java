package mygame.gameobject;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;

/**
 * ham.java 
 * item pig will drop after dying
 *
 * @author Ariana Hou
 * 2021/01/03
 */
public class Fillet extends Item {

    /**
     * Fillet
     * constructor
     * @param app application 
     * @param position current position 
     * @param name object name 
     */
    public Fillet(SimpleApplication app, Vector3f position, String name) {
        super(app, position, name);
        setPickUpRadius(2);
        init();
    }

    /**
     * init
     * init model and picture
     */
    @Override
    void init() {

        setMat(app.getAssetManager().loadMaterial("Materials/fillet.j3m"));
        setModel(app.getAssetManager().loadModel("Models/fillet/fillet.j3o"));
        getModel().setMaterial(getMat());
        getModel().setShadowMode(RenderQueue.ShadowMode.Cast);
        setModelPosition();
        app.getRootNode().attachChild(getModel());

        setItemPic(new ItemPic(app, "Models/fillet/fillet pic.png")); // set item picture

    }

}
