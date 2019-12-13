package p7_package;

public class BSTMain {
    
    
    public static void main(String[] args)
    {
        Generic_BST_Class<Integer> test = new Generic_BST_Class<Integer>();
        
        System.out.println(test.isEmpty() + "\n");
        
        test.insert(5);
        test.insert(3);
        test.insert(4);
        test.insert(2);
        test.insert(1);
        test.insert(7);
        test.insert(6);
        test.insert(9);
        test.insert(8);
        test.insert(10);
        test.insert(11);
        
        System.out.println("\n" + test.isEmpty() + "\n");
        
        System.out.println("Displaying in order");
        test.displayTree(102);
        
        System.out.println("\nDisplaying post order");
        test.displayTree(103);
        
        System.out.println("\nDisplaying pre-order");
        test.displayTree(101);
        
        test.removeItem(10);
        //System.out.println(test.removeItem(7));
        
        System.out.println("\nDisplaying in order");
        test.displayTree(102);
    }
    
    
    
}
