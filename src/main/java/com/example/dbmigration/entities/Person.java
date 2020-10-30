package com.example.dbmigration.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "person")
public class Person {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(nullable = false)
    @Basic
    private String name;

    @Column(nullable = false)
    @Basic
    private int age;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
