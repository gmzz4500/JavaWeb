package com.yyds.dao.impl;

import com.yyds.dao.BaseDao;
import com.yyds.dao.BookDao;
import com.yyds.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {
    @Override
    public List<Book> getAllBook() {
        String sql = "select book_id bookId,book_name bookName,author,price,sales,stock,img_path imgPath from bs_book";
        return getBeanList(Book.class,sql);
    }

    @Override
    public void deleteBook(Integer bookId) {
        String sql = "delete from bs_book where book_id = ?";
        update(sql,bookId);
    }

    @Override
    public void addBook(Book book) {
        String sql = "insert into bs_book values(null,?,?,?,?,?,?)";
        update(sql,book.getBookName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
    }

    @Override
    public Book getBookById(Integer bookId) {
        String sql = "select book_id bookId,book_name bookName,author,price,sales,stock,img_path imgPath from bs_book where book_id=?";
        return getBean(Book.class,sql,bookId);
    }



    @Override
    public void updateBook(Book book) {
        String sql = "update bs_book set book_name=?,author=?,price=?,sales=?,stock=? where book_id=?";
        update(sql,book.getBookName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getBookId());
    }

    @Override
    public void updateSalesAndStock(Integer bookId, Integer count) {
        String sql = "update bs_book set sales = sales + ?,stock = stock - ? where book_id = ?";
        update(sql,count,count,bookId);
    }
}
