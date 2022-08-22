package com.xzz.service.impl;

import com.xzz.domain.Student;
import com.xzz.mapper.StudentMapper;
import com.xzz.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student findById(Long id) {
        return studentMapper.findById(id);
    }
}
