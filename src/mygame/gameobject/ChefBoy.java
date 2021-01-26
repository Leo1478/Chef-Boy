package mygame.gameobject;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;

/**
 * ChefBoy.java
 * chef boy character
 * character the player controls
 * @author Leo Zeng, Ariana Hou, William Zhao
 * 2020/12/30
 */
public class ChefBoy extends Character {


    private boolean block; // if chefboy is blocking hits 

    private Pan pan; // pan chefBoy holds 

    /**
     * ChefBoy
     * constructor set all values 
     * @param app application 
     * @param bulletAppState bulletAppState for physics 
     * @param position current position 
     * @param name name
     * @param health current health
     */
    public ChefBoy(SimpleApplication app, BulletAppState bulletAppState, Vector3f position, String name, int health) {

        super(app, bulletAppState, position, name, health);
        setDamage(10);
        setAttackSpeed(1);
        setCoolDown(2);
        setRange(10);
        setAlive(true);
        setHealth(100);
        setDamage(10);
        setSpeed(1);

        setPreviousState(CharacterState.ATTACKING);
        setState(CharacterState.ATTACKING);

        init();
        initAnimation();

    }

    /**
     * init 
     * initialise model and physics 
     */
    @Override
    public void init() {

        // since chef boy has no model, just use a cube as place holder
        setModel(app.getAssetManager().loadModel("Models/cube/Cube.mesh.j3o"));
        getModel().setShadowMode(RenderQueue.ShadowMode.Off);
        app.getRootNode().attachChild(getModel());

        initCollision();
        setPhysicsPosition();
        initPan();
    }

    /**
     * initPan
     * initialise pan object 
     */
    private void initPan() {
        pan = new Pan(app, new Vector3f(getPosition()), "pan");
    }

    /**
     * initCollision
     * create collision hit box add gravity and physics to player
     */
    @Override
    public void initCollision() {

        CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(1.5f, 6f, 1);
        setCharacterControl(new CharacterControl(capsuleShape, 0.05f));
        getCharacterControl().setJumpSpeed(60);
        getCharacterControl().setFallSpeed(60);

        bulletAppState.getPhysicsSpace().add(getCharacterControl());

        getCharacterControl().setGravity(new Vector3f(0, -60f, 0));
    }

    /**
     * behaviour
     * rest of chefboy's behaviour
     *
     * @param tpf time per frame
     */
    @Override
    public void behaviour(float tpf) {

        super.behaviour(tpf);

        setModelPosition(); // set model to current position 
        setPanPosition();

        setAnimation();
    }

    /**
     * setPanPosition
     * set pan position to chefBoy position
     */
    private void setPanPosition() {
        
        getPan().setPosition(this.getPosition()); // set pan position to chefBoy position

        Quaternion rotation = new Quaternion();

        rotation = app.getCamera().getRotation();

        getPan().setRotation(rotation);
        getPan().setModelRotation();
        getPan().setModelPosition();

        float[] angles = new float[3];

        Quaternion rotation1 = new Quaternion();
        rotation1.fromAngles(0, rotation1.toAngles(angles)[1] * -1, 0); // y rotation

        setRotation(rotation1);
    }

    /**
     * setAnimation
     * set pan animation based on chefBoy 
     */
    private void setAnimation() {

        if (block) {
            pan.setAnimationBlock();
        } else if (getState() == CharacterState.ATTACKING && getCoolDown() <= 0) {
            pan.setAnimationAttack();
        } else if (getCoolDown() <= 0) {
            pan.setAnimationIdle();
        }

    }

    /**
     * set position of player
     */
    private void setPhysicsPosition() {
        getCharacterControl().setPhysicsLocation(getPosition());
    }

    /**
     * pickUpItem
     * check if chef boy can pick up an item if item is in range, it will be picked up
     *
     * @param item item to pick up
     */
    public void pickUpItem(Item item) {

        double distance;

        double x = this.getPosition().x;
        double x1 = item.getPosition().x;
        double z = this.getPosition().z;
        double z1 = item.getPosition().z;
        distance = Math.sqrt(Math.pow(x1 - x, 2) + Math.pow(z1 - z, 2)); // find distance to player 

        if (item.getPickUpRadius() > distance) { // if in pickUpRadius 
            item.pickedUp(); // pick up the item 
        }
    }

    /**
     * chef boy jump
     */
    public void jump() {

        if (getCharacterControl().onGround()) { // if chefboy is on ground 
            getCharacterControl().jump(new Vector3f(0, 20f, 0)); // add jump to characterControl
        }
    }

    /**
     * setBlock
     * @param block if chefBoy is blocking 
     */
    public void setBlock(boolean block) {
        this.block = block;
    }

    /**
     * takeDamage
     * check if chefBoy should take damage
     * @param amount amount to decrease 
     */
    @Override
    public void takeDamage(int amount) {
        if (block) { // if blocking
            super.takeDamage(amount/2);
        } else{ //not blocking
            super.takeDamage(amount); // take damage
        }
    }

    /**
     * getPan
     * @return pan
     */
    public Pan getPan() {
        return pan;
    }
}