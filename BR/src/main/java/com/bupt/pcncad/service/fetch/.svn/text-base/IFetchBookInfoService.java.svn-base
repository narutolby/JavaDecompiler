package com.bupt.pcncad.service.fetch;

import com.bupt.pcncad.domain.HotBook;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 13-4-25
 * Time: 下午10:32
 * To change this template use File | Settings | File Templates.
 */
public interface IFetchBookInfoService {

    public void fetchAndSaveDoubanBookInfo(Set<HotBook> set) throws Exception;

    public List<HotBook> getAllHotBooks(int flag) throws Exception;

    public void getBooksByHotQueryWord(Set<String> hotQueryWord) throws Exception;

    public List<HotBook> getRecommendedBookByUserId(String userId) throws Exception;

    public void fetch200BooksInfoFromLab(Set<HotBook> set) throws Exception;


    public HotBook searchDoubanBookInfo(String tag, String q, int startIndex, int max, HotBook hotBook, int flag) throws Exception;
}
