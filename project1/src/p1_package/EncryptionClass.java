package p1_package;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EncryptionClass
{
    /**
     * Constant for maximum input char limit
     */
    private final int MAX_INPUT_CHARS = 256;

    /**
     * Constant for unprintable char value used as message end char
     */
    private final int UNPRINTABLE_CHAR_VALUE = 127; // ASCII for delete key

    /**
     * Constant for minus sign used in getAnInt
     */
    private final char MINUS_SIGN = '-';

    /**
     * Class Global FileReader variable so methods can be used
     */
    private FileReader fileIn;

    private int arrSide;

    private int[][] encryptedArr;

    public EncryptionClass()
    {
    }

    public EncryptionClass(EncryptionClass copied)
    {
        this.arrSide = copied.arrSide;
        this.encryptedArr = copied.encryptedArr;
        this.fileIn = copied.fileIn;
    }

    /**
     * tests and reports if a character is found in a given string
     *
     * @param testChar   character to be tested against the string
     * @param testString string within which the character is tested
     */
    private boolean charInString(char testChar, java.lang.String testString)
    {
        //index for looping through testString
        int index;

        for (index = 0; index < testString.length(); index++)
        {
            if (testString.charAt(index) == testChar)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Decrypts string from array
     * @return String result from decryption process
     */

    public java.lang.String decryptData()
    {
        int rowIndexInsideLoop, colIndexInsideLoop;
        int rowIndexOutsideLoop, colIndexOutsideLoop;

        //integer value for array length + width
        int stringIndex = 0;

        //return string buffer to store unencrypted string
        java.lang.String stringBuffer = "";

        //boolean that turns to true after finding ascii 127 value
        boolean endOfString = false;

        //setting starting points for the array indices
        rowIndexInsideLoop = arrSide - 1;
        colIndexInsideLoop = arrSide - 1;

        rowIndexOutsideLoop = arrSide - 1;
        colIndexOutsideLoop = arrSide - 1;

        while(rowIndexOutsideLoop >= 0 && colIndexOutsideLoop >= 0 &&
                                                        !endOfString)
        {
            while (rowIndexInsideLoop > -1 && colIndexInsideLoop < arrSide &&
                                                        !endOfString)
            {
                if(encryptedArr[rowIndexInsideLoop][colIndexInsideLoop] != 127)
                {
                    stringBuffer+=
                     (char)encryptedArr[rowIndexInsideLoop][colIndexInsideLoop];
                }
                else
                {
                    endOfString = true;
                }
                stringIndex++;
                rowIndexInsideLoop--;
                colIndexInsideLoop++;
            }
            if(colIndexOutsideLoop > 0)
            {
                colIndexOutsideLoop--;
            }
            else
            {
                rowIndexOutsideLoop--;
            }
            rowIndexInsideLoop = rowIndexOutsideLoop;
            colIndexInsideLoop = colIndexOutsideLoop;
        }
        return stringBuffer;
    }

    /**
     * Displays array in character form for diagnostics
     */

    public void displayCharArray()
    {
        int rowIndex,colIndex;

        for(rowIndex = 0; rowIndex < arrSide; rowIndex++)
        {
            for(colIndex = 0; colIndex < arrSide; colIndex++)
            {
                System.out.print((char)encryptedArr[rowIndex][colIndex]);
            }
            System.out.println();
        }
    }

    /**
     * Downloads encrypted data to file
     * <p>
     * Note: No action taken if array is empty
     *
     * @param fileName String object holding file name to use
     */
    void downloadData( String fileName )
    {
        FileWriter toFile;

        int rowIndex, colIndex;

        if( arrSide > 0 )
        {
            try
            {
                toFile = new FileWriter( fileName );

                toFile.write( "" + arrSide + "\r\n" );

                for( rowIndex = 0; rowIndex < arrSide; rowIndex++ )
                {
                    for( colIndex = 0; colIndex < arrSide; colIndex++ )
                    {
                        if( encryptedArr[ rowIndex ][ colIndex ] < 100 )
                        {
                            toFile.write( "0" );
                        }

                        toFile.write(""
                                + encryptedArr[ rowIndex ][ colIndex ] + " " );
                    }

                    toFile.write( "\r\n" );
                }

                toFile.flush();
                toFile.close();
            }

            catch( IOException ioe )
            {
                ioe.printStackTrace();
            }
        }
    }

    /**
     * Encrypts given string into array
     * <p>Note: Uses .charAt and .length Java utilities for String
     * Management</p>
     * @param toEncrypt - String object to be encrypted
     */

    public void encryptData(String toEncrypt)
    {
        int rowIndexInsideLoop, colIndexInsideLoop;
        int rowIndexOutsideLoop, colIndexOutsideLoop;

        int stringIndex = 0;
        //integer value for array length + width

        //get square root of toEncrypt length
        double rootOfStringLength = findSquareRoot(toEncrypt.length());

        //because we need to make space for the unprintable character,
                            //we will always need to ceiling the square root
        arrSide = (int) rootOfStringLength + 1;

        encryptedArr = new int[arrSide][arrSide];
        rowIndexInsideLoop = arrSide - 1;
        colIndexInsideLoop = arrSide - 1;

        rowIndexOutsideLoop = arrSide - 1;
        colIndexOutsideLoop = arrSide - 1;

        while(rowIndexOutsideLoop >= 0 && colIndexOutsideLoop >= 0)
        {
            while(rowIndexInsideLoop > -1 && colIndexInsideLoop < arrSide)
            {
                if(stringIndex < toEncrypt.length())
                {
                    encryptedArr[rowIndexInsideLoop][colIndexInsideLoop] =
                            toEncrypt.charAt(stringIndex);
                }
                else if(stringIndex == toEncrypt.length())
                {
                    encryptedArr[rowIndexInsideLoop][colIndexInsideLoop] = 127;
                }
                else
                {
                    encryptedArr[rowIndexInsideLoop][colIndexInsideLoop]
                            = getRandomCharValue();
                }
                stringIndex++;
                rowIndexInsideLoop--;
                colIndexInsideLoop++;

            }

            if(colIndexOutsideLoop > 0)
            {
                colIndexOutsideLoop--;
            }
            else
            {
                rowIndexOutsideLoop--;
            }
            rowIndexInsideLoop = rowIndexOutsideLoop;
            colIndexInsideLoop = colIndexOutsideLoop;
        }

    }

    /**
     * Finds the square root of an integer value
     * <p>Note: Finds square root to precision of 0.000001 without using any
     * Java utilities other than abs</p>
     * @param value - integer value to find square root of
     * @return double square root value
     */

    private static double findSquareRoot(int value)
    {
        value = Math.abs(value);
        //value that gets set as the maximum that the square root can be
        double high = value;
        //middle point of possible square root
        double mid = (double) value/2;
        //lowest possible value for square root
        double low = 0;

        //tolerance variables
        double highTolerance = (double) value + 0.000001;
        double lowTolerance = (double) value - 0.000001;

        //return variable
        double rootOfValue = 0;

        while(rootOfValue == 0)
        {
            if (mid*mid > highTolerance)
            {
                high = mid;
                mid = high /2;
            }
            else if (mid*mid < lowTolerance)
            {
                low = mid;
                mid = (high + low) / 2;
            }
            else
            {
                rootOfValue = mid;
            }
        }
        return rootOfValue;
    }

    /**
     * gets an integer from the input stream
     *
     * @param maxLength maximum length of characters
     * input to capture the integer
     *
     * @return integer captured from file
     */
    private int getAnInt( int maxLength )
    {
        int inCharInt;
        int index = 0;
        String strBuffer = "";
        int intValue;
        boolean negativeFlag = false;

        try
        {
            inCharInt = fileIn.read();

            // clear space up to number
            while( index < maxLength && !charInString( (char)inCharInt,
                    "0123456789+-" ) )
            {
                inCharInt = fileIn.read();

                index++;
            }

            if( inCharInt == MINUS_SIGN )
            {
                negativeFlag = true;

                inCharInt = fileIn.read();
            }

            while( charInString( (char)inCharInt, "0123456789" ) )
            {
                strBuffer += (char)( inCharInt );

                index++;

                inCharInt = fileIn.read();
            }
        }

        catch( IOException ioe )
        {
            System.out.println( "INPUT ERROR: Failure to capture character" );

            strBuffer = "";
        }

        intValue = Integer.parseInt( strBuffer );

        if( negativeFlag )
        {
            intValue *= -1;
        }

        return intValue;
    }

    /**
     * Generates the integer value of a random character between the lowest
     * possible value (space) and the highest possible character value (tilde)
     * <p>Note: Method must be capable of generating a space value, a
     * tilde value, and any possible character between; may use any
     * appropriate Math utilities</p>
     * @return integer value of randomly generated character
     */

    private int getRandomCharValue()
    {
        double returnValue;
        int minValue = 32;
        int maxValue = 126;
        double randomSeed = Math.random();

        returnValue = randomSeed*(maxValue - minValue) + minValue;

        return (int) returnValue;
    }

    /**
     * Uploads data from file holding a square array
     *
     * @param fileName String object holding file name
     */
    void uploadData( String fileName )
    {
        int rowIndex, colIndex;

        try
        {
            // Open FileReader
            fileIn = new FileReader( fileName );

            // get side length
            arrSide = getAnInt( MAX_INPUT_CHARS );

            encryptedArr = new int[ arrSide ][ arrSide ];

            for( rowIndex = 0; rowIndex < arrSide; rowIndex++ )
            {
                for( colIndex = 0; colIndex < arrSide; colIndex++ )
                {
                    encryptedArr[ rowIndex ][ colIndex ]
                            = getAnInt( MAX_INPUT_CHARS );
                }
            }

            fileIn.close();
        }

        // for opening file
        catch( FileNotFoundException fnfe )
        {
            fnfe.printStackTrace();
        }

        // for closing file
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }

    }


}
