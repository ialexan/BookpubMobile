package com.ialexan.bookpub.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Book {

    private Long id;

    private String title;

    private String description;

    private File cover;
    
    private File coverThumbnail;

    private List<User> authors;

    private Date dateCreated;

    private Date datePublished;

    @JsonIgnore
    private List<Chapter> chapters;

    private int price;

    public Book()
    {
        authors = new ArrayList<User>();
        chapters = new ArrayList<Chapter>();
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
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

    public File getCover()
    {
        return cover;
    }

    public void setCover( File cover )
    {
        this.cover = cover;
    }

    public List<User> getAuthors()
    {
        return authors;
    }

    public void setAuthors( List<User> authors )
    {
        this.authors = authors;
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

    public List<Chapter> getChapters()
    {
        return chapters;
    }

    public void setChapters( List<Chapter> chapters )
    {
        this.chapters = chapters;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice( int price )
    {
        this.price = price;
    }
    
    public File getCoverThumbnail()
    {
        return coverThumbnail;
    }

    public void setCoverThumbnail( File coverThumbnail )
    {
        this.coverThumbnail = coverThumbnail;
    }
    

}
