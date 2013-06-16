package com.bupt.pcncad.filter;

import com.bupt.pcncad.domain.User;
import com.bupt.pcncad.util.AuthenticateRole;
import com.bupt.pcncad.util.LoggerUtil;
import com.bupt.pcncad.service.usesr.IUserService;
import com.bupt.pcncad.webContext.WebContextThreadLocal;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.bupt.pcncad.util.Constants;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: liboyang01
 * Date: 12-7-27
 * Time: 下午10:42
 * To change this template use File | Settings | File Templates.
 */
@Component
@Qualifier("contextFilter")
public class ContextFilter implements InitializingBean {

    @Autowired
    private IUserService userService;
    @Value("${byPassUrl}")
    private String byPassUrl;
    @Value("${userIdRuleReg}")
    private String userIdRuleReg;
    @Value("${indexPage}")
    private String indexPage;
    private Set<String> byPassUrlSet = new HashSet<String>();

    public void init() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        if (byPass(req)) {
            chain.doFilter(req, response);
            return;
        }
        {
            HttpSession session = req.getSession(false);
            if (session != null) {
                User user = (User) session.getAttribute(Constants.USER_INFO);
                if (user != null) {
                    WebContextThreadLocal.setCurrentUser(user);
                    request.setAttribute("isTeacher",user.getRole());
                   // AuthenticateRole.authenticate(req, user,this.userIdRuleReg);
                    //request.setAttribute(Constants.CURRENT_PAGE, req.getServletPath());
                    request.setAttribute(Constants.CURRENT_PAGE, indexPage);
                    request.setAttribute(Constants.USER_INFO, user);
                    chain.doFilter(request, response);
                    return;
                }
            }
        }
        {
            String userId = request.getParameter(Constants.USERID);
            String passwd = request.getParameter(Constants.PASSWD);
            if (!"".equals(userId) && null != userId && !"".equals(passwd) && null != passwd) {
                request.setAttribute("userIdRuleReg",this.userIdRuleReg);
                chain.doFilter(request, response);
                return;
            }
        }
        {
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (Constants.BRUSERID.equals(cookie.getName())) {
                        User user = this.userService.getUserByUserId(cookie.getValue());
                        if (user != null) {
                            HttpSession session = req.getSession();
                            WebContextThreadLocal.setCurrentUser(user);
                            LoggerUtil.info(this.getClass(),"当前登录用户为:"+user.getUserName());
                            request.setAttribute("isTeacher",user.getRole());
                            //AuthenticateRole.authenticate(req, user,this.userIdRuleReg);
                            //request.setAttribute(Constants.CURRENT_PAGE, req.getServletPath());
                            request.setAttribute(Constants.CURRENT_PAGE, indexPage);
                            request.setAttribute(Constants.USER_INFO, user);
                            session.setAttribute(Constants.USER_INFO, user);
                        }
                        chain.doFilter(request, response);
                        return;
                    }
                }
            }
        }
        chain.doFilter(request, response);
        this.destroy();

    }

    public void destroy() {
        WebContextThreadLocal.unbind();
    }

    private void redirectOtherPage(ServletResponse response, String url) throws IOException {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.sendRedirect("login.html");
    }

    private void userVerifySuccess(ServletRequest request, ServletResponse response, FilterChain chain, User currentUser) throws ServletException, IOException {
        WebContextThreadLocal.setCurrentUser(currentUser);
        chain.doFilter(request, response);
    }

    private boolean byPass(HttpServletRequest request) {
        boolean bp = false;
        if (this.byPassUrlSet.contains(request.getServletPath().substring(1))) {
            bp = true;
        }
        return bp;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
       /* String[] byPassUrlArray = this.byPassUrl.split(",");
        for (String url : byPassUrlArray) {
            this.byPassUrlSet.add(url);
        }*/
    }

}
