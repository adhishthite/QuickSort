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

    private static List<String> sortResults = new ArrayList<>();

    public static void main(String[] args){
        Map<String, Double[]> fileNameToArrayMap = new HashMap<>();

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

        writeToFile();
    }

    private void doQuickSortOnInput(Double[] inputArray, String fileName){
        Qsort.QuickSort qSort = new Qsort.QuickSort(inputArray, fileName);
        sortResults.add(qSort.getSortData().printSortingData());
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
                        if(!tempString.isEmpty()){
                            numberList.add(Double.parseDouble(tempString));
                        }
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



    private static void writeToFile(){
        BufferedWriter buffWrite;
        FileWriter fWriter = null;
        Boolean isFileCreated = false;

        try {
            File fileName = new File("answers.txt");

            if(!fileName.exists()){
                isFileCreated = fileName.createNewFile();
            }

            if(isFileCreated){
                fWriter = new FileWriter(fileName);
            } else {
                fWriter = new FileWriter(fileName, true);
            }

            buffWrite = new BufferedWriter(fWriter);
            for(String tempSortData : sortResults){
                buffWrite.write(tempSortData);
            }
            buffWrite.close();
        } catch (IOException ex) {
            System.out.println("\n\t\t** There is an ERROR in ANSWER.TXT file creation **\n\nError Trace is >:\n\n");
            ex.printStackTrace();
        }
    }


    // Inner Class to perform the Quick Sort Operations - Partition and Sort
    private class QuickSort {

        private SortData sd;
        private Double[] arrayToSort;

        private QuickSort(Double[] arrayToSort_Param, String fileName) {
            arrayToSort = arrayToSort_Param;

            Long startTime = System.currentTimeMillis();
            doQuickSort(arrayToSort,0,arrayToSort.length - 1);
            Long elapsedTime = System.currentTimeMillis() - startTime;

             sd = new SortData(arrayToSort, elapsedTime, fileName);
        }

        private void doQuickSort(Double[] array, int min, int max) {

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

            private SortData(Double[] sortedArray, Long elapsedTime, String fileName) {
                this.sortedArray = sortedArray;
                this.elapsedTime = elapsedTime;
                this.fileName = fileName;
            }

            private String printSortingData(){
                return ("\n" + fileName + "\n\n\t\t\tInput Size >:\t\t" + sortedArray.length + "\n\t\t\tSorted Array is >:\t" + Arrays.toString(sortedArray) + "\n\t\t\tPerformance >:\t" + elapsedTime + " ms\n\n");
            }
        }

        private SortData getSortData(){
            return sd;
        }
    }
}
