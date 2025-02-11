package mygame.state;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.audio.AudioNode;
import com.jme3.bullet.BulletAppState;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.light.PointLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.shadow.DirectionalLightShadowRenderer;
import com.jme3.util.SkyFactory;
import com.jme3.texture.Texture;

import mygame.gameobject.ChefBoy;
import mygame.gameobject.Enemy;
import mygame.gameobject.Fillet;
import mygame.gameobject.Fish;
import mygame.gameobject.GameObject;
import mygame.gameobject.Ham;
import mygame.gameobject.Hut;
import mygame.gameobject.Item;
import mygame.gameobject.Jelly;
import mygame.gameobject.Pig;
import mygame.gameobject.Player;
import mygame.gameobject.Prop;
import mygame.gameobject.Slime;
import mygame.gameobject.Terrain;
import mygame.gameobject.Tree;
import mygame.gameobject.TreeTwo;
import mygame.gameobject.TreeThree;
import mygame.gameobject.Volcano;
import mygame.gameobject.IslandTwo;
import mygame.gameobject.Wolf;
import mygame.gameobject.Bone;

import java.util.ArrayList;
import java.awt.Point;
import java.awt.Rectangle;
import utility.Queue;

import ui.Inventory;
import ui.GameStateManager;
import ui.HeadsUpDisplay;



/**
 * GameState.java
 * state for in game 
 * init all game objects 
 * update all game objects
 * @author Leo Zeng, Ariana Hou, William Zhao
 * 2020/12/20
 */
public class GameState extends State {

    private Inventory inventory; // inventory of items 
    private Queue<Enemy> enemyQueue; // queue to spawn enemies 
    private ArrayList<Item> items; // list to store all existing items
    private ArrayList<Enemy> enemies; // list to store enemies 
    private ArrayList<Prop> props; // list of all props 
    private HeadsUpDisplay hud;
    
    private float spawnRate = 10; // 10 seconds between each spawn 

    private Player player; // player object 
    private ChefBoy chefBoy;
    
    private GameStateManager gameStateManager; // controls states 
    
    private BulletAppState bulletAppState; // controls physics 
    private AppStateManager stateManager; // controls states
    private SimpleApplication app; // main object, this is needed because Main extends SimpleApplication
                                    // SimpleApplication contains things like rootNode, camera, assetManager, etc
    private DirectionalLight sun;
    private AmbientLight al;
    private PointLight pl;
    private PointLight pl1;
    private DirectionalLightShadowRenderer dlsr;
    
    private AudioNode bgm;
    
    private boolean init = false; // if gameState has been inited yet
    
