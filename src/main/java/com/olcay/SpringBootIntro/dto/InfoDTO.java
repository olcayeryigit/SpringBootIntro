package com.olcay.SpringBootIntro.dto;

import com.olcay.SpringBootIntro.domain.Student;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InfoDTO {


    @NotBlank(message = "name cannot be blank!")
    @Size(min = 2,max = 35,message = "last name must be between 2 and 35")
    @Column(nullable = false,length = 35)
    private String name;

    @NotBlank(message = "lastname cannot be blank")
    @Size(min = 2,max = 30,message = "lastname must be between 2 and 30")
    private String lastName;

    @Email(message = "please provide valid email")
    private String email;


    public InfoDTO (Student student){
            this.name= student.getName();
            this.lastName=student.getLastName();
            this.email=student.getEmail();
    }

}
