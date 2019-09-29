import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlServerManager implements DBManager {

    private Connection connection;
    private String url;
    private String user;
    private String password;

    public SqlServerManager(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void connect() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection()
    {
        return connection;
    }

}
