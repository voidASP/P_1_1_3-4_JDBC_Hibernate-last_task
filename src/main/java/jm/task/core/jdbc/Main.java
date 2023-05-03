package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl daoHibernate = new UserDaoHibernateImpl();
        daoHibernate.createUsersTable();
        daoHibernate.saveUser("Maxim", "Mantulin", (byte) 26);
        daoHibernate.saveUser("Alexey", "Soshnikov", (byte) 27);
        daoHibernate.saveUser("Maxim", "Zhelyabovsky", (byte) 28);
        daoHibernate.saveUser("Alexandr", "Podshyvailov", (byte) 26);
        System.out.println(daoHibernate.getAllUsers());
        daoHibernate.cleanUsersTable();
        daoHibernate.dropUsersTable();
    }
}
