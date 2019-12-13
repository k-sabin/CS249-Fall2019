package p2_package;

public class TestingMain
{
    public static void main (String[] args)
    {
        RegistrarClass rc = new RegistrarClass(12);
        RegistrarClass rc2 = new RegistrarClass(12);

        StudentClass studentOne = new StudentClass("Susy", 1, 'F', 3.14);
        StudentClass studentTwo = new StudentClass("Michelle", 2, 'F', 3.71);
        StudentClass studentThree = new StudentClass("Kyle", 3, 'M', 2.81);
        StudentClass studentFour = new StudentClass("Andrew", 4, 'M', 3.76);
        StudentClass studentFive = new StudentClass("Rose", 5, 'F', 3.42);
        StudentClass studentSix = new StudentClass("Jessica", 6, 'F', 3.42);
        StudentClass studentSeven = new StudentClass("Chris", 7, 'M', 3.42);
        StudentClass studentEight = new StudentClass("Javier", 8, 'M', 3.42);
        StudentClass studentNine = new StudentClass("Harry", 9, 'M', 3.42);
        StudentClass studentTen = new StudentClass("Primrose", 10, 'F', 3.42);
        StudentClass studentEleven = new StudentClass("Carter", 11, 'M', 3.42);

        rc.addStudent(studentOne);
        rc.addStudent(studentTwo);
        rc.addStudent(studentThree);
        rc.addStudent(studentFour);
        rc.addStudent(studentFive);
        rc.addStudent(studentSix);
        rc.addStudent(studentSeven);
        rc.addStudent(studentEight);
        rc.addStudent(studentNine);
        rc.addStudent(studentTen);
        rc.addStudent(studentEleven);

        System.out.println("Array before sort:");
        rc.diagnosticArrayDump();

        //not working
        System.out.println();

        System.out.println("Running Shell Sort...");
        StudentClass[] arrayShell = rc.runShellSort();
        System.out.println("Array after sort:");
        printStudentArray(arrayShell);

        //working
        /*System.out.println();

        System.out.println("Running Bubble Sort");
        StudentClass[] arrayBubble = rc.runBubbleSort();
        System.out.println("Array after sort:");
        printStudentArray(arrayBubble);*/

        //working
        /*System.out.println();

        System.out.println("Running Insertion Sort...");
        StudentClass[] arrayInsert = rc.runInsertionSort();
        System.out.println("Array after sort:");
        printStudentArray(arrayInsert);*/

        //working
        /*System.out.println();

        System.out.println("Running Selection Sort...");
        StudentClass[] arraySelect = rc.runSelectionSort();
        System.out.println("Array after sort:");
        printStudentArray(arraySelect);*/
    }
    private static void printStudentArray(StudentClass[] array)
    {
        int index = 0;
        while(array[index] != null)
        {
            System.out.println(array[index].toString());
            index++;
        }
    }
}
