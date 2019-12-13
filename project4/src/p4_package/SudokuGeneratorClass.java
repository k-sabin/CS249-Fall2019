package p4_package;

public class SudokuGeneratorClass
{
    private class CellNode
    {
        boolean fixedCell;
        int value;

        public CellNode()
        {
            //assume no value and fixed cell being false
            value = 0;
            fixedCell = false;
        }

        public CellNode(SudokuGeneratorClass.CellNode copied)
        {
            value = copied.value;
            fixedCell = copied.fixedCell;
        }
    }

    /**
     * Constant for side of grid
     */
    private final int GRID_SIDE = 9;

    /**
     * Constant for side of sub grid
     */
    private final int SUB_GRID_SIDE = 3;

    /**
     * Constant for range of numbers in Sudoku
     */
    private final int SUDOKU_RANGE = 9;

    /**
     * Two dimensional array for holding cell nodes with fixed/locked code and
     * number
     */
    private SudokuGeneratorClass.CellNode[][] sudokuArray;

    /**
     * Default generator class array sets up and initializes the Sudoku array
     */
    public SudokuGeneratorClass()
    {
        int rowIndex, colIndex;
        sudokuArray = new CellNode[GRID_SIDE][GRID_SIDE];
        //initialize array
        for(rowIndex = 0; rowIndex < GRID_SIDE; rowIndex++)
        {
            for(colIndex = 0; colIndex < GRID_SIDE; colIndex++)
            {
                sudokuArray[rowIndex][colIndex] = new CellNode();
            }
        }
    }

    /**
     * Generator class copy constructor
     * Note: Must create new CellNode for each copied element to eliminate
     * aliasing
     * @param copied - SudokuGeneratorClass object to be copied
     */

    public SudokuGeneratorClass(SudokuGeneratorClass copied)
    {
        //copy array data
        int innerIndex;
        int outerIndex;
        sudokuArray = new CellNode[GRID_SIDE][GRID_SIDE];

        for(outerIndex = 0; outerIndex < GRID_SIDE; outerIndex++)
        {
            for(innerIndex = 0; innerIndex < GRID_SIDE; innerIndex++)
            {
                sudokuArray[innerIndex][outerIndex] =
                    new CellNode(copied.sudokuArray[innerIndex][outerIndex]);
            }
        }

    }

    /**
     * Method called to create the Sudoku game
     * Note: Sets up sub diagonal grids and calls helper
     * @param numEntries integer value indicating how many Sudoku cells to
     *                   leave for the game player to fill in
     * @param showGrid Boolean value that supports display of the transactions
     *                 and the grids as the program runs
     */

    public void createSudoku(int numEntries, boolean showGrid)
    {
        setDiagonalSubGrids();
        createSudokuHelper(0,0,showGrid);
        removeNumbers(numEntries);
    }

    /**
     * Sudoku creation method tries new numbers at each cell from left to
     * right and top to bottom; backtracks if numbers don't work for given cell
     * @param rowPos integer row location of current element
     * @param colPos integer column location of current element
     * @param showGrid Boolean indicator that shows grids and transactions
     *                 as the method progresses if true
     * @return Boolean value to indicate success or failure of the 
     * recursive process
     */

    public boolean createSudokuHelper(int rowPos, int colPos, boolean showGrid)
    {
        int numberToCheck;
        //if col >= array size, increment row and set column to 0
        if(colPos >= GRID_SIDE)
        {
            rowPos++;
            colPos = 0;
            if(showGrid)
            {
                displayGrid();
            }
        }
        //if row >= array size, the sudoku board is complete, return true.
        if(rowPos >= GRID_SIDE)
        {
            return true;
        }
        //if it is a fixed cell, just skip past
        if(sudokuArray[rowPos][colPos].fixedCell)
        {
            return createSudokuHelper(rowPos, colPos+1,showGrid);
        }
        //else, run through the possibilities of all the numbers
        //recursive backtracking step
        else
        {
            for (numberToCheck = 1; numberToCheck <= SUDOKU_RANGE;
                    numberToCheck++)
            {
                if (hasConflict(rowPos, colPos, numberToCheck))
                {
                    sudokuArray[rowPos][colPos].value = numberToCheck;
                    if (createSudokuHelper(rowPos, colPos + 1, showGrid))
                    {
                        return true;
                    }
                }
                sudokuArray[rowPos][colPos].value = 0;
            }
        }
        //if no numbers work, return false, backtrack
        return false;
    }

