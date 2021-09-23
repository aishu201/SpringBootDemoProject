package com.example.SpringBootDemoProject.entity;


import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@Entity
@Table(name = "employee")
public class Employee {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer empId;


        private String firstName;
        private String lastName;

        private String email;

        @NotNull
        @Pattern(regexp="^((\\d{3}?\\d{3}?\\d{4}$))")
        @Column(name = "mobile", unique = true,length = 10)
        @Digits(fraction = 0,integer = 10)
        private String mobile;

        private String address;
        private String department;
        private String companyId;


        @OneToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "role_id",nullable = true)
        private Role role1;

        @Temporal(TemporalType.TIMESTAMP)
        private Date creationDate;
}




