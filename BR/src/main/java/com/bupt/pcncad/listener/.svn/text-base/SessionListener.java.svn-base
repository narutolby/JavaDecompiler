package com.bupt.pcncad.listener;

import com.bupt.pcncad.util.LoggerUtil;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created with IntelliJ IDEA.
 * User: liboyang01
 * Date: 12-8-18
 * Time: 下午7:20
 * To change this template use File | Settings | File Templates.
 */
public class SessionListener implements HttpSessionListener{


    @Override
    public void sessionCreated(HttpSessionEvent se) {
        LoggerUtil.info(this.getClass(), se.getClass().getName()+"session创建成功!");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        LoggerUtil.info(this.getClass(),"session销毁!");
    }
}
