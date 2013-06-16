package com.bupt.pcncad.controller.resource;

import com.bupt.pcncad.controller.BaseController;
import com.bupt.pcncad.domain.Resource;
import com.bupt.pcncad.domain.User;
import com.bupt.pcncad.service.resource.IResourceOperationService;
import com.bupt.pcncad.service.usesr.IUserService;
import com.bupt.pcncad.util.Pager;
import com.bupt.pcncad.webContext.WebContextThreadLocal;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liboyang01
 * Date: 12-8-16
 * Time: 上午9:25
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/elec_resrc")
public class ElecResourceController extends BaseController<Resource> {

    private static final String ELECRESRCROOTPATH = "electronicResource";

    @Autowired
    private IResourceOperationService resourceOperationService;

    @Autowired
    private IUserService userService;


    @RequestMapping("/resrc_rec_tmpl")
    public void template(ModelMap model) {
//          model.addAttribute("template", ModelAndTemplate.getResourceTemplate());
        List<Map<String, Object>> modelList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 4; i++) {
            Map<String, Object> modelMap = new HashMap<String, Object>();
            modelMap.put("imgSrc", "img/book_1.jpg");
            modelMap.put("bookName", "Java 编程思想");
            modelMap.put("description", "(美)埃克尔 著，陈浩鹏 译/2007/12/PDF");
            modelMap.put("commentUserCount", "100");
            modelMap.put("digest", " 如果时光可以重来，\n" +
                    "                             我会鼓起勇气问，我们在一起，好不好？\n" +
                    "                             如果时光可以重来，\n" +
                    "                             我会拿起电话说，对不起，是我太幼稚了。\n" +
                    "                             时光可以重来吗？\n" +
                    "                             不能。\n" +
                    "                             一刀未剪！剧本数版原稿...");
            modelList.add(modelMap);
        }

