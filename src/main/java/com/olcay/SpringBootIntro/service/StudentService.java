package com.olcay.SpringBootIntro.service;

import com.olcay.SpringBootIntro.domain.Student;
import com.olcay.SpringBootIntro.exception.ConflictException;
import com.olcay.SpringBootIntro.exception.StudentNotFoundException;
import com.olcay.SpringBootIntro.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

    public List<Student> getAllStudent() {
        List<Student> studentList = repository.findAll();
        return studentList;
    }

    public void createStudent(Student student) {

        if (repository.existsByEmail(student.getEmail())) {
            throw new ConflictException("Email already exist");
        }
        repository.save(student);//-ıt works "SaveOrUpdate" with the logic
    }


    public Student findById(Long id) {
        Student student = repository.findById(id).
                orElseThrow(() -> new StudentNotFoundException("Student is not found by id:" + id));
        return student;
    }

    public void deleteById(Long id) {
        findById(id);
        repository.deleteById(id);
    }


    public void updateStudent(Long id, Student student) {
        Student foundStudent = findById(id);

        //If the e-mail of the student to be updated is the same as
        // the e-mail of the registered students and different
        // from the last registered e-mail of the student to be updated-->EXCEPTION
        boolean isExıstsEmail= repository.existsByEmail(student.getEmail());
        boolean isEmailSameStudent=student.getEmail().equals(foundStudent.getEmail());
        if(isExıstsEmail&&!isEmailSameStudent){
            throw new ConflictException("This email has already been saved by a different student");
        }
        repository.save(student);//-ıt works "SaveOrUpdate" with the logic

    }


    public Page<Student> getStudentByPage(Pageable pageable) {
       Page<Student> studentPage=repository.findAll(pageable);
       return studentPage;
    }


    public List<Student> getStudentByGrade(Integer grade) {
      List <Student> studentList= repository.getAllStudentByGradeSQL(grade);
      return studentList;
    }




    public List<Student> getByName(String name) {
        List<Student> studentList=repository.getAllStudentByName(name);
        return studentList;
    }

    public List<Student> getStudentsByNamePattern(String filter) {
      List<Student> studentList =repository.findAll();
      studentList.forEach(t->{
          List<Student>containList= new ArrayList<>();

          if (t.getLastName().contains(filter)){
           containList.add(t);
       }
      });
      return studentList;
    }


    public String getInfoById(Long id) {
        Student student=repository.getById(id);
        String info="name :"+student.getName()+
       " lastName: "+ student.getLastName()+
        " email: "+student.getEmail();
        return info;
    }
}