package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userDao = new UserServiceImpl();
        userDao.createUsersTable();
        userDao.saveUser("Alex", "Petrov", (byte) 26);
        userDao.saveUser("Anton", "Ivanov", (byte) 35);
        userDao.saveUser("Petya", "Petrov", (byte) 55);
        userDao.saveUser("Vasya", "Sidorov", (byte) 6);


        List<User> users = userDao.getAllUsers();
        for(User user : users) {
            System.out.println(user);
        }
        userDao.cleanUsersTable();
        userDao.dropUsersTable();

    }
}
