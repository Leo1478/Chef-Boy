package mygame.gameobject;

import com.jme3.anim.AnimComposer;
import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingBox;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;

/**
 * character.java
 * characters that can move and attack 
 * @author William Zhao, Leo Zeng
 * 2021/1/07
 */
public abstract class Character extends GameObject implements Action, ChangeHealth, Collidable{

    private boolean alive; // if character is alive 
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
    
    private Vector3f walkDirection; // direction of walking (change in position, not current position)
    
    /**
     * Character
     * constructor init health, state
     * @param app application 
     * @param bulletAppState controls physics 
     * @param position current position
     * @param name object name
     * @param health current health
     */
    public Character(SimpleApplication app, BulletAppState bulletAppState, Vector3f position, String name, int health){
        
        super(app, position, name);
        this.health = health;
        this.state = CharacterState.IDLE;
        this.previousState = CharacterState.IDLE;
        this.bulletAppState = bulletAppState;
        walkDirection = new Vector3f();
    }
    

    /**
     * initCollision
     * initialise collision of character 
     */
    public void initCollision() {

        Vector3f extent = ((BoundingBox) getModel().getWorldBound()).getExtent(new Vector3f());
        BoxCollisionShape collisionShape = new BoxCollisionShape(extent);
        characterControl = new CharacterControl(collisionShape, 0.05f);
        characterControl.setFallSpeed(10);
        bulletAppState.getPhysicsSpace().add(characterControl);
        characterControl.setGravity(new Vector3f(0, -60f, 0));
        getCharacterControl().setFallSpeed(60);
        characterControl.setPhysicsLocation(getPosition());

    }
    
    /**
     * initAnimation
     * initialise animation 
     * set to idle to start
     */
    public void initAnimation(){
        
        animComposer = getModel().getControl(AnimComposer.class);
        animComposer.setCurrentAction("Idle");
        
    }
    
    /**
     * updatePosition
     * update position based on physics location
     */
    public void updatePosition(){
        setPosition(characterControl.getPhysicsLocation());
    }
    
    
    /**
     * deleteCollision
     * remove from bulletAppState
     */
    public void deleteCharactercontrol(){
        bulletAppState.getPhysicsSpace().remove(characterControl);
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
     * behaviour
     * all behaviour for character 
     * @param tpf time per frame 
     */
    public void behaviour(float tpf){
        
        setCoolDown(getCoolDown() - tpf); // reduce attack cooldown timer 
        
        setAnimation(); // set current animation 
        
        checkDie(); // check if character should be dead 
        
        
    }
    
    /**
     * checkDie
     * check if character should be dead 
     */
    public void checkDie(){
        
        if(health <= 0){
            alive = false;
            deleteModel();
            deleteCharactercontrol();
        }
    }
    
    /**
     * setAnimation
     * change animation when state changes 
     */
    private void setAnimation(){
        
        if(previousState != state){
            
            switch (state) {
                case IDLE:
                    animComposer.setCurrentAction("Idle"); 
                    break;
                case MOVING:
                    animComposer.setCurrentAction("Moving");
                    break;
                case ATTACKING:
                    animComposer.setCurrentAction("Attacking");    
                    break;
                default:
                    break;
            }
            previousState = state;
        }
    }
    
    /**
     * canAttack
     * check if character can attack 
     * if in attacking state and no cooldown 
     * @return if character can attack
     */
    public boolean canAttack(){
        
        if(state == CharacterState.ATTACKING){
           if(coolDown <= 0){
               coolDown = attackSpeed;
               return true;
           } 
        }
        return false;

    }
    
    /**
     * attack 
     * @param character character to attack
     */
    @Override
    public void attack(Character character) {
        
        double x = this.getPosition().x; // get position of both character 
        double x1 = character.getPosition().x;
        double z = this.getPosition().z;
        double z1 = character.getPosition().z;

        double distance = Math.abs(Math.sqrt(Math.pow(x1-x, 2) + Math.pow(z1-z, 2))); // find distance to character 

        if(distance < getRange()){ // if character is within range 
                       
            character.takeDamage(damage);   
        }  
    }
    
    /**
     * addHealth 
     * @param amount amount to add 
     */
    @Override
    public void addHealth(int amount) {
        health += amount;
    }

    /**
     * remove health 
     * @param amount amount to remove 
     */
    @Override
    public void removeHealth(int amount) {
        health -= amount;
    }
    
    /**
     * takeDamage 
     * @param amount amount of damage
     */
    @Override
    public void takeDamage(int amount){
        health -= amount;
    }
    
    /**
     * getCharacterControl
     * @return characterControl
     */
    public CharacterControl getCharacterControl(){
        return characterControl;
    }
    
    /**
     * setCharacterControl
     * @param characterControl characterControl to set
     */
    public void setCharacterControl(CharacterControl characterControl){
        this.characterControl = characterControl;
    }
    

    /**
     * getHealth
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * setHealth
     * @param health the health to set
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * getState
     * @return the state
     */
    public CharacterState getState() {
        return state;
    }

    /**
     * setState
     * @param state the state to set
     */
    public void setState(CharacterState state) {
        this.state = state;
    }
    
    /**
     * setPreviousState
     * @param previousState
     */
    public void setPreviousState(CharacterState previousState) {
        this.state = previousState;
    }
    
    /**
     * getDamage
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * setDamage
     * @param damage the damage to set
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * getSpeed
     * @return the speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * setSpeed
     * @param speed the speed to set
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * getRange
     * @return the range
     */
    public double getRange() {
        return range;
    }

    /**
     * setRange
     * @param range the range to set
     */
    public void setRange(double range) {
        this.range = range;
    }

    /**
     * getCollisionMesh
     * @return the collisionMesh
     */
    public CollisionShape getCollisionMesh() {
        return collisionMesh;
    }

    /**
     * setCollisionMesh
     * @param collisionMesh the collisionMesh to set
     */
    public void setCollisionMesh(CollisionShape collisionMesh) {
        this.collisionMesh = collisionMesh;
    }

    /**
     * setRigidBody
     * @param rigidBody the rigidBody to set
     */
    public void setRigidBody(RigidBodyControl rigidBody) {
        this.rigidBody = rigidBody;
    }

    /**
     * getRigidBody
     * @return the rigidBody
     */
    public RigidBodyControl getRigidBody() {
        return rigidBody;
    }

    /**
     * setAttackSpeed
     * @param attackSpeed the attackSpeed to set
     */
    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    /**
     * setAttackSpeed
     * @param coolDown the coolDown to set
     */
    public void setCoolDown(float coolDown) {
        this.coolDown = coolDown;
    }

    /**
     * getCoolDown
     * @return the coolDown
     */
    public float getCoolDown() {
        return coolDown;
    }

    /**
     * getAttackSpeed
     * @return the attackSpeed
     */
    public float getAttackSpeed() {
        return attackSpeed;
    }

    /**
     * getAlive
     * @return the alive
     */
    public boolean getAlive() {
        return alive;
    }

    /**
     * setAlive
     * @param alive the alive to set
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
