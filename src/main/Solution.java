/*
Author : Tauriq Dolley
08/12/2020
Impact Take Home Test
 */
package main;

import org.junit.runner.JUnitCore;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Collectors;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Solution implements NumberRangeSummarizer{

    //collect the input
    public Collection<Integer> collect(String input){
        //Checks for lack of input or a null value passed
        if(input.isEmpty() || input.equals(null)){
            return Collections.EMPTY_LIST;
        }
        else{
            //using Java8 Streams
            Collection<Integer> numbers =
                    Arrays.stream(input.split(","))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());

            return numbers;
        }
    }

    //get the summarized string
    public String summarizeCollection(Collection<Integer> input) {
        //define an iterate to access the input data
        Iterator<Integer> iterator = input.iterator();
        StringBuilder answer = new StringBuilder();   //Faster performance over normal string concatenation when done in a loop

        if(input.size() > 2) {
            int prev = iterator.next();
            int next = iterator.next();

            boolean sequential = false;
            int start;

            while (iterator.hasNext()) {
                //while there is a sequential order
                if (next == prev + 1) {
                    sequential = true;
                    start = prev;
                    //iterate until nonsequential number found
                    while (next == prev + 1 && iterator.hasNext()) {
                        prev = next;
                        next = iterator.next();
                    }
                    answer.append(start + "-" + prev + ", ");

                }
                else {
                    //value doesn't follow a sequential number set
                    if (!sequential) {
                        answer.append(prev + ", ");
                    }
                    prev = next;
                    if(iterator.hasNext()){
                        next = iterator.next();
                    }
                    sequential = false;
                }
            }
            answer.append(next);
            return answer.toString();
        }
        else if(input.size() == 1){
            answer.append(iterator.next());
            return answer.toString();
        }
        else if(input.size() == 2){
            int prev = iterator.next();
            int next = iterator.next();

            if (next == prev + 1) {
                answer.append(prev + "-" + next);
            }
            else {
                    answer.append(prev + ", "+next);
                }

            return answer.toString();
        }
        else{
            return "There is no input";
        }
    }

    public static void main(String args[]) {
        String testInput = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";

        Result result = JUnitCore.runClasses(test.SolutionTest.class);
        result.getFailures().stream().map(Failure::toString).forEach(System.out::println);
        System.out.println("Test Results : " + result.wasSuccessful());

        Solution s = new Solution();
        System.out.println("Sample Input: " + testInput);
        System.out.println("Result: " + s.summarizeCollection(s.collect(testInput)));

    }

}



