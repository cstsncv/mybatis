package com.csts.mybatis.dao;


import com.csts.mybatis.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    // 单参数查询
    public Employee getEmployeeById(Integer id);

    // 多参数指定查询参数map的key
    public Employee getEmployeeByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    // 多参数map查询
    public Employee getEmployeeByMap(Map<String, Object> map);

    // 返回list,
    public List<Employee> getEmployeesByLastName(String lastName);

    // 返回map<String, Object>
    public Map<String, Object> getEmpByIdReturnMap(Integer id);

    //返回map<Integer, Employee>     @MapKey("id")指定返回map使用的key
    @MapKey("id")
    public Map<Integer, Employee> getEmpsByLastNameReturnMap(String lastName);

    public void addEmp(Employee employee);

    public void updateEmp(Employee employee);

    public void deleteEmpById(Integer id);
}
