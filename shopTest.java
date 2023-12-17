package Work3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class shopTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String driver="com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/jdbc?serverTimezone=Asia/Shanghai";
        Class.forName(driver);
        Connection connection= DriverManager.getConnection(url,"root","taobao138139");

    }
}
