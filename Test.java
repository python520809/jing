package Work3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner scanner=new Scanner(System.in);
        Service shop = new Service();
        String driver="com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/c_shop?serverTimezone=Asia/Shanghai";
        Class.forName(driver);
        Connection connection= DriverManager.getConnection(url,"root","123456");
        /*try {
            connection.setAutoCommit(false);
            shop.shopList(connection);
            shop.orderList(connection);
            //shop.updateShop(connection);
            //shop.addShop(connection);
            //shop.deleShop(connection);
            //shop.rankShop(connection);
            //shop.addOrder(connection);
            shop.updateOrder(connection);
            connection.commit();
        }catch (Exception e){
            connection.rollback();
        }*/
        System.out.println("---------------订单管理系统-----------------");
        connection.setAutoCommit(false);
        System.out.println("1.查看商品");
        System.out.println("2.增加商品");
        System.out.println("3.删除商品");
        System.out.println("4.修改商品");
        System.out.println("5.商品排序");
        System.out.println("6.查看订单");
        System.out.println("7.增加订单");
        System.out.println("8.删除订单");
        System.out.println("9.修改订单");
        boolean a=true;
        while(a){
            System.out.println("请输入选项：");
            int choice=scanner.nextInt();
            switch (choice){
                case 1:
                    shop.shopList(connection);
                    break;
                case 2:
                    shop.addShop(connection);
                    break;
                case 3:
                    shop.deleShop(connection);
                    break;
                case 4:
                    shop.updateShop(connection);
                    break;
                case 5:
                    shop.rankShop(connection);
                    break;
                case 6:
                    shop.orderList(connection);
                    break;
                case 7:
                    shop.addOrder(connection);
                    break;
                case 8:
                    shop.deleOrder(connection);
                    break;
                case 9:
                    shop.updateOrder(connection);
                    break;
                default:
                    System.out.println("选择错误，请重新选择");
            }
            System.out.println("是否继续进行操作？输入1代表YES");
            int b=scanner.nextInt();
            if (b==1){
                System.out.println("继续进行操作");
            }else {
                a=false;
            }
        }
        connection.commit();
    }
}
