package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    //реализуйте настройку соеденения с БД
    private final static String DB_USERNAME = "postgres";
    private final static String DB_PASSWORD = "0000";
    private final static String DB_URL = "jdbc:postgresql://127.0.0.1:5432/taskDB";
    private static Connection connection;

    {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Util() {

    }

    public Connection getConnection() {
        return connection;
    }

    private static SessionFactory sessionFactory;


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, "jdbc:postgresql://127.0.0.1:5432/taskDB");
                settings.put(Environment.USER, "postgres");
                settings.put(Environment.PASS, "0000");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");

                settings.put(Environment.SHOW_SQL, "true");

                //settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "update");
                configuration.addProperties(settings);
                configuration.addAnnotatedClass(User.class);


                sessionFactory = configuration.buildSessionFactory();


            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }

}
