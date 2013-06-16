package com.bupt.pcncad.service.invite;


import com.bupt.pcncad.dao.academy.major.course.ICourseDao;
import com.bupt.pcncad.dao.community.ICommunityDao;
import com.bupt.pcncad.dao.invite.IInviteDao;
import com.bupt.pcncad.dao.user.IUserDao;
import com.bupt.pcncad.domain.Community;
import com.bupt.pcncad.domain.Invite;
import com.bupt.pcncad.domain.User;
import com.bupt.pcncad.webContext.WebContextThreadLocal;
import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhang
 * Date: 13-3-26
 * Time: 下午4:33
 * To change this template use File | Settings | File Templates.
 */
@Service
public class InviteServiceImpl implements  IInviteService {

    @Autowired
    private ICourseDao courseDao;
    @Autowired
    private IInviteDao inviteDao;
    @Autowired
    private ICommunityDao communityDao;
    @Autowired
    private IUserDao userDao;


    public void setUserInvite(String userId,String courseId,String communityId) throws Exception{
        List<Invite> invites = this.inviteDao.find("from Invite i where i.user.id=? and i.course.id=?",userId,courseId);
        Iterator it = invites.iterator();
        while (it.hasNext()) {
            Invite invite = (Invite) it.next();
            invite.setInvite(1);
            invite.setCommunityId(communityId);
            this.inviteDao.save(invite);
        }
    }

    public void setUserInCommunity(String userId,String courseId,String communityId) throws Exception{
        List<Invite> invites = this.inviteDao.find("from Invite i where i.user.id=? and i.course.id=?",userId,courseId);
        Community community = this.communityDao.get(communityId, LockMode.UPGRADE);
        User user = this.userDao.get(WebContextThreadLocal.getCurrentUser().getId());
        user.getCommunities().add(community);
        Iterator it = invites.iterator();
        while (it.hasNext()) {
            Invite invite = (Invite) it.next();
            invite.setInCommunity(1);
            this.inviteDao.save(invite);
        }
    }

    public List<Invite> getInviteCommunities() throws Exception{
        User user = WebContextThreadLocal.getCurrentUser();
        String userId = user.getId();
        List<Invite> invites = this.inviteDao.find("from Invite i where i.user.id=? and i.inCommunity=? and i.invite=?",userId,0,1);
        return invites;
    }

    public List<User> getUserByCourses(String communityId) throws Exception{
        Community community = this.communityDao.get(communityId);
        String courseId = community.getCourse().getId();

        List<User> inviters = this.userDao.find("select u from User u join u.courses c where c.id=? and u not in (select c_u from Community com join com.users c_u where com.course.id=?) and u in (select i.user from Invite i where i.course.id=? and i.invite=?)",courseId,courseId,courseId,0);

        Collections.reverse(inviters);
        return inviters;

    }

    public List<Invite> getInviteByUserId() throws Exception {
        User user = WebContextThreadLocal.getCurrentUser();
        return this.inviteDao.find("from Invite i where i.user.id=?",user.getId());
    }


}
