package com.bupt.pcncad.filter;

import com.bupt.pcncad.util.LoggerUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: liboyang01
 * Date: 12-9-21
 * Time: 下午10:46
 * To change this template use File | Settings | File Templates.
 */
public class ExpireFilter implements Filter {

    private String maxAge = "";

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String serverPath = req.getServletPath();
        int pos = serverPath.lastIndexOf(".");
        if (pos != -1) {
            String suffix = serverPath.substring(pos + 1).toLowerCase();
            String pattern = "^(gif|jpg|jpeg|png|js|css)$";
            if (suffix.matches(pattern)) {
                //静态资源允许缓存一个月
                HttpServletResponse res = (HttpServletResponse) response;
                res.addHeader("Cache-Control", "max-age=" + this.maxAge);
                res.addHeader("Keep-Alive","timeout=5, max=95");
                res.addHeader("Connection","keep-alive");
                res.setDateHeader("Expires", System.currentTimeMillis() + Integer.valueOf(this.maxAge)*1000L);
            } else {
                HttpServletResponse res = (HttpServletResponse) response;
                //pragma用来兼容http协议1.0
                res.addHeader("Pragma", "no-cache");
                //http协议1.1才有cache-controll
                res.addHeader("Cache-Control", "no-cache");
                res.setDateHeader("Expires", -1);
            }

        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.maxAge = config.getInitParameter("max-age");
    }

    @Override
    public void destroy() {
    }
}
