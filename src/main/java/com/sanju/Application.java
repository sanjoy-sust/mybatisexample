package com.sanju;

import com.sanju.config.AppConfig;
import com.sanju.service.EmployeeService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
public class Application {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        EmployeeService service = configApplicationContext.getBean(EmployeeService.class);

        service.findList().forEach(employee->{

            System.out.println(employee.getName());
        });
    }
}
