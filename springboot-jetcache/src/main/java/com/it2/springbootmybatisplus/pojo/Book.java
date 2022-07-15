package com.it2.springbootmybatisplus.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Book implements Serializable {

    private int id;
    private String name;
    private String type;
    private String description;
}