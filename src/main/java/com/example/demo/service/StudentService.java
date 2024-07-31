package com.example.demo.service;

import com.example.demo.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StudentService {

    private List<Student> students = new ArrayList<>();
    private AtomicLong counter = new AtomicLong();

    public List<Student> getAllStudents() {
        return students;
    }

    public Optional<Student> getStudentById(Long id) {
        return students.stream().filter(student -> student.getId().equals(id)).findFirst();
    }

    public Student addStudent(Student student) {
        student.setId(counter.incrementAndGet());
        students.add(student);
        return student;
    }

    public Optional<Student> updateStudent(Long id, Student studentDetails) {
        return getStudentById(id).map(student -> {
            student.setName(studentDetails.getName());
            return student;
        });
    }

    public boolean deleteStudent(Long id) {
        return students.removeIf(student -> student.getId().equals(id));
    }
}
