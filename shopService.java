package Work3;

import java.sql.*;
import java.util.Scanner;

public class shopService {
    Scanner scanner=new Scanner(System.in);
    public void shopList(Connection connection) throws SQLException {
        String sql="select id,giftName,price from y_shop ";
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sql);
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String giftName = resultSet.getString("giftName");
            String price = resultSet.getString("price");
            System.out.println(id+"--"+giftName+"--"+price);
        }
    }
    public void addGift(Connection connection){
        String sql="";
    }
    public void deleGift(Connection connection)throws SQLException{
        String sql="delete from y_shop where id =?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        System.out.println("请选择你要下单商品的编号：");
        int buyId=scanner.nextInt();
        preparedStatement.setObject(1,buyId);
        int row= preparedStatement.executeUpdate();
        if (row>0){
            System.out.println("下单成功");
            /*LocalDate localDate=LocalDate.now();
            String sql1="insert into m_shpp (buyName,buyPrice) select giftName,price from y_shop where y_shop.id= ?";
            PreparedStatement preparedStatement1=connection.prepareStatement(sql1);
            preparedStatement1.setObject(1,buyId);
            String sql2="insert into  m_shpp (time) values (?)";
            PreparedStatement preparedStatement2=connection.prepareStatement(sql2);
            preparedStatement2.setObject(1,localDate);
            int m= preparedStatement1.executeUpdate();
            int n= preparedStatement2.executeUpdate();*/
        }else {
            System.out.println("下单失败");
        }
    }
}
