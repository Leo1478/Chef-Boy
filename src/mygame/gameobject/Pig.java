/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import mygame.state.Main;

/**
 *
 * @author leoze
 */
public class Pig extends Enemy{

    public Pig(Main main, Vector3f position, String name, int health){
        super(main, position, name, health);
        setDamage(10);
        setSpeed(10);
        setRange(10);
        setDetectionRange(30);
        
        init();
        setModelPosition();

    }
    
    @Override
    void init() {
        
        Material mat = main.getAssetManager().loadMaterial("Materials/orange.j3m");
        // change to xml file with animation later 
        setModel(main.getAssetManager().loadModel("Models/pig/pig.j3o"));
        
        getModel().setMaterial(mat);
        
        getModel().setShadowMode(RenderQueue.ShadowMode.Cast);
        
        main.getRootNode().attachChild(getModel());    

    }

    

}
