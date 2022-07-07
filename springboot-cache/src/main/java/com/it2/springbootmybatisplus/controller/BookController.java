package com.it2.springbootmybatisplus.controller;

import com.it2.springbootmybatisplus.pojo.Book;
import com.it2.springbootmybatisplus.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService bookService;

    @GetMapping("{id}")
    public Book getById(@PathVariable Integer id){
        return bookService.queryById2(id);
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable Integer id){
        return bookService.deleteBook(id);
    }


}
