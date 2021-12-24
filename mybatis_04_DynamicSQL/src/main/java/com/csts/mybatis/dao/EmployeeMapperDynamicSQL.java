package com.csts.mybatis.dao;

import com.csts.mybatis.bean.Employee;

import java.util.List;

public interface EmployeeMapperDynamicSQL {
    public List<Employee> getEmpsByConditionIf(Employee employee);
}
