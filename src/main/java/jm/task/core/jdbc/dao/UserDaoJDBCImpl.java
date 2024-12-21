package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createTable = """
                CREATE TABLE IF NOT EXISTS users\s
                (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(45),
                    lastname VARCHAR(45),
                    age TINYINT
                );""";

        try (Statement createTblStmnt = Util.getConnection().createStatement();) {
            createTblStmnt.execute(createTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String dropTable = "DROP TABLE IF EXISTS users";
        try (Statement dropTableStmnt = Util.getConnection().createStatement()) {
            dropTableStmnt.execute(dropTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser = "INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)";
        try (PreparedStatement saveUserStmnt = Util.getConnection().prepareStatement(saveUser)) {
            saveUserStmnt.setString(1, name);
            saveUserStmnt.setString(2, lastName);
            saveUserStmnt.setByte(3, age);
            saveUserStmnt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String removeUser = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement removeUserStmnt = Util.getConnection().prepareStatement(removeUser)) {
            removeUserStmnt.setLong(1, id);
            removeUserStmnt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> resultUsers = new ArrayList<>();
        String selectUser = "SELECT * FROM users";
        try (Statement getAllUsersStmnt = Util.getConnection().createStatement()) {
            ResultSet resultSet = getAllUsersStmnt.executeQuery(selectUser);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                resultUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultUsers;
    }

    public void cleanUsersTable() {
        String cleanUsersTable = "DELETE FROM users";
        try (Statement cleanTableStmnt = Util.getConnection().createStatement()) {
            cleanTableStmnt.execute(cleanUsersTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
