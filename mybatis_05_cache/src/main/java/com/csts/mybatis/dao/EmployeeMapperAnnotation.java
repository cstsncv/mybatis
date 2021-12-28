package com.csts.mybatis.dao;

import com.csts.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Select;

public interface EmployeeMapperAnnotation {
    @Select("select * from tb1_employee where id = #{id}")
    public Employee getEmployeeById(Integer id);

}
