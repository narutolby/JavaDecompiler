package com.bupt.pcncad.controller;

import com.bupt.pcncad.domain.HotBook;
import com.bupt.pcncad.domain.Resource;
import com.bupt.pcncad.domain.Topic;
import com.bupt.pcncad.domain.User;
import com.bupt.pcncad.service.fetch.IFetchBookInfoService;
import com.bupt.pcncad.service.resource.IResourceOperationService;
import com.bupt.pcncad.service.topic.ITopicService;
import com.bupt.pcncad.util.Pager;
import com.bupt.pcncad.webContext.WebContextThreadLocal;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;


@Controller
public class BaseController<T extends Serializable> {

    @Autowired
    private ITopicService topicService;
    @Autowired
    private IFetchBookInfoService fetchBookInfoService;
    @Value("${hotTagsUrl}")
    private String hotTagsUrl;//from bq;
    @Autowired
    private IResourceOperationService operationService;
    @Value("${indexPage}")
    private String indexPage;

    @RequestMapping("/index")
    public String index() {
        //return "redirect:user/my_configure.html";
        return "redirect:" + indexPage;
    }

    @RequestMapping("/index1")
    public String index1(ModelMap map) throws Exception {
        List<HotBook> hotBooksList = this.fetchBookInfoService.getAllHotBooks(0);
        User user = WebContextThreadLocal.getCurrentUser();
        List<HotBook> hotWordBooksList = new ArrayList<HotBook>();
        Map<String,String> shareUser = this.operationService.getTop4ShareUser();
        if (user == null) {
            hotWordBooksList = this.fetchBookInfoService.getAllHotBooks(1);
        } else {
            try {
                if (user.getRole() == 2) {
                    Pager<Topic> topicPager = this.topicService.getAllTopic(1);
                    map.put("topics", topicPager);
                    return "admin/checkTheme";
                }
                hotWordBooksList = this.fetchBookInfoService.getRecommendedBookByUserId(user.getUserId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (hotWordBooksList.size() == 0) {
            hotWordBooksList = this.fetchBookInfoService.getAllHotBooks(1);
        }
        long listSize = hotBooksList.size();
        long listSize1 = hotWordBooksList.size();
        Set<HotBook> sets1 = new LinkedHashSet<HotBook>(24);
        if (listSize != 0) {
            Collections.shuffle(hotBooksList);
        }
        long maxSize = (listSize1 > 24 ? 24 : listSize1);
        while (sets1.size() != maxSize) {
            long index = (long) (Math.random() * listSize1);
            sets1.add(hotWordBooksList.get((int) index));
        }
        URL url = new URL(this.hotTagsUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line.trim());
        }
        line = sb.toString();
        JSONObject jsonObject = new JSONObject(line);
        JSONArray tags= jsonObject.getJSONArray("list");
        List<String> tagList = new ArrayList<String>();
        for(int i=0;i<tags.length();i++){
             tagList.add(tags.getString(i));
        }
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Resource.class);
        detachedCriteria.addOrder(Order.desc("downloadTimes"));
        Pager<Resource> pager = this.operationService.loadResource(0, 4, detachedCriteria);
        map.put("hotBooks", hotBooksList);
        map.put("hotWordBooks", sets1);
        map.put("tags", tagList);
        map.put("resources", pager.getDomainList());
        map.put("shareUser",shareUser);
        return "index1";
    }


}
