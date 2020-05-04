package menu;

import controlerCraw.CrawNew;
import entity.Article;

import java.util.ArrayList;
import java.util.Scanner;

public class ControllerMenu {

    public static Article findFolder(String forder) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Article> list = CrawNew.loadArticleByFolder(forder);
        System.out.printf("----------Danh sách tin %s-----------\n", forder);
        System.out.printf("%5s %20s %s\n", "Id", "", "Title");
        for (int i = 0; i < list.size(); i++) {
            if (list == null) {
                System.out.println(String.format("Article voi forder: %s khong ton tai", forder));
            }
            System.out.printf("%6d %10s %s\n", list.get(i).getId(), "", list.get(i).getTitle());
        }
        System.out.println("-----------Menu lựa chọn----------------");
        System.out.println("Nhập ID để tiếp đọc tin");
        System.out.println("Nhập số 0 để quay lại Menu Danh Mục Tin Tức");
        int luachon2 = scanner.nextInt();
        scanner.nextLine();
        if (luachon2 == 0) {
            ViewMenu.menuNew();
            return null;
        }
        Article article = CrawNew.loadArticleById(luachon2);
        if (article == null) {
            System.out.println(String.format("id: %d bạn vừa nhập không tồn tại", luachon2));
        }
        System.out.println(article.toNewString());
        while (true){
            System.out.println("--------Menu lựa chọn---------");
            System.out.println("Vui lòng nhập số 1 để quay lại Menu danh mục");
            System.out.println("Vui lòng nhập số 2 để quay lại Danh sách tin");
            System.out.println("Vui lòng nhập số 3 để thoát chương trình");
            luachon2 = scanner.nextInt();
            scanner.nextLine();
            switch (luachon2){
                case 1:
                    ViewMenu.menuNew();
                    break;
                case 2:
                    findFolder(forder);
                    break;
                case 3:
                    System.out.println("By by");
                    System.exit(0);
                default:
                    System.out.println("Bạn đã nhập sai yêu cầu");
                    break;
            }
            return article;
        }
    }
}