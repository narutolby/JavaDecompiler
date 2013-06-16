package com.bupt.pcncad.dao;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 13-3-3
 * Time: 下午5:00
 * To change this template use File | Settings | File Templates.
 */

import com.bupt.pcncad.service.index.AbstractIndexProcess;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/applicationContext.xml"})
public class CreateIndexTest {

    @Autowired
    @Qualifier("resourceIndexProcess")
    private AbstractIndexProcess resourceIndexProcess;
    @Autowired
    @Qualifier("communityIndexProcess")
    private AbstractIndexProcess communityIndexProcess;

    @Test
    public void createIndex() throws Exception {
        resourceIndexProcess.createIndex();
        communityIndexProcess.createIndex();

    }

}
