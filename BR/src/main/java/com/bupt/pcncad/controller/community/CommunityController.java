package com.bupt.pcncad.controller.community;

import com.bupt.pcncad.domain.*;
import com.bupt.pcncad.service.Bulletin.IBulletinService;
import com.bupt.pcncad.service.academy.IAcademyService;
import com.bupt.pcncad.service.community.ICommunityService;
import com.bupt.pcncad.service.invite.IInviteService;
import com.bupt.pcncad.service.mail.MailService;
import com.bupt.pcncad.service.topic.ITopicService;
import com.bupt.pcncad.util.Pager;
import com.bupt.pcncad.webContext.WebContextThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhaomeng
 * Date: 12-8-9
 * Time: 下午11:13
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/community")
public class CommunityController {

    private static final String COMMUNITYROOTPATH = "community";

    @Autowired
    private ICommunityService communityService;
    @Autowired
    private ITopicService topicService;
    @Autowired
    private MailService mailService;
    @Autowired
    private IBulletinService bulletinService;
    @Autowired
    private IInviteService inviteService;
    @Autowired
    private IAcademyService academyService;
    @RequestMapping("/my_community")
    public String myCommunity(ModelMap modelMap) throws Exception {

        User user = WebContextThreadLocal.getCurrentUser();
        if(user.getRole() == 2) {
            List<Course> courses = this.academyService.loadAllCourse();
            modelMap.put("courses",courses);
            return "admin/setSearchKeyword";
        }

        List<Community> communityList = this.communityService.getAllActivityCommunityByCurrentUser();

        List<Community> hottestCommunities = this.communityService.getHottestCommunities();
        modelMap.put("communities", communityList);
        int size = communityList.size();
        int pageCount = size/4+(size%4==0?0:1);
        modelMap.put("pageCount",pageCount);
        modelMap.put("hottestCommunities", hottestCommunities);
        return COMMUNITYROOTPATH + "/myCommunity";
    }

    @RequestMapping("/active")
    public void activeCommunity(String communityId) throws Exception {
        this.communityService.activeCommunity(communityId);
    }

    @RequestMapping("/unactive_community")
    public void getAllUnCommunity(ModelMap modelMap) throws Exception {
        modelMap.put("unactiveCommunities", this.communityService.getAllCommunitiesByCurrentUserRole());
    }

    @RequestMapping("/active_invite")
    public void joinCommunity(@RequestParam(value = "user_id")String user_id,@RequestParam(value = "course_id")String course_id,@RequestParam(value = "community_id")String community_id) throws Exception {
        this.inviteService.setUserInCommunity(user_id,course_id,community_id);
    }


    @RequestMapping("/community_detail")
    public ModelAndView getCommunityDetail(@RequestParam(value = "c_id") String communityId) throws Exception {
        ModelAndView mav = new ModelAndView();
        Pager<Resource> resourcesPager = this.communityService.getCommunityResourcesById(communityId,1);
        Pager<Topic> topicsPager = topicService.getTopicsByCommunityId(communityId,1);
        Pager<Bulletin> bulletinPager = this.bulletinService.getBulletinsByCommunityId(communityId);
        List<User> newMembers = this.communityService.getNewMemberByCommunityId(communityId);

        //List<Invite> studentSelectedCourse = this.communityService.getUserByCourses(communityId);
        List<User> studentSelectedCourse = this.inviteService.getUserByCourses(communityId);

        Community community = this.communityService.getCommunityByCommunityId(communityId);
        String adminId= community.getAdmin().getId();
        byte isAdmin = (byte) (WebContextThreadLocal.getCurrentUser().getId().equals(adminId)==true?1:0);
        String courseId = community.getCourse().getId();
        String communityName = community.getName();
        mav.getModelMap().put("courseId", courseId);
        mav.getModelMap().put("resourcesPager", resourcesPager);
        mav.getModelMap().put("subTopicPager", topicsPager);
        mav.getModelMap().put("newMembers", newMembers);

        mav.getModelMap().put("student",studentSelectedCourse);

        mav.getModelMap().put("communityName", communityName);
        mav.getModelMap().put("c_id", communityId);
        mav.getModelMap().put("bulletins",bulletinPager.getDomainList());
        mav.getModelMap().put("isAdmin",isAdmin);
        mav.setViewName(COMMUNITYROOTPATH + "/" + "communityDetail");
        return mav;
    }

    @RequestMapping("/pub_bull")
    public void pubBulletin(@RequestParam(value = "communityId")final String communityId, @RequestParam(value = "content")final String content,ModelMap modelMap,@RequestParam (value = "mail")int mail) throws Exception {
        List<Bulletin> bulletins = this.communityService.publicBulletin(communityId,content);
        if(mail==1){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        mailService.sendMimeMessageOnPublicBullitin(communityId,content);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        modelMap.put("bulletins",bulletins);
    }

    @RequestMapping("/get_resc")
    public void getResourcesPager(@RequestParam(value="communityId")String communityId,@RequestParam(value="pageNo")int pageNo,ModelMap modelMap) throws Exception{
        Pager<Resource> pager = this.communityService.getCommunityResourcesById(communityId,pageNo);
        modelMap.put("resourcePager",pager);
    }
    @RequestMapping("/get_topics")
    public void getTopicsPager(@RequestParam(value = "communityId")String communityId,@RequestParam(value = "pageNo")int pageNo,ModelMap modelMap) throws Exception{
        Pager<Topic> topicPager = topicService.getTopicsByCommunityId(communityId,pageNo);
        modelMap.put("topicPager",topicPager);
    }
    @RequestMapping("/invite")
    public void getInviters(@RequestParam(value = "UserId")String userId,@RequestParam(value = "CommunityId")String communityId) throws Exception{
       Community community = this.communityService.getCommunityByCommunityId(communityId);
        String courseId = community.getCourse().getId();
        this.inviteService.setUserInvite(userId,courseId,communityId);
    }
    @RequestMapping("/delete_topic")
    public void deleteTopic(@RequestParam(value = "communityId")String communityId,@RequestParam(value="t_id")String topicId,@RequestParam(value="pageNo")int pageNo,ModelMap modelMap)throws Exception{
        Topic topic = this.topicService.getTopic(topicId);
        topic.setDeleteFlag(0);
        this.topicService.updateTopic(topic);
        this.getTopicsPager(communityId,pageNo,modelMap);
    }
}
