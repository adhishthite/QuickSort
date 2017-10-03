import java.util.Arrays;

/**
 * Implementing 'Quick Sort' Algorithm
 *
 * Adhish Thite | athite@uncc.edu | UNCC Student ID : 801023459
 * Algorithms and Data Structures - Programming Assignment 2
 *
 **/

public class QuickSort {
    private static Double[] arrayToSort;
    public SortData sd;

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

    public class SortData{
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