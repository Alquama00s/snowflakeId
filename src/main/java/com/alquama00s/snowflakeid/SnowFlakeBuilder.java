package com.alquama00s.snowflakeid;

import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.alquama00s.snowflakeid.constants.Constants;
import com.alquama00s.snowflakeid.exceptions.InvalidBitConfiguration;
import com.alquama00s.snowflakeid.exceptions.InvalidMachineId;
import com.alquama00s.snowflakeid.utils.BitUtills;


public class SnowFlakeBuilder {
    //bit length settings
    private long timeBitLen = 39;
    private long machineIdBitLen = 8;
    private long sequenceBitLen = Constants.ID_BIT_LEN - 47;
    //in milli sec
    private long timeUnit;
    private long startTs;
    private long machineId;
    private boolean timeConfig = false;

    private SnowFlakeBuilder(){}

    public SnowFlakeBuilder configureBits(
        long timeBitLen,
        long machineIdBitLen
    ) throws InvalidBitConfiguration{
        var machineBitLen = Constants.ID_BIT_LEN - (machineIdBitLen+timeBitLen);
        if(timeBitLen<=0 || sequenceBitLen <=0 || machineBitLen<=0){
            throw new InvalidBitConfiguration();
        }
        this.machineIdBitLen = machineIdBitLen;
        this.timeBitLen = timeBitLen;
        this.sequenceBitLen = machineBitLen;

        return this;

    }

    public SnowFlakeBuilder configureTime(long timeUnit,int startYear,Month startMonth,int startDay){
        ZonedDateTime ts = ZonedDateTime.of(startYear, startMonth.ordinal()+1, startDay, 
        0, 0, 0, 
        0, ZoneId.of("UTC"));
        this.timeUnit = timeUnit;
        this.startTs = ts.toInstant().toEpochMilli()/this.timeUnit;
        timeConfig = true;
        return this;
    }

    public SnowFlakeBuilder configureTime(long timeUnit,int startYear){
        return configureTime(timeUnit, startYear,Month.JANUARY,0);
    }


    public SnowFlakeBuilder setMachineId(long machineId) throws InvalidMachineId {
        if(machineId > BitUtills.getMaxValueFromBitLen(machineIdBitLen)){
            throw new InvalidMachineId();
        }
        this.machineId = machineId;
        return this;
    }

    public SnowFlake build() throws InvalidMachineId {
        if(machineId==0){
            throw new InvalidMachineId();
        }
        if(!timeConfig){
            configureTime(10,2024);
        }
        return new SnowFlake(
                timeBitLen,
                machineIdBitLen,
                sequenceBitLen,
                timeUnit,
                startTs,
                0,
                0,
                machineId

        );
    }




}
