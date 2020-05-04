package controlerCraw;

import entity.Article;
import helper.ConnectionHelper;
import menu.ViewMenu;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// hàm lấy nội dung tin tức
public class CrawNew {

    public static void crawlDataVnexpress(String urlname) {

        try {
            System.out.println("Bat dau lay link moi tu trang chu vnepress");
            ArrayList<String> listlink = new ArrayList<String>();
            Document document = Jsoup.connect(urlname).get();
            Elements aElements = document.select("a");
            if (aElements.size() == 0) {
                return;
            }
            for (int i = 0; i < aElements.size(); i++) {
                Element item = aElements.get(i);
                String link = item.attr("href");
                if (link.contains(".html") && !link.contains("#box_comment")) {
                    listlink.add(link);
                }
            }


            System.out.println("Tong cong lay duoc: " + listlink.size() + " link");
            for (int i = 0; i < listlink.size(); i++) {
                Article article = crawVnexpress(listlink.get(i));
            }
            ArrayList<CrawlerThread> listThreads = new ArrayList<CrawlerThread>();
            for (int i = 0; i < listlink.size(); i++) {
                String url = listlink.get(i);
                CrawlerThread crawler = new CrawlerThread(url);
                listThreads.add(crawler);
                crawler.start();
            }
            for (CrawlerThread crawler : listThreads) {
                crawler.join();
            }
        } catch (Exception ex) {
            System.err.printf(ex.getMessage());
        }
    }

    public static void crawlDataPlovn(String urlname) {
        try {
            System.out.println("Bat dau lay link moi tu trang chu Dangcongsan");
            ArrayList<String> listlink = new ArrayList<String>();
            Document document = Jsoup.connect(urlname).get();
            Elements aElements = document.select("a");
            if (aElements.size() == 0) {
                return;
            }
            for (int i = 0; i < aElements.size(); i++) {
                Element item = aElements.get(i);
                String link = item.attr("href");
                if (link.contains(".html") && link.contains("https://") && !link.contains("Video")) {
                    System.out.println(link);
                    listlink.add(link);
                }
            }


            System.out.println("Tong cong lay duoc: " + listlink.size() + " link");
            for (int i = 0; i < listlink.size(); i++) {
                Article article = crawPlovn(listlink.get(i));
            }
            ArrayList<CrawlerThread> listThreads = new ArrayList<CrawlerThread>();
            for (int i = 0; i < listlink.size(); i++) {
                String url = listlink.get(i);
                CrawlerThread crawler = new CrawlerThread(url);
                listThreads.add(crawler);
                crawler.start();
            }
            for (CrawlerThread crawler : listThreads) {
                crawler.join();
            }
        } catch (Exception ex) {
            System.err.printf("Link web không thể truy cập");
        }
    }

    public static Article crawVnexpress(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            String title = document.select("h1.title-detail").text();
            String description = document.select("p.description").text();
            String contest = document.select("article.fck_detail").text();
            String forder = document.select("ul.breadcrumb>li>h2").text();
            Article article = new Article();
            article.setUrl(url);
            article.setSource("Vnexpress.vn");
            article.setTitle(title);
            article.setDescription(description);
            article.setContest(contest);
            article.setForder(forder);
            insertArticle(article);
            return article;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Article crawPlovn(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            String title = document.select("h1.main-title").text();
            String description = document.select("#chapeau").text();
            String contest = document.select("#abody").text();
            String forder = document.select("li> h4>a").text();
            Article article = new Article();
            article.setUrl(url);
            article.setSource("plo.vn");
            article.setTitle(title);
            article.setDescription(description);
            article.setContest(contest);
            article.setForder(forder);
            insertArticle(article);
            return article;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void insertArticle(Article article) {
        try {
            ConnectionHelper.getConnection();
            String queryString = "INSERT INTO `aricite`" +
                    "(id, title, url, description, contest, `source`, status, forder)" +
                    "VALUES (?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = ConnectionHelper.getConnection().prepareStatement(queryString);
            preparedStatement.setInt(1, article.getId());
            preparedStatement.setString(2, article.getTitle());
            preparedStatement.setString(3, article.getUrl());
            preparedStatement.setString(4, article.getDescription());
            preparedStatement.setString(5, article.getContest());
            preparedStatement.setString(6, article.getSource());
            preparedStatement.setInt(7, article.getStatus());
            preparedStatement.setString(8, article.getForder());
            preparedStatement.execute();
            System.out.println("Insert tin thanh cong lên DATABASE");
        } catch (SQLException e) {
            System.out.println("Url đã tồn tại");
        }
    }

    public static ArrayList<Article> loadArticleByFolder(String forder) {
        ArrayList<Article> listFolder = new ArrayList<Article>();
//        System.out.println(String.format("Loading data by forder: %s...", forder));
        try {
            String queryString = "SELECT * FROM `aricite` WHERE forder = ? ORDER BY id DESC LIMIT 10";
            PreparedStatement preparedStatement = ConnectionHelper.getConnection().prepareStatement(queryString);
            preparedStatement.setString(1, forder);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) { //chuyển con trỏ xuống dòng tiếp theo
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                Article article = new Article(id, title, description, forder);
                listFolder.add(article);
            }
//            System.out.println("Thao tac thanh cong");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listFolder;
    }
    public static Article loadArticleById(int id) {
        try {
            String queryString = "SELECT title, contest FROM `aricite` WHERE id = ?";
            PreparedStatement preparedStatement = ConnectionHelper.getConnection().prepareStatement(queryString);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) { //chuyển con trỏ xuống dòng tiếp theo
                String title = resultSet.getString("title");
                String contest = resultSet.getString("contest");
                Article article = new Article(id, title, contest);
                return article;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

