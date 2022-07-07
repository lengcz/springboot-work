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
        long s=System.currentTimeMillis();
        Book book= bookService.queryById2(id);
        System.out.println("耗时:"+(System.currentTimeMillis()-s));
        return book;
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable Integer id){
        return bookService.deleteBook(id);
    }


}
