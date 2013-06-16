package com.bupt.pcncad.dao;

import com.bupt.pcncad.dao.academy.IAcademyDao;
import com.bupt.pcncad.dao.user.IUserDao;
import com.bupt.pcncad.domain.Academy;
import com.bupt.pcncad.domain.Course;
import com.bupt.pcncad.domain.Major;
import com.bupt.pcncad.domain.User;
import com.bupt.pcncad.service.fetch.IFetchBookInfoService;
import com.bupt.pcncad.timer.BookInfoCrawler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liboyang01
 * Date: 12-7-22
 * Time: 上午4:22
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/applicationContext.xml"})
public class TestDao {
    @Autowired
    private IAcademyDao academyDao;
    @Autowired
    private IUserDao userDao;
    @Autowired
    private BookInfoCrawler bookInfoCrawler;
    @Autowired
    private IFetchBookInfoService fetchBookInfoService;

    private static final String ACADEMY_FILE_URL = "data" + File.separator + "academy" + File.separator + "academy.txt";

    private static final String TEACHER_FILE_URL = "data" + File.separator + "user" + File.separator + "teacher.txt";

    private static final String STUDENT_FILE_URL = "data" + File.separator + "user" + File.separator + "student.txt";

    private static final String EMAIL = "data" + File.separator + "email" + File.separator + "email.txt";

    @Test
    public void test() {
        /*{
            Academy a = new Academy();
            a.setName("信息与通信工程学院");
            Major major = new Major();
            major.setName("通信工程专业");
            major.setAcademy(a);
            Major major1 = new Major();
            major1.setName("信息工程专业");
            major1.setAcademy(a);
            Major major2 = new Major();
            major2.setName("电子信息工程专业");
            major2.setAcademy(a);
            Major major3 = new Major();
            major3.setName("数字多媒体技术");
            major3.setAcademy(a);
            a.getNodes().add(major);
            a.getNodes().add(major1);
            a.getNodes().add(major2);
            a.getNodes().add(major3);
            this.academyDao.save(a);
        }

        {
            Academy a = new Academy();
            a.setName("电子工程学院");
            Major major = new Major();
            major.setName("电子科学与技术专业");
            major.setAcademy(a);
            Major major1 = new Major();
            major1.setName("电子信息科学与技术专业");
            major1.setAcademy(a);
            Major major2 = new Major();
            major2.setName("光信息科学与技术专业");
            major2.setAcademy(a);
            a.getNodes().add(major);
            a.getNodes().add(major1);
            a.getNodes().add(major2);
            this.academyDao.save(a);
        }

        {
            Academy a = new Academy();
            a.setName("计算机学院");
            Major major = new Major();
            major.setName("计算机科学与技术专业");
            major.setAcademy(a);
            Major major1 = new Major();
            major1.setName("网络工程专业");
            major1.setAcademy(a);
            a.getNodes().add(major);
            a.getNodes().add(major1);
            this.academyDao.save(a);
        }

        {
            Academy a = new Academy();
            a.setName("自动化学院");
            Major major = new Major();
            major.setName("机械工程及自动化专业");
            major.setAcademy(a);
            Major major1 = new Major();
            major1.setName("工业设计专业");
            major1.setAcademy(a);
            Major major2 = new Major();
            major2.setName("测控技术与仪器专业");
            major2.setAcademy(a);
            Major major3 = new Major();
            major3.setName("物流工程专业");
            major3.setAcademy(a);
            Major major4 = new Major();
            major4.setName("自动化专业");
            major4.setAcademy(a);
            a.getNodes().add(major);
            a.getNodes().add(major1);
            a.getNodes().add(major2);
            a.getNodes().add(major3);
            a.getNodes().add(major4);
            this.academyDao.save(a);
        }

        {
            Academy a = new Academy();
            a.setName("软件学院");
            Major major = new Major();
            major.setName("软件工程专业");
            major.setAcademy(a);
            a.getNodes().add(major);
            this.academyDao.save(a);
        }

        {
            Academy a = new Academy();
            a.setName("经济管理学院");
            Major major = new Major();
            major.setName("信息管理与信息系统专业");
            major.setAcademy(a);
            Major major1 = new Major();
            major1.setName("市场营销专业");
            major1.setAcademy(a);
            Major major2 = new Major();
            major2.setName("会计学专业");
            major2.setAcademy(a);
            Major major3 = new Major();
            major3.setName("经济学专业");
            major3.setAcademy(a);
            Major major4 = new Major();
            major4.setName("工商管理专业");
            major4.setAcademy(a);
            Major major5 = new Major();
            major5.setName("公共事业管理专业");
            major5.setAcademy(a);
            Major major6 = new Major();
            major6.setName("工程管理专业");
            major6.setAcademy(a);
            Major major7 = new Major();
            major7.setName("电子商务专业");
            major7.setAcademy(a);
            Major major8 = new Major();
            major8.setName("国际经济与贸易专业");
            major8.setAcademy(a);
            a.getNodes().add(major);
            a.getNodes().add(major1);
            a.getNodes().add(major2);
            a.getNodes().add(major3);
            a.getNodes().add(major4);
            a.getNodes().add(major5);
            a.getNodes().add(major6);
            a.getNodes().add(major7);
            a.getNodes().add(major8);
            this.academyDao.save(a);
        }

        {
            Academy a = new Academy();
            a.setName("人文学院");
            Major major = new Major();
            major.setName("数学与应用数学专业");
            major.setAcademy(a);
            Major major1 = new Major();
            major1.setName("应用物理专业");
            major1.setAcademy(a);
            a.getNodes().add(major);
            a.getNodes().add(major1);
            this.academyDao.save(a);
        }*/
    }

