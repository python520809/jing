package Work3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class shopTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner scanner=new Scanner(System.in);
        shopService shopService = new shopService();
        String driver="com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/c_shop?serverTimezone=Asia/Shanghai";
        Class.forName(driver);
        Connection connection= DriverManager.getConnection(url,"root","123456");
        try {
            System.out.println("------商品列表------");
            connection.setAutoCommit(false);
            shopService.shopList(connection);
            shopService.deleGift(connection);
            connection.commit();

        }catch (Exception e){
            connection.rollback();
            throw e;
        }

    }
}
