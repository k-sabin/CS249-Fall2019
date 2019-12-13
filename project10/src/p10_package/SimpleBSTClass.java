package p10_package;

public class SimpleBSTClass {
    /**
     * Root of BST
     */
    private SimpleStudentClass treeRoot;
    
    /**
     * Default class constructor
     */
    public SimpleBSTClass()
    {
        treeRoot = null;
    }
    
    /**
     * Copy Constructor
     * @param copied SimpleBSTClass object to be copied
     */
    public SimpleBSTClass(SimpleBSTClass copied)
    {
        treeRoot = copyConstructorHelper(copied.treeRoot);
    }
    
    /**
     * Clears tree of all data
     */
    public void clearTree()
    {
        treeRoot = null;
    }
    
    /**
     * Recursive copy constructor helper
     * @param wkgCopiedRef reference to SimpleSTudentClass node
     * @return reference to link to calling method/node
     */
    private SimpleStudentClass copyConstructorHelper
                                        (SimpleStudentClass wkgCopiedRef)
    {
        SimpleStudentClass returnNode = new SimpleStudentClass(wkgCopiedRef);
        SimpleStudentClass leftChild = 
                copyConstructorHelper(wkgCopiedRef.leftChildRef);
        SimpleStudentClass rightChild = 
                copyConstructorHelper(wkgCopiedRef.rightChildRef);
        returnNode.leftChildRef = leftChild;
        returnNode.rightChildRef = rightChild;
        
        return returnNode;
    }
    
    /**
     * Displays BST in order
     */
    public void displayInOrder()
    {
        displayInOrderHelper(treeRoot);
    }
    
    /**
     * Recursively implements inOrder traversal action
     * @param wkgRef SimpleStudentClass tree root reference 
     * at the current recursion level
     */
    private void displayInOrderHelper(SimpleStudentClass wkgRef)
    {
        if(wkgRef != null)
        {
            displayInOrderHelper(wkgRef.leftChildRef);
            System.out.println(wkgRef.toString());
            displayInOrderHelper(wkgRef.rightChildRef);
        }
    }
    
    /**
     * Returns larger of two values
     * <p>
     * Note: used by treeHeightHelper
     * @param one one of the two values to be tested
     * @param other the other of the two values to be tested
     * @return highest value of two input values
     */
    private int getMax(int one, int other)
    {
        if(one > other)
        {
            return one;
        }
        return other;
    }
    
    /**
     * Finds height of BST
     * <p>
     * Note: empty tree: -1; root node only: 0; number of edges thereafter
     * @return height of tree -  maximum edges from root node to lowest 
     * part of tree
     */
    public int getTreeHeight()
    {
        if(treeRoot == null)
        {
            return -1;
        }
        else if (treeRoot.leftChildRef == null 
                && treeRoot.rightChildRef == null)
        {
            return 0;
        }
        return treeHeightHelper(treeRoot);
    }
    
    /**
     * Insert method for BST
     * <p>
     * Note: overloaded insert uses insert method with 
     * individual student information data items
     * @param inName name to be added to BST
     * @param inStudentID student ID data to be added to BST
     * @param inGender gender data to be added to BST
     * @param inGPA gpa data to be added to BST
     * @return boolean result of action
     */
    public boolean insert
                (String inName, int inStudentID, char inGender, double inGPA)
    {
        if(treeRoot == null)
        {
            treeRoot = new SimpleStudentClass
                                    (inName, inStudentID, inGender, inGPA);
            return true;
        }
        return insert(new SimpleStudentClass
                                    (inName, inStudentID, inGender, inGPA));
    }
    
    /**
     * Insert method for BST
     * <p>
     * Note: Overloaded insert uses insert helper method 
     * with a SimpleStudentClass object
     * @param newNode SimpleStudentClass object to be added to BST
     * @return Boolean result of action
     */
    public boolean insert(SimpleStudentClass newNode)
    {
        if(treeRoot == null)
        {
            treeRoot = newNode;
            return true;
        }
        return insertHelper(treeRoot, newNode);
    }
    
    /**
     * Insert helper method for BST insert action
     * <p>
     * Note: Does not allow for duplicate entries (i.e., duplicate student IDs)
     * @param wkgRef SimpleStudentClass tree root reference at the 
     * current recursion level
     * @param newNode SimpleStudentClass object to be added to BST
     * @return Boolean result of action
     */
    private boolean insertHelper
                    (SimpleStudentClass wkgRef, SimpleStudentClass newNode)
    {
        if(newNode.studentID > wkgRef.studentID)
        {
            if(wkgRef.rightChildRef == null)
            {
                wkgRef.rightChildRef = newNode;
                return true;
            }
            return insertHelper(wkgRef.rightChildRef, newNode);
        }
        else if (newNode.studentID < wkgRef.studentID)
        {
            if(wkgRef.rightChildRef == null)
            {
                wkgRef.leftChildRef = newNode;
                return true;
            }
            return insertHelper(wkgRef.leftChildRef, newNode);
        }
        return false;
    }
    
