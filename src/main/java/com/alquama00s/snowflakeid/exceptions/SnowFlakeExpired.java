package com.alquama00s.snowflakeid.exceptions;

import com.alquama00s.snowflakeid.SnowFlake;
import com.alquama00s.snowflakeid.utils.BitUtills;

public class SnowFlakeExpired extends Exception{
    public SnowFlakeExpired(String err){
        super(err);
    }
    public SnowFlakeExpired(){
        super("snow flake time expired");
    }

    public static SnowFlakeExpired detailedException(SnowFlake sf){
        var maxAllowed = BitUtills.getMaxValueFromBitLen(sf.getTimeBitLen());
        return new SnowFlakeExpired("current ts: "+sf.getCurrTs()+" is greater than max allowed: "+maxAllowed+" for bit len: "+sf.getTimeBitLen());
    }
}
