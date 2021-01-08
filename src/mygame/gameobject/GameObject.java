/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import mygame.state.Main;

/**
 * all game objects that exist
 *
 * @author leoze
 */
public abstract class GameObject {

    private Vector3f position; // position of object 
    private String name; // name of objct 
    private Spatial model;
    private Material mat; // color of object 

    protected SimpleApplication app;

    public GameObject(SimpleApplication app, Vector3f position, String name) {

        this.position = position;
        this.name = name;
        this.app = app;
    }

    /**
     * init, create model and add to rootNode
     */
    abstract void init();

    /**
     * set position of object
     */
    public void setModelPosition() {
        getModel().setLocalTranslation(position);
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    /**
     * delete object when finished
     */
    public void deleteModel() {
        app.getRootNode().detachChild(getModel());
    }

    /**
     * @return the model
     */
    public Spatial getModel() {
        return model;
    }

    public Vector3f getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }


    public void setModel(Spatial model) {
        this.model = model;
    }

    /**
     * @return the mat
     */
    public Material getMat() {
        return mat;
    }

    /**
     * @param mat the mat to set
     */
    public void setMat(Material mat) {
        this.mat = mat;
    }

}
