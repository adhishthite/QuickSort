/**
 * Implementing 'Quick Sort' Algorithm
 *
 * Adhish Thite | athite@uncc.edu | UNCC Student ID : 801023459
 * Algorithms and Data Structures - Programming Assignment 2
 *
 **/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Qsort {
    public static void main(String[] args) {

        ArrayList<Double> randomList = new ArrayList<Double>();

        int min = -9000;
        int max = 1000;

        System.out.println("\nProcessing Input Arrays\n\n");
        for(int i = min; i <= max; i++) {
            Random r = new Random();
            randomList.add((min + (max - min) * r.nextDouble()));
        }

        Double[] arr = randomList.toArray(new Double[randomList.size()]);

        QuickSort q = new QuickSort(arr, "test.txt");
        QuickSort.SortData sd = q.getSortData();



        if(validateSortedArray(arr)){
            System.out.println("\nThe array has been successfully sorted !\n");
        } else {
            System.out.println("\nThere has been some error in sorting !\n");
        }
    }

    private static boolean validateSortedArray(Double[] array) {
        for(int i = 0; i < array.length - 1; i++){
            if(array[i] > array[i+1]){
                return  false;
            }
        }
        return true;
    }
}
