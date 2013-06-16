package com.bupt.pcncad.strategy;

import com.bupt.pcncad.domain.Community;
import com.bupt.pcncad.domain.Course;
import com.bupt.pcncad.domain.User;
import com.bupt.pcncad.webContext.WebContextThreadLocal;

import java.util.Iterator;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 12-10-18
 * Time: 上午10:15
 * To change this template use File | Settings | File Templates.
 */
public class Teacher implements UserRole {
    @Override
    public void communityAction() {
        User user = WebContextThreadLocal.getCurrentUser();
        Set<Course> courses = user.getCourses();
        Set<Community> communities = user.getCommunities();
        for (Iterator<Course> iterator = courses.iterator(); iterator.hasNext(); ) {
            Community community = new Community();
            Course course = iterator.next();
            community.setAdmin(user);
            community.setName(course.getName() + "_" + user.getUserName());
            community.setState((short) 0);
            community.setCourse(course);
            communities.add(community);
        }
    }
}
