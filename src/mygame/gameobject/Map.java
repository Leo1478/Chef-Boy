package mygame.gameobject;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.math.Vector3f;


/**
 * Map.java
 * map objects 
 * @author Leo Zeng
 */
public abstract class Map extends GameObject implements Collidable{
    
    private CollisionShape collisionMesh; // mesh to map collision 
    private RigidBodyControl rigidBody; // rigidbody to simulate physical object 
    private BulletAppState bulletAppState; // controls physics 
    
    /**
     * Map
     * constructor
     * @param app application 
     * @param bulletAppState physics 
     * @param position current position 
     * @param name object name
     */
    public Map(SimpleApplication app, BulletAppState bulletAppState, Vector3f position, String name){
        super(app, position, name);
        this.bulletAppState = bulletAppState;
    }
    
    /**
     * initCollision
     * create collisionMesh
     */
    @Override
    public void initCollision() {
        //Vector3f extent = ((BoundingBox) getModel().getWorldBound()).getExtent(new Vector3f());
        //BoxCollisionShape collisionShape = new BoxCollisionShape(extent);
        //setCollisionMesh(collisionShape);
        
        setCollisionMesh(CollisionShapeFactory.createMeshShape(getModel()));
        setRigidBody(new RigidBodyControl(getCollisionMesh(), 0));
        getModel().addControl(getRigidBody());
    }
    
    /**
     * updateCollision
     */
    @Override
    public void updateCollision(){
        
    }
    
    /**
     * deleteCollision
     */
    @Override
    public void deleteCollision(){
        
    }
    
    /**
     * init physics 
     * add physics to bulletAppstate 
     */
    public void initPhysics(){
        bulletAppState.getPhysicsSpace().add(getRigidBody());
    }
    
    /**
     * getCollisionMesh
     * @return the collisionMesh
     */
    public CollisionShape getCollisionMesh() {
        return collisionMesh;
    }

    /**
     * setCollisionMesh
     * @param collisionMesh the collisionMesh to set
     */
    public void setCollisionMesh(CollisionShape collisionMesh) {
        this.collisionMesh = collisionMesh;
    }

    /**
     * setRigidBody
     * @param rigidBody the rigidBody to set
     */
    public void setRigidBody(RigidBodyControl rigidBody) {
        this.rigidBody = rigidBody;
    }

    /**
     * getRigidBody
     * @return the rigidBody
     */
    public RigidBodyControl getRigidBody() {
        return rigidBody;
    }
    
    

    
}
