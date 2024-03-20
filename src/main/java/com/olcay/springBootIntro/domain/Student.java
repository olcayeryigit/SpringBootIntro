package com.olcay.springBootIntro.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank(message = "name cannot be blank!")
    @Size(min = 2, max = 35, message = "last name must be between 2 and 35")
    @Column(nullable = false, length = 35)
    private String name;

    @NotBlank(message = "lastname cannot be blank")
    @Size(min = 2, max = 30, message = "lastname must be between 2 and 30")
    @Column(nullable = false, length = 30)
    private String lastName;

    @NotNull(message = "please provide grade")
    @Column(nullable = false)
    private Integer grade;

    @Column(unique = true)
    @Email(message = "please provide valid email")
    private String email;

    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "student")
    private List<Book> bookList;

}
