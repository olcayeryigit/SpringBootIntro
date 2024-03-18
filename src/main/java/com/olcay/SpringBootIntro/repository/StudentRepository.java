package com.olcay.SpringBootIntro.repository;

import com.olcay.SpringBootIntro.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);

    List<Student> findAllByGrade(Integer grade);

    //JPQL
    @Query("FROM Student WHERE grade=:pGrade")
    List<Student> getAllStudentByGrade(@Param("pGrade") Integer grade);

    //SQL
    @Query(value = "SELECT * FROM student WHERE grade=:pGrade", nativeQuery = true)
    List<Student> getAllStudentByGradeSQL(@Param("pGrade") Integer grade);

    List<Student> getAllStudentByName(String name);



}
