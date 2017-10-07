/**
 * Implementing 'Quick Sort' Algorithm
 *
 * Adhish Thite | athite@uncc.edu | UNCC Student ID : 801023459
 * Algorithms and Data Structures - Programming Assignment 2
 *
 **/

/* Import Statements */
import java.io.*;
import java.util.*;

public class Qsort {

    // Declaration of Global Variables - START
    private static List<String> sortResults = new ArrayList<>();
    // Declaration of Global Variables - END

    /**
     * MAIN METHOD
     *
     * The command line arguments are picked up and the unsorted arrays in the files are stored in a Map.
     *
     * The QSort class in then initialized and the Quick Sort is performed on one input file at a time.
     *
     * The results of all the sortings are then written to the output file.
     * */
    public static void main(String[] args){
        Map<String, Double[]> fileNameToArrayMap = new HashMap<>();

        // Stores the FileName-InputArray association in a Map
        for(int i = 0; i < args.length; i++) {
            if(args[i] != null && args[i].length() != 0 && getArrayFromFile(args[i]) != null) {
                fileNameToArrayMap.put(args[i], getArrayFromFile(args[i]));
            }
        }

        // Perform Quick Sort for all the inputs, one-by-one.
        for(String tempFile : fileNameToArrayMap.keySet()) {
            if(fileNameToArrayMap.containsKey(tempFile)) {
                new Qsort().doQuickSortOnInput(fileNameToArrayMap.get(tempFile), tempFile);
            }
        }

        // Write the output to the output file.
        writeToFile();
        System.out.println("\n\nThe results are stored in ANSWERS.TXT file.\n\n");
    }

    /**
     * Method   -       doQuickSort()
     * @param  -       Double[], String
     * @return  -       void
     *
     * Function -       Implements the quick sort on the given input file and then collects the sorted data in a String
     *                  wrapper.
     * */
    private void doQuickSortOnInput(Double[] inputArray, String fileName){
        Qsort.QuickSort qSort = new Qsort.QuickSort(inputArray, fileName);
        sortResults.add(qSort.getSortData().printSortingData());
    }

    /**
     * Method   -       getArrayFromFile
     * @param   -       String
     * @return  -       Double[]
     *
     * Function -       Gets the Double[] array from the input file.
     * */
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


    /**
     * Method   -       writeToFile
     * @param   -       none
     * @return  -       none
     *
     * Function -       Writes the Sorting Results in the
     * */
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


    /* INNER CLASS TO PERFORM QUICK SORT OPERATIONS - PARTITION AND SORT
    * Inner Class increases the security of the Quick Sort method and ensures that the data of 1 sort is maintained.
    * */
    private class QuickSort {

        private SortData sd;
        private Double[] arrayToSort;

        // Parameterized Constructor
        private QuickSort(Double[] arrayToSort_Param, String fileName) {
            arrayToSort = arrayToSort_Param;

            // Implement Quick Sort and capture performance.
            Long startTime = System.currentTimeMillis();
            doQuickSort(arrayToSort,0,arrayToSort.length - 1);
            Long elapsedTime = System.currentTimeMillis() - startTime;

            // Store the result of this sort in a wrapper class.
            sd = new SortData(arrayToSort, elapsedTime, fileName);
        }

        // QUICK SORT - SORTING METHOD
        private void doQuickSort(Double[] array, int min, int max) {

            if (min < max) {
                int index = doPartition(array, min, max);

                doQuickSort(array, min, index - 1);
                doQuickSort(array, index + 1, max);
            }
        }

