package com.example.SpringBootDemoProject.service;


import com.example.SpringBootDemoProject.entity.Employee;
import com.example.SpringBootDemoProject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class EmployeeService
{
//    @Autowired
//    private Validator validator;

    @Autowired
    private EmployeeRepository emprepo;    //created reference obj of EmployeeRepository interface

    public void save(Employee employee) throws Exception {
        System.out.println("data members are:========="+employee);
        Date date=new Date();
        employee.setCreationDate(date);
        Employee emp=emprepo.getCompanyId(employee.getCompanyId());
        String companyId = null;
        Employee employee1 = emprepo.findByEmail(employee.getEmail());
        System.out.println("48487445841:::::"+employee1);
        if(employee1==null) {
            try {
                System.out.println("In Email's if ....");
                if (emp == null) {
                    System.out.println("in if ");
                    companyId = "ST1";
                } else {
                    System.out.println("in else ");
                    companyId = emp.getCompanyId();
                    System.out.println("in else companyId:: " + companyId);
                    companyId = "ST" + (Integer.parseInt(companyId.substring(2, companyId.length())) + 1);
                    System.out.println("in else companyId======= " + companyId);
                }
                employee.setCompanyId(companyId);
                emprepo.save(employee);
            }catch (Exception e){
                throw new RuntimeException(e);
            }

        } else {
            System.out.println("email duplicate entry");
            throw new Exception("email duplicate entry");

        }

    }

    public List<Employee> listAll()
    {
        return emprepo.findAll();
    }

//    get record by id
    public Employee getEmployee(Integer empId) {

        return emprepo.findById(empId).get();
    }

//     delete record
    public void delete(Integer empId) {
      emprepo.deleteById(empId);
    }


//    fetching record date wise
    public List<Employee> findEmployeeByDate(String fromDate , String toDate)
    {
        List<Employee> results= emprepo.findEmployeeByDate(fromDate,toDate);
        return results;
    }
}
