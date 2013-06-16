package com.bupt.pcncad.service.topic;

import com.bupt.pcncad.domain.SubTopic;
import com.bupt.pcncad.domain.Topic;
import com.bupt.pcncad.util.Pager;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 12-10-28
 * Time: 下午5:11
 * To change this template use File | Settings | File Templates.
 */
public interface ITopicService {


    public Pager<Topic> getTopicsByCommunityId(String communityId,int pageNo) throws  Exception;

    public Pager<SubTopic> getSubTopicsByTopicId(String topicId,int pageNo) throws Exception;

    public Topic getTopic(String topicId) throws Exception;

    public void modifyTopic(Topic topic) throws Exception;

    public List<Topic> saveTopicAndNewTopicListBack(Topic topic) throws Exception;

    public Pager<SubTopic> saveSubTopicAndNewSubtopicListBack(SubTopic subTopic,int pageNo) throws Exception;

    public void updateTopic(Topic topic) throws Exception;

    public Pager<Topic> getAllTopic(int pageNo) throws Exception;

    public void removeTopic(Topic topic) throws Exception;

    public Pager<Topic> getTopicList(int pageNo) throws Exception;
}
