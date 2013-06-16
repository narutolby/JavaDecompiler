package com.bupt.pcncad.service.Bulletin;

import com.bupt.pcncad.util.Pager;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 12-12-18
 * Time: 下午1:17
 * To change this template use File | Settings | File Templates.
 */
public interface IBulletinService {

    Pager getBulletinsByCommunityId(String communityId)throws Exception;
}
