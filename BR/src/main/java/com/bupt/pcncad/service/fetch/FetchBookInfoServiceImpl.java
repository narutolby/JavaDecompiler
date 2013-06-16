package com.bupt.pcncad.service.fetch;

import com.bupt.pcncad.dao.fetch.IBookInfoDao;
import com.bupt.pcncad.domain.HotBook;
import com.dongxuexidu.douban4j.model.app.DoubanException;
import com.dongxuexidu.douban4j.model.common.DoubanAttributeObj;
import com.dongxuexidu.douban4j.model.common.DoubanLinkObj;
import com.dongxuexidu.douban4j.model.subject.DoubanSubjectFeedObj;
import com.dongxuexidu.douban4j.model.subject.DoubanSubjectObj;
import com.dongxuexidu.douban4j.service.DoubanBookMovieMusicService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 13-4-26
 * Time: 上午9:52 * To change this template use File | Settings | File Templates.
 */
@Service
public class FetchBookInfoServiceImpl implements IFetchBookInfoService {
    @Autowired
    private IBookInfoDao bookInfoDao;
    DoubanBookMovieMusicService instance = new DoubanBookMovieMusicService();
    @Value("${bookRecommendedUrl}")
    private String bookRecommendedUrl;

    @Override
    public List<HotBook> getRecommendedBookByUserId(String userId) throws Exception {
        String _bookRecommendedUrl = this.bookRecommendedUrl;
        _bookRecommendedUrl = _bookRecommendedUrl.replace("{userId}", userId);
        //bookRecommendedUrl = "http://10.108.210.121:8080/BS/reader/2011110720/books/json?limit=30";
        URL url = new URL(_bookRecommendedUrl);
        List<HotBook> list = new ArrayList<HotBook>(18);
        Set<HotBook> set = new HashSet<HotBook>();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line.trim());
        }
        JSONObject jsonObject = new JSONObject(sb.toString());
        JSONArray jsonArray = jsonObject.getJSONArray("list");
        int arraySize = jsonArray.length();
        int size = 0;
        for (int i = 0; i < arraySize; i++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            String bookTitle = jsonObject1.getString("title");
            HotBook hotBook = new HotBook();
            String names[] = bookTitle.split("=");
            hotBook.setChineseName(names[0]);
            if (names.length > 1) {
                hotBook.setEnglishName(names[1].trim());
            }
            hotBook = this.searchDoubanBookInfo(null, hotBook.getChineseName(), 0, 1, hotBook, 2);
            if (hotBook == null) {
                continue;
            }
            set.add(hotBook);
            if (size < set.size()) {
                list.add(hotBook);
                size = set.size();
            }
        }

        return list;
    }

    @Override
    public List<HotBook> getAllHotBooks(int flag) throws Exception {
        return this.bookInfoDao.find("from HotBook h where h.flag=?", flag);
    }

    @Override
    public void getBooksByHotQueryWord(Set<String> hotQueryWord) throws Exception {
        int startIndex = 0;
        int maxResult = 3;
        for (String hotWord : hotQueryWord) {
            System.out.println("***********************" + hotWord + " " + Calendar.getInstance().getTime());
            for (int i = startIndex; i < maxResult * 2; i += 2) {
                try {
                    this.searchDoubanBookInfo(hotWord, hotWord, i, 1, null, 1);
                } catch (Exception e) {
                    continue;
                }
            }
        }
    }

    @Override
    public void fetchAndSaveDoubanBookInfo(Set<HotBook> set) throws Exception {
        int startIndex = 0;
        int maxResult = 1;
        String tag = null;
        for (HotBook hotBook1 : set) {
            String q = hotBook1.getChineseName();
            System.out.println("==================" + Calendar.getInstance().getTime());
            this.searchDoubanBookInfo(tag, q, startIndex, maxResult, hotBook1, 0);
            System.out.println("==================");
        }
    }

    public HotBook searchDoubanBookInfo(String tag, String q, int startIndex, int maxResult, HotBook hotBook1, int flag) throws Exception {
        DoubanSubjectFeedObj result = null;
        DoubanSubjectObj obj = null;
        try {
            result = instance.searchBook(q, tag, startIndex, maxResult);
            obj = result.getSubjects().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (obj == null) {
            return null;
        }
        String id = obj.getId();
        int lastIndex = id.lastIndexOf("/");
        id = id.substring(lastIndex + 1);
        System.out.println("id:" + id + " tag:" + q);
        HotBook hotBook = this.bookInfoDao.findEntity("from HotBook h where h.doubanId=? and h.flag=?", id, flag);
        if (hotBook == null) {
            hotBook = new HotBook();
            //hotBook.setTag(q);
            hotBook.setTag(q);
            hotBook.setRating(String.valueOf(obj.getRating().getAverage()));
            hotBook.setChineseName(obj.getTitle());
            if (hotBook1 != null && hotBook1.getEnglishName() != null) {
                hotBook.setEnglishName(hotBook1.getEnglishName());
            }
            if (hotBook1 != null && hotBook1.getLabHref() != null) {
                hotBook.setLabHref(hotBook1.getLabHref());
            }
            hotBook.setDoubanId(id);
            DoubanSubjectObj obj1 = instance.getBookInfoById(Long.valueOf(id));
            hotBook.setDescription(obj1.getSummary());
            List<DoubanLinkObj> linkList = obj1.getLinks();
            for (int i = 0; i < linkList.size(); i++) {
                DoubanLinkObj doubanLinkObj = linkList.get(i);
                String rel = doubanLinkObj.getRel();
                String href = doubanLinkObj.getHref();
                if (rel.equals("alternate")) {
                    hotBook.setDoubanHref(href);
                }
                if (rel.equals("image")) {
                    hotBook.setImgUrl(href);
                }
            }
            List<DoubanAttributeObj> attributeObjList = obj1.getAttributes();
            for (int i = 0; i < attributeObjList.size(); i++) {
                String name = attributeObjList.get(i).getName();
                String value = attributeObjList.get(i).getValue();
                if (name.equals("pages")) {
                    hotBook.setPages(value);
                }
                if (name.equals("author")) {
                    hotBook.setAuthor(value);
                }
                if (name.equals("publisher")) {
                    hotBook.setPublisher(value);
                }
                if (name.equals("price")) {
                    hotBook.setPrice(value);
                }
                if (name.equals("pubdate")) {
                    hotBook.setPubDate(value);
                }
                if (name.equals("isbn10")) {
                    hotBook.setIsbn(value);
                }
                if (name.equals("binding")) {
                    hotBook.setBinding(value);
                }
            }
            hotBook.setFlag(flag);
            this.bookInfoDao.save(hotBook);
        }
        return hotBook;
    }


    public void fetch200BooksInfoFromLab(Set<HotBook> set) throws Exception {
        int startIndex = 0;
        int maxResult = 1;
        String tag = null;
        for (HotBook hotBook1 : set) {
            String q = hotBook1.getChineseName();
            System.out.println("==================" + Calendar.getInstance().getTime());
            this.searchDoubanBookInfo(tag, q, startIndex, maxResult, hotBook1, 9);
            System.out.println("==================");
        }
    }


    public static void main(String[] args) throws Exception {
        new FetchBookInfoServiceImpl().getRecommendedBookByUserId("2011110720");
    }
}
