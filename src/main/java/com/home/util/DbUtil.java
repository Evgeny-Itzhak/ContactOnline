package com.home.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Itzhak-Miryam on 14.03.2016.
 */
public class DbUtil {

    private static Connection connection = null;

    public static Connection getConnection() {

        if (connection != null) {
            return connection;
        }

        try {
            Properties properties = new Properties();
            InputStream inputStream = DbUtil.class.getClassLoader().getResourceAsStream("/db.properties");
            properties.load(inputStream);

            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");

            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
