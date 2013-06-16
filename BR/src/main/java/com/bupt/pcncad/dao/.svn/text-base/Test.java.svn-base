package com.bupt.pcncad.dao;

import com.bupt.pcncad.domain.SubTopic;

import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * User: liboyang01
 * Date: 12-9-2
 * Time: 下午6:14
 * To change this template use File | Settings | File Templates.
 */
public class Test {




    public void aa(int... c) {
        System.out.println(c.length);
    }

    public static void main(String[]ag) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class clazz = SubTopic.class;
        SubTopic subTopic = (SubTopic) clazz.newInstance();
        Field field = clazz.getDeclaredField("role") ;
        field.setAccessible(true);
        field.setShort(subTopic,(short)1);
         field.get(subTopic);
    }
}
