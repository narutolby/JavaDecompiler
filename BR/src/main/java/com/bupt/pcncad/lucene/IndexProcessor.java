/*
package com.bupt.pcncad.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.StopwordAnalyzerBase;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.*;

import static org.apache.lucene.util.Version.LUCENE_40;

*/
/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 13-1-15
 * Time: 下午8:01
 * To change this template use File | Settings | File Templates.
 *//*

public class IndexProcessor {
    private static final String INDEX_STORE_PATH = "data" +File.separator+"lucene"+File.separator + "search";

    public void createIndex(String inputDir) throws IOException {
        // IndexWriter writer = new IndexWriter(INDEX_STORE_PATH,new MMAn);
        */
/*String s = "实际中可以用来支持特定环境下的西文符号的处理。由于不完成单词过滤和小写字符转换功能，也不需要过滤词库支持。词汇分割策略上简单使用非英文字符作为分割符，不需要分词词库支持。";
        Analyzer[] analyzers = {
                new WhitespaceAnalyzer(Version.LUCENE_40),
                new SimpleAnalyzer(Version.LUCENE_40),
                new KeywordAnalyzer(),
                new StopAnalyzer(Version.LUCENE_40),
                new ChineseAnalyzer(),
                new CJKAnalyzer(Version.LUCENE_40)
        };
        for (Analyzer analyzer : analyzers) {
            TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(s));
            CharTermAttribute termAttribute = tokenStream.addAttribute(CharTermAttribute.class);
            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                System.out.print("[" + termAttribute.toString() + "]");
            }
            System.out.println("\n");
        }
*//*

        //IndexWriter indexWriter = new IndexWriter(INDEX_STORE_PATH,new CJKAnalyzer(Version.LUCENE_40),true);
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_40,analyzer);
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        Directory directory = FSDirectory.open(new File(INDEX_STORE_PATH));
        IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);
        File inputPath = new File(inputDir);
        File[]fileList = inputPath.listFiles();
        for(File file:fileList){
            Document document = new Document();
           */
/* FieldType type = new FieldType();
            type.setTokenized(true);
            type.setIndexed(true);*//*

            document.add(new StringField("title",file.getName(),Field.Store.YES));
            document.add(new TextField("content",this.loadFileToString(file), TextField.Store.YES));
            indexWriter.addDocument(document);
        }
        indexWriter.close();
    }

    public static void main(String[] args) throws IOException {
        new IndexProcessor().createIndex("");
    }
    public String loadFileToString(File path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while((line=bufferedReader.readLine())!=null){
           sb.append(line) ;
        }
        return sb.toString();

    }

}
*/
