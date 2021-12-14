package com.csts.mybatis.bean;

import com.csts.mybatis.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class EmployeeTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "com/csts/mybatis/conf/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    // 1. 原版本
    @Test
    public void EmployeeTest1() throws IOException {
        // 1. 获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2. 获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3.
        try {
            Employee employee = sqlSession.selectOne("com.csts.mybatis.conf.EmployeeMapper.selectEmp", 1);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }


    // 2. 新版本, 接口式
    @Test
    public void test01() throws IOException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            // 获取接口实现类对象,  会为接口自动创建代理对象mapper, 代理对象去执行sql
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            Employee employeeById = mapper.getEmployeeById(1);

            System.out.println(employeeById);
        } finally {
            sqlSession.close();
        }
    }
}