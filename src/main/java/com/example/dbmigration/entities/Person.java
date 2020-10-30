package com.example.dbmigration.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "person")
public class Person {

    @Id
    private String id = UUID.randomUUID().toString();

    @Basic
    @Column(nullable = false)
    private String name;

    @Basic
    @Column(nullable = false)
    private String firstName;

    @Basic
    @Column
    private String surname;

    @Column(nullable = false)
    @Basic
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
