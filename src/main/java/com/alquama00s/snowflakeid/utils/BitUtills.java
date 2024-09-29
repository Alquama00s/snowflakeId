package com.alquama00s.snowflakeid.utils;

public class BitUtills {
    public static long getMaxValueFromBitLen(long bitLen){
        var res = Math.pow(2, bitLen);
        if(res > Long.MAX_VALUE)return Long.MAX_VALUE;
        return (long)res;
    }
}
