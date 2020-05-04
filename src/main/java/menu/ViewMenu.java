package menu;

import java.util.Scanner;

public class ViewMenu {
    public static void menuNew() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------Menu Danh Mục Tin Tức-----------");
        System.out.println("1: Tin Thế giới");
        System.out.println("2: Tin Thời sự");
        System.out.println("3: Tin Pháp luật");
        System.out.println("4: Exit");
        System.out.println("Mời bạn nhập lựa chọn từ 1 đến 4");
        int choice = 0;
        while (true) {
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice <= 0 || choice >= 5) {
                System.out.println("Vui lòng nhập lựa chọn từ 1 đến 4");
            } else {
                break;
            }
        }
        switch (choice) {
            case 1:
                ControllerMenu.findFolder("Thế giới");
                break;
            case 2:
                ControllerMenu.findFolder("Thời sự");
                break;
            case 3:
                ControllerMenu.findFolder("Pháp luật");
                break;
            case 4:
                System.out.println("Byby");
                System.exit(0);
                break;
        }
    }
}
