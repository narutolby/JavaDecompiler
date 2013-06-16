package com.bupt.pcncad.strategy;

import com.bupt.pcncad.domain.Course;
import com.bupt.pcncad.domain.User;
import com.bupt.pcncad.webContext.WebContextThreadLocal;

import java.util.Iterator;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 12-10-18
 * Time: 上午10:13
 * To change this template use File | Settings | File Templates.
 */
/*
*@用户角色类，目前系统有老师和学生两种角色
*/

public  interface UserRole {

    public void communityAction();

}
