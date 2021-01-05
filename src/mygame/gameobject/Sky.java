/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

import com.jme3.math.Vector3f;
import mygame.state.Main;

/**
 *
 * @author leoze
 */
public class Sky extends Map{

    public Sky(Main main, Vector3f position, String name){
        super(main, position, name);
    }
    
    @Override
    void init() {
       
    }

}
