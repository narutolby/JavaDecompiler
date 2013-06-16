package com.bupt.pcncad.service.topic;

import com.bupt.pcncad.dao.topic.ISubTopicDao;
import com.bupt.pcncad.dao.topic.ITopicDao;
import com.bupt.pcncad.domain.SubTopic;
import com.bupt.pcncad.domain.Topic;
import com.bupt.pcncad.util.Pager;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 12-10-28
 * Time: 下午5:16
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TopicServiceImpl implements ITopicService {
    @Autowired
    private ITopicDao topicDao;
    @Autowired
    private ISubTopicDao subTopicDao;

    private static final int PAGE_SIZE = 13;

    @Override
    public Pager<Topic> getTopicsByCommunityId(String communityId,int pageNo) throws Exception {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Topic.class);
        detachedCriteria.addOrder(Order.desc("pubDate"));
        detachedCriteria.add(Restrictions.eq("community.id", communityId));
        detachedCriteria.add(Restrictions.eq("deleteFlag",1));
        Pager<Topic> pager = new Pager<Topic>(pageNo, PAGE_SIZE, topicDao, detachedCriteria);
        return pager;
         //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Pager<SubTopic> getSubTopicsByTopicId(String topicId,int pageNo) throws Exception {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SubTopic.class);
        detachedCriteria.add(Restrictions.eq("topic.id",topicId));
        detachedCriteria.addOrder(Order.asc("pubDate"));
        Pager<SubTopic> pager = new Pager<SubTopic>(pageNo, PAGE_SIZE, subTopicDao, detachedCriteria);
        return pager;
    }

    @Override
    public Topic getTopic(String topicId) throws Exception {
          return this.topicDao.get(topicId);
    }

    @Override
    public void modifyTopic(Topic topic) throws Exception {
        this.topicDao.update(topic);
    }

    @Override
    public List<Topic> saveTopicAndNewTopicListBack(Topic topic) throws Exception {
        this.topicDao.save(topic);
        this.topicDao.flush();
        this.topicDao.clear();
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Topic.class);
        detachedCriteria.add(Restrictions.eq("community.id", topic.getCommunity().getId()));
        detachedCriteria.addOrder(Order.desc("pubDate"));
        detachedCriteria.add(Restrictions.eq("deleteFlag",1));
        Pager<Topic> pager = new Pager<Topic>(1, PAGE_SIZE, topicDao, detachedCriteria);
        return pager.getDomainList();
    }

    @Override
    public Pager<SubTopic> saveSubTopicAndNewSubtopicListBack(SubTopic subTopic,int pageNo) throws Exception {
        this.subTopicDao.save(subTopic);
        this.subTopicDao.flush();
        this.subTopicDao.clear();
        Pager<SubTopic> pager = this.getSubTopicsByTopicId(subTopic.getTopic().getId(),pageNo);
        return pager;
    }

    @Override
    public void updateTopic(Topic topic) throws Exception {
        this.topicDao.update(topic);
    }

    public Pager<Topic> getAllTopic(int pageNo) throws Exception {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Topic.class);
        detachedCriteria.add(Restrictions.eq("deleteFlag",1));
        Pager<Topic> topics = new Pager<Topic>(pageNo, PAGE_SIZE, topicDao, detachedCriteria);
        return topics;
    }

    public void removeTopic(Topic topic) throws Exception {
        topic.setDeleteFlag(0);
        this.topicDao.save(topic);
    }

    public Pager<Topic> getTopicList(int pageNo) throws Exception{
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Topic.class);
        detachedCriteria.add(Restrictions.eq("deleteFlag",1));
        Pager<Topic> pager = new Pager<Topic>(pageNo, PAGE_SIZE, topicDao, detachedCriteria);
        return pager;

    }
}
