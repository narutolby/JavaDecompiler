package com.bupt.pcncad.service.community;

import com.bupt.pcncad.dao.community.ICommunityDao;
import com.bupt.pcncad.dao.invite.IInviteDao;
import com.bupt.pcncad.dao.resource.IResourceDao;
import com.bupt.pcncad.dao.user.IUserDao;
import com.bupt.pcncad.domain.*;
import com.bupt.pcncad.service.Bulletin.IBulletinService;
import com.bupt.pcncad.util.LoggerUtil;
import com.bupt.pcncad.util.Pager;
import com.bupt.pcncad.webContext.WebContextThreadLocal;
import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 12-10-18
 * Time: 上午9:22
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CommunityServiceImpl implements ICommunityService {

    @Autowired
    private ICommunityDao communityDao;
    @Autowired
    private IResourceDao resourceDao;
    @Autowired
    private IInviteDao inviteDao;
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IBulletinService bulletinService;


    @Override
    public List<Community> getAllActivityCommunityByCurrentUser() throws Exception {
        List<Invite> invites = this.inviteDao.find("from Invite i where i.user.id=? and inCommunity=?",WebContextThreadLocal.getCurrentUser().getId(),1);

        return this.communityDao.find("select c from Community c join c.users u where u.id=? and c.state=1",WebContextThreadLocal.getCurrentUser().getId());
        //DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Community.class);
        //detachedCriteria.add(Restrictions.eq("state", (short) 1)).createCriteria("users", "user").add(Restrictions.eq("user.id", WebContextThreadLocal.getCurrentUser().getId()));
       // this.communityDao.findByDetachedCriteria(detachedCriteria);
        //return new Pager<Community>(1, 4, this.communityDao, detachedCriteria).getDomainList();
    }

    @Override
    @Transactional
    public void activeCommunity(@RequestParam(value = "communityId") String communityId) throws Exception {
        User user = this.userDao.get(WebContextThreadLocal.getCurrentUser().getId());
        int role = user.getRole();
        Community community = this.communityDao.get(communityId, LockMode.UPGRADE);
        Course course = community.getCourse();
        String courseId = course.getId();
        if (role == 1) {
            community.setState((short) 1);
        } else if (role == 0) {
            user.getCommunities().add(community);
            List<Invite> invites = this.inviteDao.find("from Invite i where i.user.id=? and i.course.id=?",user.getId(),courseId);
            Iterator it = invites.iterator();
            while (it.hasNext()) {
                Invite invite = (Invite) it.next();
                invite.setInCommunity(1);
                this.inviteDao.save(invite);
            }
        }
    }

    @Override
    public List<User> getNewMemberByCommunityId(String id) throws Exception {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.createCriteria("communities", "c").add(Restrictions.eq("c.id", id));
        long count = this.userDao.getCount(detachedCriteria);
        int start = (int) (count - 10 < 0 ? 0 : count - 10);
        List<User> users = this.userDao.findByHql("select u from User u join u.communities c where c.id=?", start, 10, id);
        Collections.reverse(users);
        return users;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Community> getHottestCommunities() throws Exception {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Community.class);
        detachedCriteria.add(Restrictions.eq("state",(short)1));
        List<Community> list = this.communityDao.findByDetachedCriteria(detachedCriteria,0,5);
        Collections.sort(list, new Comparator<Community>() {
            @Override
            public int compare(Community o1, Community o2) {
                double mark1 = o1.getTopicCount() * 0.5 + o1.getMemberCount() * 0.3 + o1.getResourceCount() * 0.2;
                double mark2 = o2.getTopicCount() * 0.5 + o2.getMemberCount() * 0.3 + o2.getResourceCount() * 0.2;
                if (mark1 > mark2) {
                    return -1;
                } else if (mark1 < mark2) {
                    return 1;
                } else {
                    return 0;
                }

            }
        });
        return list;
    }

    @Override
    public Community getCommunityByCommunityId(String communityId) throws Exception {
        Community community = this.communityDao.get(communityId);
        return community;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Bulletin> publicBulletin(String communityId, String content) throws Exception {
        Community community = this.communityDao.get(communityId);
        Bulletin bulletin = new Bulletin();
        bulletin.setCommunity(community);
        bulletin.setContent(content);
        bulletin.setPubDate(new Date());
        community.getBulletins().add(bulletin);
        this.communityDao.flush();
        this.communityDao.clear();
        return this.bulletinService.getBulletinsByCommunityId(communityId).getDomainList();
    }

    @Override
    public Set<Community> getAllCommunitiesByCurrentUserRole() throws Exception {
        User user = WebContextThreadLocal.getCurrentUser();
        Set<Community> oldCom = user.getCommunities();
        Set<Community> newCom = new HashSet<Community>();
        int role = user.getRole();
        if (role == 1) {
            for (Community community : oldCom) {
                if (community.getState() == 0) {
                    //  System.out.println(community.getState());
                    newCom.add(community);
                }
            }
        } else if (role == 0) {
            Set<Course> courses = user.getCourses();
            for (Course course : courses) {
                Set<Community> communities = course.getCommunities();
                for (Community community : communities) {
                    if (community.getState() == 1 && !oldCom.contains(community)) {
                        newCom.add(community);
                    }
                }
            }
        }
        return newCom;
    }

    @Override
    public Pager<Resource> getCommunityResourcesById(String id,int pageNo) throws Exception {
        Community community = this.communityDao.get(id);
        if (community == null) {
            LoggerUtil.error(this.getClass(), "页面不存在");
            throw new RuntimeException("页面不存在");
        }
        Course course = community.getCourse();
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Resource.class);
        detachedCriteria.add(Restrictions.eq("deleteFlag", 0)).addOrder(Order.desc("uploadTime"));
        detachedCriteria.add(Restrictions.eq("resourceMark",1));
        detachedCriteria.createCriteria("resourceCourses").add(Restrictions.eq("id", course.getId()));
        short role = WebContextThreadLocal.getCurrentUser().getRole();
        int count = 0;
        if(role==1){
           count = 6;
        }else{
           count = 8;
        }
        return new Pager<Resource>(pageNo, count, this.resourceDao, detachedCriteria);
    }
    public List<Invite> getUserByCourses(String communityId) throws Exception{
        Community community = this.communityDao.get(communityId);
        String courseId = community.getCourse().getId();
        //List<Invite> inviters = this.inviteDao.find("from User u where u.id not in(select c.users.id from Community c where c.course.id=?)",courseId,courseId);
        List<Invite> inviters = this.inviteDao.find("select u from User u join u.courses c where c.id=?",courseId);
        //List<Invite> inviters = this.inviteDao.find("from Invite i where i.course.id=? and i.inCommunity=? and i.invite=? and i.user.role=?",courseId,0,0,(short)0);
        Collections.reverse(inviters);
        return inviters;

    }
}
