/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import mygame.state.Main;

/**
 * map objects 
 * @author leoze
 */
public abstract class Map extends GameObject{
    
    private CollisionShape collisionMesh; // mesh to map collision 
    private RigidBodyControl rigidBody; // rigidbody to simulate physical object 
    private BulletAppState bulletAppState; // controls physics 
    
    public Map(SimpleApplication app, BulletAppState bulletAppState, Vector3f position, String name){
        super(app, position, name);
        this.bulletAppState = bulletAppState;
    }

    /**
     * init physics 
     * add physics to bulletAppstate 
     */
    public void initPhysics(){
        bulletAppState.getPhysicsSpace().add(getRigidBody());
    }
    
    /**
     * @return the collisionMesh
     */
    public CollisionShape getCollisionMesh() {
        return collisionMesh;
    }

    /**
     * @param collisionMesh the collisionMesh to set
     */
    public void setCollisionMesh(CollisionShape collisionMesh) {
        this.collisionMesh = collisionMesh;
    }

    /**
     * @param rigidBody the rigidBody to set
     */
    public void setRigidBody(RigidBodyControl rigidBody) {
        this.rigidBody = rigidBody;
    }

    /**
     * @return the rigidBody
     */
    public RigidBodyControl getRigidBody() {
        return rigidBody;
    }
    
    

    
}
