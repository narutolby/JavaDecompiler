package com.bupt.pcncad.service.academy;

import com.bupt.pcncad.domain.Academy;
import com.bupt.pcncad.domain.Course;
import com.bupt.pcncad.domain.Major;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-8-30
 * Time: 上午10:33
 * To change this template use File | Settings | File Templates.
 */
public interface IAcademyService {

    public List<Academy> loadAll()throws Exception;

    public List<Major> loadMajorsByAcademy(String academyId) throws  Exception;

    public List<Course> loadCoursesByMajor(String majorId) throws Exception;

    public List<Course> loadCoursesByMajorInCurrentTerm(String majorId,Integer term) throws Exception;

    public void setUserFollowCourses(Map<String,String[]> paramMap)throws Exception;

    public List<Major> loadUserMajors()throws Exception;

    public Academy getAcademyById(String academyId) throws Exception;

    public String wordAnalyzer(String word, Course course) throws IOException;

    public List<Course> loadAllCourse() throws Exception;

    public void updateCourse(String keyword,String courseId) throws Exception;
}
