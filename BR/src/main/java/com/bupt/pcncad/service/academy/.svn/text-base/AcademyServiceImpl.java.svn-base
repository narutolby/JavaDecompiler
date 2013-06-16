package com.bupt.pcncad.service.academy;

import com.bupt.pcncad.dao.DoubanJson.IDoubanJsonDao;
import com.bupt.pcncad.dao.academy.IAcademyDao;
import com.bupt.pcncad.dao.academy.major.IMajorDao;
import com.bupt.pcncad.dao.academy.major.course.ICourseDao;
import com.bupt.pcncad.dao.community.ICommunityDao;
import com.bupt.pcncad.dao.user.IUserDao;
import com.bupt.pcncad.domain.*;
import com.bupt.pcncad.service.invite.IInviteService;
import com.bupt.pcncad.strategy.UserRoleStrategy;
import com.bupt.pcncad.webContext.WebContextThreadLocal;
import jeasy.analysis.MMAnalyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;

//import org.apache.lucene.analysis.tokenattributes.TermAttribute;
//import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-8-30
 * Time: 上午10:34
 * To change this template use File | Settings | File Templates.
 */
@Service
public class AcademyServiceImpl implements IAcademyService {

    @Autowired
    private IAcademyDao academyDao;

    @Autowired
    private IMajorDao majorDao;
    @Autowired
    private ICourseDao courseDao;
    @Autowired
    private IUserDao userDao;

    @Autowired
    private IDoubanJsonDao doubanJsonDao;

    @Autowired
    private ICommunityDao communityDao;

    @Autowired
    private IInviteService inviteService;

    private static final int PAGE_SIZE = 13;

    @Override
    public List<Academy> loadAll() throws Exception {
        return this.academyDao.loadAll();
    }

    @Override
    public List<Major> loadMajorsByAcademy(String academyId) throws Exception {
        return this.majorDao.find("from Major major where major.academy.id=?", academyId);
    }

    @Override
    public List<Course> loadCoursesByMajorInCurrentTerm(String majorId, Integer term) throws Exception {
        if (term == null) {
            term = 1;
            User user = WebContextThreadLocal.getCurrentUser();
            String userId = user.getUserId();
            int year = (userId.startsWith("0") ? Integer.valueOf("20" + userId.substring(0, 2)) : Integer.valueOf(userId.substring(0, 4)));
            Calendar calendar = Calendar.getInstance();
            int year1 = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;

            if (month < 9 && month >= 2) {
                term = 2;
            }
            long current = System.currentTimeMillis();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, 8);
            long start = calendar.getTimeInMillis();
            calendar.setTimeInMillis(current - start);
            int diffYear = calendar.get(Calendar.YEAR) - 1970;
            term = 2 * diffYear + term;
        }
        return this.courseDao.find("from Course course where course.major.id=? and (course.term=? or course.term=?)", majorId, term,9);
    }


    @Override
    public List<Course> loadCoursesByMajor(String majorId) throws Exception {
        return this.courseDao.find("from Course course.major.id=?", majorId);
    }

    @Override
    @Transactional
    public void setUserFollowCourses(Map<String, String[]> paramMap) throws Exception {
        User user = WebContextThreadLocal.getCurrentUser();
        int role = user.getRole();
        user = this.userDao.get(user.getId(), LockMode.UPGRADE);
        user.setRole((short)role);
        //用户原来关注的课程
        Set<Course> oldCourses = new HashSet<Course>();
        for(Iterator<Course> iterator=user.getCourses().iterator();iterator.hasNext();){
            oldCourses.add(iterator.next());
        }
        user.getCourses().clear();
        for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
            String majorId = entry.getKey();
            String[] courseIds = entry.getValue()[0].split(",");
            for (String courseId : courseIds) {
                Course course = new Course();
                course.setId(courseId);
                //this.inviteService.setCourseNotIn(courseId);
                user.getCourses().add(course);
            }
        }
        //用户最新关注的课程
        Set<Course> newCourses = user.getCourses();
        //将更改同步到数据库
        this.userDao.flush();
        //清空缓存
        this.userDao.clear();
        user = this.userDao.get(user.getId());
        Set<Community> oldCommunities = user.getCommunities();
        for(Course course:oldCourses){
            //判断取消关注了哪些课程，并对该课程对应的空间
            if(!newCourses.contains(course)){
                 Community community = new Community();
                 community.setName(course.getName()+"_"+user.getUserName());
                //老师角色只有在社区处于为创建的状态时才能将社区从提示社区中删除,学生角色不用判断社区状态
                 if(role==1 && oldCommunities.contains(community) && community.getState()==0 || role==0 && oldCommunities.contains(community)){
                        oldCommunities.remove(community);
                 }
            }
        }
        WebContextThreadLocal.setCurrentUser(user);
        UserRoleStrategy userRoleStrategy = new UserRoleStrategy();
        userRoleStrategy.setUserRole(role);
        userRoleStrategy.communityAction();
    }

    @Override
    public List<Major> loadUserMajors() throws Exception {
        return new ArrayList<Major>(this.userDao.get(WebContextThreadLocal.getCurrentUser().getId()).getMajors());
    }

    @Override
    public Academy getAcademyById(String academyId) throws Exception {
        return this.academyDao.get(academyId);
    }

    public String wordAnalyzer(String word,Course course) {
        //Analyzer analyzer = new IKAnalyzer(true);

        MMAnalyzer analyzer = new MMAnalyzer();
        TokenStream tokenStream = analyzer.tokenStream("", new StringReader(word));
        String str = null;
        boolean first = true;
        String keyword = null;
        try {
            for(Token t=tokenStream.next();t!=null;t=tokenStream.next()){
                if(first) {
                    String para = java.net.URLEncoder.encode(t.termText(),"UTF-8");
                    str = para;
                    keyword = t.termText();
                    first = false;
                }
                else {
                    String para = java.net.URLEncoder.encode(t.termText(),"UTF-8");
                    keyword = keyword + ',' + t.termText();
                    str = str+','+para;
                }
            }
            course.setSearchKeyword(keyword);
            this.courseDao.save(course);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        //TermAttribute termAtt = (TermAttribute) tokenStream.getAttribute(TermAttribute.class);
        //while (tokenStream.incrementToken()) {
        //}
        return str;
    }

    public List<Course> loadAllCourse() throws Exception {
        List<Course> courses = this.courseDao.find("from Course c group by c.name");
        return courses;
    }

    public void updateCourse(String keyword,String courseName) throws Exception{
        List<Course> courses = this.courseDao.find("from Course c where c.name=?",courseName);
        Iterator it = courses.iterator();
        while(it.hasNext()) {
            Course course = (Course)it.next();
            String courseId = course.getId();
            course.setSearchKeyword(keyword);
            this.courseDao.save(course);
            List<DoubanJson> doubanJsons = this.doubanJsonDao.find("from DoubanJson d where d.course.id=?",courseId);
            Iterator ite = doubanJsons.iterator();
            while(ite.hasNext()){
                DoubanJson doubanJson = (DoubanJson)ite.next();
                this.doubanJsonDao.delete(doubanJson);
            }
        }
    }
}
