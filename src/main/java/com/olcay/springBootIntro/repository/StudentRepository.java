package com.olcay.springBootIntro.repository;

import com.olcay.springBootIntro.domain.Student;
import com.olcay.springBootIntro.dto.InfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    //We convert students to DTOs in the repository
    @Query("SELECT new com.olcay.springBootIntro.dto.InfoDTO(s) FROM Student s WHERE id=:pId")
    Optional<InfoDTO> getInfoDTOById(@Param("pId") Long id);
}
