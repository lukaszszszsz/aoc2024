package com.lukaszszszsz.day1;

import com.lukaszszszsz.commons.FileReader;
import org.javatuples.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day1 {

    public static Integer FIRST_ELEM = 0;
    public static Integer SECOND_ELEM = 1;

    public static void main(String[] args) {
        String filename = "day1.txt";
        System.out.println(getSumOfMinDistancesBetweenGivenUnsortedList(filename));
        System.out.println(getSimilarityScore(filename));
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
                    map.put(FIRST_ELEM,list1.stream().sorted().toList());
                    map.put(SECOND_ELEM,list2.stream().sorted().toList());
                    return map;
                });

    }

    
     static Integer getSumOfMinDistancesBetweenGivenUnsortedList(String filename){
        FileReader fileReader = new FileReader();
        
        List<Integer> firstList = fileReader.readFile(filename).map(Day1::lineToTuple).map(getElem(FIRST_ELEM)).sorted().toList();
        List<Integer>  secondList = fileReader.readFile(filename).map(Day1::lineToTuple).map(getElem(SECOND_ELEM)).sorted().toList();
        assert firstList.size() == secondList.size();
        return IntStream.range(0,firstList.size())
                .mapToObj(i -> Pair.with(firstList.get(i),secondList.get(i)))
                .mapToInt(Day1::distance)
                .sum();

    }


    static Collector<Integer,?,Map<Integer,Integer>> getOccurenceMap()
    {
        return Collectors.toMap(elem -> elem, elem -> 1, Integer::sum);
    }


    static Integer getSimilarityScore(String filename){
        FileReader fileReader = new FileReader();

        Map<Integer,Integer>  occurenceMap = fileReader.readFile(filename).map(Day1::lineToTuple).map(getElem(SECOND_ELEM)).collect(getOccurenceMap());
        return fileReader.readFile(filename).map(Day1::lineToTuple).map(getElem(FIRST_ELEM)).sorted()
                .map(elem -> occurenceMap.getOrDefault(elem,0)*elem)
                .mapToInt(Integer::intValue)
                .sum();

    }
    private static Function<Pair<Integer, Integer>, Integer> getElem(Integer pos) {
        return pair -> (Integer) pair.getValue(pos);
    }

}
