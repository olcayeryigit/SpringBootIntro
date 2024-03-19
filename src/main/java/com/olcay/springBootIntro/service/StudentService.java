package com.olcay.springBootIntro.service;

import com.olcay.springBootIntro.domain.Student;
import com.olcay.springBootIntro.dto.InfoDTO;
import com.olcay.springBootIntro.exception.ConflictException;
import com.olcay.springBootIntro.exception.StudentNotFoundException;
import com.olcay.springBootIntro.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    public void updateStudent(Long id, InfoDTO updateDTO) {
        Student foundStudent = findById(id);
        foundStudent.setName(updateDTO.getName());
        foundStudent.setLastName(updateDTO.getLastName());


        //If the e-mail of the student to be updated is the same as
        // the e-mail of the registered students and different
        // from the last registered e-mail of the student to be updated-->EXCEPTION
        boolean isExıstsEmail= repository.existsByEmail(updateDTO.getEmail());
        boolean isEmailSameStudent=updateDTO.getEmail().equals(foundStudent.getEmail());
        if(isExıstsEmail&&!isEmailSameStudent){
            throw new ConflictException("This email has already been saved by a different student");
        }
        foundStudent.setEmail(updateDTO.getEmail());

        repository.save(foundStudent);//-ıt works "SaveOrUpdate" with the logic

    }


    public Page<Student> getStudentByPage(Pageable pageable) {
       Page<Student> studentPage=repository.findAll(pageable);
       return studentPage;
    }


    public List<Student> getStudentByGrade(Integer grade) {
      List <Student> studentList= repository.getAllStudentByGradeSQL(grade);
      return studentList;
    }




    public List<Student> getAllStudentByName(String name) {
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



    //We removed @NotNull-@Not Blank constraints within the DTO class
    //We will fetch DTO from repo, there is a possibility of it being null--->OPTIONAL-->EXCEPTION
    public InfoDTO getInfoDTOById(Long id) {
        InfoDTO infoDTO=repository.getInfoDTOById(id).orElseThrow(()-> new  StudentNotFoundException("Student is not found by id:"+id));
        return infoDTO;
    }

}
