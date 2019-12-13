package p7_package;

public class Generic_BST_Class<GenericData extends java.lang.Comparable
                                                            <GenericData>>
{
    private class Node
    {
        /**
         * left child reference
         */
        private Node leftChildRef;
        
        /**
         * generic data held by node
         */
        private GenericData nodeData;
        
        /**
         * right child reference
         */
        private Node rightChildRef;
        
        Node()
        {
            nodeData = null;
            leftChildRef = null;
            rightChildRef = null;
        }
        
        Node(GenericData inData)
        {
            nodeData = inData;
            leftChildRef = null;
            rightChildRef = null;
        }
        
        Node(Node copy)
        {
            nodeData = copy.nodeData;
            leftChildRef = copy.leftChildRef;
            rightChildRef = copy.rightChildRef;
        }
        
    }
    
    /**
     * Traverse code - inorder
     */
    public static final int IN_TRAVERSE = 102;
    
    /**
     * Traverse code - postorder
     */
    public static final int POST_TRAVERSE = 103;
    
    /**
     * Traverse code - preorder
     */
    public static final int PRE_TRAVERSE = 101;
    
    /**
     * Root of BST
     */
    private Node rootNode;
    
    /**
     * Default class constructor, initializes BST
     */
    public Generic_BST_Class()
    {
        rootNode = new Node();
    }
    
    /**
     * Clears tree
     */
    public void clearTree()
    {
        rootNode.nodeData = null;
        rootNode.leftChildRef = null;
        rootNode.rightChildRef = null;
    }
    
    /**
     * Provides inOrder traversal action using recursion
     * @param wkgRef Node tree root reference at the current recursion level
     */
    private void displayInOrder(Node wkgRef)
    {
        if(wkgRef != null)
        {
            displayInOrder(wkgRef.leftChildRef);
            System.out.println(wkgRef.nodeData.toString());
            displayInOrder(wkgRef.rightChildRef);
        }
    }
    
    /**
     * Provides postOrder traversal action using recursion
     * @param wkgRef Node tree root reference at the current recursion level
     */
    private void displayPostOrder(Node wkgRef)
    {
        if(wkgRef != null)
        {
            displayPostOrder(wkgRef.leftChildRef);
            displayPostOrder(wkgRef.rightChildRef);
            System.out.println(wkgRef.nodeData.toString());
        }
    }
    
    /**
     * Provides preOrder traversal action using recursion
     * @param wkgRef Node tree root reference at the current recursion level
     */
    private void displayPreOrder(Node wkgRef)
    {
        if(wkgRef != null)
        {
            System.out.println(wkgRef.nodeData.toString());
            displayPreOrder(wkgRef.leftChildRef);
            displayPreOrder(wkgRef.rightChildRef);
        }
    }
    
    /**
     * Provides user with three ways to display BST data
     * @param traverseCode int code for selecting BST traversal method, 
     * accepts PRE_TRAVERSE, IN_TRAVERSE, POST_TRAVERSE
     */
    public void displayTree(int traverseCode)
    {
        switch (traverseCode)
        {
            case IN_TRAVERSE: displayInOrder(rootNode);
                    break;
            case POST_TRAVERSE: displayPostOrder(rootNode);
                    break;
            case PRE_TRAVERSE: displayPreOrder(rootNode);
                    break;
        }
    }
    
    /**
     * Insert method for BST 
     * <p>
     * Note: method adds first node if tree is empty; 
     * otherwise calls insertHelper method
     * @param inData GenericData data to be added to BST
     * @return Boolean result of operation
     */
    public boolean insert(GenericData inData)
    {
        if(rootNode.nodeData == null)
        {
            rootNode.nodeData = inData;
            return true;
        }
        return insertHelper(rootNode,inData);
    }
    
    /**
     * Recursive insert helper method for BST insert action 
     * <p>
     * Adds new node to left or right of current node; does not allow duplicate
     * values to be inserted into tree
     * @param wkgRef Node tree root reference at the current recursion level
     * @param inData GenericData item to be added to BST
     * @return Boolean result of operation
     */
    private boolean insertHelper(Node wkgRef, GenericData inData)
    {
        int compare = wkgRef.nodeData.compareTo(inData);
        if(compare < 0)
        {
            if(wkgRef.rightChildRef == null)
            {
                wkgRef.rightChildRef = new Node(inData);
                return true;
            }
            return insertHelper(wkgRef.rightChildRef, inData);
        }
        else if(compare > 0)
        {
            if(wkgRef.leftChildRef == null)
            {
                wkgRef.leftChildRef = new Node(inData);
                return true;
            }
            return insertHelper(wkgRef.leftChildRef, inData);
        }
        return false;
    }
    
    /**
     * Test for empty tree
     * @return Boolean result of test
     */
    public boolean isEmpty()
    {
        if(rootNode.nodeData == null && rootNode.leftChildRef == null &&
                                        rootNode.rightChildRef == null)
        {
            return true;
        }
        return false;
    }
    /**
     * Searches tree from given node to minimum value node below it, stores 
     * data value found, unlinks the node, and returns it to the calling method
     * @param parentNode Node reference to current node
     * @param childNode Node reference to child node to be tested
     * @return Node reference containing removed node
     */
    private Node removeFromMin(Node parentNode, Node childNode)
    {
        if(childNode.leftChildRef != null)
        {
            return removeFromMin(childNode, childNode.leftChildRef);
        }
        Node temp = new Node(parentNode);
        
        childNode = temp.rightChildRef;
        return temp;
    }
    
    /**
     * Removes data node from tree using given key 
     * <p>
     * Note: uses remove helper method 
     * <p>
     * Note: uses search initially to get value, if it is in tree; if value 
     * found, remove helper method is called, otherwise returns null
     * @param inData GenericData that includes the necessary key
     * @return GenericData result of remove action
     */
    public GenericData removeItem(GenericData inData)
    {
        if(search(inData) == null)
        {
            return null;
        }
        Node temp = new Node(rootNode);
        rootNode = removeItemHelper(rootNode, inData);
        rootNode.leftChildRef = temp.leftChildRef;
        return rootNode.nodeData;
    }
    
    /**
     * Remove helper for BST remove action
     * <p>
     * Note: Recursive method returns updated local root to 
     * maintain tree linkage
     * <p>
     * Note: uses removeFromMin method
     * @param wkgRef Node tree root reference at the current recursion level
     * @param inData GenericData item that includes the necessary key
     * @return Node reference result of remove helper action
     */
    private Node removeItemHelper(Node wkgRef, GenericData inData)
    {
        
        if(wkgRef != null && wkgRef.nodeData.compareTo(inData) == 0)
        {
            Node temp = new Node(wkgRef);
            temp = wkgRef;
            System.out.println("Node being removed: " + temp.nodeData.toString());
            if(wkgRef.leftChildRef == null && wkgRef.rightChildRef == null)
            {
                System.out.println("got here 1");
                wkgRef = null;
                System.out.println(wkgRef.nodeData.toString());
                return temp;
            }
            else if(wkgRef.leftChildRef == null && wkgRef.rightChildRef != null)
            {
                System.out.println("got here 2");
                wkgRef = new Node(wkgRef.rightChildRef);
                System.out.println(wkgRef.nodeData.toString());
                return wkgRef;
            }
            else if(wkgRef.leftChildRef != null && wkgRef.rightChildRef == null)
            {
                System.out.println("got here 3");
                wkgRef = new Node(wkgRef.leftChildRef);
                System.out.println(wkgRef.nodeData.toString());
                return wkgRef;
            }
            else
            {
                System.out.println("got here 4");
                wkgRef = removeFromMin(wkgRef.rightChildRef, wkgRef.rightChildRef.leftChildRef);
                return wkgRef;
            }
        }
        else if(wkgRef != null && wkgRef.nodeData.compareTo(inData) > 0)
        {
            return removeItemHelper(wkgRef.leftChildRef, inData);
        }
        else if(wkgRef != null && wkgRef.nodeData.compareTo(inData) < 0)
        {
            return removeItemHelper(wkgRef.rightChildRef, inData);
        }
        
        return null;
    }
    
    /**
     * Searches for data in BST given GenericData with necessary key
     * @param searchData GenericData item containing key
     * @return GenericData reference to found data
     */
    public GenericData search(GenericData searchData)
    {
        return searchHelper(rootNode, searchData);
    }
    
    /**
     * Helper method for recursive BST search action 
     * @param wkgRef Node tree root reference at the current recursion level
     * @param searchData GenericData item containing key
     * @return GenericData item found
     */
    private GenericData searchHelper(Node wkgRef, GenericData searchData)
    {
        if(wkgRef != null && wkgRef.nodeData.compareTo(searchData) == 0)
        {
            return wkgRef.nodeData;
        }
        else if(wkgRef != null && wkgRef.nodeData.compareTo(searchData) > 0)
        {
            return searchHelper(wkgRef.leftChildRef, searchData);
        }
        else if (wkgRef != null && wkgRef.nodeData.compareTo(searchData) < 0)
        {
            return searchHelper(wkgRef.rightChildRef, searchData);
        }
        return null;
    }
}
