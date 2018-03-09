package com.mays.lookimage.aitaotu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class CrawlMain {

    private static Pattern pattern = Pattern.compile("\\[(.*?)\\](.*?)");

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
//            Gson gson = new Gson();
//            String s = gson.toJson(hrefMap);
//            System.out.println(s);
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
