package com.bupt.pcncad.service.resource;

import com.bupt.pcncad.domain.Resource;
import com.bupt.pcncad.util.Pager;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liboyang01
 * Date: 12-7-27
 * Time: 下午8:45
 * To change this template use File | Settings | File Templates.
 */
public interface IResourceOperationService {

    public String uploadResource(MultipartFile file,String userId,String... keyword) throws Exception;
    /**
     * 社区话题上传,图片
    **/
    public String uploadImage(MultipartFile file,String imgRootPath) throws Exception;

    public void downloadResource(String resourceId,HttpServletResponse response) throws Exception;
    /*
    * targetId为资源分享对象的id，包括userId,resourceId
    * */
    public void shareResource(String resourceId,String...targetId)throws Exception;

    public void collectResource(String resourceId)throws Exception;

    public void saveResourceWithUser(String resourceId,String category, String keyWords)throws Exception;
    
    public boolean deleteResource(String resourceId)throws Exception;

    public Pager<Resource> loadResource(int pageNo,int pageSize, DetachedCriteria detachedCriteria)throws Exception;

    public void saveResource(String[]resourceIdArray,String courseId,String description) throws Exception;

    public List<Resource> map(String[]resourceIds)throws Exception;

    public Resource updateResource(String resourceId) throws Exception;

    public Pager<Resource> getReviewResource(int pageNo) throws Exception;

    public Pager<Resource> getPassResource(int pageNo) throws Exception;

    public Pager<Resource> getRefusedResource(int pageNo) throws Exception;

    public Map<String,String> getTop4ShareUser() throws Exception;
}


