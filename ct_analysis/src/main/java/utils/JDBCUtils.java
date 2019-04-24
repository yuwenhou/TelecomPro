package utils;

import java.sql.*;

/**
 * @description:
 * @time:2019/4/24
 */
/*
实例化JDBC连接器
 */
public class JDBCUtils {

    /**
     * MYSQL_URL记得使用自己的库
     *CREATE DATABASE db_telecom;
     */
    private static final String MYSQL_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String MYSQL_URL = "jdbc:mysql://hou-01:3306/db_telecom?useUnicode=true&characterEncoding=UTF-8";
    private static final String MYSQL_USERNAME = "root";
    private static final String MYSQL_PASSWORD = "123456";

    /**
     * 实例化JDBC连接器
     * @return
     */
    public static Connection getConnection(){
        try {
            Class.forName(MYSQL_DRIVER_CLASS);
            return DriverManager.getConnection(MYSQL_URL,MYSQL_USERNAME,MYSQL_PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet){
        try {
            if (resultSet!= null || !resultSet.isClosed()){
                resultSet.close();
            }
            if (statement!= null || !statement.isClosed()){
                statement.close();
            }
            if (connection!= null || !connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