    /**
     * initialize 
     * called when state is attatched to StateManager
     * @param stateManager engine StateManager, controls states 
     * @param app application 
     */
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        
        this.app = (SimpleApplication) app;
        this.stateManager = stateManager;

    }
    
    /**
     * init 
     * init all models, lighting, camera, physics, objects, and add them to game world
     */
    @Override
    public void init(){
        
        if(!init){
            
            init = true;
            
            enemyQueue = new Queue<>();
            items = new ArrayList<>();
            enemies = new ArrayList<>();
            props = new ArrayList<>();

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
            initEnemy();
            initInventory();
            initHud();

            gameStateManager = new GameStateManager(app, stateManager, inventory);
            
            addListener();
        }
    }
    
    /**
     * initHud
     * init heads up display with health bar
     */
    private void initHud(){
        hud = new HeadsUpDisplay(app, chefBoy);
    }

    /**
     * initLight
     * init lighting for game
     * directional light for sun
     */
    private void initLight() {

        sun = new DirectionalLight();
        sun.setColor(ColorRGBA.White.mult(0.8f));
        sun.setDirection(new Vector3f(-0.5f, -1f, -0.5f));
        app.getRootNode().addLight(sun);
        
        al = new AmbientLight();
        al.setColor(new ColorRGBA(1f, 0.5f, 0.5f, 1f).mult(0.2f));
        al.setEnabled(true);
        app.getRootNode().addLight(al);
        
        pl = new PointLight();
        pl.setPosition(new Vector3f(-205, 30, 208));
        pl.setRadius(50);
        pl.setEnabled(true);
        pl.setColor(ColorRGBA.White.mult(0.2f));
        app.getRootNode().addLight(pl);
        
        pl1 = new PointLight();
        pl1.setPosition(new Vector3f(300, -5, 250));
        pl1.setRadius(50);
        pl1.setEnabled(true);
        pl1.setColor(ColorRGBA.White.mult(0.2f));
        app.getRootNode().addLight(pl1);
        

        final int SHADOWMAP_SIZE = 512;
        dlsr = new DirectionalLightShadowRenderer(app.getAssetManager(), SHADOWMAP_SIZE, 1);
        dlsr.setLight(sun);
        app.getViewPort().addProcessor(dlsr);
    }

    /**
     * initCamera
     * change fly cam speed
     */
    private void initCamera() {
        ((SimpleApplication)app).getFlyByCamera().setMoveSpeed(30); 
        
    }

    /**
     * initSky
     * set sky to image
     */
    private void initSky() {
        Texture westTex = app.getAssetManager().loadTexture("Textures/Sky/yellowcloud_right.jpg");
        Texture eastTex = app.getAssetManager().loadTexture("Textures/Sky/yellowcloud_left.jpg");
        Texture northTex = app.getAssetManager().loadTexture("Textures/Sky/yellowcloud_front.jpg");
        Texture southTex = app.getAssetManager().loadTexture("Textures/Sky/yellowcloud_back.jpg");
        Texture upTex = app.getAssetManager().loadTexture("Textures/Sky/yellowcloud_up.jpg");
        Texture downTex = app.getAssetManager().loadTexture("Textures/Sky/yellowcloud_down.jpg");
        app.getRootNode().attachChild(SkyFactory.createSky(app.getAssetManager(), westTex,eastTex,northTex,southTex,upTex,downTex,new Vector3f(-1, 1, 1)));
    }

    /**
     * initTerrain
     * init ground terrain
     */
    private void initTerrain() {

        GameObject terrain = new Terrain(app, bulletAppState, new Vector3f(0, 0, 0), "terrain");
    }

    /**
     * initProp
     * init all props eg. trees
     */
    private void initProp() {
        
        Prop hut0 = new Hut(app, bulletAppState, new Vector3f(-205, 20, 208), "hut0");
        Prop hut1 = new Hut(app, bulletAppState, new Vector3f(300, -15, 250), "hut1");
        
        Prop tree0 = new Tree(app, bulletAppState, new Vector3f(260, -10, 193), "tree0");
        Prop tree1 = new TreeTwo(app, bulletAppState, new Vector3f(205, -13, 223), "tree1");
        Prop tree2 = new Tree(app, bulletAppState, new Vector3f(105, 4, 154), "tree2");
        Prop tree3 = new TreeTwo(app, bulletAppState, new Vector3f(41, -6, 164), "tree3");
        Prop tree4 = new Tree(app, bulletAppState, new Vector3f(-40, 7, 221), "tree4");
        Prop tree5 = new Tree(app, bulletAppState, new Vector3f(-112, -7, 191), "tree5");
        Prop tree6 = new TreeTwo(app, bulletAppState, new Vector3f(-251, 15, 212), "tree6");
        Prop tree7 = new Tree(app, bulletAppState, new Vector3f(-304, 2, 162), "tree7");
        Prop tree8 = new TreeTwo(app, bulletAppState, new Vector3f(-214, 2, 146), "tree8");
        Prop tree9 = new TreeTwo(app, bulletAppState, new Vector3f(-50, -6, 180), "tree9");
        Prop tree10 = new Tree(app, bulletAppState, new Vector3f(99, -4, 122), "tree10");
        Prop tree11 = new Tree(app, bulletAppState, new Vector3f(204, -15, 104), "tree11");
        Prop tree12 = new TreeTwo(app, bulletAppState, new Vector3f(260, 10, 11), "tree12");
        Prop tree13 = new Tree(app, bulletAppState, new Vector3f(192, -6, -49), "tree13");
        Prop tree14 = new TreeTwo(app, bulletAppState, new Vector3f(108, 5, -42), "tree14");
        Prop tree15 = new Tree(app, bulletAppState, new Vector3f(38, -4, -21), "tree15");
        Prop tree16 = new TreeTwo(app, bulletAppState, new Vector3f(-78, -9, -16), "tree16");
        Prop tree17 = new Tree(app, bulletAppState, new Vector3f(-236, 10, 35), "tree17");
        Prop tree18 = new Tree(app, bulletAppState, new Vector3f(-259, 14, -62), "tree18");
        Prop tree19 = new TreeTwo(app, bulletAppState, new Vector3f(-50, -7, -151), "tree19");
        Prop tree20 = new Tree(app, bulletAppState, new Vector3f(53, 6, -152), "tree20");
        Prop tree21 = new Tree(app, bulletAppState, new Vector3f(252, -17, -184), "tree21");
        Prop tree22 = new TreeTwo(app, bulletAppState, new Vector3f(295, -12, -345), "tree22");
        Prop tree23 = new Tree(app, bulletAppState, new Vector3f(201, -9, -366), "tree23");
        Prop tree24 = new TreeTwo(app, bulletAppState, new Vector3f(33, -6, -363), "tree24");
        Prop tree25 = new Tree(app, bulletAppState, new Vector3f(-185, 18, -318), "tree25");
        Prop tree26 = new TreeThree(app, bulletAppState, new Vector3f(-183, -3, 101), "tree26");
        Prop tree27 = new TreeThree(app, bulletAppState, new Vector3f(-201, 2, 63), "tree27");
        Prop tree28 = new TreeThree(app, bulletAppState, new Vector3f(-164, -11, 51), "tree28");
        Prop tree29 = new TreeThree(app, bulletAppState, new Vector3f(-153, -3, -202), "tree29");
        Prop tree30 = new TreeThree(app, bulletAppState, new Vector3f(-27, -10, -158), "tree30");


        Prop volcano0 = new Volcano(app, bulletAppState, new Vector3f(-250, -12, -200), "volcano0");
        
        Prop island0 = new IslandTwo(app, bulletAppState, new Vector3f(600, 5, 200), "island0");

    }
    
    /**
     * initChefBoy
     * init chefBoy object
     */
    private void initChefBoy(){
        chefBoy = new ChefBoy(app, bulletAppState, new Vector3f(-205, 30, 208), "chefBoy", 100);
    }

    /**
     * initPlayer
     * init player object
     */
    private void initPlayer() {

        player = new Player(app, chefBoy);
        
    }

    /**
     * initEnemy
     * init all enemy objects
     * add enemies to queue
     */
    private void initEnemy() {
        
        Enemy pig0 = new Pig(app, bulletAppState, new Vector3f(100, 20, 100), "pig0", 30);
        enemyQueue.enqueue(pig0, 0);
        Enemy pig1 = new Pig(app, bulletAppState, new Vector3f(90, 20, 80), "pig1", 30);
        enemyQueue.enqueue(pig1, 1);
        Enemy pig2 = new Pig(app, bulletAppState, new Vector3f(90, 20, 120), "pig2", 30);
        enemyQueue.enqueue(pig2, 2);
        Enemy pig3 = new Pig(app, bulletAppState, new Vector3f(130, 20, 110), "pig3", 30);
        enemyQueue.enqueue(pig3, 3);
        Enemy pig4 = new Pig(app, bulletAppState, new Vector3f(80, 20, 100), "pig4", 30);
        enemyQueue.enqueue(pig4, 4);
        Enemy pig5 = new Pig(app, bulletAppState, new Vector3f(30, 20, 20), "pig5", 30);
        enemyQueue.enqueue(pig5, 5);
        Enemy pig6 = new Pig(app, bulletAppState, new Vector3f(250, 20, 190), "pig6", 30);
        enemyQueue.enqueue(pig6, 6);
        Enemy pig7 = new Pig(app, bulletAppState, new Vector3f(66, 20, 119), "pig7", 30);
        enemyQueue.enqueue(pig7, 7);
        Enemy pig8 = new Pig(app, bulletAppState, new Vector3f(39, 20, 72), "pig8", 30);
        enemyQueue.enqueue(pig8, 8);
        Enemy pig9 = new Pig(app, bulletAppState, new Vector3f(70, 20, 140), "pig9", 30);
        enemyQueue.enqueue(pig9, 9);
        Enemy pig10 = new Pig(app, bulletAppState, new Vector3f(85, 20, 154), "pig10", 30);
        enemyQueue.enqueue(pig10, 10);
        Enemy pig11 = new Pig(app, bulletAppState, new Vector3f(60, 20, 124), "pig11", 30);
        enemyQueue.enqueue(pig11, 11);
        Enemy pig12 = new Pig(app, bulletAppState, new Vector3f(50, 20, 174), "pig12", 30);
        enemyQueue.enqueue(pig12, 12);
        Enemy pig13 = new Pig(app, bulletAppState, new Vector3f(90, 20, 114), "pig13", 30);
        enemyQueue.enqueue(pig13, 13);
        Enemy pig14 = new Pig(app, bulletAppState, new Vector3f(100, 20, 104), "pig14", 30);
        enemyQueue.enqueue(pig14, 14);
        Enemy pig15 = new Pig(app, bulletAppState, new Vector3f(77, 20, 169), "pig15", 30);
        enemyQueue.enqueue(pig15, 15);
        
        
        Enemy slime0 = new Slime(app, bulletAppState, new Vector3f(-122, 20, 70), "slime0", 10);
        enemyQueue.enqueue(slime0, 0);
        Enemy slime1 = new Slime(app, bulletAppState, new Vector3f(-130, 20, 63), "slime1", 10);
        enemyQueue.enqueue(slime1, 1);
        Enemy slime2 = new Slime(app, bulletAppState, new Vector3f(-60, 20, 142), "slime2", 10);
        enemyQueue.enqueue(slime2, 2);
        Enemy slime3 = new Slime(app, bulletAppState, new Vector3f(-74, 20, 102), "slime3", 10);
        enemyQueue.enqueue(slime3, 3);
        Enemy slime4 = new Slime(app, bulletAppState, new Vector3f(-96, 20, 50), "slime4", 10);
        enemyQueue.enqueue(slime4, 4);
        Enemy slime5 = new Slime(app, bulletAppState, new Vector3f(-104, 20, 40), "slime5", 10);
        enemyQueue.enqueue(slime5, 5);
        Enemy slime6 = new Slime(app, bulletAppState, new Vector3f(-104, 20, 72), "slime6", 10);
        enemyQueue.enqueue(slime6, 6);
        Enemy slime7 = new Slime(app, bulletAppState, new Vector3f(-69, 20, 154), "slime7", 10);
        enemyQueue.enqueue(slime7, 7);
        Enemy slime8 = new Slime(app, bulletAppState, new Vector3f(-124, 20, 42), "slime8", 10);
        enemyQueue.enqueue(slime8, 8);
        Enemy slime9 = new Slime(app, bulletAppState, new Vector3f(-106, 20, 124), "slime9", 10);
        enemyQueue.enqueue(slime9, 9);
        Enemy slime10 = new Slime(app, bulletAppState, new Vector3f(-99, 20, 140), "slime10", 10);
        enemyQueue.enqueue(slime10, 10);
        Enemy slime11 = new Slime(app, bulletAppState, new Vector3f(-260, 20, -50), "slime11", 10); 
        enemyQueue.enqueue(slime11, 11);
        Enemy slime12 = new Slime(app, bulletAppState, new Vector3f(-265, 20, -30), "slime12", 10); 
        enemyQueue.enqueue(slime12, 11);
        Enemy slime13 = new Slime(app, bulletAppState, new Vector3f(-280, 20, -50), "slime13", 10); 
        enemyQueue.enqueue(slime13, 11);
        Enemy slime14 = new Slime(app, bulletAppState, new Vector3f(-290, 20, -64), "slime14", 10); 
        enemyQueue.enqueue(slime14, 11);
        Enemy slime15 = new Slime(app, bulletAppState, new Vector3f(-276, 20, -27), "slime15", 10); 
        enemyQueue.enqueue(slime15, 11);
        
        Enemy fish0 = new Fish(app, bulletAppState, new Vector3f(0, 20, -100), "fish0", 20);
        enemyQueue.enqueue(fish0, 0);
        Enemy fish1 = new Fish(app, bulletAppState, new Vector3f(20, 40, -120), "fish1", 20);
        enemyQueue.enqueue(fish1, 1);
        Enemy fish2 = new Fish(app, bulletAppState, new Vector3f(-10, 40, -80), "fish2", 20);
        enemyQueue.enqueue(fish2, 2);
        Enemy fish3 = new Fish(app, bulletAppState, new Vector3f(40, 40, -130), "fish3", 20);
        enemyQueue.enqueue(fish3, 3);
        Enemy fish4 = new Fish(app, bulletAppState, new Vector3f(30, 40, -70), "fish4", 20);
        enemyQueue.enqueue(fish4, 4);
        Enemy fish5 = new Fish(app, bulletAppState, new Vector3f(-30, 40, -60), "fish5", 20);
        enemyQueue.enqueue(fish5, 5);
        Enemy fish6 = new Fish(app, bulletAppState, new Vector3f(-10, 40, -110), "fish6", 20);
        enemyQueue.enqueue(fish6, 6);
        Enemy fish7 = new Fish(app, bulletAppState, new Vector3f(50, 40, -140), "fish7", 20);
        enemyQueue.enqueue(fish7, 7);
        Enemy fish8 = new Fish(app, bulletAppState, new Vector3f(-50, 40, -90), "fish8", 20);
        enemyQueue.enqueue(fish8, 8);
        Enemy fish9 = new Fish(app, bulletAppState, new Vector3f(-75, 40, -85), "fish9", 20);
        enemyQueue.enqueue(fish9, 9);
        Enemy fish10 = new Fish(app, bulletAppState, new Vector3f(-90, 40, -60), "fish10", 20);
        enemyQueue.enqueue(fish10, 10);
        Enemy fish11 = new Fish(app, bulletAppState, new Vector3f(-78, 40, -97), "fish11", 20);
        enemyQueue.enqueue(fish11, 11);
        Enemy fish12 = new Fish(app, bulletAppState, new Vector3f(-30, 40, -50), "fish12", 20);
        enemyQueue.enqueue(fish12, 12);
        Enemy fish13 = new Fish(app, bulletAppState, new Vector3f(-10, 40, -80), "fish13", 20);
        enemyQueue.enqueue(fish13, 13);
        Enemy fish14 = new Fish(app, bulletAppState, new Vector3f(-35, 40, -99), "fish14", 20);
        enemyQueue.enqueue(fish14, 14);
        Enemy fish15 = new Fish(app, bulletAppState, new Vector3f(-10, 40, -50), "fish15", 20);
        enemyQueue.enqueue(fish15, 15);
        
        Enemy wolf0 = new Wolf(app, bulletAppState, new Vector3f(273, 20, -336), "wolf0", 50);
        enemyQueue.enqueue(wolf0, 10);
        
        for(int i = 0; i < 15; i++){ // spawn all enemies for testing 
            Enemy enemy = enemyQueue.dequeue(); // remove from queue 
            enemy.spawn(); // spawn, make model visible 
            enemies.add(enemy); // add to enemy list 
        }
    }
    
    /**
     * initInventory
     * init inventory 
     */
    private void initInventory(){
        inventory = new Inventory(app, stateManager);
    }
    
    /**
     * update
     * game updates update enemy behaviour, enemy position, chef boy etc
     * main update loop for gameState 
     * @param tpf time per frame
     */
    @Override
    public void update(float tpf) {
        
        playerBehaviour(tpf);
        enemyBehaviour(tpf);
        itemBehaviour(tpf);
        
        spawnEnemy(tpf);
        
        if(checkWin()){ // if win game
            endGame(true);
        }
        if(! chefBoy.getAlive()){ // if chefBoy is dead
            endGame(false);
        }
        
        updateHUD();

    }

    
    /**
     * playerBehaviour
     * behaviour for player & chefboy 
     * @param tpf time per frame
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
     * enemyBehaviour
     * behaviour for all enemy in enemies list 
     * @param tpf time per frame
     */
    private void enemyBehaviour(float tpf){
        
        for(int i = 0; i < enemies.size(); i++){
            
            Enemy current = enemies.get(i);
            
            current.behaviour(tpf, chefBoy);

            
            if(! current.getAlive()){ // if current enemy dies 
                
                enemies.remove(current); // remove from list 
                
                if(current instanceof Pig){
                    
                    Item item = new Ham(app, current.getPosition(), "ham"); // spawn cooresponding item 
                    items.add(item);
                } else if(current instanceof Slime){
                    
                    Item item = new Jelly(app, current.getPosition(), "jelly"); // spawn cooresponding item 
                    items.add(item);
                } else if(current instanceof Fish){
                    
                    Item item = new Fillet(app, current.getPosition(), "fillet"); // spawn cooresponding item 
                    items.add(item);
                } else if(current instanceof Wolf){
                    
                    Item item = new Bone(app, current.getPosition(), "bone"); // spawn cooresponding item 
                    items.add(item);
                }
            }
        }
    }
    
    /**
     * itemBehaviour
     * behaviour of all items in list 
     * @param tpf time per frame
     */
    private void itemBehaviour(float tpf){
        
        for(int i = 0; i < items.size(); i++){
            
            Item current = items.get(i);
            
            current.behaviour(tpf);
            
            if(current.getPickedUp()){ // if current is picked up 
                
                items.remove(current); // remove from list 
                inventory.add(current); // add to inventory
                
            }
        }
    }
    
    /**
     * spawnEnemy
     * spawn enemies slowly throughout the game
     * @param tpf time per frame
     */
    private void spawnEnemy(float tpf){
        if(spawnRate <= 0){
            if(enemies.size() <= 15){ // if less than 15 enemies on map
                if(enemyQueue.size() > 0){
                    Enemy enemy = enemyQueue.dequeue(); // remove from queue 
                    enemy.spawn(); // spawn, make model visible 
                    enemies.add(enemy); // add to enemy list 
            }
            spawnRate = 10; // reset spawn rate
            }
        }
        
        if(spawnRate > 0){
            spawnRate -= tpf;
        }
    }
    
    /**
     * checkWin
     * check if winning conditions are met
     */
    private boolean checkWin(){
        
        Rectangle winHut = new Rectangle(285, 235, 20, 20); // second hut position 
        
        Point chefBoyPos = new Point((int)chefBoy.getPosition().x, (int)chefBoy.getPosition().z);
       
        if(winHut.contains(chefBoyPos)){ // if chefboy enters second hut 
            
            if(inventory.getSize() >= 30){ // if more than 30 items are collected 
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * updateHUD
     * update hud (health bar)
     */
    private void updateHUD(){
        hud.update();
    }
    
    
    
    /**
     * getInventory
     * @return the inventory
     */
    public Inventory getInventory() {
        return inventory;
    }
    
    /**
     * getInit 
     * @return init
     */
    public boolean getInit(){
        return init;
    }
    
    
    /**
     * cleanUp
     * clean up every game object when finished 
     * remove from root node 
     * delete queues & lists
     */
    @Override
    public void cleanUp(){
        
        app.getGuiNode().detachAllChildren();
        app.getRootNode().detachAllChildren();
        app.getInputManager().removeListener(player); 
        app.getInputManager().removeListener(gameStateManager); 
        app.getRootNode().removeLight(sun);
        app.getRootNode().removeLight(al);
        app.getViewPort().removeProcessor(dlsr);
        
        inventory = null;
        enemyQueue = null;
        items = null;
        enemies = null;
        props = null;  
        player = null;
        chefBoy = null;
        gameStateManager = null;
        bulletAppState = null;
        sun = null;
        al = null;
        dlsr = null;
        
    }
    
    /**
     * addListener 
     * add listener for inputs 
     * wasd, jump, mouse, esc, inventory
     */
    public void addListener(){
        
        app.getInputManager().addListener(player, "Left");
        app.getInputManager().addListener(player, "Right");
        app.getInputManager().addListener(player, "Forward");
        app.getInputManager().addListener(player, "Back");
        app.getInputManager().addListener(player, "Jump");
        
        app.getInputManager().addListener(player, "MouseLeft");
        app.getInputManager().addListener(player, "MouseRight");
        
        app.getInputManager().addListener(gameStateManager, "Inventory");
        app.getInputManager().addListener(gameStateManager, "Setting");
        
    }
    
    /**
     * removeListener
     * remove listener when finished 
     */
    public void removeListener(){
        
        app.getInputManager().removeListener(player);
        app.getInputManager().removeListener(gameStateManager);
    }
    
    /**
     * openInventory
     * pause GameState, open InventoryState
     */
    public void openInventory(){
        stateManager.getState(InventoryState.class).enterState();
        stateManager.getState(InventoryState.class).init(inventory);
        stateManager.getState(InventoryState.class).addListener();
        stateManager.getState(GameState.class).removeListener();
        stateManager.getState(GameState.class).exitState();
    }
    
    /**
     * openSetting
     * pause GameState, open SettingState
     */
    public void openSetting(){
        stateManager.getState(SettingState.class).enterState();
        stateManager.getState(SettingState.class).init();
        stateManager.getState(SettingState.class).addListener();
        stateManager.getState(GameState.class).removeListener();
        stateManager.getState(GameState.class).exitState();
    }
    
    /**
     * endGame
     * end game when win / lose 
     * @param win if player win 
     */
    private void endGame(boolean win){
        stateManager.getState(EndState.class).enterState();
        stateManager.getState(EndState.class).init(win);
        stateManager.getState(GameState.class).removeListener();
        stateManager.getState(GameState.class).exitState();
    }
            
}
