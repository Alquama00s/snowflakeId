package com.alquama00s.snowflakeid.exceptions;

public class InvalidMachineId extends Exception{
    public InvalidMachineId(long maxLen,long bitlen,long givenId){
        super("given machine id: "+givenId+" to big for bitLength: "+bitlen+" max allowed: "+maxLen);
    }

    public InvalidMachineId(String err){
        super(err);
    }
    public static InvalidMachineId zeroMachineId(){
        return  new InvalidMachineId("machine id cant be zero");
    }
}
