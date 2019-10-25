package com.example.kafakreceive.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/*
 @Description
 *@author kang.li
 *@date 2019/10/24 17:13   
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Programmer {
    private String name;

    private int age;

    private float salary;

    private Date birthday;
}
