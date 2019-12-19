import util.Connector;

public class TestInitializator {
    public static void init() {
        try {
            Connector.init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/new_schema?useSSL=false", "root", "");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
