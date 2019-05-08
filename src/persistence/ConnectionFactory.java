package persistence;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import java.sql.SQLException;

public class ConnectionFactory {
    private static MysqlConnectionPoolDataSource conn;

    private ConnectionFactory() {
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        if (conn == null) {
            Class.forName("com.mysql.jdbc.Driver");
            conn = new MysqlConnectionPoolDataSource();
            conn.setServerName("127.0.0.1");
            conn.setPort(3306);
            conn.setDatabaseName("snake");
            conn.setUser("root");
            conn.setPassword("root");
        }
        return (Connection) conn.getPooledConnection().getConnection();
    }
}
