package com.bupt.pcncad.dao.tag;

import com.bupt.pcncad.dao.HibernateGenericDaoImpl;
import com.bupt.pcncad.domain.Tag;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: liboyang01
 * Date: 12-7-27
 * Time: 下午9:04
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class TagDaoImpl extends HibernateGenericDaoImpl<Tag,String> implements ITagDao{
}
