/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

import com.jme3.anim.AnimComposer;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.math.Matrix3f;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import mygame.state.Main;

/**
 *
 * @author leoze
 */
public abstract class Enemy extends Character implements Action, ChangeHealth{
    
    private AnimComposer animComposer; // animation 
    
    private double detectionRange; // range to detect player 
    
    private double distanceToChef;
    
    // private boolean change // if animation should change (last state is different from current)
    
    public Enemy(SimpleApplication app, Vector3f position, String name, int health){
        super(app, position, name, health);
    }
    
    /**
     * behaviour of enemy 
     * calls other enemy methods 
     * @param tpf
     * @param chefBoy 
     */
    public void behaviour(float tpf, ChefBoy chefBoy){
        
        determineDistance(chefBoy);
        
        determinState();
        
        setAnimation();
        
        switch (getState()) {
            case IDLE:
                idle();
                break;
            case MOVING:
                charge(tpf, chefBoy);
                break;
            case ATTACKING:
                attack(chefBoy);
                break;
            default:
                break;
        }
    }
    
    private void setAnimation(){
        // if animation is changing
            // set animation 
            
    }
    
    /**
     * determine distance to chefBoy
     * @param chefBoy 
     */
    private void determineDistance(ChefBoy chefBoy){

        double x = this.getPosition().x;
        double x1 = chefBoy.getPosition().x;
        double z = this.getPosition().z;
        double z1 = chefBoy.getPosition().z;
        
        distanceToChef = Math.sqrt(Math.pow(x1-x, 2) + Math.pow(z1-z, 2));
    }
    
    /**
     * determin state based on distance to chefBoy
     * if in detectionRange, set state to charge 
     * if in attackRange, set state to attack 
     */
    private void determinState(){
        
        if(distanceToChef < getDetectionRange()){
            setState(CharacterState.MOVING); 
            if(distanceToChef < getRange()){
                setState(CharacterState.ATTACKING);
            }
        }
        else {
            setState(CharacterState.IDLE);         
        }
    }
    
    /**
     * idle mode for enemy 
     */
    private void idle(){
        
        //System.out.println("chilling");
    }
    
    /**
     * charge chef boy 
     * find location of chefBoy, move towards chefBoy
     * @param chefBoy 
     */
    private void charge(float tpf, ChefBoy chefBoy){
        
        double xDiff = this.getPosition().x - chefBoy.getPosition().x;
        double zDiff = this.getPosition().z - chefBoy.getPosition().z;

        // normalizeNumber adjust speed to be constant 
        double normalizeNumber = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(zDiff, 2));

        xDiff = xDiff / normalizeNumber;
        zDiff = zDiff / normalizeNumber;

        this.getPosition().x -= xDiff * getSpeed() * tpf; // move enemy
        this.getPosition().z -= zDiff * getSpeed() * tpf;

        setModelPosition();
        
        
        float radian = (float) Math.atan2(zDiff, xDiff); // find out radian between player and enemy 
        // System.out.println(radian);
        
        Quaternion roatation = new Quaternion();
        roatation.fromAngles(0, radian*-1 , 0); // y rotation,  reverse number

        getModel().setLocalRotation(roatation); // change model rotation 

        
        //System.out.println("charg");
            
    }
    
    /*
    check if this enemy is colliding with any other game object when it is moving 
    private boolean collideWithObject(){
        
    }
    */
    
    void spawn(){
        
        // add to list in gameState
        // add to rootNode
    }
    
    
    @Override
    public void move(Vector3f change){
        
    }
    

    @Override
    public void attack(Character character) {
        //System.out.println("attack");
    }
    
    @Override
    public void block(){
        
    }

    @Override
    public void addHealth(int amount) {
        
    }

    @Override
    public void removeHealth(int amount) {
        
    }
    
    @Override
    public void takeDamage(int arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
}
