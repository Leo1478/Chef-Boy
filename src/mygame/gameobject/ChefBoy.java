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
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import java.util.ArrayList;
import mygame.state.Main;
import ui.Inventory;

/**
 * chef boy character 
 * @author leoze
 */
public class ChefBoy extends Character{
    
    private Vector3f walkDirection = new Vector3f(); // direction of walking (change in position, not current position)
    
    private boolean blocking; // if chefboy is blocking hits 
    
    
    public ChefBoy(SimpleApplication app, BulletAppState bulletAppState, Vector3f position, String name, int health){
        
        super(app, bulletAppState, position, name, health);
        setDamage(10);
        setAttackSpeed(2);
        setCoolDown(2);
        setRange(10);
        setAlive(true);
        setHealth(20);
        setDamage(10);
        setSpeed(1);

        setPreviousState(CharacterState.ATTACKING);
        setState(CharacterState.ATTACKING);
        
        init();
        initAnimation();
        
    }
    

    @Override
    void init() {
        
        setModel(app.getAssetManager().loadModel("Models/pig/Pig.mesh.xml"));

        getModel().setShadowMode(RenderQueue.ShadowMode.Off);
        
        app.getRootNode().attachChild(getModel());   
        
        initCollision();
        setPhysicsPosition();
    }
    
    /**
     * create collision hit box
     * add gravity and physics to player 
     */
    @Override
    public void initCollision(){
        
        CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(1.5f, 6f, 1);
        setCharacterControl(new CharacterControl(capsuleShape, 0.05f));
        getCharacterControl().setJumpSpeed(60);
        getCharacterControl().setFallSpeed(60);
        
        bulletAppState.getPhysicsSpace().add(getCharacterControl());
        
        getCharacterControl().setGravity(new Vector3f(0,-60f,0));
        
       
    }
    
    
    public void behaviour(float tpf, ArrayList<Item> items, ArrayList<Enemy> enemies){
        
        super.behaviour(tpf);
        

        for(Item i : items){
            pickUpItem(i);
        }
        
        for(Enemy e : enemies){
            
        }
        
    }
    
    
    /**
     * set position of player
     */
    private void setPhysicsPosition() {
        getCharacterControl().setPhysicsLocation(getPosition());

    }
    
    private void pickUpItem(Item item){
        
        double distance;
        
        double x = this.getPosition().x;
        double x1 = item.getPosition().x;
        double z = this.getPosition().z;
        double z1 = item.getPosition().z;
        distance = Math.sqrt(Math.pow(x1-x, 2) + Math.pow(z1-z, 2));
        
        if (item.getPickUpRadius() > distance){
            System.out.println("picked up item");
            item.deleteModel();
            item.pickedUp();
            // also need to remove from item list 
            //call on item's method 
        }
        
    }
    
    private void addToInventory(Inventory inventory, Item item){
        
    }
    

    
    public void jump(){
        
        if(getCharacterControl().onGround()){
            getCharacterControl().jump(new Vector3f(0, 20f, 0)); 
        }
    }
    

    public void block(){
        
    }
   
}
