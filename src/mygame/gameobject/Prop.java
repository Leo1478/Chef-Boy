/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import mygame.state.Main;

/**
 * prop objects 
 * @author leoze
 */
public abstract class Prop extends Map{
    
    private RigidBodyControl propCollision;

    public Prop(Main main, Vector3f position, String name){
        super(main, position, name);
    }
    
    void initPhysics(){
        main.gameState.bulletAppState.getPhysicsSpace().add(getPropCollision());
    }

    /**
     * @return the propCollision
     */
    public RigidBodyControl getPropCollision() {
        return propCollision;
    }

    /**
     * @param propCollision the propCollision to set
     */
    public void setPropCollision(RigidBodyControl propCollision) {
        this.propCollision = propCollision;
    }
}
