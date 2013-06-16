package com.bupt.pcncad.service.usesr;

import com.bupt.pcncad.dao.user.IUserDao;
import com.bupt.pcncad.domain.Course;
import com.bupt.pcncad.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: liboyang01
 * Date: 12-7-28
 * Time: 上午1:12
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Override
    public User getUserByUserId(String userId) throws Exception {
        User user = this.userDao.findEntity("from User user where user.id=?", userId);
      //  Set<Course> courseSet = user.getCourses();
        if (user == null) {
            return null;
        } else {
            return user;
        }
    }

    @Override
    public User getUser(String userId, String userPasswd) throws Exception {
        User user = null;
        user = this.userDao.findEntity("from User user where user.userId=? and userPasswd=?", userId, userPasswd);
        return user;
    }

    @Override
    public int getUsersCount() throws Exception {
        return this.userDao.find("from User user").size();
    }

    @Override
    public void flush() throws Exception {
        this.userDao.flush();
    }
}
