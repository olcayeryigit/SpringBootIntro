package com.olcay.springBootIntro.service;

import com.olcay.springBootIntro.domain.Book;
import com.olcay.springBootIntro.domain.Student;
import com.olcay.springBootIntro.exception.BookNotFoundException;
import com.olcay.springBootIntro.repository.BookRepository;
import com.olcay.springBootIntro.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public void createBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getAllBook() {
      List<Book>bookList=bookRepository.findAll();
      return bookList;
    }


}
