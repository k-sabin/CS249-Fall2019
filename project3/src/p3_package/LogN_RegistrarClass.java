package p3_package;

public class LogN_RegistrarClass
{
    /**
     * Constant default capacity
     */
    private final int DEFAULT_CAPACITY = 10;
    /**
     * Constant used if item not found in array
     */
    private final int NOT_FOUND = -1;
    /**
     * Private array holding student data
     */
    private StudentClass[] studentArr;
    /**
     * Private capacity and size data
     */
    private int capacity;
    /**
     * Private capacity and size data
     */
    private int size;

    /**
     * Default constructor
     */
    public LogN_RegistrarClass()
    {
        capacity = DEFAULT_CAPACITY;
        size = 0;
        studentArr = new StudentClass[capacity];
    }

    /**
     * Initialization Constructor
     * @param initialCapacity - integer value to set class initial capacity
     */
    public LogN_RegistrarClass(int initialCapacity)
    {
        this.capacity = initialCapacity;
        size = 0;
        studentArr = new StudentClass[capacity];
    }

    /**
     * Copy constructor
     * @param copied - RegistrarClass object to be copied
     */
    public LogN_RegistrarClass(LogN_RegistrarClass copied)
    {
        int index;
        this.capacity = copied.capacity;
        this.size = copied.size;
        this.studentArr = new StudentClass[copied.capacity];
        copyArrayData(this.studentArr, copied.studentArr, 0, size);
    }
    /**
     * Adds a StudentClass item to the list
     * <p>Note: Overloaded method</p>
     * @param newStudent - StudentClass object o be added to array
     * @return Boolean result of item addition to array
     */
    public boolean addStudent(StudentClass newStudent)
    {
        int indexForAddition = size;
        //checking if student exists in array. if null, doesn't exist
        StudentClass checkForStudent = findStudent(newStudent);
        //make sure array is big enough for new student
        checkForResize();
        //if the student exists, return null
        if(checkForStudent != null)
        {
            return false;
        }
        studentArr[indexForAddition] = newStudent;
        size++;
        return true;
    }

    /**
     * Creates a StudentClass item, then adds to list using other method
     * <p>Uses anonymous StudentClass instantiation in call to other method;
     * one line of code</p>
     * @param stdName - String name of student
     * @param stdID - integer student ID of student
     * @param stdGender - character gender of student
     * @param stdGPA - double GPA of student
     * @return Boolean result of adding student
     */

    public boolean addStudent(java.lang.String stdName, int stdID,
                              char stdGender, double stdGPA)
    {
        return addStudent(new StudentClass(stdName, stdID,
                                stdGender,stdGPA));
    }

    /**
     * Checks array capacity to verify there is room to accept new data;
     * if array is at capacity, resizes array and copies data as needed to
     * double capacity of the array
     */

    private void checkForResize()
    {
        if(size==capacity)
        {
            capacity*=2;
            StudentClass[] temp = new StudentClass[capacity];
            copyArrayData(temp,studentArr,0,size - 1);
            studentArr = temp;
        }
    }

    /**
     * Copies student list from one array to other
     * <p>Note: Must create new StudentClass object to assign to each
     * element to destination to eliminate aliasing</p>
     * @param dest - StudentClass array to which data is copied
     * @param source - StudentClass array from which data is copied
     * @param lowIndex integer index at which to start copying from source
     *                 array, inclusive
     * @param highIndex integer index at which to end copying from source array,
     *                 inclusive
     */

    private void copyArrayData(StudentClass[] dest, StudentClass[] source,
                                                int lowIndex, int highIndex)
    {
        int index;
        //for each item in the source between the low and high index, copy to
        //                                                          dest
        //indicies are INCLUSIVE

        for(index = lowIndex; index <= highIndex; index++)
        {
            dest[index] = new StudentClass(source[index]);
        }
    }

    /**
     * Find student in array, returns data
     * <p>Note: Uses findStudentIndex</p>
     * @param student - StudentClass object to be found
     * @return object found, or null if not found
     */

    public StudentClass findStudent(StudentClass student)
    {
        if(findStudentIndex(student) != NOT_FOUND)
        {
            return studentArr[findStudentIndex(student)];
        }
        return null;
    }

