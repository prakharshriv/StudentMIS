package com.prakhar.ecom.models;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name = "user_table")
public class User {
    @Column(name = "id")
    @Id
    @NonNull
    String id;
    @Column(name = "name")
    @NonNull
    String name;
    @Column(name = "email")
    @NonNull
    String email;
    @Column(name = "password")
    @NonNull
    String password;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public User() {
        this.id=idGenerator();
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public User(@NonNull String name, @NonNull String email, @NonNull String password) {
        this.id = idGenerator();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String idGenerator(){
        String end="";
        Random r = new Random();
        for (int i = 0; i < 6; i++) {
                end+=(char)(r.nextInt(26) + 'A');
        }
        return ("ecom"+(100000+(int) (Math.random()*899999))+end);
    }



}
