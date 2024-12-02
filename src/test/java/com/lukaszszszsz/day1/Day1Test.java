package com.lukaszszszsz.day1;

import org.javatuples.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static com.lukaszszszsz.day1.Day1.FIRST_ELEM;
import static com.lukaszszszsz.day1.Day1.SECOND_ELEM;

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
        Assertions.assertTrue(res.containsKey(FIRST_ELEM));
        Assertions.assertTrue(res.containsKey(SECOND_ELEM));
        Assertions.assertTrue(res.get(FIRST_ELEM).containsAll(List.of(1,1)));

    }
    @Test
    public void getOccurenceMapTest(){
        var res = Stream.of(4,3,5,3,9,3).collect(Day1.getOccurenceMap());
        Assertions.assertTrue(res.containsKey(4));
        Assertions.assertEquals(1,res.get(4));
        Assertions.assertEquals(3,res.get(3));

    }

    @Test
    public void getSumOfMinDistancesBetweenGivenUnsortedListTest(){
        String filename = "day1.txt";
        var res = Day1.getSumOfMinDistancesBetweenGivenUnsortedList(filename);
        Assertions.assertEquals(11,res);

    }
    @Test
    public void getSimilarityScoreTest(){
        String filename = "day1.txt";
        var res = Day1.getSimilarityScore(filename);
        Assertions.assertEquals(31,res);

    }
}
