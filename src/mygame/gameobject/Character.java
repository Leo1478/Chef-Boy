/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

import com.jme3.anim.AnimComposer;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingBox;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.math.Vector3f;
import mygame.state.Main;

/**
 * characters that can move and attack 
 * @author leoze
 */
public abstract class Character extends GameObject implements Action, ChangeHealth, Collidable{


    private boolean alive;
    private int health;
    private CharacterState state;
    private CharacterState previousState;
    private double speed;
    private double range;
    private int damage;
    private float attackSpeed; // interval between each attack
    private float coolDown; // time remaining on interval
    
    private AnimComposer animComposer; // animation 
    
    private CharacterControl characterControl;
    
    protected BulletAppState bulletAppState; 
    private CollisionShape collisionMesh; // mesh to map collision 
    private RigidBodyControl rigidBody; // rigidbody to simulate physical object 
    
    private Vector3f walkDirection = new Vector3f(); // direction of walking (change in position, not current position)
    
    public Character(SimpleApplication app, BulletAppState bulletAppState, Vector3f position, String name, int health){
        
        super(app, position, name);
        this.health = health;
        this.state = CharacterState.IDLE;
        this.previousState = CharacterState.IDLE;
        this.bulletAppState = bulletAppState;
        
    }
    

    
    @Override
    public void initCollision() {

        Vector3f extent = ((BoundingBox) getModel().getWorldBound()).getExtent(new Vector3f());
        BoxCollisionShape collisionShape = new BoxCollisionShape(extent);
        characterControl = new CharacterControl(collisionShape, 0.05f);
        characterControl.setFallSpeed(10);

        bulletAppState.getPhysicsSpace().add(characterControl);
        characterControl.setGravity(new Vector3f(0, -60f, 0));

        characterControl.setPhysicsLocation(getPosition());

        // init rigidbody here for all characters 
    }
    
    public void updatePosition(){
        setPosition(characterControl.getPhysicsLocation());
    }
    
    @Override
    public void updateCollision(){
        
    }
    
    @Override
    public void deleteCollision(){
        
    }
    
    /**
     * move 
     * move character control by certain position 
     * @param change vector3f of change in position 
     */
    @Override
    public void move(Vector3f change){
        walkDirection.set(0, 0, 0); // reset walk direction change 
        
        change.x = change.x * (float)speed; // multiply change by character's speed 
        change.z = change.z * (float)speed;
        
        walkDirection.addLocal(change); // add change
        
        walkDirection.y = 0; // make sure player does not increase in y axis (up)
        
        characterControl.setWalkDirection(walkDirection); // apply to characterControl 
 
        setPosition(characterControl.getPhysicsLocation()); // update position 
    }
    
    /**
     * all behaviour for character 
     * @param tpf 
     */
    public void behaviour(float tpf){
        
        setCoolDown(getCoolDown() - tpf); // reduce attack cooldown timer 
        
        setAnimation();
        
        checkDie();
        
    }
    
    /**
     * check if character should be dead 
     */
    public void checkDie(){
        
        if(health <= 0){
            alive = false;
            deleteModel();
        }
        
    }
    
    /**
     * change animation when state changes 
     */
    private void setAnimation(){
        
        if(previousState != state){
            
            switch (state) {
                case IDLE:
                    
                    System.out.println("animation");
                    animComposer.setCurrentAction("Idle");
                    break;
                case MOVING:
                    System.out.println("animation");
                    animComposer.setCurrentAction("Running");
                    break;
                case ATTACKING:
                    animComposer.setCurrentAction("Attack");    
                    break;
                default:
                    break;
            }
            previousState = state;
        }
    }
    
    /**
     * attack 
     * @param character character to attack
     */
    @Override
    public void attack(Character character) {
        
        if(state == CharacterState.ATTACKING){ // if character is in attacking state 
            
            //System.out.println(getCoolDown());
            
            if(getCoolDown() <= 0){ // if no cooldown, ready to attack    
                
                double x = this.getPosition().x;
                double x1 = character.getPosition().x;
                double z = this.getPosition().z;
                double z1 = character.getPosition().z;
        
                double distance = Math.abs(Math.sqrt(Math.pow(x1-x, 2) + Math.pow(z1-z, 2))); // find distance to character 
                
                if(distance < getRange()){ // if character is within range 
                    character.removeHealth(damage);
                    //System.out.println("attacking   " + character.getClass().toString());
                    coolDown = getAttackSpeed(); // reset cool down 
                }
                else{
                    //System.out.println("tried to attack" + character.getClass().toString());
                }
            }    
        }
    }
    
    @Override
    public void addHealth(int amount) {
        health += amount;
    }

    @Override
    public void removeHealth(int amount) {
        health -= amount;
    }
    
    @Override
    public void takeDamage(int amount){
        
    }
    
    public CharacterControl getCharacterControl(){
        return characterControl;
    }
    
    public void setCharacterControl(CharacterControl characterControl){
        this.characterControl = characterControl;
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

        /**
     * @return the collisionMesh
     */
    public CollisionShape getCollisionMesh() {
        return collisionMesh;
    }

    /**
     * @param collisionMesh the collisionMesh to set
     */
    public void setCollisionMesh(CollisionShape collisionMesh) {
        this.collisionMesh = collisionMesh;
    }

    /**
     * @param rigidBody the rigidBody to set
     */
    public void setRigidBody(RigidBodyControl rigidBody) {
        this.rigidBody = rigidBody;
    }

    /**
     * @return the rigidBody
     */
    public RigidBodyControl getRigidBody() {
        return rigidBody;
    }

    /**
     * @param attackSpeed the attackSpeed to set
     */
    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    /**
     * @param coolDown the coolDown to set
     */
    public void setCoolDown(float coolDown) {
        this.coolDown = coolDown;
    }

    /**
     * @return the coolDown
     */
    public float getCoolDown() {
        return coolDown;
    }

    private float getAttackSpeed() {
        return attackSpeed;
    }

    /**
     * @return the alive
     */
    public boolean getAlive() {
        return alive;
    }

    /**
     * @param alive the alive to set
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    
}
