package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private final static String DB_USERNAME = "postgres";
    private final static String DB_PASSWORD = "0000";
    private final static String DB_URL = "jdbc:postgresql://127.0.0.1:5432/taskDB";


    public static void connectionToBD() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
