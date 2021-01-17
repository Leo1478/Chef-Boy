/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.system.AppSettings;
import com.jme3.ui.Picture;
import mygame.gameobject.ChefBoy;
import static mygame.state.Main.SCREENHEIGHT;
import static mygame.state.Main.SCREENWIDTH;

/**
 *
 * @author leoze
 */
public class HeadsUpDisplay {
    
    private SimpleApplication app;
    private Picture healthBar;
    private Picture healthBarRed;
    private Picture crossHair; 
    private ChefBoy chefBoy;
    private BitmapFont guiFont;
    private BitmapText healthText;
    
    public HeadsUpDisplay(SimpleApplication app, ChefBoy chefBoy){
        
        this.app = app;
        this.chefBoy = chefBoy;
        init();
    }
    
    private void init(){

        AppSettings settings = new AppSettings(true);
        
        healthBarRed = new Picture("health bar red"); // red part of health bar 
        healthBarRed.setImage(app.getAssetManager(), "UI/health bar red.png", true);
        healthBarRed.setWidth(400);
        healthBarRed.setHeight(50);
        healthBarRed.setPosition(50, SCREENHEIGHT - 100);
        app.getGuiNode().attachChild(healthBarRed);
        healthBarRed.setQueueBucket(RenderQueue.Bucket.Gui);
     
        healthBar = new Picture("health bar"); // background of health bar 
        healthBar.setImage(app.getAssetManager(), "UI/health bar.png", true);
        healthBar.setWidth(400);
        healthBar.setHeight(50);
        healthBar.setPosition(50, SCREENHEIGHT - 100);
        app.getGuiNode().attachChild(healthBar);
        healthBar.setQueueBucket(RenderQueue.Bucket.Gui);
        
        guiFont = app.getAssetManager().loadFont("Interface/Fonts/Arial.fnt"); // health text
        healthText = new BitmapText(guiFont, false);
        healthText.setSize(30);      // font size
        healthText.setColor(ColorRGBA.White);
        healthText.setText("Health : " + chefBoy.getHealth());
        healthText.setLocalTranslation(50, SCREENHEIGHT - 100, 0);
        app.getGuiNode().attachChild(healthText);
        
    }
    
    /**
     * update to chefboy's heath 
     */
    public void update(){
        healthText.setText("Health : " + chefBoy.getHealth());
        healthBarRed.setWidth(chefBoy.getHealth() * 400 / 100 );
    }
    
}
