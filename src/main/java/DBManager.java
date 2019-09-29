import java.sql.Connection;

public interface DBManager {

    void connect();
    void disconnect();
    Connection getConnection();

}
