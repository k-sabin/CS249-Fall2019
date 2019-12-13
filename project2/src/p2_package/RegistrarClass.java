package p2_package;

public class RegistrarClass
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
    public RegistrarClass()
    {
        capacity = DEFAULT_CAPACITY;
        size = 0;
        studentArr = new StudentClass[capacity];
    }

    /**
     * Initialization Constructor
     * @param initialCapacity - integer value to set class initial capacity
     */
    public RegistrarClass(int initialCapacity)
    {
        this.capacity = initialCapacity;
        size = 0;
        studentArr = new StudentClass[capacity];
    }

    /**
     * Copy constructor
     * @param copied - RegistrarClass object to be copied
     */
    public RegistrarClass(RegistrarClass copied)
    {
        int index;
        this.capacity = copied.capacity;
        this.size = copied.size;
        this.studentArr = new StudentClass[copied.capacity];
        copyArrayData(this.studentArr, copied.studentArr);
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
        //if student exists in array OR the size of the class is equal to
                                            //its capacity, return false
        if(checkForStudent != null || size == capacity)
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
     * Copies student list from one array to other
     * <p>Note: Must create new StudentClass object to assign to each
     * element to destination to eliminate aliasing</p>
     * @param dest - StudentClass array to which data is copied
     * @param source - StudentClass array from which data is copied
     */

    private void copyArrayData(StudentClass[] dest, StudentClass[] source)
    {
        int index;
        //for each item in the source, recreate them in the destination array
        for(index = 0; index < source.length; index++)
        {
            if(source[index] != null)
            {
                dest[index] = new StudentClass(source[index]);
            }
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
     * Sorts elements using the bubble sort algorithm
     * <p>Note: Creates new StudentClass array, sorts contents of array, and
     * returns the sorted array; does not modify (this) object student array</p>
     * @return new StudentClass array with sorted items
     */

    public StudentClass[] runBubbleSort()
    {
        //copying array
        StudentClass[] copy = new StudentClass[capacity];
        copyArrayData(copy,studentArr);
        int outerArrayIndex;
        int innerArrayIndex;

        for(outerArrayIndex = 0; outerArrayIndex < size -1; outerArrayIndex++)
        {
            for(innerArrayIndex = 0; innerArrayIndex < size -1;
                                        innerArrayIndex++)
            {
                if(copy[innerArrayIndex].
                        compareTo(copy[innerArrayIndex + 1]) > 0)
                {
                    swapValues(copy, innerArrayIndex,
                            innerArrayIndex +1);
                }
            }
        }
        return copy;
    }

    /**
     * Sorts elements using the insertion sort algorithm
     * <p>Note: Creates new StudentClass array, sorts contents of array, and
     * returns the sorted array; does not modify (this) object student array</p>
     * @return new StudentClass array with sorted items
     */

    public StudentClass[] runInsertionSort()
    {
        //copying array
        StudentClass[] copy = new StudentClass[capacity];
        copyArrayData(copy,studentArr);
        int outerArrayIndex;
        int innerArrayIndex;

        //going through array starting at 1
        for(outerArrayIndex = 1; outerArrayIndex < size; outerArrayIndex++)
        {
            //inner array index starts at 0 and will decrement in the
                                                //while loop
            innerArrayIndex = outerArrayIndex - 1;

            //while inner is greater than 0 and the next item in the array
                            //is less than the current index, swap
            while(innerArrayIndex >= 0)
            {
                if(copy[innerArrayIndex].compareTo(
                                copy[innerArrayIndex +1]) > 0)
                {
                    swapValues(copy,innerArrayIndex, innerArrayIndex+1);
                }
                innerArrayIndex--;
            }
        }
        return copy;
    }

    /**
     * Sorts elements using the selection sort algorithm
     * <p>Note: Creates new StudentClass array, sorts contents of array, and
     * returns the sorted array; does not modify (this) object student array</p>
     * @return new StudentClass array with sorted items
     */

    public StudentClass[] runSelectionSort()
    {
        //copying array
        StudentClass[] copy = new StudentClass[capacity];
        copyArrayData(copy,studentArr);
        //variable instantiation for loops
        int outerArrayIndex;
        int innerArrayIndex;
        int smallestIndex;

        for(outerArrayIndex = 0; outerArrayIndex < size; outerArrayIndex++)
        {
            //smallest index is set to current index until set in inner loop
            smallestIndex = outerArrayIndex;
            for(innerArrayIndex = outerArrayIndex; innerArrayIndex < size;
                                        innerArrayIndex++)
            {
                //compareTo will return a number greater than 0 if smallestIndex
                                //is greater than inner array index
                if(copy[smallestIndex].compareTo(
                                            copy[innerArrayIndex]) > 0)
                {
                    smallestIndex = innerArrayIndex;
                }
            }
            swapValues(copy, smallestIndex, outerArrayIndex);
        }
        return copy;
    }

    /**
     * Sorts elements using the shell sort algorithm
     * <p>Note: Creates new StudentClass array, sorts contents of array, and
     * returns the sorted array; does not modify (this) object student array</p>
     * @return new StudentClass array with sorted items
     */

    public StudentClass[] runShellSort()
    {
        //copying array
        StudentClass[] copy = new StudentClass[capacity];
        copyArrayData(copy,studentArr);
        int innerArrayIndex;
        //integer used to mark the last index compared

        //gap for the outer array looping and inner array index manipulation
        int gap;

        //outer array where the gap starts at n/2
        //reduced by half every iteration (floored)
        for(gap = size/2; gap > 0; gap /= 2)
        {
            //gap can be used as an addition modifier, but NOT an index itself
            //setting it as an index will complicate things later
            for(innerArrayIndex=0; innerArrayIndex+gap < size;
                                                        innerArrayIndex++)
            {
                if(copy[innerArrayIndex].compareTo(
                        copy[innerArrayIndex+gap]) > 0)
                {
                    swapValues(copy,innerArrayIndex,innerArrayIndex+gap);
                }
            }
            //checks the first index and first index + gap for last swap
            innerArrayIndex=0;
            if(copy[innerArrayIndex].compareTo(
                    copy[innerArrayIndex+gap]) > 0)
            {
                swapValues(copy,innerArrayIndex,innerArrayIndex+gap);
            }
        }
        return copy;
    }

    /**
     * Swaps values within given array
     * @param stdArray - StudentClass array used for swapping
     * @param indexOne - integer index for one of the two items to be swapped
     * @param indexOther --integer index for the other of the two items to
     *                                                      be swapped
     */
    private void swapValues(StudentClass[] stdArray, int indexOne,
                            int indexOther)
    {
        StudentClass tempStorage = stdArray[indexOne];
        stdArray[indexOne] = stdArray[indexOther];
        stdArray[indexOther] = tempStorage;
    }

}
