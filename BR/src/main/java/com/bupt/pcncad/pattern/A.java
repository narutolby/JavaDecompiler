 package com.bupt.pcncad.pattern;

 import org.springframework.jdbc.core.JdbcTemplate;

 import java.lang.reflect.InvocationTargetException;
 import java.lang.reflect.Method;

 /**
  * Created with IntelliJ IDEA.
  * User: naruto
  * Date: 13-1-19
  * Time: 上午10:21
  * To change this template use File | Settings | File Templates.
  */


 public class A {
     public void print() throws Exception{

     }
     public static void main(String[]args)  {
         A b = new B();
         A c = new C();
         try {
             b.print();
         } catch (Exception e) {
         }
     }
 }
 class B extends A {
     @Override
     public void print()throws Exception{

        System.out.println(1/0);
     }
     public static void main(String[]args,int a) throws Exception{
         Class clazz = new C().getClass();
         Method[]m = clazz.getMethods();
         Method[]m1 = clazz.getDeclaredMethods();
         Method mp = clazz.getDeclaredMethod("print1");
         mp.setAccessible(true);
         mp.invoke(new C());
         for(Method name : m){
             System.out.println(name.getName());
         }
         System.out.println("..........");
         for(Method name : m1){
             System.out.println(name.getName());
         }
     }
 }
 class C extends A {
     private void print1() {
        System.out.println("I am C");
         //JdbcTemplate j = new JdbcTemplate();
         //j.update("");
     }

 }

