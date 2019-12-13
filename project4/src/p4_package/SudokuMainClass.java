package p4_package;

public class SudokuMainClass
{
    public static void main(String[] args)
    {
        SudokuGeneratorClass sudoku = new SudokuGeneratorClass();
        sudoku.createSudoku(0,true);
        SudokuGeneratorClass sudokuTwo = new SudokuGeneratorClass(sudoku);
        
    }
}
