/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;

/**
 *
 * @author leoze
 */
public class Pan extends GameObject{

    
    
    public Pan(SimpleApplication app, Vector3f position, String name){
        super(app, position, name);
        
        init();
    }
    


    @Override
    void init() {

        setMat(app.getAssetManager().loadMaterial("Materials/pan.j3m"));

        //setModel(app.getAssetManager().loadModel("Models/pan/pan.mesh.xml"));
        setModel(app.getAssetManager().loadModel("Models/pan/pan.glb"));

        getModel().setMaterial(getMat());

        getModel().setShadowMode(RenderQueue.ShadowMode.Cast);

        app.getRootNode().attachChild(getModel());

    }

}
