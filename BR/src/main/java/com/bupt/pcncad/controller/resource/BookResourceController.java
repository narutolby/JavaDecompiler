package com.bupt.pcncad.controller.resource;

import com.bupt.pcncad.controller.BaseController;
import com.bupt.pcncad.domain.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: liboyang01
 * Date: 12-8-18
 * Time: 上午11:47
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/book_resrc")
public class BookResourceController extends BaseController<Resource> {

    private static final String BOOKRESRCROOTPATH = "bookResource";
    @RequestMapping("/book_sys_rec")
    public String bookResourceSystemRecommend(ModelMap model) {
         return BOOKRESRCROOTPATH+"/bookSystemRecommend";
    }

    @RequestMapping("/book_resrc_detail")
    public String bookResourceDetail() {
        return BOOKRESRCROOTPATH+"/bookResourceDetail";
    }

    @RequestMapping("/tag_all_book_resrc")
    public String tagAllBookResourceView() {
        return BOOKRESRCROOTPATH+"/tagAllBookResourceView";
    }
}
