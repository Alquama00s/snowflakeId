package com.alquama00s.snowflakeid;

import com.alquama00s.snowflakeid.exceptions.SnowFlakeExpired;
import com.alquama00s.snowflakeid.utils.BitUtills;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
public class SnowFlake {


    //bit length settings
    private final long timeBitLen;
    private final long machineIdBitLen;
    private final long sequenceBitLen;
    private final long timeUnit;
    private final long startTs;
    private long currTs;
    private long currSequence;
    private final long machineId;


    public synchronized long nextId() throws InterruptedException,SnowFlakeExpired {
        var currentTs =  ZonedDateTime.now(ZoneId.of("UTC")).toInstant().toEpochMilli()/timeUnit - startTs;
        if(this.currTs < currentTs) {
            this.currTs = currentTs;
            this.currSequence = 0;
        } else { //sf.currTs  equal to currentTs
            if (this.currSequence < BitUtills.getMaxValueFromBitLen(sequenceBitLen)) {
                this.currSequence++;
            } else {
                System.out.println("sequence space exhausted");
                this.currTs++;
                var sleepDur = (this.currTs - currentTs) * this.timeUnit;
                Thread.sleep(sleepDur);
            }
        }
        if (this.currTs > BitUtills.getMaxValueFromBitLen(timeBitLen)) {
            throw SnowFlakeExpired.detailedException(this);
        }

        return currentId();
    }

    public long currentId(){
        return (this.currTs<<(this.sequenceBitLen + this.machineIdBitLen))+
                (this.machineId<<(this.sequenceBitLen))+
                this.currSequence;

    }

}
