package com.bupt.pcncad.service.Bulletin;

import com.bupt.pcncad.dao.bulletin.IBulletinDao;
import com.bupt.pcncad.domain.Bulletin;
import com.bupt.pcncad.util.Pager;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 12-12-18
 * Time: 下午1:19
 * To change this template use File | Settings | File Templates.
 */
@Service
public class BulletinServiceImpl implements IBulletinService {
    @Autowired
    private IBulletinDao bulletinDao;
    private int pageSize = 15;
    @Override
    public Pager getBulletinsByCommunityId(String communityId) throws Exception {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Bulletin.class);
        detachedCriteria.add(Restrictions.eq("community.id",communityId));
        detachedCriteria.addOrder(Order.desc("pubDate"));
        Pager<Bulletin> pager  = new Pager<Bulletin>(1,pageSize,bulletinDao,detachedCriteria);
        return pager;
    }
}
