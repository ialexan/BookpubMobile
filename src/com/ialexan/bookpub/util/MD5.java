package com.ialexan.bookpub.util;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    private String text;

    public MD5( String text )
    {
        this.text = text;
    }

    private BigInteger hashValue( MessageDigest digest, String str )
    {
        digest.update( str.getBytes() );
        return new BigInteger( 1, digest.digest() );
    }

    public String getEncryptedValue() throws NoSuchAlgorithmException
    {
        MessageDigest digest = MessageDigest.getInstance( "MD5" );
        return String.format( "%1$032X", hashValue( digest, this.text ) )
            .toLowerCase(); // Change to lower case because the
        // the ones initially inserted in database are lower case
    }

    public void setText( String text )
    {
        this.text = text;
    }

}
