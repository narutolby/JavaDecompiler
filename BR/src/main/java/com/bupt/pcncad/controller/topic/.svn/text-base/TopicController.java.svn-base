package com.bupt.pcncad.controller.topic;

import com.bupt.pcncad.domain.Community;
import com.bupt.pcncad.domain.SubTopic;
import com.bupt.pcncad.domain.Topic;
import com.bupt.pcncad.domain.User;
import com.bupt.pcncad.service.topic.ITopicService;
import com.bupt.pcncad.util.Pager;
import com.bupt.pcncad.webContext.WebContextThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 12-10-28
 * Time: 下午5:31
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/topic")
public class TopicController {
    @Autowired
    private ITopicService topicService;

    @RequestMapping(value = "/pub")
    public void publicTopic(@RequestParam(value = "topicTitle") String title, @RequestParam(value = "topicContent") String content, @RequestParam(value = "communityId") String communityId, ModelMap modelMap) throws Exception {
        Topic topic = new Topic();
        Community community = new Community();
        community.setId(communityId);
        User user = WebContextThreadLocal.getCurrentUser();
        topic.setAuthor(user);
        topic.setCommunity(community);
        topic.setTopicTitle(title);
        topic.setTopicContent(content);
        topic.setPubDate(new Date());
        List<Topic> topics = this.topicService.saveTopicAndNewTopicListBack(topic);
        modelMap.put("topics", topics);

    }

    @RequestMapping(value = "/detail")
    public String topicDetail(@RequestParam(value = "t_id") String topicId, ModelMap modelMap) throws Exception {
        Topic topic = this.topicService.getTopic(topicId);
        String communityId = topic.getCommunity().getId();
        List<Topic> newTopics = this.topicService.getTopicsByCommunityId(communityId,1).getDomainList();
        modelMap.put("topic", topic);
        modelMap.put("newTopics",newTopics);
        modelMap.put("communityName",topic.getCommunity().getName());
        modelMap.put("t_id",topicId);
        modelMap.put("c_id",topic.getCommunity().getId());
        modelMap.put("isTopicAuthor",topic.getAuthor().getId().equals(WebContextThreadLocal.getCurrentUser().getId()));
       /* Pager<SubTopic> subTopicPager = this.topicService.getSubTopicsByTopicId(topicId,1);
        modelMap.put("subTopicPager",subTopicPager);*/
        return "community/topic/topicDetail";
    }

    @RequestMapping(value = "reply")
    public void replyTopic(@RequestParam(value = "replyTopicId") String replyTopicId, @RequestParam(value = "content") String content,@RequestParam(value = "pageNo")int pageNo, ModelMap modelMap) throws Exception {
        SubTopic subTopic = new SubTopic();
        subTopic.setContent(content);
        Topic topic = new Topic();
        topic.setId(replyTopicId);
        subTopic.setTopic(topic);
        subTopic.setPubDate(new Date());
        subTopic.setUser(WebContextThreadLocal.getCurrentUser());
        Pager<SubTopic> subTopicPager = this.topicService.saveSubTopicAndNewSubtopicListBack(subTopic,pageNo);
        modelMap.put("subTopicPager", subTopicPager);
    }
    @RequestMapping(value = "get")
    public void getSubTopics(@RequestParam(value = "replyTopicId")String topicId,int pageNo,ModelMap modelMap) throws Exception{
        Pager<SubTopic> subTopicPager = this.topicService.getSubTopicsByTopicId(topicId,pageNo );
        modelMap.put("subTopicPager",subTopicPager);
    }
    @RequestMapping(value = "modify")
    public void modifyTopic(ModelMap modelMap,@RequestParam(value = "t_id")String topicId,@RequestParam(value = "topicContent")String content)throws Exception{
       Topic topic = this.topicService.getTopic(topicId);
       topic.setTopicContent(content);
       this.topicService.modifyTopic(topic);
       modelMap.put("success",1);
    }
    @RequestMapping(value = "removeTopic")
    public void removeTopic(@RequestParam(value = "topic_id")String topicId) throws Exception {
        Topic topic = this.topicService.getTopic(topicId);
        this.topicService.removeTopic(topic);
    }
    @RequestMapping(value = "topicPage")
    public void getTopicList(ModelMap modelMap,@RequestParam(value = "pageNo")int pageNo) throws Exception{
        Pager<Topic> pager;
        pager = topicService.getTopicList(pageNo);
        modelMap.put("pager",pager);
    }
}
