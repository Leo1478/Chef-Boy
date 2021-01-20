/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.state;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Spatial;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import mygame.gameobject.ChefBoy;
import mygame.gameobject.Enemy;
import mygame.gameobject.GameLight;
import mygame.gameobject.GameObject;
import mygame.gameobject.Ham;
import mygame.gameobject.Hut;
import ui.Inventory;
import mygame.gameobject.Item;
import mygame.gameobject.Jelly;
import mygame.gameobject.Pan;
import mygame.gameobject.Pig;
import mygame.gameobject.Player;
import mygame.gameobject.Prop;
import mygame.gameobject.Slime;
import mygame.gameobject.Terrain;
import mygame.gameobject.Tree;
import mygame.gameobject.Volcano;
import ui.GameStateManager;
import ui.HeadsUpDisplay;

/**
 * state for in game init all game objects update all game objects
 *
 * @author leoze
 */
public class GameState extends State {

    private Inventory inventory; // inventory of items 
    private Queue<Enemy> enemyQueue = new LinkedList<>(); // queue to spawn enemies 
    private ArrayList<Item> items = new ArrayList<>(); // list to store all existing items
    private ArrayList<Enemy> enemies = new ArrayList<>(); // list to store enemies 
    private ArrayList<Prop> props = new ArrayList<>(); // list of all props 
    private HeadsUpDisplay hud;
    
    private float spawnRate = 10; // 10 seconds between each spawn 
    
    private GameLight gameLight; // lighting

    private Player player; // player object 
    private ChefBoy chefBoy;
    
    private GameStateManager gameStateManager;

    private BulletAppState bulletAppState; // controls physics 
    private AppStateManager stateManager; // controls states
    private SimpleApplication app; // main object, this is needed because Main extends SimpleApplication
                // SimpleApplication contains things like rootNode, camera, assetManager, etc
    

