package com.example.SpringBootDemoProject.entity;

import lombok.Data;

import javax.annotation.Generated;
import javax.persistence.*;


@Data
@Entity
@Table(name = "employeeRole")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String roleName;
}
