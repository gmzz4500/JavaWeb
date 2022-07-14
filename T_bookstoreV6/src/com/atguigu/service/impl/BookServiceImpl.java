package com.atguigu.service.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import com.atguigu.service.BookService;

import java.util.List;

/**
 * Date:2022/4/12
 * Author:ybc
 * Description:
 */
public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public List<Book> getAllBook() {
        return bookDao.getAllBook();
    }

    @Override
    public void deleteBook(Integer bookId) {
        bookDao.deleteBook(bookId);
    }

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public Book getBookById(Integer bookId) {
        return bookDao.getBookById(bookId);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }
}
