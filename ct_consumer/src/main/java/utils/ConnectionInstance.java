package utils;

        import org.apache.hadoop.conf.Configuration;
        import org.apache.hadoop.hbase.client.Connection;
        import org.apache.hadoop.hbase.client.ConnectionFactory;

        import java.io.IOException;


public class ConnectionInstance {
    private static Connection conn;
    public static synchronized Connection getConnection(Configuration configuration){
        try {
            if (conn == null || conn.isClosed()){
                conn = ConnectionFactory.createConnection(configuration);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
