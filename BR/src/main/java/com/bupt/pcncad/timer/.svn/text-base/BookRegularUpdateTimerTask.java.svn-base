package com.bupt.pcncad.timer;

import com.bupt.pcncad.service.fetch.FetchBookInfoServiceImpl;
import com.bupt.pcncad.service.fetch.IFetchBookInfoService;
import com.bupt.pcncad.service.index.AbstractIndexProcess;
import com.bupt.pcncad.util.LoggerUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 13-4-23
 * Time: 下午3:19
 * To change this template use File | Settings | File Templates.
 */
@Component
public class BookRegularUpdateTimerTask implements InitializingBean {

    private Timer timer = new Timer(true);
    @Autowired
    private BookInfoCrawler bookInfoCrawler;
    @Autowired
    private IFetchBookInfoService fetchBookInfoService;

    @Autowired
    @Qualifier("resourceIndexProcess")
    private AbstractIndexProcess resourceIndexProcess;
    @Autowired
    @Qualifier("communityIndexProcess")
    private AbstractIndexProcess communityIndexProcess;

    @Override
    public void afterPropertiesSet() throws Exception {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    bookInfoCrawler.fetchBookInfo();
                    fetchBookInfoService.fetchAndSaveDoubanBookInfo(bookInfoCrawler.getAllBooks());
                    bookInfoCrawler.fetchBookInfoByHotSearchQuery();
                    fetchBookInfoService.getBooksByHotQueryWord(bookInfoCrawler.getHotWordSet());
                    LoggerUtil.debug(this.getClass(), Calendar.getInstance().toString() + " fetch book info");
                } catch (IOException e) {
                    LoggerUtil.error(this.getClass(), "Timer Error");
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }, Calendar.getInstance().getTime(), 60 * 60 * 1000 * 24);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    System.out.println("***********************index begain****************");
                    resourceIndexProcess.createIndex();
                    communityIndexProcess.createIndex();
                    System.out.println("***********************index end****************");
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

            }
        }, Calendar.getInstance().getTime(), 60*1000 * 24);
    }

    /*  public static void main(String[] args) throws Exception {
        BookInfoCrawler bookInfoCrawler = new BookInfoCrawler();
        IFetchBookInfoService fetchBookInfoService = new FetchBookInfoServiceImpl();
        bookInfoCrawler.fetchBookInfo();
        bookInfoCrawler.fetchBookInfoByHotSearchQuery();
        fetchBookInfoService.fetchAndSaveDoubanBookInfo(bookInfoCrawler.getAllBooks());
        fetchBookInfoService.getBooksByHotQueryWord(bookInfoCrawler.getHotWordSet());
    }*/
}
