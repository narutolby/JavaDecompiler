package com.bupt.pcncad.dao.community;

import com.bupt.pcncad.dao.HibernateGenericDaoImpl;
import com.bupt.pcncad.domain.Community;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 12-10-18
 * Time: 上午9:28
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CommunityDaoImpl extends HibernateGenericDaoImpl<Community,String> implements ICommunityDao{
}
