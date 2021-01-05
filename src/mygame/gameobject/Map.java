/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.math.Vector3f;
import mygame.state.Main;

/**
 * map objects 
 * @author leoze
 */
public abstract class Map extends GameObject{
    
    private CollisionShape collisionMesh;
    
    Map(Main main, Vector3f position, String name){
        super(main, position, name);
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
    
}
