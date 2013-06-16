package com.bupt.pcncad.service.bookJson;

import com.bupt.pcncad.dao.DoubanJson.IDoubanJsonDao;
import com.bupt.pcncad.dao.academy.major.course.ICourseDao;
import com.bupt.pcncad.domain.Course;
import com.bupt.pcncad.domain.DoubanJson;
import org.hibernate.LockMode;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: zhang
 * Date: 13-4-19
 * Time: 下午9:29
 * To change this template use File | Settings | File Templates.
 */
@Service
public class BookJsonServiceImpl implements IBookJsonService{

    @Autowired
    private IDoubanJsonDao doubanJsonDao;

    @Autowired
    private ICourseDao courseDao;

    public Set<DoubanJson> getJsonString(String urlPath, String courseId) throws Exception {
        urlPath = "https://api.douban.com/v2/book/search?apikey=04c8b4bc5f51496c0e5a1e351df3dc44&count=6&q=" + urlPath;
        URL url = new URL(urlPath);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        InputStream inputStream = connection.getInputStream();
        //对应的字符编码转换
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String str = null;
        StringBuffer sb = new StringBuffer();
        while ((str = bufferedReader.readLine()) != null) {
            sb.append(str);
        }
        reader.close();
        connection.disconnect();

        return jsonToObj(sb.toString(),courseId);
    }

    public Set<DoubanJson> jsonToObj(String jsonStr, String courseId) throws Exception {

        JSONObject jsonObject = new JSONObject(jsonStr);
        JSONArray books= jsonObject.getJSONArray("books");
        int length = books.length();
        Set<DoubanJson> jsonSets = new HashSet<DoubanJson>();

        for (int i = 0; i < length; i++) {

            jsonObject = books.getJSONObject(i);
            String title = jsonObject.getString("title");
            JSONArray author = jsonObject.getJSONArray("author");
            StringBuffer authors = new StringBuffer();
            for(int j = 0; j < author.length(); j++){
                String authorObject = (String)author.get(j);
                authors.append(authorObject);
                if(j < author.length()-1)
                    authors.append("/");
            }
            JSONArray translator = jsonObject.getJSONArray("translator");
            StringBuffer translators = new StringBuffer();
            for(int j = 0; j < translator.length(); j++){
                String translatorObject = (String)translator.get(j);
                translators.append(translatorObject);
                if(j < translator.length()-1)
                    translators.append("/");
            }
            String pubdate = jsonObject.getString("pubdate");
            String publisher = jsonObject.getString("publisher");
            String price = jsonObject.getString("price");
            String summary = jsonObject.getString("summary");
            String alt = jsonObject.getString("alt");
            String isbn10 = jsonObject.getString("isbn10");
            JSONObject images = jsonObject.getJSONObject("images");
            String smallPic = images.getString("small");
            String mediumPic = images.getString("medium");
            String largePic = images.getString("large");
            String url = jsonObject.getString("url");

            DoubanJson doubanJson = new DoubanJson();
            doubanJson.setTitle(title);
            doubanJson.setAuthors(authors.toString());
            doubanJson.setTranslators(translators.toString());
            doubanJson.setPubdate(pubdate);
            doubanJson.setPublisher(publisher);
            doubanJson.setPrice(price);
            doubanJson.setSummary(summary);
            doubanJson.setAlt(alt);
            doubanJson.setIsbn10(isbn10);
            doubanJson.setSmallPic(smallPic);
            doubanJson.setMediumPic(mediumPic);
            doubanJson.setLargePic(largePic);
            doubanJson.setUrl(url);
            Course course = this.courseDao.get(courseId, LockMode.UPGRADE);
            doubanJson.setCourse(course);
            this.doubanJsonDao.save(doubanJson);
            jsonSets.add(doubanJson);

        }
        return jsonSets;
//        Iterator it = jsonlists.iterator();
//        while(it.hasNext()){
//            DoubanJson jsonlist = (DoubanJson)it.next();
//            String title = jsonlist.getTitle();
//            System.out.println(title);
//
//        }
    }

    public List<DoubanJson> getBookByCourse(String courseId) throws Exception {
        return this.doubanJsonDao.find("from DoubanJson d where d.course.id=?",courseId);
    }

}
