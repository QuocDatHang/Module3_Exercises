package com.example.demo.Service;

import com.example.demo.Enum.EGender;
import com.example.demo.Models.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private static final List<Student> students;
    private static int currentId;
    static {
        students = new ArrayList<>();
        students.add(new Student(++currentId, "Dat", LocalDate.parse("2000-09-12"), EGender.MALE));
        students.add(new Student(++currentId, "Long", LocalDate.parse("2000-03-08"), EGender.MALE));
        students.add(new Student(++currentId, "Vinh", LocalDate.parse("2000-06-22"), EGender.FEMALE));
    }

    public List<Student> getStudents() {
        return students;
    }
    public Student findStudentById(int id){
        for (Student student: students){
            if (student.getId() == id){
                return student;
            }
        }
        return null;
    }
    public void addStudent(String name, String dob, String gender) {
        students.add(new Student(++currentId, name, LocalDate.parse(dob), EGender.valueOf(gender)));
    }
    public void updateStudent(int id, String name, String dob, String gender) {
        Student editStudent = findStudentById(id);
        editStudent.setName(name);
        editStudent.setDob(LocalDate.parse(dob));
        editStudent.setGender(EGender.valueOf(gender));
    }
}