    /**
     * Finds student's index in array, returns index, or returns NOT_FOUND
     * if the item is not in the array
     * <p>Note: Must use appropriate comparison method for class</p>
     * @param student - StudentClass object to be found
     * @return index of StudentClass object, or NOT_FOUND
     */

    public int findStudentIndex(StudentClass student)
    {
        int index;

        for (index = 0; index < size; index++)
        {
            // using compareTo method in student class, if the student
                                    // matches return index
            if(studentArr[index].compareTo(student) == 0)
            {
                return index;
            }
        }
        //assume student is not found, return constant
        return NOT_FOUND;
    }

    /**
     * Optional method, local array dump for diagnostics
     */

    public void diagnosticArrayDump()
    {
        int index;
        for(index = 0; index < size; index++)
        {
            StudentClass studentToPrint = studentArr[index];
            System.out.println(studentToPrint.toString());
        }
    }

    /**
     * Removes student from array, shifts elements down to keep array contiguous
     * <p>Note: Uses findStudentIndex/p>
     * @param student - StudentClass object to be removed
     * @return StudentClass object that was removed, or null if not found
     */
    public StudentClass removeStudent(StudentClass student)
    {
        //temporary storage for moving students inside loop
        StudentClass tempStudent;
        //loop index for adjusting array
        int loopIndex;
        //finding student to remove or set to null
        StudentClass studentToRemove = findStudent(student);
        //index of removed student
        int studentIndexToRemove = findStudentIndex(student);

        //if student is found, start with index and shift items back one until
        // end of array
        if(studentIndexToRemove != NOT_FOUND)
        {
            for (loopIndex = studentIndexToRemove; loopIndex <= size;
                                                        loopIndex++)
            {
                swapValues(studentArr, loopIndex, loopIndex + 1);
            }
            size--;
        }
        //return student removed or null
        return studentToRemove;
    }

    /**
     * Merges StudentClass values brought in between a low and high index
     * segment (inclusive) of an array
     * <p>Creates local temporary array for the exact size needed to
     * conduct merge</p>
     * @param workingArray lowest index of array segment to be managed
     * @param lowIndex middle index of array segment to be managed
     * @param middleIndex high index of array segment to be managed
     * @param highIndex StudentClass array to be managed by method
     */

    private void runMerge(StudentClass[] workingArray, int lowIndex,
                                                int middleIndex, int highIndex)
    {
        //determine exact size to make array
        int sizeOfTemp = (highIndex - lowIndex) +1;
        //create new array
        StudentClass[] temp = new StudentClass[sizeOfTemp];
        //indices for comparing in the while loop
        int leftHalfIndex = lowIndex;
        int rightHalfIndex = middleIndex+1;

        //index for accessing workingArray
        int transferIndex = lowIndex;

        //index for accessing temp array
        int index = 0;

        while (leftHalfIndex <= middleIndex &&
                        rightHalfIndex <= highIndex)
        {
            //if leftHalfIndex <= rightHalfIndex, put left half in the temp arr
            if (workingArray[leftHalfIndex].compareTo(
                                    workingArray[rightHalfIndex]) <= 0)
            {
                temp[index] = workingArray[leftHalfIndex];
                leftHalfIndex++;
            }
            //else, put right half index into temp array
            else
            {
                temp[index] = workingArray[rightHalfIndex];
                rightHalfIndex++;
            }
            index++;
        }
        //once stopping the comparison, copy the remainder of the items into
        //the temp array
        while(leftHalfIndex <= middleIndex)
        {
            temp[index] = workingArray[leftHalfIndex];
            leftHalfIndex++;
            index++;
        }
        while(rightHalfIndex <= highIndex)
        {
            temp[index] = workingArray[rightHalfIndex];
            rightHalfIndex++;
            index++;
        }
        //place data from temporary array to workingArray (copy)
        //copyArrayData(workingArray,temp,lowIndex,highIndex);
        for(index = 0; index < sizeOfTemp; index++)
        {
            workingArray[transferIndex] = temp[index];
            transferIndex++;
        }
    }

    /**
     * StudentClass data sorted using merge sort algorithm
     * <p>Note: Calls runMergeSortHelper with lower and upper indices of array
     * to be sorted </p>
     * <p>Note: Creates new StudentClass array, sorts contents of array,
     * and returns the sorted result; does not modify (this) object student
     * array</p>
     * @return StudentClass array containing sorted data
     */

