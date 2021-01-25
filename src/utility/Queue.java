package utility;

/**
 * Queue.java
 * queue with priority 
 * @author Leo Zeng
 * 2021/01/13
 */
public class Queue<E>{

    private PriorityNode<E> head;
    private PriorityNode<E> tail;
    private int size;

    /**
     * Queue
     * constructor
     */
    public Queue(){
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * enqueue
     * add object to queue
     * @param item item to add
     */
    public void enqueue(E item){
        PriorityNode<E> temp = new PriorityNode<E>(item);

        if(head == null) {//if list is empty, head and tail points to newNode
            head = tail = temp;
            head.setPrev(null);
            tail.setNext(null);
        }
        else {
            tail.setNext(temp);//add newNode to the end of list. tail->next set to newNode
            temp.setPrev(tail);
            tail = temp;
            tail.setNext(null);
        }
        size ++;
    }

    /**
     * enqueue
     * enqueue with priority
     * @param item item to add
     * @param priority priority number
     */
    public void enqueue(E item, int priority){
        PriorityNode<E> temp = new PriorityNode<E>(item, priority);

        if(head == null) {//if list is empty, head and tail points to newNode
            head = tail = temp;
            head.setPrev(null);
            tail.setNext(null);
        }
        else {
            if(temp.getPriority() > head.getPriority()){ // if priority is greater than head
                head.setPrev(temp); // replace head with current node
                temp.setNext(head);
                head = temp;

            }
            else{ // if priority is less than head
                PriorityNode<E> current = tail;

                while(current.getPrev() != null && current.getPriority() < temp.getPriority()){
                    current = current.getPrev(); // start from tail and find node that is 1 smaller
                }

                if(current.equals(tail)){ // current is at tail
                    tail = temp; // replace tail
                    tail.setNext(null);
                }
                else{ // if current and temp is between head and tail
                    current.getNext().setPrev(temp);
                    temp.setNext(current.getNext());
                }
                current.setNext(temp);
                temp.setPrev(current);
            }
        }
        size ++;
    }

    /**
     * dequeue
     * remove item from queue
     * @return item 
     */
    public E dequeue(){
        PriorityNode<E> temp = head; // remove from head
        head = head.getNext();
        size --;
        return temp.getItem();
        
    }
    
    /**
     * size of queue
     * @return size
     */
    public int size(){
        return size;
    }
}
