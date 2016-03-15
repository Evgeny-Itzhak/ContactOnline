package com.home.dao;

import com.home.model.User;
import com.home.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Itzhak-Miryam on 14.03.2016.
 */
public class UserDao {

    private Connection connection;

    public UserDao() {
        connection = DbUtil.getConnection();
    }

    public void addUser(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (firstname, lastname, dob, email) VALUES (?, ? ,? ,?)");

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, String.valueOf(new java.sql.Date(user.getDob().getTime())));
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userId) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE userid = ?");
            preparedStatement.setString(1, String.valueOf(userId));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET firstname=?, " +
                    "lastname=?, dob=?, email=?" + "WHERE userid = ?");
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, String.valueOf(new java.sql.Date(user.getDob().getTime())));
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, String.valueOf(user.getUserId()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("userid"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setDob(resultSet.getDate("dob"));
                user.setEmail(resultSet.getString("email"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserById(int userId) {

        User user = new User();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE userid=?");
            preparedStatement.setString(1, String.valueOf(userId));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user.setUserId(resultSet.getInt("userid"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setDob(resultSet.getDate("dob"));
                user.setEmail(resultSet.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;

    }
}