    /**
     * Displays grid as it is currently set up
     * Uses character formatting for grid display
     */

    public void displayGrid()
    {
        int rowIndex, colIndex;
        for(rowIndex = 0; rowIndex < GRID_SIDE; rowIndex++)
        {
        	//formatting for beginning of a row
            if(rowIndex %3 == 0)
            {
                System.out.println("#===|===|===#===|===|===#===|===|===#");
            }
            else
            {
            	System.out.println("#---|---|---#---|---|---#---|---|---#");
            }
            
            //formatting for the columns
            System.out.print("#");
            for(colIndex = 0; colIndex < GRID_SIDE; colIndex++)
            {
                if(colIndex %3 == 0 && colIndex > 0)
                {
                    System.out.print("#");
                }
                else if(colIndex > 0)
                {
                    System.out.print("|");
                }
                //skip 0's, make it blank
                if(sudokuArray[rowIndex][colIndex].value == 0)
                {
                    System.out.print("   ");
                }
                else
                {
                    System.out.print(" " + 
                            sudokuArray[rowIndex][colIndex].value + " ");
                }
            }
            System.out.println("#");
            
        }
        System.out.println("#===|===|===#===|===|===#===|===|===#");
        System.out.println();
        System.out.println();
        System.out.println();
    }

    /**
     * Generates random value between 1 and 9
     * Note: Uses double stage process. Calls random to get number between
     * 1 and 9, then loops that many times generating random values.
     * Finally takes the last value generated. Uses Math.random
     * @return integer random value generated
     */

    public int genRandSudokuValue()
    {
        int loopIndex;
        int firstStage = (int)(Math.random() * ((9-1) + 1)) + 1;
        int finalRandom = firstStage;
        for(loopIndex = 1; loopIndex <= firstStage; loopIndex++)
        {
            finalRandom = (int)(Math.random() * SUDOKU_RANGE) + 1;
        }
        return finalRandom;
    }

    /**
     * Checks for conflict of a given number in a given element
     * Note: Uses isInRow, isInCol, and isInSubGrid in one line of code to
     * indicate if the number has already been used in the same row,
     * the same column, or the same sub grid
     * @param rowLocIndex integer row index of element
     * @param colLocIndex integer column index of element
     * @param value integer value to be tested for conflict
     * @return Boolean result of test
     */

    private boolean hasConflict(int rowLocIndex, int colLocIndex, int value)
    {
        return !isInCol(colLocIndex, value) && !isInRow(rowLocIndex, value) &&
                            !isInSubGrid(rowLocIndex, colLocIndex, value);
    }

    /**
     * Checks for conflict of value in the same column
     * @param colIndex integer column index
     * @param value integer value to be tested
     * @return Boolean result of test
     */

    private boolean isInCol(int colIndex, int value)
    {
        int rowIterator = 0;
        while(rowIterator < GRID_SIDE)
        {
            if(sudokuArray[rowIterator][colIndex].value == value)
            {
                return true;
            }
            rowIterator++;
        }
        return false;
    }

    /**
     * Checks for conflict of value in the same row
     * @param rowIndex integer row index
     * @param value integer value to be tested
     * @return Boolean result of test
     */

    private boolean isInRow(int rowIndex, int value)
    {
        int colIterator = 0;
        while(colIterator < GRID_SIDE)
        {
            if(sudokuArray[rowIndex][colIterator].value == value)
            {
                return true;
            }
            colIterator++;
        }
        return false;
    }

