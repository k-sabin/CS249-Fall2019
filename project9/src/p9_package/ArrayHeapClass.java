package p9_package;

public class ArrayHeapClass {

    /**
     * Initial array capacity
     */
    public final int DEFAULT_ARRAY_CAPACITY = 10;
    
    /**
     * Constant value for not found
     */
    public final int NOT_FOUND = 9999;
    
    /**
     * Management data for array
     */
    private int arrayCapacity;
    
    /**
     * Management data for array
     */
    private int arraySize;
    
    /**
     * Display flag can be set to observe bubble up and trickle down operations
     */
    private boolean displayFlag;
    /**
     * Array for heap
     */
    private int[] heapArray;
    
    /**
     * Default constructor sets up array management conditions and 
     * default display flag setting
     */
    public ArrayHeapClass()
    {
        arrayCapacity = DEFAULT_ARRAY_CAPACITY;
        arraySize = 0;
        displayFlag = false;
        heapArray = new int[arrayCapacity];
    }
    
    /**
     * Copy constructor copies array and array management 
     * conditions and default display flag setting
     * @param copied ArrayHeapClass object to be copied
     */
    public ArrayHeapClass(ArrayHeapClass copied)
    {
        arrayCapacity = copied.arrayCapacity;
        arraySize = copied.arraySize;
        displayFlag = false;
        heapArray = new int[arrayCapacity];
        int loopIndex;
        for(loopIndex = 0; loopIndex < arraySize; loopIndex++)
        {
            heapArray[loopIndex] = copied.heapArray[loopIndex];
        }
    }
    
    /**
     * Accepts integer item and adds it to heap
     * <p>
     * Note: uses bubbleUpArrayHeap to resolve unbalanced heap after data 
     * addition
     * <p>
     * Note: Always checks for resize before adding data
     * @param newItem integer item to be added
     */
    public void addItem(int newItem)
    {
        checkForResize();
        heapArray[arraySize] = newItem;
        arraySize++;
        bubbleUpArrayHeap(arraySize -1);
    }
    
    /**
     * Recursive operation to reset data in the correct order for the 
     * max heap after new data addition
     * @param currentIndex index of current item being assessed, and 
     * moved up as needed
     */
    private void bubbleUpArrayHeap(int currentIndex)
    {
        int temp;
        int largest = currentIndex;
        int parentIndex = (currentIndex - 1) / 2;
        
        if(parentIndex >= 0 && heapArray[currentIndex] < 
                                                    heapArray[parentIndex])
        {
            largest = parentIndex;
        }
        
        if(largest != currentIndex)
        {
            temp = heapArray[currentIndex];
            heapArray[currentIndex] = heapArray[largest];
            heapArray[largest] = temp;
            
            bubbleUpArrayHeap(largest);
        }
    }
    
    /**
     * Automatic resize operation used prior to any new data 
     * addition in the heap
     * <p>
     * Note: Tests for full heap array, and resizes to twice the 
     * current capacity as required
     */
    private void checkForResize()
    {
        if(arrayCapacity == arraySize)
        {
            arrayCapacity *=2;
            int[] newArray = new int[arrayCapacity];
            int loopIndex;
            
            for(loopIndex = 0; loopIndex < arraySize; loopIndex++)
            {
                newArray[loopIndex] = heapArray[loopIndex];
            }
            
            heapArray = newArray;
        }
    }
    
    /**
     * Tests for empty heap
     * @return boolean result of test
     */
    public boolean isEmpty()
    {
        if(arraySize == 0)
        {
            return true;
        }
        return false;
    }
    
    /**
     * Removes integer item from top of max heap
     * <p>
     * Note: Uses trickleDownArrayHeap to resolve unbalanced 
     * heap after data removal
     * @return integer item removed
     */
    public int removeItem()
    {
        int temp = heapArray[0];
        heapArray[0] = heapArray[arraySize - 1];
        arraySize--;
        trickleDownArrayHeap(0);
        return temp;
    }
    
    /**
     * Utility method to set the display flag for displaying internal 
     * operations of the heap bubble and trickle operations
     * @param setState flag used to set the state to display, or not
     */
    public void setDisplayFlag(boolean setState)
    {
        displayFlag = setState;
    }
    
    /**
     * Dumps array to screen as is, no filtering or management
     */
    public void showArray()
    {
        if(displayFlag)
        {
            int loopIndex;
            System.out.println("Heap Array:");
            System.out.print("[");
            for(loopIndex = 0; loopIndex < arraySize; loopIndex++)
            {
                System.out.print(heapArray[loopIndex] + ", ");
            }
            System.out.println("]");
        }
    }
    
    /**
     * Recursive operation to reset data in the correct order for the 
     * max heap after data removal
     * @param currentIndex index of current item being assessed, 
     * and moved down as required
     */
    private void trickleDownArrayHeap(int currentIndex)
    {
        int largest = currentIndex;
        int leftChild = (currentIndex * 2) + 1;
        int rightChild = (currentIndex * 2) + 2;
        
        if(leftChild < arraySize && 
                             heapArray[largest] > heapArray[leftChild])
        {
            largest = leftChild;
        }
        
        if(rightChild < arraySize && 
                            heapArray[largest] > heapArray[rightChild])
        {
            largest = rightChild;
        }
        
        if(largest != currentIndex)
        {
            int temp = heapArray[currentIndex];
            heapArray[currentIndex] = heapArray[largest];
            heapArray[largest] = temp;
            
            trickleDownArrayHeap(largest);
        }
    }
}
