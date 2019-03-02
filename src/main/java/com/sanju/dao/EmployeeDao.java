package com.sanju.dao;

import com.sanju.model.EmployeeModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface EmployeeDao {
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "email", column = "email"),
            @Result(property = "age", column = "age"),
            @Result(property = "department", column = "department"),
            @Result(property = "name", column = "name"),
    })
    @Select("SELECT * FROM EMPLOYEE")
    List<EmployeeModel> findAll();

    /* Example of using default method in the interface*/
    default List<EmployeeModel> defaultFindAll() {
        return findAll();
    }

    @Insert("INSERT INTO EMPLOYEE (email,name,age,department) VALUES (#{email},#{name},#{age},#{department})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(EmployeeModel transaction);

    @Delete("DELETE FROM EMPLOYEE where id = #{id}")
    void deleteById(@Param(value = "id") int id);

    @Delete("UPDATE EMPLOYEE set name = #{name} WHERE id = #{id}")
    void updateNmeById(@Param(value = "id") int id, @Param(value = "name") String newName);
}
