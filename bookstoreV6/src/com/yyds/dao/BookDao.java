package com.yyds.dao;

import com.yyds.pojo.Book;

import java.util.List;

public interface BookDao {
    /**
     * 查询所有的图书信息
     * @return
     */
    List<Book> getAllBook();

    /**
     * 删除图书信息
     */
    void deleteBook(Integer bookId);

    /**
     * 添加图书信息
     * @param book
     */
    void addBook(Book book);

    /**
     * 修改图示信息
     * @param book
     */
    void updateBook(Book book);

    /**
     * 根据图书id查询图书信息
     * @param bookId
     * @return
     */
    Book getBookById(Integer bookId);

    /**
     * 更新图书的库存和销量
     * @param bookId
     * @param count
     */
    void updateSalesAndStock(Integer bookId, Integer count);
}
