package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Name2", "LastName2", (byte) 25);
        System.out.println("User с именем — Name1 добавлен в базу данных");
        userDao.saveUser("Name1", "LastName1", (byte) 20);
        System.out.println("User с именем — Name2 добавлен в базу данных");
        userDao.saveUser("Name3", "LastName3", (byte) 31);
        System.out.println("User с именем — Name3 добавлен в базу данных");
        userDao.saveUser("Name4", "LastName4", (byte) 38);
        System.out.println("User с именем — Name4 добавлен в базу данных");

        userDao.removeUserById(1);
        System.out.println(userDao.getAllUsers());
        userDao.cleanUsersTable();
        userDao.dropUsersTable();

        // Не до конца понял по заданию следует ли алгоритм работы приложения делать через Dao
        // или нужно через Srvice делать, поэтому напишу тут такой же код через сервисы ¯\_(ツ)_/¯

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

        Util.closeConnection();
    }
}
