# CS249-Project4
CS249 Project 4 Fall 2019


## ASSIGNMENT OBJECTIVES

- Students will review the development of a basic data type that incorporates the use of array data management

- Students will review use of Java arrays, and practice basic algorithmic activities with arrays

- Students will review the pass-by-copy practice of Java, along with managing potential aliasing issues in method development

- Students will practice development, implementation, and usage of supporting/utility methods

- Students will generate classes that manage data in the form of a two dimensional array

- Students will practice implementing methods in such a way that code is rarely, if ever, repeated by implementing supporting or utility methods in their development activities

- Students will demonstrate analytic and diagnostic competence with testing and verifying all components of the given assignment

- Students will develop and analyze one or more recursive backtracking operations

## ASSIGNMENT OVERVIEW

- Students will develop a class that creates a Sudoku game using recursive backtracking

## ASSIGNMENT SPECIFICATIONS

- Students must develop a recursive backtracking operation that creates a Sudoku game

- Students must develop given supporting methods and use ALL of these methods to support the Sudoku creation process.
  - development will start by creating three subgrids (3x3) and placing them diagonally across the playing board; each element in these subgrids has its "fixed" state set to true so that it will not be changed by the subsequent operations

  - the createSudoku method must only set the diagonal subgrids, call the recursive helper method, remove the specified number of elements once the board has been completed, and display the playing board solution. It must not conduct any other actions

  - the createSudokuHelper starts in the upper left corner of the playing board and recursively travels across the board in row major fashion (i.e., start at a row, exhaust the columns, and then go to the next row, etc.), setting and testing each element for conflict (e.g., same number in the same row, same column, or same subgrid), and backtracking if all possible values at that element location have been exhausted. Note that as part of this process, it must not attempt to modify the values set in the original three subgrids; these items' fixed flags will be set to true

  - when the recursive process reaches the bottom of the playing board with correct values (i.e., no disqualifying conflicts), the method returns true, and as stated above, the number of values requested by the user are randomly removed (in the createSudoku method) to complete the game creation

  - finally, if the user sets the "showGrid" parameter to true, the method must display the grid at the end of each completed row in addition to each time a new conflict-free number is being tested

- Students will also document all classes and methods using the Javadoc commenting process; comments are not required to be exactly the same as found in the supporting document (below) but they should be semantically equivalent

- Students will upload the SudokuGeneratorClass.java file to this assignment; any other uploaded files will result in a reduction of the project grade, and in some cases, may cause a complete loss of credit (e.g., uploading *.class, or other unusable files)
