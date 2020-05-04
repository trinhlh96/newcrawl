package controlerCraw;

import controlerCraw.CrawNew;

public class MainCrawl {
    public static void main(String[] args) {
        CrawNew.crawlDataVnexpress("https://vnexpress.net/thoi-su");
        CrawNew.crawlDataVnexpress("https://vnexpress.net/thoi-su-p2");
        CrawNew.crawlDataVnexpress("https://vnexpress.net/the-gioi");
        CrawNew.crawlDataVnexpress("https://vnexpress.net/the-gioi-p2");
        CrawNew.crawlDataPlovn("https://plo.vn/");
        CrawNew.crawlDataPlovn("https://plo.vn/phap-luat/?trang=2");

    }

}
