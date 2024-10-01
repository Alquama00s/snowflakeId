package com.alquama00s.snowflakeId;

import com.alquama00s.snowflakeid.SnowFlake;
import com.alquama00s.snowflakeid.SnowFlakeBuilder;
import com.alquama00s.snowflakeid.exceptions.SnowFlakeExpired;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MultiSnowFlakeTest{

    public static List<SnowFlake> snowFlakeList(){
        var sfb = new SnowFlakeBuilder();
        var lst = new ArrayList<SnowFlake>();
        for (int tbl = 35; tbl <41 ; tbl++) {
            for (int mbl = 5; mbl < 11; mbl++) {
                try {
                    lst.add(sfb.configureBits(tbl,mbl)
                            .setMachineId(12)
                            .build());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        return lst;
    }


    @ParameterizedTest
    @MethodSource("snowFlakeList")
    public void testIdNotNull(SnowFlake sf){
        try {
            var id = sf.nextId();
            assertNotEquals(id,0l);
        } catch (InterruptedException | SnowFlakeExpired e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @MethodSource("snowFlakeList")
    public void testNonDuplicate(SnowFlake sf){
        int len =100;
        HashSet<Long> set= new HashSet<>();
        for (int i = 0; i < len; i++) {
            try {
                set.add(sf.nextId());
            } catch (InterruptedException | SnowFlakeExpired e) {
                e.printStackTrace();
            }
        }

        assertEquals(set.size(),len);
    }

}
