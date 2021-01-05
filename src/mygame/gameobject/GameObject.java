/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import mygame.state.Main;

/**
 * all game objects that exist 
 * @author leoze
 */
public abstract class GameObject {
    
    private Vector3f position; // position of object 
    private String name; // name of objct 
    private Spatial model;
    
    protected Main main;
   
    public GameObject(Main main, Vector3f position, String name){
        
        this.position = position;
        this.name = name;
        this.main = main; 
    }
    
    /**
     * init, create model and add to rootNode
     */
    abstract void init();
    
    /**
     * set position of object 
     */
    public void setPosition(){
        getModel().setLocalTranslation(position);
    }
    
    /**
     * delete object when finished
     */
    abstract void delete();
    /**
     * @return the model
     */
    public Spatial getModel() {
        return model;
    }
    public Vector3f getPosition(){
        return position;
    }
    public String getName(){
        return name;
    }
    public void setPosition(Vector3f position){
        this.position = position;
    }
    public void setModel(Spatial model){
        this.model = model;
    }


    
    
    
    
}
