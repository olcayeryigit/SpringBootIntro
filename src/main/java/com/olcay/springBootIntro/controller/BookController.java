package com.olcay.springBootIntro.controller;

import com.olcay.springBootIntro.domain.Book;
import com.olcay.springBootIntro.domain.Student;
import com.olcay.springBootIntro.service.BookService;
import com.olcay.springBootIntro.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    private final StudentService studentService;


//We stated that the "set" process should be done through the book object.(with mappedBy)
    @PostMapping("/{std_id}")
    public ResponseEntity<String> createBook(@RequestBody Book book, @PathVariable("std_id") Long id) {
        Student student=studentService.findById(id);
        book.setStudent(student);
        bookService.createBook(book);
        String response = "Book created succesfully.";
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Book>> listAllBook() {
        List <Book> bookList=bookService.getAllBook();
        return new ResponseEntity<>(bookList,HttpStatus.OK);
    }


}
