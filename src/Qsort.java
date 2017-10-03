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
                QuickSort qSort = new QuickSort(fileNameToArrayMap.get(tempFile), tempFile);
                sortDataDetails.add(qSort.getSortData());
            }
        }

        for(QuickSort.SortData tempSortData : sortDataDetails) {
            tempSortData.printSortingData();
        }

        // getRandomNumbers();
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
}
