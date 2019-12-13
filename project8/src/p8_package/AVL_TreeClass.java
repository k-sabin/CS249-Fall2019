package p8_package;

public class AVL_TreeClass 
{
    
    private class Node
    {
        /**
         * Character data for Node class
         */
        private char data;
        
        /**
         * Left child reference for tree
         */
        private Node leftChildRef;
        
        /**
         * Right child reference for tree
         */
        private Node rightChildRef;
        
        /**
         * Copy constructor for AVL tree node
         * @param copied Node object to be copied
         */
        Node(Node copied)
        {
            data = copied.data;
            leftChildRef = copied.leftChildRef;
            rightChildRef = copied.rightChildRef;
        }
        
        /**
         * Initialization constructor for Node Class
         * @param inData char quantity
         */
        Node(char inData)
        {
            data = inData;
            leftChildRef = null;
            rightChildRef = null;
        }
        
        /**
         * Initialization constructor for data and child references
         * @param inData char quantity
         * @param leftRef reference for left child
         * @param rightRef reference for right child
         */
        Node(char inData, Node leftRef, Node rightRef)
        {
            data = inData;
            leftChildRef = leftRef;
            rightChildRef = rightRef;
        }
    }
    
    /**
     * Constant used to represent dash
     */
    private static final char DASH = 45;
    
    /**
     * Null character returned if data not available
     */
    private static final char NULL_CHAR = 0;
    
    /**
     * Class global variable used to display tree structure
     */
    private boolean rowStartFlag;
    
    /**
     * Constant used to represent space
     */
    private static final char SPACE = 32;
    
    /**
     * Root of AVL tree
     */
    private Node treeRoot;
    
    
    
    /**
     * Default class constructor
     */
    AVL_TreeClass()
    {
        rowStartFlag = false;
        treeRoot = new Node(NULL_CHAR);
    }
    
    /**
     * Copy constructor
     * @param copied AVL_TreeClass object to be copied
     */
    AVL_TreeClass(AVL_TreeClass copied)
    {
        rowStartFlag = false;
        treeRoot = copyConstructorHelper(copied.treeRoot);
    }
    
    
    /**
     * Recursive copy constructor helper
     * <p>
     * Node: Uses preorder strategy to copy nodes
     * @param wkgCopiedRef Node reference at which method starts at each 
     * level of recursion
     * @return Node reference to link current node information to methods/Nodes
     * calling this method
     */
    private Node copyConstructorHelper(Node wkgCopiedRef)
    {
        Node leftNode, rightNode;
        if(wkgCopiedRef.leftChildRef != null)
        {
            leftNode = new Node(wkgCopiedRef.leftChildRef);
            return leftNode;
        }
        else if(wkgCopiedRef.rightChildRef != null)
        {
            rightNode = new Node(wkgCopiedRef.rightChildRef);
            return rightNode;
        }
        else
        {
            return null;
        }
    }
    
    /**
     * Clears Tree
     */
    public void clearTree()
    {
        treeRoot.data = NULL_CHAR;
        treeRoot.leftChildRef = null;
        treeRoot.rightChildRef = null;
    }
    
    /**
     * Displays text-graphical representation of one level/line of the AVL tree
     * @param workingNode node reference at current recursive level
     * @param nodeHeight height of tree plus two for current height of nodes,
     * including lowermost null children
     * @param displayLevel level of tree at which the current line of display
     * is to be presented
     * @param workingLevel current level during recursive actions
     */
    private void displayAtTreeLevel( Node workingNode, int nodeHeight, 
            int displayLevel, int workingLevel )
    {
        char charOut = workingNode.data;
        
        if( workingLevel == displayLevel )
        {
            displayValue( charOut, nodeHeight, workingLevel );
            
            return;
        }
        
        if( workingNode.leftChildRef != null )
        {
            displayAtTreeLevel( workingNode.leftChildRef, nodeHeight,
                                            displayLevel, workingLevel + 1 );
        }
        
        else
        {
            displayEmptyNodeSpaces( nodeHeight, displayLevel, 
                                                            workingLevel + 1 );
        }
        
        if( workingNode.rightChildRef != null )
        {
            displayAtTreeLevel( workingNode.rightChildRef, nodeHeight,
                                            displayLevel, workingLevel + 1 );
        }
        
        else
        {
            displayEmptyNodeSpaces( nodeHeight, displayLevel, 
                                                    workingLevel + 1 );
        }              
    }
    