    public StudentClass[] runMergeSort()
    {
        StudentClass[] copy = new StudentClass[size];
        copyArrayData(copy,studentArr,0,size - 1);

        runMergeSortHelper(copy,0,size -1);

        return copy;
    }

    /**
     * Merge sort helper, recursively breaks given array segment down to smaller
     * segments between lowIndex and highIndex (inclusive),
     * then sorts data using merge sort method
     * @param workingArray String array holding unsorted values
     * @param lowIndex lowest index of array segment to be managed;
     *                 this varies as the segments are broken down recursively
     * @param highIndex highest index of array segment to be managed;
     *                  this varies as the segments are broken down recursively
     */

    private void runMergeSortHelper(StudentClass[] workingArray, int lowIndex,
                                                                int highIndex)
    {
        if(lowIndex < highIndex)
        {
            int midPoint = (lowIndex + highIndex) /2;
            //left half of array
            runMergeSortHelper(workingArray,lowIndex,midPoint);
            //right half of array
            runMergeSortHelper(workingArray,midPoint +1, highIndex);
            //merge
            runMerge(workingArray,lowIndex,midPoint,highIndex);
        }
    }

    /**
     * Partitions array using the first value as the pivot; when this method
     * is complete the partition value is in the correct location in the array
     * @param workingArray StudentClass array holding array to be managed by
     *                     method
     * @param lowIndex low index of array segment to be partitioned
     * @param highIndex high index of array segment to be partitioned
     * @return integer index of partition pivot
     */

    private int runPartition(StudentClass[] workingArray, int lowIndex,
                                                                int highIndex)
    {
        //pivot is the first item in the array
        StudentClass pivot = workingArray[lowIndex];

        int fenceIndex = highIndex +1;
        //index for loop
        int currentIndex;

        for(currentIndex = highIndex; currentIndex >= lowIndex + 1;
                                            currentIndex--)
        {
            //if arr[currentIndex] < pivot, swap with fence
            if(workingArray[currentIndex].compareTo(pivot) > 0)
            {
                fenceIndex--; //increase before swap
                swapValues(workingArray, fenceIndex, currentIndex);
            }
        }
        //put pivot into correct place
        swapValues(workingArray,fenceIndex -1,lowIndex);

        return fenceIndex -1;
    }

    /**
     * Studentclass data sorted using quick sort algorithm
     * <p>Note: Calls runQuickSortHelper with lower and upper indices of
     * array to be sorted </p>
     * <p>Note: Creates new StudentClass array, sorts contents of array,
     * and returns the sorted result; does not modify (this) object
     * student array</p>
     * @return StudentClass array containing sorted data
     */

    public StudentClass[] runQuickSort()
    {
        //copies array data into a copy for sorting
        StudentClass[] copy = new StudentClass[size];
        copyArrayData(copy,studentArr,0,size -1);

        runQuickSortHelper(copy,0,size-1);

        return copy;
    }

    /**
     * Helper method run with parameters that support recursive access
     * @param workingArray StudentClass array holding unsorted values
     * @param lowIndex low index of the segment of the array to be processed
     * @param highIndex high index of the segment of the array to be processed
     */

    private void runQuickSortHelper(StudentClass[] workingArray, int lowIndex,
                                                                int highIndex)
    {
        if(lowIndex < highIndex)
        {
            int partition = runPartition(workingArray,lowIndex,highIndex);

            //partition is now in proper place, sort the rest
            runQuickSortHelper(workingArray,lowIndex,partition -1);
            runQuickSortHelper(workingArray,partition +1, highIndex);
        }
    }

    /**
     * Swaps values within given array
     * @param workingArray - StudentClass array used for swapping
     * @param indexOne - integer index for one of the two items to be swapped
     * @param indexOther --integer index for the other of the two items to
     *                                                      be swapped
     */
    private void swapValues(StudentClass[] workingArray, int indexOne,
                            int indexOther)
    {
        StudentClass tempStorage = workingArray[indexOne];
        workingArray[indexOne] = workingArray[indexOther];
        workingArray[indexOther] = tempStorage;
    }

}
