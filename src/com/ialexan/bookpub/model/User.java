package com.ialexan.bookpub.model;


import java.util.Date;


public class User {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String email;

    private String lastName;

    private String firstName;

    private Date birthday;

    public User()
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    public Date getBirthday()
    {
        return birthday;
    }

    public void setBirthday( Date birthday )
    {
        this.birthday = birthday;
    }



}
