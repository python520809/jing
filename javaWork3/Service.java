package Work3;

import java.sql.*;
import java.util.Scanner;

public class Service {
    Scanner scanner=new Scanner(System.in);
    public void shopList(Connection connection) {
        try {
            System.out.println("------商品列表------");
            String sql="select giftid,giftName,price from y_shop ";
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while(resultSet.next()){
                int giftid = resultSet.getInt("giftid");
                String giftName = resultSet.getString("giftName");
                String price = resultSet.getString("price");
                System.out.println("ID："+giftid+"--"+"商品名称："+giftName+"--"+"价格："+price);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void orderList(Connection connection){
        try {
            System.out.println("------订单列表------");
            String sql="select id,buyName,buyprice ,time,number from m_shpp ";
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String buyName = resultSet.getString("buyName");
                String buyprice = resultSet.getString("buyprice");
                java.util.Date orferTime = resultSet.getTimestamp("time");
                int number=resultSet.getInt("number");
                System.out.println("ID："+id+"--订单名称："+buyName+"--价格："+buyprice+"--下单时间："+orferTime+"--数量："+number);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void addShop(Connection connection)throws SQLException{
        try {
            System.out.println("请输入所要添加商品的名称：");
            String shopName=scanner.next();
            if(!judge(connection,shopName)) {
                String sql1 = "insert into y_shop(giftName,price) values (?,?)";
                System.out.println("请输入所要添加商品的价格：");
                double price = scanner.nextDouble();
                PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
                preparedStatement1.setObject(1, shopName);
                preparedStatement1.setObject(2, price);
                int row = preparedStatement1.executeUpdate();
                if (row > 0) {
                    System.out.println("添加成功");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void updateShop(Connection connection){
        try {
            System.out.println("请输入想要更改商品的名称：");
            String shopName=scanner.next();
            if(judge(connection,shopName)){
                System.out.println("请输入该商品更改后的价格：");
                double price=scanner.nextDouble();
                String sql="update y_shop set price =? where y_shop.giftName =?";
                PreparedStatement preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setObject(1,price);
                preparedStatement.setObject(2,shopName);
                int row = preparedStatement.executeUpdate();
                if (row > 0) {
                    System.out.println("修改成功");
                } else {
                    System.out.println("修改失败");
                }
            }else {
                System.out.println("不存在该商品");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void deleShop(Connection connection){
        try {
            System.out.println("请输入想要删除商品的id");
            int id1=scanner.nextInt();
            String sql1="delete from y_shop where giftid=?";
            PreparedStatement preparedStatement=connection.prepareStatement(sql1);
            preparedStatement.setObject(1,id1);
            int row=preparedStatement.executeUpdate();
            if(row>0){
                System.out.println("删除成功");
            }else {
                System.out.println("删除失败");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void rankShop(Connection connection){
        try {
            System.out.println("请选择升序还是降序?1.升序 2.降序");
            String sql="select giftid,giftName,price from y_shop order by price ";
            String sql1 = "";
            int choice=scanner.nextInt();
            switch (choice){
                case 1:sql1="asc";
                    break;
                case 2:sql1="desc";
                    break;
                default:
                    System.out.println("输入错误，默认升序");
                    sql1="asc";
            }
            sql+=sql1;
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while(resultSet.next()){
                int giftid = resultSet.getInt("giftid");
                String giftName = resultSet.getString("giftName");
                double price = resultSet.getDouble("price");
                System.out.println(giftid+"---"+giftName+"---"+price);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void addOrder(Connection connection){
        try {
            System.out.println("从商品列表中选择要增加到订单的商品编号：");
            int id=scanner.nextInt();
            String sql="insert into m_shpp (buyName,buyPrice) select giftName,price from y_shop where y_shop.giftid=?                         ";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setObject(1,id);
            int row=preparedStatement.executeUpdate();
            if(row>0){
                System.out.println("请选择数量，并自动生成下单时间：");
                int number=scanner.nextInt();
                String sql1="update m_shpp set time=? ,number=? where  1 ORDER BY id DESC LIMIT 1";
                PreparedStatement preparedStatement1=connection.prepareStatement(sql1);
                preparedStatement1.setDate(1,new java.sql.Date(new java.util.Date().getTime()));
                preparedStatement1.setObject(2,number);
                int rows= preparedStatement1.executeUpdate();
                System.out.println("下单成功");
            }else{
                System.out.println("输入错误，下单失败");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateOrder(Connection connection){
        try {
            System.out.println("请输入想要更改订单的名称：");
            String orderName=scanner.next();
            if (judgeOrder(connection,orderName)){
                System.out.println("请选择更改内容：1.价格 2.数量 3.价格和数量");
                int choice=scanner.nextInt();
                switch (choice){
                    case 1:
                        updatePrice(connection,orderName);
                        break;
                    case 2:
                        updateNumber(connection,orderName);
                        break;
                    case 3:
                        updateNumber(connection,orderName);
                        updatePrice(connection,orderName);
                        break;
                    default:
                        System.out.println("输入错误，退出系统");
                }
            }else{
                System.out.println("订单不存在");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleOrder(Connection connection){
        try {
            System.out.println("请输入想要删除订单的id");
            int id1=scanner.nextInt();
            String sql1="delete from m_shpp where giftid=?";
            PreparedStatement preparedStatement=connection.prepareStatement(sql1);
            preparedStatement.setObject(1,id1);
            int row=preparedStatement.executeUpdate();
            if(row>0){
                System.out.println("删除成功");
            }else {
                System.out.println("删除失败");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public boolean judge(Connection connection,String shopName)throws SQLException{
        String sql="select giftName from y_shop";
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sql);
        while(resultSet.next()){
            String name1=resultSet.getString("giftName");
            if(shopName.equals(name1)){
                System.out.println("商品存在");
                return true;
            }
        }
        return false;
    }
    public boolean judgeOrder(Connection connection,String orderName)throws SQLException{
        String sql="select buyName from m_shpp";
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sql);
        while(resultSet.next()){
            String name1=resultSet.getString("buyName");
            if(orderName.equals(name1)){
                System.out.println("商品存在");
                return true;
            }
        }
        return false;
    }
    public void updatePrice(Connection connection,String name){
        try {
            System.out.println("请输入修改后的价格：");
            double price=scanner.nextDouble();
            String sql1="update m_shpp set buyPrice =? where m_shpp.buyName =?";
            PreparedStatement preparedStatement1=connection.prepareStatement(sql1);
            preparedStatement1.setObject(1,price);
            preparedStatement1.setObject(2,name);
            int row=preparedStatement1.executeUpdate();
            if(row>0){
                System.out.println("修改成功");
            }else {
                System.out.println("失败");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void updateNumber(Connection connection,String name){
        try {
            System.out.println("请输入修改后的数量：");
            int num=scanner.nextInt();
            String sql1="update m_shpp set number =? where m_shpp.buyName =?";
            PreparedStatement preparedStatement1=connection.prepareStatement(sql1);
            preparedStatement1.setObject(1,num);
            preparedStatement1.setObject(2,name);
            int row=preparedStatement1.executeUpdate();
            if (row>0){
                System.out.println("修改成功");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