    /**
     * initialize
     * add enable state 
     *
     * @param stateManager
     * @param app
     */
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        
        this.app = (SimpleApplication) app;
        this.stateManager = stateManager;

    }
    /**
     * init all models, lighting, camera, physics, objects, and add them to game
     * world
     */
    @Override
    public void init(){
        
        bulletAppState = new BulletAppState(); // for physics 
        stateManager.attach(bulletAppState); // add bulletAppState into state manager
        
        app.getInputManager().setCursorVisible(false);
        
        initLight();
        initCamera();
        initTerrain();
        initProp();
        initSky();
        initChefBoy();
        initPlayer();
        initItem();
        initEnemy();
        initInventory();
        
        initHud();
        
        gameStateManager = new GameStateManager(app, stateManager, inventory);
    }
    
    private void initHud(){
        hud = new HeadsUpDisplay(app, chefBoy);
    }

    /**
     * init lighting for game
     *
     */
    private void initLight() {

        gameLight = new GameLight(app);
    }

    /**
     * change fly cam speed
     * camera is attached to player's head, so this doesn't matter for now
     * flycam still works if camera is not based on player position 
     */
    private void initCamera() {

        ((SimpleApplication)app).getFlyByCamera().setMoveSpeed(30); // not needed since camera is tied to player
    }

    /**
     * set sky color to light blue
     */
    private void initSky() {
        
        app.getViewPort().setBackgroundColor(new ColorRGBA(1f, 0.7f, 0.5f, 1f));
    }

    /**
     * init ground terrain a flat square for now TODO change terrain to
     * different model
     */
    private void initTerrain() {

        GameObject terrain = new Terrain( app, bulletAppState, new Vector3f(0, 0, 0), "terrain");
    }

    /**
     * init all props eg. trees
     */
    private void initProp() {
        
        Prop hut = new Hut(app, bulletAppState, new Vector3f(-205, 20, 208), "hut");
        props.add(hut);
        
        Prop tree0 = new Tree( app, bulletAppState, new Vector3f(260, -10, 193), "tree0");
        Prop tree1 = new Tree( app, bulletAppState, new Vector3f(205, -13, 223), "tree1");
        Prop tree2 = new Tree( app, bulletAppState, new Vector3f(105, 4, 154), "tree2");
        Prop tree3 = new Tree( app, bulletAppState, new Vector3f(41, -6, 164), "tree3");
        Prop tree4 = new Tree( app, bulletAppState, new Vector3f(-40, 7, 221), "tree4");
        Prop tree5 = new Tree( app, bulletAppState, new Vector3f(-112, -7, 191), "tree4");
        Prop tree6 = new Tree( app, bulletAppState, new Vector3f(-251, 15, 212), "tree4");
        Prop tree7 = new Tree( app, bulletAppState, new Vector3f(-304, 2, 162), "tree4");
        Prop tree8 = new Tree( app, bulletAppState, new Vector3f(-214, 2, 146), "tree4");
        Prop tree9 = new Tree( app, bulletAppState, new Vector3f(-112, -6, 153), "tree4");
        Prop tree10 = new Tree( app, bulletAppState, new Vector3f(99, -4, 122), "tree4");
        Prop tree11 = new Tree( app, bulletAppState, new Vector3f(204, -15, 104), "tree4");
        Prop tree12 = new Tree( app, bulletAppState, new Vector3f(260, 10, 11), "tree4");
        Prop tree13 = new Tree( app, bulletAppState, new Vector3f(192, -6, -49), "tree4");
        Prop tree14 = new Tree( app, bulletAppState, new Vector3f(108, 5, -42), "tree4");
        Prop tree15 = new Tree( app, bulletAppState, new Vector3f(38, -4, -21), "tree4");
        Prop tree16 = new Tree( app, bulletAppState, new Vector3f(-78, -9, -16), "tree4");
        Prop tree17 = new Tree( app, bulletAppState, new Vector3f(-236, 10, 35), "tree4");
        Prop tree18 = new Tree( app, bulletAppState, new Vector3f(-279, 14, -82), "tree4");
        Prop tree19 = new Tree( app, bulletAppState, new Vector3f(-119, -7, -161), "tree4");
        Prop tree20 = new Tree( app, bulletAppState, new Vector3f(53, 6, -152), "tree4");
        Prop tree21 = new Tree( app, bulletAppState, new Vector3f(252, -17, -184), "tree4");
        Prop tree22 = new Tree( app, bulletAppState, new Vector3f(295, -12, -345), "tree4");
        Prop tree23 = new Tree( app, bulletAppState, new Vector3f(201, -9, -366), "tree4");
        Prop tree24 = new Tree( app, bulletAppState, new Vector3f(33, -6, -363), "tree4");
        Prop tree25 = new Tree( app, bulletAppState, new Vector3f(-185, 18, -318), "tree4");


        props.add(tree0);
        props.add(tree1);
        props.add(tree2);
        props.add(tree3);
        props.add(tree4);
        props.add(tree5);
        props.add(tree6);
        props.add(tree7);
        props.add(tree8);
        props.add(tree9);
        props.add(tree10);
        props.add(tree11);
        props.add(tree12);
        props.add(tree13);
        props.add(tree14);
        props.add(tree15);
        props.add(tree16);
        props.add(tree17);
        props.add(tree18);
        props.add(tree19);
        props.add(tree20);
        props.add(tree21);
        props.add(tree22);
        props.add(tree23);
        props.add(tree24);
        props.add(tree25); 

        Prop volcano0 = new Volcano( app, bulletAppState, new Vector3f(150, 0, 0), "volcano0");
        props.add(volcano0);
        Prop volcano1 = new Volcano( app, bulletAppState, new Vector3f(-250, 5, -200), "volcano1");
        props.add(volcano1);
    }
    
    private void initChefBoy(){
        chefBoy = new ChefBoy( app, bulletAppState, new Vector3f(-205, 30, 208), "chefBoy", 100);
    }

    /**
     * init chefBoy object
     */
    private void initPlayer() {

        player = new Player( app, chefBoy);
        
        Pan pan = new Pan(app, new Vector3f(0, 0, 0), "pan");
        pan.getModel().setLocalScale(10);
    }
    
    /**
     * init all item objects
     */
    private void initItem() {
        Item ham0 = new Ham( app, new Vector3f(5, 0, 5), "ham0");
        items.add(ham0);
    }

    /**
     * init all enemy objects
     */
    private void initEnemy() {
        
        Enemy pig0 = new Pig( app, bulletAppState, new Vector3f(100, 20, 100), "pig0", 20);
        enemyQueue.add(pig0);
        Enemy pig1 = new Pig( app, bulletAppState, new Vector3f(90, 20, 80), "pig1", 20);
        enemyQueue.add(pig1);
        Enemy pig2 = new Pig( app, bulletAppState, new Vector3f(90, 20, 120), "pig2", 20);
        enemyQueue.add(pig2);
        Enemy pig3 = new Pig( app, bulletAppState, new Vector3f(130, 20, 110), "pig3", 20);
        enemyQueue.add(pig3);
        Enemy pig4 = new Pig( app, bulletAppState, new Vector3f(80, 20, 100), "pig4", 20);
        enemyQueue.add(pig4);
        Enemy pig5 = new Pig( app, bulletAppState, new Vector3f(30, 20, 20), "pig5", 20);
        enemyQueue.add(pig5);
        Enemy pig6 = new Pig( app, bulletAppState, new Vector3f(250, 20, 190), "pig6", 20);
        enemyQueue.add(pig6);
        Enemy pig7 = new Pig( app, bulletAppState, new Vector3f(-210, 20, -250), "pig7", 20);
        enemyQueue.add(pig7);
        Enemy pig8 = new Pig( app, bulletAppState, new Vector3f(-260, 20, -250), "pig8", 20);
        enemyQueue.add(pig8);
        Enemy pig9 = new Pig( app, bulletAppState, new Vector3f(-180, 20, -170), "pig9", 20);
        enemyQueue.add(pig9);
        Enemy pig10 = new Pig( app, bulletAppState, new Vector3f(-220, 20, -190), "pig10", 20);
        enemyQueue.add(pig10);
        
        //Enemy slime0 = new Slime( app, bulletAppState, new Vector3f(100, 10, 100), "slime0", 20);
        //slime0.spawn();
        //enemies.add(slime0);
        
        Enemy slime0 = new Slime( app, bulletAppState, new Vector3f(210, 20, -200), "slime0", 10);
        enemyQueue.add(slime0);
        Enemy slime1 = new Slime( app, bulletAppState, new Vector3f(200, 20, -150), "slime0", 10);
        enemyQueue.add(slime1);
        Enemy slime2 = new Slime( app, bulletAppState, new Vector3f(250, 20, -180), "slime0", 10);
        enemyQueue.add(slime2);
        Enemy slime3 = new Slime( app, bulletAppState, new Vector3f(220, 20, -190), "slime0", 10);
        enemyQueue.add(slime3);
        Enemy slime4 = new Slime( app, bulletAppState, new Vector3f(210, 20, -220), "slime0", 10);
        enemyQueue.add(slime4);
        Enemy slime5 = new Slime( app, bulletAppState, new Vector3f(280, 20, -240), "slime0", 10);
        enemyQueue.add(slime5);
        Enemy slime6 = new Slime( app, bulletAppState, new Vector3f(240, 20, -230), "slime0", 10);
        enemyQueue.add(slime6);
        Enemy slime7 = new Slime( app, bulletAppState, new Vector3f(230, 20, -180), "slime0", 10);
        enemyQueue.add(slime7);
        Enemy slime8 = new Slime( app, bulletAppState, new Vector3f(220, 20, -150), "slime0", 10);
        enemyQueue.add(slime8);
        Enemy slime9 = new Slime( app, bulletAppState, new Vector3f(180, 20, -190), "slime0", 10);
        enemyQueue.add(slime9);
        Enemy slime10 = new Slime( app, bulletAppState, new Vector3f(190, 20, -160), "slime0", 10);
        enemyQueue.add(slime10);
        
        
        Enemy slime11 = new Slime( app, bulletAppState, new Vector3f(-200, 40, 200), "slime0", 10); // right beside chefBoy spawn 
        enemyQueue.add(slime11);
        
        
        for(int i = 0; i < 23; i++){ // spawn the first 30 enemies 
            Enemy enemy = enemyQueue.poll(); // remove from queue 
            enemy.spawn(); // spawn, make model visible 
            enemies.add(enemy); // add to enemy list 
        }
  
    }
    
    /**
     * init inventory 
     */
    private void initInventory(){
        inventory = new Inventory(app);
    }
    
    /**
     * game updates update enemy behaviour, enemy position, chef boy etc
     * main update loop for gameState 
     * @param tpf delta time
     */
    @Override
    public void update(float tpf) {
        
        playerBehaviour(tpf);
        enemyBehaviour(tpf);
        itemBehaviour(tpf);
        
        spawnEnemy(tpf);
        
        updateHUD();
        
        System.out.println(chefBoy.getPosition());
    }
    
    
    
    /**
     * behaviour for player & chefboy 
     * @param tpf 
     */
    private void playerBehaviour(float tpf){
        
        player.move(); // check move inputs 
        

        player.attack(enemies); // attack inputs 
        player.block(); // block inputs 
        
        for(Item i : items){
            chefBoy.pickUpItem(i); // pick up item 
        }
        
        chefBoy.behaviour(tpf); // rest of chefboy's behaviour 
        
    }
    
    /**
     * behaviour for all enemy in enemies list 
     * @param tpf 
     */
    private void enemyBehaviour(float tpf){
        
        for(int i = 0; i < enemies.size(); i++){
            
            Enemy current = enemies.get(i);
            
            current.behaviour(tpf, chefBoy);
            
            if(current.canAttack()){ // if ready to attack
                current.attack(chefBoy);
            }
            
            if(! current.getAlive()){ // if current enemy dies 
                
                enemies.remove(current); // remove from list 
                
                if(current instanceof Pig){
                    
                    Item item = new Ham(app, current.getPosition(), "ham"); // spawn cooresponding item 
                    items.add(item);
                }
                else if(current instanceof Slime){
                    
                    Item item = new Jelly(app, current.getPosition(), "jelly"); // spawn cooresponding item 
                    items.add(item);
                }

            }
        }
    }
    
    /**
     * behaviour of all items in list 
     * @param tpf 
     */
    private void itemBehaviour(float tpf){
        
        for(int i = 0; i < items.size(); i++){
            
            Item current = items.get(i);
            
            current.behaviour(chefBoy);
            
            if(current.getPickedUp()){ // if current is picked up 
                
                items.remove(current); // remove from list 
                inventory.add(current); // add to inventory
            }
        }
        
    }
    
    private void spawnEnemy(float tpf){
        if(spawnRate <= 0){
            if(enemyQueue.size() > 0){
                Enemy enemy = enemyQueue.poll(); // remove from queue 
                enemy.spawn(); // spawn, make model visible 
                enemies.add(enemy); // add to enemy list 
            }
            spawnRate = 10; // reset spawn rate
        }
        
        if(spawnRate > 0){
            spawnRate -= tpf;
        }
    }
    
    private void updateHUD(){
        hud.update();
    }
    
    
    
    /**
     * @return the inventory
     */
    public Inventory getInventory() {
        return inventory;
    }
    
    
    public void cleanUp(){
        app.getRootNode().detachAllChildren();
    }
}
