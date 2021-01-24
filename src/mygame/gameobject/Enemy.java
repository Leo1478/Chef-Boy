package mygame.gameobject;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

/**
 * Enemy.java 
 * enemy that exist in game 
 * @author Leo Zeng
 * 2020/12/30
 */
public abstract class Enemy extends Character{
    
    private double detectionRange; // range to detect player 
    
    private double distanceToChef;
    
    /**
     * Enemy 
     * constructor
     * @param app application 
     * @param bulletAppState physics 
     * @param position current position 
     * @param name name of object 
     * @param health current health
     */
    public Enemy(SimpleApplication app, BulletAppState bulletAppState, Vector3f position, String name, int health){
        super(app, bulletAppState, position, name, health);
          
    }
    
    /**
     * behaviour 
     * behaviour of enemy 
     * calls other enemy methods 
     * @param tpf time per frame 
     * @param chefBoy chefBoy to interact with 
     */
    public void behaviour(float tpf, ChefBoy chefBoy){
        
        super.behaviour(tpf);
        
        determineDistance(chefBoy); // find distance to chefBoy
        
        determinState();

        move(new Vector3f(0, 0, 0)); // calling move with 0 change will make sure gravity works as intended 
              
        switch (getState()) { // idle. move, or attack depending on state 
            case IDLE:
                idle();
                break;
            case MOVING:
                charge(tpf, chefBoy);
                break;
            case ATTACKING:
                if(canAttack()){ // if ready to attack
                    attack(chefBoy);
                }
                break;
            default:
                break;
        }
        setModelPosition();
    }
    
    /**
     * determineDistance 
     * determine distance to chefBoy
     * @param chefBoy chefBoy to interact with 
     */
    private void determineDistance(ChefBoy chefBoy){

        double x = this.getPosition().x;
        double x1 = chefBoy.getPosition().x;
        double z = this.getPosition().z;
        double z1 = chefBoy.getPosition().z;
        
        distanceToChef = Math.sqrt(Math.pow(x1-x, 2) + Math.pow(z1-z, 2)); // pythagorean theorm
    }
    
    /**
     * determinstate
     * determin state based on distance to chefBoy
     * if in detectionRange, set state to charge 
     * if in attackRange, set state to attack 
     * otherwise set state to idle 
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
     * idle
     * idle mode for enemy 
     */
    private void idle(){
        //System.out.println("chilling");
        //getRigidBody().setKinematic(false);
    }
    
    /**
     * charge
     * charge chef boy 
     * find location of chefBoy, move towards chefBoy
     * @param chefBoy chefBoy to interact with
     */
    private void charge(float tpf, ChefBoy chefBoy){
        //getRigidBody().setKinematic(true);
        
        Vector3f change = new Vector3f(); // change in position 
        
        double xDiff = this.getPosition().x - chefBoy.getPosition().x; // find difference in position 
        double zDiff = this.getPosition().z - chefBoy.getPosition().z; 

        // normalizeNumber adjust speed to be constant 
        double normalizeNumber = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(zDiff, 2));

        xDiff = xDiff / normalizeNumber;
        zDiff = zDiff / normalizeNumber;
        
        change.x -= xDiff * getSpeed();
        change.z -= zDiff * getSpeed();
        change.y = 1;
        
        move(change); // move enemy 
        
        // rotate enemy 
        float radian = (float) Math.atan2(zDiff, xDiff); // find out radian between player and enemy 
        Quaternion rotation = new Quaternion();
        rotation.fromAngles(0, radian*-1, 0); // y rotation
        
        setRotation(rotation);
        getModel().setLocalRotation(rotation); // change model rotation 

        move(change);
        
        //System.out.println(getRigidBody().getPhysicsLocation());
        
    }
    
    /**
     * spawn
     * spawn enemy 
     * add model to rootNode to be displayed
     */
    public void spawn(){
        app.getRootNode().attachChild(getModel());
    }
    
    /**
     * getDectionRange
     * @return the detectionRange
     */
    public double getDetectionRange() {
        return detectionRange;
    }

    /**
     * setDetectionRange
     * @param detectionRange the detectionRange to set
     */
    public void setDetectionRange(double detectionRange) {
        this.detectionRange = detectionRange;
    }
    
    
}
