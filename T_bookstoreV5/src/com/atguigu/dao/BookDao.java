package com.atguigu.dao;

import com.atguigu.pojo.Book;

import java.util.List;

/**
 * Date:2022/4/12
 * Author:ybc
 * Description:
 */
public interface BookDao {

    /**
     * 查询所有的图书信息
     * @return
     */
    List<Book> getAllBook();

    /**
     * 删除图书信息
     * @param bookId
     */
    void deleteBook(Integer bookId);

    /**
     * 添加图书信息
     * @param book
     */
    void addBook(Book book);

    /**
     * 通过图书的id查询图书信息
     * @param bookId
     * @return
     */
    Book getBookById(Integer bookId);

    /**
     * 修改图书信息
     * @param book
     */
    void updateBook(Book book);
}
