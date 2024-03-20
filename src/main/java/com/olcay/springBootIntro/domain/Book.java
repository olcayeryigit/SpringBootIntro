package com.olcay.springBootIntro.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String bookName;

    @ManyToOne
    @JsonIgnore //stackoverflow --> @JsonIgnore
    private Student student;

    //We may want a field in a Java class to be represented with a different name in a JSON object. For this purpose, we use the @JsonProperty annotation.
}
