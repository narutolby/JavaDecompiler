package com.bupt.pcncad.study;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 13-2-24
 * Time: 下午5:40
 * To change this template use File | Settings | File Templates.
 */
public class Parent {
    private static  int a=1;
    public void s(){
        final int c=0;
        class c{
            {
                System.out.print(c);
            }


        }
    }


    static class child{
        {
           System.out.print(a);
        }

    }


}
