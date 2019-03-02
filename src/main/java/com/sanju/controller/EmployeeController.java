package com.sanju.controller;


import com.sanju.model.EmployeeModel;
import com.sanju.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public @ResponseBody List<EmployeeModel> getEmployees() {
       return employeeService.findList();
    }
}
