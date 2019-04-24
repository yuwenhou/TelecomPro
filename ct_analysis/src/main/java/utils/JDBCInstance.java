package utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @description:
 * @time:2019/4/24
 */
public class JDBCInstance {
    private  static Connection connection = null;

    public  JDBCInstance(){
    }

    public static Connection getInstance(){
        try {
            if (connection==null || connection.isClosed() || !connection.isValid(3)){
                connection = JDBCUtils.getConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }

}
