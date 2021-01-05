/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.math.Vector3f;
import mygame.state.Main;

/**
 * chef boy character 
 * @author leoze
 */
public class ChefBoy extends Character implements Action, ChangeHealth{
    
    public CharacterControl user; // object for controling player
    
    private Player player;
    
    private Vector3f walkDirection = new Vector3f(); // direction of walking (change in position, not current position)
    
    
    public ChefBoy(Main main, Vector3f position, String name, int health){
        
        super(main, position, name, health);
        
        this.player = main.gameState.getPlayer();
        
        init();
        
    }
    

    @Override
    void init() {
        initCollision();
        setPosition();
    }
    
    /**
     * create collision hit box
     * add gravity and physics to player 
     */
    public void initCollision(){
        
        CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(1.5f, 6f, 1);
        user = new CharacterControl(capsuleShape, 0.05f);
        user.setJumpSpeed(60);
        user.setFallSpeed(60);
        
        main.gameState.bulletAppState.getPhysicsSpace().add(user);
        
        user.setGravity(new Vector3f(0,-60f,0));
        
       
    }
    
    /**
     * set position of player
     */
    public void setPosition() {
        user.setPhysicsLocation(new Vector3f(-40, 20, 0));
    }
    
    
    @Override
    public void move(){

        walkDirection.set(0, 0, 0); // reset walk direction change 
        
        if (player.isLeft()) {
            walkDirection.addLocal(player.getCamLeft());
        }
        if (player.isRight()) {
            walkDirection.addLocal(player.getCamLeft().negate());
        }
        if (player.isForward()) {
            walkDirection.addLocal(player.getCamDir());
        }
        if (player.isBack()) {
            walkDirection.addLocal(player.getCamDir().negate());
        }
        if (player.isJump() && user.onGround()) {
            user.jump(new Vector3f(0, 20f, 0));
        }
        
        player.setJump(false); // reset player jump
        
        walkDirection.y = 0; // make sure player does not increase in y axis (up)
        
        user.setWalkDirection(walkDirection);
        main.getCamera().setLocation(user.getPhysicsLocation()); // update camera position to player position
        
        setPosition(user.getPhysicsLocation());
        
    }

    @Override
    public void attack(Character character) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addHealth(int amount) {
        
    }

    @Override
    public void removeHealth(int amount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