        // QUICK SORT - PARTITION METHOD
        private int doPartition(Double[] tempArray, int min, int max ) {

            // Pivot is set as the last element of the Array.
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

        // WRAPPER CLASS - Used to capture all the details of one sort.
        private class SortData{
            Double[] sortedArray;
            Long elapsedTime;
            String fileName;

            private SortData(Double[] sortedArray, Long elapsedTime, String fileName) {
                this.sortedArray = sortedArray;
                this.elapsedTime = elapsedTime;
                this.fileName = fileName;
            }

            // Returns the String which consolidates the entire sorting details.
            private String printSortingData(){
                return ("\n" + fileName + "\n\n\t\t\tInput Size >:\t\t" + sortedArray.length + "\n\t\t\tSorted Array is >:\t" + Arrays.toString(sortedArray) + "\n\t\t\tPerformance >:\t" + elapsedTime + " ms\n\n");
            }
        }

        // Returns the Sort Details for 1 sort operation.
        private SortData getSortData(){
            return sd;
        }
    }
}

/*
Adhish Thite | athite@uncc.edu | UNCC ID: 801023459

QUICK SORT

This program is an implementation of the Quick Sort algorithm written in Java.

Compiler used -> JAVAC
Platform ->  macOS High Sierra 10.13
IDE -> IDEA IntelliJ Community 2017.2

Program Design ->

        1.  The start of program begins in the 'main' function where the input file name is captured from the command line.
            There can be more than 1 input file for this program.
        2.  The control then passes to the 'getArrayFromFile()' where the file name is passed as a parameter.
            This method then reads each line of the file, and stores the individual elements in an array.
        3.  The control is transferred to the 'doQuickSort()' method which instantiates the 'Quick Sort' inner class.
        4.  The inner class 'Quick Sort' performs the Quick Sort on the input array and returns the details of Sorting
            (Sorted Array, Performance, Filename).
        5.  Steps 3 & 4 are repeated depending upon the number of input files.
        6.  The code handles exceptions ? IO Exception while writing to output file
            and general exceptions while reading the input file.

Algorithm Working ->

        1.  The algorithm is based on the Divide and Conquer method.
        2.  The 'partition()' method implements the partition of the input array in such a way that at any point of time,
            the elements lesser than the pivot are stored to the left of the pivot and the elements greater to the pivot are
            stored to the right. This is the loop invariant for Quick Sort.
        3.  The 'quicksort()' divides the array into subarrays and transfers them to the 'partition()' method recursively.

Performance Analysis ->

        I tested the performance of this Quick Sort for the following input sizes and this is the performance -

        Input Size                      Performance
        10^1                            0 ms
        10^2                            0 ms
        10^3                            ~2 ms
        10^4                            ~5 ms
        10^5                            ~18 ms
        10^6                            ~60 ms
        10^7                            ~300 ms

Data Structures ->

        I have used the 'Double' Array List data type to store the input array. This is the only class-wide variable used.
        All the other variables are method-specific.

        I have made use of Inner Classes to make the program well-structured and to enhance the moularity.

Running the program ->

        1. Open the Command Prompt(Windows) OR Terminal(Mac/Linux/UNIX) and navigate to the folder where the 'Qsort.java' file exists.
        2. Make sure that the input files (input1.txt, input2.txt files are placed in the folder; otherwise the program will throw an error.
        3. Run the following commands ->
            a. javac Qsort.java
            b. java Qsort input1.txt input2.txt
        4. After the program is executed, a file called 'answers.txt' will be created in that folder. This file contains the sorted elements.
        5. Note that the results of the sorting are appended to the 'answers.txt' file. So in case we want a new file, then we must delete/rename the previous answer.txt file.

Program Limitations ->

        1.  For input sizes more than 10^7, 'OUT OF SPACE ERROR' occurs for Java, as the heap memory runs out of space.
            I tried setting the Heap Size to 8GB (-Xmx8G parameter), but the even that was not sufficient.
        2.  The program will run ONLY if there are integer and decimal inputs. For string inputs, an error is thrown.
        3.  The input array should be stored in a SEMICOLON (;) separated format. All other formats will fail.
        4.  If the value of the inputs is more than the max limit of the DOUBLE data type, then we will get undesired outputs.
*/
