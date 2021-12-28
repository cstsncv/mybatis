package com.csts.mybatis.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String deptName;
    private List<Employee> employees;


    public Department(Integer id) {
        this.id = id;
    }
}
