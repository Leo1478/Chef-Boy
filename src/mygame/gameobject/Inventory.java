/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gameobject;

import com.jme3.app.Application;
import java.util.ArrayList;
import mygame.state.Main;

/**
 *
 * @author leoze
 */
public class Inventory {
    
    private Application app;
    
    Inventory(Application app){
        this.app = app;
    }
    
    ArrayList<Item> itemList = new ArrayList<>();
    
    public void add(Item item){
        itemList.add(item);
    }
    
    public void remove(Item item){
        itemList.remove(item);
    }
    
    
}
