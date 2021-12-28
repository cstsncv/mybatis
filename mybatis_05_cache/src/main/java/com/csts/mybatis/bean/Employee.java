package com.csts.mybatis.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Data
@Alias("empl")
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String lastName;
    private String email;
    private Integer gender;
    private Department dept;
}
