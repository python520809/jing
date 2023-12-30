package com.powernodb;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * Mybatis工具类
 * 防止new对象，用private
 */
public class SqlSessionUtil {
    private SqlSessionUtil(){}
    private static SqlSessionFactory sqlSessionFactory;
    static {
        try{
            sqlSessionFactory =new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    /*

     */
    public static SqlSession openSession(){
        return sqlSessionFactory.openSession();
    }
}
