package com.bupt.pcncad.util;

import com.bupt.pcncad.dao.HibernateGenericDaoImpl;
import com.bupt.pcncad.dao.IHibernateGenericDao;
import com.bupt.pcncad.domain.Resource;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.bupt.pcncad.util.Constants.PAGE_NO;

/**
 * Created with IntelliJ IDEA.
 * User: liboyang01
 * Date: 12-8-12
 * Time: 下午9:14
 * To change this template use File | Settings | File Templates.
 */
public class Pager<T extends Serializable> {

    private IHibernateGenericDao<T, String> hibernateGenericDao = null;

    private DetachedCriteria detachedCriteria;

    private int pageNo = PAGE_NO;

    private int pageCount;

    private int pageSize;

    private long totalCount;

    private List<T> domainList = new ArrayList<T>();

    public Pager(int pageNo, int pageSize, IHibernateGenericDao<T, String> hibernateGenericDao, DetachedCriteria detachedCriteria) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.hibernateGenericDao = hibernateGenericDao;
        this.detachedCriteria = detachedCriteria;
        this.init();

    }

    private void init() {
        this.totalCount = this.hibernateGenericDao.getCount(detachedCriteria);
        this.pageCount = (int) (this.totalCount % this.pageSize == 0 ? this.totalCount / this.pageSize : this.totalCount / this.pageSize + 1);
        if(pageNo<1){
            pageNo = 1;
        }else if(pageNo>pageCount){
            pageNo = pageCount;
        }
        detachedCriteria.setProjection(null);
        detachedCriteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        this.domainList = this.hibernateGenericDao.findByDetachedCriteria(this.detachedCriteria, (this.pageNo - 1) * this.pageSize, this.pageSize);
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getDomainList() {
        return domainList;
    }

    public void setDomainList(List<T> domainList) {
        this.domainList = domainList;
    }

}
