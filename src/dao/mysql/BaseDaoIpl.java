package dao.mysql;

import java.sql.Connection;

public abstract class BaseDaoIpl {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
