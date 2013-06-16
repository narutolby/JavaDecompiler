package com.bupt.pcncad.controller.academy;

import com.bupt.pcncad.domain.*;
import com.bupt.pcncad.service.academy.IAcademyService;
import com.bupt.pcncad.service.bookJson.IBookJsonService;
import com.bupt.pcncad.service.community.ICommunityService;
import com.bupt.pcncad.service.invite.IInviteService;
import com.bupt.pcncad.util.HashMapReduce;
import com.bupt.pcncad.webContext.WebContextThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: net$
 * Date: 12-8-31
 * Time: 上午9:44
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/academy")
public class AcademyController {
    @Autowired
    private IAcademyService academyService;

    @Autowired
    private IBookJsonService bookJsonService;

    @Autowired
    private IInviteService inviteService;

    @Autowired
    private ICommunityService communityService;

    /*@RequestMapping("/all")
    public void loadAllAcademy(ModelMap modelMap) throws Exception {
        List<Academy> allMajorList = this.academyService.loadAll();
        List<Major> userMajorList = this.academyService.loadUserMajors();
        modelMap.put("allMajors", allMajorList);
        modelMap.put("userMajors",userMajorList);
    }*/

    /*@RequestMapping("/set_major")
    @ResponseBody
    public boolean setUserMajor(@RequestParam("majors[]") String[] majors) throws Exception{
        boolean flag = false;
        this.academyService.setMajors(majors);
        flag = true;
        return flag;
    }*/
    @RequestMapping("/major/set_course")
    public void setUserFollowCourses(HttpServletRequest request,ModelMap modelMap) throws Exception{
        Map<String,String[]> paramMap = request.getParameterMap();
        this.academyService.setUserFollowCourses(paramMap);
        User user = WebContextThreadLocal.getCurrentUser();
        Set<Course> courseSet = user.getCourses();
        HashMapReduce<String, Set<DoubanJson>> recommendBooks = new HashMapReduce<String, Set<DoubanJson>>();
        for (Course course : courseSet) {
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

        List<Invite> inviteList = this.inviteService.getInviteByUserId();
        HashMap<String, List<DoubanJson>> bookList = new HashMap<String, List<DoubanJson>>();
        for (Invite invite : inviteList) {
            Course course = invite.getCourse();
            List<DoubanJson> bookInfos = this.bookJsonService.getBookByCourse(course.getId());
            bookList.put(course.getName(),bookInfos);
        }
        modelMap.put("bookList", bookList);

        modelMap.put("communities",communityService.getAllCommunitiesByCurrentUserRole());
        modelMap.put("recommendBooks",recommendBooks.getMap());
    }

    @RequestMapping("/all")
    public void getAllAcademies(ModelMap modelMap) throws Exception {
         List<Academy> academies = this.academyService.loadAll();
         modelMap.put("academies",academies);
    }
    @RequestMapping("/majors")
    public void getMajors(ModelMap modelMap,@RequestParam("academyId")String academyId)throws Exception{
          List<Major> majors = this.academyService.loadMajorsByAcademy(academyId);
          modelMap.put("majors",majors);

    }

    @RequestMapping("/major/courses")
    public void getCourses(ModelMap modelMap,@RequestParam("majorId")String majorId)throws Exception{
        Integer term = null;
        if(majorId.indexOf("_")!=-1){
            String[]array = majorId.split("_");
            term = Integer.valueOf(array[1]);
            majorId = array[0];
        }
        List<Course> courses = this.academyService.loadCoursesByMajorInCurrentTerm(majorId,term);
        modelMap.put("courses",courses);
    }

//    @RequestMapping("/coursePage")
//    public void coursePage(ModelMap modelMap,@RequestParam("pageNo")int pageNo) throws Exception{
//        List<Course> pager = this.academyService.loadAllCourse();
//        modelMap.put("pager",pager);
//    }
    @RequestMapping("/updateCourse")
    public void updateKeyword(@RequestParam("keyword")String keyword, @RequestParam("course_name")String courseName) throws Exception{
        this.academyService.updateCourse(keyword,courseName);
    }

}
