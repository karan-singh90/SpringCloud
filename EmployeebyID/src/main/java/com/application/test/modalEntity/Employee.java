package com.application.test.modalEntity;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Getter@Setter
@ToString@AllArgsConstructor
@Table
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    @PrimaryKey
    private Integer id;
    @ApiModelProperty(notes = "Employee first name")
    private String first_name;
    @ApiModelProperty(notes = "Employee last name")
    private String last_name;
    @ApiModelProperty(notes = "Employee age")
    private int age;
    @ApiModelProperty(notes = "Employee email")
    private String email;
}
