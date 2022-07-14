package com.yyds.service.impl;

import com.yyds.dao.BookDao;
import com.yyds.dao.impl.BookDaoImpl;
import com.yyds.pojo.Book;
import com.yyds.service.BookService;

import java.util.List;

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
