package com.bupt.pcncad.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 12-12-3
 * Time: 上午10:22
 * To change this template use File | Settings | File Templates.
 */
public class MutiThread {
    private List<Double> list = new ArrayList<Double>();

    {
        for(int i=0;i<2000;i++){
            list.add(Math.random()*10);
        }
    }




    public void test1(){
        for(int i=0;i<list.size();i++){
            System.out.println("test1:"+i+":"+list.get(i));

        }
    }
    public void test2(){
        this.list = new ArrayList<Double>();
        for(int i=0;i<10;i++){
            list.add(Double.valueOf(i));
        }
    }
    public static void main(String[]args){
        System.out.println(Math.random());
        System.out.println(Math.random());
    }
}
