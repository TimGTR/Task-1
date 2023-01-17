package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Alex", "Petrov", (byte) 26);
        userService.saveUser("Anton", "Ivanov", (byte) 35);
        userService.saveUser("Petya", "Petrov", (byte) 55);
        userService.saveUser("Vasya", "Sidorov", (byte) 6);

        userService.removeUserById(2);


        List<User> users = userService.getAllUsers();
        for(User user : users) {
            System.out.println(user);
        }
       // userService.cleanUsersTable();
//        userService.dropUsersTable();

    }
}
