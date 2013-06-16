package com.bupt.pcncad.service.index;

import com.bupt.pcncad.dao.community.ICommunityDao;
import com.bupt.pcncad.domain.Community;
import com.bupt.pcncad.domain.Resource;
import com.bupt.pcncad.webContext.BRWebApplicationContext;
import jeasy.analysis.MMAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 13-3-3
 * Time: 下午5:38
 * To change this template use File | Settings | File Templates.
 */
@Service
@Qualifier("communityIndexProcess")
public class CommunityIndexProcess extends AbstractIndexProcess {
    @Autowired
    private ICommunityDao communityDao;

    @Override
    public void createIndex() throws Exception {
        IndexWriter iw = new IndexWriter(BRWebApplicationContext.getWebRootPath() + File.separator + getPathByClass(this.getClass()), new MMAnalyzer(),false);
        iw.setUseCompoundFile(false);
        List<Community> list = communityDao.find("from Community c where c.state=1");
        for (Community community : list) {
            Document document = new Document();
            Field name = new Field("name", community.getName(), Field.Store.YES, Field.Index.TOKENIZED);
            Field size = new Field("creator", community.getAdmin().getUserName(), Field.Store.YES, Field.Index.UN_TOKENIZED);
            Field uploader = new Field("course", community.getCourse().getName(), Field.Store.YES, Field.Index.UN_TOKENIZED);
            Field id= new Field("id", community.getId(), Field.Store.YES, Field.Index.NO);
            document.add(name);
            document.add(size);
            document.add(uploader);
            document.add(id);
            iw.addDocument(document);
        }
        iw.close();
    }
}
