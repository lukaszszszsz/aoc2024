package com.lukaszszszsz.day1;

import com.lukaszszszsz.commons.FileReader;
import org.javatuples.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static com.lukaszszszsz.day1.Day1.FIRST;
import static com.lukaszszszsz.day1.Day1.SECOND;

public class Day1Test {

    @Test
    public void lineToTupleTest(){
        String inputLine = "3   4";
        var res = Day1.lineToTuple(inputLine);
        Assertions.assertEquals(3,res.getValue0());
        Assertions.assertEquals(4,res.getValue1());
    }
    @Test
    public void distanceTest(){
        Pair<Integer,Integer> input = Pair.with(3,4);
        Assertions.assertEquals(1,Day1.distance(input));
    }

    @Test
    public void collectStreamsTest(){
        var res = Stream.of(Pair.with(1,2),Pair.with(1,3)).collect(Day1.collectStreams());
        Assertions.assertTrue(res.containsKey(FIRST));
        Assertions.assertTrue(res.containsKey(SECOND));
        Assertions.assertTrue(res.get(FIRST).containsAll(List.of(1,1)));

    }

    @Test
    public void getSumOfMinDistancesBetweenGivenUnsortedListTest(){
        String filename = "day1.txt";
        var res = Day1.getSumOfMinDistancesBetweenGivenUnsortedList(filename);
        Assertions.assertEquals(11,res);

    }
}
