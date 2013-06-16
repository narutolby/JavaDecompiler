package com.bupt.pcncad.timer;

import com.bupt.pcncad.domain.HotBook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 13-4-23
 * Time: 下午3:37
 * To change this template use File | Settings | File Templates.
 */
@Component
public class BookInfoCrawler {

    @Value("${fetch.url}")
    private String fetchUrl;
    @Value("${fetch.hotword.url}")
    private String fetchHotWordUrl;
    private Set<HotBook> borrowBooks = new LinkedHashSet<HotBook>();
    private Set<HotBook> recommondedBooks = new LinkedHashSet<HotBook>();
    private Set<HotBook> collectedBooks = new LinkedHashSet<HotBook>();
    private Set<HotBook> watchBooks = new LinkedHashSet<HotBook>();
    private Set<HotBook> allBooks = new LinkedHashSet<HotBook>();
    private Pattern regExp = Pattern.compile("<a href=\"([\\s\\S]*?)\" target=\"_blank\">([\\s\\S]*?)</a>");
    private Pattern hotWordRegExp = Pattern.compile("<div id=\"paihang_list1\">[\\s\\S]*?</div>");
    private Set<String> hotWordSet = new LinkedHashSet<String>();



    public String getFetchUrl() {
        return fetchUrl;
    }

    public void setFetchUrl(String fetchUrl) {
        this.fetchUrl = fetchUrl;
    }

    public String getFetchHotWordUrl() {
        return fetchHotWordUrl;
    }

    public void setFetchHotWordUrl(String fetchHotWordUrl) {
        this.fetchHotWordUrl = fetchHotWordUrl;
    }

    public Set<HotBook> getBorrowBooks() {
        return borrowBooks;
    }

    public void setBorrowBooks(Set<HotBook> borrowBooks) {
        this.borrowBooks = borrowBooks;
    }

    public Set<HotBook> getRecommondedBooks() {
        return recommondedBooks;
    }

    public Set<String> getHotWordSet() {
        return hotWordSet;
    }

    public void setHotWordSet(Set<String> hotWordSet) {
        this.hotWordSet = hotWordSet;
    }

    public void setRecommondedBooks(Set<HotBook> recommondedBooks) {
        this.recommondedBooks = recommondedBooks;
    }

    public Set<HotBook> getCollectedBooks() {
        return collectedBooks;
    }

    public void setCollectedBooks(Set<HotBook> collectedBooks) {
        this.collectedBooks = collectedBooks;
    }

    public Set<HotBook> getWatchBooks() {
        return watchBooks;
    }

    public void setWatchBooks(Set<HotBook> watchBooks) {
        this.watchBooks = watchBooks;
    }

    public void fetchBookInfoByHotSearchQuery() throws Exception {
        URL url = new URL(this.fetchHotWordUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "gbk"));
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line.trim());
        }
        String html = sb.toString();
        Matcher matcher = hotWordRegExp.matcher(html);
        while (matcher.find()) {
            html = matcher.group();
            break;
        }
        matcher = regExp.matcher(html);
        String q = null;
        while (matcher.find()) {
            q = matcher.group(2);
            q = q.substring(q.lastIndexOf(";")+1);
            System.out.println("q:"+q);
            hotWordSet.add(q);
        }
    }

    public void fetchBookInfo() throws IOException {
        URL url = new URL(this.fetchUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "gbk"));
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line.trim());
        }
        String html = sb.toString();
        Pattern pattern = Pattern.compile("(<div id=\"top_3\"[\\s\\S]*?)</ul>", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(html);
        String bookInfoHtmlFragment = null;
        int i = 0;
        while (matcher.find()) {
            bookInfoHtmlFragment = matcher.group(1);
            switch (i) {
                case 0:
                    this.buildBorrowBooksSet(bookInfoHtmlFragment);
                    break;
                case 1:
                    this.buildCollectedBooksSet(bookInfoHtmlFragment);
                    break;
                case 2:
                    this.buildWatchBooksSet(bookInfoHtmlFragment);
                    break;
                case 3:
                    this.buildRecommondedBooksSet(bookInfoHtmlFragment);
                    break;
            }
            i++;
        }
    }

    private void buildBorrowBooksSet(String html) {
        this.buildTemplate(html, borrowBooks);
    }


    private void buildRecommondedBooksSet(String html) {
        this.buildTemplate(html, recommondedBooks);
    }

    private void buildCollectedBooksSet(String html) {
        this.buildTemplate(html, collectedBooks);
    }

    private void buildWatchBooksSet(String html) {
        this.buildTemplate(html, watchBooks);
    }

    private void buildTemplate(String html, Set<HotBook> set) {
        html = html.replace("&nbsp;", "");
        Matcher matcher = regExp.matcher(html);
        while (matcher.find()) {
            String labHref = matcher.group(1);
            String[] bookName = matcher.group(2).split("=");
            String chineseName = bookName[0].trim();
            String englishName = "";
            if (bookName.length > 1) {
                englishName = bookName[1].trim();
            }
            HotBook hotBooks = new HotBook();
            hotBooks.setChineseName(chineseName);
            hotBooks.setEnglishName(englishName);
            hotBooks.setLabHref(labHref);
            set.add(hotBooks);
            allBooks.add(hotBooks);
        }
    }
   public Set<HotBook> getAllBooks(){
       return this.allBooks;
   }
    public static void main(String[] args) throws Exception {
        new BookInfoCrawler().fetchBookInfoByHotSearchQuery();
    }
}
