package com.csts.mybatis.bean;

import com.csts.mybatis.dao.EmployeeMapper;
import com.csts.mybatis.dao.EmployeeMapperAnnotation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;


public class EmployeeTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
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


    // 测试基于注解mapper
    @Test
    public void test02() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 获取接口实现类对象,  会为接口自动创建代理对象mapper, 代理对象去执行sql
            EmployeeMapperAnnotation mapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);

            Employee employeeById = mapper.getEmployeeById(1);

            System.out.println(employeeById);
        } finally {
            sqlSession.close();
        }

    }
}