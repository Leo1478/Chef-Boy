/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;


import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import mygame.state.Main;

/**
 *
 * @author leoze
 */
public class Player implements ActionListener{
    boolean playerPickUp;
    
    public CharacterControl user; // object for controling player
    
    public Vector3f walkDirection = new Vector3f(); // direction of walking (change in position, not current position)
    public boolean left = false, right = false, up = false, down = false; // movement
    
    private Vector3f camDir = new Vector3f(); // camera direction
    private Vector3f camLeft = new Vector3f();
    
    Vector3f position = new Vector3f(); // current player position 
    
    Main main;
    
    public Player(Main main){
        this.main = main;
        init();
        
    }
    
    /**
     * init model, collision, position
     */
    
    private void init() {
        
        setKeys();
        initCollision();
        setPosition();
        
        user.getWalkDirection();
        
        
    }

    /**
     * create collision hit box
     * add gravity and physics to player 
     */
    private void initCollision(){
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
    private void setPosition() {
        user.setPhysicsLocation(new Vector3f(-40, 10, 0));
    }
    
    /**
     * set up binding for movement keys 
     * W = up
     * A = left 
     * S = down 
     * D = right 
     */
    private void setKeys(){
        main.getInputManager().addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
        main.getInputManager().addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
        main.getInputManager().addMapping("Up", new KeyTrigger(KeyInput.KEY_W));
        main.getInputManager().addMapping("Down", new KeyTrigger(KeyInput.KEY_S));
        main.getInputManager().addMapping("Jump", new KeyTrigger(KeyInput.KEY_SPACE));
        main.getInputManager().addListener(this, "Left");
        main.getInputManager().addListener(this, "Right");
        main.getInputManager().addListener(this, "Up");
        main.getInputManager().addListener(this, "Down");
        main.getInputManager().addListener(this, "Jump");
    }

    /**
     * method from ActionListener 
     * if key is pressed, change to true
     * @param binding
     * @param isPressed
     * @param tpf 
     */
    @Override
    public void onAction(String binding, boolean isPressed, float tpf) {
        
        
        if (binding.equals("Left")) {
            left = isPressed;
        } else if (binding.equals("Right")) {
            right = isPressed;
        } else if (binding.equals("Up")) {
            up = isPressed;
        } else if (binding.equals("Down")) {
            down = isPressed;
        } else if (binding.equals("Jump")) {
            if (isPressed && user.onGround()) {
                user.jump(new Vector3f(0, 20f, 0));
            }
        }
    }
    
    /**
     * update movement of player
     */
    public void updateMovement(){
        
        camDir.set(main.getCamera().getDirection()).multLocal(0.6f);
        camLeft.set(main.getCamera().getLeft()).multLocal(0.4f);
        walkDirection.set(0, 0, 0);
        
        if (left) {
            walkDirection.addLocal(camLeft);
        }
        if (right) {
            walkDirection.addLocal(camLeft.negate());
        }
        if (up) {
            walkDirection.addLocal(camDir);
        }
        if (down) {
            walkDirection.addLocal(camDir.negate());
        }
        
        walkDirection.y = 0; // make sure player does not increase in y axis (up)
        
        user.setWalkDirection(walkDirection);
        main.getCamera().setLocation(user.getPhysicsLocation()); // update camera position to player position
        
        position = user.getPhysicsLocation();
        
    }
    void checkPickUp (Player player){
        user.getPhysicsLocation();
       
        
    }
    void pickUpMove (Player player){
        if (playerPickUp == true){
            double xDiff = this.position.x - player.position.x;
            double zDiff = this.position.z - player.position.z;
            
            double normalizeNumber = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(zDiff, 2));
            
            xDiff = xDiff / normalizeNumber;
            zDiff = zDiff / normalizeNumber;
            
            
            setPosition();
        }
    }
    
    public Vector3f getPosition(){
        return position;
    }
    
}
