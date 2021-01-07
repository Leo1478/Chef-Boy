/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;

/**
 *
 * @author leoze
 */
public class Pan extends GameObject{

    
    
    Pan(SimpleApplication app, Vector3f position, String name){
        super(app, position, name);
    }
    
    @Override
    void init() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
