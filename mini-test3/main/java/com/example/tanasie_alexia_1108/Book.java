package com.example.tanasie_alexia_1108;

import java.util.Date;

public class Book {
    private String bookTitle;
    private String bookAuthor;
    private float bookPrice;
    private Date bookReleaseDate;

    public Book(){

    }
    public Book(String bookTitle, String bookAuthor, float bookPrice, Date bookReleaseDate) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPrice = bookPrice;
        this.bookReleaseDate = bookReleaseDate;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        if (bookTitle == null || bookTitle.isEmpty()) {
            throw new IllegalArgumentException("not null");
        }
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        if (bookAuthor == null || bookAuthor.isEmpty()) {
            throw new IllegalArgumentException("not null");
        }
        this.bookAuthor = bookAuthor;

    }

    public float getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(float bookPrice) {
        if (bookPrice <= 0) {
            throw new IllegalArgumentException("> 0");
        }
        this.bookPrice = bookPrice;
    }

    public Date getBookReleaseDate() {
        return bookReleaseDate;
    }

    public void setBookReleaseDate(Date bookReleaseDate) {
        this.bookReleaseDate = bookReleaseDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookTitle='" + bookTitle + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookPrice=" + bookPrice +
                ", bookReleaseDate=" + bookReleaseDate +
                '}';
    }
}
