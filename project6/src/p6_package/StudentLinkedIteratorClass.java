package p6_package;

public class StudentLinkedIteratorClass 
{
    /**
     * Current index of iterator
     */
    private int currentIndex;
    
    /**
     * StudentClassList data used by this class
     */
    private StudentLinkedListClass iteratorList;
    
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
    public StudentLinkedIteratorClass()
    {
        iteratorList = new StudentLinkedListClass();
        currentIndex = 0;
    }
    
    /**
     * Copy constructor for StudentIteratorClass
     */
    
    public StudentLinkedIteratorClass(StudentLinkedIteratorClass copied)
    {
        currentIndex = copied.currentIndex;
        iteratorList = new StudentLinkedListClass(copied.iteratorList);
    }
    
    /**
     * Clears array
     */
    public void clear()
    {
        iteratorList.clear();
    }
    
    /**
     * Recursive method displays spaces for runDiagnosticDisplay
     * @param numSpaces integer value specifying number of spaces to display
     */
    
    private void displaySpaces(int numSpaces)
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
     * <p>
     * Note: Current value must remain the same after data set, unless the 
     * list was empty prior to data set
     * @param newValue StudentClass object to be inserted in list
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
        else if(isAtEnd())
        {
            iteratorList.appendDataAtEnd(newValue);
            return true;
        }
        return iteratorList.insertDataAtNthPosition(currentIndex + 1, 
                newValue);
        
    }
    
    
    /**
     * Inserts new before value at iterator cursor
     * <p>
     * Note: Current value must remain the same after data set, unless the 
     * list was empty prior to data set
     * @param newValue StudentClass object to be inserted in list
     * @return Boolean result of action; true if successful, false otherwise
     */
    
    public boolean insertBeforeCurrent(StudentClass newValue)
    {
        if(isEmpty())
        {
            iteratorList.insertDataAtBeginning(newValue);
            setToBeginning();
            return true;
        }
        else if(isAtBeginning())
        {
            iteratorList.insertDataAtBeginning(newValue);
            currentIndex++;
            return true;
        }
        return iteratorList.insertDataAtNthPosition(currentIndex -1, newValue);
    }
    
    /**
     * Reports if iterator cursor is at beginning
     * <p>
     * If list is empty, cursor is not at beginning
     * @return
     */
    
    public boolean isAtBeginning()
    {
        if(currentIndex == 1)
        {
            return true;
        }
        return false;
    }
    
    /**
     * Reports if iterator cursor is at end
     * <p>
     * If list is empty, cursor is not at end
     * @return
     */
    
    public boolean isAtEnd()
    {
        if(getAtCurrent().nextRef == null)
        {
            return true;
        }
        return false;
            
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
        if(currentIndex >= iteratorList.getCurrentSize())
        {
            return false;
        }
        currentIndex++;
        return true;
    }
    
    /**
     * If possible, moves iterator cursor one position to the left, or previous
     * @return Boolean result of action; true if successful, false otherwise
     */
    
    public boolean movePrev()
    {
        if(currentIndex == 1 || isEmpty())
        {
            return false;
        }
        currentIndex--;
        return true;
    }
    
    /**
     * Removes and returns a data value from the iterator cursor position
     * <p>
     * Note: cursor must be located at succeeding element unless last item 
     * removed
     * @return StudentClass object removed from list, or null if not found
     */
    
    public StudentClass removeAtCurrent()
    {
        StudentClass temp;
        if(iteratorList.getCurrentSize() == 1)
        {
            temp = getAtCurrent();
            clear();
            currentIndex--;
            return temp;
        }
        return iteratorList.removeNthStudent(currentIndex);
        
    }
    
    /**
     * Replaces value at iterator cursor with new value
     * @param newValue StudentClass object to be inserted in list
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
     * Sets iterator cursor to end of the list
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
