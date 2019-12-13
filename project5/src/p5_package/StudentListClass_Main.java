package p5_package;

public class StudentListClass_Main
   {

   public static void main(String[] args)
      {
///////////////// TEST ITERATOR //////////////////////////////////////////////////////////////*

       System.out.println("\tTESTING ITERATOR \n \n");
       StudentIteratorClass sic = new StudentIteratorClass( 25 );
       StudentClass si1 = new StudentClass( "Johnson, Robert", 603667, 'M', 2.844077875 );
       StudentClass si2 = new StudentClass( "Elliott, Cayley", 135658, 'F', 2.978848017 );
       StudentClass si3 = new StudentClass( "Reyes, Connor", 191261, 'M', 3.295578577 );
       StudentClass si4 = new StudentClass( "Rodriguez, Susan", 154838, 'F', 2.063213296 );
       StudentClass si5 = new StudentClass( "Penn, Frederick",  19367, 'M', 2.828974752 );
       StudentClass si6 = new StudentClass( "Deangelis, Shawna", 764050, 'F', 2.203316877 );

       sic.insertAfterCurrent( si1 );
       sic.insertAfterCurrent( si2 );
       sic.insertAfterCurrent( si3 );
       sic.runDiagnosticDisplay();
     //space for readability
       System.out.println();

       sic.insertBeforeCurrent( si4 );
       sic.insertBeforeCurrent( si5 );
       sic.insertBeforeCurrent( si6 );
       sic.setToEnd();
     //space for readability
       System.out.println();
       
       sic.runDiagnosticDisplay();
       
       sic.setToBeginning();
       
       System.out.println();
       
       sic.runDiagnosticDisplay();
     //space for readability
       System.out.println();
       
       System.out.println("Clearing iterator");
     //space for readability
       System.out.println();
       sic.clear();
       sic.setToEnd();
       sic.runDiagnosticDisplay();
     //space for readability
       System.out.println();
       sic.insertBeforeCurrent( si5 );
       sic.insertBeforeCurrent( si6 );
       sic.runDiagnosticDisplay();
     //space for readability
       System.out.println();
       
       sic.removeAtCurrent();
       sic.removeAtCurrent();
       sic.runDiagnosticDisplay();
     //space for readability
       System.out.println();
       
       sic.insertBeforeCurrent( si5 );
       sic.insertBeforeCurrent( si6 );
       sic.runDiagnosticDisplay();

       
///////////////// TEST QUEUE //////////////////////////////////////////////////////////////*
       System.out.println("\n \n\tTESTING QUEUE \n \n");
       StudentQueueClass sqc = new StudentQueueClass( 25 );
       StudentClass sq1 = new StudentClass( "Johnson, Robert", 603667, 'M', 2.844077875 );
       StudentClass sq2 = new StudentClass( "Elliott, Cayley", 135658, 'F', 2.978848017 );
       StudentClass sq3 = new StudentClass( "Reyes, Connor", 191261, 'M', 3.295578577 );
       StudentClass sq4 = new StudentClass( "Rodriguez, Susan", 154838, 'F', 2.063213296 );
       StudentClass sq5 = new StudentClass( "Penn, Frederick",  19367, 'M', 2.828974752 );
       StudentClass sq6 = new StudentClass( "Deangelis, Shawna", 764050, 'F', 2.203316877 );
       StudentClass sq7 = new StudentClass( "Shafer, Tristan", 693686, 'F', 3.180700609 );

       sqc.enqueue( sq1 );
       sqc.enqueue( sq2 );
       sqc.enqueue( sq3 ); 
       
       sqc.displayQueue();
     //space for readability
       System.out.println();
       
       sqc.enqueue( sq4 );
       sqc.enqueue( sq5 );
       sqc.enqueue( sq6 ); 
       
       sqc.displayQueue();
     //space for readability
       System.out.println();
       
       System.out.println( "Dequeued: " + sqc.dequeue().toString() );
       System.out.println( "Dequeued: " + sqc.dequeue().toString() );
       System.out.println( "Dequeued: " + sqc.dequeue().toString() );
       System.out.println( "Dequeued: " + sqc.dequeue().toString() );
     //space for readability
       System.out.println();
       
       sqc.displayQueue();

       sqc.clear();
     //space for readability
       System.out.println();
       sqc.displayQueue();
     //space for readability
       System.out.println();
       sqc.enqueue( sq5 );
       sqc.enqueue( sq6 );
       sqc.enqueue( sq7 ); 
       
       sqc.displayQueue();

       
///////////////// TEST LIST //////////////////////////////////////////////////////////////*
       System.out.println("\n \n\tTESTING LIST \n \n");
       StudentListClass slc = new StudentListClass( 25 );
       StudentClass s1 = new StudentClass( "Johnson, Robert", 603667, 'M', 2.844077875 );
       StudentClass s2 = new StudentClass( "Elliott, Cayley", 135658, 'F', 2.978848017 );
       StudentClass s3 = new StudentClass( "Reyes, Connor", 191261, 'M', 3.295578577 );
       StudentClass s4 = new StudentClass( "Rodriguez, Susan", 154838, 'F', 2.063213296 );
       StudentClass s5 = new StudentClass( "Penn, Frederick",  19367, 'M', 2.828974752 );
       StudentClass s6 = new StudentClass( "Deangelis, Shawna", 764050, 'F', 2.203316877 );
       StudentClass s7 = new StudentClass( "Shafer, Tristan", 693686, 'F', 3.180700609 );
       StudentClass s8 = new StudentClass( "Ruan, Francisco", 587182, 'M', 3.726603614 );
       StudentClass s9 = new StudentClass( "Werner, Riley", 444253, 'F', 2.34553428 );

       slc.appendDataAtEnd( s1 );
       slc.appendDataAtEnd( s2 );
       slc.appendDataAtEnd( s3 );
       
       slc.displayList();
     //space for readability
       System.out.println();
       
       int num = slc.findStudentNumber( s2 );
       
       System.out.println( "Number is: " + num );
       
       System.out.println( "Size: " + slc.getCurrentSize() );
       //space for readability
       System.out.println();
       
       slc.insertDataAtBeginning( s4 );
       slc.insertDataAtBeginning( s5 );
       slc.insertDataAtBeginning( s6 );
       
       slc.displayList();
       //space for readability
       System.out.println();
       
       System.out.println( "Nth Student: " + slc.getNthStudent( 5 ).toString() );
     //space for readability
       System.out.println();
       
       slc.insertDataAtNthPosition( 1, s7 );
       slc.insertDataAtNthPosition( 8, s8 );
       slc.insertDataAtNthPosition( 4, s9 );
       
       slc.displayList();
     //space for readability
       System.out.println();
       
       System.out.println( "Removed: " + slc.removeNthStudent( 7 ).toString());  
         
      }

   }