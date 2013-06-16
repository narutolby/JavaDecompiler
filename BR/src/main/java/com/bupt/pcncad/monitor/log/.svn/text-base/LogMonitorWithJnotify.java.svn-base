package com.bupt.pcncad.monitor.log;

import com.bupt.pcncad.monitor.AbstractMonitor;
import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyListener;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: liboyang01
 * Date: 12-9-28
 * Time: 下午10:46
 * To change this template use File | Settings | File Templates.
 */
public class LogMonitorWithJnotify extends AbstractMonitor{


    public void on(boolean first) throws Exception {
        // path to watch
        //String path = System.getProperty("user.home");
        String path1 = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String path = "C:\\Documents and Settings\\liboyang01\\.IntelliJIdea11\\system\\tomcat\\Unnamed_BR\\logs";
        System.out.println(path1);
        // watch mask, specify events you care about,
        // or JNotify.FILE_ANY for all events.
        int mask = JNotify.FILE_MODIFIED;


        // watch subtree?
        boolean watchSubtree = false;

        // add actual watch
        int watchID = JNotify.addWatch(path, mask, watchSubtree, (JNotifyListener) new Listener());
        while (true)
            Thread.sleep(10000);
        // sleep a little, the application will exit if you
        // don't (watching is asynchronous), depending on your
        // application, this may not be required
       /* Thread.sleep(1000000);

        // to remove watch the watch
        boolean res = JNotify.removeWatch(watchID);
        if (!res) {
            // invalid watch ID specified.
        }*/
    }

    @Override
    public void off() throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    class Listener implements JNotifyListener {
        public void fileRenamed(int wd, String rootPath, String oldName,
                                String newName) {
            print("renamed " + rootPath + " : " + oldName + " -> " + newName);
        }
        public void fileModified(int wd, String rootPath, String name) {

            try {
                RandomAccessFile rf = new RandomAccessFile(rootPath+File.separator+name,"r");
                long len = rf.length();
                long start = rf.getFilePointer();
                long nextend = start + len - 1;
                String line = "";
                rf.seek(nextend);
                int c = -1;
                int t = 0;
                while (nextend > start) {
                    c = rf.read();
                    if (c == '\n' || c == '\r') {
                        line = new String(rf.readLine().getBytes("ISO8859-1"),"UTF-8");
                        t++;
                        if (t >= 1 && line != null) {
                            print(line);
                            return;
                        }
                        nextend--;
                    }
                    nextend--;
                    rf.seek(nextend);
                    if (nextend == 0) {// 当文件指针退至文件开始处，输出第一行
                        print(new String(rf.readLine().getBytes("ISO8859-1"),"UTF-8"));
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            print("modified " + rootPath + " : " + name);
        }
        public void fileDeleted(int wd, String rootPath, String name) {
            print("deleted " + rootPath + " : " + name);
        }
        public void fileCreated(int wd, String rootPath, String name) {
            print("created " + rootPath + " : " + name);
        }
        void print(String msg) {
            System.out.println(msg);
        }
    }
    public static void main(String[]args)throws Exception{
        new LogMonitorWithJnotify().on(true);
    }
}
