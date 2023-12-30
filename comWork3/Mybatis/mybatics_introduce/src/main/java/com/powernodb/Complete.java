package com.powernodb;

import com.mysql.cj.Session;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import pojo.Car;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Complete {
    @Test
    public void asdsdfg(){
        SqlSession sqlSession=SqlSessionUtil.openSession();
        List<Object> cars=sqlSession.selectList("selectAll");
        cars.forEach(car-> System.out.println(cars));
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void asdasf(){
        SqlSession sqlSession=SqlSessionUtil.openSession();
        Object car=sqlSession.selectOne("selectId",1);
        System.out.println(car);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void asd(){
        SqlSession sqlSession=SqlSessionUtil.openSession();
        Car car=new Car(4L,"0008","泵车");
        sqlSession.update("updateId",car);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void de(){
        SqlSession sqlSession=SqlSessionUtil.openSession();
        sqlSession.delete("deleteId",29);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testPojo(){
        SqlSession sqlSession=SqlSessionUtil.openSession();
        Car car=new Car(30l,"1023","宝马");
        int count=sqlSession.insert("insertCarw",car);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void Insert(){
        SqlSession sqlSession=SqlSessionUtil.openSession();
        Map<String,Object>map=new HashMap<>();
        map.put("k1","1111");
        map.put("k2","比亚迪");
        int count=sqlSession.insert("insertCar",map);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void test(){
        SqlSession sqlSession=null;
        try {
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
            InputStream is= Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory=sqlSessionFactoryBuilder.build(is);
            sqlSession=sqlSessionFactory.openSession();
            sqlSession.insert("insertCar");
            int row=sqlSession.insert("insertCar");
            sqlSession.commit();
        }catch (Exception e){
            if (sqlSession!=null){
                sqlSession.rollback();
            }
            e.printStackTrace();
        }finally {
            if (sqlSession!=null){
                sqlSession.close();
            }
        }
    }

}
