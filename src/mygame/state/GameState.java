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
import com.jme3.math.Vector3f;
import java.util.ArrayList;
import java.util.Queue;
import mygame.gameobject.ChefBoy;
import mygame.gameobject.Enemy;
import mygame.gameobject.GameLight;
import mygame.gameobject.GameObject;
import mygame.gameobject.Ham;
import mygame.gameobject.Inventory;
import mygame.gameobject.Item;
import mygame.gameobject.Pig;
import mygame.gameobject.Player;
import mygame.gameobject.Terrain;
import mygame.gameobject.Tree;
import mygame.gameobject.Volcano;

/**
 * state for in game init all game objects update all game objects
 *
 * @author leoze
 */
public class GameState extends AbstractAppState {

    private Inventory inventory; // inventory of items 
    private Queue enemyQueue; // queue to spawn enemies 
    private ArrayList<Item> items = new ArrayList<>(); // list to store all existing items
    private ArrayList<Enemy> enemies = new ArrayList<>(); // list to store enemies 
    private ArrayList<GameObject> props = new ArrayList<>(); // list of all props 
    
    
    private GameLight gameLight; // lighting

    private Player player; // player object 
    private ChefBoy chefBoy;

    public BulletAppState bulletAppState; // controls physics 

    private Application app; // main object, this is needed because Main extends SimpleApplication
                // SimpleApplication contains things like rootNode, camera, assetManager, etc
    


    /**
     * init all models, lighting, camera, physics, objects, and add them to game
     * world
     *
     * @param stateManager
     * @param app
     */
    @Override
    public void initialize(AppStateManager stateManager, Application app) {

        this.app = app;

        bulletAppState = new BulletAppState(); // for physics 
        stateManager.attach(bulletAppState); // add bulletAppState into state manager

        initLight();
        initCamera();
        initTerrain();
        initProp();
        initSky();
        initChefBoy();
        initPlayer();
        initItem();
        initEnemy();

    }

    /**
     * init lighting for game
     *
     */
    private void initLight() {

        gameLight = new GameLight((SimpleApplication)app);
    }

    /**
     * change fly cam speed
     * camera is attached to player's head, so this doesn't matter for now
     * flycam still works if camera is not based on player position 
     */
    private void initCamera() {

        // app.getFlyByCamera().setMoveSpeed(30);
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

        GameObject terrain = new Terrain((SimpleApplication) app, bulletAppState, new Vector3f(0, 0, 0), "terrain");
    }

    /**
     * init all props eg. trees
     */
    private void initProp() {
        
    
        GameObject tree0 = new Tree((SimpleApplication) app, bulletAppState, new Vector3f(5, 0, 20), "tree0");
        GameObject tree1 = new Tree((SimpleApplication) app, bulletAppState, new Vector3f(10, 0, 20), "tree1");
        GameObject tree2 = new Tree((SimpleApplication) app, bulletAppState, new Vector3f(15, 0, 20), "tree2");
        GameObject tree3 = new Tree((SimpleApplication) app, bulletAppState, new Vector3f(20, 0, 20), "tree3");
        GameObject tree4 = new Tree((SimpleApplication) app, bulletAppState, new Vector3f(25, 0, 20), "tree4");

        getProps().add(tree0);
        getProps().add(tree1);
        getProps().add(tree2);
        getProps().add(tree3);
        getProps().add(tree4);

        
        
        GameObject volcano0 = new Volcano((SimpleApplication) app, bulletAppState, new Vector3f(50, 0, 50), "volcano0");
        getProps().add(volcano0);
    }
    
    private void initChefBoy(){
        chefBoy = new ChefBoy((SimpleApplication) app, bulletAppState, new Vector3f(0, 0, 30), "chefBoy", 100);
    }

    /**
     * init chefBoy object
     */
    private void initPlayer() {

        player = new Player((SimpleApplication) app, chefBoy);
    }
    
    /**
     * init all item objects
     */
    private void initItem() {
        Item ham0 = new Ham((SimpleApplication) app, new Vector3f(5, 3, 5), "ham0");
        getItems().add(ham0);
    }

    private void initEnemy() {
        
        Enemy pig0 = new Pig((SimpleApplication) app, new Vector3f(100, 0, 50), "pig0", 20);
        getEnemies().add(pig0);
        Enemy pig1 = new Pig((SimpleApplication) app, new Vector3f(60, 0, 80), "pig1", 20);
        getEnemies().add(pig1);
        Enemy pig2 = new Pig((SimpleApplication) app, new Vector3f(90, 0, 12), "pig2", 20);
        getEnemies().add(pig2);
        Enemy pig3 = new Pig((SimpleApplication) app, new Vector3f(14, 0, 20), "pig3", 20);
        getEnemies().add(pig3);
        Enemy pig4 = new Pig((SimpleApplication) app, new Vector3f(0, 0, 10), "pig4", 20);
        getEnemies().add(pig4);
        Enemy pig5 = new Pig((SimpleApplication) app, new Vector3f(30, 0, 20), "pig5", 20);
        getEnemies().add(pig5);
        
    }

    /**
     * game updates update enemy behaviour, enemy position, chef boy etc
     *
     * @param tpf delta time
     */
    @Override
    public void update(float tpf) {
        
        player.move();
        
        chefBoy.behaviour(items, enemies);
       
        enemyBehaviour(tpf, player);
        itemBehaviour(tpf, player);
    }
    
    private void enemyBehaviour(float tpf, Player player){
        
        for(int i = 0; i < getEnemies().size(); i++){
            getEnemies().get(i).behaviour(tpf, chefBoy);
        }
    }
    
    private void itemBehaviour(float tpf, Player player){
        for(int i = 0; i < getItems().size(); i++){
            getItems().get(i).behaviour(chefBoy);
        }
    }

    /**
     * @return the inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * @return the enemyQueue
     */
    public Queue getEnemyQueue() {
        return enemyQueue;
    }

    /**
     * @return the items
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * @return the enemies
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * @return the props
     */
    public ArrayList<GameObject> getProps() {
        return props;
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

}
