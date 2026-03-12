package com.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] ir = {3,2};
          Set<Integer> setOfInteger=Arrays.stream(ir).mapToObj(Integer::valueOf)
        		  .collect(Collectors.toSet());
          System.out.println(setOfInteger);
          String[] str = {"apple","apple","pineapple"};
            List<String> listOfInteger=Arrays.stream(str).filter(n->n.startsWith("a")).collect(Collectors.toList());
            System.out.println(listOfInteger);
            List<Integer> listOfInt=new ArrayList<>();
            listOfInt.add(20);
            listOfInt.add(12);
            
          List<Integer> sortedList=  listOfInt.stream().sorted().collect(Collectors.toList());
         System.out.println(sortedList);
}
}