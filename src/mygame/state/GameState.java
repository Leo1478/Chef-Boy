/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.state;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.light.PointLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.shadow.DirectionalLightShadowRenderer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import mygame.gameobject.ChefBoy;
import mygame.gameobject.Enemy;
import mygame.gameobject.Fillet;
import mygame.gameobject.Fish;
import mygame.gameobject.GameLight;
import mygame.gameobject.GameObject;
import mygame.gameobject.Ham;
import mygame.gameobject.Hut;
import ui.Inventory;
import mygame.gameobject.Item;
import mygame.gameobject.Jelly;
import mygame.gameobject.Pig;
import mygame.gameobject.Player;
import mygame.gameobject.Prop;
import mygame.gameobject.Slime;
import mygame.gameobject.Terrain;
import mygame.gameobject.Tree;
import mygame.gameobject.TreeTwo;
import mygame.gameobject.Volcano;
import mygame.gameobject.Crystal;
import ui.GameStateManager;
import ui.HeadsUpDisplay;
import com.jme3.util.SkyFactory;
import mygame.gameobject.IslandTwo;
import com.jme3.texture.Texture;
<<<<<<< Updated upstream
import java.awt.Point;
import java.awt.Rectangle;
import mygame.gameobject.Wolf;
=======
<<<<<<< HEAD

import java.awt.Point;
import java.awt.Rectangle;

import mygame.gameobject.Wolf;

=======
import java.awt.Point;
import java.awt.Rectangle;
import mygame.gameobject.Wolf;
>>>>>>> 1cfb6c40ecab2876c58587db74521558d9908710
>>>>>>> Stashed changes

/**
 * state for in game init all game objects update all game objects
 *
 * @author leoze
 */
public class GameState extends State {

    private Inventory inventory; // inventory of items 
    private Queue<Enemy> enemyQueue; // queue to spawn enemies 
    private ArrayList<Item> items; // list to store all existing items
    private ArrayList<Enemy> enemies; // list to store enemies 
    private ArrayList<Prop> props; // list of all props 
    private HeadsUpDisplay hud;
    
    private float spawnRate = 10; // 10 seconds between each spawn 
    
    private GameLight gameLight; // lighting

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
    
