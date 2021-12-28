package com.csts.mybatis.bean;

import com.csts.mybatis.dao.EmployeeMapper;
import com.csts.mybatis.dao.EmployeeMapperDynamicSQL;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class EmployeeTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    /**
     *  一级缓存: 默认开启, 每个session独享. 会话提交或关闭后会放到二级缓存内
     *  二级缓存(全局缓存): 需显式开启. namespaces级别
     *     使用: 1. 开启二级缓存        <setting name="cacheEnabled" value="true"/>
     *          2. mapper.xml 中配置使用二级缓存: <cache></cache>
     *          3. POJO实现序列化接口
     *
     * @throws IOException
     */

    @Test
    public void testFirstLevelCache() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee employeeById = mapper.getEmployeeById(1);
            System.out.println(employeeById);

            Employee employeeById1 = mapper.getEmployeeById(1);
            System.out.println(employeeById1);
            System.out.println(employeeById==employeeById1);
        }finally {
            openSession.close();
        }
    }



    @Test
    public void testSecondLevelCache() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession1 = sqlSessionFactory.openSession();
        SqlSession openSession2 = sqlSessionFactory.openSession();

        try {
            EmployeeMapper openSession1Mapper = openSession1.getMapper(EmployeeMapper.class);
            EmployeeMapper openSession2Mapper = openSession2.getMapper(EmployeeMapper.class);

            Employee employeeById1 = openSession1Mapper.getEmployeeById(1);
            System.out.println(employeeById1);
            openSession1.close();

            Employee employeeById2 = openSession2Mapper.getEmployeeById(1);
            System.out.println(employeeById2);
        } finally {
            openSession1.close();
            openSession2.close();
        }
    }
}