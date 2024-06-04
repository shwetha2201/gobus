package com.gobus.gobusservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "gobus_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "age")
    private Integer age;
    @Column(name = "user_type")
    private UserType userType;
    @Column(name = "email_id")
    private String emailId;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "gobus_password")
    private String password;

    public enum UserType {
        CUSTOMER, ADMIN, BUS_OWNER;
    }
}


