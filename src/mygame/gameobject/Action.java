/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

import com.jme3.math.Vector3f;

/**
 *
 * @author leoze
 */
public interface Action {
    
    void attack(Character character);
    
    void move(Vector3f change);
    
}
