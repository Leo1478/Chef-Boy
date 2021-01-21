/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.shadow.DirectionalLightShadowRenderer;
import mygame.state.Main;

/**
 *
 * @author leoze
 */
public class GameLight {
    
    private SimpleApplication app;
    
    public GameLight(SimpleApplication app){
        this.app = app;
        init();
    }
    
    
    /**
     * init light
     * create directional light to represent sun
     * low ambient light for everything 
     */
    private void init(){
        
        /*
        // TODO can also use different types of light 
        DirectionalLight dl = new DirectionalLight();
        dl.setColor(ColorRGBA.White);
        dl.setDirection(new Vector3f(0f, -30f, 0f).normalizeLocal());
        main.getRootNode().addLight(dl);
        */
        
        


        
        // NOTE: use either renderer or filter, rednerer can change cast/receive setting 
        /*
        DirectionalLightShadowFilter dlsf = new DirectionalLightShadowFilter(main.getAssetManager(), SHADOWMAP_SIZE, 1);
        dlsf.setLight(sun);
        dlsf.setEnabled(true);
        FilterPostProcessor fpp = new FilterPostProcessor(main.getAssetManager());
        fpp.addFilter(dlsf);
        main.getViewPort().addProcessor(fpp);
        */
        
        
        
        // ambient light requires material to work 

    }
        
}
