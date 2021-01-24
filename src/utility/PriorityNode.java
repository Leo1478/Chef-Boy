/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

/**
 *
 * @author leoze
 */
public class PriorityNode<T>{
    private T item;
    private PriorityNode<T> next;
    private PriorityNode<T> prev;
    private int priority;

    PriorityNode(T item){
        this.item = item;
        this.priority = 0;
    }

    PriorityNode(T item, int priority){
        this.item = item;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public PriorityNode<T> getNext() {
        return next;
    }

    public PriorityNode<T> getPrev(){
        return prev;
    }

    public void setNext(PriorityNode<T> next) {
        this.next = next;
    }
    public void setPrev(PriorityNode<T> prev){
        this.prev = prev;
    }

    public T getItem() {
        return item;
    }
}
