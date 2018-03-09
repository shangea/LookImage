package com.mays.lookimage.aitaotu;

import com.mays.lookimage.activity.MainActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CrawlMain {

    private final MainActivity.OnGetUrlFinishListener mListener;
    private int num = 5;
    private Map<String, List<String>> mUrlMap;

    public CrawlMain(MainActivity.OnGetUrlFinishListener listener) {
        mListener = listener;
    }

    public void getUrl() {
        try {
            String baseUrl = "https://www.aitaotu.com/taotu/";
            Document parseOut = Jsoup.connect(baseUrl).execute().parse();
            Elements as = parseOut.select(".taotu-main a > img");

            mUrlMap = new LinkedHashMap<>();// <标题,urlList>
            Map<String, String> hrefMap = new LinkedHashMap<>();// <标题,url>
            for (Element a : as) {
                String title = a.parent().nextElementSibling().text();
                String url = a.parent().attr("abs:href");
                hrefMap.put(title, url);
            }
//            Document parse = Jsoup.connect(url).execute().parse();
//            List<String> urlList = new ArrayList<>();
//            paserImage(parse,urlList);
//            urlMap.put(title, urlList);
            for (int i = 0; i < num; i++) {
                new MyThread(hrefMap, i).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void paserImage(Document parse, List<String> urlList) {
        try {
//            String title = parse.select("#photos h2").first().text();
            Elements imgs = parse.select("#big-pic a img");
            for (Element img : imgs) {
                urlList.add(img.attr("abs:src"));// url
            }
            Element nextPage = parse.select("#nl a").first();
            if (nextPage != null) {
                paserImage(Jsoup.connect(nextPage.attr("abs:href")).execute().parse(), urlList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class MyThread extends Thread {

        private final Map<String, String> mHrefMap;
        private final int no;

        public MyThread(Map<String, String> hrefMap, int no) {
            mHrefMap = hrefMap;
            this.no = no;
        }

        @Override
        public void run() {
            super.run();
            String[] strings = (String[]) mHrefMap.keySet().toArray();
            for (int i = 0; i < strings.length; i++) {
                if (i % num == no) {
                    try {
                        Document parse = Jsoup.connect(mHrefMap.get(strings[i])).execute().parse();
                        List<String> urlList = new ArrayList<>();
                        paserImage(parse,urlList);
                        mUrlMap.put(strings[i], urlList);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (--num == 0) {
                if (mListener != null) {
                    mListener.onGetUrlFinish(mUrlMap);
                }
            }
        }
    }

}
