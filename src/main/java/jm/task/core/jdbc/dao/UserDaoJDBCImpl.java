package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String TABLENAME = "Users";


    Util util = new Util();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try(Statement statement = util.getConnection().createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLENAME + "(id SERIAL PRIMARY KEY, name VARCHAR(40), lastName VARCHAR(40), age SMALLINT)";
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println("Такая таблица уже существует");
        }
    }


    public void dropUsersTable() {
            try(Statement statement = util.getConnection().createStatement()) {
                String sql = "DROP TABLE IF EXISTS " + TABLENAME;
                statement.execute(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    public void saveUser(String name, String lastName, byte age) {
        try(Statement statement = util.getConnection().createStatement()) {
            String sql = "INSERT INTO " + TABLENAME + "(name, lastname, age) " +
                    "VALUES ('" + name + "', '" + lastName + "','" + age + "')";
            statement.execute(sql);
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public void removeUserById(long id) {
        try(Statement statement = util.getConnection().createStatement()) {
            String sql = "DELETE FROM " + TABLENAME + " WHERE id = '" + id + "'";
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try(Statement statement = util.getConnection().createStatement()) {
            String sql = "SELECT * FROM " + TABLENAME;
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                users.add(new User(resultSet.getString(2), resultSet.getString(3), (byte) resultSet.getInt(4)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return users;
    }


    public void cleanUsersTable() {
        try(Statement statement = util.getConnection().createStatement()) {
            String sql = "DELETE FROM " + TABLENAME;
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
