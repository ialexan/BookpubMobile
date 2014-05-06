package com.ialexan.bookpub.model;

import java.util.Date;

public class Chapter {


    private Long id;

    private Book book;

    private int number;

    private String title;

    private String description;

    private String content;


    private Date dateCreated;

    private Date datePublished;

    public Chapter()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public Book getBook()
    {
        return book;
    }

    public void setBook( Book book )
    {
        this.book = book;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber( int number )
    {
        this.number = number;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle( String title )
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent( String content )
    {
        this.content = content;
    }

    public Date getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated( Date dateCreated )
    {
        this.dateCreated = dateCreated;
    }

    public Date getDatePublished()
    {
        return datePublished;
    }

    public void setDatePublished( Date datePublished )
    {
        this.datePublished = datePublished;
    }

}
