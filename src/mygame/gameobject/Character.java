/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import mygame.state.Main;

/**
 * characters that can move and attack 
 * @author leoze
 */
public abstract class Character extends GameObject{
    
    private int health;
    private CharacterState state;
    private double speed;
    private double range;
    private int damage;
    private int coolDown; // interval between each attack 
    
    public Character(SimpleApplication app, Vector3f position, String name, int health){
        
        super(app, position, name);
        this.health = health;
        this.state = CharacterState.IDLE;
        
    }

    /**
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health the health to set
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @return the state
     */
    public CharacterState getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(CharacterState state) {
        this.state = state;
    }
    
        /**
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * @param damage the damage to set
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * @return the speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * @return the range
     */
    public double getRange() {
        return range;
    }

    /**
     * @param range the range to set
     */
    public void setRange(double range) {
        this.range = range;
    }

    

    
}
