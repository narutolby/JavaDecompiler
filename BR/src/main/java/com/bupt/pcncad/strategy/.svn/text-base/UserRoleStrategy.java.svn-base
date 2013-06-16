package com.bupt.pcncad.strategy;

import com.bupt.pcncad.dao.user.IUserDao;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 12-10-18
 * Time: 上午10:13
 * To change this template use File | Settings | File Templates.
 *
 */

/*
*  用户角色策略类，根据不同的用户角色执行不同的动作
*/
public class UserRoleStrategy {

    private UserRole userRole;

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        if(userRole==0){
            this.userRole = new Student();
        } else if(userRole==1){
            this.userRole = new Teacher();
        }
    }

    public void  communityAction(){
        this.userRole.communityAction();
    }
}
