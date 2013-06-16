package com.bupt.pcncad.monitor.log;

import com.bupt.pcncad.monitor.AbstractMonitor;
import com.bupt.pcncad.util.BlockingQueue;
import com.bupt.pcncad.util.LoggerUtil;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created with IntelliJ IDEA.
 * User: liboyang01
 * Date: 12-9-29
 * Time: 下午8:12
 * To change this template use File | Settings | File Templates.
 */
public class LogMonitor extends AbstractMonitor {

    private String logPath;

    private long lastSeekPointer = 0;

    //  private BlockingQueue<Byte> blockingQueue = new BlockingQueue<Byte>(4096);
    private byte[] bytes = new byte[4096];

    private int availableLen;

    private RandomAccessFile ra;

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) throws Exception {
        this.logPath = logPath;
        this.ra = new RandomAccessFile(logPath, "rw");
    }

    public String getLog() {
        /*  Byte[] bytes = this.blockingQueue.toArray();
      byte[] bytes1 = new byte[bytes.length];
      for (int b : bytes) {
          bytes1[b] = bytes[b];
      }
      bytes = null;*/
        return new String(bytes, 0, availableLen);
    }

    @Override
    public void on(boolean first) throws Exception {
        try {
            synchronized (this) {
                if (lastSeekPointer == ra.length()) {
                    ra.seek(lastSeekPointer - 4096 > 0 ? (lastSeekPointer - 4096) : 0);
                    if(first){
                        availableLen = this.ra.read(bytes);
                    }else {
                        this.availableLen = 0;
                    }
                } else {
                    ra.seek(lastSeekPointer);
                    availableLen = this.ra.read(bytes);
                    lastSeekPointer += availableLen;
                }

            }
        } catch (IOException e) {
            LoggerUtil.error(this.getClass(), "读取log文件出错");
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    @Override
    public void off() throws Exception {
        this.setOn(false);
    }
}
