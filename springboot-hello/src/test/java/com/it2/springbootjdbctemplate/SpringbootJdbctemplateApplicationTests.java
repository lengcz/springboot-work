package com.it2.springbootjdbctemplate;

import com.it2.springbootjdbctemplate.pojo.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class SpringbootJdbctemplateApplicationTests {



    @Test
    void testQuery(@Autowired JdbcTemplate jdbcTemplate){
        String sql="select * from book ";
        RowMapper<Book> rm=new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                Book book=new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setType(rs.getString("type"));
                book.setDescription(rs.getString("description"));
                return book;
            }
        };
        List<Book> books=jdbcTemplate.query(sql,rm);
        System.out.println(books);
    }

    @Test
    void testUpdate(@Autowired JdbcTemplate jdbcTemplate){
        String sql="insert into book (name,type,description) VALUES ('hello','测试','测试内容')";
        int i=jdbcTemplate.update(sql);
        System.out.println(i);
    }

}
