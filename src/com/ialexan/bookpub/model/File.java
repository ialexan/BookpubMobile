package com.ialexan.bookpub.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class File {

    private Long id;

    private String name;

    private String type;

    private Long size;

    private Date date;

    @JsonIgnore
    private User owner;

    private boolean deleted;
    
    private String baseDir;
    
    private String diskFile;

    public File()
    {
        date = new Date();
        deleted = false;
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType( String type )
    {
        this.type = type;
    }

    public Long getSize()
    {
        return size;
    }

    public void setSize( Long size )
    {
        this.size = size;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate( Date date )
    {
        this.date = date;
    }

    public User getOwner()
    {
        return owner;
    }

    public void setOwner( User owner )
    {
        this.owner = owner;
    }

    public boolean isDeleted()
    {
        return deleted;
    }

    public void setDeleted( boolean deleted )
    {
        this.deleted = deleted;
    }

    
    public String getBaseDir()
    {
        return baseDir;
    }

    
    public void setBaseDir( String baseDir )
    {
        this.baseDir = baseDir;
    }

    
    public String getDiskFile()
    {
        return diskFile;
    }

    
    public void setDiskFile( String diskFile )
    {
        this.diskFile = diskFile;
    }

    
}
