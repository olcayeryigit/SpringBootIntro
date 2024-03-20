package com.olcay.springBootIntro.controller;

import com.olcay.springBootIntro.domain.Student;
import com.olcay.springBootIntro.dto.InfoDTO;
import com.olcay.springBootIntro.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {


    private final StudentService service;

    @GetMapping("/greet")
    public String greet() {
        return "Hello Spring Boot :)";
    }

    @GetMapping("/list")
    public ResponseEntity<List<Student>> listAllStudents() {
        List<Student> studentList = service.getAllStudent();
        return ResponseEntity.ok(studentList);
    }

    //*id information will not appear on the frontend
    @PostMapping
    public ResponseEntity<String> createStudent(@Valid @RequestBody Student student) {
        service.createStudent(student);
        String response = "Student created succesfully";
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
        Student student = service.findById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/query")
    public ResponseEntity<Student> findStudent(@RequestParam("id") Long id) {
        Student student = service.findById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable("id") Long id) {
        service.deleteById(id);
        String response = "Student deleted succesfully";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable("id") Long id, @Valid @RequestBody InfoDTO updateDTO) {
        service.updateStudent(id, updateDTO);
        String response = "Student updated succesfully";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity <Page<Student>> getAllStudentByPage(
            @RequestParam("page") Integer page,
            @RequestParam("size")Integer size,
            @RequestParam("sort") String prop,
            @RequestParam("direction") Sort.Direction direction

            ) {
        Pageable pageable= PageRequest.of(page,size,Sort.by(prop));
        Page<Student> studentPage=service.getStudentByPage(pageable);
        return new ResponseEntity<>(studentPage, HttpStatus.OK);
    }

    @GetMapping("/grade/{grade}")
    public ResponseEntity<List<Student>>getStudentByGrade(@PathVariable("grade") Integer grade){
      List<Student> studentList=service.getStudentByGrade(grade);
      return new ResponseEntity<>(studentList,HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<Student>>getStudentByName(@RequestParam("name") String name){
        List<Student> studentList= service.getAllStudentByName(name);
        return new  ResponseEntity<>(studentList,HttpStatus.OK);
    }



    @GetMapping("/lastname/{filter}")
    public ResponseEntity<List<Student>>getStudentsByNamePattern(@PathVariable("filter") String filter){
       List<Student> studentList= service.getStudentsByNamePattern(filter);
       return new  ResponseEntity<>(studentList,HttpStatus.OK);
    }


    @GetMapping("/info/{id}")
    public ResponseEntity<InfoDTO>getInfoDTOById(@PathVariable Long id){
        InfoDTO infoDTO=service.getInfoDTOById(id);

        return new ResponseEntity<>(infoDTO,HttpStatus.OK);
    }


}
