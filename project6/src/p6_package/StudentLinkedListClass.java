package p6_package;

public class StudentLinkedListClass 
{
    /**
     * Member Data
     */
    private StudentClass headRef;
    
    /**
     * For N not found in search
     */
    public static final int NOT_FOUND = -1;
    
    /**
     * Default constructor, initialized linked list
     */
    public StudentLinkedListClass()
    {
        headRef = null;
    }
    
    /**
     * Copy Constructor, creates new nodes to eliminate aliasing
     */
    public StudentLinkedListClass(StudentLinkedListClass copied)
    {
        headRef = new StudentClass(copied.headRef);
        StudentClass copyWkgRef = copied.headRef;
        StudentClass wkgRef = headRef;
        while(copyWkgRef.nextRef != null)
        {
            wkgRef.nextRef = new StudentClass(copyWkgRef.nextRef);
            wkgRef = wkgRef.nextRef;
            copyWkgRef = copyWkgRef.nextRef;
        }
        wkgRef.nextRef = null;
    }
    
    /**
     * Sets data at end of list
     * <p>
     * Note: Uses appendDataAtEnd_Helper
     * @param student StudentClass object to be appended to list
     */
    
    public void appendDataAtEnd(StudentClass student)
    {
        headRef = appendDataAtEnd_Helper(headRef, student);
    }
    
    /**
     * Recursive helper method that appends data to end of list
     * @param wkgRef StudentClass reference for linking nodes
     * @param student StudentClass data to be added to the list
     * @return StudentClass link to calling method
     */
    
    private StudentClass appendDataAtEnd_Helper(StudentClass wkgRef, 
                                                StudentClass student)
    {
        if(wkgRef == null)
        {
            wkgRef = student;
            student.nextRef = null;
            return student;
        }
        else
        {
            wkgRef.nextRef = appendDataAtEnd_Helper(wkgRef.nextRef, student);
            return wkgRef;
        }
    }
    
    /**
     * Clears linked list of all valid values by setting linked list 
     * head to null
     */
    
    public void clear()
    {
        headRef = null;
    }
    
    /**
     * Displays student list
     */
    
    public void displayList()
    {
        StudentClass wkgRef = headRef;
        while(wkgRef != null)
        {
            System.out.println(wkgRef.toString());
            wkgRef = wkgRef.nextRef;
        }
    }
    
    /**
     * Gets number of student if found in list
     * @param student StudentClass object for finding N
     * @return integer N of the StudentClass object, or NOT_FOUND if not in 
     * list
     */
    
    public int findStudentNumber(StudentClass student)
    {
        int counter;
        StudentClass studentToCheck = headRef;
        for(counter = 1; counter <= getCurrentSize(); counter++)
        {
            if(studentToCheck == student)
            {
                return counter;
            }
            studentToCheck = studentToCheck.nextRef;
        }
        return NOT_FOUND;
    }
    
    /**
     * Description: Gets current size of linked list
     * <p>
     * Note: Uses getCurrentSize_Helper
     * @return integer size of linked list
     */
    
    public int getCurrentSize()
    {
        return getCurrentSize_Helper(headRef);
    }
    
    /**
     * Recursive helper method for finding list size
     * @param wkgRef StudentClass reference for recursion management
     * @return integer size at each recursive level
     */
    
    private int getCurrentSize_Helper(StudentClass wkgRef)
    {
        if(wkgRef == null)
        {
            return 0;
        }
        else
        {
            return 1 + getCurrentSize_Helper(wkgRef.nextRef);
        }
    }
    
    /**
     * Acquires the Nth item in the list, starting with N = 1
     * @param N_value integer N value for acessing student
     * @return StudentClass value at element or null if attempt to acquire data
     * out of bounds
     */
    
    public StudentClass getNthStudent(int N_value)
    {
        int index;
        StudentClass currentIndex = headRef;
        for(index = 1; index <= getCurrentSize(); index++)
        {
            if(index == N_value)
            {
                return currentIndex;
            }
            currentIndex = currentIndex.nextRef;
        }
        return null;
    }
    
    /**
     * Sets data at beginning of list; moves all subsequent data up by 
     * one element
     * <p>
     * Note: No failure mode; data will be inserted at beginning no matter what
     * the size of the linked list is
     * @param student StudentClass object to set at beginning
     */
    
    public void insertDataAtBeginning(StudentClass student)
    {
        student.nextRef = headRef;
        headRef = student;
    }
    
    /**
     * Description: Links data into list at Nth position, where N starts at 1
     * <p>
     * Note: Allows item to be appended to end of list
     * @param NthPos integer value to indicate insertion location
     * @param student StudentClass object to be inserted at Nth position 
     * in list
     * @return
     */
    
    public boolean insertDataAtNthPosition(int NthPos, StudentClass student)
    {
        int loopIndex;
        StudentClass temp;
        StudentClass prevRef = null;
        StudentClass wkgRef = headRef;
        if(NthPos > getCurrentSize() || isEmpty())
        {
            return false;
        }
        
        for(loopIndex = 1; loopIndex < NthPos; loopIndex++)
        {
            prevRef = wkgRef;
            wkgRef = wkgRef.nextRef;
            
        }
        temp = wkgRef;
        wkgRef = student;
        prevRef.nextRef = student;
        student.nextRef = temp;
        return true;
    }
    
    /**
     * Checks for empty list
     * @return Boolean result of test for empty
     */
    
    public boolean isEmpty()
    {
        if(getCurrentSize() == 0)
        {
            return true;
        }
        return false;
    }
    
    /**
     * Description: Removes Nth item from linked list
     * @param numberN integer number of element value to be removed,
     * starts at N = 1
     * @return removed StudentClass value if successful, null if not
     */
    
    public StudentClass removeNthStudent(int numberN)
    {
        StudentClass wkgRef = headRef;
        StudentClass prevRef = null;
        StudentClass removed = null;
        int index;
        for(index = 1; index <= getCurrentSize(); index++)
        {
            if(index == numberN)
            {
                removed = wkgRef;
                wkgRef = wkgRef.nextRef;
                prevRef.nextRef = wkgRef;
                return removed;
            }
            prevRef = wkgRef;
            wkgRef = wkgRef.nextRef;
        }
        return null;
        
    }
    
    /**
     * Description: Replaces item in linked list at specified Nth position,
     * where N starts at 1
     * @param NthPos integer value to indicate position of value to be replaced
     * @param student StudentClass object to be inserted at Nth position
     * @return Boolean success if replaced, or failure if incorrect N was used
     */
    
    public boolean replaceDataAtNthPosition(int NthPos, StudentClass student)
    {
        int index = 1;
        StudentClass wkgRef = headRef;
        if(NthPos > getCurrentSize() || isEmpty())
        {
            return false;
        }
        while(index < getCurrentSize())
        {
            //replace if the index is one less
            //replace by setting next ref of wkgRef to student, but not
            //before setting nextRef of student to the next next ref
            if(index == NthPos -1)
            {
                student.nextRef = wkgRef.nextRef.nextRef;
                wkgRef.nextRef = student;
                return true;
            }
            //else count up
            else
            {
                wkgRef = wkgRef.nextRef;
                index++;
            }
        }
        return false;
    }
}
