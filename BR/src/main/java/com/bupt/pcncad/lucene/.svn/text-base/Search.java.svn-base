/*
package com.bupt.pcncad.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 13-1-24
 * Time: 下午3:05
 * To change this template use File | Settings | File Templates.
 *//*

public class Search {
    private static final String INDEX_STORE_PATH = "data" + File.separator +"lucene"+File.separator+ "search";

    public void indexSearch(String searchType,String searchKey) throws IOException, ParseException {
        Directory directory = FSDirectory.open(new File(INDEX_STORE_PATH));
        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        //Term term = new Term(searchType,searchKey);
        //TermQuery termQuery = new TermQuery(term);
        QueryParser queryParser = new QueryParser(Version.LUCENE_40,"content",new StandardAnalyzer(Version.LUCENE_40));
        Query query = queryParser.parse(searchKey);
        TopDocs topDocs = indexSearcher.search(query, 100);
        ScoreDoc[]scoreDocs = topDocs.scoreDocs;
        for(ScoreDoc sd : scoreDocs){
            System.out.println(indexSearcher.doc(sd.doc).get("title")+":"+sd.score+":"+sd.shardIndex);
        }
        System.out.println(topDocs.totalHits+":"+indexSearcher.getIndexReader().totalTermFreq(new Term(queryParser.getToken(1).toString())));
    }

}
*/
