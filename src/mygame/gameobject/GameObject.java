package mygame.gameobject;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

/**
 * GameObject.java
 * all game objects that exist
 * @author Leo Zeng, Ariana Hou
 * 2020/12/20
 */
public abstract class GameObject {

    private Vector3f position; // position of object 
    private Quaternion rotation; // rotation of object
    private String name; // name of objct 
    private Spatial model; // model of object
    private Material mat; // color of object 
    
    protected SimpleApplication app; // application

    /**
     * GameObject
     * constructor set values
     * @param app application 
     * @param position current position 
     * @param name object name 
     */
    public GameObject(SimpleApplication app, Vector3f position, String name) {

        this.position = position;
        this.name = name;
        this.app = app;

        this.rotation = new Quaternion();
        rotation.fromAngles(0, 0, 0);
    }

    /**
     * init
     * create model and add to rootNode
     */
    abstract void init();

    /**
     * setModelPosition
     * set position of object
     */
    public void setModelPosition() {
        getModel().setLocalTranslation(position);
    }

    /**
     * setModelRotation
     * set rotation of object
     */
    public void setModelRotation() {
        getModel().setLocalRotation(rotation);
    }

    /**
     * setPosition
     * @param position position of object
     */
    public void setPosition(Vector3f position) {
        this.position = position;
    }

    /**
     * deleteModel
     * delete object when finished
     */
    public void deleteModel() {
        app.getRootNode().detachChild(getModel());
    }

    /**
     * getModel
     * @return the model
     */
    public Spatial getModel() {
        return model;
    }

    /**
     * getPosition
     * @return the position
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * getName
     * @return the name 
     */
    public String getName() {
        return name;
    }

    /**
     * setModel
     * @param model model of object
     */
    public void setModel(Spatial model) {
        this.model = model;
    }

    /**
     * getMat
     * @return the material
     */
    public Material getMat() {
        return mat;
    }

    /**
     * setMat
     * @param mat the material to set
     */
    public void setMat(Material mat) {
        this.mat = mat;
    }

    /**
     * getRotation
     * @return the rotation
     */
    public Quaternion getRotation() {
        return rotation;
    }

    /**
     * setRotation
     * @param rotation the rotation to set
     */
    public void setRotation(Quaternion rotation) {
        this.rotation = rotation;
    }

}
