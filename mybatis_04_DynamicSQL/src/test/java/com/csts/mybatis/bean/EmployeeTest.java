package com.csts.mybatis.bean;

import com.csts.mybatis.dao.EmployeeMapperDynamicSQL;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class EmployeeTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    @Test
    public void test() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee = new Employee(1, "Flora", "Flora@163.com", null, null);
//            List<Employee> empsByConditionIf = mapper.getEmpsByConditionTrim(employee);
//            System.out.println(empsByConditionIf);

            // test set
//            mapper.updateEmp(employee);
//            sqlSession.commit();

            // test foreach
//            List<Employee> empsByConditionForeach = mapper.getEmpsByConditionForeach(Arrays.asList(1, 2, 3, 4));
//            System.out.println(empsByConditionForeach);

            //test foreach add
            List<Employee> emps = new ArrayList<>();
            emps.add(new Employee(null, "add5", "add1@123.com", 0, new Department(1)));
            emps.add(new Employee(null, "add6", "add1@123.com", 0, new Department(1)));
            mapper.addEmps(emps);
            sqlSession.commit();

        } finally {
            sqlSession.close();
        }
    }
}