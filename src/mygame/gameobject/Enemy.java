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
public abstract class Enemy extends Character{
    
    private int damage;
    private double speed;
    private double range;
    private double detectionRange;
    private boolean detectedPlayer;
    
    public Enemy(Main main, Vector3f position, String name, int health){
        super(main, position, name, health);

    }
    
    
    public void behaviour(ChefBoy chefBoy){
        
        detection(chefBoy);
        
        attack(chefBoy);
        
        moveTowardsPlayer(chefBoy);
        
        
    }
    
    private void detection(ChefBoy chefBoy){
        
        double distance;
        
        double x = this.getPosition().x;
        double x1 = chefBoy.getPosition().x;
        double z = this.getPosition().z;
        double z1 = chefBoy.getPosition().z;
        
        distance = Math.sqrt(Math.pow(x1-x, 2) + Math.pow(z1-z, 2));
        
        if(distance < getDetectionRange()){
            System.out.println("charge");
            setDetectedPlayer(true);
            
            if(distance < getRange()){
                System.out.println("attack");
                setDetectedPlayer(false);
            }
        }
        
        else {
            System.out.println("far from player");
            setDetectedPlayer(false);         
        }
    }
    
    private void attack(ChefBoy chefBoy){
        
        
        /**
         * actual attack method will be very different 
         */
        
        double distance;
        
        double x = this.getPosition().x;
        double x1 = chefBoy.getPosition().x;
        double z = this.getPosition().z;
        double z1 = chefBoy.getPosition().z;
        
        distance = Math.sqrt(Math.pow(x1-x, 2) + Math.pow(z1-z, 2));
        
        if(distance < getRange()){
            System.out.println("attack");
        }
    }
    
    private void moveTowardsPlayer(ChefBoy chefBoy){
        if(isDetectedPlayer()){
            
            double xDiff = this.getPosition().x - chefBoy.getPosition().x;
            double zDiff = this.getPosition().z - chefBoy.getPosition().z;
            
            double normalizeNumber = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(zDiff, 2));
            
            xDiff = xDiff / normalizeNumber;
            zDiff = zDiff / normalizeNumber;
            
            this.getPosition().x -= xDiff * getSpeed();
            this.getPosition().z -= zDiff * getSpeed();
            
            setPosition();
            

        }
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

    /**
     * @return the detectionRange
     */
    public double getDetectionRange() {
        return detectionRange;
    }

    /**
     * @param detectionRange the detectionRange to set
     */
    public void setDetectionRange(double detectionRange) {
        this.detectionRange = detectionRange;
    }

    /**
     * @return the detectedPlayer
     */
    public boolean isDetectedPlayer() {
        return detectedPlayer;
    }

    /**
     * @param detectedPlayer the detectedPlayer to set
     */
    public void setDetectedPlayer(boolean detectedPlayer) {
        this.detectedPlayer = detectedPlayer;
    }
    
    
}
