package com.bupt.pcncad.controller.user;

import com.bupt.pcncad.controller.BaseController;
import com.bupt.pcncad.domain.*;
import com.bupt.pcncad.service.academy.IAcademyService;
import com.bupt.pcncad.service.bookJson.IBookJsonService;
import com.bupt.pcncad.service.community.ICommunityService;
import com.bupt.pcncad.service.invite.IInviteService;
import com.bupt.pcncad.service.resource.IResourceOperationService;
import com.bupt.pcncad.service.usesr.IUserService;
import com.bupt.pcncad.util.*;
import com.bupt.pcncad.webContext.WebContextThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: liboyang01
 * Date: 12-8-9
 * Time: 下午11:05
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User> {

    @Autowired
    private IUserService userService;

    @Autowired
    private IResourceOperationService resourceOperationService;

    @Autowired
    private ICommunityService communityService;

    @Autowired
    private IBookJsonService bookJsonService;

    @Autowired
    private IAcademyService academyService;

    @Autowired
    private IInviteService inviteService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(@ModelAttribute("user") User user, HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "userId", required = true) String userId, @RequestParam(value = "passwd", required = true) String passwd) throws Exception {
        user = this.userService.getUser(userId, passwd);
        PrintWriter pw = response.getWriter();
        if (user != null) {
            Cookie cookie = new Cookie(Constants.BRUSERID, user.getId());
            cookie.setPath("/");
            cookie.setMaxAge(10 * 365 * 24 * 3600);
            response.addCookie(cookie);
            WebContextThreadLocal.setCurrentUser(user);
            LoggerUtil.info(this.getClass(), "当前登录用户为:" + user.getUserName());
            AuthenticateRole.authenticate(request, user, (String) request.getAttribute("userIdRuleReg"));
            request.setAttribute("user", user);
            session.setAttribute("user", user);
            this.userService.flush();
            pw.write("{\"login\" : true ,\"user\":\"" + user.getUserName() + "\",\"isTeacher\":\"" + request.getAttribute("isTeacher") + "\"}");
        } else {
            LoggerUtil.debug(this.getClass(), "用户名或密码错误！");
            pw.write("{\"login\":false}");
        }
        pw.close();
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, @RequestParam("c_p") String redirectPage) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (Constants.BRUSERID.equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    break;
                }
            }
            WebContextThreadLocal.unbind();
        }
        return "redirect:" + redirectPage;
    }

    @RequestMapping("/my_configure")
    public ModelAndView my_configure() throws Exception {
        ModelAndView mav = new ModelAndView();
        User user = WebContextThreadLocal.getCurrentUser();
        if (user != null) {
            int role = user.getRole();
            HashMapReduce<Major, Course> hashMapReduce = new HashMapReduce<Major, Course>();
            String id = user.getId();
            user = this.userService.getUserByUserId(id);
            WebContextThreadLocal.setCurrentUser(user);
            Set<Course> courseSet = user.getCourses();
            HashMapReduce<String, Set<DoubanJson>> recommendBooks = new HashMapReduce<String, Set<DoubanJson>>();
            for (Course course : courseSet) {
                hashMapReduce.put(course.getMajor(), course);
                Set<DoubanJson> recommendBook = new HashSet<DoubanJson>();
                if(course.getDoubanJsons().size() == 0) {
                    String str = null;
                    if(course.getSearchKeyword() == null)
                        str = this.academyService.wordAnalyzer(course.getName(),course);
                    else {
                        str = course.getSearchKeyword();
                        String[] strs = str.split(" ");
                        boolean first = true;
                        for(int i=0; i<strs.length; i++) {
                            if(first) {
                                String para = java.net.URLEncoder.encode(strs[i],"UTF-8");
                                str = para;
                                first = false;
                            }
                            else {
                                String para = java.net.URLEncoder.encode(strs[i],"UTF-8");
                                str = str + ',' + para;
                            }
                        }
                    }
                    //String str = "https://api.douban.com/v2/book/search?apikey=00e256c12217228802a7044366456798&q="+course.getName();
                    recommendBook = this.bookJsonService.getJsonString(str,course.getId());
                }
                else {
                    recommendBook = course.getDoubanJsons();
                }
                recommendBooks.put(course.getName(),recommendBook);
            }
            //String str = this.academyService.wordAnalyzer("Java课程设计");

            List<Invite> inviteList = this.inviteService.getInviteByUserId();
            HashMap<String, List<DoubanJson>> bookList = new HashMap<String, List<DoubanJson>>();
            for (Invite invite : inviteList) {
                Course course = invite.getCourse();
                List<DoubanJson> bookInfos = this.bookJsonService.getBookByCourse(course.getId());
                bookList.put(course.getName(),bookInfos);
            }
            mav.getModel().put("bookList", bookList);

            mav.getModel().put("majorMap", hashMapReduce.getMap());

            //ArrayList<DoubanJson> recommendBooks = this.bookJsonService.getJsonString(str);
            mav.getModel().put("recommendBooks",recommendBooks.getMap());
            //unactiveCommunities对于老师指的是没有创建的社区，对于学生指的是没有加入，但已经存在的社区
            Set<Community> unactiveCommunities = new HashSet<Community>();
            if(role==1){
                unactiveCommunities = this.communityService.getAllCommunitiesByCurrentUserRole();
            }else if(role==0){
               for(Course course:courseSet){
                  Set<Community> communities = course.getCommunities();
                  for(Community community:communities){
                     if(community.getState()==1 && !user.getCommunities().contains(community)){
                         unactiveCommunities.add(community);
                     }
                  }
               }
            }
            mav.getModel().put("unactiveCommunities", unactiveCommunities);
            mav.getModel().put("invitedCommunities", this.inviteService.getInviteCommunities());
            if(role == 2){
                Pager<Resource> pager = this.resourceOperationService.getReviewResource(1);
                mav.getModel().put("pager", pager);
                mav.getModel().put("htmlTag", 1);
                mav.setViewName("admin/reviewResource");
                return  mav;
            }
        }

        mav.setViewName("user/myConfigure");
        return mav;
    }
    @RequestMapping(value = "/p_info")
    public String personalInfo(ModelMap modelMap){
        String userId = WebContextThreadLocal.getCurrentUser().getUserId();
        String email = WebContextThreadLocal.getCurrentUser().getUserEmail();
        modelMap.put("userId",userId);
        modelMap.put("email",email);
        return "user/personalInfo";

    }
    @RequestMapping(value="/modify")
    public void modifyUserInfo(@RequestParam(value="passwd")String passwd,@RequestParam(value="email")String email,ModelMap modelMap)throws Exception{
       User user = WebContextThreadLocal.getCurrentUser();
       User user1 = this.userService.getUserByUserId(user.getId());
       if(null!=email && !"".equals(email.trim())){
         user1.setUserEmail(email);
         user.setUserEmail(email);
       }
       if(null!=passwd && !"".equals(passwd.trim())){
         user1.setUserPasswd(passwd);
       }
       this.userService.flush();
       modelMap.put("submit",true);
    }

    @RequestMapping("/get_book_info")
    public ModelMap getBookInfo()throws Exception {
        ModelMap model = new ModelMap();
        User user = WebContextThreadLocal.getCurrentUser();
        List<Invite> inviteList = this.inviteService.getInviteByUserId();
        //Set<Course> courseSet = user.getCourses();
        HashMap<String, List<DoubanJson>> recommendBooks = new HashMap<String, List<DoubanJson>>();
        for (Invite invite : inviteList) {
            //Set<DoubanJson> bookInfos = course.getDoubanJsons();
            Course course = invite.getCourse();
            List<DoubanJson> bookInfos = this.bookJsonService.getBookByCourse(course.getId());
            recommendBooks.put(course.getName(),bookInfos);
             // model.put(course.getName(),bookInfos)  ;
        }
        model.put("bookList", recommendBooks);
        return model;
    }
    @RequestMapping("/guide")
    public String guide(){
        return "user/guide";
    }
}
