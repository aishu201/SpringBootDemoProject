package com.example.SpringBootDemoProject.controller;


import com.example.SpringBootDemoProject.entity.Employee;
import com.example.SpringBootDemoProject.entity.ExcelHelper;
import com.example.SpringBootDemoProject.service.EmployeeService;
import com.sun.xml.internal.ws.server.sei.EndpointResponseMessageBuilder;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
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

   //   deleting record by specific id
   @DeleteMapping("/employee/{empId}")
   public void delete(@PathVariable Integer empId) {
      employeeService.delete(empId);
   }

   //   fetching record date wise
   @GetMapping("/getDateWiseEmployee")

   public Object findEmployeeByDate(@Param("fromDate") String fromDate, @Param("toDate") String toDate) {
      try {
         return employeeService.findEmployeeByDate(fromDate, toDate);
//         public List<Employee> list()
//         return employeeService.listAll();
      } catch (Exception e) {
         e.printStackTrace();
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
   }

   @DeleteMapping("/deleteMultipleId")
   public void deleteMultipleId(@Param("fromEmpId") Integer toEmpId, @Param("toEmpId") Integer fromEmpId) throws Exception {
      try {
         employeeService.deleteMultipleId(fromEmpId, toEmpId);
      } catch (Exception e) {
//         new ResponseEntity<>(HttpStatus.NOT_FOUND);
         throw new Exception(e);
      }
   }


//   uploading excel file
   @PostMapping("/uploadExcelFile")
   public ResponseEntity<Employee> uploadFile(@RequestParam("file") MultipartFile file)
   {
      String msg ="";
      if (ExcelHelper.hasExcelFormat(file));
      try{
         employeeService.saveFile(file);
         msg="File Successfully Uploaded...."+file.getOriginalFilename();
         return new ResponseEntity<>(HttpStatus.OK);
      }
      catch (Exception e)
      {
         msg="Could not upload the File : "+file.getOriginalFilename();
         return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
      }
   }
}




