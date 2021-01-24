package ui;

import com.jme3.app.SimpleApplication;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.ui.Picture;
import java.awt.Rectangle;

/**
 * Button.java
 * buttons for menu and setting 
 * @author Leo Zeng
 * 2021/01/03
 */
public class Button {
    
    private Rectangle hitBox; // rectangle size of button
    private Picture picture; // picture of button
    private String path;
    private boolean clicked; // if button is clicked
    private SimpleApplication app;
    
    /**
     * Button
     * constructor, init button 
     * @param app application 
     * @param hitBox rectangle size 
     * @param path path in folder 
     */
    public Button(SimpleApplication app, Rectangle hitBox, String path){
        this.hitBox = hitBox;
        this.path = path;
        this.clicked = false;
        this.app = app;
        init();
    }
    
    /**
     * init
     * initialise picture 
     */
    private void init(){

        Picture picture = new Picture("button");
        picture.setImage(app.getAssetManager(), path, true);
        picture.setWidth(hitBox.width);
        picture.setHeight(hitBox.height);
        picture.setPosition(hitBox.x, hitBox.y);
        picture.setLocalTranslation(hitBox.x, hitBox.y, 2);

        app.getGuiNode().attachChild(picture);
        picture.setQueueBucket(RenderQueue.Bucket.Gui);
        
    }

    /**
     * getHitBox
     * @return the hitBox
     */
    public Rectangle getHitBox() {
        return hitBox;
    }

    /**
     * setHitBox
     * @param hitBox the hitBox to set
     */
    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
    }

    /**
     * getPicture
     * @return the picture
     */
    public Picture getPicture() {
        return picture;
    }

    /**
     * setPicture
     * @param picture the picture to set
     */
    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    /**
     * isClicked
     * @return the clicked
     */
    public boolean isClicked() {
        return clicked;
    }

    /**
     * setClicked
     * @param clicked the clicked to set
     */
    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
}
