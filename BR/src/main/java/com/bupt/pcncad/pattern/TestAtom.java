package com.bupt.pcncad.pattern;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 13-6-8
 * Time: 下午3:19
 * To change this template use File | Settings | File Templates.
 */
public class TestAtom {
    public static void main(String[]args){
        AtomicInteger ai = new AtomicInteger();
        ai.incrementAndGet();
        ai.decrementAndGet();
        System.out.print(Integer.reverse(0));
    }
}
