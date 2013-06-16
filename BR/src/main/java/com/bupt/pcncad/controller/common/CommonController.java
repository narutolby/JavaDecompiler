package com.bupt.pcncad.controller.common;

import com.bupt.pcncad.controller.BaseController;
import com.bupt.pcncad.domain.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: Zhao Meng
 * Date: 13-6-11
 * Time: 下午3:16
 */
@Controller
@RequestMapping("/common")
public class CommonController extends BaseController<Resource> {

    private static final String COMMONROOTPATH = "common";

    /*
     *关于我们
     */
    @RequestMapping("/aboutUs")
    public String aboutUs() {
        return COMMONROOTPATH + "/aboutUs";
    }

    /*
    *联系我们
    */
    @RequestMapping("/contactUs")
    public String contactUs() {
        return COMMONROOTPATH + "/contactUs";
    }



}
