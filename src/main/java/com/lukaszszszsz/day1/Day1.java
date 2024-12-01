package com.lukaszszszsz.day1;

import com.lukaszszszsz.commons.FileReader;
import org.javatuples.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day1 {

    public static Integer FIRST = 1;
    public static Integer SECOND = 2;

    public static void main(String[] args) {
        String filename = "day1.txt";
        System.out.println(getSumOfMinDistancesBetweenGivenUnsortedList(filename));
    }


     static Pair<Integer,Integer> lineToTuple(String line){

        String[] res = line.split("\\s+");
        assert res.length == 2;

        var resA = Integer.parseInt(res[0]);
        var resB = Integer.parseInt(res[1]);

        return  Pair.with(resA,resB);

    }

     static Integer distance(Pair<Integer,Integer> pair){
        return  Math.abs(pair.getValue0()-pair.getValue1());
    }

    static Collector<Pair<Integer,Integer>,?,Map<Integer, List<Integer>>>collectStreams(){
        return Collectors.teeing(
                Collectors.mapping(Pair::getValue0, Collectors.toList()),
                Collectors.mapping(Pair::getValue1, Collectors.toList()),
                (list1,list2) ->
                {Map<Integer, List<Integer>> map = new HashMap<>();
                    map.put(FIRST,list1.stream().sorted().toList());
                    map.put(SECOND,list2.stream().sorted().toList());
                    return map;
                });

    }

     static Integer getSumOfMinDistancesBetweenGivenUnsortedList(String filename){
        FileReader fileReader = new FileReader();
        var collectedLists = fileReader.readFile(filename).map(Day1::lineToTuple).collect(Day1.collectStreams());
        assert collectedLists.get(FIRST).size() == collectedLists.get(SECOND).size();
        return IntStream.range(0,collectedLists.get(FIRST).size())
                .mapToObj(i -> Pair.with(collectedLists.get(FIRST).get(i),collectedLists.get(SECOND).get(i)))
                .mapToInt(Day1::distance)
                .sum();

    }

}