        model.addAttribute("model", modelList);

    }

    /*
   *系统推荐资源
    */
    @RequestMapping("/resrc_sys_rec")
    public String electronicResourceSystemRecommend(ModelMap model) {
        List<Map<String, Object>> modelList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 4; i++) {
            Map<String, Object> modelMap = new HashMap<String, Object>();
            modelMap.put("imgSrc", "img/book_1.jpg");
            modelMap.put("bookName", "Java 编程思想");
            modelMap.put("description", "(美)埃克尔 著，陈浩鹏 译/2007/12/PDF");
            modelMap.put("commentUserCount", "100");
            modelMap.put("digest", " 如果时光可以重来，\n" +
                    "                             我会鼓起勇气问，我们在一起，好不好？\n" +
                    "                             如果时光可以重来，\n" +
                    "                             我会拿起电话说，对不起，是我太幼稚了。\n" +
                    "                             时光可以重来吗？\n" +
                    "                             不能。\n" +
                    "                             一刀未剪！剧本数版原稿...");
            modelList.add(modelMap);
        }

        model.addAttribute("model", modelList);
        return ELECRESRCROOTPATH + "/resourceSystemRecommend";
    }

    /*
   *好友推荐资源
    */
    @RequestMapping("/resrc_friend_rec")
    public String electronicResourceFriendRecommend() {
        return ELECRESRCROOTPATH + "/resourceFriendRecommend";

    }

    /*
   *电子资源详细页面
    */
    @RequestMapping("/elec_resrc_detail")
    public String electronicResourceDetail(@RequestParam(value="resourceId")String resourceId,ModelMap modelMap)throws Exception{
        String[] resourceIds = {resourceId};
        List<Resource> listR = this.resourceOperationService.map(resourceIds);
        Resource resource = listR.get(0);
        modelMap.put("resource",resource);
        return ELECRESRCROOTPATH + "/electronicResourceDetail";
    }

    @RequestMapping("/tag_all_elec_resrc")
    public String tagAllElectronicResourceView() {
        return ELECRESRCROOTPATH + "/tagAllElectronicResourceView";

    }


    /*
    *资源上传页面
     */
    @RequestMapping("/upload")
    public String uploadResource(ModelMap model) throws Exception{
        User user = WebContextThreadLocal.getCurrentUser();
        int role = user.getRole();

        if(role == 2){
            Pager<Resource> pager = this.resourceOperationService.getRefusedResource(1);
            model.addAttribute("pager", pager);
            model.addAttribute("htmlTag",3);
            return  "admin/reviewResource";
        }
        return ELECRESRCROOTPATH + "/uploadResource";
    }

    /*
   *我的资源
    */
    @RequestMapping("/my_resrc")
    public String myResource(ModelMap model, @RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize,@RequestParam(value="order",required = false)String order,@RequestParam(value="resourceType",required = false)String resourceType,@RequestParam(value="courseId",required = false)String courseId) throws Exception{

        User user = WebContextThreadLocal.getCurrentUser();
        int role = user.getRole();

        pageNo = (pageNo == null) ? 1 : pageNo;
        pageSize = (pageSize == null) ? 12 : pageSize;
        Order o = null;
        if(order==null || "".equals(order)){
        	order = "uploadTime_desc";
        }
        if(resourceType==null || "".equals(resourceType)){
        	resourceType = "all";
        }
        String[]queryCondition = order.split("_");
        String descOrAsc = queryCondition[1];
        String orderProperty = queryCondition[0];
        if(descOrAsc.equalsIgnoreCase("asc")){
        	o = Order.asc(orderProperty);
        }else if(descOrAsc.equalsIgnoreCase("desc")){
        	o = Order.desc(orderProperty);
        }
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Resource.class).addOrder(o);
        if(!"all".equalsIgnoreCase(resourceType) && !"other".equalsIgnoreCase(resourceType)){
        	String[]resourceTypeArray = resourceType.split("\\|");
        	detachedCriteria.add(Restrictions.in("resourceType", resourceTypeArray));
        }
        if("other".equalsIgnoreCase(resourceType)){
        	detachedCriteria.add(Restrictions.not(Restrictions.in("resourceType", new String[]{"pdf","doc","docx","ppt","pptx","pps","txt"})));
        }
        //detachedCriteria.add(Restrictions.eq("uploadUser.id",WebContextThreadLocal.getCurrentUser().getId())).add(Restrictions.eq("deleteFlag",0));
        detachedCriteria.add(Restrictions.eq("deleteFlag",0));

        Pager<Resource> pager;
        if(role == 2){
            //detachedCriteria.createAlias("uploadUser","user");
            //detachedCriteria.add(Restrictions.eq("resourceMark",0));
            //detachedCriteria.add(Restrictions.not(Restrictions.like("user.userId","20108%")));
            /*if(){
                resourceMark = 1;
            }*/
            pager = this.resourceOperationService.getPassResource(1);
            model.addAttribute("htmlTag",2);
        }
        else{

            detachedCriteria.createCriteria("resourceCourses").add(Restrictions.eq("id", courseId));
            detachedCriteria.add(Restrictions.eq("resourceMark",1));
            pager=this.resourceOperationService.loadResource(pageNo,pageSize,detachedCriteria);
        }


        //Pager<Resource> pager=this.resourceOperationService.loadResource(pageNo,pageSize,detachedCriteria);

        model.addAttribute("pager", pager);
        /*  DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Resource.class);
       / detachedCriteria.*/
        if(role == 2)
            return "admin/reviewResource";
        return ELECRESRCROOTPATH + "/myResource";
    }

    /*@RequestMapping("/reviewList")
    public void demo(HttpServletRequest request,ModelMap model) throws Exception{
        Map<String,String> paramMap = request.getParameterMap();
        Iterator it = paramMap.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry entry = (Map.Entry)it.next();
            String resourceId = (String)entry.getKey();
            String[] mark = (String[])entry.getValue();
            //int flag = ()mark[0];
            if(mark[0].equals("1"))
                this.resourceOperationService.updateResource(resourceId);
            else
                this.resourceOperationService.deleteResource(resourceId);
        }

        //model.put("communities",communityService.getAllCommunitiesByCurrentUserRole());
    }  */

    @RequestMapping("/reviewList")
    public void demo(ModelMap model,@RequestParam(value = "resourceId", required = false) String resourceId,@RequestParam(value = "mark", required = false) Integer mark) throws Exception{
        if(mark == 1)
            this.resourceOperationService.updateResource(resourceId);
        else
            this.resourceOperationService.deleteResource(resourceId);
    }

    @RequestMapping("/reviewPage")
    public void getReviewPager(@RequestParam(value = "pageNo")int pageNo,@RequestParam(value = "htmlTag")int htmlTag,ModelMap modelMap) throws Exception{
        Pager<Resource> pager;
        if(htmlTag == 1)
            pager = resourceOperationService.getReviewResource(pageNo);
        else if(htmlTag ==2)
            pager = resourceOperationService.getPassResource(pageNo);
        else
            pager = resourceOperationService.getRefusedResource(pageNo);
        modelMap.put("pager",pager);
    }

    @RequestMapping("/invite")
    public void getInviteUser(@RequestParam(value = "UserId")String userId,@RequestParam(value = "CommunityId")String communityId) throws Exception{

    }


    @RequestMapping("/my_resrc_tmpl")
    public void myResourceTemplate(ModelMap model) throws Exception{
        List<Map<String, Object>> modelList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 12; i++) {
            Map<String, Object> modelMap = new HashMap<String, Object>();
            modelMap.put("imgSrc", "img/book_2.jpg");
            modelMap.put("resrcName", "资源名称");
            //  modelMap.put("alia",)
            modelList.add(modelMap);
        }
        model.addAttribute("model", modelList);
    }

    @RequestMapping("/resrc_detail_tmpl")
    public void resourceDetailTemplate(ModelMap model) throws Exception{
        List<Map<String, Object>> modelList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 12; i++) {
            Map<String, Object> modelMap = new HashMap<String, Object>();
            modelMap.put("resrcName", "最美的，徒劳无功");
            modelMap.put("resrcSubtitle", "九把刀 / 2012-7 / 朝华出版社 / 35.00元 / 平装");
            modelMap.put("resrcDigest", " 如果时光可以重来，\n" +
                    "            我会鼓起勇气问，我们在一起，好不好？\n" +
                    "            如果时光可以重来，\n" +
                    "            我会拿起电话说，对不起，是我太幼稚了。\n" +
                    "            时光可以重来吗？\n" +
                    "            不能。\n" +
                    "            所以有了这一个无法重来的故事。\n" +
                    "            ——九把刀\n" +
                    "            感激！电影《那些年，我们一起追的女孩》\n" +
                    "            勇夺香港电影金像奖，最佳两岸华语电影\n" +
                    "            九把刀：谢谢这个世界，包容了我的幼稚\n" +
                    "            《那些年，我们一起追的女孩》封笔之作\n" +
                    "            一刀未剪！剧本数版原稿...");
            modelList.add(modelMap);
        }
        model.addAttribute("model", modelList);
    }


}
