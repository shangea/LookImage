package com.mays.lookimage.aitaotu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CrawlMain {

    public Map<String, List<String>> getUrl() {
        try {
            String baseUrl = "https://www.aitaotu.com/taotu/";
            Document parseOut = Jsoup.connect(baseUrl).execute().parse();
            Elements as = parseOut.select(".taotu-main a > img");
            Map<String, List<String>> hrefMap = new LinkedHashMap<>();// <标题,url>
            int i = 0;
            for (Element a : as) {
//                hrefSet.add(a.parent().attr("abs:href"));
//                System.out.println(a.parent().nextElementSibling().text());
                Document parse = Jsoup.connect(a.parent().attr("abs:href")).execute().parse();
                List<String> urlList = new ArrayList<>();
                paserImage(parse,urlList);
                hrefMap.put(a.parent().nextElementSibling().text(), urlList);
                if (++i == 5)  //控制一下组数
                    break;
            }
            return hrefMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

}
