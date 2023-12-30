package com.powernodb;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MybatisTest {
    public static void main(String[] args) throws Exception{
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //InputStream isa=new FileInputStream("");
        //InputStream isb=ClassLoader.getSystemClassLoader().getResourceAsStream("");
        InputStream is=Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory=sqlSessionFactoryBuilder.build(is);
        SqlSession sqlSession=sqlSessionFactory.openSession();
        sqlSession.insert("insertCar");
        int count=sqlSession.insert("insertCar");
        sqlSession.commit();
    }
}
