package com.olcay.springBootIntro.repository;

import com.olcay.springBootIntro.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository <Book,Long> {


    Book getByBookName(String bookName);
}
