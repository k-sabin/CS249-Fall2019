package p10_package;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HashClass {

    /**
     * Constant for identifying character in file in data load operation
     */
    private static final char CARRIAGE_RETURN_CHAR = 13;
    
    /**
     * Table size default
     */
    private final int DEFAULT_TABLE_SIZE = 10;
    
    /**
     * Constant for identifying end of file in data load operation
     */
    private static final int END_OF_FILE_MARKER = -1;
    
    /**
     * File reader object for use in data load operation
     */
    private static java.io.FileReader fileIn;
    
    /**
     * Constant for identifying character in file in data load operation
     */
    private static final char NEWLINE_CHAR = 10;
    private static final char SEMICOLON = 59;
    private static final char SPACE = 32;
    private static final char TAB_CHAR = 9;
    
    /**
     * Array for hash table
     */
    private SimpleBSTClass[] tableArray;
    
    /**
     * Size of the base table
     */
    private int tableSize;
    
    
    /**
     * Default constructor
     */
    public HashClass()
    {
        tableSize = DEFAULT_TABLE_SIZE;
        tableArray = new SimpleBSTClass[tableSize];
    }
    
    /**
     * Copy constructor
     * @param 
     */
    public HashClass(HashClass copied)
    {
        tableSize = copied.tableSize;
        tableArray = new SimpleBSTClass[tableSize];
        int index = 0;
        while(index < tableSize)
        {
            tableArray[index] = copied.tableArray[index];
            index++;
        }
    }
    
    /**
     * Initialization constructor
     * @param
     */
    public HashClass(int inTableSize)
    {
        tableSize = inTableSize;
        tableArray = new SimpleBSTClass[tableSize];
    }
    
    /**
     * Adds item to hash table
     * <p>
     * Uses overloaded addItem with object, optionally may be anonymous 
     * constructor
     * @param inName name of student
     * @param inStudentID student ID
     * @param inGender gender of student
     * @param inGPA gpa of student
     * @return Boolean success of operation
     */
    public boolean addItem
                (String inName, int inStudentID, char inGender, double inGPA)
    {
        return addItem(new SimpleStudentClass
                                (inName, inStudentID, inGender, inGPA));
    }
    
    /**
     * Adds item to hash table
     * <p>
     * Overloaded method that accepts SimpleStudentClass object
     * @param newItem student class object
     * @return Boolean success of operation
     * @throws IndexOutOfBoundsException if hash calculation failure
     */
    public boolean addItem(SimpleStudentClass newItem)
    {
        int indexOfNew = generateHash(newItem.studentID);
        if(findItem(newItem.studentID) == null)
        {
            if(indexOfNew > tableSize)
            {
                throw new IndexOutOfBoundsException();
            }
            tableArray[indexOfNew] = new SimpleBSTClass();
        }
        return tableArray[indexOfNew].insert(newItem);
    }
    
    /**
     * Indicates whether a given character is found in a given string
     * <p>
     * Note: Uses .length and .charAt
     * @param testChar character to be tested in the string
     * @param testString string given for the character search
     * @return Boolean indication that the character was found in the string
     */
    private boolean charInString(char testChar, String testString)
    {
        int index;
        for (index = 0; index < testString.length(); index++)
        {
            if(testChar == testString.charAt(index))
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Clears hash table by clearing all trees
     */
    public void clearHashTable()
    {
        int index;
        
        for(index = 0; index < tableSize; index++)
        {
            if(tableArray[index] != null)
            {
                tableArray[index].clearTree();
            }
        }
    }
    
    /**
     * Searches for item in hash table
     * @param studentID used for requesting data
     * @return SimpleStudentClass object found, or null if not found
     */
    public SimpleStudentClass findItem(int studentID)
    {
        int hashToFind = generateHash(studentID);
        if(tableArray[hashToFind] != null)
        {
            return tableArray[hashToFind].search(studentID);
        }
        return null;
    }
    
    /**
     * Method converts studentID within data value to hash value 
     * for use as index in hash table
     * <p>
     * Note: Method is overloaded, this one can be used with a 
     * student class string value that holds the student ID
     * <p>
     * Note: calls overloaded generateHash with string converted to integer
     * <p>
     * Note: digits are individually multiplied by position (1-6,
     * left to right) and added to sum before being set to hash value.
     * E.g, for 654987, last (LSD) digit (7) is multiplied by 6, then fifth
     * digit (8) is multiplied by 5, then fourth digit (9) is multiplied by 4, 
     * etc.
     * @param dataString String value contains student data within 
     * which the student ID will be converted to hash value
     * @return integer hash index value
     */
    public int generateHash(String dataString)
    {
        int idToHash = getStudentID(dataString);
        return generateHash(idToHash);
    }
    
    /**
     * Method converts student ID within data value to hash value 
     * for use as index in hash table
     * <p>
     * Note: Method is overloaded, this one can be used with a 
     * student ID number
     * <p>
     * Note: digits are individually multiplied by position (1-6,
     * left to right) and added to sum before being set to hash value.
     * E.g, for 654987, last (LSD) digit (7) is multiplied by 6, then fifth
     * digit (8) is multiplied by 5, then fourth digit (9) is multiplied by 4, 
     * etc.
     * @param studentID contains student ID number to be converted to 
     * hash value
     * @return integer hash index value
     */
    public int generateHash(int studentID)
    {
        //return value
        int hash = 0;
        //value to add to hash after calculated
        int numberToHash = 0;
        //current place so we can use modulo to get the remainder
        int currentPlace = 10;
        int index;
        
        for(index = 1; index <= 6; index++)
        {
            //subtracting the last place's number to leave the proper remainder
            studentID -= numberToHash;
            numberToHash = studentID % currentPlace;
            numberToHash = numberToHash / (currentPlace/10);
            hash += numberToHash * index;
            currentPlace*= 10;
        }
        
        return hash;
    }
    
    /**
     * Removes items from hash table
     * @param studentID used for requesting data
     * @return SimpleStudentClass object removed, or null if not found
     */
    public SimpleStudentClass removeItem(int studentID)
    {
        if(findItem(studentID) != null)
        {
            int hash = generateHash(studentID);
            return tableArray[hash].removeNode(studentID);
        }
        return null;
    }
    
    /**
     * Traverses through string, finds student ID, returns
     * 
     * @param dataString String object through which process traverses
     * 
     * @return extracted student ID
     */
    private int getStudentID( String dataString )
    {
        int index = 0;
        int strLen = dataString.length();
        char testChar;
        String numString = "";
    
        while( index < strLen && 
                !charInString( dataString.charAt( index ), "0123456789" ) )
        {
            index++;
        }
    
        testChar = dataString.charAt( index );
    
        while( index < strLen && charInString( testChar, "0123456789" ) )
        {
            numString += testChar;
        
            index++;
        }
    
        return Integer.parseInt( numString );
    }
    
    /**
     * traverses through all array bins, finds heights of each tree,
     * then displays as table
     * <p>
     * Shows table of tree heights, then shows table size
     * and number of digits of the student ID used for hashing,
     * then shows the number of empty bins and the tallest
     * tree height of the set
     */
    public void showHashTableStatus()
       {
        int index, heightValue = 0, nilCount = 0, maxHeight = -1;
        
        System.out.println( "Tree height report: " );
        System.out.print( " Index: ");
        
        for( index = 0; index < tableSize; index++ )
           {
            System.out.format( "%6d ", index );
           }
        
        System.out.println();
        System.out.print("         ");

        for( index = 0; index < tableSize; index++ )
           {
            System.out.print( "  -----");
           }
        
        System.out.println();
        System.out.print( "         ");
        
        for( index = 0; index < tableSize; index++ )
           {
            if( !tableArray[ index ].isEmpty() )
               {
                heightValue = tableArray[ index ].getTreeHeight();
                
                if( heightValue > maxHeight )
                   {
                    maxHeight = heightValue;
                   }
                
                System.out.format("%6d ", heightValue );                 
               }
            
            else
               {
                nilCount++;
                
                System.out.print("     * " );                 
               }
           }
        
        // final test for max after loop
        if( heightValue > maxHeight )
           {
            maxHeight = heightValue;
           }
        
        System.out.println( "\n\nWith a table size of " + tableSize );
        System.out.println( "The number of empty bins was "
              + nilCount + ", and the tallest tree height was "
              + maxHeight + '\n' );
       }

    /**
     * Local method uploads data character by character,
     * parses characters, and loads into hash data structure
     * <p>
     * Exception: If there is a file failure such as file not found,
     * method will return false
     * <p>
     * 
     * @param fileName name of file in local directory required for upload
     * 
     * @return returns Boolean evidence of success
     */
    public boolean loadDataFromFile( String fileName )
       {
        String name, idStr, genderStr, gpaStr;
        int idVal;
        char genderVal;
        double gpaVal;
        boolean failedAccess = false, endInput = false;

        try
           {
            // Open FileReader 
            fileIn = new FileReader( fileName );
           }
        
        catch( FileNotFoundException fnfe )
           {
            failedAccess = true;
            
            return false;
           }

        do
           {
            // get name
            name = getStringFromFile( SEMICOLON );
             
            if( name != "" )
               {
                // get student ID
                idStr = getStringFromFile( SEMICOLON );
                idVal = Integer.parseInt( idStr );
             
                // get gender
                genderStr = getStringFromFile( SEMICOLON );
                genderVal = genderStr.charAt( 0 );
             
                // get gpa
                gpaStr = getStringFromFile( CARRIAGE_RETURN_CHAR );
                gpaVal = Double.parseDouble( gpaStr );
             
                // load data into StudentClass object
                failedAccess = !addItem( name, idVal, genderVal, gpaVal );
               }
             
            else
               {
                endInput = true;
               }
           }
        while( !failedAccess && !endInput);
        
        try
           {
            if( fileIn != null )
               {
                fileIn.close();
               }
           }
        
        catch( IOException ioe )
           {
            System.out.println( "DATA ACCESS ERROR: Failure to close file" );
           }
            
        return !failedAccess;
       }

    /** Local method for getting a string with specified end characters,
     * ignoring most white space
     * 
     * @param endChar flag character to end input
     *  
     * @return integer character for use in input process
     */
    private String getStringFromFile( char endChar )
       { 
        int nextCharInt = 0;
        String outString = "";
        
        try
           {
            // skip leading white space
            do
               {
                nextCharInt = fileIn.read();                  
               }
            while( nextCharInt != END_OF_FILE_MARKER 
                     && ( (char)nextCharInt == SPACE 
                         || (char)nextCharInt == TAB_CHAR ) 
                             || (char)nextCharInt == NEWLINE_CHAR 
                                 || (char)nextCharInt == CARRIAGE_RETURN_CHAR );
            
            while( nextCharInt != END_OF_FILE_MARKER 
                             && (char)nextCharInt != endChar
                               && (char)nextCharInt != NEWLINE_CHAR 
                                  && (char)nextCharInt != CARRIAGE_RETURN_CHAR )
               {
                outString += (char)nextCharInt;
                
                nextCharInt = fileIn.read();
               }            
           }
        
        catch( IOException ioe )
           {
            System.out.println( "INPUT ERROR: Failure to capture character" );
            
            outString = "";
           }
        
        if( nextCharInt == END_OF_FILE_MARKER )
           {
            outString = "";
           }
        
        return outString;
       }
        
}