    /**
     * Test for empty tree
     * @return Boolean result of test
     */
    public boolean isEmpty()
    {
        if(treeRoot == null)
        {
            return true;
        }
        return false;
    }
    
    /**
     * Recursively searches tree from given node to minimum value node, 
     * stores data value found, then unlinks the node
     * @param minParent SimpleStudentClass reference to parent of child node; 
     * used for linking from parent to child's right child
     * @param minChild SimpleStudentClass reference to child node being tested
     * @return SimpleStudentClass reference containing removed node
     */
    private SimpleStudentClass removeFromMin
                    (SimpleStudentClass minParent, SimpleStudentClass minChild)
    {
        if(minChild.leftChildRef != null)
        {
            return removeFromMin(minChild, minChild.leftChildRef);
        }
        SimpleStudentClass temp = new SimpleStudentClass(minParent);
        
        minChild = temp.rightChildRef;
        return temp;
    }
    
    /**
     * Removes data from tree using given key
     * <p>
     * Note: verifies data node is in the tree, then uses remove helper method
     * @param studentID item that is used for search/removal
     * @return SimpleStudentClass result of remove action
     */
    public SimpleStudentClass removeNode(int studentID)
    {
        if(search(studentID) != null)
        {
            return removeNodeHelper(treeRoot, studentID);
        }
        return null;
    }
    
    /**
     * Recursive helper for BST remove action
     * <p>
     * Note: Uses removeFromMin method
     * <p>
     * Note: Since removeNode calling method verifies data node existence, 
     * this method does not need to treat missing node condition
     * @param wkgRef SimpleStudentClass tree root reference at the 
     * current recursion level
     * @param studentID item that is used for search/removal
     * @return SimpleStudentClass reference result of remove helper action
     */
    private SimpleStudentClass removeNodeHelper
                                (SimpleStudentClass wkgRef, int studentID)
    {
        SimpleStudentClass temp = new SimpleStudentClass(wkgRef);
        if(wkgRef.studentID == studentID)
        {
            if(wkgRef.leftChildRef == null && wkgRef.rightChildRef == null)
            {
                wkgRef = null;
                return temp;
            }
            else if (wkgRef.leftChildRef == null && wkgRef.rightChildRef != null)   
            {
                wkgRef = new SimpleStudentClass(wkgRef.rightChildRef);
                return temp;
            }
            else if(wkgRef.leftChildRef != null && wkgRef.rightChildRef == null)
            {
                wkgRef = new SimpleStudentClass(wkgRef.leftChildRef);
                return temp;
            }
            else
            {
                wkgRef = removeFromMin
                        (wkgRef.rightChildRef, wkgRef.rightChildRef.leftChildRef);
                return temp;
            }
        }
        
        if (wkgRef.studentID > studentID)
        {
            return removeNodeHelper(wkgRef.leftChildRef,studentID);
        }
        else
        {
            return removeNodeHelper(wkgRef.rightChildRef, studentID);
        }
    }
    
    /**
     * Searches for data in BST given necessary studentID key
     * @param studentID search value
     * @return SimpleStudentReference to found data
     */
    public SimpleStudentClass search(int studentID)
    {
        return searchHelper(treeRoot, studentID);
    }
    
    /**
     * Helper method for BST search action
     * @param wkgRef SimpleStudentClass working reference at the 
     * current recursion level
     * @param StudentID item containing key (student ID number)
     * @return Boolean result of search
     */
    private SimpleStudentClass searchHelper
                                    (SimpleStudentClass wkgRef, int studentID)
    {
        if(wkgRef != null)
        {
            if(studentID > wkgRef.studentID)
            {
                return searchHelper(wkgRef.rightChildRef, studentID);
            }
            else if(studentID < wkgRef.studentID)
            {
                return searchHelper(wkgRef.leftChildRef, studentID);
            }
            
            else
            {
                if(wkgRef.studentID == studentID)
                {
                    return wkgRef;
                }
            }
        }
        return null;
    }
    
    /**
     * Helper method to find height of BST
     * @param wkgRef SimpleStudentClass working reference used at the 
     * current level of recursion
     * @return height of tree - maximum number of edges from root 
     * node to lowest part of tree
     */
    private int treeHeightHelper(SimpleStudentClass wkgRef)
    {
        int leftHeight = treeHeightHelper(wkgRef.leftChildRef);
        int rightHeight = treeHeightHelper(wkgRef.rightChildRef);
        
        return 1 + getMax(leftHeight,rightHeight);
    }
    
}
