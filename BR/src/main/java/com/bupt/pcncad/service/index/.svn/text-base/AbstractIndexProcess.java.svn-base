package com.bupt.pcncad.service.index;

import com.bupt.pcncad.webContext.BRWebApplicationContext;
import jeasy.analysis.MMAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermDocs;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 13-3-3
 * Time: 下午5:58
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractIndexProcess {
    private static final String RESOURCE_INDEX_PATH = "data" + File.separator + "lucene" + File.separator + "index" + File.separator + "resource";
    private static final String COMMUNITY_INDEX_PATH = "data" + File.separator + "lucene" + File.separator + "index" + File.separator + "community";
    private static final Map<Class, String> pathMap = new HashMap<Class, String>();

    static {
        pathMap.put(ResourceIndexProcess.class, RESOURCE_INDEX_PATH);
        pathMap.put(CommunityIndexProcess.class, COMMUNITY_INDEX_PATH);
    }

    public abstract void createIndex() throws Exception;

    public TermDocs searchTermDocs(String keyType, String keyWord) throws Exception {
        IndexSearcher is = new IndexSearcher(BRWebApplicationContext.getWebRootPath()+File.separator+pathMap.get(this.getClass()));
        Term term = new Term(keyType, keyWord);
        return is.getIndexReader().termDocs(term);

    }


    public Hits searchHits(String keyType, String keyWord) throws Exception {
        IndexSearcher is = new IndexSearcher(BRWebApplicationContext.getWebRootPath()+File.separator +pathMap.get(this.getClass()));
        QueryParser parser = new QueryParser(keyType, new MMAnalyzer());
        parser.setDefaultOperator(QueryParser.AND_OPERATOR);
        Query query = parser.parse(keyWord);
        //Term term = new Term(keyType,keyWord);
        //Query query = new TermQuery(term);
        return is.search(query);
    }

    protected static String getPathByClass(Class clazz) {
        return pathMap.get(clazz);
    }

}
