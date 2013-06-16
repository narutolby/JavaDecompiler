package com.bupt.pcncad.dao.academy.major;

import com.bupt.pcncad.dao.HibernateGenericDaoImpl;
import com.bupt.pcncad.domain.Major;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: liboyang01
 * Date: 12-9-8
 * Time: 下午6:43
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class MajorDaoImpl extends HibernateGenericDaoImpl<Major,String> implements IMajorDao {
}
