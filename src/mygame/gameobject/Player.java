package mygame.gameobject;


import com.jme3.app.Application;
import com.jme3.audio.AudioData;
import com.jme3.audio.AudioNode;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import com.jme3.input.controls.MouseButtonTrigger;
import java.util.ArrayList;

/**
 * Player.java
 * player inputs and player camera 
 * @author Leo Zeng
 * 2020/12/25
 */
public class Player implements ActionListener{
    
    private boolean left = false; // movement input
    private boolean right = false;
    private boolean forward = false;
    private boolean back = false;
    private boolean jump = false;
    private boolean mouseLeft = false; // mouse input
    private boolean mouseRight = false;
    
    private Vector3f camDir = new Vector3f(); // camera direction
    private Vector3f camLeft = new Vector3f(); // camera's left direction (for left and right movement) 
    
    private Vector3f position = new Vector3f(); // current player position 
    
    private Application app;
    private ChefBoy chefBoy;
    
    private AudioNode panSound;
    
    /**
     * Player
     * constructor
     * @param app application 
     * @param chefBoy chefBoy to control 
     */
    public Player(Application app, ChefBoy chefBoy){
        
        this.app = app;
        this.chefBoy = chefBoy;
        
        init();
    }
    
    /**
     * init 
     * initialise 
     */
    private void init() {
        
        setKeys();
        
    }
    
    /**
     * setKeys
     * set up binding for movement keys 
     * W = up
     * A = left 
     * S = down 
     * D = right 
     * space = jump
     * mouseLeft = attack
     * mouseRight = block
     */
    private void setKeys(){
        app.getInputManager().addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
        app.getInputManager().addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
        app.getInputManager().addMapping("Forward", new KeyTrigger(KeyInput.KEY_W));
        app.getInputManager().addMapping("Back", new KeyTrigger(KeyInput.KEY_S));
        app.getInputManager().addMapping("Jump", new KeyTrigger(KeyInput.KEY_SPACE));
        
        app.getInputManager().addMapping("MouseLeft", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        app.getInputManager().addMapping("MouseRight", new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
    }

    /**
     * onAction
     * method from ActionListener 
     * if key is pressed, change to true
     * @param binding key binding 
     * @param isPressed if key is pressed 
     * @param tpf time per frame
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
     * move
     * get inputs and call chefBoy's move 
     */
    public void move(){
        
        camDir.set(app.getCamera().getDirection()).multLocal(0.6f); // forward speed 
        camLeft.set(app.getCamera().getLeft()).multLocal(0.4f); // left speed 
        
        Vector3f change = new Vector3f(0, 0, 0); // change in position
        
        if (left) { // update change variable based on input
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
        
        setCamPosition(); // update camera position 
    }
    /**
     * attack
     * attack enemies 
     * @param enemies list of enemies to attack 
     */
    public void attack(ArrayList<Enemy> enemies){
        
        if(mouseLeft && ! mouseRight){ // if pressing left mouse
            if(chefBoy.canAttack()){ // if no cool down           
                for(Enemy e : enemies){
                    chefBoy.attack(e); // attack enemy 
                }
                chefBoy.setCoolDown(chefBoy.getAttackSpeed()); // reset cooldown 
                playSoundEffect();
            }
        }
        
    }
    
    /**
     * block
     * set chefboy's blocking based on mouseRight
     */
    public void block(){
        chefBoy.setBlock(mouseRight);
    }
    
    /**
     * setCamPosition
     * set camera position based on chefBoy's position 
     */
    private void setCamPosition(){
        
        position = chefBoy.getPosition();
        app.getCamera().setLocation(position); // update camera position to player position
    }
    
    public void playSoundEffect(){
        panSound = new AudioNode(app.getAssetManager(), "Sounds/Frying Pan 2.ogg",AudioData.DataType.Buffer);
        panSound.setDirectional(false);
        panSound.setPositional(false);
        panSound.play();
        
    }

}
