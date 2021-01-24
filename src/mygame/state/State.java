package mygame.state;

import com.jme3.app.state.AbstractAppState;

/**
 * State.java
 * parent class for all states 
 * @author Leo Zeng
 * 2021/01/12
 */
public abstract class State extends AbstractAppState{
    

    /**
     * enterState
     * enable state in stateManager
     */
    public void enterState(){       
        setEnabled(true);
    }

    /**
     * exitState
     * disable state in stateManager
     */
    public void exitState() {
        setEnabled(false);
    }
    
    /**
     * init all objects for the state 
     */
    public abstract void init();
    
    /**
     * remove all graphics from rootNode or guiNode 
     * delete objects from lists 
     */
    public abstract void cleanUp();
    
}
