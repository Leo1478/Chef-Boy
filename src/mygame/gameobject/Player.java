/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;


import com.jme3.app.Application;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import mygame.state.Main;
import com.jme3.input.controls.MouseButtonTrigger;
import java.util.ArrayList;

/**
 *
 * @author leoze
 */
public class Player implements ActionListener{
    
    
    private boolean left = false;
    private boolean right = false;
    private boolean forward = false;
    private boolean back = false;
    private boolean jump = false;
    private boolean mouseLeft = false;
    private boolean mouseRight = false;
    
    private Vector3f camDir = new Vector3f(); // camera direction
    private Vector3f camLeft = new Vector3f(); // camera's left direction (for left and right movement) 
    
    private Vector3f position = new Vector3f(); // current player position 
    
    private Application app;
    
    private ChefBoy chefBoy;
    
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
        
        app.getInputManager().addMapping("MouseLeft", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        app.getInputManager().addMapping("MouseRight", new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
        app.getInputManager().addListener(this, "MouseLeft");
        app.getInputManager().addListener(this, "MouseRight");
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
        } else if (binding.equals("Forward")) {
            forward = isPressed;
        } else if (binding.equals("Back")) {
            back = isPressed;
        } else if (binding.equals("Jump")) {
            jump = isPressed;
        }
        
        if(binding.equals("MouseLeft")){
            mouseLeft = isPressed;
        } else if (binding.equals("MouseRight")){
            mouseRight = isPressed;
        }
        
    }
    
    /**
     * update movement of player
     */
    public void move(){
        
        camDir.set(app.getCamera().getDirection()).multLocal(0.6f); // forward speed 
        camLeft.set(app.getCamera().getLeft()).multLocal(0.4f); // left speed 
        
        Vector3f change = new Vector3f(0, 0, 0); // change in position
        
        if (left) {
            change.x += camLeft.x;
            change.z += camLeft.z;
        }
        if (right) {
            change.x += camLeft.negate().x;
            change.z += camLeft.negate().z;
        }
        if (forward) {
            change.x += camDir.x;
            change.z += camDir.z;
        }
        if (back) {
            change.x += camDir.negate().x;
            change.z += camDir.negate().z;
        }

        chefBoy.move(change); // call chefBoy's method to move 
        
        if (jump) {
            chefBoy.jump();
        }
        
        jump = false;
        
        setCamPosition();
    }
    
    public void attack(Enemy enemy){
        

        if(mouseLeft){
            chefBoy.attack(enemy);
        }

    }
    
    private void setCamPosition(){
        
        position = chefBoy.getPosition();
        app.getCamera().setLocation(position); // update camera position to player position
    }

}
