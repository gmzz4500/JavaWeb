package com.yyds.service;

import com.yyds.pojo.Book;

import java.util.List;

public interface BookService {
    /**
     * 查询所有的图书信息
     * @return
     */
    List<Book> getAllBook() ;

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
     * 修改图书信息功能
     * @param book
     */
    void updateBook(Book book);

    /**
     * 根据图书Id查询图书信息
     * @param bookId
     * @return
     */
    Book getBookById(Integer bookId);
}
