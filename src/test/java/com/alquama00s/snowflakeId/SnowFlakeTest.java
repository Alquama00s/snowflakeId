package com.alquama00s.snowflakeId;

import com.alquama00s.snowflakeid.SnowFlake;
import com.alquama00s.snowflakeid.SnowFlakeBuilder;
import com.alquama00s.snowflakeid.exceptions.InvalidMachineId;
import com.alquama00s.snowflakeid.exceptions.SnowFlakeExpired;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SnowFlakeTest extends MultiSnowFlakeTest{
    static SnowFlake sf;

    @BeforeAll
    public static void setup(){
        try {
            sf = new SnowFlakeBuilder()
                    .setMachineId(120)
                    .build();
        } catch (InvalidMachineId e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testIdNotNull(){
        super.testIdNotNull(sf);
    }

    @Test
    public void testNonDuplicate(){
        super.testNonDuplicate(sf);
    }

}
