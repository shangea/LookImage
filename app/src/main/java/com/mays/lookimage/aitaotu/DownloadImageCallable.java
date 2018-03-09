package com.mays.lookimage.aitaotu;

import java.util.concurrent.Callable;

/**
 * Created by 666666 on 2018/1/13.
 */
public class DownloadImageCallable implements Callable<String> {
//    private BlockingQueue<String> queue;
//    private ExecutorService pool;
//    private static Pattern pattern = Pattern.compile("\\[(.*?)\\](.*?)");
//    public DownloadImageCallable(BlockingQueue<String> queue, ExecutorService pool) {
//        this.queue = queue;
//        this.pool = pool;
//    }
//
    @Override
    public String call() throws Exception {
//        try {
//            Document parse = Jsoup.connect(queue.poll()).execute().parse();
//            paserImage(parse);
//            queue.add(parse.select(".preandnext.connext a").first().attr("abs:href"));
//        }catch (Exception e){
//
//        }finally {
////            new Thread(new SaveQueueRunnable(queue)).start();     // 自动保存(断点续传)
////            pool.submit(new DownloadImageCallable(queue, pool));  // 下载完之后再起一个线程,继续下载
//        }
        return "OK";
    }
//
//    private void paserImage(Document parse){
//        try {
//            String title = parse.select("#photos h2").first().text();
//            Elements imgs = parse.select("#big-pic a img");
//            System.out.println(title);
//            for (Element img : imgs){
//                img.attr("abs:src");// url
//            }
//            Element nextPage = parse.select("#nl a").first();
//            if (nextPage != null){
//                paserImage(Jsoup.connect(nextPage.attr("abs:href")).execute().parse());
//            }
//        }catch (Exception e){
//
//        }
//    }

//    private void downloadImage(String title, String attr) throws IOException {
//        Matcher matcher = pattern.matcher(title);
//        if (!matcher.matches()){
//            return;
//        }
//        String fileName = attr.replace("https://img.aitaotu.cc:8089/Pics/","").replace("/", "");
//        File file = new File("./output/" + matcher.group(1) + "/" + matcher.group(2).trim() + "/" + fileName);
//        if (file.exists()){
//            return;
//        }
//        CloseableHttpResponse execute = HttpClients.createDefault().execute(new HttpGet(attr));
//        FileUtils.copyInputStreamToFile(execute.getEntity().getContent(), file);
//    }
}
