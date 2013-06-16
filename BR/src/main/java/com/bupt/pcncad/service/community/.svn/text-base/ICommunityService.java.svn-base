package com.bupt.pcncad.service.community;

import com.bupt.pcncad.domain.*;
import com.bupt.pcncad.util.Pager;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 12-10-18
 * Time: 上午9:21
 * To change this template use File | Settings | File Templates.
 */
public interface ICommunityService {

    public void activeCommunity(String communityId) throws Exception;

    public List<Community> getAllActivityCommunityByCurrentUser() throws Exception;

    public Set<Community> getAllCommunitiesByCurrentUserRole() throws Exception;

    public Pager<Resource> getCommunityResourcesById(String id,int pageNo) throws Exception;

    public List<User> getNewMemberByCommunityId(String id) throws Exception;

    public List<Community> getHottestCommunities() throws Exception;

    public Community getCommunityByCommunityId(String communityId) throws Exception;

    public List<Bulletin> publicBulletin(String communityId,String content) throws Exception;

    public List<Invite> getUserByCourses(String communityId) throws Exception;
}
