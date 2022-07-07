package com.it2.springbootmybatisplus.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.it2.springbootmybatisplus.mapper.BookMapper;
import com.it2.springbootmybatisplus.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    @Cacheable(value="cachespace",key="#id",unless  = "#result ==null")  //#id表示读取形参里名称为id的值，value表示缓存空间(自定义名称)
    public Book queryById(Integer id) {
        System.out.println("读取数据库:id="+id);
        return bookMapper.selectById(id);
    }

    @Override
    @Cacheable(value="cachespace",keyGenerator = "myKeyGenerator",condition ="#id>3",sync =true)  //使用自定义的key生成器
    public Book queryById2(Integer id) {
        System.out.println("使用myKeyGenerator生成key,读取数据库:id="+id);
        return bookMapper.selectById(id);
    }

    @Override
    @CacheEvict(value="cachespace",key="#id") //使缓存失效
    public boolean deleteBook(Integer id) {
        System.out.println("删除了数据:id="+id);
        return true;
    }
}