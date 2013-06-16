package com.bupt.pcncad.dao;

import com.bupt.pcncad.dao.user.IUserDao;
import com.bupt.pcncad.domain.User;
import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 13-2-22
 * Time: 下午12:51
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/applicationContext.xml"})
public class ImportStudentDataFromSqlServerTest {
    @Autowired
    private IUserDao userDao;
    private Connection sqlServerConn;

    static {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public ImportStudentDataFromSqlServerTest() throws SQLException {
        sqlServerConn = DriverManager.getConnection("jdbc:jtds:sqlserver://10.108.210.121:1433/library", "root", "root");
    }

    @Test
    public void importStudentData() throws SQLException {
        Statement st = sqlServerConn.createStatement();
        ResultSet rs = st.executeQuery("select ident_id,reader_name from dbo.readers");
        int i=0;
        while (rs.next()) {
            String code = rs.getString(1);
            if(code==null || code.trim().equals("")){
                continue;
            }
            String prefix = code.substring(0,2);
            i = prefix.compareTo("10");
            if (!code.startsWith("20") && i>=0) {
                code = "20" + code;
            }
            String userName = rs.getString(2);
            System.out.println(userName);
            User user = new User();
            user.setUserId(code);
            user.setUserName(userName);
            user.setUserPasswd("111");
            userDao.save(user);
        }
    }

}
