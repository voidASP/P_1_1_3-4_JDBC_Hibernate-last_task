package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.Main;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE user (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), " +
                "lastname VARCHAR(20), age INT(3))";
        try (Session session = Main.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE user";
        try (Session session = Main.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        }  catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO user (name, lastname, age) VALUES(?, ?, ?)";
        try (Session session = Main.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sql).setString(1, name)
                    .setString(2, lastName).setByte(3, age).executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM USER WHERE id=?";
        try (Session session = Main.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sql).setLong(1, id).executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT a FROM User a";
        try (Session session = Main.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            userList = session.createQuery(sql, User.class).getResultList();
            transaction.commit();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE user";
        try (Session session = Main.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        }
    }
}
