package com.bupt.pcncad.util;

import com.bupt.pcncad.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: liboyang01
 * Date: 12-9-17
 * Time: 上午11:09
 * To change this template use File | Settings | File Templates.
 */
public class AuthenticateRole {

    public static void authenticate(HttpServletRequest request, User user,String userIdRuleReg){
        if (user.getRole() == null) {
            Pattern pattern = Pattern.compile(userIdRuleReg);
            Matcher matcher = pattern.matcher(user.getUserId());

            if (user.getUserId().equals("111")){
                request.setAttribute("isTeacher",2);
                user.setRole((short)2);
            }

            else if (matcher.find()) {
                request.setAttribute("isTeacher",1);
                user.setRole((short)1);
            }else{
                request.setAttribute("isTeacher",0);
                user.setRole((short)0);
            }
        }else{
            request.setAttribute("isTeacher",user.getRole());
        }
    }
}
