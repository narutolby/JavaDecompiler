package com.bupt.pcncad.pattern;

import org.hibernate.tuple.component.DynamicMapComponentTuplizer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 13-1-17
 * Time: 下午9:02
 * To change this template use File | Settings | File Templates.
 */
interface lby{
   void a() ;
    void b() ;
}

public class DymicProxy implements lby
{
    public static void main(String[]args){
        lby o = (lby)Proxy.newProxyInstance(DymicProxy.class.getClassLoader(),DymicProxy.class.getInterfaces(),new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.print("ceshi");
                method.invoke(proxy,args);
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
       o.a();
    }

    @Override
    public void a() {
        System.out.print("a");
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void b() {
        System.out.print("b");
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

