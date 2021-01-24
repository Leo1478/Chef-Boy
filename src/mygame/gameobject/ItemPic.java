package mygame.gameobject;

import com.jme3.app.SimpleApplication;
import com.jme3.ui.Picture;

/**
 * ItemPic.java
 * @author Leo Zeng
 * 2021/01/15
 */
public class ItemPic {
    
    private Picture picture; // picture of item 
    
    private SimpleApplication app;
    
    private final int PICTUREWIDTH = 100; // size of picture 
    private final int PICTURELENGTH = 100;
    
    /**
     * ItemPic
     * constructor 
     * @param app application 
     * @param path path of file
     */
    ItemPic(SimpleApplication app,  String path){
        
        this.app = app;

        init(path);
    }
    
    /**
     * init 
     * @param path path of file 
     */
    private void init(String path){
        
        picture = new Picture("item pic");
        getPicture().setImage(app.getAssetManager(), path, true);
        getPicture().setWidth(PICTUREWIDTH);
        getPicture().setHeight(PICTURELENGTH);
        getPicture().setPosition(0, 0);
        getPicture().setLocalTranslation(0,0,2);
           
    }
    
    
    /**
     * getPicture
     * @return the picture
     */
    public Picture getPicture() {
        return picture;
    }
    
    /**
     * setPosition 
     * set position of picture 
     * @param x x position 
     * @param y y position
     */
    public void setPosition(int x, int y){
        getPicture().setPosition(x, y);
    }
}
