/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;


import com.jme3.app.Application;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import mygame.state.Main;
import com.jme3.input.controls.MouseButtonTrigger;

/**
 *
 * @author leoze
 */
public class Player implements ActionListener{
    
    private boolean playerPickUp;
    
    private boolean left = false;
    private boolean right = false;
    private boolean forward = false;
    private boolean back = false;
    private boolean jump = false;
    
    private Vector3f camDir = new Vector3f(); // camera direction
    private Vector3f camLeft = new Vector3f(); // camera's left direction (for left and right movement) 
    
    private Vector3f position = new Vector3f(); // current player position 
    
    Application app;
    
    ChefBoy chefBoy;
    
    public Player(Application app, ChefBoy chefBoy){
        
        this.app = app;
        
        this.chefBoy = chefBoy;
        
        init();
        
    }
    
    /**
     * init model, collision, position
     */
    
    private void init() {
        
        setKeys();
        
    }
    
    /**
     * set up binding for movement keys 
     * W = up
     * A = left 
     * S = down 
     * D = right 
     */
    private void setKeys(){
        app.getInputManager().addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
        app.getInputManager().addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
        app.getInputManager().addMapping("Forward", new KeyTrigger(KeyInput.KEY_W));
        app.getInputManager().addMapping("Back", new KeyTrigger(KeyInput.KEY_S));
        app.getInputManager().addMapping("Jump", new KeyTrigger(KeyInput.KEY_SPACE));
        app.getInputManager().addListener(this, "Left");
        app.getInputManager().addListener(this, "Right");
        app.getInputManager().addListener(this, "Forward");
        app.getInputManager().addListener(this, "Back");
        app.getInputManager().addListener(this, "Jump");
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
            setLeft(isPressed);
        } else if (binding.equals("Right")) {
            setRight(isPressed);
        } else if (binding.equals("Forward")) {
            setUp(isPressed);
        } else if (binding.equals("Back")) {
            setDown(isPressed);
        } else if (binding.equals("Jump")) {
            setJump(true);
        }
        
    }
    
    /**
     * update movement of player
     */
    public void move(){
        
        camDir.set(app.getCamera().getDirection()).multLocal(0.6f);
        camLeft.set(app.getCamera().getLeft()).multLocal(0.4f);
        
        
        Vector3f change = new Vector3f(0, 0, 0);
        
        if (left) {
            change.x += getCamLeft().x;
            change.z += getCamLeft().z;
        }
        if (right) {
            change.x += getCamLeft().negate().x;
            change.z += getCamLeft().negate().z;
        }
        if (forward) {
            change.x += getCamDir().x;
            change.z += getCamDir().z;
        }
        if (back) {
            change.x += getCamDir().negate().x;
            change.z += getCamDir().negate().z;
        }

        chefBoy.move(change); // call chefBoy's method to move 
        
        if (jump) {
            chefBoy.jump();
            jump = false;
        }
        
        setPosition();
        
    }
    
    private void setPosition(){
        
        position = chefBoy.getPosition();
        app.getCamera().setLocation(position); // update camera position to player position
    }
    
    /*
    void checkPickUp (Player player){
        user.getPhysicsLocation();
       
        
    }
    void pickUpMove (Player player){
        if (isPlayerPickUp() == true){
            double xDiff = this.getPosition().x - player.getPosition().x;
            double zDiff = this.getPosition().z - player.getPosition().z;
            
            double normalizeNumber = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(zDiff, 2));
            
            xDiff = xDiff / normalizeNumber;
            zDiff = zDiff / normalizeNumber;
            
            
            setPosition();
        }
    }
    
    public Vector3f getPosition(){
        return position;
    }
    */

    /**
     * @return the playerPickUp
     */
    public boolean isPlayerPickUp() {
        return playerPickUp;
    }

    /**
     * @param playerPickUp the playerPickUp to set
     */
    public void setPlayerPickUp(boolean playerPickUp) {
        this.playerPickUp = playerPickUp;
    }

    /**
     * @return the left
     */
    public boolean isLeft() {
        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(boolean left) {
        this.left = left;
    }

    /**
     * @return the right
     */
    public boolean isRight() {
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(boolean right) {
        this.right = right;
    }

    /**
     * @return the up
     */
    public boolean isForward() {
        return forward;
    }

    /**
     * @param forward the up to set
     */
    public void setUp(boolean forward) {
        this.forward = forward;
    }

    /**
     * @return the back
     */
    public boolean isBack() {
        return back;
    }

    /**
     * @param back the back to set
     */
    public void setDown(boolean back) {
        this.back = back;
    }

    /**
     * @return the jump
     */
    public boolean isJump() {
        return jump;
    }

    /**
     * @param jump the jump to set
     */
    public void setJump(boolean jump) {
        this.jump = jump;
    }

    /**
     * @return the camDir
     */
    public Vector3f getCamDir() {
        return camDir;
    }

    /**
     * @param camDir the camDir to set
     */
    public void setCamDir(Vector3f camDir) {
        this.camDir = camDir;
    }

    /**
     * @return the camLeft
     */
    public Vector3f getCamLeft() {
        return camLeft;
    }

    /**
     * @param camLeft the camLeft to set
     */
    public void setCamLeft(Vector3f camLeft) {
        this.camLeft = camLeft;
    }
    
    public Vector3f getPosition(){
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Vector3f position) {
        this.position = position;
    }
    
    // im going to comment this out for now
    /*
    public void getItem (Vector3f position){
        if (MouseButtonTrigger(MouseInput.BUTTON_LEFT) == true){
            
        }
    }
    */
    
}
