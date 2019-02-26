package com.cxs.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/23 21:36
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class People {

    private String name;
    private Integer age;
    private String address;

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
