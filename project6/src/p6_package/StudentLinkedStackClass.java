package p6_package;

public class StudentLinkedStackClass extends StudentLinkedListClass
{
    /**
     * Constant for displaying spaces
     */
    private static String SPACE = " ";
    
    /**
     * Default constructor
     */
    public StudentLinkedStackClass()
    {
        super();
    }
    
    /**
     * Copy constructor
     */
    public StudentLinkedStackClass(StudentLinkedStackClass copied)
    {
        super(copied);
    }
    
    /**
     * Clears stack using parent's operation
     */
    
    public void clearStack()
    {
        clear();
    }
    
    /**
     * Recursive method displays spaces for displayStack
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
     * Displays stack from bottom to top
     */
    
    public void displayStack()
    {
        int index;
        System.out.println("Bottom of Stack");
        for(index = 1; index <= super.getCurrentSize(); index++)
        {
            displaySpaces(index*2);
            System.out.println(super.getNthStudent(index).toString());
        }
        displaySpaces(index*2);
        System.out.println("Top of Stack");
    }
    
    /**
     * Reports stack empty using parent's operation
     * @return Boolean result of empty test
     */
    @Override
    
    public boolean isEmpty()
    {
        return super.isEmpty();
    }
    
    /**
     * Peeks at the top of the stack, no state change
     * @return StudentClass object viewed at the top of the stack
     */
    
    public StudentClass peekTop()
    {
        return getNthStudent(getCurrentSize());
    }
    
    /**
     * Pops from top of stack
     * @return StudentClass object popped from stack
     */
    
    public StudentClass pop()
    {
        return removeNthStudent(getCurrentSize());
    }
    
    /**
     * Pushes onto top of stack
     * @param student StudentClass object to be pushed onto stack
     */
    
    public void push(StudentClass student)
    {
        appendDataAtEnd(student);
    }
}
