package com.mays.lookimage.aitaotu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by 666666 on 2018/1/13.
 */
public class MainRun {

//    private static final int MAX_POOL_SIZE = 5;
//    public static void main(String[] args) throws IOException {
//        //  先从配置文件读要下载的url
////        BlockingQueue<String> hrefQueue = SaveQueueRunnable.readFile();
//        // 如果读不到，则获取首页的下载链接
////        if (hrefQueue == null){
////            hrefQueue = readIndexFile();
////        }
//        BlockingQueue<String> hrefQueue = readIndexFile();
//        // 启动下载功能
//        ExecutorService pool = Executors.newFixedThreadPool(MAX_POOL_SIZE);
//        for (int i = 0; i < MAX_POOL_SIZE; i ++){
//            pool.submit(new DownloadImageCallable(hrefQueue, pool));
//        }
//        pool.shutdown();
//    }

//    private static BlockingQueue<String> readIndexFile() throws IOException {
//        String baseUrl = "https://www.aitaotu.com/taotu/";
//        Document parse = Jsoup.connect(baseUrl).execute().parse();
//        Elements as = parse.select(".taotu-main a > img");
//        Set<String> hrefSet = new HashSet<>();
//        for (Element a : as){
//            hrefSet.add(a.parent().attr("abs:href"));
//        }
//        BlockingQueue<String> hrefQueue = new LinkedBlockingDeque<>();
//        hrefQueue.addAll(hrefSet);
//        return hrefQueue;
//    }
}
