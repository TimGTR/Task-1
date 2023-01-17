package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    Util util = new Util();
    SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx1 = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS users"
                    + "(id SERIAL PRIMARY KEY, name VARCHAR(40), " +
                    "lastName VARCHAR(40), age NUMERIC)";
            session.createSQLQuery(sql).executeUpdate();
            tx1.commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx1 = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS USERS";
            session.createSQLQuery(sql).executeUpdate();
            tx1.commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = sessionFactory.openSession();) {
            Transaction tx = session.beginTransaction();
            session.persist(
                    new User(name, lastName, age));
            tx.commit();
        }


    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        User user = session.get(User.class, id);
        System.out.println(user);
        session.delete(user);
        tx.commit();
        session.close();

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;

        try(Session session = sessionFactory.openSession();) {
            Transaction tx = session.beginTransaction();
            users = session.createQuery("from User").getResultList();
            tx.commit();

        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            List<User> users = session.createQuery("from User", User.class).list();
            for(User u : users) {
                session.delete(u);
            }
            tx.commit();
        }
    }
}
