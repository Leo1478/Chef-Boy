/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import java.util.ArrayList;
import mygame.state.Main;
import ui.Inventory;

/**
 * chef boy character
 *
 * @author leoze
 */
public class ChefBoy extends Character {

    private Vector3f walkDirection = new Vector3f(); // direction of walking (change in position, not current position)

    private boolean block; // if chefboy is blocking hits 

    private Pan pan;

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

    private void initPan() {
        pan = new Pan(app, new Vector3f(getPosition()), "pan");
    }

    /**
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
     * rest of chefboy's behaviour
     *
     * @param tpf
     */
    public void behaviour(float tpf) {

        super.behaviour(tpf);

        setModelPosition(); // set model to current position 
        setPanPosition();

        setAnimation();

    }
    
    private void setPanPosition(){
        getPan().setPosition(this.getPosition());

        Quaternion rotation = new Quaternion();

        rotation = app.getCamera().getRotation();

        getPan().setRotation(rotation);
        getPan().setModelRotation();
        getPan().setModelPosition();

        float[] angles = new float[3];

        Quaternion roatation = new Quaternion();
        roatation.fromAngles(0, rotation.toAngles(angles)[1] * -1, 0); // y rotation

        setRotation(roatation);
    }

    private void setAnimation() {
        
        if(block){
            pan.setAnimationBlock();
        }
        else if (getState() == CharacterState.ATTACKING && getCoolDown() <= 0) {
            pan.setAnimationAttack();
        } 
        else if (getCoolDown() <= 0) {
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
     * check if chef boy can pick up an item if item is in range, it will be
     * picked up
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

    public void setBlock(boolean block) {
        this.block = block;
    }

    @Override
    public void takeDamage(int amount) {
        if (!block) {
            super.takeDamage(amount);
        } else {
            // block = false;
        }
    }

    /**
     * @return the pan
     */
    public Pan getPan() {
        return pan;
    }

}