    private void insertUser(String fileUrl) throws Exception {
        File teacherFile = new File(fileUrl);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(teacherFile)));
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            if ("".equals(line)) {
                break;
            }
            if (line.startsWith("#")) {
                continue;
            }
            String[] array = line.split("\\s+?");
            String teacherId = array[0].trim();
            String teacherName = array[1].trim();
            System.out.println(teacherId + ":" + teacherName);
            User user = new User();
            user.setUserId(teacherId);
            user.setUserName(teacherName);
            user.setUserPasswd("111");
            this.userDao.save(user);
        }
    }

    @Test
    public void insertTeacher() throws Exception {
        this.insertUser(TEACHER_FILE_URL);
    }

    @Test
    public void insertStudent() throws Exception {
        this.insertUser(STUDENT_FILE_URL);
        /*User user = new User();
        user.setUserId("2011140244");
        user.setUserPasswd("111");
        user.setUserName(" 李博洋");
        this.userDao.save(user);*/
    }

    @Test
    public void saveCourses() throws Exception {
        Map<String, Academy> academyMap = this.readAcademyDataFromFile();
        for (Map.Entry<String, Academy> entry : academyMap.entrySet()) {

            Academy a = entry.getValue();
            Map<String, Major> majorMap = this.readMajorDataFromFile(entry.getKey());
            for (Map.Entry<String, Major> entry1 : majorMap.entrySet()) {
                Major major = entry1.getValue();
                major.setAcademy(a);
                a.getMajors().add(major);
                this.saveCourseDataFromFile("data" + File.separator + "academy" + File.separator + entry.getKey() + File.separator + entry1.getKey() + ".txt", major);
            }
            //  System.out.print(a);
            this.academyDao.save(a);
        }


        //  a.getNodes().add(major);
        // a.getNodes().add(major1);
        //InputStream is = TestDao.class.getClassLoader().getResourceAsStream("data/guangxinxi.txt");

    }


    public void saveCourseDataFromFile(String fileUrl, Major major) throws IOException {
        {
            InputStream is = new FileInputStream(fileUrl);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if ("".equals(line)) {
                    break;
                }
                if (line.startsWith("#")) {
                    continue;
                }
                String[] columns = line.split("\\s+");
                int size = columns.length;
                String courseName = columns[1];
                String term = columns[size - 3];
                if (term.indexOf("/") != -1) {
                    String[] times = term.split("/");
                    for (String i : times) {
                        short n = Short.valueOf(i);
                        Course course = new Course();
                        course.setName(columns[1]);
                        course.setTerm(n);
                        course.setMajor(major);
                        major.getCourses().add(course);
                        System.out.print(columns[1] + " " + n);
                        System.out.print("             ");
                    }
                    System.out.println();
                    continue;
                }
                if (term.indexOf("～") != -1) {
                    String[] times = term.split("～");
                    for (int i = Integer.valueOf(times[0]); i <= Integer.valueOf(times[1]); i++) {
                        Course course = new Course();
                        course.setName(columns[1]);
                        course.setTerm((short) i);
                        course.setMajor(major);
                        major.getCourses().add(course);
                        System.out.print(columns[1] + " " + i);
                        System.out.print("             ");
                    }
                    System.out.println();
                    continue;
                }
                Course course = new Course();
                course.setName(columns[1]);
                course.setTerm(Short.valueOf(columns[size - 3]));
                course.setMajor(major);
                major.getCourses().add(course);
                System.out.println(columns[1] + " " + columns[size - 3]);
            }
        }
    }


    public Map<String, Academy> readAcademyDataFromFile() throws Exception {
        Map<String, Academy> academyMap = new HashMap<String, Academy>();
        InputStream is = new FileInputStream(ACADEMY_FILE_URL);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = "";
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if ("".equals(line)) {
                break;
            }
            if (line.startsWith("#")) {
                continue;
            }
            String[] columns = line.split(":");
            System.out.println(columns[0] + ">>学院>>" + columns[1]);
            Academy academy = new Academy();
            academy.setName(columns[1]);
            academyMap.put(columns[0], academy);
        }
        return academyMap;
    }

    public Map<String, Major> readMajorDataFromFile(String majorUrl) throws Exception {
        Map<String, Major> majorMap = new HashMap<String, Major>();
        InputStream is = new FileInputStream("data" + File.separator + "academy" + File.separator + majorUrl + File.separator + "root.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = "";
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if ("".equals(line)) {
                break;
            }
            if (line.startsWith("#")) {
                continue;
            }
            String[] columns = line.split(":");
            Major major = new Major();
            major.setName(columns[1]);
            majorMap.put(columns[0], major);
            System.out.println(columns[0] + ">>>>专业>>>>" + columns[1]);
        }
        return majorMap;
    }


    @Test
    public void test1() {
        final MutiThread mutiThread = new MutiThread();
        new Thread(new Runnable() {
            @Override
            public void run() {
                mutiThread.test1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                //To change body of implemented methods use File | Settings | File Templates.
                mutiThread.test2();
            }
        }).start();
    }

    @Test
    public void test2() {
        class a {
            public synchronized void add() {
                System.out.println("add");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }

            public synchronized void del() {
                System.out.println("del");
            }
        }
        final a A = new a();
        new Thread(new Runnable() {
            @Override
            public void run() {
                A.add();      //To change body of implemented methods use File | Settings | File Templates.

            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                A.del();
                //To change body of implemented methods use File | Settings | File Templates.
            }
        }).start();
    }

    @Test
    public void test3() {
        File file = null;
        try {
            //file.exists();
        } catch (NullPointerException e) {
            System.out.println("exception");
        } finally {
            System.out.println("finally");
        }
        System.out.println("ok");
    }

    @Test
    public void insertEmail() throws IOException {
        File file = new File(EMAIL);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line = null;
        String[] buffer = null;
        String userId = null;
        String email = null;
        User user = null;
        while ((line = bufferedReader.readLine()) != null) {
            buffer = line.split("\\s+");
            userId = buffer[1];
            if (buffer.length > 3) {
                email = buffer[3];
            }
            user = this.userDao.findEntity("from User u where u.userId=?","20"+userId);
            if(email!=null){
              user.setUserEmail(email);
            }
            System.out.println(user.getUserName());
            this.userDao.save(user);
            email=null;

        }


    }

    @Test
    public void i(){
        User user = new User();

        user.setUserName("卢洋");
        user.setUserPasswd("111");
        user.setUserId("2010210005");
        this.userDao.save(user);
    }

    @Test
    public void testFetchUrl() throws Exception{
     //   bookInfoCrawler.fetchBookInfoByHotSearchQuery();
     /*   List<String> list = new ArrayList<String>();
        list.add("python");
        list.add("java");
        list.add("andriod");
        list.add("math");
        list.add("matlab");*/
        //bookInfoCrawler.setHotWordList(list);
        //fetchBookInfoService.getBooksByHotQueryWord(bookInfoCrawler.getHotWordList());
        fetchBookInfoService.getRecommendedBookByUserId("2011140244");
        //bookInfoCrawler.fetchBookInfo();
        //fetchBookInfoService.fetchAndSaveDoubanBookInfo(bookInfoCrawler.getAllBooks());
    }

}