    /**
     * Local recursive method to display a specified number of a 
     * specified character
     * @param numChars number of characters to display
     * @param outChar character to display
     */
    private void displayChars(int numChars, char outChar)
    {
        if(numChars > 0)
        {
            displayChars(numChars -1, outChar);
        }
        System.out.print(outChar);
    }
    
    /**
     * Method that displays null or blank nodes for a tree at null locations
     * Note: used by displayAtTreeLevel
     * @param nodeHeight height of tree plus two for current height of nodes, 
     * including lowermost null children
     * @param displayLevel level of the tree at which the display will be 
     * applied
     * @param workingLevel level of tree just below non-null node at which
     * method is currently working
     */
    private void displayEmptyNodeSpaces( int nodeHeight, 
            int displayLevel, int workingLevel )
    {
        int nodesToDisplay = toPower( 2, displayLevel - workingLevel ); 
        char charOut = SPACE;
        
        if( displayLevel == workingLevel )
        {
            charOut = DASH;
        }
        
        while( nodesToDisplay > 0 )
        {
            displayValue( charOut, nodeHeight, displayLevel );
            nodesToDisplay--;
        }
    }
    
    /**
     * Displays text-graphical representation of AVL tree
     */
    public void displayTreeStructure()
    {
        int displayLevel, nodeHeight = getTreeHeight( treeRoot ) + 2;
        int workingLevel = 1;
         
        if( treeRoot != null )
        {
            for(displayLevel = 1; displayLevel <= nodeHeight; displayLevel++)
            {
                rowStartFlag = true;
                
                displayAtTreeLevel( treeRoot, nodeHeight, 
                                             displayLevel, workingLevel );
                
                System.out.println();
            }
        }
        
        else
        {
            System.out.println( "\nEmpty Tree - No Display");
        }
    }
    
    /**
     * Method used to display a character or color letter along with 
     * calculated leading spaces
     * <p>
     * Node: used in displayAtTreeLevel and displayEmptyNodeSpaces
     * @param data data value to display, either letter or color data
     * @param nodeHeight height of tree plus two for current height of nodes, 
     * including lowermost null children
     * @param workingLevel current level during recursive actions
     */
    private void displayValue( char data, int nodeHeight, int workingLevel )
    {
        int leadingSpaces;
         
        if( rowStartFlag )
        {
            leadingSpaces = toPower( 2, nodeHeight - workingLevel );
    
            rowStartFlag = false;
        }
        
        else
        {
            leadingSpaces = toPower( 2, nodeHeight - workingLevel + 1 ) - 1;
        }
        displayChars( leadingSpaces, SPACE );
        
        System.out.print( data );         
    }
    
    /**
     * Provides tree height to user
     * <p>
     * Note: uses getTreeHeight
     * @return integer height of tree
     */
    public int findTreeHeight()
    {
        return getTreeHeight(treeRoot);
    }
    
    /**
     * gets balance factor indicating if tree is unbalanced from 
     * given root down
     * @param wkgLocalRef Node from which balance factor is found
     * @return integer balance factor
     */
    private int getBalanceFactor(Node wkgLocalRef)
    {
        int leftSideHeight = getTreeHeight(treeRoot.leftChildRef);
        int rightSideHeight = getTreeHeight(treeRoot.rightChildRef);
        
        return leftSideHeight - rightSideHeight;
    }
    
    /**
     * Finds maximum of two given numbers
     * @param one one of the values to be tested
     * @param other other of two values to be tested
     * @return
     */
    private int getMax(int one, int other)
    {
        if(one>other)
        {
            return one;
        }
        return other;
    }
    
    /**
     * Tree height helper method
     * @param wkgLocalRef Node from which height is found
     * @return integer height of tree
     */
    private int getTreeHeight(Node wkgLocalRef)
    {
        if(wkgLocalRef == null)
        {
            return -1;
        }
        
        int leftHeight = getTreeHeight(wkgLocalRef.leftChildRef);
        int rightHeight = getTreeHeight(wkgLocalRef.rightChildRef);
        
        return 1 + getMax(leftHeight,rightHeight);
    }
    
    /**
     * In order display of tree
     */
    public void inOrderDisplay()
    {
        inOrderDisplayHelper(treeRoot);
    }
    
    /**
     * Provides inOrder traversal action
     * @param wkgLocalRef Node tree root reference at the current 
     * recursion level
     */
    private void inOrderDisplayHelper(Node wkgLocalRef)
    {
        if(wkgLocalRef != null)
        {
            inOrderDisplayHelper(wkgLocalRef.leftChildRef);
            System.out.println(wkgLocalRef.data);
            inOrderDisplayHelper(wkgLocalRef.rightChildRef);
        }
    }
    
