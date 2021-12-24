package com.csts.mybatis.dao;

import com.csts.mybatis.bean.Department;

public interface DepartmentMapper {
    Department getDeptById(Integer id);
    Department getDeptByIdPlus(Integer id);
}
