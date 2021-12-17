package com.csts.mybatis.bean;

import com.csts.mybatis.bean.Employee;
import com.csts.mybatis.dao.EmployeeMapper;
import com.csts.mybatis.dao.EmployeeMapperAnnotation;
import com.csts.mybatis.dao.EmployeeMapperPlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EmployeeTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }



    // 2. 新版本, 接口式 xml
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

    // 增删改 需要手动提交数据
    @Test
    public void test03() throws IOException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            // 获取接口实现类对象,  会为接口自动创建代理对象mapper, 代理对象去执行sql
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            Employee employee = new Employee(null,"Jerry", "jerry@123.com", "1");
            Employee employee1 = new Employee(1,"Jerry", "jerry@123.com", "1");
            // 增加
            mapper.addEmp(employee);

//            mapper.updateEmp(employee1);
            System.out.println(employee.getId());

            // 删除
//            mapper.deleteEmpById(1);

            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test04() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            // 多参数
//            Employee employee = mapper.getEmployeeByIdAndLastName(9, "jerry");

            // 多参数map传值
//            HashMap<String, Object> stringObjectHashMap = new HashMap<>();
//            stringObjectHashMap.put("id", 8);
//            stringObjectHashMap.put("lastName", "Tom");
//            Employee employee1 = mapper.getEmployeeByMap(stringObjectHashMap);
////            sqlSession.commit();
//
//            System.out.println(employee+"\n"+employee1);

//            List<Employee> employees = mapper.getEmployeesByLastName("je%");
//            for (Employee employee: employees) {
//                System.out.println(employee);
//            }

//            Map<String, Object> returnMap = mapper.getEmpByIdReturnMap(9);
//            System.out.println(returnMap);

            Map<Integer, Employee> empsByLastNameReturnMap = mapper.getEmpsByLastNameReturnMap("%e%");
            System.out.println(empsByLastNameReturnMap);

        } finally {
            sqlSession.close();
        }
    }


    @Test
    public void test() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try {
            EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);
            Employee emp = mapper.getEmpById(9);
            System.out.println(emp);
        } finally {
            sqlSession.close();
        }

    }
}