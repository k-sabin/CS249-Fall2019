package p5_package;

public class StudentQueueClass extends StudentListClass
{
    /**
     * Constant for displaying spaces
     */ 
    private static final String SPACE = " ";
    
    /**
     * Default Constructor
     */
    public StudentQueueClass()
    {
        super();
    }
    
    /**
     * Initialization constructor
     * @param integer value to specify initial capacity
     */
    public StudentQueueClass(int capacity)
    {
        super(capacity);
    }
    
    /**
     * Copy constructor
     * @param StudentQueueClass object to be copied
     */
    public StudentQueueClass(StudentQueueClass copied)
    {
        super(copied);
    }
    
    /**
     * Clears queue using parent's operation
     */
    
    public void clearQueue()
    {
        super.clear();
    }
    
    /**
     * Dequeues from end of list, front of queue
     * @return StudentClass object if available; null otherwise
     */
    public StudentClass dequeue()
    {
        return super.removeNthStudent(super.getCurrentSize());
    }
    
    /**
     * Displays queue from tail to head
     */
    public void displayQueue()
    {
        int index;
        System.out.println("Tail of Queue");
        for(index = 1; index <= super.getCurrentSize(); index++)
        {
            displaySpaces(index*2);
            System.out.println(super.getNthStudent(index).toString());
        }
        displaySpaces(index*2);
        System.out.println("Head of Queue");
    }
    
    /**
     * Recursive method displays spaces for displayQueue
     * @param integer value specifying number of spaces to display
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
     * Enqueues to beginning of list, tail of queue
     * @param StudentClass object to be enqueued
     */
    public void enqueue(StudentClass student)
    {
        super.insertDataAtBeginning(student);
    }
    
    /**
     * Reports queue empty using parent's operation
     * @Override isEmpty in class StudentListClass
     * @return Boolean result of empty test
     */
    @Override
    public boolean isEmpty()
    {
        if(super.getCurrentSize() != 0)
        {
            return false;
        }
        return true;
    }
    
    /**
     * Peek at front of queue, no state change
     * @return StudentClass object if available; null otherwise
     */
    public StudentClass peekFront()
    {
        return super.getNthStudent(super.getCurrentSize());
    }
}

