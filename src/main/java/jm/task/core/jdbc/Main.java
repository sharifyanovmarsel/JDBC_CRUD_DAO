package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;


public class Main {
    public static void main(String[] args) {
        //Мои тесты через Dao:
        UserDao userDao = new UserDaoHibernateImpl();

        userDao.createUsersTable();

        userDao.saveUser("Name2", "LastName2", (byte) 25);
        System.out.println("User с именем — Name1 добавлен в базу данных");
        userDao.saveUser("Name1", "LastName1", (byte) 20);
        System.out.println("User с именем — Name2 добавлен в базу данных");
        userDao.saveUser("Name3", "LastName3", (byte) 31);
        System.out.println("User с именем — Name3 добавлен в базу данных");
        userDao.saveUser("Name4", "LastName4", (byte) 38);
        System.out.println("User с именем — Name4 добавлен в базу данных");

        userDao.removeUserById(2);
        System.out.println(userDao.getAllUsers());
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
        //Реализация алгоритма работы программы по условию:
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Name2", "LastName2", (byte) 25);
        userService.saveUser("Name1", "LastName1", (byte) 20);
        userService.saveUser("Name3", "LastName3", (byte) 31);
        userService.saveUser("Name4", "LastName4", (byte) 38);

        userService.removeUserById(1);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.closeSessionFactory();
    }
}
