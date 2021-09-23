package com.example.SpringBootDemoProject.repository;

import com.example.SpringBootDemoProject.entity.Employee;
import com.sun.org.apache.bcel.internal.generic.ARETURN;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>
{

//    fetching data to check wheather database is empty or not
    @Query(value = "SELECT * FROM employee ORDER BY emp_id DESC LIMIT 1",nativeQuery = true)
     Employee getCompanyId(String companyID);

//    fetching employee records datewise
    @Query(value = "SELECT * FROM employee WHERE DATE(creation_date)>= DATE(:fromDate) AND DATE(creation_date) <= DATE(:toDate)", nativeQuery = true)
    List<Employee> findEmployeeByDate(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

//  validating email is exist or not
    @Query(value = "SELECT * FROM employee WHERE LOWER(email) = LOWER(:email)",nativeQuery = true)
    public Employee findByEmail(@Param("email") String email );




}
