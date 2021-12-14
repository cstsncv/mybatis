package com.csts.mybatis.dao;


import com.csts.mybatis.bean.Employee;

public interface EmployeeMapper {
    public Employee getEmployeeById(Integer id);
}
