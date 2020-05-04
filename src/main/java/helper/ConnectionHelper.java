package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
    private static Connection connection;
    private static final String DATABASE_NAME = "javatest";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/"+DATABASE_NAME+"?useUnicode=true&serverTimezone=Asia/Bangkok&characterEncoding=utf-8";
    private static final String DATABASE_USE = "root";
    private static final String DATABASE_PWD = "123@123";

    public static Connection getConnection() throws SQLException {
        if (connection==null){
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USE, DATABASE_PWD);
//            System.out.println("ket noi database thanh cong");
        }
        return connection;
    }
}
