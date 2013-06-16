package com.bupt.pcncad.lucene;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 13-1-15
 * Time: 上午10:40
 * To change this template use File | Settings | File Templates.
 */
public class FilePreprocessor {
    public static final String baseDir = "data"+File.separator+"lucene"+File.separator;
    public void splitToSmallFiles(File file, String outputDir) throws IOException {
        int filePointer = 0;
        int MAX_SIZE = 5120;
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\r\n");
            if (sb.length() >= MAX_SIZE) {
                File outputFile = new File(outputDir + filePointer + ".txt");
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile)));
                bw.write(sb.toString());
                sb = new StringBuilder();
                filePointer++;
                bw.close();
            }
        }
        if(sb.length()>0){
            File outputFile = new File(outputDir + filePointer + ".txt");
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile)));
            bw.write(sb.toString());
            bw.close();
        }
        br.close();
    }
    public static void main(String[]args) throws IOException {
        File file = new File(FilePreprocessor.baseDir+"gfs.txt");
        new FilePreprocessor().splitToSmallFiles(file, baseDir + "sub" + File.separator);
    }
}
