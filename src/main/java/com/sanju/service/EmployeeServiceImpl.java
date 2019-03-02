package com.sanju.service;

import com.sanju.dao.EmployeeDao;
import com.sanju.model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public void insert(EmployeeModel model) {
        employeeDao.insert(model);
    }

    @Override
    public List<EmployeeModel> findList() {
        EmployeeModel employeeModel = new EmployeeModel();
        List<EmployeeModel> employeeModels = new ArrayList<>();
        employeeModels.add(employeeModel);
        //return employeeModels;
        return employeeDao.findAll();
    }
}
