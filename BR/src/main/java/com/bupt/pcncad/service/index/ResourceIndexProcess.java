package com.bupt.pcncad.service.index;

import com.bupt.pcncad.dao.resource.IResourceDao;
import com.bupt.pcncad.domain.Resource;
import com.bupt.pcncad.webContext.BRWebApplicationContext;
import jeasy.analysis.MMAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermDocs;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 13-3-3
 * Time: 下午4:25
 * To change this template use File | Settings | File Templates.
 */
@Service
@Qualifier("resourceProcessIndex")
public class ResourceIndexProcess extends AbstractIndexProcess {
    @Autowired
    private IResourceDao resourceDao;

    @Override
    public void createIndex() throws Exception {
        IndexWriter iw = new IndexWriter(BRWebApplicationContext.getWebRootPath() + File.separator + getPathByClass(this.getClass()), new MMAnalyzer(),true);
        iw.setUseCompoundFile(false);
        List<Resource> list = resourceDao.find("from Resource r where r.deleteFlag=0");
        for (Resource resource : list) {
            Document document = new Document();
            Field name = new Field("fileName", resource.getResourceRealName(), Field.Store.YES, Field.Index.TOKENIZED);
            Field size = new Field("fileSize", String.valueOf(resource.getResourceSize()), Field.Store.YES, Field.Index.NO);
            Field uploader = new Field("uploader", resource.getUploadUser().getUserName(), Field.Store.YES, Field.Index.NO);
            Field uploadTime = new Field("uploadTime", resource.getUploadTime().toString(), Field.Store.YES, Field.Index.NO);
            Field id = new Field("id",resource.getId(),Field.Store.YES,Field.Index.NO);
            document.add(name);
            document.add(size);
            document.add(uploader);
            document.add(uploadTime);
            document.add(id);
            iw.addDocument(document);
        }
        iw.close();
    }
}
