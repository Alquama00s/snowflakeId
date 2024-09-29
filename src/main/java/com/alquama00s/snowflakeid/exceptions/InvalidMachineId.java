package com.alquama00s.snowflakeid.exceptions;

public class InvalidMachineId extends Exception{
    public InvalidMachineId(){
        super("machine id to big");
    }
}
