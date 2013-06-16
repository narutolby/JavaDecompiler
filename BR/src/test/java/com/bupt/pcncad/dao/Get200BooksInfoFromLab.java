package com.bupt.pcncad.dao;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 13-5-14
 * Time: 下午3:07
 * To change this template use File | Settings | File Templates.
 */

import com.bupt.pcncad.domain.HotBook;
import com.bupt.pcncad.service.fetch.FetchBookInfoServiceImpl;
import com.bupt.pcncad.service.fetch.IFetchBookInfoService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/applicationContext.xml"})
public class Get200BooksInfoFromLab {

    @Autowired
    private IFetchBookInfoService fetchBookInfoService;

    @org.junit.Test
    public void fetch()throws Exception{
        URL url = new URL("http://211.68.68.197/opac_two/top_detail.jsp?type=circul.circulog_A&cname=%BD%E8%D4%C4%C5%C5%D0%D0%B0%F1");
        HttpURLConnection re = (HttpURLConnection) url.openConnection();
        re.setRequestMethod("GET");
        re.connect();
        BufferedReader br = new BufferedReader(new InputStreamReader(re.getInputStream(), "gbk"));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line.trim());
        }
        line = sb.toString();
        System.out.println(line);
        Pattern p = Pattern.compile("<a href=\"search2/s_detail.jsp.*?\"  target=\"_blank\">(.*?)</a>", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(line);
        int i = 0;
        while (m.find()) {
            if(i>=500){
                break;
            }
            i++;
            String bookName = m.group(1).split("=")[0];
            HotBook hotBook = new HotBook();
            hotBook.setChineseName(bookName);
            this.fetchBookInfoService.searchDoubanBookInfo(null,bookName,0,1, hotBook, 9);
        }
 //URL url = new URL
        //this.fetchBookInfoService.fetch200BooksInfoFromLab();
    }

    public static void main(String[] args) throws IOException {
        URL url = new URL("http://211.68.68.197/opac_two/top_detail.jsp?type=circul.circulog_A&cname=%BD%E8%D4%C4%C5%C5%D0%D0%B0%F1");
        HttpURLConnection re = (HttpURLConnection) url.openConnection();
        re.setRequestMethod("GET");
        re.connect();
        BufferedReader br = new BufferedReader(new InputStreamReader(re.getInputStream(), "gbk"));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line.trim());
        }
        line = sb.toString();
        System.out.println(line);
        Pattern p = Pattern.compile("<a href=\"search2/s_detail.jsp.*?\"  target=\"_blank\">(.*?)</a>", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(line);
        int i = 0;
        while (m.find()) {

            i++;
            String bookName = m.group(1).split("=")[0];
            System.out.println(i);
            HotBook hotBook = new HotBook();
            hotBook.setChineseName(bookName);
            //this.fetchBookInfoService.searchDoubanBookInfo(null,bookName,0,1, hotBook, 9);
        }




    }
}
