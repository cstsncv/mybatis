package com.csts.mybatis.dao;

import com.csts.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapperDynamicSQL {

    public List<Employee> getEmpsByConditionIf(Employee employee);

    public List<Employee> getEmpsByConditionTrim(Employee employee);

    public void updateEmp(Employee employee);

    //查询给定结合中的id的员工
    public List<Employee> getEmpsByConditionForeach(@Param("ids") List<Integer> list);

    // 批量添加
    public void addEmps(@Param("emps") List<Employee> list);
}
