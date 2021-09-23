package com.example.SpringBootDemoProject.controller;


import com.example.SpringBootDemoProject.entity.Employee;
import com.example.SpringBootDemoProject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/user")
public class EmployeeController {

   @Autowired
   private EmployeeService employeeService;


   //inserting or posting data into DB
   @PostMapping("/employee")
   public void add(@RequestBody Employee employee) throws Exception {
      employeeService.save(employee);
   }
//   fetching all records
   @GetMapping("/getAllEmployee")
   public List<Employee> list() {
      return employeeService.listAll();
   }

//   getting record by specific id
//   @RequestMapping(value = "/getEmployee/{empId}")

      @GetMapping("/getEmployee/{empId}")
      public ResponseEntity<Employee> getUser(@PathVariable Integer empId) {
      try {
         Employee employee = employeeService.getEmployee(empId);
         return new ResponseEntity<Employee>(employee, HttpStatus.OK);
      } catch (NoSuchElementException ne) {
         return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
      }
   }


//   update record in database
   @PutMapping("/employee/{empId}")
   public ResponseEntity<Object> update(@RequestBody Employee employee, @PathVariable Integer empId) throws Exception {
      try {
         Employee existEmployee = employeeService.getEmployee(empId);
         employeeService.save(employee);
         return new ResponseEntity<>(HttpStatus.OK);
      } catch (NoSuchElementException e) {
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      } catch (Exception e) {
         throw new Exception(e);
      }
   }

//   deleting record
   @DeleteMapping("/employee/{empId}")
   public void delete(@PathVariable Integer empId) {
      employeeService.delete(empId);
   }

//   fetching record date wise
   @GetMapping("/getDateWiseEmployee")
   public Object findEmployeeByDate (@Param("fromDate") String fromDate, @Param("toDate") String toDate)
   {
      try
      {
         return employeeService.findEmployeeByDate(fromDate,toDate);
//         public List<Employee> list()
//         return employeeService.listAll();
      }

      catch (Exception e) {
         e.printStackTrace();
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
   }

//    auto incrementing company id ST01


}













