package com.bupt.pcncad.util;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: liboyang01
 * Date: 12-9-10
 * Time: 上午10:24
 * To change this template use File | Settings | File Templates.
 */
public class HashMapReduce<T,V> {

    private Map<T,List<V>> map = new HashMap<T,List<V>>();


    public void put(T key,V value){
         if(map.containsKey(key)){
             map.get(key).add(value);
         }else {
             List<V> list = new ArrayList<V>();
             list.add(value);
             map.put(key,list);
         }
    }

    public List<V> get(T key){
        return map.get(key);
    }

    public Set<T> keySet(){
        return map.keySet();
    }

    public Map<T,List<V>> getMap(){
        return this.map;
    }
}
