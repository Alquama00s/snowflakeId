package com.alquama00s.snowflakeid.exceptions;

import java.lang.Exception;

public class InvalidBitConfiguration extends Exception {

    public InvalidBitConfiguration(){
        super("the bit lengths must be frester thsn 0 ");
    }
    
}
