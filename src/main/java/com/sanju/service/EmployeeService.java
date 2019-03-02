package com.sanju.service;

import com.sanju.model.EmployeeModel;

import java.util.List;

public interface EmployeeService {

   void insert(EmployeeModel model);
   List<EmployeeModel> findList();
}

