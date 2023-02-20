package brewery;

import java.sql.Connection;
import java.sql.SQLException;

public interface BasicDataSource {
    Connection getConnection();
    void closeConnection();
}
