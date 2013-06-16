package com.bupt.pcncad.dao.invite;

import com.bupt.pcncad.dao.HibernateGenericDaoImpl;
import com.bupt.pcncad.domain.Invite;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: zhang
 * Date: 13-3-26
 * Time: 下午5:08
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class InviteDaoImpl extends HibernateGenericDaoImpl<Invite,String> implements IInviteDao{
}
