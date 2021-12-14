package com.csts.mybatis.bean;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("empl")
public class Employee {
    private Integer id;
    private String lastName;
    private String email;
    private String gender;

}