    private boolean init = false; // if gameState has been inited yet
    
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
        if(!init){
            
            init = true;
            
            enemyQueue = new LinkedList<>();
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
            initItem();
            initEnemy();
            initInventory();

            initHud();
            
            bulletAppState.setDebugEnabled(true);

            gameStateManager = new GameStateManager(app, stateManager, inventory);
            
            addListener();
        }
    }
    
    private void initHud(){
        hud = new HeadsUpDisplay(app, chefBoy);
    }

    /**
     * init lighting for game
     *
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
        

        final int SHADOWMAP_SIZE=512;
        dlsr = new DirectionalLightShadowRenderer(app.getAssetManager(), SHADOWMAP_SIZE, 1);
        dlsr.setLight(sun);
        app.getViewPort().addProcessor(dlsr);
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
        Texture westTex = app.getAssetManager().loadTexture("Textures/Sky/yellowcloud_right.jpg");
        Texture eastTex = app.getAssetManager().loadTexture("Textures/Sky/yellowcloud_left.jpg");
        Texture northTex = app.getAssetManager().loadTexture("Textures/Sky/yellowcloud_front.jpg");
        Texture southTex = app.getAssetManager().loadTexture("Textures/Sky/yellowcloud_back.jpg");
        Texture upTex = app.getAssetManager().loadTexture("Textures/Sky/yellowcloud_up.jpg");
        Texture downTex = app.getAssetManager().loadTexture("Textures/Sky/yellowcloud_down.jpg");
        app.getRootNode().attachChild(SkyFactory.createSky(app.getAssetManager(), westTex,eastTex,northTex,southTex,upTex,downTex,new Vector3f(-1, 1, 1)));
        //app.getViewPort().setBackgroundColor(new ColorRGBA(1f, 0.7f, 0.5f, 1f));
    }

    /**
     * init ground terrain a flat square for now TODO change terrain to
     * different model
     */
    private void initTerrain() {

        GameObject terrain = new Terrain(app, bulletAppState, new Vector3f(0, 0, 0), "terrain");
    }

    /**
     * init all props eg. trees
     */
    private void initProp() {
        

        Prop hut0 = new Hut(app, bulletAppState, new Vector3f(-205, 20, 208), "hut1");
        props.add(hut0);
        
        Prop hut1 = new Hut(app, bulletAppState, new Vector3f(300, -15, 250), "hut1");
        props.add(hut1);
        
        
        Prop tree0 = new Tree(app, bulletAppState, new Vector3f(260, -10, 193), "tree0");
        Prop tree1 = new TreeTwo(app, bulletAppState, new Vector3f(205, -13, 223), "tree1");
        Prop tree2 = new Tree(app, bulletAppState, new Vector3f(105, 4, 154), "tree2");
        Prop tree3 = new TreeTwo(app, bulletAppState, new Vector3f(41, -6, 164), "tree3");
        Prop tree4 = new Tree(app, bulletAppState, new Vector3f(-40, 7, 221), "tree4");
        Prop tree5 = new Tree(app, bulletAppState, new Vector3f(-112, -7, 191), "tree4");
        Prop tree6 = new TreeTwo(app, bulletAppState, new Vector3f(-251, 15, 212), "tree4");
        Prop tree7 = new Tree(app, bulletAppState, new Vector3f(-304, 2, 162), "tree4");
        Prop tree8 = new TreeTwo(app, bulletAppState, new Vector3f(-214, 2, 146), "tree4");
        Prop tree9 = new TreeTwo(app, bulletAppState, new Vector3f(-112, -6, 153), "tree4");
        Prop tree10 = new Tree(app, bulletAppState, new Vector3f(99, -4, 122), "tree4");
        Prop tree11 = new Tree(app, bulletAppState, new Vector3f(204, -15, 104), "tree4");
        Prop tree12 = new TreeTwo(app, bulletAppState, new Vector3f(260, 10, 11), "tree4");
        Prop tree13 = new Tree(app, bulletAppState, new Vector3f(192, -6, -49), "tree4");
        Prop tree14 = new TreeTwo(app, bulletAppState, new Vector3f(108, 5, -42), "tree4");
        Prop tree15 = new Tree(app, bulletAppState, new Vector3f(38, -4, -21), "tree4");
        Prop tree16 = new TreeTwo(app, bulletAppState, new Vector3f(-78, -9, -16), "tree4");
        Prop tree17 = new Tree(app, bulletAppState, new Vector3f(-236, 10, 35), "tree4");
        Prop tree18 = new Tree(app, bulletAppState, new Vector3f(-279, 14, -82), "tree4");
        Prop tree19 = new TreeTwo(app, bulletAppState, new Vector3f(-119, -7, -161), "tree4");
        Prop tree20 = new Tree(app, bulletAppState, new Vector3f(53, 6, -152), "tree4");
        Prop tree21 = new Tree(app, bulletAppState, new Vector3f(252, -17, -184), "tree4");
        Prop tree22 = new TreeTwo(app, bulletAppState, new Vector3f(295, -12, -345), "tree4");
        Prop tree23 = new Tree(app, bulletAppState, new Vector3f(201, -9, -366), "tree4");
        Prop tree24 = new TreeTwo(app, bulletAppState, new Vector3f(33, -6, -363), "tree4");
        Prop tree25 = new Tree(app, bulletAppState, new Vector3f(-185, 18, -318), "tree4");

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

        Prop volcano0 = new Volcano(app, bulletAppState, new Vector3f(150, 0, 0), "volcano0");
        props.add(volcano0);
        Prop volcano1 = new Volcano(app, bulletAppState, new Vector3f(-250, 5, -200), "volcano1");
        props.add(volcano1);
        
        Prop crystal0 = new Crystal(app, bulletAppState, new Vector3f(-210, 20, 200), "crystal0");
        props.add(volcano0);
        
        Prop island0 = new IslandTwo(app, bulletAppState, new Vector3f(150, 160, 600), "island0");
        props.add(island0);
    }
    
    private void initChefBoy(){
        chefBoy = new ChefBoy(app, bulletAppState, new Vector3f(-205, 30, 208), "chefBoy", 100);
    }

    /**
     * init chefBoy object
     */
    private void initPlayer() {

        player = new Player(app, chefBoy);
        
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
        
        Enemy pig0 = new Pig(app, bulletAppState, new Vector3f(100, 20, 100), "pig0", 20);
        enemyQueue.add(pig0);
        Enemy pig1 = new Pig(app, bulletAppState, new Vector3f(90, 20, 80), "pig1", 20);
        enemyQueue.add(pig1);
        Enemy pig2 = new Pig(app, bulletAppState, new Vector3f(90, 20, 120), "pig2", 20);
        enemyQueue.add(pig2);
        Enemy pig3 = new Pig(app, bulletAppState, new Vector3f(130, 20, 110), "pig3", 20);
        enemyQueue.add(pig3);
        Enemy pig4 = new Pig(app, bulletAppState, new Vector3f(80, 20, 100), "pig4", 20);
        enemyQueue.add(pig4);
        Enemy pig5 = new Pig(app, bulletAppState, new Vector3f(30, 20, 20), "pig5", 20);
        enemyQueue.add(pig5);
        Enemy pig6 = new Pig(app, bulletAppState, new Vector3f(250, 20, 190), "pig6", 20);
        enemyQueue.add(pig6);
        Enemy pig7 = new Pig(app, bulletAppState, new Vector3f(-210, 20, -250), "pig7", 20);
        enemyQueue.add(pig7);
        Enemy pig8 = new Pig(app, bulletAppState, new Vector3f(-260, 20, -250), "pig8", 20);
        enemyQueue.add(pig8);
        Enemy pig9 = new Pig(app, bulletAppState, new Vector3f(-180, 20, -170), "pig9", 20);
        enemyQueue.add(pig9);
        Enemy pig10 = new Pig(app, bulletAppState, new Vector3f(-220, 20, -190), "pig10", 20);
        enemyQueue.add(pig10);
        
        //Enemy slime0 = new Slime( app, bulletAppState, new Vector3f(100, 10, 100), "slime0", 20);
        //slime0.spawn();
        //enemies.add(slime0);
        
        Enemy slime0 = new Slime(app, bulletAppState, new Vector3f(210, 20, -200), "slime0", 10);
        enemyQueue.add(slime0);
        Enemy slime1 = new Slime(app, bulletAppState, new Vector3f(200, 20, -150), "slime0", 10);
        enemyQueue.add(slime1);
        Enemy slime2 = new Slime(app, bulletAppState, new Vector3f(250, 20, -180), "slime0", 10);
        enemyQueue.add(slime2);
        Enemy slime3 = new Slime(app, bulletAppState, new Vector3f(220, 20, -190), "slime0", 10);
        enemyQueue.add(slime3);
        Enemy slime4 = new Slime(app, bulletAppState, new Vector3f(210, 20, -220), "slime0", 10);
        enemyQueue.add(slime4);
        Enemy slime5 = new Slime(app, bulletAppState, new Vector3f(280, 20, -240), "slime0", 10);
        enemyQueue.add(slime5);
        Enemy slime6 = new Slime(app, bulletAppState, new Vector3f(240, 20, -230), "slime0", 10);
        enemyQueue.add(slime6);
        Enemy slime7 = new Slime(app, bulletAppState, new Vector3f(230, 20, -180), "slime0", 10);
        enemyQueue.add(slime7);
        Enemy slime8 = new Slime(app, bulletAppState, new Vector3f(220, 20, -150), "slime0", 10);
        enemyQueue.add(slime8);
        Enemy slime9 = new Slime(app, bulletAppState, new Vector3f(180, 20, -190), "slime0", 10);
        enemyQueue.add(slime9);
        Enemy slime10 = new Slime(app, bulletAppState, new Vector3f(190, 20, -160), "slime0", 10);
        enemyQueue.add(slime10);
        
        
        Enemy slime11 = new Slime(app, bulletAppState, new Vector3f(-200, 40, 200), "slime0", 10); // right beside chefBoy spawn 
        enemyQueue.add(slime11);
        
        Enemy fish0 = new Fish(app, bulletAppState, new Vector3f(0, 20, -100), "fish0", 10);
        enemyQueue.add(fish0);
        Enemy fish1 = new Fish(app, bulletAppState, new Vector3f(20, 40, -120), "fish0", 10);
        enemyQueue.add(fish1);
        Enemy fish2 = new Fish(app, bulletAppState, new Vector3f(-10, 40, -80), "fish0", 10);
        enemyQueue.add(fish2);
        Enemy fish3 = new Fish(app, bulletAppState, new Vector3f(40, 40, -130), "fish0", 10);
        enemyQueue.add(fish3);
        Enemy fish4 = new Fish(app, bulletAppState, new Vector3f(30, 40, -70), "fish0", 10);
        enemyQueue.add(fish4);
        Enemy fish5 = new Fish(app, bulletAppState, new Vector3f(-30, 40, -60), "fish0", 10);
        enemyQueue.add(fish5);
        Enemy fish6 = new Fish(app, bulletAppState, new Vector3f(-10, 40, -110), "fish0", 10);
        enemyQueue.add(fish6);
        Enemy fish7 = new Fish(app, bulletAppState, new Vector3f(50, 40, -140), "fish0", 10);
        enemyQueue.add(fish7);
        Enemy fish8 = new Fish(app, bulletAppState, new Vector3f(-50, 40, -90), "fish0", 10);
        enemyQueue.add(fish8);
        
        Enemy wolf0 = new Wolf(app, bulletAppState, new Vector3f(0, 3, 0), "wolf0", 10);
        enemyQueue.add(wolf0);
        
        for(int i = 0; i < enemyQueue.size(); ){ // spawn all enemies for testing 
            Enemy enemy = enemyQueue.poll(); // remove from queue 
            enemy.spawn(); // spawn, make model visible 
            enemies.add(enemy); // add to enemy list 
        }
        System.out.println(enemies.size());
    }
    
    /**
     * init inventory 
     */
    private void initInventory(){
        inventory = new Inventory(app, stateManager);
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
        
        if(checkWin()){
            endGame(true);
        }
        if(! chefBoy.getAlive()){
            endGame(false);
        }
        
        updateHUD();
        
        //System.out.println(chefBoy.getPosition()); //-6 to y

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
                else if(current instanceof Fish){
                    
                    Item item = new Fillet(app, current.getPosition(), "fillet"); // spawn cooresponding item 
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
            
            current.behaviour(chefBoy, tpf);
            
            if(current.getPickedUp()){ // if current is picked up 
                
                items.remove(current); // remove from list 
                inventory.add(current); // add to inventory
                
            }
        }
        
    }
    
    private void spawnEnemy(float tpf){
        if(spawnRate <= 0){
            if(enemies.size() <= 30){ // if less than 30 enemies on map
                if(enemyQueue.size() > 0){
                    Enemy enemy = enemyQueue.poll(); // remove from queue 
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
     * check if winning conditions are met
     */
    private boolean checkWin(){
        
        Rectangle winHut = new Rectangle(285, 235, 20, 20); // second hut position 
        
        Point chefBoyPos = new Point((int)chefBoy.getPosition().x, (int)chefBoy.getPosition().z);
       
        if(winHut.contains(chefBoyPos)){ // if chefboy enters second hut 
            System.out.println("in hut");
            System.out.println(inventory.getSize());
            if(inventory.getSize() > 30){ // if more than 30 items are collected 
                System.out.println("win");
                return true;
            }
        }
        
        return false;
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
    
    public boolean getInit(){
        return init;
    }
    
    
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
        gameLight = null;
        player = null;
        chefBoy = null;
        gameStateManager = null;
        bulletAppState = null;
        sun = null;
        al = null;
        dlsr = null;
        
    }
    
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
    
    public void removeListener(){
        
        app.getInputManager().removeListener(player);
        app.getInputManager().removeListener(gameStateManager);
    }
    
    public void openInventory(){
        stateManager.getState(InventoryState.class).enterState();
        stateManager.getState(InventoryState.class).init(inventory);
        stateManager.getState(InventoryState.class).addListener();
        stateManager.getState(GameState.class).removeListener();
        stateManager.getState(GameState.class).exitState();
    }
    public void openSetting(){
        stateManager.getState(SettingState.class).enterState();
        stateManager.getState(SettingState.class).init();
        stateManager.getState(SettingState.class).addListener();
        stateManager.getState(GameState.class).removeListener();
        stateManager.getState(GameState.class).exitState();
    }
    
    private void endGame(boolean win){
        stateManager.getState(EndState.class).enterState();
        stateManager.getState(EndState.class).init(win);
        stateManager.getState(GameState.class).removeListener();
        stateManager.getState(GameState.class).exitState();
    }
            
}
