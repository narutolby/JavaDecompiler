package com.bupt.pcncad.dao.academy.major.course;

import com.bupt.pcncad.dao.HibernateGenericDaoImpl;
import com.bupt.pcncad.domain.Course;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: liboyang01
 * Date: 12-9-8
 * Time: 下午6:47
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CourseDaoImpl extends HibernateGenericDaoImpl<Course,String> implements ICourseDao {
}
