package com.bupt.pcncad.service.invite;

import com.bupt.pcncad.domain.Invite;
import com.bupt.pcncad.domain.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhang
 * Date: 13-3-26
 * Time: 下午4:39
 * To change this template use File | Settings | File Templates.
 */
public interface IInviteService {

    public void setUserInvite(String userId,String courseId,String communityId) throws Exception;

    public List<Invite> getInviteCommunities() throws Exception;

    public void setUserInCommunity(String userId,String courseId,String communityId) throws Exception;

    public List<User> getUserByCourses(String communityId) throws Exception;

    public List<Invite> getInviteByUserId() throws Exception;
}
