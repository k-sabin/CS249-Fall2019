package p5_package;

public class StudentListClass 
{
    /**
     * Member data
     */
    private int arrayCapacity;
    /**
     * Member Data
     */
    private int arraySize;
    /**
     * Default constant capacity
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * For N not found in search
     */
    static final int NOT_FOUND = -1;
    /**
     * Member - StudentClass array
     */
    private StudentClass[] studentArray;
    
    /**
     * Default constructor, initializes array to default capacity
     */
    public StudentListClass()
    {
        arrayCapacity = DEFAULT_CAPACITY;
        arraySize = 0;
        studentArray = new StudentClass[arrayCapacity];
    }
    /**
     * Initializing constructor, initializes array to specified capacity
     */
    public StudentListClass(int capacity)
    {
        arrayCapacity = capacity;
        arraySize = 0;
        studentArray = new StudentClass[arrayCapacity];
    }
    /**
     * Copy constructor, initializes array to size and capacity of copied 
     * array, then copies only the elements up to the given size
     */
    public StudentListClass(StudentListClass copied)
    {
        int copyIndex;
        arrayCapacity = copied.arrayCapacity;
        arraySize = 0;
        studentArray = new StudentClass[arrayCapacity];
        
        for(copyIndex = 0; copyIndex <= copied.arraySize; 
                copyIndex++)
        {
            studentArray[copyIndex] = new 
                    StudentClass(copied.studentArray[copyIndex]);
            arraySize++;
        }
    }
    
    /**
     * Sets data at end of list
     * Note: Uses setAtNthPosisition
     * @param StudentClass object to be appended to list
     */
    public void appendDataAtEnd(StudentClass student)
    {
        checkForResize();
        studentArray[arraySize] = student;
        arraySize++;
    }
    
    /**
     * Description: Checks for need to resize; if this is necessary, 
     * creates new array with double the original capacity, loads data 
     * from original array to new one, then sets studentArray to new array
     */
    
    private void checkForResize()
    {
        if(arraySize == arrayCapacity)
        {
            int copyIndex;
            arrayCapacity *=2;
            StudentClass[] temp = new StudentClass[arrayCapacity];
            for(copyIndex = 0; copyIndex <= arraySize; copyIndex++)
            {
                temp[copyIndex] = studentArray[copyIndex];
            }
            studentArray = temp;
        }
    }
    
    /**
     * Clears array of all valid values by setting array size to zero, 
     * values remain in array but are not accessible
     */
    public void clear()
    {
        arraySize = 0;
    }
    
    /**
     * Displays student list
     */
    public void displayList()
    {
        int index;
        for(index = 0; index < arraySize; index++)
        {
            System.out.println(studentArray[index].toString());
        }
    }
    /**
     * Gets number of student if found in list
     * @param StudentClass object for finding N
     * @return integer N of the StudentClass object, or NOT_FOUND 
     * if not in list
     */
    
    public int findStudentNumber(StudentClass student)
    {
        int loopIndex;
        for(loopIndex = 0; loopIndex <= arraySize; loopIndex++)
        {
            if(studentArray[loopIndex] == student)
            {
                return loopIndex +1;
            }
        }
        return NOT_FOUND;
    }
    
    /**
     * Description: Gets current size of array 
     * Note: size of array indicates number of valid or viable values in 
     * the array
     * @return integer size of array
     */
    
    public int getCurrentSize()
    {
        return arraySize;
    }
    
    /**
     * Acquires the Nth item in the list, starting with N = 1
     * @param integer value to identify Nth student to retrieve
     * @return StudentClass value at element or null if 
     * attempt to acquire data out of bounds
     */
    
    public StudentClass getNthStudent(int N_value)
    {
        //because indexing happens at 0, but referenced by 1, subtracting to 
        //correct for this change
        N_value -= 1;
        if(N_value >= 0 && N_value <= arraySize)
        {
            return studentArray[N_value];
        }
        return null;
    }
    
    /**
     * Sets data at beginning of list; moves all subsequent data up 
     * by one element 
     * Note: No failure mode; data will be set at beginning no 
     * matter what the size of the array is
     */
    public void insertDataAtBeginning(StudentClass student)
    {
        //verify there is enough room to add the data
        checkForResize();
        int index;
        for(index = arraySize; index > 0; index--)
        {
            //shift items up one
            studentArray[index] = studentArray[index -1];
        }
        //by this point, index == 0, but array at pos 1 also includes data
        //from pos 0, overwriting pos 0
        studentArray[index] = student;
        arraySize++;
    }
    
    /**
     * Description: Moves data up one element, then sets item in array at 
     * specified Nth position, where N starts at 1 
     * Note: Allows item to be appended to end of list
     * @param integer value to indicate which position (N) at which to 
     * insert student data
     * @param StudentClass object to be inserted at Nth position 
     * (N - 1 in the array)
     * @return Boolean success if inserted, or failure if incorrect N was used
     */
    public boolean insertDataAtNthPosition(int NthPos, StudentClass student)
    {
        if(NthPos -1 > arraySize)
        {
            return false;
        }
        //if the NthPos is the end of the list, append
        if(NthPos == arraySize)
        {
            appendDataAtEnd(student);
            return true;
        }
        //if the NthPos is at the beginning, insert
        if(NthPos == 1)
        {
            insertDataAtBeginning(student);
            return true;
        }
        checkForResize();
        int index;
        for(index = arraySize; index > NthPos; index--)
        {
            studentArray[index] = studentArray[index -1];
        }
        studentArray[index] = student;
        arraySize++;
        return true;
    }
    
    /**
     * Tests for size of array equal to zero, no valid values stored in array
     * @return boolean result of test for empty
     */
    
    public boolean isEmpty()
    {
        if(arraySize > 0)
        {
            return false;
        }
        return true;
    }
    /**
     * Description: Removes Nth item from array if index 
     * within array size bounds 
     * Note: Each data item from the element immediately above the 
     * remove index to the end of the array is moved down by one element
     * @param integer number of element value to be removed, starts at N = 1
     * @param removed StudentClass value if successful, null if not
     */
    public StudentClass removeNthStudent(int numberN)
    {
        numberN--;
        if(getNthStudent(numberN) != null && numberN <= arraySize)
        {
            StudentClass returnStudent = getNthStudent(numberN);
            int index;
            for(index=numberN -1; index <= arraySize; index++)
            {
                studentArray[index] = studentArray[index+1];
            }
            arraySize--;
            return returnStudent;
        }
        return null;
    }
    
    /**
     * Description: Replaces item in array at specified Nth position, 
     * where N starts at 1
     * @param integer value to indicate Nth position at which to 
     * replace student data
     * @param StudentClass object to be inserted at Nth position
     * (N - 1 in the array)
     * @return Boolean success if inserted, or failure if incorrect N was used
     */
    public boolean replaceDataAtNthPosition(int NthPos, StudentClass student)
    {
        if(NthPos < 1 || NthPos > arraySize)
        {
            return false;
        }
        NthPos -= 1;
        studentArray[NthPos] = student;
        return true;
    }
}
