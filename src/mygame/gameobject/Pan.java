package mygame.gameobject;

import com.jme3.anim.AnimComposer;
import com.jme3.app.SimpleApplication;
import com.jme3.audio.AudioData;
import com.jme3.audio.AudioNode;
import com.jme3.bullet.BulletAppState;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;

/**
 * Pan.java
 * pan that chefBoy holds
 * @author Leo Zeng
 * 2021/01/12
 */
public class Pan extends GameObject{
    
    private AnimComposer animComposer; // animation 
    
    /**
     * Pan
     * constructor
     * @param app application
     * @param position current position 
     * @param name object name
     */
    public Pan(SimpleApplication app, Vector3f position, String name){
        super(app, position, name);
        
        init();
        initAnimation();
    }
    
    /**
     * initAnimation
     * initialise animComposer
     */
    public void initAnimation(){
        
        animComposer = getModel().getControl(AnimComposer.class);
        animComposer.setCurrentAction("Idle");
        
    }
    
    /**
     * setAnimationAttack
     */
    public void setAnimationAttack(){
        animComposer.setCurrentAction("Attacking");
        
    }
    
    /**
     * setAnimationIdle
     */
    public void setAnimationIdle(){
        animComposer.setCurrentAction("Idle");
    }
    
    /**
     * setAnimationBlock
     */
    public void setAnimationBlock(){
        animComposer.setCurrentAction("Block");
    }
    
    /**
     * init
     * initialise model
     */
    @Override
    void init() {

        setMat(app.getAssetManager().loadMaterial("Materials/pan.j3m"));
        setModel(app.getAssetManager().loadModel("Models/pan/pan.mesh.j3o"));
        getModel().setMaterial(getMat());
        getModel().setShadowMode(RenderQueue.ShadowMode.Off);

        app.getRootNode().attachChild(getModel());

    }
}
