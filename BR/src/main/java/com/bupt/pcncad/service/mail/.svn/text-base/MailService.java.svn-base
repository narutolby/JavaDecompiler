package com.bupt.pcncad.service.mail;

import com.bupt.pcncad.dao.community.ICommunityDao;
import com.bupt.pcncad.dao.user.IUserDao;
import com.bupt.pcncad.domain.Community;
import com.bupt.pcncad.domain.Course;
import com.bupt.pcncad.domain.Resource;
import com.bupt.pcncad.domain.User;
import com.bupt.pcncad.service.community.ICommunityService;
import com.bupt.pcncad.service.resource.IResourceOperationService;
import com.bupt.pcncad.util.LoggerUtil;
import com.bupt.pcncad.util.ResourceUtil;
import com.bupt.pcncad.webContext.BRWebApplicationContext;
import com.bupt.pcncad.webContext.WebContextThreadLocal;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 12-11-21
 * Time: 下午3:35
 * To change this template use File | Settings | File Templates.
 */
@Service
public class MailService {
    @Autowired
    private JavaMailSenderImpl mailSender;
    @Autowired
    private IResourceOperationService resourceOperationService;
    @Autowired
    private ICommunityDao communityDao;
    @Autowired
    private IUserDao userDao;
    @Value(value = "${mail.username}")
    private String from;
    Configuration configuration;

    {
        configuration = new Configuration();
        try {
            //设置Freemarker的模版文件位置
            configuration.setDirectoryForTemplateLoading(new File(this.getClass().getClassLoader().getResource("mail").getPath()));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void sendMimeMessageOnPublicBullitin(String communityId, String bulletin) throws Exception {
        Community community = this.communityDao.get(communityId);
        Set<User> users = community.getUsers();
        List<String> emailList = new ArrayList<String>();
        for (User user : users) {
            String userEmail = user.getUserEmail();
            if (null == userEmail || "".equals(userEmail.trim())) {
                continue;
            }
            emailList.add(user.getUserEmail());
        }
        String[] emailArray = new String[emailList.size()];
        emailArray = emailList.toArray(emailArray);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        mimeMessageHelper.setTo(emailArray);
        mimeMessageHelper.setFrom(this.from,"知识资源共享系统");
        mimeMessageHelper.setSubject(community.getName() + "社区新公告");
        Map<String, String> map = new HashMap<String, String>();
        map.put("community", community.getName());
        map.put("bulletin", bulletin);
        map.put("link", BRWebApplicationContext.getDomain() + "/community/community_detail.html?c_id=" + community.getId());
        map.put("pubDateTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        StringWriter stringWriter = new StringWriter();
        Template template = configuration.getTemplate("bulletin.ftl");
        template.process(map, stringWriter);
        mimeMessageHelper.setText(stringWriter.getBuffer().toString(), true);
        LoggerUtil.info(this.getClass(), "发送公告邮件:from:" + this.from);
        mailSender.send(mimeMessage);
    }

    public void sendMimeMessageOnUploadResource(String[] resourceIds) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        mimeMessageHelper.setFrom(this.from);
        List<String> emailList = new ArrayList<String>();

        List<Resource> list = this.resourceOperationService.map(resourceIds);
        List<String> fileNameList = new ArrayList<String>();
        StringBuilder courseName = null;
        for (Resource resource : list) {
            fileNameList.add(resource.getResourceRealName());
            if (courseName == null) {
                courseName = new StringBuilder();
                int index = 0;
                for (Course course : resource.getResourceCourses()) {
                    Set<Community> communities = course.getCommunities();
                    for (Community community : communities) {
                        Set<User> users = community.getUsers();
                        for (User user : users) {
                            if (null != user.getUserEmail() && !"".equals(user.getUserEmail().trim())) {
                                emailList.add(user.getUserEmail());
                            }

                        }
                    }
                    if (index == 0) {
                        courseName.append(course.getName());
                    } else {
                        courseName.append("," + course.getName());
                    }
                    index++;
                }
            }
            mimeMessageHelper.addAttachment(MimeUtility.encodeText(resource.getResourceRealName()), new File(ResourceUtil.getRealSavePathByResourceId(resource.getId()), resource.getId() + "." + resource.getResourceType()));
        }
        String[] emailArray = new String[emailList.size()];
        emailArray = emailList.toArray(emailArray);
        mimeMessageHelper.setTo(emailArray);
        mimeMessageHelper.setSubject("资源更新通知");
        StringWriter stringWriter = new StringWriter();
        Template template = configuration.getTemplate("resource.ftl");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pubDateTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        map.put("link", BRWebApplicationContext.getDomain() + "/elec_resrc/my_resrc.html");
        map.put("courseName", courseName.toString());
        map.put("resourceName", fileNameList);
        template.process(map, stringWriter);
        mimeMessageHelper.setText(stringWriter.getBuffer().toString(), true);
        mailSender.send(mimeMessage);
        LoggerUtil.info(this.getClass(), "发送资源更新邮件from:" + this.from);
        // resourceOperationService.collectResource();
        //mimeMessageHelper.addAttachment();
    }

}
