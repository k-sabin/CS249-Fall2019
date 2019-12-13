package p6_package;

public class StudentLinkedListClass_Main
   {

   public static void main(String[] args)
      {
       ////TESTING LINKEDLIST ////
       System.out.println("TESTING LIST\n\n");
       StudentLinkedListClass sll = new StudentLinkedListClass();
       //new items for list
       StudentClass sl1 = new StudentClass("Sabin, Kyle", 100287, 'M', 2.86);
       StudentClass sl2 = new StudentClass("Voyles, Rose", 100286, 'F', 3.87);
       StudentClass sl3 = new StudentClass("Sanderson, Amy", 100340, 'F', 4.00);
       StudentClass sl4 = new StudentClass("Downard, Andrew", 100752, 'M', 3.68);
       StudentClass sl5 = new StudentClass("Smith, Jessica", 101734, 'F', 3.99);
       StudentClass sl6 = new StudentClass("Worley, Michelle", 103712, 'F', 3.75);
       
       //add at beginning
       System.out.println("Inserting Kyle and Rose at beginning");
       
       sll.insertDataAtBeginning(sl1);
       
       sll.insertDataAtBeginning(sl2);
       
       //print
       
       sll.displayList();
       System.out.println();
       
       System.out.println("Inserting Andrew and Amy at the end");
       //add at end
       
       sll.appendDataAtEnd(sl4);
       
       sll.appendDataAtEnd(sl3);
       
       //print
       
       sll.displayList();
       System.out.println();
       
       //get current size
       
       System.out.println("The current size is: " + sll.getCurrentSize());
       System.out.println();
       
       //add at nth
       System.out.println("Inserting Jess at position 2");
       sll.insertDataAtNthPosition(2, sl5);
       
       //print
       
       sll.displayList();
       System.out.println();
       
       //get current size
       
       System.out.println("The current size is (after insert): " + 
                                                   sll.getCurrentSize());
       System.out.println();
       
       //remove at nth
       System.out.println("Removing Jess");
       sll.removeNthStudent(2);
       
       //replace at nth
       System.out.println("Replacing Andrew with Michelle");
       System.out.println();
       sll.replaceDataAtNthPosition(3, sl6);
       
       //print
       sll.displayList();
       System.out.println();
       
       //get current size
       
       System.out.println("The current size is (after removal): " + 
               sll.getCurrentSize());
       System.out.println();
       
       //find student number
       
       System.out.println("Student at #3: " + sll.getNthStudent(3));
       System.out.println();
       
       //copy constructor testing
       StudentLinkedListClass sll2 = new StudentLinkedListClass(sll);
       
       //clear
       System.out.println("Clearing list");
       sll.clear();
       
       //print
       
       sll.displayList();
       System.out.println();
       
       
       
       ////TESTING ITERATOR ////
       System.out.println("\n\nTESTING ITERATOR\n\n");
       StudentLinkedIteratorClass sli = new StudentLinkedIteratorClass();
       //new items for iterator
       StudentClass si1 = new StudentClass("Sabin, Kyle", 100287, 'M', 2.86);
       StudentClass si2 = new StudentClass("Voyles, Rose", 100286, 'F', 3.87);
       StudentClass si3 = new StudentClass("Sanderson, Amy", 100340, 'F', 4.00);
       StudentClass si4 = new StudentClass("Downard, Andrew", 100752, 'M', 3.68);
       StudentClass si5 = new StudentClass("Smith, Jessica", 101734, 'F', 3.99);
       StudentClass si6 = new StudentClass("Worley, Michelle", 103712, 'F', 3.75);
       
       //insert before current
       System.out.println("Inserting Kyle and Rose before current");
       sli.insertBeforeCurrent(si1);
       sli.insertBeforeCurrent(si2);
       
       //print
       sli.runDiagnosticDisplay();
       System.out.println();
       
       //insert after current
       System.out.println("Inserting Amy and Andrew after current");
       sli.insertAfterCurrent(si3);
       sli.insertAfterCurrent(si4);
       
       //print
       sli.runDiagnosticDisplay();
       System.out.println();
       
       //set to end
       System.out.println("Setting iterator to end");
       sli.setToEnd();
       
       //print
       sli.runDiagnosticDisplay();
       System.out.println();
       
       //is at beginning
       System.out.println("Is the current index at the beginning?: " + sli.isAtBeginning());
       
       //is at end
       System.out.println("Is the current index at the end?: " + sli.isAtEnd());
       
       //move next
       System.out.println("Moving iterator to next position");
       sli.moveNext();
       
       //print
       sli.runDiagnosticDisplay();
       System.out.println();
       
       //move prev
       System.out.println("Moving iterator to previous position");
       sli.movePrev();
       
       //move prev
       System.out.println("Moving iterator to previous position");
       sli.movePrev();
       
       //move next();
       System.out.println("Moving iterator to next position");
       sli.moveNext();
       
       //print
       sli.runDiagnosticDisplay();
       System.out.println();
       
       //remove at current
       System.out.println("Removing at current");
       sli.removeAtCurrent();
       
       //print
       sli.runDiagnosticDisplay();
       System.out.println();
       
       //replace at current
       System.out.println("Replacing current with Michelle");
       sli.replaceAtCurrent(si6);
       
       //set to beginning
       System.out.println("Setting to beginning");
       sli.setToBeginning();
       
       //print
       sli.runDiagnosticDisplay();
       System.out.println();
       
       //is at beginning
       System.out.println("Is the current index at the beginning?: " + sli.isAtBeginning());
       
       //move prev
       System.out.println("Moving to previous position");
       sli.movePrev();
       
       //print
       sli.runDiagnosticDisplay();
       System.out.println();
       
       //copy constructor testing
       StudentLinkedIteratorClass sli2 = new StudentLinkedIteratorClass(sli);
       
       //clear
       System.out.println("Clearing iterator list");
       sli.clear();
       
       //print
       sli.runDiagnosticDisplay();
       System.out.println();
       
       
       ////TESTING STACK ////
       System.out.println("\n\nTESTING STACK\n\n");
       StudentLinkedStackClass sls = new StudentLinkedStackClass();
       StudentClass ss1 = new StudentClass("Sabin, Kyle", 100287, 'M', 2.86);
       StudentClass ss2 = new StudentClass("Voyles, Rose", 100286, 'F', 3.87);
       StudentClass ss3 = new StudentClass("Sanderson, Amy", 100340, 'F', 4.00);
       StudentClass ss4 = new StudentClass("Downard, Andrew", 100752, 'M', 3.68);
       StudentClass ss5 = new StudentClass("Smith, Jessica", 101734, 'F', 3.99);
       StudentClass ss6 = new StudentClass("Worley, Michelle", 103712, 'F', 3.75);
       
       //push
       System.out.println("Pushing Kyle onto stack");
       sls.push(ss1);
       
       //push
       System.out.println("Pushing Rose onto stack");
       sls.push(ss2);
       
       //push
       System.out.println("Pushing Amy onto stack");
       sls.push(ss3);
       
       //print
       sls.displayStack();
       System.out.println();
       
       //pop
       System.out.println("Popping Amy from stack");
       sls.pop();
       
       //pop
       System.out.println("Popping Rose from stack");
       sls.pop();
       
       //print
       
       sls.displayStack();
       System.out.println();
       
       //push
       System.out.println("Pushing Andrew onto stack");
       sls.push(ss4);
       
       //peek at top
       
       System.out.println("The student at the top of the stack is: " + 
                                                       sls.peekTop());
       System.out.println();
       
       //print
       
       sls.displayStack();
       System.out.println();
       
       //copy constructor testing
       StudentLinkedStackClass sls2 = new StudentLinkedStackClass(sls);
       
       //clear stack
       System.out.println("Clearing stack");
       sls.clear();
       
       //print
       
       sls.displayStack();
      }

   }
