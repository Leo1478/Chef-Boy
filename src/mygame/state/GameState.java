/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.state;

import com.jme3.app.Application;
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

    private Main main; // main object, this is needed because Main extends SimpleApplication
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

        this.main = (Main) app;

        bulletAppState = new BulletAppState(); // for physics 
        stateManager.attach(bulletAppState); // add bulletAppState into state manager

        initLight();
        initCamera();
        initTerrain();
        initProp();
        initSky();
        initPlayer();
        initChefBoy();
        initItem();
        initEnemy();

    }

    /**
     * init lighting for game
     *
     */
    void initLight() {

        gameLight = new GameLight(main);
    }

    /**
     * change fly cam speed
     * camera is attached to player's head, so this doesn't matter for now
     * flycam still works if camera is not based on player position 
     */
    void initCamera() {

        main.getFlyByCamera().setMoveSpeed(30);
    }

    /**
     * set sky color to light blue
     */
    void initSky() {
        
        main.getViewPort().setBackgroundColor(new ColorRGBA(1f, 0.7f, 0.5f, 1f));
    }

    /**
     * init ground terrain a flat square for now TODO change terrain to
     * different model
     */
    void initTerrain() {

        
        GameObject terrain = new Terrain(main, new Vector3f(0, 0, 0), "terrain");
    }

    /**
     * init all props eg. trees
     */
    void initProp() {
        
    
        GameObject tree0 = new Tree(main, new Vector3f(5, 0, 20), "tree0");
        GameObject tree1 = new Tree(main, new Vector3f(10, 0, 20), "tree1");
        GameObject tree2 = new Tree(main, new Vector3f(15, 0, 20), "tree2");
        GameObject tree3 = new Tree(main, new Vector3f(20, 0, 20), "tree3");
        GameObject tree4 = new Tree(main, new Vector3f(25, 0, 20), "tree4");

        getProps().add(tree0);
        getProps().add(tree1);
        getProps().add(tree2);
        getProps().add(tree3);
        getProps().add(tree4);

        
        
        GameObject volcano = new Volcano(main, new Vector3f(50, 0, 50), "volcano");
    }

    /**
     * init chefBoy object
     */
    void initPlayer() {

        player = new Player(main);
    }
    
    void initChefBoy(){
        chefBoy = new ChefBoy(main, new Vector3f(0, 0, 30), "chefBoy", 100);
    }

    /**
     * init all item objects
     */
    void initItem() {
        Item ham = new Ham(main, new Vector3f(5, 0, 5), "ham");
        getItems().add(ham);
    }

    void initEnemy() {
        
        Enemy pig0 = new Pig(main, new Vector3f(20, 0, 5), "pig1", 20);
        getEnemies().add(pig0);
    }

    /**
     * game updates update enemy behaviour, enemy position, chef boy etc
     *
     * @param tpf
     */
    @Override
    public void update(float tpf) {

        getPlayer().move();
        chefBoy.move();
        
        enemyBehaviour(getPlayer());
        itemBehaviour(getPlayer());
        
    }
    
    private void enemyBehaviour(Player player){
        
        for(int i = 0; i < getEnemies().size(); i++){
            getEnemies().get(i).behaviour(chefBoy);
        }
    }
    private void itemBehaviour(Player player){
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