    /**
     * Checks for conflict of value in sub grid
     * Note: Must find upper left corner of sub grid from the row and column
     * indices, then search the sub grid
     * @param rowLocIndex integer row index of test item
     * @param colLocIndex integer column index of test item
     * @param value integer value to be tested
     * @return Boolean result of test
     */

    private boolean isInSubGrid(int rowLocIndex, int colLocIndex, int value)
    {
        //starting positions for the search function used later
        //will be determined once subGrid is found
        int subGridIndexRow, subGridIndexCol;

        //indices for loop
        int innerIndex, outerIndex;

        //finding rowIndex subGrid
        if(rowLocIndex < SUB_GRID_SIDE)
        {
            subGridIndexRow = 0;
        }
        else if(rowLocIndex < SUB_GRID_SIDE*2)
        {
            subGridIndexRow = 3;
        }
        else {
            subGridIndexRow = 6;
        }
        //finding columnIndex subGrid
        if(colLocIndex < SUB_GRID_SIDE)
        {
            subGridIndexCol = 0;
        }
        else if(colLocIndex < SUB_GRID_SIDE*2)
        {
            subGridIndexCol = 3;
        }
        else
        {
            subGridIndexCol = 6;
        }
        //so outer loop counts rows up to +2 of current
        for(outerIndex = subGridIndexRow; outerIndex <= subGridIndexRow +2;
            outerIndex++)
        {
            //inner loop counts cols up to +2 of current
            for(innerIndex = subGridIndexCol;
                    innerIndex <= subGridIndexCol +2;
            innerIndex++)
            {
                //if equal, return true
                if(sudokuArray[outerIndex][innerIndex].value == value)
                {
                    return true;
                }
            }
        }
        //else return false
        return false;
    }

    /**
     * Randomly removes the specified number of items from the 
     * Sudoku array for preparing the game
     * @param numbersToBeRemoved integer number of elements to be cleared
     */

    private void removeNumbers(int numbersToBeRemoved)
    {
        //generate random values -1 (genRandSudokuValue has a range of 1-9)
        int randomRow = genRandSudokuValue() - 1;
        int randomCol = genRandSudokuValue() - 1;
        int loopIndex;
        
        for(loopIndex = 0; loopIndex < numbersToBeRemoved; loopIndex++)
        {
            //if the value is equal to 0, generate another random 
        	//cell to remove
            while(sudokuArray[randomRow][randomCol].value == 0)
            {
                randomRow = genRandSudokuValue() - 1;
                randomCol = genRandSudokuValue() - 1;
            }
            //set value to 0
            sudokuArray[randomRow][randomCol].value = 0;
        }
        
    }

    /**
     * Sets all three diagonal sub grids in preparation for setting other
     * values
     * Note: Calls setInitialSubGrid for each grid to be set up
     */

    private void setDiagonalSubGrids()
    {
        //diagonal subgrids start at 0,0 && 3,3 && 6,6
        setInitialSubGrid(0,0);
        setInitialSubGrid(3,3);
        setInitialSubGrid(6,6);
    }

    /**
     * Sets one sub grid with non-repeating values
     * @param startRow integer row of upper left corner of sub grid to set up
     * @param startCol integer column of upper left corner of sub grid to
     *                 set up
     */

    private void setInitialSubGrid(int startRow, int startCol)
    {
        int randomToPlace;
        int rowIndex, colIndex;

        for(rowIndex = startRow; rowIndex <= startRow+2; rowIndex++)
        {
            for(colIndex = startCol; colIndex <= startCol+2; colIndex++)
            {
                //generate a random value and check if it already exists
                            // in the sub grid
                randomToPlace = genRandSudokuValue();
                while(isInSubGrid(rowIndex,colIndex,randomToPlace))
                {
                    //if value already exists, generate a new one
                    randomToPlace = genRandSudokuValue();
                }
                sudokuArray[rowIndex][colIndex].value = randomToPlace;
                sudokuArray[rowIndex][colIndex].fixedCell = true;
            }
        }
    }
}
