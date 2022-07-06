package com.it2.springbootmongodb;

import com.it2.springbootmongodb.pojo.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@SpringBootTest
class SpringbootMongodbApplicationTests {


    @Test
    void contextLoads() {
    }

    @Test
    void save(@Autowired MongoTemplate mongoTemplate) {
        Book book = new Book();
        book.setId(1);
        book.setName("abc");
        book.setType("test");
        book.setDescription("测试描述");

        mongoTemplate.save(book);

    }

    @Test
    void find(@Autowired MongoTemplate mongoTemplate) {
        List<Book> list= mongoTemplate.findAll(Book.class);
        System.out.println(list);
    }

}
