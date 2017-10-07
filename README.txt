Adhish Thite | athite@uncc.edu | UNCC ID: 801023459

QUICK SORT

This program is an implementation of the Quick Sort algorithm written in Java.

Compiler used -> JAVAC
Platform ->  macOS High Sierra 10.13
IDE -> IDEA IntelliJ Community 2017.2

Program Design ->

    1. The start of program begins in the 'main' function where the input file name is captured from the command line. There can be more than 1 input file for this program.
    2. The control then passes to the 'getArrayFromFile()' where the file name is passed as a parameter. This method then reads each line of the file, and stores the individual elements in an array.
    3. The control is transferred to the 'doQuickSort()' method which instantiates the 'Quick Sort' inner class.
    4. The inner class 'Quick Sort' performs the Quick Sort on the input array and returns the details of Sorting (Sorted Array, Performance, Filename).
    5. Steps 3 & 4 are repeated depending upon the number of input files.
    6. The code handles exceptions ? IO Exception while writing to output file and general exceptions while reading the input file.

Algorithm Working ->

    1. The algorithm is based on the Divide and Conquer method.
    2. The 'partition()' method implements the partition of the input array in such a way that at any point of time,
       the elements lesser than the pivot are stored to the left of the pivot and the elements greater to the pivot are
       stored to the right. This is the loop invariant for Quick Sort.
    3. The 'quicksort()' divides the array into subarrays and transfers them to the 'partition()' method recursively.

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