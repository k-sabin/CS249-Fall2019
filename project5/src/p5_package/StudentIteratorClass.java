package p5_package;

public class StudentIteratorClass 
{
    /**
     * Current index of the iterator
     */
    private int currentIndex;
    /**
     * StudentListClass data used by this class
     */
    private StudentListClass iteratorList;
    /**
     * Constant character for display
     */
    private final char LEFT_BRACKET = 91;
    /**
     * Constant character for display
     */
    private final char RIGHT_BRACKET = 93;
    /**
     * Constant character for display
     */
    private final char SPACE = 32;
    /**
     * Default constructor for StudentIteratorClass
     */
    public StudentIteratorClass()
    {
        currentIndex = 0;
        iteratorList = new StudentListClass();
    }
    /**
     * Initialization constructor for StudentIteratorClass
     */
    public StudentIteratorClass(int initialCapacity)
    {
        currentIndex = 0;
        iteratorList = new StudentListClass(initialCapacity);
    }
    /**
     * Copy constructor for StudentIteratorClass
     */
    public StudentIteratorClass(StudentIteratorClass copied)
    {
        currentIndex = 0;
        iteratorList = new StudentListClass(copied.iteratorList);
    }
    
    /**
     * Clears array
     */
    public void clear()
    {
        iteratorList.clear();
        currentIndex = 0;
    }
    
    /**
     * Recursive method displays spaces for displayQueue
     * @param numspaces integer value specifying number of spaces to display
     */
    public void displaySpaces(int numSpaces)
    {
        if(numSpaces > 0)
        {
            displaySpaces(numSpaces -1);
        }
        System.out.print(SPACE);
    }
    
    /**
     * Gets value at iterator cursor location
     * @return StudentClass object returned; null if not found
     */
    public StudentClass getAtCurrent()
    {
        return iteratorList.getNthStudent(currentIndex);
    }
    
    /**
     * Inserts new value after value at iterator cursor
     * Note: Current value must remain the same after data set, 
     * unless there is only one item in the set
     * @param newValue Student Object to be inserted in list
     * @return Boolean result of action; true if successful, false otherwise
     */
    public boolean insertAfterCurrent(StudentClass newValue)
    {
        if(isEmpty())
        {
            iteratorList.insertDataAtNthPosition(currentIndex, newValue);
            currentIndex = 1;
            return true;
        }
        return iteratorList.insertDataAtNthPosition(currentIndex + 1, 
                newValue);
    }
    
    /**
     * Inserts new before value at iterator cursor 
     * Note: Current value must remain the same after data set, 
     * unless there is only one item in the set
     * @param StudentClass object to be inserted into the list
     * @return Boolean result of action; true if successful, false otherwise
     */
    public boolean insertBeforeCurrent(StudentClass newValue)
    {
        if(isEmpty())
        {
            iteratorList.insertDataAtNthPosition(currentIndex, newValue);
            currentIndex = 1;
            return true;
        }
        return iteratorList.insertDataAtNthPosition(currentIndex -1, newValue);
    }
    /**
     * Reports if iterator cursor is at beginning 
     * If list is empty, cursor is not at beginning
     * @return Boolean result of action; true if at beginning, false otherwise
     */
    public boolean isAtBeginning()
    {
        if(isEmpty() || currentIndex > 1)
        {
            return false;
        }
        return true;
    }
    
    /**
     * Reports if iterator cursor is at end 
     * If list is empty, cursor is not at end
     * @return Boolean result of action; true if at end, false otherwise
     */
    public boolean isAtEnd()
    {
        if(isEmpty() || currentIndex < iteratorList.getCurrentSize())
        {
            return false;
        }
        return true;
    }
    
    /**
     * Reports if list is empty
     * @return Boolean result of action; true if empty, false otherwise
     */
    public boolean isEmpty()
    {
        return iteratorList.isEmpty();
    }
    
    /**
     * If possible, moves iterator cursor one position to the right, or next
     * @return Boolean result of action; true if successful, false otherwise
     */
    public boolean moveNext()
    {
        if(currentIndex < iteratorList.getCurrentSize())
        {
            currentIndex++;
            return true;
        }
        return false;
    }
    
    /**
     * If possible, moves iterator cursor one position to the left, or previous
     * @return Boolean result of action; true if successful, false otherwise
     */
    public boolean movePrev()
    {
        if(currentIndex > 1)
        {
            currentIndex--;
            return true;
        }
        return false;
    }
    
    /**
     * Removes and returns a data value from the iterator cursor position 
     * Note: cursor must be located at succeeding element unless last 
     * item removed
     * @param StudentClass object removed from list, or null if not found
     */
    public StudentClass removeAtCurrent()
    {
        if(iteratorList.getCurrentSize() == 1)
        {
            StudentClass studentToReturn = 
                    iteratorList.getNthStudent(currentIndex);
            clear();
            return studentToReturn;
        }
        return iteratorList.removeNthStudent(currentIndex);
    }
    
    /**
     * Replaces value at iterator cursor with new value
     * @param integer value to be inserted in list
     * @return Boolean result of action; true if successful, false otherwise
     */
    public boolean replaceAtCurrent(StudentClass newValue)
    {
        return iteratorList.replaceDataAtNthPosition(currentIndex, newValue);
    }
    
    /**
     * Shows space-delimited list with cursor location indicated
     */
    public void runDiagnosticDisplay()
    {
        System.out.println("Left end of iterator:");
        int index;
        for(index = 1; index <= iteratorList.getCurrentSize(); index ++)
        {
            displaySpaces(index*2);
            if(currentIndex == index)
            {
                System.out.print(LEFT_BRACKET);
            }
            System.out.print(iteratorList.getNthStudent(index).toString());
            if(currentIndex == index)
            {
                System.out.print(RIGHT_BRACKET);
            }
            System.out.println();
        }
        displaySpaces((index*2));
        System.out.println("Right end of iterator");
    }
    
    /**
     * Sets iterator cursor to beginning of list
     * @return Boolean result of action; true if successful, false otherwise
     */
    public boolean setToBeginning()
    {
        if(isEmpty())
        {
            return false;
        }
        currentIndex = 1;
        return true;
    }
    
    /**
     * Sets iterator cursor to the end of the list
     * @return Boolean result of action; true if successful, false otherwise
     */
    public boolean setToEnd()
    {
        if(isEmpty())
        {
            return false;
        }
        currentIndex = iteratorList.getCurrentSize();
        return true;
    }
}
