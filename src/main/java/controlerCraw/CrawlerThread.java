package controlerCraw;

public class CrawlerThread extends Thread {
    private String url;
    public CrawlerThread(String url){
        this.url = url;
    }

    @Override
    public void run() {
       CrawNew.crawVnexpress(url);
       CrawNew.crawPlovn(url);
    }
}
