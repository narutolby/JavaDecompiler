package com.bupt.pcncad.util;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 * User: liboyang01
 * Date: 12-9-30
 * Time: 下午1:17
 * To change this template use File | Settings | File Templates.
 */
public class BlockingQueue<T> {

    private int length;

    private int size;

    private Queue<T> queue;

    public BlockingQueue(int initLength){
        this.length = initLength;
        this.queue = new LinkedList<T>();
    }
    public Queue<T> getQueue() {
        return queue;
    }

    public void setQueue(Queue<T> queue) {
        this.queue = queue;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getSize() {
        return size;
    }

    public synchronized void  offer(T t){
       if(this.size<this.length){
           queue.offer(t);
           this.size++;
       }else {
           this.poll();
           this.size--;
       }

    }
    public synchronized T poll(){
        return this.queue.poll();
    }

    public T[] toArray(){
        return (T[])this.queue.toArray();
    }

    public boolean isFull(){
        return this.size == this.length;
    }

    public void clear(){
        this.queue.clear();
    }

}
