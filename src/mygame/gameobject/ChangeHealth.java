/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

/**
 *
 * @author leoze
 */
public interface ChangeHealth {
    
    public void addHealth(int amount);
    
    public void removeHealth(int amount);
    
    public void takeDamage(int amount);
}
