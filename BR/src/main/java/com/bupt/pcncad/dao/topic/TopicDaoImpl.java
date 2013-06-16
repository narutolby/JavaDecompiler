package com.bupt.pcncad.dao.topic;

import com.bupt.pcncad.dao.HibernateGenericDaoImpl;
import com.bupt.pcncad.domain.Topic;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 12-10-28
 * Time: 下午4:42
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class TopicDaoImpl extends HibernateGenericDaoImpl<Topic,String> implements ITopicDao{
}
