package com.bupt.pcncad.controller.search;

import com.bupt.pcncad.service.index.AbstractIndexProcess;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.Hits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 13-3-3
 * Time: 下午8:44
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class SearchController {
    @Autowired
    @Qualifier("resourceProcessIndex")
    private AbstractIndexProcess resourceIndexProcess;
    @Autowired
    @Qualifier("communityIndexProcess")
    private AbstractIndexProcess communityIndexProcess;

    @RequestMapping("/search")
    public String search(@RequestParam("q") String keyWord, ModelMap modelMap) throws Exception {
        keyWord = new String(keyWord.getBytes("ISO-8859-1"), "UTF-8");
        Hits resourceHits = resourceIndexProcess.searchHits("fileName", keyWord);
        Hits communityHits = communityIndexProcess.searchHits("name", keyWord);
        Hits communityHits1 = communityIndexProcess.searchHits("creator", keyWord);
        Set<Document> resourceSet = new HashSet<Document>();
        Set<Document> communitySet = new HashSet<Document>();
        for (int i = 0; i < resourceHits.length(); i++) {
            resourceSet.add(resourceHits.doc(i));
        }
        for (int i = 0; i < communityHits.length(); i++) {
            communitySet.add(communityHits.doc(i));
        }
        for (int i = 0; i < communityHits1.length(); i++) {
            communitySet.add(communityHits1.doc(i));
        }
      /*  URL url = new URL("http://211.68.68.197/opac_two/search2/searchout.jsp");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        conn.setUseCaches(false);
        conn.setDoOutput(true);
        conn.addRequestProperty("suchen_type", "1");
        conn.addRequestProperty("suchen_word", keyWord);
        conn.addRequestProperty("suchen_match", "qx");
        conn.addRequestProperty("recordtype", "all");
        conn.addRequestProperty("library_id", "all");
        conn.addRequestProperty("suchen_way", "all");
        conn.addRequestProperty("show_type", "wenzi");
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(conn.getInputStream(), "gbk");
            BufferedReader br = new BufferedReader(inputStreamReader);
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line.trim());

            }
        }catch (Exception e){
            e.printStackTrace();
        }*/
        try{
            modelMap.put("resources", resourceSet);
            modelMap.put("communities", communitySet);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "search/search";
    }

}
