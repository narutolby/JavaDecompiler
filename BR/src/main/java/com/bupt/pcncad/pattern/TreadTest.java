package com.bupt.pcncad.pattern;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 13-2-7
 * Time: 下午7:47
 * To change this template use File | Settings | File Templates.
 */
public class TreadTest {
    public static void main(String[]args){
       Runnable r = new Runnable() {
           private int a;
           @Override
           public void run() {
               for(;a<100;a++){
                   System.out.println(Thread.currentThread().getName()+" "+a);
               }
           }
       };
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
    }

}