    /**
     * Insert method for AVL Tree
     * <p>
     * Note: uses insert helper method which returns the root node reference
     * to this value
     * @param inData char data to be added to the AVL tree
     */
    public void insert(char inData)
    {
        if(treeRoot.data == NULL_CHAR)
        {
            treeRoot.data = inData;
        }
        if(search(inData) == NULL_CHAR)
        {
            //insert data
            treeRoot = insertHelper(treeRoot,inData);
            //balance the tree if necessary
            while(getBalanceFactor(treeRoot) > 1)
            {
                Node balanceNode = treeRoot;
                while(getBalanceFactor(balanceNode) < -1 && 
                                            getBalanceFactor(balanceNode) > 1)
                {
                    balanceNode = balanceNode.leftChildRef;
                }
                treeRoot = rotateRight(balanceNode);
            }
            while(getBalanceFactor(treeRoot) < -1)
            {
                Node balanceNode = treeRoot;
                while(getBalanceFactor(balanceNode) < -1 && 
                                            getBalanceFactor(balanceNode) > 1)
                {
                    balanceNode = balanceNode.rightChildRef;
                }
                treeRoot = rotateLeft(balanceNode);
            }
        }
    }
    
    /**
     * Insert helper method for AVL Tree insert action
     * @param wkgLocalRef Node tree root reference at the current recursion 
     * level
     * @param inData char item to be added to AVL tree
     * @return Node reference to current AVL Tree root
     */
    public Node insertHelper(Node wkgLocalRef, char inData)
    {
        if(wkgLocalRef != null && inData > wkgLocalRef.data)
        {
            wkgLocalRef.rightChildRef = insertHelper(
                                           wkgLocalRef.rightChildRef, inData);
        }
        else if(wkgLocalRef != null && inData < wkgLocalRef.data)
        {
            wkgLocalRef.leftChildRef = insertHelper(
                                            wkgLocalRef.leftChildRef, inData);
        }
        else 
        {
            wkgLocalRef = new Node(inData);
        }
        return wkgLocalRef;
    }
    
    /**
     * Test for empty tree
     * @return Boolean result of test
     */
    public boolean isEmpty()
    {
        if(treeRoot.data == NULL_CHAR && treeRoot.leftChildRef == null && 
                                                treeRoot.rightChildRef == null)
        {
            return true;
        }
        return false;
    }
    
    /**
     * Rotates local tree left or CCW
     * @param wkgLocalRef reference of current item
     * @return Node resulting current root
     */
    private Node rotateLeft(Node wkgLocalRef)
    {
        Node newLocal = wkgLocalRef.rightChildRef;
        wkgLocalRef.rightChildRef = newLocal.leftChildRef;
        newLocal.leftChildRef = wkgLocalRef;
        return newLocal;
    }
    
    /**
     * Rotates local tree right or CW
     * @param wkgLocalRef reference of current item
     * @return Node resulting current root
     */
    private Node rotateRight(Node wkgLocalRef)
    {
        Node newLocal = wkgLocalRef.leftChildRef;
        wkgLocalRef.leftChildRef = newLocal.rightChildRef;
        newLocal.rightChildRef = wkgLocalRef;
        return newLocal;
    }
    
    /**
     * Searches data in AVL Tree given char with necessary key
     * @param searchData char item containing key
     * @return char reference to found data
     */
    public char search(char searchData)
    {
        return searchHelper(treeRoot, searchData);
    }
    
    /**
     * Helper method for AVL Tree search action
     * @param wkgLocalRef Node tree root reference at the current 
     * recursion level
     * @param searchData char item containing key
     * @return char result of search
     */
    private char searchHelper(Node wkgLocalRef, char searchData)
    {
        if(wkgLocalRef != null)
        {
            if(searchData > wkgLocalRef.data)
            {
                return searchHelper(wkgLocalRef.rightChildRef, searchData);
            }
            else if(searchData < wkgLocalRef.data)
            {
                return searchHelper(wkgLocalRef.leftChildRef, searchData);
            }
            
            else
            {
                if(wkgLocalRef.data == searchData)
                {
                    return wkgLocalRef.data;
                }
                return NULL_CHAR;
            }
        }
        return NULL_CHAR;
    }
    
    /**
     * Local recursive method to calculate exponentiation with integers;
     * @param base base of exponentiation
     * @param exponent exponent of exponentiation
     * @return result of exponentiation calculation
     */
    private int toPower(int base, int exponent)
    {
        if(exponent == 0)
        {
            return 1;
        }
        return base * toPower(base, exponent - 1);
    }
}
