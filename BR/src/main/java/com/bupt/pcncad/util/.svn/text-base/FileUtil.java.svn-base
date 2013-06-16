package com.bupt.pcncad.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: liboyang01
 * Date: 12-7-31
 * Time: 上午12:45
 * To change this template use File | Settings | File Templates.
 */
public class FileUtil {

    public static void copy(InputStream from,OutputStream to) throws IOException {
        byte[]bytes = new byte[1024];
        int length = -1;
        while((length=from.read(bytes))!=-1){
            to.write(bytes,0,length);
            to.flush();
        }
        from.close();
        to.close();

    }
}
