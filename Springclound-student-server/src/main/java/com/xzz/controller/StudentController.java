package com.xzz.controller;

import com.xzz.domain.Student;
import com.xzz.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @Autowired
    private IStudentService studentService;

    //订单服务来调用这个方法      http://localhost:1100/student/1
    @GetMapping( "/student/{id}" )
    public Student getById(@PathVariable("id")Long id){
        //根据id去数据库查询User
        return studentService.findById(id);
    }
}