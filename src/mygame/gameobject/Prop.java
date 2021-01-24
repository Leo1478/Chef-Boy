package mygame.gameobject;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.math.Vector3f;

/**
 * Prop.java
 * prop objects 
 * @author Leo Zeng
 * 2020/12/25
 */
public abstract class Prop extends Map{
    
    /**
     * Prop
     * @param app appication
     * @param bulletAppState physics 
     * @param position current position 
     * @param name object name 
     */
    public Prop(SimpleApplication app, BulletAppState bulletAppState, Vector3f position, String name){
        super(app, bulletAppState, position, name);
    }

}
