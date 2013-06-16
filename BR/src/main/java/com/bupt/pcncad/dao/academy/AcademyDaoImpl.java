package com.bupt.pcncad.dao.academy;

import org.springframework.stereotype.Repository;

import com.bupt.pcncad.dao.HibernateGenericDaoImpl;
import com.bupt.pcncad.domain.Academy;

@Repository
public class AcademyDaoImpl extends HibernateGenericDaoImpl<Academy,String> implements IAcademyDao{

}
