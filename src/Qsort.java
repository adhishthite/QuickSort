/**
 * Implementing 'Quick Sort' Algorithm
 *
 * Adhish Thite | athite@uncc.edu | UNCC Student ID : 801023459
 * Algorithms and Data Structures - Programming Assignment 2
 *
 **/

import java.io.*;
import java.util.*;

public class Qsort {

    public static void main(String[] args){
        Map<String, Double[]> fileNameToArrayMap = new HashMap<>();
        List<QuickSort.SortData>  sortDataDetails = new ArrayList<>();

        for(int i = 0; i < args.length; i++) {
            if(args[i] != null && args[i].length() != 0 && getArrayFromFile(args[i]) != null) {
                fileNameToArrayMap.put(args[i], getArrayFromFile(args[i]));
            }
        }

        for(String tempFile : fileNameToArrayMap.keySet()) {
            if(fileNameToArrayMap.containsKey(tempFile)) {
                new Qsort().doQuickSortOnInput(fileNameToArrayMap.get(tempFile), tempFile);
            }
        }

        for(QuickSort.SortData tempSortData : sortDataDetails) {
            tempSortData.printSortingData();
        }
    }

    private void doQuickSortOnInput(Double[] inputArray, String fileName){
        Qsort.QuickSort qSort = new Qsort.QuickSort(inputArray, fileName);
        qSort.getSortData().printSortingData();
    }

    private static Double[] getArrayFromFile(String fileName) {
        File inputFile = new File(fileName);
        ArrayList<Double> numberList = new ArrayList<>();
        Double[] arrayToSort = null;

        try{
            BufferedReader buffReader = new BufferedReader(new FileReader(inputFile));
            String readLine;

            while ((readLine = buffReader.readLine()) != null) {
                if(readLine.length() != 0){
                    for(String tempString : readLine.split(";")){
                        numberList.add(Double.parseDouble(tempString));
                    }
                }
            }

            buffReader.close();

            arrayToSort = numberList.toArray(new Double[numberList.size()]);
        } catch (NumberFormatException ex){
            System.err.println("\n Invalid Input Provided !\n" + ex);
            return null;
        } catch (IOException ex) {
            System.err.println("\nI/O Error is >> : " + ex + "\n");
            return null;
        }

        return arrayToSort;
    }



    private static void writeToFile(Double[] arrayToWrite){
        BufferedWriter buffWrite;

        try {
            // Implement logic to write the sorted array in a file.
            File fileName = new File("input3.txt");

            if(!fileName.exists()){
                fileName.createNewFile();
            }

            FileWriter fWriter = new FileWriter(fileName);
            buffWrite = new BufferedWriter(fWriter);
            buffWrite.write(Arrays.toString(arrayToWrite).replace(',',';').replace('[',' ').replace(']',' '));
            buffWrite.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    // Inner Class to perform the Quick Sort Operations - Partition and Sort
    private class QuickSort {

        public SortData sd;
        private Double[] arrayToSort;

        public QuickSort() {
            arrayToSort = null;
        }

        public QuickSort(Double[] arrayToSort_Param, String fileName) {
            arrayToSort = arrayToSort_Param;

            Long startTime = System.currentTimeMillis();
            doQuickSort(arrayToSort,0,arrayToSort.length - 1);
            Long elapsedTime = System.currentTimeMillis() - startTime;

             sd = new SortData(arrayToSort, elapsedTime, fileName);
        }

        public void doQuickSort(Double[] array, int min, int max) {

            if (min < max) {
                int index = doPartition(array, min, max);

                doQuickSort(array, min, index - 1);
                doQuickSort(array, index + 1, max);
            }
        }

        private int doPartition(Double[] tempArray, int min, int max ) {

            Double pivot = tempArray[max];
            int i = (min - 1);

            for(int j = min; j <= max - 1; j++) {
                if (tempArray[j] <= pivot) {
                    i++;

                    Double temp = tempArray[i];
                    tempArray[i] = tempArray[j];
                    tempArray[j] = temp;
                }
            }

            Double temp2 = tempArray[i+1];
            tempArray[i+1] = tempArray[max];
            tempArray[max] = temp2;

            return (i + 1);
        }

        private class SortData{
            Double[] sortedArray;
            Long elapsedTime;
            String fileName;

            public SortData(Double[] sortedArray, Long elapsedTime, String fileName) {
                this.sortedArray = sortedArray;
                this.elapsedTime = elapsedTime;
                this.fileName = fileName;
            }

            public void printSortingData(){
                System.out.println("\nInput >:\t" + fileName);
                System.out.println("\t\t\tInput Size >:\t" + sortedArray.length);
                //System.out.println("\nSorted Array is -\n" + Arrays.toString(sortedArray));
                System.out.println("\t\t\tInput has been sorted in >:\t" + elapsedTime + " ms\n\n");
            }
        }

        public SortData getSortData(){
            return sd;
        }
    }



    // UTILITY METHODS
    public static void getRandomNumbers() {

        ArrayList<Double> randomList = new ArrayList<Double>();

        int min = -9999999;
        int max = 9999999;

        for(int i = min; i <= max; i++) {
            Random r = new Random();
            randomList.add((min + (max - min) * r.nextDouble()));
        }

        Double[] arr = randomList.toArray(new Double[randomList.size()]);

        writeToFile(arr);
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
