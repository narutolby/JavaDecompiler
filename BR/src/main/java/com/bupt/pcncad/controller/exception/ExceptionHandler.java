package com.bupt.pcncad.controller.exception;

import com.bupt.pcncad.util.LoggerUtil;
import com.bupt.pcncad.webContext.WebContextThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: liboyang01
 * Date: 12-9-4
 * Time: 下午10:15
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        WebContextThreadLocal.unbind();
        ModelAndView modelAndView = new ModelAndView();
       // modelAndView.setViewName("redirect:/index.html");
        modelAndView.getModel().put("response","login");
        LoggerUtil.error(this.getClass(),ex.getMessage());
        ex.printStackTrace();
        return modelAndView;
    }
}
