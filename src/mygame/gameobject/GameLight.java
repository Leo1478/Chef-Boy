/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

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
    
    private Main main;
    
    public GameLight(Main main){
        this.main = main;
        init();
    }
    
    
    /**
     * init light
     * create directional light to represent sun
     * low ambient light for everything 
     */
    void init(){
        /*
        // TODO can also use different types of light 
        DirectionalLight dl = new DirectionalLight();
        dl.setColor(ColorRGBA.White);
        dl.setDirection(new Vector3f(0f, -30f, 0f).normalizeLocal());
        main.getRootNode().addLight(dl);
        */
        

        
        
        DirectionalLight sun = new DirectionalLight();
        sun.setColor(ColorRGBA.White.mult(0.8f));
        sun.setDirection(new Vector3f(-0.5f, -1f, -0.5f));
        main.getRootNode().addLight(sun);
        

        final int SHADOWMAP_SIZE=512;
        DirectionalLightShadowRenderer dlsr = new DirectionalLightShadowRenderer(main.getAssetManager(), SHADOWMAP_SIZE, 1);
        dlsr.setLight(sun);
        main.getViewPort().addProcessor(dlsr);

        
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
        AmbientLight al = new AmbientLight();
        al.setColor(new ColorRGBA(1f, 0.5f, 0.5f, 1f).mult(0.2f));
        al.setEnabled(true);
        main.getRootNode().addLight(al);
    }
        
}
