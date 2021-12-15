package com.csts.mybatis.dao;


import com.csts.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface EmployeeMapper {
    // 单参数查询
    public Employee getEmployeeById(Integer id);

    // 多参数指定查询参数map的key
    public Employee getEmployeeByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    // 多参数map查询
    public Employee getEmployeeByMap(Map<String, Object> map);



    public void addEmp(Employee employee);

    public void updateEmp(Employee employee);

    public void deleteEmpById(Integer id);
}
